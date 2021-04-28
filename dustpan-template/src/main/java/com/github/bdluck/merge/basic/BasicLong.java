package com.github.bdluck.merge.basic;

/**
 * @author bdluck
 */
public class BasicLong implements Basic<Long> {

    @Override
    public Long convert(byte[] data) {
        int length = Math.min(data.length, 8);
        long value = 0;
        for (int i = 0; i < length; i++) {
            value |= (data[i] & 0xff) << ((length - i - 1) * 8);
        }
        return value;
    }
}
