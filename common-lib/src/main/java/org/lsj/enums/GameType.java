package org.lsj.enums;

/**
 * 遊戲類型
 */
public enum GameType {
    INVALID(0), // 無效
    BANKER(1), // 搶莊類
    BAIREN(2), // 百人類
    FISH(3), // 魚機
    SLOT(4), // 老虎機
    BATTLE(5), // 對戰類
    THIRD_GAME(500);        //三方游戏game_id的起始id（与客户端约定大于此id为三方游戏）

    private final int code; // 編碼

    GameType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
