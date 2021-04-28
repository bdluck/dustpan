package io.github.bdluck.merge;

import io.github.bdluck.merge.basic.Basic;
import io.github.bdluck.merge.basic.BasicByteArray;

/**
 * @author bdluck
 */
public class MergeBasic extends BaseMerge {
    /**
     * 分段键值
     */
    protected final String readSegmentMark;
    /**
     * 数据转换
     */
    private Basic<?> basic = new BasicByteArray();

    public MergeBasic(String name, String readSegmentMark) {
        super(name);
        this.readSegmentMark = readSegmentMark;
    }

    /**
     * 设置数据转换
     *
     * @param basic 转换器
     */
    public MergeBasic basic(Basic<?> basic) {
        this.basic = basic;
        return this;
    }

    @Override
    public Object getMessage(int retryIndex, SegmentWrapper segmentWrapper) {
        byte[] segment;
        if (retryIndex != 0) {
            segment = segmentWrapper.getSegment(readSegmentMark + retryIndex);
        } else {
            segment = segmentWrapper.getSegment(readSegmentMark);
        }
        if (segment != null) {
            return basic.convert(segment);
        }
        return null;
    }
}
