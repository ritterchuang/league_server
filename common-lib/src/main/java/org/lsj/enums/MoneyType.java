package org.lsj.enums;

public enum MoneyType {

    DIAMOND(1),
    GOLD(2),
    BEAN(3),
    DOLLAR(4);

    private int code;

    MoneyType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
