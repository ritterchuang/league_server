package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.eliminateCtr.enity.result.EliminateCtrResult;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.DampCtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.ReelStopResultExtendStopByScreen;
import org.lsj.utils.ListUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// 消除處理者 超級王牌 工具包
public class CascadeHlrCjwpUtil {
    private final SymbolConfig symbolConfig; // 標誌設定
    private final ITableUtil tableUtil; // 牌桌工具包

    public CascadeHlrCjwpUtil(SymbolConfig symbolConfig, ITableUtil tableUtil) {
        this.symbolConfig = symbolConfig;
        this.tableUtil = tableUtil;
    }

    //* 計算畫面 *//

    // 產出畫面生產結果，並依照可視範圍畫面修改
    public void updateScreenGtrResultWithLastCascadeViewScreen(ScreenGtrResult screenGtrResult, List<List<Integer>> viewScreenAfterLastCascade) {
        // 1. 取得待修改資訊
        List<List<Integer>> screenGtrResultSymbol = screenGtrResult.getScreenSymbol();
        List<List<Integer>> reelExtendResultSymbol = ((ReelStopResultExtendStopByScreen)screenGtrResult.getReelCtrResult().getReelStopResultExtend()).getScreenSymbol();

        // 2. 依照可視範圍畫面修改
        for (int columnIndex = 0; columnIndex < viewScreenAfterLastCascade.size(); columnIndex++) {
            for (int rowIndex = 0; rowIndex < viewScreenAfterLastCascade.get(columnIndex).size(); rowIndex++) {
                int targetSymbolId = viewScreenAfterLastCascade.get(columnIndex).get(rowIndex);
                if (this.symbolConfig.isWildSymbol(targetSymbolId)) {
                    screenGtrResultSymbol.get(columnIndex).set(rowIndex, targetSymbolId);
                    reelExtendResultSymbol.get(columnIndex).set(rowIndex, targetSymbolId);
                }
            }
        }
    }

    // 依照random wild列表修改畫面標誌
    public void updateScreenGtrResultWithRandomWild(ScreenGtrResult screenGtrResult, Map<Integer, Map<Integer,List<List<Boolean>>>> bigWildTransformPosition, int bigWildId) {
        // 1. 取得待修改資訊
        List<List<Integer>> screenGtrResultSymbol = screenGtrResult.getScreenSymbol();
        List<List<Integer>> reelExtendResultSymbol = ((ReelStopResultExtendStopByScreen)screenGtrResult.getReelCtrResult().getReelStopResultExtend()).getScreenSymbol();

        // 2. 針對每一個 Big Wild, 依照可視範圍做 Random Wild 的畫面修改
        bigWildTransformPosition.forEach((columnId, bigWildColumnPosition) ->
            bigWildColumnPosition.forEach((rowId, bigWildRowPosition) -> {
                for (int columnIndex = 0; columnIndex < bigWildRowPosition.size(); columnIndex++) {
                    for (int rowIndex = 0; rowIndex < bigWildRowPosition.get(columnIndex).size(); rowIndex++) {
                        if (bigWildRowPosition.get(columnIndex).get(rowIndex)) {
                            screenGtrResultSymbol.get(columnIndex).set(rowIndex, bigWildId);
                            reelExtendResultSymbol.get(columnIndex).set(rowIndex, bigWildId);
                        }
                    }
                }
        }));
    }

    //* 計算黃金位置 *//

    // 聯集上次消除後黃金位置 與 此次新增畫面黃金位置
    public List<List<Boolean>> unionGoldenPositionAddSymbolAndGoldPositionAfterCascadeLastTime(List<List<Boolean>> goldenPositionForAddSymbolList, List<List<Boolean>> goldPositionAfterCascadeLastTime) {
        List<List<Boolean>> result = ListUtil.copy2DimensionBooleanList(goldPositionAfterCascadeLastTime);

        for (int columnIndex = 0; columnIndex < result.size(); columnIndex++) {
            for (int addSymbolIndex = 0; addSymbolIndex < goldenPositionForAddSymbolList.get(columnIndex).size(); addSymbolIndex++) {
                result.get(columnIndex).set(addSymbolIndex, goldenPositionForAddSymbolList.get(columnIndex).get(addSymbolIndex));
            }
        }

        return result;
    }


    //* 計算轉變 Wild 位置 *//

    // 計算整欄那些位置為 wild
    public List<Boolean> calculateChangeWildPerColumn(List<Boolean> hitPositionWithDampPerCol, List<Boolean> goldenPositionPerCol) {
        List<Boolean> result = new ArrayList<>();

        for (int rowIndex = 0; rowIndex < hitPositionWithDampPerCol.size(); rowIndex++) {
            // 消除前為金 && 有得分 => 則為 wild
            boolean isWild = hitPositionWithDampPerCol.get(rowIndex) && goldenPositionPerCol.get(rowIndex);
            result.add(isWild);
        }

        return result;
    }


    //* 計算消除位置 *//

    // 修改消除位置含破框
    public void adjustEliminatePositionWithDamp(EliminateCtrResult eliminateCtrResult, List<List<Boolean>> changeToWildPosition) {
        for (int columnIndex = 0; columnIndex < changeToWildPosition.size(); columnIndex++) {
            for (int rowIndex = 0; rowIndex < changeToWildPosition.get(columnIndex).size(); rowIndex++) {
                if (eliminateCtrResult.getEliminatePositionDamp().get(columnIndex).get(rowIndex)
                        && changeToWildPosition.get(columnIndex).get(rowIndex)) {
                    eliminateCtrResult.getEliminatePositionDamp().get(columnIndex).set(rowIndex, false);
                }
            }
        }
    }

    // 修改消除位置
    public void adjustEliminatePositionWithScore(EliminateCtrResult eliminateCtrResult, DampCtrResult dampCtrResult) {
        // 1. 複製消除位置含破框
        List<List<Boolean>> eliminatePositionDampCopy = ListUtil.copy2DimensionBooleanList(eliminateCtrResult.getEliminatePositionDamp());

        // 2. 依照破框結果，移出消除位置含破框的 破框
        for (int columnIndex = 0; columnIndex < eliminatePositionDampCopy.size(); columnIndex++) {
            if (!dampCtrResult.getUpperDampSymbolIdList().isEmpty()) {
                for (int upperDampIndex = 0; upperDampIndex < dampCtrResult.getUpperDampSymbolIdList().get(columnIndex).size(); upperDampIndex++) {
                    eliminatePositionDampCopy.get(columnIndex).remove(0);
                }
            }

            if (!dampCtrResult.getLowerDampSymbolIdList().isEmpty()) {
                for (int lowerDampIndex = 0; lowerDampIndex < dampCtrResult.getLowerDampSymbolIdList().get(columnIndex).size(); lowerDampIndex++) {
                    eliminatePositionDampCopy.get(columnIndex).remove(eliminatePositionDampCopy.get(columnIndex).size() - 1);
                }
            }
        }

        // 3. 修改消除位置
        for (int columnIndex = 0; columnIndex < eliminatePositionDampCopy.size(); columnIndex++) {
            for (int rowIndex = 0; rowIndex < eliminatePositionDampCopy.get(columnIndex).size(); rowIndex++) {
                eliminateCtrResult.getEliminatePosition().get(columnIndex).set(rowIndex, eliminatePositionDampCopy.get(columnIndex).get(rowIndex));
            }
        }
    }
}
