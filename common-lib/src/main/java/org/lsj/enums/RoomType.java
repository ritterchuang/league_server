package org.lsj.enums;

/**
 * 在 JS coreserver 叫做 GameType
 */
public enum RoomType {

    FIELD(1), // 游戏场
    BOX(2), // 包厢
    MATCH(3); // 比赛

    private int code;

    RoomType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
