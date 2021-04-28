package com.github.bdluck.merge;

import com.github.bdluck.segment.SegmentResult;

import java.util.List;

/**
 * @author bdluck
 */
public class SegmentWrapper {

    /**
     * 分段结果
     */
    private final List<SegmentResult> segmentResultList;

    public SegmentWrapper(List<SegmentResult> segmentResultList) {
        this.segmentResultList = segmentResultList;
    }

    /**
     * 获取分段字节
     *
     * @param fieldKey 分段键值
     * @return 分段字节
     */
    protected byte[] getSegment(String fieldKey) {
        return segmentResultList.stream()
                .filter(segmentMessage -> fieldKey.equals(segmentMessage.getName()))
                .map(SegmentResult::getResult)
                .findAny().orElse(null);
    }
}
