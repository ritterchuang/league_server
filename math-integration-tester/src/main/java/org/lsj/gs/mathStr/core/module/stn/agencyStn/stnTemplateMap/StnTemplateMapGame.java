package org.lsj.gs.mathStr.core.module.stn.agencyStn.stnTemplateMap;

import org.lsj.gs.mathStr.config.entity.AgencyStnConfig;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrReturnResult;
import org.lsj.gs.mathStr.core.module.stn.TemplateStn;

import java.util.HashMap;
import java.util.Map;

// 遊戲統記者
public class StnTemplateMapGame extends AbstractStnTemplateMap {
    private final Map<Integer, TemplateStn> stnGameMap; // 各遊戲統計 <遊戲索引, 基礎統計物件>

    public StnTemplateMapGame(AgencyStnConfig config) {
        super(config);
        this.stnGameMap = new HashMap<>();
    }

    // 更新統計物件
    @Override
    public void update(BoardGtrResult boardGtrResult) {
        // 1. 取得統計物件
        TemplateStn stn = super.getStn(stnGameMap, boardGtrResult.getStnResult().getGameIndex());

        // 2. 更新統計物件
        stn.update(boardGtrResult);

        // 3. 更新各統計
        this.stnGameMap.put(boardGtrResult.getStnResult().getGameIndex(), stn);
    }

    // 更新返還資訊
    @Override
    public void updateReturnResult(BoardGtrReturnResult boardGtrReturnResult) {
        // 1. 取得統計物件
        TemplateStn stn = super.getStn(stnGameMap, boardGtrReturnResult.getStnResult().getGameIndex());

        // 2. 更新統計物件
        stn.updateReturnResult(boardGtrReturnResult);

        // 3. 更新各統計
        this.stnGameMap.put(boardGtrReturnResult.getStnResult().getGameIndex(), stn);
    }

    // 印出統計資訊
    @Override
    public void print() {
        this.stnGameMap.forEach((stnIndex, stn) -> super.printTitleStn("遊戲資訊", stnIndex, stn));
    }
}
