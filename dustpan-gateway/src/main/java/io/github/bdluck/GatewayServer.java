package io.github.bdluck;

import io.github.bdluck.disruptor.DisruptorQueue;
import io.github.bdluck.netty.AbstractNettyServer;
import io.github.bdluck.unpack.Unpack;
import io.github.bdluck.unpack.UnpackDecoder;
import io.netty.channel.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bdluck
 */
public class GatewayServer extends AbstractNettyServer {

    private final List<Unpack> unpackList;

    private final DisruptorQueue<Packet> disruptorQueue;

    public GatewayServer(String serverId, int port, List<Unpack> unpackList, DisruptorQueue<Packet> disruptorQueue) {
        super(serverId, port);
        this.unpackList = unpackList;
        this.disruptorQueue = disruptorQueue;
    }

    public GatewayServer(String serverId, List<Unpack> unpackList, DisruptorQueue<Packet> disruptorQueue, int... port) {
        super(serverId, port);
        this.unpackList = unpackList;
        this.disruptorQueue = disruptorQueue;
    }

    @Override
    public List<ChannelHandler> getHandler() {
        List<ChannelHandler> handlers = new ArrayList<>();
        handlers.add(new UnpackDecoder(unpackList));
        handlers.add(new PacketHandler(disruptorQueue));
        return handlers;
    }
}
