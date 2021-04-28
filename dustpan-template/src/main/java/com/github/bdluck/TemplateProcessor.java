package com.github.bdluck;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author bdluck
 */
public class TemplateProcessor {
    /**
     * 模板集合
     */
    private final List<Template> templateList;

    public TemplateProcessor(List<Template> templateList) {
        this.templateList = templateList;
    }

    /**
     * 数据解析
     *
     * @param data 数据
     * @return 解析后数据
     */
    public List<TemplateResult> resolve(byte[] data) {
        return templateList.stream()
                .filter(template -> template.isHandle(data))
                .map(template -> template.resolve(data))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
