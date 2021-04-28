package io.github.bdluck.merge.basic;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author bdluck
 */
public class BasicString implements Basic<String> {
    /**
     * 编码
     */
    private CharsetType charsetType;

    public BasicString(CharsetType charsetType) {
        this.charsetType = charsetType;
    }

    public BasicString() {
    }

    @Override
    public String convert(byte[] data) {
        Charset charset = null;
        switch (charsetType) {
            case US_ASCII:
                charset = StandardCharsets.US_ASCII;
                break;
            case ISO_8859_1:
                charset = StandardCharsets.ISO_8859_1;
                break;
            case UTF_8:
                charset = StandardCharsets.UTF_8;
                break;
            case UTF_16BE:
                charset = StandardCharsets.UTF_16BE;
                break;
            case UTF_16LE:
                charset = StandardCharsets.UTF_16LE;
                break;
            case UTF_16:
                charset = StandardCharsets.UTF_16;
                break;
        }
        return new String(data, charset);
    }
}
