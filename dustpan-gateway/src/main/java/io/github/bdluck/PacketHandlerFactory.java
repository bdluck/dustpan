package io.github.bdluck;

import io.github.bdluck.netty.ChannelHandlerFactory;
import io.netty.channel.ChannelHandler;

/**
 * @author bdluck
 */
public class PacketHandlerFactory implements ChannelHandlerFactory {

    private final PacketConsumer consumer;

    private final ConnectManager connectManager;

    public PacketHandlerFactory(PacketConsumer consumer, ConnectManager connectManager) {
        this.consumer = consumer;
        this.connectManager = connectManager;
    }

    @Override
    public ChannelHandler newInstance() {
        return new PacketHandler(consumer, connectManager);
    }
}
