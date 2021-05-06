package io.github.bdluck;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bdluck
 */
class PacketHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final PacketConsumer consumer;

    PacketHandler(PacketConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf byteBuf) {
        byte[] data = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(data);
        Packet packet = new Packet();
        packet.setChannel(ctx.channel());
        packet.setPacketType(PacketType.REPORT);
        packet.setData(data);
        consumer.accept(packet);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Packet packet = new Packet();
        packet.setChannel(ctx.channel());
        packet.setPacketType(PacketType.CONNECT);
        consumer.accept(packet);
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Packet packet = new Packet();
        packet.setChannel(ctx.channel());
        packet.setPacketType(PacketType.DISCONNECT);
        consumer.accept(packet);
        ctx.fireChannelInactive();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("channelId:{} client:{} exception:{}", ctx.channel().id().asLongText(), ctx.channel().remoteAddress().toString(), cause.getMessage(), cause);
    }
}
