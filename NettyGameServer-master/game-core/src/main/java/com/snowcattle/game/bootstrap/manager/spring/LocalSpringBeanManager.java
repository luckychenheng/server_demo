package com.snowcattle.game.bootstrap.manager.spring;

import com.snowcattle.game.logic.net.NetMessageProcessLogic;
import com.snowcattle.game.logic.net.NetMessageTcpDispatchLogic;
import com.snowcattle.game.service.message.command.MessageCommandFactory;
import com.snowcattle.game.service.message.decoder.NetProtoBufHttpMessageDecoderFactory;
import com.snowcattle.game.service.message.decoder.NetProtoBufTcpMessageDecoderFactory;
import com.snowcattle.game.service.message.decoder.NetProtoBufUdpMessageDecoderFactory;
import com.snowcattle.game.service.message.encoder.NetProtoBufHttpMessageEncoderFactory;
import com.snowcattle.game.service.message.encoder.NetProtoBufTcpMessageEncoderFactory;
import com.snowcattle.game.service.message.encoder.NetProtoBufUdpMessageEncoderFactory;
import com.snowcattle.game.service.message.factory.TcpMessageFactory;
import com.snowcattle.game.service.net.tcp.pipeline.DefaultTcpServerPipeLine;
import com.snowcattle.game.service.net.tcp.pipeline.DefaultUdpServerPipeLine;
import com.snowcattle.game.service.net.tcp.session.builder.NettyTcpSessionBuilder;
import com.snowcattle.game.service.net.tcp.session.builder.NettyUdpSessionBuilder;
import com.snowcattle.game.service.rpc.client.RpcRequestFactory;
import com.snowcattle.game.service.rpc.serialize.protostuff.ProtostuffSerializeI;
import com.snowcattle.game.service.uuid.LongIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by jiangwenping on 17/3/2.
 * 这里的都是单例
 *
 */
@Repository
public class LocalSpringBeanManager {

    @Autowired
    private NettyTcpSessionBuilder nettyTcpSessionBuilder;

    @Autowired
    private NettyUdpSessionBuilder nettyUdpSessionBuilder;

    @Autowired
    private LongIdGenerator longIdGenerator;

    @Autowired
    private NetMessageTcpDispatchLogic netMessageTcpDispatchLogic;

    @Autowired
    private NetMessageProcessLogic netMessageProcessLogic;

    @Autowired
    private DefaultTcpServerPipeLine defaultTcpServerPipeLine;

    @Autowired
    private DefaultUdpServerPipeLine defaultUdpServerPipeLine;

    @Autowired
    private TcpMessageFactory tcpMessageFactory;

    @Autowired
    private ProtostuffSerializeI protostuffSerialize;

    @Autowired
    private RpcRequestFactory requestFactory;

    @Autowired
    private MessageCommandFactory messageCommandFactory;

    @Autowired
    private NetProtoBufTcpMessageDecoderFactory netProtoBufTcpMessageDecoderFactory;

    @Autowired
    private NetProtoBufUdpMessageDecoderFactory netProtoBufUdpMessageDecoderFactory;

    @Autowired
    private NetProtoBufTcpMessageEncoderFactory netProtoBufTcpMessageEncoderFactory;

    @Autowired
    private NetProtoBufUdpMessageEncoderFactory netProtoBufUdpMessageEncoderFactory;

    @Autowired
    private NetProtoBufHttpMessageDecoderFactory netProtoBufHttpMessageDecoderFactory;

    @Autowired
    private NetProtoBufHttpMessageEncoderFactory netProtoBufHttpMessageEncoderFactory;

    public NettyUdpSessionBuilder getNettyUdpSessionBuilder() {
        return nettyUdpSessionBuilder;
    }

    public void setNettyUdpSessionBuilder(NettyUdpSessionBuilder nettyUdpSessionBuilder) {
        this.nettyUdpSessionBuilder = nettyUdpSessionBuilder;
    }

    public NettyTcpSessionBuilder getNettyTcpSessionBuilder() {
        return nettyTcpSessionBuilder;
    }

    public void setNettyTcpSessionBuilder(NettyTcpSessionBuilder nettyTcpSessionBuilder) {
        this.nettyTcpSessionBuilder = nettyTcpSessionBuilder;
    }

    public LongIdGenerator getLongIdGenerator() {
        return longIdGenerator;
    }

    public void setLongIdGenerator(LongIdGenerator longIdGenerator) {
        this.longIdGenerator = longIdGenerator;
    }

    public NetMessageTcpDispatchLogic getNetMessageTcpDispatchLogic() {
        return netMessageTcpDispatchLogic;
    }

    public void setNetMessageTcpDispatchLogic(NetMessageTcpDispatchLogic netMessageTcpDispatchLogic) {
        this.netMessageTcpDispatchLogic = netMessageTcpDispatchLogic;
    }

