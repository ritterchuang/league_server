package com.lx.gs.mathStr.config.entity;

import com.lx.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;

// 遊戲中心管理器設定
public class GameCenterMgrConfig {
    private final PlayGameFieldConfig[] playGameFieldConfigArray; // 遊戲房間設定陣列

    public GameCenterMgrConfig(PlayGameFieldConfig[] playGameFieldConfigArray){
        this.playGameFieldConfigArray = playGameFieldConfigArray;
    }

    public PlayGameFieldConfig[] getPlayGameFieldConfigArray() {
        return playGameFieldConfigArray;
    }
}
