package io.github.bdluck.merge.basic;

/**
 * @author bdluck
 */
public class BasicShort implements Basic<Short> {

    @Override
    public Short convert(byte[] data) {
        int length = Math.min(data.length, 2);
        short value = 0;
        for (int i = 0; i < length; i++) {
            value |= (data[i] & 0xff) << ((length - i - 1) * 8);
        }
        return value;
    }
}
