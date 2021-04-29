package io.github.bdluck.unpack;

import io.github.bdluck.handle.BatchHandler;
import io.github.bdluck.handle.ByteHandler;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bdluck
 */
public class UnpackFactory {

    /**
     * 获取拆包实例
     *
     * @param unpackDataList 拆包数据集合
     * @return 拆包实例
     */
    public static List<Unpack> getInstance(List<UnpackData> unpackDataList) {
        return unpackDataList.stream().map(UnpackFactory::getInstance).collect(Collectors.toList());
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
