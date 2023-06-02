package org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity;

import org.lsj.utils.ListUtil;

import java.util.Collections;
import java.util.List;

// 特殊世界處理者結果集合
public class SpecialFeatureHlrResultCluster {
    private final double totalWin; // 總得分
    private final List<SpecialFeatureHlrResult> specialFeatureHlrResultList; // 特殊事件處理者結果列表

    public SpecialFeatureHlrResultCluster(double totalWin, List<SpecialFeatureHlrResult> specialFeatureHlrResultList) {
        this.totalWin = totalWin;
        this.specialFeatureHlrResultList = specialFeatureHlrResultList;
    }

    public SpecialFeatureHlrResultCluster() {
        this.totalWin = 0.0;
        this.specialFeatureHlrResultList = Collections.emptyList();
    }

    public double getTotalWin() {
        return totalWin;
    }

    public List<SpecialFeatureHlrResult> getSpecialFeatureHlrResultList() {
        return specialFeatureHlrResultList;
    }


    /* 計算得分位置聯集 */
    // 計算所有得分位置聯集
    public List<List<Boolean>> getIntegralHitPosition() {
        // 1. 若無結果回傳空陣列
        if (this.specialFeatureHlrResultList.size() == 0) {
            return Collections.emptyList();
        }

        // 2. 複製第一個結果
        List<List<Boolean>> result = ListUtil.copy2DimensionBooleanList(this.specialFeatureHlrResultList.get(0).getScreenHitPosition());

        // 3. 遍歷所有結果取聯集
        for (int specialFeatureIndex = 1; specialFeatureIndex < this.specialFeatureHlrResultList.size(); specialFeatureIndex++) {
            // 3.1 取得當前得分結果位置
            List<List<Boolean>> currentHitPosition = this.specialFeatureHlrResultList.get(specialFeatureIndex).getScreenHitPosition();

            // 3.2 取聯集
            for (int columnIndex = 0; columnIndex < currentHitPosition.size(); columnIndex++) {
                for (int rowIndex = 0; rowIndex < currentHitPosition.get(columnIndex).size(); rowIndex++) {
                    if (currentHitPosition.get(columnIndex).get(rowIndex) == true) {
                        result.get(columnIndex).set(rowIndex, true);
                    }
                }
            }
        }

        // 4. 回傳
        return result;
    }

    // 計算所有得分位置聯集
    public List<List<Boolean>> getIntegralDampHitPosition() {
        // 1. 若無結果回傳空陣列
        if (this.specialFeatureHlrResultList.size() == 0) {
            return Collections.emptyList();
        }

        // 2. 若無 Damp, 回傳一般得分位置聯集
        if (this.specialFeatureHlrResultList.get(0).getDampScreenHitPosition().isEmpty()) {
            return this.getIntegralHitPosition();
        }

        // 3. 複製第一個結果
        List<List<Boolean>> result = ListUtil.copy2DimensionBooleanList(this.specialFeatureHlrResultList.get(0).getDampScreenHitPosition());

        // 3. 遍歷所有結果取聯集
        for (int specialFeatureIndex = 1; specialFeatureIndex < this.specialFeatureHlrResultList.size(); specialFeatureIndex++) {
            // 3.1 取得當前得分結果位置
            List<List<Boolean>> currentHitPosition = this.specialFeatureHlrResultList.get(specialFeatureIndex).getDampScreenHitPosition();

            // 3.2 取聯集
            for (int columnIndex = 0; columnIndex < currentHitPosition.size(); columnIndex++) {
                for (int rowIndex = 0; rowIndex < currentHitPosition.get(columnIndex).size(); rowIndex++) {
                    if (currentHitPosition.get(columnIndex).get(rowIndex) == true) {
                        result.get(columnIndex).set(rowIndex, true);
                    }
                }
            }
        }

        // 4. 回傳
        return result;
    }
}
