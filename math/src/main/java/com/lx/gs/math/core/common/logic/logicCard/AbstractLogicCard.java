package com.lx.gs.math.core.common.logic.logicCard;

import com.lx.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import com.lx.gs.math.core.common.gameAdjust.entity.UidScore;
import com.lx.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import com.lx.gs.math.core.common.logic.AbstractLogic;
import com.lx.gs.math.core.common.logic.ILogic;
import com.lx.gs.math.core.common.logic.ILogicCard;
import com.lx.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultCard;
import com.lx.gs.math.core.common.logic.gameBetLogResultCtr.module.IGameBetLogResultCtrCard;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;
import com.lx.gs.math.core.common.table.module.ITableBase;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 抽象邏輯卡牌
public abstract class AbstractLogicCard extends AbstractLogic implements ILogicCard, ILogic {
    protected final IGameBetLogResultCtrCard gameBetLogResultCtrCard; // 遊戲投注紀錄計算器棋牌

    public AbstractLogicCard(ITableBase table,
                             TableFieldConfig tableFieldConfig,
                             GamePlayerHlr gamePlayerHlr,
                             PoolCtr poolCtr,
                             IGameBetLogResultCtrCard gameBetLogResultCtrCard,
                             ITableUtil tableUtil){
        super(table, tableFieldConfig, gamePlayerHlr, poolCtr, gameBetLogResultCtrCard, tableUtil);
        this.gameBetLogResultCtrCard = gameBetLogResultCtrCard;
    }

    // 計算下注紀錄結果
    @Override
    public IGameBetLogResultCard getGameBetLogResultCard(UidScore uidScore) {
        return this.gameBetLogResultCtrCard.calculateGameBetLogResultCard(
                super.table.getRoundId(), uidScore);
    }
}
