package io.github.bdluck.handle;

/**
 * @author bdluck
 */
public interface Handler {

    /**
     * 是否拦截
     *
     * @param bytes 数据
     * @return 拦截结果
     */
    boolean isHandle(byte[] bytes);
}
