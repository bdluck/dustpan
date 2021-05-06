package io.github.bdluck;

import io.github.bdluck.netty.ChannelHandlerFactory;
import io.netty.channel.ChannelHandler;

/**
 * @author bdluck
 */
public class PacketHandlerFactory implements ChannelHandlerFactory {

    private final PacketConsumer consumer;

    public PacketHandlerFactory(PacketConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public ChannelHandler newInstance() {
        return new PacketHandler(consumer);
    }
}
