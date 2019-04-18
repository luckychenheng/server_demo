package com.spring.cloud.netty.server.command;

import com.spring.cloud.netty.common.constant.Const;
import com.spring.cloud.netty.common.constant.MessageDataConstant;
import com.spring.cloud.netty.server.interfaces.CommandAdapter;
import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author wangmj
 * @since 2018/11/22
 */
@Slf4j
@Component
public class HeartBeatCommand extends CommandAdapter {

    private byte result;

    @Override
    public void fillCmdBody(ByteBuf byteBuf) {
        byteBuf.writeInt(1);
        byteBuf.writeByte(result);
        byteBuf.writeByte(MessageDataConstant.CMD_END);
    }

    @Override
    public short getCmd() {
        return Const.HEART_BEAT;
    }

    @Override
    public int getCmdBodySize() {
        return 1;
    }

    @Override
    public boolean disposeData(ByteBuf buffer) {
        int dataLength = buffer.readInt();
        byte[] data = new byte[dataLength];
        buffer.readBytes(data);
        if (data.length == 1 && data[0] == MessageDataConstant.SUCCESS_CODE) {
            result = MessageDataConstant.SUCCESS_CODE;
            return true;
        }
        return false;
    }
}
