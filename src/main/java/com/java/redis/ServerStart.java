package com.java.redis;

import com.java.redis.context.RedisContext;
import com.java.redis.handler.RedisChildHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.UnsupportedEncodingException;


public class ServerStart {
    public static void main(String[] args) throws UnsupportedEncodingException {
        RedisContext.initRedisContext(null, null);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup(1);
        serverBootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.SO_BACKLOG, 512)
                .childHandler(new RedisChildHandler());

        try {
            ChannelFuture sync = serverBootstrap.bind("localhost", 6379).sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {

        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
