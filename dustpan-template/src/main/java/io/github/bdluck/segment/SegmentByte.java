package io.github.bdluck.segment;

import java.util.Collections;
import java.util.List;

/**
 * @author bdluck
 */
public class SegmentByte extends BaseSegment {

    public SegmentByte(String segmentMark, int length) {
        super(segmentMark, length);
    }

    @Override
    public List<SegmentResult> segment(ByteWrapper byteWrapper) {
        byte[] data = read(length, byteWrapper);
        return Collections.singletonList(new SegmentResult(segmentMark, data));
    }
}
