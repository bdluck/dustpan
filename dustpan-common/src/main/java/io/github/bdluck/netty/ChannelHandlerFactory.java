package io.github.bdluck.netty;

import io.netty.channel.ChannelHandler;

/**
 * @author bdluck
 */
public interface ChannelHandlerFactory {

    /**
     * 获取handler
     */
    ChannelHandler newInstance();
}
