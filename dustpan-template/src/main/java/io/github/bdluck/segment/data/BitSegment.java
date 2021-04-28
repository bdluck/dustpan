package io.github.bdluck.segment.data;

/**
 * @author bdluck
 */
public class BitSegment {
    /**
     * 位分段标记
     */
    private String segmentMark;
    /**
     * 位分段大小（所有的位长度加起来不能大小整个分段的8倍）
     */
    private int size;

    public String getSegmentMark() {
        return segmentMark;
    }

    public void setSegmentMark(String segmentMark) {
        this.segmentMark = segmentMark;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
