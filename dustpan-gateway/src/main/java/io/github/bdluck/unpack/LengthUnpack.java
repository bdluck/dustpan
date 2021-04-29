package io.github.bdluck.unpack;

import io.github.bdluck.handle.AbstractHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author bdluck
 */
public class LengthUnpack extends AbstractHandler implements Unpack {

    private final LengthWrapper lengthWrapper;

    public LengthUnpack(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip, boolean failFast) {
        this.lengthWrapper = new LengthWrapper(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip, failFast);
    }

    @Override
    public ByteBuf unpack(ByteBuf in) throws Exception {
        return (ByteBuf) lengthWrapper.decode(null, in);
    }

    static class LengthWrapper extends LengthFieldBasedFrameDecoder {

        public LengthWrapper(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip, boolean failFast) {
            super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip, failFast);
        }

        @Override
        public Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
            return super.decode(ctx, in);
        }
    }
}
