package com.lx.gs.math.games.cjnn_java.module.logic;

import com.lx.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import com.lx.gs.math.config.entity.tableGameConfig.TableGameConfigCjnnJava;
import com.lx.gs.math.core.baiRen.ConstMathNiu;
import com.lx.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import com.lx.gs.math.core.common.logic.gameBetLogResultCtr.module.IGameBetLogResultCtrBaiRen;
import com.lx.gs.math.core.common.logic.logicBaiRen.AbstractLogicBaiRenNiu;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.games.cjnn_java.TableCjnnJava;
import com.lx.gs.math.games.cjnn_java.module.niu.NiuStackTmrCjnnJava;

// 新超級牛牛邏輯
public class LogicCjnnJava extends AbstractLogicBaiRenNiu implements ILogicCjnnJava {
    private final NiuStackTmrCjnnJava niuStackTmr; // 牛牛牌型轉換器

    public LogicCjnnJava(TableCjnnJava table,
                         TableFieldConfig tableFieldConfig,
                         TableGameConfigCjnnJava tableGameConfig,
                         GamePlayerHlr gamePlayerHlr,
                         PoolCtr poolCtr,
                         IGameBetLogResultCtrBaiRen gameBetLogResultCtr,
                         ITableUtil tableUtil) {
        // 1. 初始化
        super(  table,
                tableFieldConfig,
                gamePlayerHlr,
                poolCtr,
                gameBetLogResultCtr,
                tableUtil,
                table.getTableConfigMgr().getModuleConfigMgrBaiRen().createBetAreaCtrConfig(
                        tableGameConfig.getBetAreaCtrGameConfig().getChipsOddsList(),
                        tableGameConfig.getBetAreaCtrGameConfig().getAreasTopLimitOddsMap(),
                        tableGameConfig.getBetAreaCtrGameConfig().getCantBetTogetherArray(),
                        ConstMathNiu.BetAreaEnum.getBetAreaCount()
                ),
                table.getTableConfigMgr().getModuleConfigMgrCard().createCardWallCtrConfig(
                        tableGameConfig.getCardWallCtrGameConfig().getCardType(),
                        tableGameConfig.getCardWallCtrGameConfig().getInitWallList(),
                        tableGameConfig.getCardWallCtrGameConfig().getCardValueWeightMap()
                ),
                table.getTableConfigMgr().getModuleConfigMgrBaiRen().createNiuBaiRenStackCtrConfig(tableGameConfig),
                table.getTableConfigMgr().getModuleConfigMgrBaiRen().createCjnnBaiRenResultCtrConfig(
                        ConstMathNiu.BetAreaEnum.getBetAreaCount(),
                        tableGameConfig
                )
        );

        this.niuStackTmr = new NiuStackTmrCjnnJava(tableGameConfig.getNiuStackTmrConfigCjnnJava().getNiuTypeTransformMap());
    }

    // 取得牛牛牌型陣列
    public int[] getAreaStackArray(){
        return this.niuStackTmr.transformNiuTypeArray(super.stackCtr.getAreaNiuTypeCommonArray());
    }

    // 重設
    @Override
    public void reset(){
        super.reset();
    }
}