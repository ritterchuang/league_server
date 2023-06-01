package org.lsj.gs.math.core.common.logic.logicCard;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.AbstractLogic;
import org.lsj.gs.math.core.common.logic.ILogic;
import org.lsj.gs.math.core.common.logic.ILogicCard;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultCard;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.IGameBetLogResultCtrCard;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.ITableBase;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

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
