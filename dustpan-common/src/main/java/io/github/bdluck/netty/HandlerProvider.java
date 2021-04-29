package io.github.bdluck.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.List;

/**
 * @author bdluck
 */
public interface HandlerProvider {

    /**
     * 拦截器
     */
    List<ChannelHandler> getHandler();

    /**
     * channel初始化
     */
    default ChannelInitializer<SocketChannel> channelInitializer() {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) {
                socketChannel.pipeline().addLast(new IdleStateHandler(0, 0, 900));
                List<ChannelHandler> handlers = getHandler();
                for (ChannelHandler handler : handlers) {
                    socketChannel.pipeline().addLast(handler);
                }
            }
        };
    }

}
