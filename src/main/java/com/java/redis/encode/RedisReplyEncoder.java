package com.java.redis.encode;

import com.java.redis.constant.RedisReplyCodeEnum;
import com.java.redis.context.LockContext;
import com.java.redis.entity.RedisReply;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.StandardCharsets;

public class RedisReplyEncoder extends MessageToByteEncoder<RedisReply> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RedisReply redisReply, ByteBuf byteBuf) throws Exception {
        System.out.println("RedisReplyEncoder" + redisReply);
        String reply = RedisReplyCodeEnum.encodeReply(redisReply);
        System.out.println(reply);
        ByteBuf buffer = channelHandlerContext.alloc().buffer(reply.getBytes(StandardCharsets.UTF_8).length);
        buffer.writeBytes(reply.getBytes(StandardCharsets.UTF_8));
        channelHandlerContext.writeAndFlush(buffer);
        LockContext.unlock();
    }
}
