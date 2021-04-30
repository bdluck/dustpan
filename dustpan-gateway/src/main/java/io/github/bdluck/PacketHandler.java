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

    private final ConnectManager connectManager;

    PacketHandler(PacketConsumer consumer, ConnectManager connectManager) {
        this.consumer = consumer;
        this.connectManager = connectManager;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf byteBuf) {
        byte[] data = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(data);
        Packet packet = new Packet();
        String uid = connectManager.getUid(ctx.channel());
        packet.setUid(uid);
        packet.setPacketType(PacketType.REPORT);
        packet.setData(data);
        consumer.accept(packet);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        String uid = connectManager.addChannel(ctx.channel());
        Packet packet = new Packet();
        packet.setUid(uid);
        packet.setPacketType(PacketType.CONNECT);
        consumer.accept(packet);
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        String uid = connectManager.removeChannel(ctx.channel());
        Packet packet = new Packet();
        packet.setUid(uid);
        packet.setPacketType(PacketType.DISCONNECT);
        consumer.accept(packet);
        ctx.fireChannelInactive();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        String uid = connectManager.getUid(ctx.channel());
        log.error("uid:{} client:{} exception:{}", uid, ctx.channel().remoteAddress().toString(), cause.getMessage(), cause);
    }
}
