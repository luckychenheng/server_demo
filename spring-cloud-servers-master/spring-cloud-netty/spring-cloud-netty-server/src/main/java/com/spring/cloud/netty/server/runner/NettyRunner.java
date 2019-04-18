package com.spring.cloud.netty.server.runner;

import com.spring.cloud.netty.server.decode.ServerMessageDecode;
import com.spring.cloud.netty.server.handler.AuthHandler;
import com.spring.cloud.netty.server.handler.ServerMessageHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * netty启动类
 *
 * @author wangmj
 * @since 2018/11/5
 */
@Slf4j
@Component
public class NettyRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        start();
    }

    private void start() {
        log.info("netty服务端启动开始");
        //负责接收新连接的线程组
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        //负责网络数据的读写及业务逻辑线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors() * 2);

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boosGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    //请求队列的长度，若连接创建频繁、处理连接较慢，可以加大此数值
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    //标识是否开启底层心跳连接检测
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //是否开启Nagle算法
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    .addLast(new ServerMessageDecode())
                                    .addLast(new ServerMessageHandler());
//                                    .addLast(new AuthHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.bind(9999).sync();
            //监听是否启动成功
            channelFuture.addListener((e) -> {
                if (e.isSuccess()) {
                    log.info("服务启动启动成功!");
                } else {
                    log.error("服务启动启动失败!", e.cause());
                }
            });
            //等待服务端监听端口关闭
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
