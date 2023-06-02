package org.lsj.gs.math.core.card.betCtr.qzBetCtr;

import org.lsj.gs.math.core.card.betCtr.AbstractBetCtr;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.Objects;

// 搶莊押注計算器
public class QzBetCtr extends AbstractBetCtr {
    final QzBetCtrConfig config; // 設定檔
    final IQzBetUtil betUtil; // 下注工具包

    public QzBetCtr(QzBetCtrConfig config, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(config, gamePlayerHlr, poolCtr, tableUtil);
        this.config = config;
        this.betUtil = this.create(config);
        this.reset();
    }


    //* 初始化 *//

    // 初始化下注工具包
    private IQzBetUtil create(QzBetCtrConfig config) {
        switch (config.getBetType()) {
            case BET_01: return new QzBetUtilBetType01();
            default: return new QzBetUtilBetType02();
        }
    }


    //* 可下注倍數相關 *//

    // 產出閒家押注列表
    public void generateCanBetRateListMap(int bankerChair, int bankerRate) {
        this.gamePlayerHlr.getAllGamePlayerMap().keySet().stream().filter(chairIndex -> chairIndex != bankerChair).forEach(
                chairIndex -> this.canBetRateListMap.put(chairIndex,
                        this.betUtil.calculateCanBetRateListByPlayer(super.gamePlayerHlr, this.config, bankerChair, chairIndex, bankerRate)));
    }

    // 取得真人閒家倍數列表
    public int[] getHumanCanBetRateArray(int bankerChairIndex) {
        // 1. 真人為莊回傳空，或尚未搶莊
        if (bankerChairIndex == super.gamePlayerHlr.getHumanChairIndex() || bankerChairIndex == -1) {
            return new int[]{};
        }

        // 2. 未初始化，防呆
        if (this.canBetRateListMap.containsKey(super.gamePlayerHlr.getHumanChairIndex()) == false) {
            return new int[]{};
        }

        // 3. 取得真人倍數列表
        return this.canBetRateListMap.get(super.gamePlayerHlr.getHumanChairIndex()).stream()
                .mapToInt(canBetRate -> canBetRate)
                .toArray();
    }


    //* 結束下注相關 *//

    // 檢查是否結束下注狀態
    public boolean isFinishBet(int bankerChairIndex) {
        return (this.gamePlayerHlr.getAllGamePlayerMap().keySet().stream().filter(chairIndex ->
                ((chairIndex != bankerChairIndex) && (!Objects.isNull(this.betRateMap.get(chairIndex))))).count() ==
                (this.gamePlayerHlr.getPlayerCount() - 1));
    }

    // 結束下注
    public void finishBet(int bankerChairIndex) {
        // 1. 非莊家且沒押注的玩家，則選擇最低倍數
        super.gamePlayerHlr.getAllGamePlayerMap().keySet().stream().filter(chairIndex -> Objects.isNull(this.betRateMap.get(chairIndex)) && chairIndex != bankerChairIndex).forEach(
                entry -> this.timeOutBetRateMap.put(entry, this.canBetRateListMap.get(entry).get(0))
        );

        // 2. 設定閒家倍數
        this.betRateMap.putAll(this.timeOutBetRateMap);
    }
}
