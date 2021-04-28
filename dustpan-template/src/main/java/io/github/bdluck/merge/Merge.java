package io.github.bdluck.merge;

import io.github.bdluck.handle.Handler;

/**
 * @author bdluck
 */
public interface Merge extends Handler {

    /**
     * 数据合并
     *
     * @param segmentWrapper 分段数据包装
     */
    MergeResult merge(SegmentWrapper segmentWrapper);
}
