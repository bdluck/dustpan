package io.github.bdluck.merge.data;

import io.github.bdluck.handle.ByteHandler;
import io.github.bdluck.merge.basic.BasicType;
import io.github.bdluck.merge.basic.CharsetType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bdluck
 */
public class MergeData {
    /**
     * 合并名称（分发时转json的键）
     */
    private String name;
    /**
     * 读取的分段标记
     */
    private String readSegmentMark;
    /**
     * 重复读取类型（指定大小伙或者根据分段数据计算）
     */
    private RetryType retryType;
    /**
     * 重复合并键（根据某个分段值的大小进行多次合并 从1-n）
     */
    private String retryKey;
    /**
     * 重复合并大小（自定义的重复合并次数）
     */
    private int retrySize;
    /**
     * 表达式（用于表达式替换和计算时使用）
     */
    private String expression;
    /**
     * 合并类型
     */
    private MergeType mergeType;
    /**
     * 基础类型转换 用于Basic合并使用 将byte数据转为其他数据
     */
    private BasicType basicType;
    /**
     * 数据转为String的时候可以选择编码规则 默认不编码
     */
    private CharsetType charset;
    /**
     * 补充0 在转16进制时选择是否补充0
     */
    private boolean replenishZero = true;
    /**
     * Case集合 用于CASE类型的基础类型转换
     */
    private List<CaseData> caseData = new ArrayList<>();
    /**
     * 子合并 用于除BASIC合并以外的其他合并的入参数据
     */
    private List<MergeData> child = new ArrayList<>();
    /**
     * 包拦截器
     */
    private List<ByteHandler> packHandler = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReadSegmentMark() {
        return readSegmentMark;
    }

    public void setReadSegmentMark(String readSegmentMark) {
        this.readSegmentMark = readSegmentMark;
    }

    public RetryType getRetryType() {
        return retryType;
    }

    public void setRetryType(RetryType retryType) {
        this.retryType = retryType;
    }

    public String getRetryKey() {
        return retryKey;
    }

    public void setRetryKey(String retryKey) {
        this.retryKey = retryKey;
    }

    public int getRetrySize() {
        return retrySize;
    }

    public void setRetrySize(int retrySize) {
        this.retrySize = retrySize;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public MergeType getMergeType() {
        return mergeType;
    }

    public void setMergeType(MergeType mergeType) {
        this.mergeType = mergeType;
    }

    public BasicType getBasicType() {
        return basicType;
    }

    public void setBasicType(BasicType basicType) {
        this.basicType = basicType;
    }

    public CharsetType getCharset() {
        return charset;
    }

    public void setCharset(CharsetType charset) {
        this.charset = charset;
    }

    public boolean isReplenishZero() {
        return replenishZero;
    }

    public void setReplenishZero(boolean replenishZero) {
        this.replenishZero = replenishZero;
    }

    public List<CaseData> getCaseData() {
        return caseData;
    }

    public void setCaseData(List<CaseData> caseData) {
        this.caseData = caseData;
    }

    public List<MergeData> getChild() {
        return child;
    }

    public void setChild(List<MergeData> child) {
        this.child = child;
    }

    public List<ByteHandler> getPackHandler() {
        return packHandler;
    }

    public void setPackHandler(List<ByteHandler> packHandler) {
        this.packHandler = packHandler;
    }
}
