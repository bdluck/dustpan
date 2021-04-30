package io.github.bdluck;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author bdluck
 */
public class ConnectManager {

    /**
     * uid <> channel
     */
    private final Map<String, Channel> channelMap = new ConcurrentHashMap<>();
    /**
     * channelId <> uid
     */
    private final Map<String, String> channelIdUidMap = new ConcurrentHashMap<>();

    /**
     * 新增通道
     *
     * @param channel 连接通道
     */
    public String addChannel(Channel channel) {
        String channelId = getChannelId(channel);
        String uid = getUUID();
        channelIdUidMap.put(channelId, uid);
        channelMap.put(uid, channel);
        return uid;
    }

    /**
     * 移除通道
     *
     * @param channel 连接通道
     */
    public String removeChannel(Channel channel) {
        String channelId = getChannelId(channel);
        String uid = channelIdUidMap.remove(channelId);
        if (uid != null) {
            channelMap.remove(uid);
            return uid;
        }
        return null;
    }

    /**
     * 获取唯一id
     *
     * @param channel 连接通道
     */
    public String getUid(Channel channel) {
        String channelId = getChannelId(channel);
        return channelIdUidMap.get(channelId);
    }

    /**
     * 数据下发
     *
     * @param uid 唯一id
     * @param msg 消息
     */
    public void writeAndFlush(String uid, Object msg) {
        Channel channel = channelMap.get(uid);
        if (channel != null) {
            channel.writeAndFlush(msg);
        }
    }

    /**
     * 是否存在该id
     *
     * @param uid 唯一id
     */
    public boolean contain(String uid) {
        return channelMap.containsKey(uid);
    }

    /**
     * 获取唯一id 标识分布式唯一channel
     */
    private String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取通道id
     *
     * @param channel 连接通道
     */
    private String getChannelId(Channel channel) {
        return channel.id().asLongText();
    }
}
