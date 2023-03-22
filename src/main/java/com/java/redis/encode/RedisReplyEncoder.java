package com.java.redis.encode;

import com.java.redis.entity.RedisReply;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class RedisReplyEncoder extends MessageToByteEncoder<RedisReply> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RedisReply redisReply, ByteBuf byteBuf) throws Exception {

    }
}
