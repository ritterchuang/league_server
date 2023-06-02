package org.lsj.gs.mathStr.core.module.stn.agencyStn.stnTemplateMap;

import org.lsj.gs.mathStr.config.entity.AgencyStnConfig;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrReturnResult;
import org.lsj.gs.mathStr.core.module.stn.TemplateStn;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

// 玩家統計者
public class StnTemplateMapPlayer extends AbstractStnTemplateMap {
    private final Map<Integer, TemplateStn> stnPlayerMap; // 各玩家統計 <玩家索引, 基礎統計物件>

    public StnTemplateMapPlayer(AgencyStnConfig config) {
        super(config);
        this.stnPlayerMap = new HashMap<>();
    }

    // 更新統計物件
    @Override
    public void update(BoardGtrResult boardGtrResult) {
        // 1. 取得統計物件
        TemplateStn stnPlayer = super.getStn(stnPlayerMap, boardGtrResult.getStnResult().getPlayerIndex());

        // 2. 更新統計物件
        stnPlayer.update(boardGtrResult);

        // 3. 更新各統計
        this.stnPlayerMap.put(boardGtrResult.getStnResult().getPlayerIndex(), stnPlayer);
    }

    // 更新返還資訊
    @Override
    public void updateReturnResult(BoardGtrReturnResult boardGtrReturnResult) {
        // 1. 取得統計物件
        TemplateStn stnPlayer = super.getStn(stnPlayerMap, boardGtrReturnResult.getStnResult().getPlayerIndex());

        // 2. 更新統計物件
        stnPlayer.updateReturnResult(boardGtrReturnResult);

        // 3. 更新各統計
        this.stnPlayerMap.put(boardGtrReturnResult.getStnResult().getPlayerIndex(), stnPlayer);
    }

    // 印出統計資訊
    @Override
    public void print() {
        // 1. 計算打印資訊
        long totalCount = this.stnPlayerMap.size();
        long winnerCount = this.stnPlayerMap.entrySet().stream().filter(entry -> entry.getValue().getTotalScore() > 0).count();
        long loserCount = this.stnPlayerMap.entrySet().stream().filter(entry -> entry.getValue().getTotalScore() < 0).count();
        long tieCount = totalCount - winnerCount - loserCount;

        // 2. 打印資訊
        System.out.println("[玩家資訊]");
        System.out.println("總玩家數: " + totalCount);
        System.out.println("總贏家數: " + winnerCount);
        System.out.println("總輸家數: " + loserCount);
        System.out.println("總和家數: " + tieCount);
        System.out.println("贏家率: " + new DecimalFormat("#.##").format((double)winnerCount / (double)totalCount * 100.00) + "%");
        System.out.println("輸家率: " + new DecimalFormat("#.##").format((double)loserCount / (double)totalCount * 100.00) + "%");
        System.out.println("和家率: " + new DecimalFormat("#.##").format((double)tieCount / (double)totalCount * 100.00) + "%");
    }
}
