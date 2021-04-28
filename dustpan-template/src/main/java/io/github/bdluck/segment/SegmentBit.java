package io.github.bdluck.segment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bdluck
 */
public class SegmentBit extends BaseSegment {
    /**
     * bit长度和
     */
    private int totalBitSize;
    /**
     * bit分段集合
     */
    private final List<BitMap> bitMapList = new ArrayList<>();

    public SegmentBit(int length) {
        super("", length);
    }

    /**
     * 获取最小byte长度
     */
    private int byteLength() {
        return (totalBitSize >>> 3) + ((totalBitSize % 8) == 0 ? 0 : 1);
    }

    @Override
    public List<SegmentResult> segment(ByteWrapper byteWrapper) {
        List<SegmentResult> segmentResults = new ArrayList<>();
        byte[] segment = read(length, byteWrapper);
        if (segment != null && !bitMapList.isEmpty()) {
            // 获取分段所有bit
            Bit[] bits = Bit.convert(segment);
            int mark = 0;
            for (BitMap bitMap : bitMapList) {
                int readLength = bitMap.length;
                Bit[] bit = new Bit[readLength];
                System.arraycopy(bits, mark, bit, 0, readLength);
                mark += readLength;
                // 加入分段列表
                segmentResults.add(new SegmentResult(bitMap.getSegmentMark(), Bit.toByteArray(bit)));
            }
        }
        return segmentResults;
    }

    /**
     * 添加分段条件
     *
     * @param segmentMark 位分段标记
     * @param bitSize     bit位长度
     */
    public void append(String segmentMark, int bitSize) {
        this.totalBitSize += bitSize;
        if (byteLength() > length) {
            throw new IllegalArgumentException("byte size is less than bit size!");
        }
        BitMap map = new BitMap(segmentMark, bitSize);
        bitMapList.add(map);
    }

    /**
     * bit分段
     */
    public static class BitMap {
        /**
         * 分段标识
         */
        private final String segmentMark;
        /**
         * 分段长度
         */
        private final int length;

        public BitMap(String segmentMark, int length) {
            this.segmentMark = segmentMark;
            this.length = length;
        }

        public String getSegmentMark() {
            return segmentMark;
        }

        public int getLength() {
            return length;
        }
    }

    public enum Bit {

        ZERO("0"),
        ONE("1");

        private final String bit;

        Bit(String bit) {
            this.bit = bit;
        }

        public String getBit() {
            return bit;
        }

        /**
         * bit转换
         */
        public static Bit[] convert(byte b) {
            Bit[] array = new Bit[8];
            for (int i = 7; i >= 0; i--) {
                byte b1 = (byte) (b & 1);
                if (b1 == 0x01) {
                    array[i] = Bit.ONE;
                } else {
                    array[i] = Bit.ZERO;
                }
                b = (byte) (b >> 1);
            }
            return array;
        }

        /**
         * bit数组转换
         */
        public static Bit[] convert(byte[] bytes) {
            if (bytes == null) {
                return null;
            }
            int index = 0;
            Bit[] bits = new Bit[bytes.length * 8];
            for (byte b : bytes) {
                Bit[] bit = convert(b);
                for (int i = 0; i < 8; i++) {
                    bits[index++] = bit[i];
                }
            }
            return bits;
        }

        /**
         * 转byte数组
         */
        public static byte[] toByteArray(Bit[] bits) {
            String binary = toBinaryString(bits);
            int byteSize = binary.length() >> 3;
            byte[] bytes = new byte[byteSize];
            int index = 0;
            for (int i = 0; i < binary.length(); ) {
                int tmp = i + 8;
                String byteString = binary.substring(i, tmp);
                i = tmp;
                bytes[index++] = Integer.valueOf(byteString, 2).byteValue();
            }
            return bytes;
        }

        /**
         * 转二进制字符
         */
        public static String toBinaryString(Bit[] bits) {
            if (bits == null) {
                throw new NullPointerException();
            }
            int mod = bits.length % 8;
            StringBuilder builder = new StringBuilder();
            // 不足八位 高位补0
            if (mod != 0) {
                for (int i = 0; i < 8 - mod; i++) {
                    builder.append("0");
                }
            }
            for (Bit bit : bits) {
                builder.append(bit.getBit());
            }
            return builder.toString();
        }


        @Override
        public String toString() {
            return bit;
        }
    }
}
