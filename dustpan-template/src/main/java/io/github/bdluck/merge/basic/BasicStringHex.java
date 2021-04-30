package io.github.bdluck.merge.basic;

import io.github.bdluck.utils.HexUtils;

/**
 * @author bdluck
 */
public class BasicStringHex implements Basic<String> {
    /**
     * 是否补充0
     */
    private final boolean replenishZero;

    public BasicStringHex(boolean replenishZero) {
        this.replenishZero = replenishZero;
    }

    @Override
    public String convert(byte[] data) {
        return HexUtils.getHexString(data, replenishZero);
    }
}
