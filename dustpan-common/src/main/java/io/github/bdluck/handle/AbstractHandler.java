package io.github.bdluck.handle;

/**
 * @author bdluck
 */
public class AbstractHandler implements Handler {

    private Handler handler = new DefaultHandler();

    @Override
    public boolean isHandle(byte[] bytes) {
        return handler.isHandle(bytes);
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
