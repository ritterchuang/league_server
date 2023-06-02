package org.lsj.gs.math.games.mybjl_java.module.logic;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigMybjlJava;
import org.lsj.gs.math.core.baiRen.ConstMathBjl;
import org.lsj.gs.math.core.card.resultCtr.bjlResultCtr.AbstractBjlBaiRenResultCtr;
import org.lsj.gs.math.core.card.resultCtr.bjlResultCtr.BjlBaiRenResultCtr;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.IGameBetLogResultCtrBaiRen;
import org.lsj.gs.math.core.common.logic.logicBaiRen.AbstractLogicBaiRenBjl;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.games.mybjl_java.TableMybjlJava;

// 新免傭百家樂邏輯
public class LogicMybjlJava extends AbstractLogicBaiRenBjl implements ILogicMybjlJava {
    private final BjlBaiRenResultCtr resultCtr; // 結果計算器

    public LogicMybjlJava(TableMybjlJava table,
                          TableFieldConfig tableFieldConfig,
                          TableGameConfigMybjlJava tableGameConfig,
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
                        ConstMathBjl.BetAreaEnum.getBetAreaCount()
                ),
                table.getTableConfigMgr().getModuleConfigMgrCard().createCardWallCtrConfig(
                        tableGameConfig.getCardWallCtrGameConfig().getCardType(),
                        tableGameConfig.getCardWallCtrGameConfig().getInitWallList(),
                        tableGameConfig.getCardWallCtrGameConfig().getCardValueWeightMap()
                )
        );

        this.resultCtr = new BjlBaiRenResultCtr(
                table.getTableConfigMgr().getModuleConfigMgrBaiRen().createBjlBaiRenResultCtrConfig(
                        ConstMathBjl.BetAreaEnum.getBetAreaCount(),
                        tableFieldConfig.getFeeType(),
                        tableFieldConfig.getGameRate(),
                        tableGameConfig.getResultCtrGameConfig().getBetAreaIdToRateMap()
                ),
                gamePlayerHlr,
                poolCtr,
                tableUtil);
    }

    // 取得結果計算器
    @Override
    public AbstractBjlBaiRenResultCtr getAbstractBjlBaiRenResultCtr() {
        return this.resultCtr;
    }

    // 重設
    @Override
    public void reset(){
        super.reset();
        this.resultCtr.reset();
    }
}