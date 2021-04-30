package io.github.bdluck.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author bdluck
 */
public abstract class AbstractNettyClient implements  Server {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final int CORE = Runtime.getRuntime().availableProcessors();
    private NioEventLoopGroup workGroup;
    private Channel channel;
    private static Bootstrap bootstrap;
    private final AtomicBoolean init = new AtomicBoolean(false);
    protected final String host;
    protected final int port;
    protected final String serverId;
    private final ChannelHandlerFactory factory;

    public AbstractNettyClient(String serverId, String host, int port, ChannelHandlerFactory factory) {
        this.serverId = serverId;
        this.host = host;
        this.port = port;
        this.factory = factory;
    }

    @Override
    public void start() {
        try {
            this.init();
            this.channel = bootstrap.connect(host, port).sync().channel();
            log.info("{} connect to > {}:{}", serverId, host, port);
        } catch (Exception e) {
            log.error("{} connect error {}:{}", serverId, host, port);
        }
    }

    @Override
    public void stop() {
        if (this.channel != null) {
            this.channel.close();
        }
        if (this.workGroup != null) {
            this.workGroup.shutdownGracefully();
        }
    }

    /**
     * 是否连接
     */
    public boolean isConnect() {
        return this.channel != null && this.channel.isActive();
    }

    /**
     * 消息下发
     *
     * @param msg 消息
     */
    public ChannelFuture writeAndFlush(Object msg) {
        if (this.isConnect()) {
            log.debug("{} send to > {}:{} msg:{}", serverId, host, port, msg.toString());
            return this.channel.writeAndFlush(msg);
        } else {
            log.error("connect channel is null");
            return null;
        }
    }

    /**
     * 自定义配置
     *
     * @param bootstrap 引导器
     */
    protected void config(Bootstrap bootstrap) {

    }

    /**
     * 初始化信息
     */
    private synchronized void init() {
        if (!this.init.get()) {
            this.workGroup = new NioEventLoopGroup(CORE);
            bootstrap = new Bootstrap();
            config(bootstrap);
            bootstrap.group(this.workGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.TCP_NODELAY, true);
            bootstrap.handler(factory.newInstance());
            this.init.set(true);
        }
    }
}
