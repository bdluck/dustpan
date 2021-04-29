package io.github.bdluck.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;

/**
 * @author bdluck
 */
public class MsgEventTranslator<T> implements EventTranslatorOneArg<MsgEvent<T>, T> {

    @Override
    public void translateTo(MsgEvent<T> event, long sequence, T arg0) {
        event.setSequence(sequence);
        event.setData(arg0);
    }
}
