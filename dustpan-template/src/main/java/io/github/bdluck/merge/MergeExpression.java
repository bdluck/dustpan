package io.github.bdluck.merge;

import java.util.List;

/**
 * @author bdluck
 */
public class MergeExpression extends BaseMerge {
    /**
     * 表达式
     */
    protected final String expression;
    /**
     * 格式化数据
     */
    protected final List<? extends BaseMerge> merges;

    public MergeExpression(String name, String expression, List<? extends BaseMerge> merges) {
        super(name);
        this.expression = expression;
        this.merges = merges;
    }

    @Override
    public Object getMessage(int retryIndex, SegmentWrapper segmentWrapper) {
        return format(expression, retryIndex, segmentWrapper);
    }

    /**
     * 格式化表达式
     *
     * @param expression     表达式
     * @param retryIndex     重复次数键值
     * @param segmentWrapper 分段数据包装
     * @return 格式结果
     */
    protected String format(String expression, int retryIndex, SegmentWrapper segmentWrapper) {
        int i = 0;
        String expressionTmp = expression;
        for (BaseMerge baseMerge : merges) {
            Object result = baseMerge.getMessage(retryIndex, segmentWrapper);
            // 保证byte的无符号
            if (result instanceof Byte) {
                result = (Byte) result & 0xff;
            }
            expressionTmp = expressionTmp.replace("{" + i++ + "}", String.valueOf(result));
        }
        return expressionTmp;
    }
}
