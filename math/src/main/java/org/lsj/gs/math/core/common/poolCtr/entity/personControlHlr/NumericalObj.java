package org.lsj.gs.math.core.common.poolCtr.entity.personControlHlr;

// 數控物件
public class NumericalObj {
    private int id; // 個控代碼
    private String name; // 個控名稱
    private int value; // 個控權重

    public NumericalObj() {}

    public NumericalObj(int id, String name, int value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
