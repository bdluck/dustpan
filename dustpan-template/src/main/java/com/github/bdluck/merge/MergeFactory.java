package com.github.bdluck.merge;

import com.github.bdluck.handle.ByteHandler;
import com.github.bdluck.merge.basic.*;
import com.github.bdluck.merge.data.MergeData;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bdluck
 */
public class MergeFactory {

    /**
     * 获取合并处理器
     *
     * @param mergeDataList 合并数据
     * @return 分段处理器
     */
    public static MergeProcessor getProcessor(List<MergeData> mergeDataList) {
        return new MergeProcessor(getInstance(mergeDataList));
    }

    /**
     * 获取合并实例
     *
     * @param mergeDataList 合并数据集合
     * @return 合并实例
     */
    public static List<BaseMerge> getInstance(List<MergeData> mergeDataList) {
        return mergeDataList.stream().map(MergeFactory::getInstance).collect(Collectors.toList());

    }

    /**
     * 获取合并实例
     *
     * @param mergeData 合并数据
     * @return 合并实例
     */
    public static BaseMerge getInstance(MergeData mergeData) {
        BaseMerge baseMerge = null;
        switch (mergeData.getMergeType()) {
            case BASIC:
                baseMerge = new MergeBasic(mergeData.getName(), mergeData.getReadSegmentMark()).basic(getBasic(mergeData));
                break;
            case EXPRESSION:
                List<MergeData> formatJson = mergeData.getChild();
                List<BaseMerge> formatBaseMerge = formatJson.stream().map(MergeFactory::getInstance).collect(Collectors.toList());
                baseMerge = new MergeExpression(mergeData.getName(), mergeData.getExpression(), formatBaseMerge);
                break;
            case CALCULATOR:
                List<MergeData> calculatorJson = mergeData.getChild();
                List<BaseMerge> calculatorBaseMerge = calculatorJson.stream().map(MergeFactory::getInstance).collect(Collectors.toList());
                baseMerge = new MergeCalculator(mergeData.getName(), mergeData.getExpression(), calculatorBaseMerge);
                break;
            case LOCAL_DATA_TIME:
                List<MergeData> localDateTimeJson = mergeData.getChild();
                List<BaseMerge> localDateTimeBaseMerge = localDateTimeJson.stream().map(MergeFactory::getInstance).collect(Collectors.toList());
                baseMerge = new MergeLocalDateTime(mergeData.getName(), localDateTimeBaseMerge);
                break;
        }
        // 获取拦截参数
        List<ByteHandler> packHandlers = mergeData.getPackHandler();
        baseMerge.addHandler(packHandlers);
        // 写入重复读参数
        baseMerge.setRetry(mergeData.getRetryType(), mergeData.getRetryKey(), mergeData.getRetrySize());
        return baseMerge;
    }

    /**
     * 获取数据转换类型
     *
     * @param mergeData 合并数据
     * @return 数据转换模型
     */
    private static Basic<?> getBasic(MergeData mergeData) {
        switch (mergeData.getBasicType()) {
            case BYTE:
                return new BasicByte();
            case BYTE_ARRAY:
                return new BasicByteArray();
            case SHORT:
                return new BasicShort();
            case INT:
                return new BasicInt();
            case LONG:
                return new BasicLong();
            case CASE:
                BasicCase basicCase = new BasicCase();
                mergeData.getCaseData().forEach(c -> basicCase.appendCase(c.getCaseKey(), c.getCaseValue()));
                return basicCase;
            case STRING:
                if (mergeData.getCharset() != null) {
                    return new BasicString(mergeData.getCharset());
                }
                return new BasicString();
            case STRING_HEX:
                return new BasicStringHex(mergeData.isReplenishZero());
        }
        return null;
    }

}
