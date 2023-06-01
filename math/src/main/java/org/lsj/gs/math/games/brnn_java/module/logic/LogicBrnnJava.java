package org.lsj.gs.math.games.brnn_java.module.logic;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigBrnnJava;
import org.lsj.gs.math.core.baiRen.ConstMathNiu;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.IGameBetLogResultCtrBaiRen;
import org.lsj.gs.math.core.common.logic.logicBaiRen.AbstractLogicBaiRenNiu;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.games.brnn_java.TableBrnnJava;
import org.lsj.gs.math.games.brnn_java.module.niu.NiuStackTmrBrnnJava;

// 新百人牛牛邏輯
public class LogicBrnnJava extends AbstractLogicBaiRenNiu implements ILogicBrnnJava {
    private final NiuStackTmrBrnnJava niuStackTmr; // 牛牛牌型轉換器

    public LogicBrnnJava(TableBrnnJava table,
                         TableFieldConfig tableFieldConfig,
                         TableGameConfigBrnnJava tableGameConfig,
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
                table.getTableConfigMgr().getModuleConfigMgrBaiRen().createBrnnBaiRenResultCtrConfig(
                        ConstMathNiu.BetAreaEnum.getBetAreaCount(),
                        tableGameConfig
                )
        );

        this.niuStackTmr = new NiuStackTmrBrnnJava(tableGameConfig.getNiuStackTmrConfigBrnnJava().getNiuTypeTransformMap());
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