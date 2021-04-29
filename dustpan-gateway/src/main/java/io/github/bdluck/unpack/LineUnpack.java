package io.github.bdluck.unpack;

import io.github.bdluck.handle.AbstractHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LineBasedFrameDecoder;

/**
 * @author bdluck
 */
public class LineUnpack extends AbstractHandler implements Unpack {

    private final LineWrapper lineWrapper ;

    public LineUnpack(int maxLength) {
        this.lineWrapper = new LineWrapper(maxLength);
    }

    @Override
    public ByteBuf unpack(ByteBuf in) throws Exception {
        return (ByteBuf) lineWrapper.decode(null, in);
    }

    static class LineWrapper extends LineBasedFrameDecoder {

        public LineWrapper(int maxLength) {
            super(maxLength);
        }

        @Override
        public Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
            return super.decode(ctx, in);
        }
    }
}
