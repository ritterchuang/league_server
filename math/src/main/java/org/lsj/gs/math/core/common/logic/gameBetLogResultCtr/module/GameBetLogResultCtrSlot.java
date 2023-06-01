package org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module;

import org.lsj.db.GameBetLogObj;
import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gameFlowHlr.enity.result.GameFlowHlrResult;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.GameBetLogResultSlot;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultSlot;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.ArrayList;

// 下注記錄計算器老虎機
public class GameBetLogResultCtrSlot extends AbstractGameBetLogResultCtr implements IGameBetLogResultCtrSlot {
    public GameBetLogResultCtrSlot(long beginTime,int tableId, TableFieldConfig config, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(beginTime, tableId, config, gamePlayerHlr, poolCtr, tableUtil);
    }

    // 計算遊戲下注紀錄結
    @Override
    public IGameBetLogResultSlot calculateGameBetLogResultSlot(String roundId, UidScore uidScore, GameFlowHlrResult gameFlowHlrResult) {
        // 1. 計算下注記錄物件
        GameBetLogObj gameBetLogObj = super.calculateGameBetLogObj(roundId, uidScore);

        // 2. 封裝
        return new GameBetLogResultSlot(
                gameBetLogObj,
                new ArrayList<>(this.detail), super.poolCtr.getPersonControlLogString(), super.tableUtil.getMainRandomUtil().getRandomNumberUsedList(),
                gameFlowHlrResult
        );
    }
}
