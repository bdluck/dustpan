package io.github.bdluck;

import io.github.bdluck.merge.MergeResult;
import io.github.bdluck.segment.SegmentResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bdluck
 */
public class TemplateResult {

    /**
     * 解析数据
     */
    private byte[] data;
    /**
     * 模板标识
     */
    private String templateMark;
    /**
     * 分段结果
     */
    private List<SegmentResult> segmentResultList = new ArrayList<>();
    /**
     * 合并结果
     */
    private List<MergeResult> mergeResultList = new ArrayList<>();

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getTemplateMark() {
        return templateMark;
    }

    public void setTemplateMark(String templateMark) {
        this.templateMark = templateMark;
    }

    public List<SegmentResult> getSegmentResultList() {
        return segmentResultList;
    }

    public void setSegmentResultList(List<SegmentResult> segmentResultList) {
        this.segmentResultList = segmentResultList;
    }

    public List<MergeResult> getMergeResultList() {
        return mergeResultList;
    }

    public void setMergeResultList(List<MergeResult> mergeResultList) {
        this.mergeResultList = mergeResultList;
    }
}
