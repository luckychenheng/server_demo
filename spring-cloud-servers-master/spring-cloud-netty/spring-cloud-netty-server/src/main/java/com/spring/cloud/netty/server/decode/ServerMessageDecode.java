package com.spring.cloud.netty.server.decode;

import com.spring.cloud.netty.common.constant.MessageDataConstant;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 服务端解码器
 *
 * @author wangmj
 * @since 2018/11/21
 */
@Slf4j
public class ServerMessageDecode extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        int readableBytes = msg.readableBytes();
        int readerIndex = msg.readerIndex();

        if (readableBytes < MessageDataConstant.MIN_LENGTH) {
            return;
        }

        int headIndex = msg.indexOf(readerIndex, readerIndex + readableBytes, MessageDataConstant.CMD_HEAD);
        if (headIndex < 0) {//no valid data,skip all
            msg.skipBytes(readableBytes);
            return;
        }

        int endIndex = msg.indexOf(headIndex , msg.readerIndex() + readableBytes, MessageDataConstant.CMD_END);
        if (endIndex < 0) {//no end flag found
            if (readableBytes < MessageDataConstant.MAX_LENGTH) {//wait for the end flag
                return;
            } else {//if the readable bytes is greater than 2K, skip the wrong data(it should not happen)
                msg.skipBytes(readableBytes);
                return;
            }
        }

        int wholePackSize = endIndex - headIndex + 1;

        if (wholePackSize >= MessageDataConstant.MIN_LENGTH) {//correct data
            msg.readerIndex(headIndex);//set the headIndex to next readerIndex;
            msg.skipBytes(1);//skip cmdHead
            int magic = msg.readInt();
            if (magic != MessageDataConstant.MAGIC_DATA) {
                log.info("不符合数据传输协议,,magic:{}", magic);
                ctx.channel().close();
                return;
            }
            msg.skipBytes(1);//skip version
            out.add(msg);
            return;
        } else {//skip the invalid data
            if (endIndex < msg.capacity() - 1) {
                msg.readerIndex(endIndex + 1);
            } else {
                msg.readerIndex(endIndex);
            }
            return;
        }
    }
}