    public NetMessageProcessLogic getNetMessageProcessLogic() {
        return netMessageProcessLogic;
    }

    public void setNetMessageProcessLogic(NetMessageProcessLogic netMessageProcessLogic) {
        this.netMessageProcessLogic = netMessageProcessLogic;
    }

    public DefaultTcpServerPipeLine getDefaultTcpServerPipeLine() {
        return defaultTcpServerPipeLine;
    }

    public void setDefaultTcpServerPipeLine(DefaultTcpServerPipeLine defaultTcpServerPipeLine) {
        this.defaultTcpServerPipeLine = defaultTcpServerPipeLine;
    }

    public DefaultUdpServerPipeLine getDefaultUdpServerPipeLine() {
        return defaultUdpServerPipeLine;
    }

    public void setDefaultUdpServerPipeLine(DefaultUdpServerPipeLine defaultUdpServerPipeLine) {
        this.defaultUdpServerPipeLine = defaultUdpServerPipeLine;
    }

    public TcpMessageFactory getTcpMessageFactory() {
        return tcpMessageFactory;
    }

    public void setTcpMessageFactory(TcpMessageFactory tcpMessageFactory) {
        this.tcpMessageFactory = tcpMessageFactory;
    }

    public ProtostuffSerializeI getProtostuffSerialize() {
        return protostuffSerialize;
    }

    public void setProtostuffSerialize(ProtostuffSerializeI protostuffSerialize) {
        this.protostuffSerialize = protostuffSerialize;
    }

    public RpcRequestFactory getRequestFactory() {
        return requestFactory;
    }

    public void setRequestFactory(RpcRequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    public MessageCommandFactory getMessageCommandFactory() {
        return messageCommandFactory;
    }

    public void setMessageCommandFactory(MessageCommandFactory messageCommandFactory) {
        this.messageCommandFactory = messageCommandFactory;
    }

    public NetProtoBufTcpMessageDecoderFactory getNetProtoBufTcpMessageDecoderFactory() {
        return netProtoBufTcpMessageDecoderFactory;
    }

    public void setNetProtoBufTcpMessageDecoderFactory(NetProtoBufTcpMessageDecoderFactory netProtoBufTcpMessageDecoderFactory) {
        this.netProtoBufTcpMessageDecoderFactory = netProtoBufTcpMessageDecoderFactory;
    }

    public NetProtoBufUdpMessageDecoderFactory getNetProtoBufUdpMessageDecoderFactory() {
        return netProtoBufUdpMessageDecoderFactory;
    }

    public void setNetProtoBufUdpMessageDecoderFactory(NetProtoBufUdpMessageDecoderFactory netProtoBufUdpMessageDecoderFactory) {
        this.netProtoBufUdpMessageDecoderFactory = netProtoBufUdpMessageDecoderFactory;
    }

    public NetProtoBufTcpMessageEncoderFactory getNetProtoBufTcpMessageEncoderFactory() {
        return netProtoBufTcpMessageEncoderFactory;
    }

    public void setNetProtoBufTcpMessageEncoderFactory(NetProtoBufTcpMessageEncoderFactory netProtoBufTcpMessageEncoderFactory) {
        this.netProtoBufTcpMessageEncoderFactory = netProtoBufTcpMessageEncoderFactory;
    }

    public NetProtoBufUdpMessageEncoderFactory getNetProtoBufUdpMessageEncoderFactory() {
        return netProtoBufUdpMessageEncoderFactory;
    }

    public void setNetProtoBufUdpMessageEncoderFactory(NetProtoBufUdpMessageEncoderFactory netProtoBufUdpMessageEncoderFactory) {
        this.netProtoBufUdpMessageEncoderFactory = netProtoBufUdpMessageEncoderFactory;
    }

    public NetProtoBufHttpMessageDecoderFactory getNetProtoBufHttpMessageDecoderFactory() {
        return netProtoBufHttpMessageDecoderFactory;
    }

    public void setNetProtoBufHttpMessageDecoderFactory(NetProtoBufHttpMessageDecoderFactory netProtoBufHttpMessageDecoderFactory) {
        this.netProtoBufHttpMessageDecoderFactory = netProtoBufHttpMessageDecoderFactory;
    }

    public NetProtoBufHttpMessageEncoderFactory getNetProtoBufHttpMessageEncoderFactory() {
        return netProtoBufHttpMessageEncoderFactory;
    }

    public void setNetProtoBufHttpMessageEncoderFactory(NetProtoBufHttpMessageEncoderFactory netProtoBufHttpMessageEncoderFactory) {
        this.netProtoBufHttpMessageEncoderFactory = netProtoBufHttpMessageEncoderFactory;
    }
}
