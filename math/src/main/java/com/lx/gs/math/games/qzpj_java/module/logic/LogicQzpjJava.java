package com.lx.gs.math.games.qzpj_java.module.logic;

import com.lx.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import com.lx.gs.math.config.entity.tableGameConfig.TableGameConfigQzpjJava;
import com.lx.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import com.lx.gs.math.core.common.logic.gameBetLogResultCtr.module.IGameBetLogResultCtrCard;
import com.lx.gs.math.core.common.logic.logicCard.AbstractLogicQzPj;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.games.qzpj_java.TableQzpjJava;

// 搶莊牌九邏輯
public class LogicQzpjJava extends AbstractLogicQzPj implements ILogicQzpjJava{

    public LogicQzpjJava(TableQzpjJava table,
                         TableFieldConfig tableFieldConfig,
                         TableGameConfigQzpjJava tableGameConfig,
                         GamePlayerHlr gamePlayerHlr,
                         PoolCtr poolCtr,
                         IGameBetLogResultCtrCard gameBetLogResultCtrCard,
                         ITableUtil tableUtil) {
        // 1. 初始化
        super(  table,
                tableFieldConfig,
                gamePlayerHlr,
                poolCtr,
                gameBetLogResultCtrCard,
                tableUtil,
                table.getTableConfigMgr().getModuleConfigMgrCard().createVieBankerCtrConfig(
                        tableGameConfig.getVieBankerCtrGameConfig()),
                table.getTableConfigMgr().getModuleConfigMgrCard().createQzBetCtrConfig(
                        tableGameConfig.getBetCtrGameConfig()),
                table.getTableConfigMgr().getModuleConfigMgrCard().createCardWallCtrConfig(
                        tableGameConfig.getCardWallCtrGameConfig().getCardType(),
                        tableGameConfig.getCardWallCtrGameConfig().getInitWallList(),
                        tableGameConfig.getCardWallCtrGameConfig().getCardValueWeightMap()),
                table.getTableConfigMgr().getModuleConfigMgrCard().createPjResultCtrConfig(),
                table.getTableConfigMgr().getModuleConfigMgrCard().createPjStackCtrConfig()
        );
    }

    // 取得所有玩家牌型
    public int[] getAllPlayerSelectTypeArray() { return super.getAllPlayerSelectTypeArrayCommon(); }

    // 取得指定位置的選牌牌型
    public int getPlayerSelectType(int chairIndex) { return super.getPlayerSelectTypeCommon(chairIndex); }
}