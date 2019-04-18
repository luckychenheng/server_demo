package com.spring.cloud.netty.server.interfaces;

import com.spring.cloud.netty.common.constant.MessageDataConstant;
import com.spring.cloud.netty.common.util.BuildByteBuf;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.springframework.stereotype.Component;

/**
 * 抽象命令模板
 *
 * @author wangmj
 * @since 2018/11/14
 */
@Component
public abstract class CommandAdapter implements ICommand {

    private int magicData;
    private byte version;

    public CommandAdapter() {
        magicData = MessageDataConstant.MAGIC_DATA;
        version = MessageDataConstant.VERSION;
    }

    private int getCmdHeadSize() {
        return 1 + 4 + 1 + 2 + 4 + 1;
    }

    private void setFiled() {
        this.magicData = MessageDataConstant.MAGIC_DATA;
        this.version = MessageDataConstant.VERSION;
    }


    @Override
    public ByteBuf getSendBuffer() {
        setFiled();
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer(getCmdBodySize() + getCmdHeadSize());
        fillChannelBuffer(byteBuf);
        return byteBuf;
    }

    private boolean fillChannelBuffer(ByteBuf byteBuf) {
        byteBuf.writeByte(MessageDataConstant.CMD_HEAD);
        byteBuf.writeInt(this.magicData);
        byteBuf.writeByte(this.version);
        byteBuf.writeShort(getCmd());
        fillCmdBody(byteBuf);
        return true;
    }

    public abstract void fillCmdBody(ByteBuf byteBuf);

    public abstract short getCmd();

    public abstract int getCmdBodySize();
}
