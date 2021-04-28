package com.github.bdluck.merge;

import com.github.bdluck.handle.SimpleHandler;
import com.github.bdluck.merge.basic.BasicInt;
import com.github.bdluck.merge.data.RetryType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bdluck
 */
public abstract class BaseMerge extends SimpleHandler implements Merge {

    /**
     * 合并名称
     */
    protected final String name;
    /**
     * 重复读类型
     */
    protected RetryType retryType;
    /**
     * 重复读键
     */
    protected String retryKey;
    /**
     * 重复读大小
     */
    protected int retrySize = 0;

    public BaseMerge(String name) {
        this.name = name;
    }

    public void setRetry(RetryType retryType, String retryKey, int retrySize) {
        this.retryType = retryType;
        this.retryKey = retryKey;
        this.retrySize = retrySize;
    }

    /**
     * 数据合并
     *
     * @param segmentWrapper 分段数据包装
     */
    public MergeResult merge(SegmentWrapper segmentWrapper) {
        if (retryType == null) {
            MergeResult mergeResult = null;
            Object message = getMessage(0, segmentWrapper);
            if (message != null) {
                mergeResult = new MergeResult(name, message);
            }
            return mergeResult;
        } else {
            int retryTime = getRetryTime(segmentWrapper);
            List<Object> objectList = new ArrayList<>(retryTime);
            for (int i = 1; i <= retryTime; i++) {
                Object message = getMessage(i, segmentWrapper);
                if (message != null) {
                    objectList.add(message);
                }
            }
            return new MergeResult(name, objectList);
        }
    }

    /**
     * 获取重复读次数
     *
     * @param segmentWrapper 分段数据包装
     * @return 重复次数
     */
    private int getRetryTime(SegmentWrapper segmentWrapper) {
        int retryTime = 0;
        if (retryType == RetryType.KEY) {
            byte[] segment = segmentWrapper.getSegment(retryKey);
            if (segment != null) {
                retryTime = new BasicInt().convert(segment);
            }
        }
        if (retryType == RetryType.SIZE) {
            retryTime = retrySize;
        }
        return retryTime;
    }

    /**
     * 根据包获取合并结果
     *
     * @param retryIndex     重复读次数
     * @param segmentWrapper 分段数据包装
     * @return 合并结果
     */
    abstract Object getMessage(int retryIndex, SegmentWrapper segmentWrapper);
}
