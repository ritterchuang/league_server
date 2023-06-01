package com.lx.gs.mathStr.core.module.stn.agencyStn.gameStnExtend;

import com.lx.gs.mathStr.config.entity.AgencyStnConfig;
import com.lx.gs.mathStr.core.entity.playStr.BoardGtrResult;
import com.lx.gs.mathStr.core.module.stn.agencyStn.AbstractStn;

// 客製遊戲統計者父類別
public abstract class GameStnExtend extends AbstractStn {
    public GameStnExtend(AgencyStnConfig config) {
        super(config);
    }

    // 更新統計資訊
    @Override
    public abstract void update(BoardGtrResult boardGtrResult);

    // 印出統計資訊
    @Override
    public abstract void print();
}
