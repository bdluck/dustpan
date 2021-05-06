package io.github.bdluck.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

import java.util.Arrays;
import java.util.List;

/**
 * @author bdluck
 */
public class BatchChannelHandlerFactory implements ChannelHandlerFactory {

    private final List<ChannelHandlerFactory> factoryList;

    public BatchChannelHandlerFactory(List<ChannelHandlerFactory> factoryList) {
        this.factoryList = factoryList;
    }

    public BatchChannelHandlerFactory(ChannelHandlerFactory... factories) {
        this.factoryList = Arrays.asList(factories);
    }

    @Override
    public ChannelHandler newInstance() {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) {
                for (ChannelHandlerFactory factory : factoryList) {
                    ch.pipeline().addLast(factory.newInstance());
                }
            }
        };
    }
}
