package io.github.bdluck.segment;

/**
 * @author bdluck
 */
public class ByteWrapper {

    /**
     * 剩余字节
     */
    private  byte[] rest;

    public ByteWrapper(byte[] rest) {
        this.rest = rest;
    }

    public byte[] getRest() {
        return rest;
    }

    public void setRest(byte[] rest) {
        this.rest = rest;
    }
}
