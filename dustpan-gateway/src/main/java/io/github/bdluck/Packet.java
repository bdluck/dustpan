package io.github.bdluck;

/**
 * @author bdluck
 */
public class Packet {

    /**
     * 会话信息
     */
    private String uid;
    /**
     * 包类型
     */
    private PacketType packetType;
    /**
     * 数据信息
     */
    private byte[] data;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
