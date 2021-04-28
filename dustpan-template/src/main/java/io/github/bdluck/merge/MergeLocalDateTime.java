package io.github.bdluck.merge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author bdluck
 */
public class MergeLocalDateTime extends BaseMerge {

    private final static Logger log = LoggerFactory.getLogger(MergeLocalDateTime.class);

    /**
     * 合并时间数据
     */
    private final List<? extends BaseMerge> merges;

    public MergeLocalDateTime(String name, List<? extends BaseMerge> merges) {
        super(name);
        this.merges = merges;
    }

    @Override
    public Object getMessage(int retryIndex, SegmentWrapper segmentWrapper) {
        if (merges.size() != 6) {
            log.error("合并时间数据失败,合并数据长度不为6位 实际长度:{}", merges.size());
        }
        try {
            int[] data = merges.stream()
                    .mapToInt(merges -> Integer.parseInt(String.valueOf(merges.getMessage(retryIndex, segmentWrapper))))
                    .toArray();
            return LocalDateTime.of(data[0], data[1], data[2], data[3], data[4], data[5]);
        } catch (Exception e) {
            log.error("合并时间失败", e);
        }
        return null;
    }
}
