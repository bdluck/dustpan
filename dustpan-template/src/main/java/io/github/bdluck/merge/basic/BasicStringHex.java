package io.github.bdluck.merge.basic;

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
        return getHexString(data, replenishZero);
    }

    /**
     * 获取16进制字符
     */
    public static String getHexString(byte[] data, boolean replenishZero) {
        if (null != data && data.length > 0) {
            return getHexString(data, 0, data.length, "", replenishZero);
        }
        return "";
    }

    /**
     * 获取16进制字符
     */
    public static String getHexString(byte[] data, int offset, int limit, String sep, boolean replenishZero) {
        if (null != data && data.length >= (offset + limit)) {
            StringBuilder sb = new StringBuilder();
            for (int i = offset; i < offset + limit; i++) {
                if (replenishZero) {
                    sb.append(String.format("%02x", data[i]));
                } else {
                    sb.append(String.format("%2x", data[i]).trim());
                }
                sb.append(sep);
            }
            return sb.toString();
        }
        return "";
    }
}
