package com.github.bdluck.segment;

import com.github.bdluck.handle.ByteHandler;
import com.github.bdluck.segment.data.SegmentData;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bdluck
 */
public class SegmentFactory {

    /**
     * 获取分段处理器
     *
     * @param segmentDataList 分段数据集合
     * @return 分段处理器
     */
    public static SegmentProcessor getProcessor(List<SegmentData> segmentDataList) {
        return new SegmentProcessor(getInstance(segmentDataList));
    }

    /**
     * 获取分段实例
     *
     * @param segmentDataList 分段数据集合
     * @return 分段实例
     */
    public static List<Segment> getInstance(List<SegmentData> segmentDataList) {
        return segmentDataList.stream().map(SegmentFactory::getInstance).collect(Collectors.toList());
    }

    /**
     * 获取分段实例
     *
     * @param segmentData 分段数据
     * @return 分段对象
     */
    public static Segment getInstance(SegmentData segmentData) {
        Segment segment = null;
        switch (segmentData.getSegmentType()) {
            case BIT:
                SegmentBit segmentBit = new SegmentBit(segmentData.getSize());
                segmentData.getBitMap().forEach(bitSegment -> segmentBit.append(bitSegment.getSegmentMark(), bitSegment.getSize()));
                segment = segmentBit.order(segmentData.getSegmentOrder());
                break;
            case BYTE:
                segment = new SegmentByte(segmentData.getSegmentMark(), segmentData.getSize()).order(segmentData.getSegmentOrder());
                break;
            case REMAIN:
                segment = new SegmentRemain(segmentData.getSegmentMark()).order(segmentData.getSegmentOrder());
                break;
            case RETRY:
                List<Segment> segmentList = segmentData.getChild().stream()
                        .map(SegmentFactory::getInstance)
                        // 过滤子分段仍为重复分段的数据
                        .filter(seg -> !(seg instanceof SegmentRetry))
                        .collect(Collectors.toList());
                segment = new SegmentRetry(segmentData.getSegmentMark(), segmentData.getSize(), segmentList).order(segmentData.getSegmentOrder());
        }
        // 获取拦截参数
        List<ByteHandler> packHandlers = segmentData.getPackHandler();
        segment.addHandler(packHandlers);
        return segment;
    }
}
