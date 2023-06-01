package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrWay.wayHitUtil;

import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.way.WayCtrWinResult;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayTableConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.utils.ListUtil;

import java.util.List;

// 擊中個數工具包效率 [若有混搭，混搭所有子集都要寫]
public class WayHitCountUtilEfficient extends AbstractWayUtil {

    public WayHitCountUtilEfficient(SymbolConfig symbolConfig, PayTableConfig payTableConfig) {
        super(symbolConfig, payTableConfig);
    }

    // 計算 純 wild 打擊次數
    public int calculateSingleWildSymbolHitCount(List<List<Integer>> hitScreen, int hitNumber) {
        // 1. 計算簡易版擊中畫面
        List<List<Integer>> simplifyHitScreen = super.calculateSimplifyHitScreen(hitScreen, hitNumber);

        // 2. 計算總打擊數
        return super.calculateTotalHitCount(simplifyHitScreen, hitNumber);
    }

    // 計算 混 wild 擊中數
    public int calculateMixWildSymbolHitCount(List<List<Integer>> hitScreen, List<Integer> payComboTargetSymbolIdList, int hitNumber, List<WayCtrWinResult> wayCtrWinResultList) {
        // 1. 計算簡易版擊中畫面
        List<List<Integer>> simplifyHitScreen = super.calculateSimplifyHitScreen(hitScreen, hitNumber);

        // 2. 計算總打擊數
        int totalHitCount = super.calculateTotalHitCount(simplifyHitScreen, hitNumber);

        // 3. 扣除相同連線數子集合的擊中數
        for (int wayWinIndex = 0; wayWinIndex < wayCtrWinResultList.size(); wayWinIndex++) {
            WayCtrWinResult wayCtrWinResult = wayCtrWinResultList.get(wayWinIndex);

            if (this.isNeedToMinusInMixWild(payComboTargetSymbolIdList, hitNumber, wayCtrWinResult)) {
                totalHitCount = totalHitCount - wayCtrWinResult.getHitCount();
            }
        }

        // 4. 回傳
        return totalHitCount;
    }

    // 計算 純 symbol 擊中數
    public int calculateSingleNormalSymbolAndWildSymbolHitCount(List<List<Integer>> hitScreen, List<Integer> payComboTargetSymbolIdList, int hitNumber, List<WayCtrWinResult> wayCtrWinResultList) {
        // 1. 計算簡易版擊中畫面
        List<List<Integer>> simplifyHitScreen = super.calculateSimplifyHitScreen(hitScreen, hitNumber);

        // 2. 計算總打擊數
        int totalHitCount = super.calculateTotalHitCount(simplifyHitScreen, hitNumber);

        // 3. 扣除相同連線數子集合的擊中數
        for (int wayWinIndex = 0; wayWinIndex < wayCtrWinResultList.size(); wayWinIndex++) {
            WayCtrWinResult wayCtrWinResult = wayCtrWinResultList.get(wayWinIndex);

            if (this.isNeedToMinusInSingleSymbol(hitNumber, wayCtrWinResult)) {
                totalHitCount = totalHitCount - wayCtrWinResult.getHitCount();
            }
        }

        // 4. 回傳
        return totalHitCount;
    }

    // 計算 混 symbol 擊中數
    public int calculateMixNormalSymbolAndWildSymbolHitCount(List<List<Integer>> hitScreen, List<Integer> payComboTargetSymbolIdList, int hitNumber, List<WayCtrWinResult> wayCtrWinResultList) {
        // 1. 計算簡易版擊中畫面
        List<List<Integer>> simplifyHitScreen = super.calculateSimplifyHitScreen(hitScreen, hitNumber);

        // 2. 計算總打擊數
        int totalHitCount = super.calculateTotalHitCount(simplifyHitScreen, hitNumber);

        // 3. 扣除相同連線數子集合的擊中數
        for (int wayWinIndex = 0; wayWinIndex < wayCtrWinResultList.size(); wayWinIndex++) {
            WayCtrWinResult wayCtrWinResult = wayCtrWinResultList.get(wayWinIndex);

            if (this.isNeedToMinusInMixSymbol(payComboTargetSymbolIdList, hitNumber, wayCtrWinResult)) {
                totalHitCount = totalHitCount - wayCtrWinResult.getHitCount();
            }
        }

        // 4. 回傳
        return totalHitCount;
    }



    //* 判斷是否扣除重複堆疊 *//

    // 判斷混 wild 是否要扣除該堆疊
    private boolean isNeedToMinusInMixWild(List<Integer> payComboTargetSymbolIdList, int hitNumber, WayCtrWinResult wayCtrWinResult) {
        // 1. 檢查連線數
        if (hitNumber != wayCtrWinResult.getHitNumber()) {
            return false;
        }

        // 2. 檢查 是否為目標標誌陣列子集合
        return this.isSubWinResult(payComboTargetSymbolIdList, wayCtrWinResult);
    }

    // 判斷計算純標誌是否要扣除該堆疊
    private boolean isNeedToMinusInSingleSymbol(int hitNumber, WayCtrWinResult wayCtrWinResult) {
        // 1. 檢查連線數
        if (hitNumber != wayCtrWinResult.getHitNumber()) {
            return false;
        }

        // 2. 檢查是否為 wild Result
        return this.isWildResult(wayCtrWinResult);
    }

    // 判斷計算混搭是否要扣除該堆疊
    private boolean isNeedToMinusInMixSymbol(List<Integer> payComboTargetSymbolIdList, int hitNumber, WayCtrWinResult wayCtrWinResult) {
        // 1. 檢查連線數
        if (hitNumber != wayCtrWinResult.getHitNumber()) {
            return false;
        }

        // 2. 檢查是否為 wild 結果
        return  this.isWildResult(wayCtrWinResult) || this.isSubWinResult(payComboTargetSymbolIdList, wayCtrWinResult);
    }

    // 檢查是否為 wild 結果
    private boolean isWildResult(WayCtrWinResult wayCtrWinResult) {
        // 1. 取得 wayWin 的標誌列表
        List<Integer> wayWinSymbolList = super.payTableConfig.getPayComboList().get(wayCtrWinResult.getPayComboId()).getSymbolIdList();

        // 3. 檢查 是否為 wild 結果
        return wayWinSymbolList.stream().allMatch(super.symbolConfig::isWildSymbol);
    }

    // 檢查是否為子陣列
    private boolean isSubWinResult(List<Integer> payComboTargetSymbolIdList, WayCtrWinResult wayCtrWinResult) {
        // 1. 取得路得分標誌列表
        List<Integer> wayWinSymbolList = super.payTableConfig.getPayComboList().get(wayCtrWinResult.getPayComboId()).getSymbolIdList();

        // 2. 檢查得分標誌列表是否為目標標誌列表子陣列
        return ListUtil.isSubList(payComboTargetSymbolIdList, wayWinSymbolList);
    }
}
