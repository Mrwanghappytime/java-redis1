package com.java.redis;

import com.java.redis.context.RedisContext;
import com.java.redis.decode.RedisCommandDecode;
import com.java.redis.encode.RedisReplyEncoder;
import com.java.redis.hanler.RedisCommandHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.redis.RedisDecoder;
import io.netty.handler.codec.redis.RedisEncoder;


public class ServerStart {
    public static void main(String[] args) {
        RedisContext.initRedisContext(null, null);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup(1);
        serverBootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 512)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception {
                        ChannelPipeline pipeline = channel.pipeline();
                        pipeline.addLast("redis-command-decoder", new RedisCommandDecode());
                        pipeline.addLast("redis-command-encoder", new RedisReplyEncoder());
                        pipeline.addLast("command-handler", new RedisCommandHandler());
                    }
                });

        try {
            ChannelFuture sync = serverBootstrap.bind("localhost", 6379).sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {

        }
    }
}
