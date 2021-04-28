package io.github.bdluck.segment;

import io.github.bdluck.handle.SimpleHandler;
import io.github.bdluck.segment.data.SegmentOrder;

/**
 * @author bdluck
 */
public abstract class BaseSegment extends SimpleHandler implements Segment {

    /**
     * 读取顺序
     */
    protected SegmentOrder segmentOrder = SegmentOrder.FORWARD;
    /**
     * 分段标记
     */
    protected final String segmentMark;
    /**
     * 分段长度
     */
    protected final int length;

    public BaseSegment(String segmentMark, int length) {
        this.segmentMark = segmentMark;
        this.length = length;
    }

    /**
     * 设置读取顺序
     *
     * @param segmentOrder 顺序
     */
    public BaseSegment order(SegmentOrder segmentOrder) {
        if (segmentOrder == null) {
            throw new NullPointerException("order can not be null!");
        }
        this.segmentOrder = segmentOrder;
        return this;
    }

    /**
     * 数据基础分段
     * 读取指定长度的byte数据
     *
     * @param readLength  读取长度
     * @param byteWrapper 字节数据包装
     * @return 读取的分段数据
     */
    protected byte[] read(int readLength, ByteWrapper byteWrapper) {
        byte[] data = byteWrapper.getRest();
        int restLength = data.length;
        if (restLength == 0) {
            return null;
        }
        // 剩余长度大于需要读取的长度
        if (readLength < restLength) {
            // 拷贝所需分段长度
            byte[] segment = new byte[readLength];
            int segmentMark = 0;
            if (segmentOrder == SegmentOrder.FORWARD) {
                // 顺序读写
                System.arraycopy(data, 0, segment, 0, readLength);
                segmentMark = readLength;
            } else {
                // 倒序读写
                System.arraycopy(data, restLength - readLength, segment, 0, readLength);
            }
            // 计算剩余的byte数
            restLength = restLength - readLength;
            // 读取剩余的字节到新的数组中
            byte[] rest = new byte[restLength];
            System.arraycopy(data, segmentMark, rest, 0, restLength);
            byteWrapper.setRest(rest);
            return segment;
        } else {
            byteWrapper.setRest(new byte[0]);
            return data;
        }
    }
}
