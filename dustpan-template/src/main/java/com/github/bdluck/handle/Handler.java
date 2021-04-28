package com.github.bdluck.handle;

import java.util.List;

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

    /**
     * 添加拦截器
     *
     * @param handlers 拦截器集合
     */
    default void addHandler(List<? extends Handler> handlers) {

    }

    /**
     * 比对数据
     *
     * @param handlers 拦截器集合
     * @param bytes    数据
     * @return 比对结果
     */
    default boolean comparison(List<Handler> handlers, byte[] bytes) {
        if (handlers.isEmpty()) {
            return true;
        }
        for (Handler handler : handlers) {
            if (!handler.isHandle(bytes)) {
                return false;
            }
        }
        return true;
    }
}
