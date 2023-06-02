package org.lsj.gs.math.games.lznn_java.module.logic;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigLznnJava;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.AbstractNiuStack;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.IGameBetLogResultCtrCard;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.games.lznn_java.TableLznnJava;
import org.lsj.gs.math.games.lznn_java.entity.NiuStackLznnJava;
import org.lsj.gs.math.games.lznn_java.module.niu.NiuStackTmrLznnJava;

// 新賴子牛牛邏輯
public class LogicLzNnJava extends AbstractLogicLzNn implements ILogicLznnJava {
    private final NiuStackTmrLznnJava niuStackTmr; // 牛牛牌型轉換器

    public LogicLzNnJava(TableLznnJava table,
                         TableFieldConfig tableFieldConfig,
                         TableGameConfigLznnJava tableGameConfig,
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
        this.niuStackTmr = new NiuStackTmrLznnJava(tableGameConfig.getNiuStackTmrConfigLznnJava().getNiuTypeTransformMap()); // 牛型轉換器
    }

    // 取得所有玩家牌型
    @Override
    public int[] getAllPlayerSelectTypeArray() { return this.niuStackTmr.transformNiuTypeArray(super.getAllPlayerSelectTypeArrayCommon()); }

    // 取得指定位置的選牌牌型
    @Override
    public int getPlayerSelectType(int chairIndex) { return this.niuStackTmr.transformNiuType(super.getPlayerSelectTypeCommon(chairIndex)); }

    // 取得真人選牌結果
    @Override
    public NiuStackLznnJava getHumanPlayerSelectResult() { return this.niuStackTmr.transformNiuStack(super.getHumanPlayerSelectResultCommon()); }

    // 取得真人選牌牌型
    @Override
    public int getHumanPlayerSelectType() { return this.niuStackTmr.transformNiuType(super.getHumanPlayerSelectTypeCommon()); }

    @Override
    public NiuStackLznnJava getPlayerSelectResult(int chairIndex) {
        return this.niuStackTmr.transformNiuStack((AbstractNiuStack) super.lzNiuStackCtr.getAllPlayerSelectResult().get(chairIndex));
    }
}