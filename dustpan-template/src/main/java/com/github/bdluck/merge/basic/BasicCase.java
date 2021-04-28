package com.github.bdluck.merge.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author bdluck
 */
public class BasicCase implements Basic<Object> {
    /**
     * case -> result
     */
    private final List<CaseMap> caseMapList = new ArrayList<>();

    @Override
    public Object convert(byte[] data) {
        if (caseMapList.isEmpty()) {
            return data;
        }
        for (CaseMap map : caseMapList) {
            if (Arrays.equals(map.getCaseKey(), data)) {
                return map.getCaseValue();
            }
        }
        return data;
    }

    /**
     * 添加case映射
     *
     * @param caseKey   映射键
     * @param caseValue 映射值
     */
    public void appendCase(byte[] caseKey, Object caseValue) {
        caseMapList.add(new CaseMap(caseKey, caseValue));
    }

    static class CaseMap {
        private final byte[] caseKey;
        private final Object caseValue;

        public CaseMap(byte[] caseKey, Object caseValue) {
            this.caseKey = caseKey;
            this.caseValue = caseValue;
        }

        public byte[] getCaseKey() {
            return caseKey;
        }

        public Object getCaseValue() {
            return caseValue;
        }
    }
}
