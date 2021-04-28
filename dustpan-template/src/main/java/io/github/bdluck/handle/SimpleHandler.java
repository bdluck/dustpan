package io.github.bdluck.handle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bdluck
 */
public class SimpleHandler implements Handler {

    /**
     * 拦截器集合
     */
    protected final List<Handler> handlers = new ArrayList<>();

    @Override
    public boolean isHandle(byte[] bytes) {
        return comparison(handlers, bytes);
    }

    @Override
    public void addHandler(List<? extends Handler> handlers) {
        this.handlers.addAll(handlers);
    }

}
