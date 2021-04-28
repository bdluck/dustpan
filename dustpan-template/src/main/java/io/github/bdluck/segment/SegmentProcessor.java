package io.github.bdluck.segment;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bdluck
 */
public class SegmentProcessor {

    /**
     * 分段参数
     */
    private final List<Segment> segmentList;

    public SegmentProcessor(List<Segment> segmentList) {
        this.segmentList = segmentList;
    }

    /**
     * 读取分段
     *
     * @param data 字节数据
     */
    public List<SegmentResult> segmentRead(byte[] data) {
        ByteWrapper wrapper = new ByteWrapper(data);
        return segmentList.stream()
                .filter(segment -> segment.isHandle(data))
                .flatMap(segment -> {
                    List<SegmentResult> segmentResults = segment.segment(wrapper);
                    return segmentResults.stream();
                }).collect(Collectors.toList());
    }
}
