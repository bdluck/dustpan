package io.github.bdluck.merge;

import io.github.bdluck.segment.SegmentResult;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author bdluck
 */
public class MergeProcessor {

    /**
     * 合并参数
     */
    private final List<BaseMerge> baseMergeList;

    public MergeProcessor(List<BaseMerge> baseMergeList) {
        this.baseMergeList = baseMergeList;
    }

    /**
     * 读取合并
     *
     * @param segmentResultList 分段结果
     */
    public List<MergeResult> mergeRead(List<SegmentResult> segmentResultList, byte[] data) {
        SegmentWrapper segmentWrapper = new SegmentWrapper(segmentResultList);
        return baseMergeList.stream()
                .filter(merge -> merge.isHandle(data))
                .flatMap(merge -> {
                    MergeResult mergeResult = merge.merge(segmentWrapper);
                    if (mergeResult != null) {
                        return Stream.of(mergeResult);
                    }
                    return Stream.empty();
                })
                .collect(Collectors.toList());
    }
}
