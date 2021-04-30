package io.github.bdluck;

import io.github.bdluck.netty.AbstractNettyServer;
import io.github.bdluck.netty.ChannelHandlerFactory;

/**
 * @author bdluck
 */
public class GatewayServer extends AbstractNettyServer {

    public GatewayServer(String serverId, int port, ChannelHandlerFactory factory) {
        super(serverId, port, factory);
    }

    public GatewayServer(String serverId, ChannelHandlerFactory factory, int... port) {
        super(serverId, factory, port);
    }
}
