package io.github.bdluck.unpack;

import io.github.bdluck.handle.Handler;
import io.netty.buffer.ByteBuf;

/**
 * @author bdluck
 */
public interface Unpack extends Handler {

    /**
     * 解码包
     *
     * @param in 输入流
     * @return 解码结果
     */
    ByteBuf unpack(ByteBuf in) throws Exception;
}
