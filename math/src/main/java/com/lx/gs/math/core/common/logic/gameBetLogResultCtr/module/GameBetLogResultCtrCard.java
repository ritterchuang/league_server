package com.lx.gs.math.core.common.logic.gameBetLogResultCtr.module;

import com.lx.db.GameBetLogObj;
import com.lx.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import com.lx.gs.math.core.common.gameAdjust.entity.UidScore;
import com.lx.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import com.lx.gs.math.core.common.logic.gameBetLogResultCtr.enity.GameBetLogResultCard;
import com.lx.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultCard;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.ArrayList;

// 下注記錄計算器棋牌
public class GameBetLogResultCtrCard extends AbstractGameBetLogResultCtr implements IGameBetLogResultCtrCard {
    public GameBetLogResultCtrCard(long beginTime, int tableId, TableFieldConfig config, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(beginTime, tableId, config, gamePlayerHlr, poolCtr, tableUtil);
    }


    // 計算下注紀錄結果
    public IGameBetLogResultCard calculateGameBetLogResultCard(String roundId, UidScore uidScore) {
        // 1. 計算下注記錄物件
        GameBetLogObj gameBetLogObj = super.calculateGameBetLogObj(roundId, uidScore);

        // 2. 封裝
        return new GameBetLogResultCard(gameBetLogObj, new ArrayList<>(this.detail), super.poolCtr.getPersonControlLogString(), super.tableUtil.getMainRandomUtil().getRandomNumberUsedList());
    }

}
