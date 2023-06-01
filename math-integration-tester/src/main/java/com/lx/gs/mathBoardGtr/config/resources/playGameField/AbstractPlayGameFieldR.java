package com.lx.gs.mathBoardGtr.config.resources.playGameField;

import com.lx.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;

// 抽象遊戲房間設定
public abstract class AbstractPlayGameFieldR implements IPlayGameFieldR{
    public abstract PlayGameFieldConfig create();
}
