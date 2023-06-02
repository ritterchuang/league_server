package org.lsj.gs.state;

public enum BaiRenGameState {

    WAIT_BEGIN(1),      // 等待游戏开始
    GAME_BEGIN(2),      // 游戏开始
    GAME_END(99);       // 游戏结束

    private final int code;

    BaiRenGameState(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
