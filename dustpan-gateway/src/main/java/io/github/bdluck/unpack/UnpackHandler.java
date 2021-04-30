package io.github.bdluck.unpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author bdluck
 */
class UnpackHandler extends ByteToMessageDecoder {
    /**
     * 拆包器
     */
    private final List<Unpack> unpackList;

    UnpackHandler(List<Unpack> unpackList) {
        this.unpackList = unpackList;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        ByteBuf byteBuf = unpack(in);
        if (byteBuf != null) {
            out.add(byteBuf);
        }
    }

    /**
     * 数据拆包
     *
     * @param in 输入数据包
     * @return 解析后数据
     */
    public ByteBuf unpack(ByteBuf in) {
        byte[] targetBytes = new byte[in.readableBytes()];
        in.getBytes(in.readerIndex(), targetBytes);
        for (Unpack unpack : unpackList) {
            // 判断是否属于该拆包器
            if (unpack.isHandle(targetBytes)) {
                // 基础长度拆包
                ByteBuf byteBuf = null;
                try {
                    byteBuf = unpack.unpack(in);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 只拆一次 如果未拆出结果说明不是一个整包 直接返回
                if (byteBuf != null) {
                    return byteBuf;
                }
                break;
            }
        }
        return null;
    }
}
