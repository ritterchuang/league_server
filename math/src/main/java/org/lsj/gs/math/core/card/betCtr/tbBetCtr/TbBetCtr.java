package org.lsj.gs.math.core.card.betCtr.tbBetCtr;

import org.lsj.gs.math.core.card.betCtr.AbstractBetCtr;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.Objects;

// 通比押注計算器
public class TbBetCtr extends AbstractBetCtr {
    final TbBetCtrConfig config; // 設定檔

    public TbBetCtr(TbBetCtrConfig config, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(config, gamePlayerHlr, poolCtr, tableUtil);
        this.config = config;
    }


    // 產出閒家押注列表
    public void generateCanBetRateListMap() {
        this.gamePlayerHlr.getAllGamePlayerMap().keySet().forEach(
                chairIndex -> super.canBetRateListMap.put(chairIndex, this.config.getBetList()));
    }

    // 取得真人閒家倍數列表
    public int[] getHumanCanBetRateArray() {
        return super.canBetRateListMap.get(super.gamePlayerHlr.getHumanChairIndex()).stream().mapToInt(canBetRate -> canBetRate).toArray();
    }


    //* 結束下注相關 *//

    // 檢查是否結束下注狀態
    public boolean isFinishBet() {
        return (this.gamePlayerHlr.getAllGamePlayerMap().keySet().stream().filter(chairId ->
                (!Objects.isNull(super.betRateMap.get(chairId)))).count() == (this.gamePlayerHlr.getPlayerCount()));
    }

    // 結束下注
    public void finishBet() {
        // 1. 非莊家且沒押注的玩家，則選擇最低倍數
        super.gamePlayerHlr.getAllGamePlayerMap().keySet().stream().filter(chairId -> Objects.isNull(super.betRateMap.get(chairId))).forEach(
                entry -> super.timeOutBetRateMap.put(entry, super.canBetRateListMap.get(entry).get(0))
        );

        // 2. 設定閒家倍數
        super.betRateMap.putAll(super.timeOutBetRateMap);
    }
}
