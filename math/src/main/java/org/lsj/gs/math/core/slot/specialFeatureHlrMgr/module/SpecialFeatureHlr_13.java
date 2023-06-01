package org.lsj.gs.math.core.slot.specialFeatureHlrMgr.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrConfig;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResult;
import org.lsj.utils.ListUtil;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// 畫面中由左往右連續 3 軸各有1個以上FreeGame_01 Symbol 且達成連線(line game專用且Pay Line固定)
public class SpecialFeatureHlr_13 extends AbstractSpecialFeatureHlr implements ISpecialFeatureHlr{
    private final int targetNumber = 3; // 目標標誌個數
    private final SpecialFeatureHlrConfig config; // 設定檔
    private final ITableUtil tableUtil; // 牌桌工具包

    // Pay Line 設定
    private final List<List<Integer>> payLineList = new ArrayList<>(){
        {
            add(List.of(1,1,1,1,1));
            add(List.of(0,0,0,0,0));
            add(List.of(2,2,2,2,2));
            add(List.of(0,1,2,1,0));
            add(List.of(2,1,0,1,2));
            add(List.of(0,0,1,0,0));
            add(List.of(2,2,1,2,2));
            add(List.of(1,2,2,2,1));
            add(List.of(1,0,0,0,1));
            add(List.of(1,0,1,0,1));
            add(List.of(1,2,1,2,1));
            add(List.of(0,1,0,1,0));
            add(List.of(2,1,2,1,2));
            add(List.of(1,1,0,1,1));
            add(List.of(1,1,2,1,1));
        }
    };

    public SpecialFeatureHlr_13(SpecialFeatureHlrConfig specialFeatureHlrConfig, ITableUtil tableUtil) {
        super(List.of(ConstMathSlot.SymbolAttribute.FREE_GAME_01));
        this.config = specialFeatureHlrConfig;
        this.tableUtil = tableUtil;
    }


    // 計算特殊事件結果
    @Override
    public Optional<SpecialFeatureHlrResult> calculateSpecialFeatureHlrResult(SpinRequest spinRequest, ScreenGtrResult screenGtrResult, List<CascadeHlrResult> cascadeHlrResultList) {
        // 1. 計算打擊畫面
        List<List<Boolean>> hitScreen = this.calculateScreenHitPosition(screenGtrResult.getScreenSymbol());

        // 2. 判斷是否達成條件
        if (this.isTrigger(hitScreen) == false) {
            return Optional.empty();
        }

        // 3. 計算得分
        double totalWin = MathUtil.multiply(spinRequest.getPlayerCost(), this.config.getSpecialFeatureConfig().getBasePay());

        // 4. 回傳
        return Optional.of(
                new SpecialFeatureHlrResult(
                        this.config.getSpecialFeatureConfig().getSpecialFeatureType(),
                        this.config.getSpecialFeatureConfig().getTriggerEvent(),
                        hitScreen,
                        super.calculateDampScreenHitPosition(hitScreen, screenGtrResult.getReelCtrResult().getReelStopResultExtend().getDampCtrResult()),
                        MathUtil.moneyScale(totalWin)));
    }

    // 計算擊中位置
    private List<List<Boolean>> calculateScreenHitPosition(List<List<Integer>> screenSymbol) {
        // 1. 創建空間
        List<List<Boolean>> result = ListUtil.create2DimensionBooleanList(screenSymbol, false);

        // 2. 遍歷 pay line
        for (int payLineIndex = 0; payLineIndex < payLineList.size(); payLineIndex++) {
            // 3. 宣告有 目標標誌 的欄數
            int columnOfTargetSymbol = 0;

            // 4. 遍例 pay Line 上每個位置
            for (int columnIndex = 0; columnIndex < payLineList.get(payLineIndex).size(); columnIndex++) {
                // 4.2 取得標誌Id
                int symbolId = screenSymbol.get(columnIndex).get(payLineList.get(payLineIndex).get(columnIndex));

                // 4.3 如果等於目標則++
                if (super.isTargetSymbolAttribute(this.config.getSymbolConfig().getSymbolAttributeList().get(symbolId))) {
                    columnOfTargetSymbol++;
                }
            }

            // 5. 判斷是否符合條件
            if(columnOfTargetSymbol >= this.targetNumber) {
                // 5.2 遍例 pay Line 上每個位置
                for (int columnIndex = 0; columnIndex < columnOfTargetSymbol; columnIndex++) {
                    // 5.3 將每個位置都改為true
                    result.get(columnIndex).set(payLineList.get(payLineIndex).get(columnIndex), true);

                }
            }
        }

        // 6. 回傳
        return result;
    }

    // 判斷是否有觸發條件
    private boolean isTrigger(List<List<Boolean>> hitPosition) {
        // 1. 宣告有 目標標誌 的欄數
        int columnOfTargetSymbol = 0;

        // 2. 遍例畫面
        for (int columnIndex = 0; columnIndex < hitPosition.size(); columnIndex++) {
            boolean isThisColumnContainTargetSymbol = false;
            if (hitPosition.get(columnIndex).stream().anyMatch(value -> value)) {
                columnOfTargetSymbol++;
                isThisColumnContainTargetSymbol = true;
            }

            if (isThisColumnContainTargetSymbol == false) {
                break;
            }
        }

        // 3. 回傳
        return columnOfTargetSymbol >= this.targetNumber;
    }
}
