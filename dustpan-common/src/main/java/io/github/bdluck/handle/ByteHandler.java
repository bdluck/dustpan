package io.github.bdluck.handle;

import java.util.Arrays;

/**
 * @author bdluck
 */
public class ByteHandler implements Handler {

    /**
     * 拦截数据偏移位
     */
    private int offset;
    /**
     * 拦截数据长度
     */
    private int length;
    /**
     * 拦截数据
     */
    private byte[] handleBytes;

    @Override
    public boolean isHandle(byte[] bytes) {
        if (bytes.length < offset + length) {
            return false;
        }
        byte[] targetBytes = new byte[length];
        System.arraycopy(bytes, offset, targetBytes, 0, length);
        return Arrays.equals(handleBytes, targetBytes);
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setHandleBytes(byte[] handleBytes) {
        this.handleBytes = handleBytes;
    }
}
