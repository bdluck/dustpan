package com.github.bdluck.merge.basic;

/**
 * @author bdluck
 */
public class BasicByteArray implements Basic<byte[]> {

    @Override
    public byte[] convert(byte[] data) {
        return data;
    }
}
