package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.module;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.resultPgrUtil.ResultPgrUtil;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.enity.EliminateResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.ScreenResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.SymbolBox;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.eliminateCtr.enity.result.EliminateCtrResult;

import java.util.ArrayList;
import java.util.List;

// 消除結果包裝者
public class EliminateResultPgr {
    private final ResultPgrUtil resultPgrUtil; // 結果包裝者工具包
    private final ITableUtil tableUtil; // 牌桌工具包

    public EliminateResultPgr(ResultPgrUtil resultPgrUtil, ITableUtil tableUtil) {
        this.resultPgrUtil = resultPgrUtil;
        this.tableUtil = tableUtil;
    }

    // 包裝消除結果
    public EliminateResult packageEliminateResult(ScreenResult screenResult, EliminateCtrResult eliminateCtrResult) {
        // 1. 計算消除位置
        List<List<Boolean>> eliminatePosition = eliminateCtrResult.getEliminatePosition();

        // 2. 取得畫面 位置ID 列表
        List<List<Integer>> screenPositionIdList = this.resultPgrUtil.calculatePositionIdList(screenResult.getScreenSymbolBox());

        // 3. 計算畫面消除位置
        List<List<Integer>> eliminatePositionIdList = this.resultPgrUtil.calculateTargetPositionId(eliminatePosition, screenPositionIdList);

        // 4. 計算畫面留存位置
        List<List<Integer>> savePositionIdList = this.calculateSavePositionIdList(screenResult.getScreenSymbolBoxWithDamp());

        // 5. 回傳
        return new EliminateResult(eliminatePositionIdList, savePositionIdList);
    }

    // 計算畫面留存位置
    private List<List<Integer>> calculateSavePositionIdList(List<List<SymbolBox>> screenSymbolBoxWithDamp) {
        // 1. 創建空間
        List<List<Integer>> result = new ArrayList<>();

        // 2. 計算含破框畫面位置ID
        List<List<Integer>> eliminatePositionIdListWithDamp = this.resultPgrUtil.calculatePositionIdList(screenSymbolBoxWithDamp);

        // 3. 倒序添加
        for (List<Integer> list : eliminatePositionIdListWithDamp) {
            List<Integer> positionIdListPerColumn = new ArrayList<>();

            for (int index = list.size() - 1; index >= 0; index--) {
                positionIdListPerColumn.add(list.get(index));
            }
            result.add(positionIdListPerColumn);
        }

        // 4. 回傳
        return result;
    }
}
