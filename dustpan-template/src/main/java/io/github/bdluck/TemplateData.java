package io.github.bdluck;

import io.github.bdluck.handle.ByteHandler;
import io.github.bdluck.merge.data.MergeData;
import io.github.bdluck.segment.data.SegmentData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bdluck
 */
public class TemplateData {
    /**
     * 模板名称
     */
    private String name;
    /**
     * 模板标识
     */
    private String dispenseMark;
    /**
     * 分段集合
     */
    private List<SegmentData> segment = new ArrayList<>();
    /**
     * 合并集合
     */
    private List<MergeData> merge = new ArrayList<>();
    /**
     * 字节拦截器
     */
    private List<ByteHandler> packHandler = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDispenseMark() {
        return dispenseMark;
    }

    public void setDispenseMark(String dispenseMark) {
        this.dispenseMark = dispenseMark;
    }

    public List<SegmentData> getSegment() {
        return segment;
    }

    public void setSegment(List<SegmentData> segment) {
        this.segment = segment;
    }

    public List<MergeData> getMerge() {
        return merge;
    }

    public void setMerge(List<MergeData> merge) {
        this.merge = merge;
    }

    public List<ByteHandler> getPackHandler() {
        return packHandler;
    }

    public void setPackHandler(List<ByteHandler> packHandler) {
        this.packHandler = packHandler;
    }
}
