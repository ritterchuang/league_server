package org.lsj.gs.math.core.slot.specialFeatureHlrMgr.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrConfig;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResult;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// 畫面中恰有2欄有 FreeGame_01
public class SpecialFeatureHlr_01 extends AbstractSpecialFeatureHlr implements ISpecialFeatureHlr{
    private final int targetNumber = 2; // 目標標誌個數
    private final SpecialFeatureHlrConfig config; // 設定檔
    private final ITableUtil tableUtil; // 牌桌工具包

    public SpecialFeatureHlr_01(SpecialFeatureHlrConfig specialFeatureHlrConfig, ITableUtil tableUtil) {
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
        List<List<Boolean>> result = new ArrayList<>();

        // 2. 遍歷畫面
        for (int columnIndex = 0; columnIndex < screenSymbol.size(); columnIndex++) {
            // 2.1 創建擊中位置_欄
            List<Boolean> hitPositionPerColumn = new ArrayList<>();

            for (int rowIndex = 0; rowIndex < screenSymbol.get(columnIndex).size(); rowIndex++) {
                // 2.2 取得標誌Id
                int symbolId = screenSymbol.get(columnIndex).get(rowIndex);

                // 2.3 判斷是否為目標標誌
                if (super.isTargetSymbolAttribute(this.config.getSymbolConfig().getSymbolAttributeList().get(symbolId))) {
                    hitPositionPerColumn.add(true);
                }else {
                    hitPositionPerColumn.add(false);
                }
            }

            // 2.4 添加結果
            result.add(hitPositionPerColumn);
        }

        // 3. 回傳
        return result;
    }

    // 判斷是否有觸發條件
    private boolean isTrigger(List<List<Boolean>> hitPosition) {
        // 1. 宣告有 freeGame_01 的欄數
        int columnOfTargetSymbol = 0;

        // 2. 遍例畫面
        for (int columnIndex = 0; columnIndex < hitPosition.size(); columnIndex++) {
            if (hitPosition.get(columnIndex).stream().anyMatch(value -> value)) {
                columnOfTargetSymbol++;
            }
        }

        // 3. 回傳
        return columnOfTargetSymbol == this.targetNumber;
    }
}
