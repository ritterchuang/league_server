package org.lsj.gs.math.games.tbnn_java.module.logic;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigTbnnJava;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.IGameBetLogResultCtrCard;
import org.lsj.gs.math.core.common.logic.logicCard.AbstractLogicTbNn;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.games.tbnn_java.TableTbnnJava;
import org.lsj.gs.math.games.tbnn_java.entity.NiuStackTbnnJava;
import org.lsj.gs.math.games.tbnn_java.module.niu.NiuStackTmrTbnnJava;

// 通比牛牛邏輯
public class LogicTbnnJava extends AbstractLogicTbNn implements ILogicTbnnJava {
    private final NiuStackTmrTbnnJava niuStackTmr; // 牛牛牌型轉換器

    public LogicTbnnJava(TableTbnnJava table,
                         TableFieldConfig tableFieldConfig,
                         TableGameConfigTbnnJava tableGameConfig,
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
                table.getTableConfigMgr().getModuleConfigMgrCard().createTbBetCtrConfig(tableGameConfig.getTbBetCtrGameConfig()),
                table.getTableConfigMgr().getModuleConfigMgrCard().createCardWallCtrConfig(
                        tableGameConfig.getCardWallCtrGameConfig().getCardType(),
                        tableGameConfig.getCardWallCtrGameConfig().getInitWallList(),
                        tableGameConfig.getCardWallCtrGameConfig().getCardValueWeightMap()),
                table.getTableConfigMgr().getModuleConfigMgrCard().createNiuStackCtrConfig(
                        tableGameConfig.getNiuStackCtrGameConfig().getNiuTypeRateConfig()),
                table.getTableConfigMgr().getModuleConfigMgrCard().createNiuTonbiResultCtrConfig(
                        tableGameConfig.getNiuTonbiResultCtrGameConfig().getNiuTypeRateConfig())
        );

        // 2. 模組初始化
        this.niuStackTmr = new NiuStackTmrTbnnJava(tableGameConfig.getNiuStackTmrConfigTbnnJava().getNiuTypeTransformMap()); // 牛型轉換器
    }

    // 取得所有玩家牌型
    @Override
    public int[] getAllPlayerSelectTypeArray() { return this.niuStackTmr.transformNiuTypeArray(super.getAllPlayerSelectTypeArrayCommon()); }

    // 取得指定位置的選牌牌型
    @Override
    public int getPlayerSelectType(int chairIndex) { return this.niuStackTmr.transformNiuType(super.getPlayerSelectTypeCommon(chairIndex)); }

    // 取得真人選牌結果
    @Override
    public NiuStackTbnnJava getHumanPlayerSelectResult() { return this.niuStackTmr.transformNiuStack(super.getHumanPlayerSelectResultCommon()); }

    // 取得真人選牌牌型
    @Override
    public int getHumanPlayerSelectType() { return this.niuStackTmr.transformNiuType(super.getHumanPlayerSelectTypeCommon()); }
}