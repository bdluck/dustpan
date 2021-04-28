package com.github.bdluck.merge.basic;

/**
 * @author bdluck
 */
public class BasicInt implements Basic<Integer> {

    @Override
    public Integer convert(byte[] data) {
        int length = Math.min(data.length, 4);
        int value = 0;
        for (int i = 0; i < length; i++) {
            value |= (data[i] & 0xff) << ((length - i - 1) * 8);
        }
        return value;
    }
}
