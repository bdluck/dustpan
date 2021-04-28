package com.github.bdluck.merge;

import com.github.bdluck.handle.Handler;

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
