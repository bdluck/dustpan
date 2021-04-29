package io.github.bdluck.handle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bdluck
 */
public class BatchHandler implements Handler {

    /**
     * 拦截器集合
     */
    private final List<Handler> handlers = new ArrayList<>();

    public BatchHandler(List<? extends Handler> handlers) {
        this.handlers.addAll(handlers);
    }

    @Override
    public boolean isHandle(byte[] bytes) {
        return comparison(handlers, bytes);
    }

    /**
     * 比对数据
     *
     * @param handlers 拦截器集合
     * @param bytes    数据
     * @return 比对结果
     */
    private boolean comparison(List<Handler> handlers, byte[] bytes) {
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
