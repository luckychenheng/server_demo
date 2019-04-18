package com.spring.cloud.netty.client.handler;

import com.spring.cloud.netty.common.constant.Const;
import com.spring.cloud.netty.common.constant.MessageDataConstant;
import com.spring.cloud.netty.common.entity.LoginInfo;
import com.spring.cloud.netty.common.entity.SendDataInfo;
import com.spring.cloud.netty.common.enums.CmdTypeEnum;
import com.spring.cloud.netty.common.protocol.SerializationUtil;
import com.spring.cloud.netty.common.util.BuildByteBuf;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @author wangmj
 * @since 2018/11/7
 */
@Slf4j
public class ClientMessageHandler extends ChannelInboundHandlerAdapter {

    boolean _verify = false;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("客户端登录开始");
        login(ctx);
    }

    private void login(ChannelHandlerContext ctx) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUserId(UUID.randomUUID().toString());
        loginInfo.setUserName("root");
        loginInfo.setPassword("123456");
        loginInfo.setPlatform("ios");
        loginInfo.setVersion("1.0");
        byte[] loginData = SerializationUtil.serialize(loginInfo);
        ByteBuf data = BuildByteBuf.build(loginData, Const.CONNECT_REQ);
        ctx.writeAndFlush(data);
        log.info("发送登录请求，userId:{}", loginInfo.getUserId());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws InterruptedException {
        ByteBuf byteBuf = (ByteBuf) msg;
        short cmd = byteBuf.readShort();
        int length = byteBuf.readInt();//数据长度
        byte result = byteBuf.readByte();
        if (length == 1 && result == MessageDataConstant.SUCCESS_CODE) {
            log.info(new Date() + ": 客户端读到数据,{}", CmdTypeEnum.getTypeEnum(cmd).getCmdName());
            _verify = true;
        }
//        if (_verify) {
//        sendMessage(ctx);
//            Thread.sleep(100);
//        }

    }

    private void sendMessage(ChannelHandlerContext ctx) {
        SendDataInfo sendDataInfo = new SendDataInfo();
        sendDataInfo.setCreatedDate(new Date());
        sendDataInfo.setUserName("Tom");
        Random rand = new Random();
        sendDataInfo.setContent("Hello, I am Tom11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111!" + rand.nextInt(100));
        byte[] serialize = SerializationUtil.serialize(sendDataInfo);
        ByteBuf data = BuildByteBuf.build(serialize, Const.SEND_DATA);
        ctx.writeAndFlush(data);
    }
}
