package io.github.bdluck.segment;

/**
 * @author bdluck
 */
public class SegmentResult {

    /**
     * 分段名称
     */
    private String name;
    /**
     * 分段结果
     */
    private byte[] result;

    public SegmentResult(String name, byte[] result) {
        this.name = name;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getResult() {
        return result;
    }

    public void setResult(byte[] result) {
        this.result = result;
    }
}
