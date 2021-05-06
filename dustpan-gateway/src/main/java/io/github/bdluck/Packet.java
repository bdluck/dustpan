package io.github.bdluck;

import io.netty.channel.Channel;

/**
 * @author bdluck
 */
public class Packet {

    /**
     * 连接信息
     */
    private Channel channel;
    /**
     * 包类型
     */
    private PacketType packetType;
    /**
     * 数据信息
     */
    private byte[] data;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public PacketType getPacketType() {
        return packetType;
    }

    public void setPacketType(PacketType packetType) {
        this.packetType = packetType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
