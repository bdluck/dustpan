package io.github.bdluck.segment;

import io.github.bdluck.handle.Handler;

import java.util.List;

/**
 * @author bdluck
 */
public interface Segment extends Handler {

    /**
     * 直接读取分段
     *
     * @param byteWrapper 字节数据包装
     * @return 分段结果
     */
    List<SegmentResult> segment(ByteWrapper byteWrapper);
}
