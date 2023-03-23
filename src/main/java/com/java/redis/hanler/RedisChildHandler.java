package com.java.redis.hanler;

import com.java.redis.context.RedisContext;
import com.java.redis.decode.RedisMessageDecode;
import com.java.redis.encode.RedisReplyEncoder;
import com.java.redis.util.RedisLogger;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class RedisChildHandler extends ChannelInitializer<NioSocketChannel> {

    @Override
    protected void initChannel(NioSocketChannel channel) throws Exception {

        String hostName = channel.remoteAddress().getHostName();
        int port = channel.remoteAddress().getPort();
        RedisLogger.info("客户端 <ip:" + hostName + ">" + "<port:" + port + "> 上线");
        RedisContext.addClient(hostName, port);
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast("redis-command-decoder", new RedisMessageDecode());
        pipeline.addLast("redis-command-encoder", new RedisReplyEncoder());
        pipeline.addLast("command-handler", new RedisCommandHandler());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        NioSocketChannel channel = (NioSocketChannel) ctx.channel();
        String hostName = channel.remoteAddress().getAddress().getHostAddress();
        int port = channel.remoteAddress().getPort();
        RedisLogger.info("客户端 <ip:" + hostName + ">" + "<port:" + port + "> 下线");
        RedisContext.removeClient(hostName, port);
    }
}
