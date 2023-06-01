package org.lsj.db.enums;

public enum SQLParamType {

    INT(1),
    STRING(2),
    BOOLEAN(3),
    TIMESTAMP(4);

    private final int code;

    SQLParamType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
