package io.github.bdluck;

import io.github.bdluck.disruptor.DisruptorQueue;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author bdluck
 */
public class PacketHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private final DisruptorQueue<Packet> disruptorQueue;

    public PacketHandler(DisruptorQueue<Packet> disruptorQueue) {
        this.disruptorQueue = disruptorQueue;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf byteBuf) {
        byte[] data = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(data);
        Packet packet = new Packet(ctx.channel(), data);
        disruptorQueue.postMsg(packet);
    }
}
