package com.lx.gs.math.games.sg_java.module.logic;

import com.lx.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import com.lx.gs.math.config.entity.tableGameConfig.TableGameConfigSgJava;
import com.lx.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import com.lx.gs.math.core.common.logic.gameBetLogResultCtr.module.IGameBetLogResultCtrCard;
import com.lx.gs.math.core.common.logic.logicCard.AbstractLogicQzSg;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.games.sg_java.TableSgJava;

import java.util.ArrayList;

// 新三公邏輯
public class LogicSgJava extends AbstractLogicQzSg implements ILogicSgJava{

    public LogicSgJava(TableSgJava table,
                       TableFieldConfig config,
                       TableGameConfigSgJava tableGameConfig,
                       GamePlayerHlr gamePlayerHlr,
                       PoolCtr poolCtr,
                       IGameBetLogResultCtrCard gameBetLogResultCtrCard,
                       ITableUtil tableUtil) {
        // 1. 初始化
        super(  table,
                config,
                gamePlayerHlr,
                poolCtr,
                gameBetLogResultCtrCard,
                tableUtil,
                table.getTableConfigMgr().getModuleConfigMgrCard().createVieBankerCtrConfig(
                        tableGameConfig.getVieBankerCtrGameConfig()),
                table.getTableConfigMgr().getModuleConfigMgrCard().createQzBetCtrConfig(tableGameConfig.getBetCtrGameConfig()),
                table.getTableConfigMgr().getModuleConfigMgrCard().createCardWallCtrConfig(
                        tableGameConfig.getCardWallCtrGameConfig().getCardType(),
                        tableGameConfig.getCardWallCtrGameConfig().getInitWallList(),
                        tableGameConfig.getCardWallCtrGameConfig().getCardValueWeightMap()),
                table.getTableConfigMgr().getModuleConfigMgrCard().createSgResultCtrConfig(tableGameConfig.getSgResultCtrGameConfig().getSgTypeConfig()),
                table.getTableConfigMgr().getModuleConfigMgrCard().createSgStackCtrConfig()
                );
    }

    // 取得所有玩家牌型
    @Override
    public int[] getAllPlayerSelectTypeArray() { return super.getAllPlayerSelectTypeArrayCommon(); }

    // 取得指定位置的選牌牌型
    @Override
    public int getPlayerSelectType(int chairIndex) { return super.getPlayerSelectTypeCommon(chairIndex); }

    // 是否過早選牌
    @Override
    public boolean isEarlyPlayerSelect(int chairIndex) { return super.getHandCardListMap().getOrDefault(chairIndex, new ArrayList<>()).size() != 3;}
}