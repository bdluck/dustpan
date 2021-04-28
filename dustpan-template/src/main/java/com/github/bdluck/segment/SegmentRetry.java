package com.github.bdluck.segment;

import com.github.bdluck.merge.basic.BasicInt;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bdluck
 */
public class SegmentRetry extends BaseSegment {
    /**
     * 子分段
     */
    private final List<? extends Segment> segmentList;

    public SegmentRetry(String segmentMark, int length, List<? extends Segment> segmentList) {
        super(segmentMark, length);
        this.segmentList = segmentList;
        if (length == 0 || length > 4) {
            throw new IllegalArgumentException("length must be less than eight and not equal zero!");
        }
    }

    /**
     * 读取所需分段并获取剩余字节
     *
     * @param byteWrapper 分段读取包装
     */
    @Override
    public List<SegmentResult> segment(ByteWrapper byteWrapper) {
        List<SegmentResult> segmentResults = new ArrayList<>();
        // 读取需要重复读取的次数
        byte[] segment = read(length, byteWrapper);
        if (segment != null) {
            // 加入结果集
            segmentResults.add(new SegmentResult(segmentMark, segment));
            // 转换次数
            int retry = new BasicInt().convert(segment);
            if (retry != 0) {
                for (int i = 1; i <= retry; i++) {
                    for (Segment seg : segmentList) {
                        List<SegmentResult> childSegmentResult = seg.segment(byteWrapper);
                        // 更新后缀
                        final String suffix = String.valueOf(i);
                        segmentResults.forEach(segmentResult -> segmentResult.setName(segmentResult.getName() + suffix));
                        // 加入结果集
                        segmentResults.addAll(childSegmentResult);
                    }
                }
            }
        }
        return segmentResults;
    }
}
