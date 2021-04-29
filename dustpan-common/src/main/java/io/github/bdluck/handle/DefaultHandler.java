package io.github.bdluck.handle;

/**
 * @author bdluck
 */
public class DefaultHandler implements Handler {

    @Override
    public boolean isHandle(byte[] bytes) {
        return true;
    }
}
