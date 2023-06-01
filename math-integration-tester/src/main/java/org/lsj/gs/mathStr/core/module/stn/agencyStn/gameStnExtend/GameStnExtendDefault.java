package org.lsj.gs.mathStr.core.module.stn.agencyStn.gameStnExtend;

import org.lsj.gs.mathStr.config.entity.AgencyStnConfig;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrReturnResult;

// 預設客製遊戲統計者
public class GameStnExtendDefault extends GameStnExtend {
    public GameStnExtendDefault(AgencyStnConfig config) {
        super(config);
    }

    // 更新統計資訊
    @Override
    public void update(BoardGtrResult boardGtrResult) {}

    @Override
    public void updateReturnResult(BoardGtrReturnResult boardGtrReturnResult) { }

    // 印出統計資訊
    @Override
    public void print() {}
}
