package io.github.bdluck;

import io.github.bdluck.handle.BatchHandler;
import io.github.bdluck.merge.MergeFactory;
import io.github.bdluck.merge.MergeProcessor;
import io.github.bdluck.segment.SegmentFactory;
import io.github.bdluck.segment.SegmentProcessor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bdluck
 */
public class TemplateFactory {

    /**
     * 获取模板处理器
     *
     * @param templateDataList 模板数据集合
     * @return 模板处理器
     */
    public static TemplateProcessor getProcessor(List<TemplateData> templateDataList) {
        return new TemplateProcessor(getInstance(templateDataList));
    }

    /**
     * 获取模板实例
     *
     * @param templateDataList 模板数据集合
     * @return 模板实例
     */
    public static List<Template> getInstance(List<TemplateData> templateDataList) {
        return templateDataList.stream().map(TemplateFactory::getInstance).collect(Collectors.toList());
    }

    /**
     * 获取模板实例
     *
     * @param templateData 模板数据
     * @return 模板对象
     */
    public static Template getInstance(TemplateData templateData) {
        // 获取拦截参数
        BatchHandler batchHandler = new BatchHandler(templateData.getPackHandler());
        // 获取分段处理器
        SegmentProcessor segmentProcessor = SegmentFactory.getProcessor(templateData.getSegment());
        // 获取合并处理器
        MergeProcessor mergeProcessor = MergeFactory.getProcessor(templateData.getMerge());
        // 构建模板
        Template template = new Template(templateData.getName(), templateData.getDispenseMark(), segmentProcessor, mergeProcessor);
        template.setHandler(batchHandler);
        return template;
    }
}
