package io.github.bdluck.merge.basic;

/**
 * @author bdluck
 */
public class BasicByte implements Basic<Byte> {

    @Override
    public Byte convert(byte[] data) {
        if (data.length > 0) {
            return data[0];
        }
        return 0;
    }
}
