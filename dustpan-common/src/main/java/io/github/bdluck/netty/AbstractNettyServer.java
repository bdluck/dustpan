package io.github.bdluck.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bdluck
 */
public abstract class AbstractNettyServer implements Server {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final int CORE = Runtime.getRuntime().availableProcessors();
    private NioEventLoopGroup bossGroup;
    private NioEventLoopGroup workGroup;
    private final String serverId;
    private final int[] port;
    private final ChannelHandlerFactory factory;

    public AbstractNettyServer(String serverId, int port, ChannelHandlerFactory factory) {
        this.serverId = serverId;
        this.port = new int[]{port};
        this.factory = factory;
    }

    public AbstractNettyServer(String serverId, ChannelHandlerFactory factory, int... port) {
        this.serverId = serverId;
        this.factory = factory;
        this.port = port;
    }

    @Override
    public void start() {
        this.bossGroup = new NioEventLoopGroup(CORE);
        this.workGroup = new NioEventLoopGroup(CORE + 1);
        final ServerBootstrap bootstrap = new ServerBootstrap();
        config(bootstrap);
        bootstrap.group(this.bossGroup, this.workGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.childHandler(factory.newInstance());
        try {
            for (int p : port) {
                ChannelFuture feature = bootstrap.bind(p);
                feature.addListener((ChannelFutureListener) future -> {
                    if (future.isSuccess()) {
                        log.info("{} start at port: {} ", serverId, p);
                    } else {
                        log.info("{} start at port: {} failed!! ", serverId, p);
                    }
                });
            }
        } catch (Exception e) {
            log.error("{} start error", serverId, e);
        }
    }

    /**
     * 自定义配置
     *
     * @param bootstrap 引导器
     */
    protected void config(ServerBootstrap bootstrap) {

    }

    @Override
    public void stop() {
        if (this.bossGroup != null) {
            this.bossGroup.shutdownGracefully();
        }
        if (this.workGroup != null) {
            this.workGroup.shutdownGracefully();
        }
    }
}
