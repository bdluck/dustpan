package com.github.bdluck;

import com.github.bdluck.handle.SimpleHandler;
import com.github.bdluck.merge.MergeProcessor;
import com.github.bdluck.merge.MergeResult;
import com.github.bdluck.segment.SegmentProcessor;
import com.github.bdluck.segment.SegmentResult;

import java.util.List;

/**
 * @author bdluck
 */
public class Template extends SimpleHandler {

    /**
     * 模板名称
     */
    private final String name;
    /**
     * 模板标识
     */
    private final String templateMark;
    /**
     * 分段处理器
     */
    private final SegmentProcessor segmentProcessor;
    /**
     * 合并处理器
     */
    private final MergeProcessor mergeProcessor;

    public Template(String name, String templateMark, SegmentProcessor segmentProcessor, MergeProcessor mergeProcessor) {
        this.name = name;
        this.templateMark = templateMark;
        this.segmentProcessor = segmentProcessor;
        this.mergeProcessor = mergeProcessor;
    }

    /**
     * 模板数据解析
     *
     * @param data 解析数据
     */
    public TemplateResult resolve(byte[] data) {
        // 分段结果
        List<SegmentResult> segmentResultList = segmentProcessor.segmentRead(data);
        // 合并结果
        List<MergeResult> mergeResultList = mergeProcessor.mergeRead(segmentResultList, data);
        // 构建模板结果
        TemplateResult templateResult = new TemplateResult();
        templateResult.setData(data);
        templateResult.setTemplateMark(templateMark);
        templateResult.setSegmentResultList(segmentResultList);
        templateResult.setMergeResultList(mergeResultList);
        return templateResult;
    }

    /**
     * 获取模板名称
     */
    public String getName() {
        return name;
    }

    /**
     * 是否匹配
     */
    public boolean contain(String key) {
        return templateMark.equals(key);
    }
}
