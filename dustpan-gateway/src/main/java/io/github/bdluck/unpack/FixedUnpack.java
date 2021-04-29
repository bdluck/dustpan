package io.github.bdluck.unpack;

import io.github.bdluck.handle.AbstractHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.FixedLengthFrameDecoder;

/**
 * @author bdluck
 */
public class FixedUnpack extends AbstractHandler implements Unpack {

    private final FixedWrapper fixedWrapper;

    public FixedUnpack(int frameLength) {
        this.fixedWrapper = new FixedWrapper(frameLength);
    }

    @Override
    public ByteBuf unpack(ByteBuf in) throws Exception {
        return (ByteBuf) fixedWrapper.decode(null, in);
    }

    static class FixedWrapper extends FixedLengthFrameDecoder {

        /**
         * Creates a new instance.
         *
         * @param frameLength the length of the frame
         */
        public FixedWrapper(int frameLength) {
            super(frameLength);
        }

        @Override
        public Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
            return super.decode(ctx, in);
        }
    }
}
