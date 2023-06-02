package org.lsj.gs.math.core.slot.screenGtrMgr.enity;

import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.eliminateCtr.enity.result.EliminateCtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.ReelCtrResult;
import org.lsj.utils.ListUtil;

import java.util.ArrayList;
import java.util.List;

// 畫面產出結果
public class ScreenGtrResult {
    private final List<List<Integer>> screenSymbol; // 畫面標誌 [column][row] = symbolId
    private final FrameResult frameResult; // 畫面結果
    private final ReelCtrResult reelCtrResult; // 輪帶表結果

    public ScreenGtrResult(List<List<Integer>> screenSymbol,
                           FrameResult frameResult,
                           ReelCtrResult reelCtrResult) {
        this.screenSymbol = screenSymbol;
        this.frameResult = frameResult;
        this.reelCtrResult = reelCtrResult;
    }

    // 計算消除後畫面 [還需要 damp 一起做]
    public List<List<Integer>> calculateScreenSymbolAfterCascade(EliminateCtrResult eliminateCtrResult) {
        // 1. 取得資料
        List<List<Integer>> result = ListUtil.copy2DimensionIntegerList(this.screenSymbol);
        List<List<Boolean>> eliminatePosition = eliminateCtrResult.getEliminatePosition();

        // 2. 修改消除位置標誌
        for (int columnIndex = 0; columnIndex < eliminatePosition.size(); columnIndex++) {
            for (int rowIndex = 0; rowIndex < eliminatePosition.get(columnIndex).size(); rowIndex++) {
                if (eliminatePosition.get(columnIndex).get(rowIndex) == true) {
                    result.get(columnIndex).set(rowIndex, -1);
                }
            }
        }

        // 3. 將 -1 位置往上移
        for (int index = 0; index < result.size(); index++) {
            result.set(index, ListUtil.transNegativeNumberToArrayFirst(result.get(index)));
        }
        
        // 4. 回傳
        return result;
    }

    // 計算包含破框畫面
    public List<List<Integer>> calculateScreenSymbolWithDamp() {
        if (this.reelCtrResult.getReelStopResultExtend().getDampCtrResult().getUpperDampSymbolIdList().isEmpty() && this.reelCtrResult.getReelStopResultExtend().getDampCtrResult().getLowerDampSymbolIdList().isEmpty()) {
            return ListUtil.copy2DimensionIntegerList(this.screenSymbol);
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int columnIndex = 0; columnIndex < this.screenSymbol.size(); columnIndex++) {
            List<Integer> screenSymbolPerColumn = new ArrayList<>();

            if (this.reelCtrResult.getReelStopResultExtend().getDampCtrResult().isExistUpperDamp()) {
                for (int upperDampIndex = 0; upperDampIndex < this.reelCtrResult.getReelStopResultExtend().getDampCtrResult().getUpperDampSymbolIdList().get(columnIndex).size(); upperDampIndex++) {
                    screenSymbolPerColumn.add(this.reelCtrResult.getReelStopResultExtend().getDampCtrResult().getUpperDampSymbolIdList().get(columnIndex).get(upperDampIndex));
                }
            }

            for (int rowIndex = 0; rowIndex < this.screenSymbol.get(columnIndex).size(); rowIndex++) {
                screenSymbolPerColumn.add(this.screenSymbol.get(columnIndex).get(rowIndex));
            }

            if (this.reelCtrResult.getReelStopResultExtend().getDampCtrResult().isExistLowerDamp()) {
                for (int lowerDampIndex = 0; lowerDampIndex < this.reelCtrResult.getReelStopResultExtend().getDampCtrResult().getLowerDampSymbolIdList().get(columnIndex).size(); lowerDampIndex++) {
                    screenSymbolPerColumn.add(this.reelCtrResult.getReelStopResultExtend().getDampCtrResult().getLowerDampSymbolIdList().get(columnIndex).get(lowerDampIndex));
                }
            }

            result.add(screenSymbolPerColumn);
        }

        return result;
    }

    public List<List<Integer>> getScreenSymbol() {
        return screenSymbol;
    }

    public FrameResult getFrameResult() {
        return frameResult;
    }

    public ReelCtrResult getReelCtrResult() {
        return reelCtrResult;
    }
}
