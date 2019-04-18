package org.menacheri.jetclient.codecs.impl 
{
	import flash.utils.ByteArray;
	import org.menacheri.jetclient.codecs.Transform;
	import org.menacheri.jetclient.communication.MessageBuffer;
	import org.menacheri.jetclient.event.JetEvent;
	/**
	 * Converts an incoming JetEvent into a ByteArray. It will read the event type and 
	 * the source of the event and write them to a ByteArray.
	 * @author Abraham Menacherry
	 */
	public class MessageBufferEventEncoder implements Transform
	{
		import org.menacheri.jetclient.event.Events;
		
		public function MessageBufferEventEncoder() 
		{
			
		}
		
		public function transform(input:Object):Object
		{
			var event:JetEvent = input as JetEvent;
			var message:ByteArray = new ByteArray();
			var opCode:int = event.getType();
			message.writeByte(opCode);
			if (opCode == Events.LOG_IN) {
				message.writeByte(Events.JET_PROTOCOL);
			}
			var messageBuffer:MessageBuffer = event.getSource() as MessageBuffer
			message.writeBytes(messageBuffer.getBuffer());
			return message;
		}
	}

}