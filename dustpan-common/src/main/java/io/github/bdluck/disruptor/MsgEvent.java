package io.github.bdluck.disruptor;

/**
 * @author bdluck
 */
public class MsgEvent<T> {

    /**
     * 消费序号
     */
    private long sequence;

    /**
     * 是否最后一批次
     */
    private boolean endOfBatch;

    /**
     * 消息数据
     */
    private T data;

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }

    public boolean isEndOfBatch() {
        return endOfBatch;
    }

    public void setEndOfBatch(boolean endOfBatch) {
        this.endOfBatch = endOfBatch;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
