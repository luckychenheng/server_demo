package com.spring.cloud.netty.server.interfaces;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;

/**
 * @author wangmj
 * @since 2018/11/13
 */
@Component
public interface ICommand {

    //解析收到的命令
    boolean disposeData(ByteBuf buffer);

    ByteBuf getSendBuffer();

}
