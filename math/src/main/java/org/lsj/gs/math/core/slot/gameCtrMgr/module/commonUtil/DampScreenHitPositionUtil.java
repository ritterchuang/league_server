package org.lsj.gs.math.core.slot.gameCtrMgr.module.commonUtil;

import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.DampCtrResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 破框擊中畫面計算工具包 TODO 分類位置
public class DampScreenHitPositionUtil {

    // 計算破框中獎畫面 TODO 無設定是否直接回傳傳入值?
    public List<List<Boolean>> calculateDampScreenHitPosition(List<List<Boolean>> screenPosition, DampCtrResult dampCtrResult) {
        // 1. 無設定，不計算
        if (dampCtrResult.getUpperDampSymbolIdList().isEmpty() && dampCtrResult.getLowerDampSymbolIdList().isEmpty()) {
            return Collections.emptyList();
        }

        // 2. 宣告變數
        List<List<Boolean>> result = new ArrayList<>();

        // 3. 添加上破框打擊結果
        if (dampCtrResult.isExistUpperDamp()) {
            for (int columnIndex = 0; columnIndex < dampCtrResult.getUpperDampSymbolIdList().size(); columnIndex++) {
                List<Boolean> hitPositionByColumn = new ArrayList<>();
                for (int dampIndex = 0; dampIndex < dampCtrResult.getUpperDampSymbolIdList().get(columnIndex).size(); dampIndex++) {
                    hitPositionByColumn.add(false);
                }
                result.add(hitPositionByColumn);
            }
        }else {
            for (int columnIndex = 0; columnIndex < screenPosition.size(); columnIndex++) {
                result.add(new ArrayList<>());
            }
        }


        // 4. 添加此次打擊結果
        for (int columnIndex = 0; columnIndex < screenPosition.size(); columnIndex++) {
            List<Boolean> hitPositionByColumn = result.get(columnIndex);

            for (int rowIndex = 0; rowIndex < screenPosition.get(columnIndex).size(); rowIndex++) {
                hitPositionByColumn.add(screenPosition.get(columnIndex).get(rowIndex));
            }
        }

        // 5. 添加下破框打擊結果
        if (dampCtrResult.isExistLowerDamp()) {
            for (int columnIndex = 0; columnIndex < dampCtrResult.getLowerDampSymbolIdList().size(); columnIndex++) {
                List<Boolean> hitPositionByColumn = result.get(columnIndex);

                for (int dampIndex = 0; dampIndex < dampCtrResult.getLowerDampSymbolIdList().get(columnIndex).size(); dampIndex++) {
                    hitPositionByColumn.add(false);
                }
            }
        }

        // 6. 回傳
        return result;
    }
}
