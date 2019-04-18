package com.spring.cloud.netty.client.handler;

import com.spring.cloud.netty.common.constant.Const;
import com.spring.cloud.netty.common.constant.MessageDataConstant;
import com.spring.cloud.netty.common.enums.CmdTypeEnum;
import com.spring.cloud.netty.common.util.BuildByteBuf;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author wangmj
 * @since 2018/11/22
 */
@Slf4j
public class ClientHeartBeatHandler extends ChannelInboundHandlerAdapter {

    private volatile ScheduledFuture<?> heartBeat;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        short cmd = byteBuf.readShort();
        int length = byteBuf.readInt();//数据长度
        byte result = byteBuf.readByte();
        if (length == 1 && result == MessageDataConstant.SUCCESS_CODE && cmd == Const.CONNECT_RESP) {
            heartBeat = ctx.executor().scheduleAtFixedRate(
                    new ClientHeartBeatHandler.HeartBeatTask(ctx), 0, 3000,
                    TimeUnit.MILLISECONDS);
            log.info(new Date() + ": 客户端读到数据,{}", CmdTypeEnum.getTypeEnum(cmd).getCmdName());
        }
        super.channelRead(ctx, msg);
    }

    private class HeartBeatTask implements Runnable {
        private final ChannelHandlerContext ctx;

        public HeartBeatTask(final ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            ByteBuf byteBuf = BuildByteBuf.build(new byte[Const.HEART_BEAT_DATA], Const.HEART_BEAT);
            log.info("Client send heart beat messsage to server : ---> "
                    + Const.HEART_BEAT_DATA);
            System.out.println("Client send heart beat messsage to server : ---> "
                    + Const.HEART_BEAT_DATA);
            ctx.writeAndFlush(byteBuf);
        }
    }
}
