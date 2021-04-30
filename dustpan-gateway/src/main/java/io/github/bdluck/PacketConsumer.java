package io.github.bdluck;

/**
 * @author bdluck
 */
public interface PacketConsumer {

    /**
     * 包消费处理
     *
     * @param packet 包数据
     */
    void accept(Packet packet);
}
