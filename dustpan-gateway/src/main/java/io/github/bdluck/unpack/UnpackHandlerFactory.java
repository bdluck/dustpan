package io.github.bdluck.unpack;

import io.github.bdluck.handle.BatchHandler;
import io.github.bdluck.handle.ByteHandler;
import io.github.bdluck.netty.ChannelHandlerFactory;
import io.github.bdluck.unpack.data.UnpackData;
import io.netty.channel.ChannelHandler;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bdluck
 */
public class UnpackHandlerFactory implements ChannelHandlerFactory {

    private final List<Unpack> unpackList;

    public UnpackHandlerFactory(List<UnpackData> unpackDataList) {
        this.unpackList = unpackDataList.stream().map(UnpackHandlerFactory::getInstance)
                .collect(Collectors.toList());
    }

    @Override
    public ChannelHandler newInstance() {
        return new UnpackHandler(unpackList);
    }

    /**
     * 获取拆包实例
     *
     * @param unpackData 拆包数据
     * @return 拆包对象
     */
    private static Unpack getInstance(UnpackData unpackData) {
        // 获取拦截参数
        List<ByteHandler> packHandlers = unpackData.getPackHandler();
        BatchHandler batchHandler = new BatchHandler(packHandlers);
        switch (unpackData.getUnpackType()) {
            case FIXED:
                FixedUnpack fixedUnpack = new FixedUnpack(unpackData.getLength());
                fixedUnpack.setHandler(batchHandler);
                return fixedUnpack;
            case LENGTH:
                LengthUnpack lengthUnpack = new LengthUnpack(
                        unpackData.getMaxFrameLength(),
                        unpackData.getLengthFieldOffset(),
                        unpackData.getLengthFieldLength(),
                        unpackData.getLengthAdjustment(),
                        unpackData.getInitialBytesToStrip(),
                        unpackData.isFailFast());
                lengthUnpack.setHandler(batchHandler);
                return lengthUnpack;
        }
        return null;
    }
}
