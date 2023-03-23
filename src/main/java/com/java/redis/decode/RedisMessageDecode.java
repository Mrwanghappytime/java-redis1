package com.java.redis.decode;

import com.java.redis.context.RedisContext;
import com.java.redis.entity.RedisMessage;
import com.java.redis.util.RedisLogger;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class RedisMessageDecode extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        char c = (char) byteBuf.readByte();
        int paramsCount = readIntValue(byteBuf);
        List<Object> params = new ArrayList<>();
        for (int i = 0; i < paramsCount; i++) {
            params.add(readParam(byteBuf));
        }
        RedisMessage message = new RedisMessage();
        message.setCommand((String) params.get(0));
        params.remove(0);
        message.setArgs(params);
        list.add(message);
    }

    private Object readParam(ByteBuf byteBuf) {
        byteBuf.readByte();
        int length = readIntValue(byteBuf);
        CharSequence sequence = byteBuf.readCharSequence(length, Charset.forName("UTF8"));
        byteBuf.readByte();
        byteBuf.readByte();
        return sequence;
    }

    private int readIntValue(ByteBuf byteBuf) {
        int value = 0;
        char c;
        while ((c = (char) byteBuf.readByte()) != '\r') {
            value = value * 10 + (c - '0');
        }
        byteBuf.readByte();
        return value;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (cause instanceof IOException) {
            NioSocketChannel channel = (NioSocketChannel) ctx.channel();
            String hostName = channel.remoteAddress().getAddress().getHostAddress();
            int port = channel.remoteAddress().getPort();
            RedisLogger.info("客户端 <ip:" + hostName + ">" + "<port:" + port + "> 下线");
            RedisContext.removeClient(hostName, port);
        }
    }
}
