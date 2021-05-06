package io.github.bdluck;

import io.github.bdluck.handle.ByteHandler;
import io.github.bdluck.netty.NettyServer;
import io.github.bdluck.netty.BatchChannelHandlerFactory;
import io.github.bdluck.netty.ChannelHandlerFactory;
import io.github.bdluck.unpack.data.UnpackData;
import io.github.bdluck.unpack.UnpackHandlerFactory;
import io.github.bdluck.unpack.data.UnpackType;
import io.github.bdluck.utils.HexUtils;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author bdluck
 */
public class GatewayExample {

    public static void main(String[] args) {

        // 获取消费者
        ChannelHandlerFactory factory = new PacketHandlerFactory(packet ->
                System.out.println("收到消息  type:" + packet.getPacketType() + " data:" + HexUtils.getHexString(packet.getData())));

        // 拆包器
        UnpackData unpackData = new UnpackData();
        unpackData.setUnpackType(UnpackType.LENGTH);
        unpackData.setMaxFrameLength(32767);
        unpackData.setLengthFieldOffset(2);
        unpackData.setLengthFieldLength(2);
        unpackData.setLengthAdjustment(2);
        unpackData.setInitialBytesToStrip(0);
        unpackData.setFailFast(true);
        ByteHandler byteHandler = new ByteHandler();
        byteHandler.setOffset(0);
        byteHandler.setLength(2);
        byteHandler.setHandleBytes(new byte[]{121, 121});
        unpackData.setPackHandler(Collections.singletonList(byteHandler));

        UnpackData unpackData1 = new UnpackData();
        unpackData1.setUnpackType(UnpackType.LENGTH);
        unpackData1.setMaxFrameLength(32767);
        unpackData1.setLengthFieldOffset(2);
        unpackData1.setLengthFieldLength(1);
        unpackData1.setLengthAdjustment(2);
        unpackData1.setInitialBytesToStrip(0);
        unpackData1.setFailFast(true);
        ByteHandler byteHandler1 = new ByteHandler();
        byteHandler1.setOffset(0);
        byteHandler1.setLength(2);
        byteHandler1.setHandleBytes(new byte[]{120, 120});
        unpackData.setPackHandler(Collections.singletonList(byteHandler1));

        UnpackHandlerFactory factory1 = new UnpackHandlerFactory(Arrays.asList(unpackData, unpackData1));

        BatchChannelHandlerFactory batchChannelHandlerFactory = new BatchChannelHandlerFactory(Arrays.asList(factory, factory1));

        NettyServer gatewayServer = new NettyServer("测试网关", batchChannelHandlerFactory, 21100);
        gatewayServer.start();

    }
}
