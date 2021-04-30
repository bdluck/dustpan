package io.github.bdluck.unpack.data;

/**
 * @author bdluck
 */
public enum UnpackType {
    /**
     * 基于指定长度
     */
    FIXED,
    /**
     * 基于不定长度
     */
    LENGTH,
    /**
     * 基于换行符
     */
    LINE,
    /**
     * 基于分隔符
     */
    SPLIT;
}
