package io.github.bdluck.unpack;

import io.github.bdluck.handle.AbstractHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

/**
 * @author bdluck
 */
public class SplitUnpack extends AbstractHandler implements Unpack {

    private final SplitWrapper splitWrapper;

    public SplitUnpack(int maxFrameLength, byte[] delimit) {
        this.splitWrapper = new SplitWrapper(maxFrameLength, Unpooled.wrappedBuffer(delimit));
    }

    @Override
    public ByteBuf unpack(ByteBuf in) throws Exception {
        return (ByteBuf) splitWrapper.decode(null, in);
    }

    static class SplitWrapper extends DelimiterBasedFrameDecoder {

        public SplitWrapper(int maxFrameLength, ByteBuf delimiter) {
            super(maxFrameLength, delimiter);
        }

        @Override
        public Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
            return super.decode(ctx, in);
        }
    }
}
