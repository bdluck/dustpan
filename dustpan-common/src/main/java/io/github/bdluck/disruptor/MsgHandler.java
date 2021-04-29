package io.github.bdluck.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * @author bdluck
 */
public interface MsgHandler<T> extends EventHandler<MsgEvent<T>>, WorkHandler<MsgEvent<T>> {

    @Override
    default void onEvent(MsgEvent<T> event, long sequence, boolean endOfBatch) {
        onMsg(event.getData());
    }

    @Override
    default void onEvent(MsgEvent<T> event) {
        onMsg(event.getData());
    }

    void onMsg(T data);
}
