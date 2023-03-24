package com.java.redis.handler;

import com.java.redis.context.RedisContext;
import com.java.redis.entity.RedisClient;
import com.java.redis.entity.RedisMessage;
import com.java.redis.entity.RedisReply;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class RedisCommandHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RedisMessage message = (RedisMessage) msg;
        System.out.println(message);
        NioSocketChannel channel = (NioSocketChannel) ctx.channel();
        InetSocketAddress address = channel.remoteAddress();
        RedisClient client = RedisContext.getClient(channel.remoteAddress().getHostName(), address.getPort());
        RedisReply redisReply = RedisContext.execCommand(message, client);
        ctx.writeAndFlush(redisReply);
    }
}
