package io.github.bdluck.utils;

/**
 * @author bdluck
 */
public class HexUtils {

    /**
     * 获取16进制字符 replenish
     */
    public static String getHexString(byte[] data) {
        if (null != data && data.length > 0) {
            return getHexString(data, 0, data.length, "", true);
        }
        return "";
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
    public static String getHexString(byte[] data, String split) {
        if (null != data && data.length > 0) {
            return getHexString(data, 0, data.length, split, true);
        }
        return "";
    }

    /**
     * 解析16进制字符串
     */
    public static byte[] parseHexString(String data, String split) {
        String[] sArray = data.split(split);
        byte[] bytes = new byte[sArray.length];
        for (int i = 0; i < sArray.length; i++) {
            byte b = Byte.parseByte(sArray[i], 16);
            bytes[i] = b;
        }
        return bytes;
    }

    /**
     * 获取16进制字符
     */
    public static String getHexString(byte data) {
        return String.format("%02x", data);
    }

    /**
     * 获取16进制字符 不补充0
     */
    public static String getHexStringNoZero(byte data) {
        return String.format("%2x", data).trim();
    }

    /**
     * 获取16进制字符
     */
    public static String getHexString(byte[] data, int offset, int limit, String sep, boolean replenishZero) {
        if (null != data && data.length >= (offset + limit)) {
            StringBuilder sb = new StringBuilder();
            for (int i = offset; i < offset + limit; i++) {
                if (replenishZero) {
                    sb.append(getHexString(data[i]));
                } else {
                    sb.append(getHexStringNoZero(data[i]));
                }
                sb.append(sep);
            }
            return sb.toString();
        }
        return "";
    }

}
