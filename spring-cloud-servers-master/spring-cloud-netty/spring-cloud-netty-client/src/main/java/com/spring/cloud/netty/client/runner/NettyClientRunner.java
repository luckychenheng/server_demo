package com.spring.cloud.netty.client.runner;

import com.spring.cloud.netty.client.decode.ClientMessageDecode;
import com.spring.cloud.netty.client.handler.ClientHeartBeatHandler;
import com.spring.cloud.netty.client.handler.ClientMessageHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author wangmj
 * @since 2018/11/7
 */
@Slf4j
@Component
public class NettyClientRunner implements CommandLineRunner {

    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "9999"));

    @Override
    public void run(String... args) throws Exception {
        log.info("开启netty客户端流程");
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workGroup)
                // 2.指定 IO 类型为 NIO
                .channel(NioSocketChannel.class)
                // 3.IO 处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ch.pipeline()
                                .addLast(new ClientMessageDecode())
                                .addLast(new ClientMessageHandler())
                                .addLast(new ClientHeartBeatHandler());
                    }
                });
        // Start the client.
        for (int i = 1; i <= 1; i++) {
            startConnection(bootstrap, i);
        }
    }

    private static void startConnection(Bootstrap b, int index) {
        b.connect(HOST, PORT)
                .addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture future)
                            throws Exception {
                        if (future.isSuccess()) {
                            //init registry
                            log.info("Client[{}] connected Gate Successed...", index);
                        } else {
                            log.error("Client[{}] connected Gate Failed", index);
                        }
                    }
                });
    }
}
