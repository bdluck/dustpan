package com.github.bdluck.merge.data;

/**
 * @author bdluck
 */
public class CaseData {
    /**
     * case键
     */
    private byte[] caseKey;
    /**
     * case值
     */
    private Object caseValue;

    public byte[] getCaseKey() {
        return caseKey;
    }

    public void setCaseKey(byte[] caseKey) {
        this.caseKey = caseKey;
    }

    public Object getCaseValue() {
        return caseValue;
    }

    public void setCaseValue(Object caseValue) {
        this.caseValue = caseValue;
    }
}
