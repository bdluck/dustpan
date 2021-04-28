package com.github.bdluck.segment.data;

import com.github.bdluck.handle.ByteHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bdluck
 */
public class SegmentData {
    /**
     * 分段标记 标记一个分段
     */
    private String segmentMark;
    /**
     * 分段长度
     */
    private int size;
    /**
     * 分段方向（正向，反向）
     */
    private SegmentOrder segmentOrder;
    /**
     * 分段类型
     */
    private SegmentType segmentType;
    /**
     * 子分段（RETRY-重复分段时使用）
     */
    private List<SegmentData> child = new ArrayList<>();
    /**
     * 位拆分集合（BIT-按位分段时使用）
     */
    private List<BitSegment> bitMap = new ArrayList<>();
    /**
     * 包拦截器
     */
    private List<ByteHandler> packHandler = new ArrayList<>();

    public String getSegmentMark() {
        return segmentMark;
    }

    public void setSegmentMark(String segmentMark) {
        this.segmentMark = segmentMark;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public SegmentOrder getSegmentOrder() {
        return segmentOrder;
    }

    public void setSegmentOrder(SegmentOrder segmentOrder) {
        this.segmentOrder = segmentOrder;
    }

    public SegmentType getSegmentType() {
        return segmentType;
    }

    public void setSegmentType(SegmentType segmentType) {
        this.segmentType = segmentType;
    }

    public List<SegmentData> getChild() {
        return child;
    }

    public void setChild(List<SegmentData> child) {
        this.child = child;
    }

    public List<BitSegment> getBitMap() {
        return bitMap;
    }

    public void setBitMap(List<BitSegment> bitMap) {
        this.bitMap = bitMap;
    }

    public List<ByteHandler> getPackHandler() {
        return packHandler;
    }

    public void setPackHandler(List<ByteHandler> packHandler) {
        this.packHandler = packHandler;
    }
}

