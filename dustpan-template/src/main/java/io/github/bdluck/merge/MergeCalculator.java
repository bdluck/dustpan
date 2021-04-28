package io.github.bdluck.merge;

import java.util.List;

/**
 * @author bdluck
 */
public class MergeCalculator extends MergeExpression {

    public MergeCalculator(String name, String expression, List<? extends BaseMerge> merges) {
        super(name, expression, merges);
    }

    @Override
    public Object getMessage(int retryIndex, SegmentWrapper segmentWrapper) {
        String format = format(expression, retryIndex, segmentWrapper);
        double result = Calculator.conversion(format);
        return replace(String.valueOf(result));
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     *
     * @param s double字符
     * @return string
     */
    public static String replace(String s) {
        if (null != s && s.indexOf(".") > 0) {
            //去掉多余的0
            s = s.replaceAll("0+?$", "");
            //如最后一位是.则去掉
            s = s.replaceAll("[.]$", "");
        }
        return s;
    }
}
