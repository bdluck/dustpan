package com.github.bdluck.merge.basic;

/**
 * @author bdluck
 */
public interface Basic<T> {

    /**
     * 数据转换
     *
     * @param data 元数据
     * @return 转换结果
     */
    T convert(byte[] data);
}
