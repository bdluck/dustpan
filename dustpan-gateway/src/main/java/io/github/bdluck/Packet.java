package io.github.bdluck;

import io.netty.channel.Channel;

/**
 * @author bdluck
 */
public class Packet {

    /**
     * 会话信息
     */
    private Channel channel;
    /**
     * 数据信息
     */
    private byte[] data;

    public Packet(Channel channel, byte[] data) {
        this.channel = channel;
        this.data = data;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
