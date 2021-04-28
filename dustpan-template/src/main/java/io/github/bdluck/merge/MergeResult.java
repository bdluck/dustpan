package io.github.bdluck.merge;

/**
 * @author bdluck
 */
public class MergeResult {
    /**
     * 数据名称
     */
    private String name;
    /**
     * 数据信息
     */
    private Object result;

    public MergeResult(String name, Object result) {
        this.name = name;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
