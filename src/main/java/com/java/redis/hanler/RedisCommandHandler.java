package com.java.redis.hanler;

import com.java.redis.entity.RedisMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.redis.ArrayHeaderRedisMessage;

public class RedisCommandHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RedisMessage message = (RedisMessage) msg;


    }
}
