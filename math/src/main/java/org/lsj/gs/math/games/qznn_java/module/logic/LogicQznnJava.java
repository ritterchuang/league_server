package org.lsj.gs.math.games.qznn_java.module.logic;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigQznnJava;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.IGameBetLogResultCtrCard;
import org.lsj.gs.math.core.common.logic.logicCard.AbstractLogicQzNn;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.games.qznn_java.TableQznnJava;
import org.lsj.gs.math.games.qznn_java.entity.NiuStackQznnJava;
import org.lsj.gs.math.games.qznn_java.module.niu.NiuStackTmrQznnJava;

// 搶莊牛牛邏輯
public class LogicQznnJava extends AbstractLogicQzNn implements ILogicQznnJava {
    private final NiuStackTmrQznnJava niuStackTmr; // 牛牛牌型轉換器

    public LogicQznnJava(TableQznnJava table,
                         TableFieldConfig tableFieldConfig,
                         TableGameConfigQznnJava tableGameConfig,
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
                table.getTableConfigMgr().getModuleConfigMgrCard().createNiuStackCtrConfig(
                        tableGameConfig.getNiuStackCtrGameConfig().getNiuTypeRateConfig()),
                table.getTableConfigMgr().getModuleConfigMgrCard().createNiuBankerResultCtrConfig(
                        tableGameConfig.getNiuResultCtrGameConfig().getNiuTypeRateConfig())
        );

        // 2. 模組初始化
        this.niuStackTmr = new NiuStackTmrQznnJava(tableGameConfig.getNiuStackTmrConfigQznnJava().getNiuTypeTransformMap()); // 牛型轉換器
    }

    // 取得所有玩家牌型
    @Override
    public int[] getAllPlayerSelectTypeArray() { return this.niuStackTmr.transformNiuTypeArray(super.getAllPlayerSelectTypeArrayCommon()); }

    // 取得指定位置的選牌牌型
    @Override
    public int getPlayerSelectType(int chairIndex) { return this.niuStackTmr.transformNiuType(super.getPlayerSelectTypeCommon(chairIndex)); }

    // 取得真人選牌結果
    @Override
    public NiuStackQznnJava getHumanPlayerSelectResult() { return this.niuStackTmr.transformNiuStack(super.getHumanPlayerSelectResultCommon()); }

    // 取得真人選牌牌型
    @Override
    public int getHumanPlayerSelectType() { return this.niuStackTmr.transformNiuType(super.getHumanPlayerSelectTypeCommon()); }
}