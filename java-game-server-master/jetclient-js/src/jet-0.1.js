(function (jet) {
    "use strict";

    // Event code Constants
    jet.ANY = 0x00;
    jet.PROTOCOL_VERSION = 0x01;

    jet.CONNECT = 0x02;
    jet.RECONNECT = 0x03;
    jet.CONNECT_FAILED = 0x06;
    jet.LOG_IN = 0x08;
    jet.LOG_OUT = 0x0a;
    jet.LOG_IN_SUCCESS = 0x0b;
    jet.LOG_IN_FAILURE = 0x0c;
    jet.LOG_OUT_SUCCESS = 0x0e;
    jet.LOG_OUT_FAILURE = 0x0f;

    jet.GAME_LIST = 0x10;
    jet.ROOM_LIST = 0x12;
    jet.GAME_ROOM_JOIN = 0x14;
    jet.GAME_ROOM_LEAVE = 0x16;
    jet.GAME_ROOM_JOIN_SUCCESS = 0x18;
    jet.GAME_ROOM_JOIN_FAILURE = 0x19;

    //Event sent from server to client to start message sending from client to server.
    jet.START = 0x1a;
    // Event sent from server to client to stop messages from being sent to server.
    jet.STOP = 0x1b;
    // Incoming data from server or from another session.
    jet.SESSION_MESSAGE = 0x1c;
    // This event is used to send data from the current machine to remote server
    jet.NETWORK_MESSAGE = 0x1d;
    jet.CHANGE_ATTRIBUTE = 0x20;
    jet.DISCONNECT = 0x22;// Use this one for handling close event of ws.
    jet.EXCEPTION = 0x24;

    // Functions
    // Creates a new event object
    jet.NEvent = function (eventType, payload, session, date) {
        return {
            type : eventType,
            source : payload,
            target: session,
            timeStamp : (typeof date === 'undefined') ? new Date().getTime() : date.getTime()
        };
    };

    // Creates a login event object to login to remote jetserver
    jet.LoginEvent = function (config) {
        return jet.NEvent(jet.LOG_IN, [config.user, config.pass, config.connectionKey]);
    };

    // If using a differnt protocol, then use this codec chain, to decode and encode incoming and outgoing requests. Something like a Chain of Responsibility pattern.
    jet.CodecChain = function () {
        this.chain = [];
    };

    jet.CodecChain.prototype.add = function (func) {
        if (func && typeof (func) === 'function') {
            this.chain.push(func);
        } else {
            throw new Error("Parameter:" + func + " is not of type function.");
        }
        return this;
    };

    jet.CodecChain.prototype.remove = function (func) {
        removeFromArray(this.chain, func);
    };

    jet.CodecChain.prototype.tranform = function (message) {
        var i = 0;
        for(;i<this.chain.length(); i++){
            message = this.chain[i].transform(message);
        }
        return message;
    };

    // Default codes which use JSON to decode and encode messages.
    jet.Codecs = {
        encoder : {transform: function (e){ return JSON.stringify(e)}},
        decoder : {transform: function (e){
                        var evt = JSON.parse(e);
                        if((typeof evt.type !== 'undefined') && (evt.type === jet.NETWORK_MESSAGE)){
                            evt.type = jet.SESSION_MESSAGE;
                        }
                        return evt;
                    }
                  }
    };

    // Generally a client needs only one session to the server. But this function can be used to create more.
    jet.sessionFactory = function (url, config, callback) {
        new Session(url,config,callback);
    };

    jet.reconnect = function (session, reconnectPolicy, callback) {
        reconnectPolicy(session, callback);
    };

    // Used to create a session. Once START event is received from the remote jetserver then the callback is invoked with the created session.
    function Session(url, config, callback) {
        var me = this;
        var onStart = callback;
        var callbacks = {};
        me.inCodecChain = (typeof config.inCodecChain === 'undefined') ? jet.Codecs.decoder : config.inCodecChain;
        me.outCodecChain = (typeof config.outCodecChain === 'undefined') ? jet.Codecs.encoder : config.outCodecChain;
        var message = getLogin(config);
        var ws = connectWebSocket(url);
        var state = 0;// 0=CONNECTING, 1=CONNECTED, 2=NOT CONNECTED, 3=CLOSED
        var reconnectKey;

        function connectWebSocket(url) {
            ws = new WebSocket(url);            
            ws.onopen = function() {
                if (state === 0) {
                    ws.send(message);
                } else if (state === 2) {
                    ws.send(getReconnect(config));
                } else {
                    var evt = jet.NEvent(jet.EXCEPTION,"Cannot reconnect when session state is: " + state);
                    me.onerror(evt);
                    dispatch(jet.EXCEPTION, evt);
                }
            };
        
            // Login to jetserver when the start event is received the callback will return the session.
            ws.onmessage = function (e) {
                var loginDecoder = (typeof config.loginDecoder === 'undefined') ? jet.Codecs.decoder : config.loginDecoder;
                var evt = loginDecoder.transform(e.data);
                if(!evt.type){
                    throw new Error("Event object missing 'type' property.");
                }
                if(evt.type === jet.LOG_IN_FAILURE || evt.type === jet.GAME_ROOM_JOIN_FAILURE){
                    ws.close();
                }
                if(evt.type === jet.GAME_ROOM_JOIN_SUCCESS) {
                    reconnectKey = (typeof evt.source === 'undefined') ? reconnectKey : evt.source;
                }
                if(evt.type === jet.START){
                    if (onStart && typeof(onStart) === 'function') {
                        state = 1;
                        applyProtocol(config);
                        onStart(me);
                    }
                }
            };

            ws.onclose = function (e) {
                state = 2;  
                dispatch(jet.DISCONNECT, jet.NEvent(jet.DISCONNECT, e, me));
            };

            ws.onerror = function (e) {
                state = 2;
                dispatch(jet.EXCEPTION, jet.NEvent(jet.EXCEPTION, e, me));
            };
            return ws;
        }

        me.onmessage = doNothing;
        me.onerror = doNothing;
        me.onclose = doNothing;

        me.onevent = function (evt) {
            dispatch(evt.type, evt);
        };
        me.on = me.onevent;// alias for onevent

        me.send = function (evt) {
            if(state !== 1 && !((evt.type === jet.RECONNECT) && (state === 2))){
               throw new Error("Session is not in connected state"); 
            }
            ws.send( me.outCodecChain.transform(evt) ); // <= send JSON/Binary data to socket server
            return me; // chainable
        };

        me.addHandler = function(eventName, callback) {
            callbacks[eventName] = callbacks[eventName] || [];
            callbacks[eventName].push(callback);
            return me;// chainable
        };
        
        me.removeHandler = function(eventName, handler) {
            removeFromArray(callbacks[eventName], handler);
        };

        me.clearHandlers = function () {
            callbacks = {};
            me.onerror = doNothing;
            me.onmessage = doNothing;
            me.onclose = doNothing;
        };
        
        me.close = function () {
            state = 3;
            ws.close();
            dispatch(jet.CLOSED, jet.NEvent(jet.CLOSED));
        };
        
        me.disconnect = function () { 
            state = 2;
            ws.close();
        };
        
        me.reconnect = function (callback) {
            if (state !== 2) {
                throw new Error("Session is not in not-connected state. Cannot reconnect now"); 
            }
            onStart = callback;
            ws = connectWebSocket(url);      
        };
        
        me.setState = function (newState) {state = newState};
        
        function dispatch(eventName, evt) {
            if (typeof evt.target === 'undefined') {
                evt.target = me;
            }
            if (eventName === jet.SESSION_MESSAGE){
                me.onmessage(evt);
            }
            if (eventName === jet.CLOSED){
                me.onclose(evt);
            }
            dispatchToEventHandlers(callbacks[jet.ANY], evt);
            dispatchToEventHandlers(callbacks[eventName], evt);
        }
        
        function dispatchToEventHandlers(chain, evt) {
            if(typeof chain === 'undefined') return; // no callbacks for this event
            for(var i = 0; i < chain.length; i++){
              chain[i]( evt );
            }
        }
        
        function getLogin(config) {
            var loginEncoder = (typeof config.loginEncoder === 'undefined') ? jet.Codecs.encoder : config.loginEncoder;
            return loginEncoder.transform(jet.LoginEvent(config));
        }
        
        function getReconnect(config) {
            if (typeof reconnectKey === 'undefined') throw new Error("Session does not have reconnect key");
            var loginEncoder = (typeof config.loginEncoder === 'undefined') ? jet.Codecs.encoder : config.loginEncoder;
            return loginEncoder.transform(jet.NEvent(jet.RECONNECT, reconnectKey));
        }
        
        function applyProtocol(config) {
            ws.onmessage = (typeof config.protocol === 'undefined') ? protocol : config.protocol;
        }
        
        function protocol(e) {
            var evt = me.inCodecChain.transform(e.data);
            dispatch(evt.type, evt);
        }
        
        function doNothing(evt) {}
    }

    jet.ReconnetPolicies = {
        noReconnect : function (session, callback) { session.close() } ,
        reconnectOnce : function (session, callback) {
            session.reconnect(callback);
        }
    };

    function removeFromArray(chain, func) {
        if(chain instanceof Array){
            var index = chain.indexOf(func);
            while(index !== -1){
                chain.splice(index,1);
                index = chain.indexOf(func);
            }
        }
    }

}( window.jet = window.jet || {}));