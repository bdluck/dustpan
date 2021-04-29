package io.github.bdluck.unpack;

import io.github.bdluck.handle.ByteHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bdluck
 */
public class UnpackData {
    /**
     * 最大包长
     */
    private int maxFrameLength;
    /**
     * 长度域偏移
     */
    private int lengthFieldOffset;
    /**
     * 长度域长度
     */
    private int lengthFieldLength;
    /**
     * 长度域长度调整
     */
    private int lengthAdjustment;
    /**
     * 需要跳过读取的长度
     */
    private int initialBytesToStrip;
    /**
     * 是否快速失败
     */
    private boolean failFast;
    /**
     * 固定包长度
     */
    private int length;
    /**
     * 拆包类型（定长，不定长）
     */
    private UnpackType unpackType;
    /**
     * 包拦截器
     */
    private List<ByteHandler> packHandler = new ArrayList<>();

    public int getMaxFrameLength() {
        return maxFrameLength;
    }

    public void setMaxFrameLength(int maxFrameLength) {
        this.maxFrameLength = maxFrameLength;
    }

    public int getLengthFieldOffset() {
        return lengthFieldOffset;
    }

    public void setLengthFieldOffset(int lengthFieldOffset) {
        this.lengthFieldOffset = lengthFieldOffset;
    }

    public int getLengthFieldLength() {
        return lengthFieldLength;
    }

    public void setLengthFieldLength(int lengthFieldLength) {
        this.lengthFieldLength = lengthFieldLength;
    }

    public int getLengthAdjustment() {
        return lengthAdjustment;
    }

    public void setLengthAdjustment(int lengthAdjustment) {
        this.lengthAdjustment = lengthAdjustment;
    }

    public int getInitialBytesToStrip() {
        return initialBytesToStrip;
    }

    public void setInitialBytesToStrip(int initialBytesToStrip) {
        this.initialBytesToStrip = initialBytesToStrip;
    }

    public boolean isFailFast() {
        return failFast;
    }

    public void setFailFast(boolean failFast) {
        this.failFast = failFast;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public UnpackType getUnpackType() {
        return unpackType;
    }

    public void setUnpackType(UnpackType unpackType) {
        this.unpackType = unpackType;
    }

    public List<ByteHandler> getPackHandler() {
        return packHandler;
    }

    public void setPackHandler(List<ByteHandler> packHandler) {
        this.packHandler = packHandler;
    }
}
