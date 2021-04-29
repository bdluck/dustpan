package io.github.bdluck.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author bdluck
 */
public class MsgEventFactory<T> implements EventFactory<MsgEvent<T>> {
    @Override
    public MsgEvent<T> newInstance() {
        return new MsgEvent<>();
    }
}
