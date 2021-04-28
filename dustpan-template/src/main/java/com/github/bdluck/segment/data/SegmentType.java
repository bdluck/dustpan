package com.github.bdluck.segment.data;

/**
 * @author bdluck
 */
public enum SegmentType {
    /**
     * bit读取
     */
    BIT,
    /**
     * byte读取
     */
    BYTE,
    /**
     * 读取剩余的所有
     */
    REMAIN,
    /**
     * 重复读取
     */
    RETRY
}
