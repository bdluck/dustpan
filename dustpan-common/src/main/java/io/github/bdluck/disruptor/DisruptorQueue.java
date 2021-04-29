package io.github.bdluck.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * @author bdluck
 */
public class DisruptorQueue<T> {

    private final static Logger log = LoggerFactory.getLogger(DisruptorQueue.class);

    /**
     * 队列
     */
    private final Disruptor<MsgEvent<T>> disruptor;

    /**
     * 环形队列
     */
    private final RingBuffer<MsgEvent<T>> ringBuffer;

    /**
     * 队列缓存
     */
    private static final List<DisruptorQueue<?>> DISRUPTOR_QUEUE_LIST = new ArrayList<>();

    @SafeVarargs
    public static <T> DisruptorQueue<T> getInstanceMultiOnce(final int ringBufferSize,
                                                             final ThreadFactory threadFactory,
                                                             final WaitStrategy waitStrategy, MsgHandler<T>... msgHandler) {
        return new DisruptorQueue<>(ringBufferSize, threadFactory, ProducerType.MULTI, waitStrategy, false, msgHandler);
    }

    @SafeVarargs
    public static <T> DisruptorQueue<T> getInstanceMultiDup(final int ringBufferSize,
                                                            final ThreadFactory threadFactory,
                                                            final WaitStrategy waitStrategy, MsgHandler<T>... msgHandler) {
        return new DisruptorQueue<>(ringBufferSize, threadFactory, ProducerType.MULTI, waitStrategy, true, msgHandler);
    }


    @SafeVarargs
    public static <T> DisruptorQueue<T> getInstanceSingleOnce(final int ringBufferSize,
                                                              final ThreadFactory threadFactory,
                                                              final WaitStrategy waitStrategy, MsgHandler<T>... msgHandler) {
        return new DisruptorQueue<>(ringBufferSize, threadFactory, ProducerType.SINGLE, waitStrategy, false, msgHandler);
    }

    @SafeVarargs
    public static <T> DisruptorQueue<T> getInstanceSingleDup(final int ringBufferSize,
                                                             final ThreadFactory threadFactory,
                                                             final WaitStrategy waitStrategy, MsgHandler<T>... msgHandler) {
        return new DisruptorQueue<>(ringBufferSize, threadFactory, ProducerType.SINGLE, waitStrategy, true, msgHandler);
    }

    @SafeVarargs
    private DisruptorQueue(
            final int ringBufferSize,
            final ThreadFactory threadFactory,
            final ProducerType producerType,
            final WaitStrategy waitStrategy,
            boolean dup,
            MsgHandler<T>... msgHandler) {
        if (null == msgHandler || msgHandler.length < 1) {
            throw new InvalidParameterException("handler is null");
        }
        disruptor = new Disruptor<>(new MsgEventFactory<>(), ringBufferSize, threadFactory, producerType, waitStrategy);
        // 异常处理
        disruptor.setDefaultExceptionHandler(new DisruptorExceptionHandler<>());
        ringBuffer = disruptor.getRingBuffer();
        // 是否重复消费
        if (dup) {
            disruptor.handleEventsWithWorkerPool(msgHandler);
        } else {
            disruptor.handleEventsWith(msgHandler);
        }
        disruptor.start();
        DISRUPTOR_QUEUE_LIST.add(this);
    }


    /**
     * 推送消息
     */
    public void postMsg(T data) {
        ringBuffer.publishEvent(new MsgEventTranslator<>(), data);
    }

    /**
     * 队列大小
     */
    public long getSize() {
        return ringBuffer.getBufferSize() - ringBuffer.remainingCapacity();
    }

    /**
     * 关闭服务
     */
    public void shutdown() {
        disruptor.shutdown();
    }

    /**
     * 关闭所的队列
     */
    public static void shutdownAll() {
        for (DisruptorQueue<?> disruptorQueue : DISRUPTOR_QUEUE_LIST) {
            disruptorQueue.shutdown();
        }
    }

    static class DisruptorExceptionHandler<T> implements ExceptionHandler<MsgEvent<T>> {

        @Override
        public void handleEventException(Throwable ex, long sequence, MsgEvent<T> event) {
            log.error("队列消费异常 序列:{} case:{} event:{}", sequence, ex.getMessage(), event.toString(), ex);
        }

        @Override
        public void handleOnStartException(Throwable ex) {
            log.error("队列启动异常 case:{}", ex.getMessage(), ex);
        }

        @Override
        public void handleOnShutdownException(Throwable ex) {
            log.error("队列结束异常 case:{}", ex.getMessage(), ex);
        }
    }
}
