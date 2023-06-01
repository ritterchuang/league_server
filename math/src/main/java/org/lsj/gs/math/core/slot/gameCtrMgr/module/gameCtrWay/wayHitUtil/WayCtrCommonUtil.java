package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrWay.wayHitUtil;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.commonUtil.AbstractCtrCommonUtil;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayTableConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.utils.ListUtil;

import java.util.ArrayList;
import java.util.List;

// 路 計算共同工具包
public class WayCtrCommonUtil extends AbstractCtrCommonUtil {

    public WayCtrCommonUtil(SymbolConfig symbolConfig, PayTableConfig payTableConfig) {
        super(symbolConfig, payTableConfig);
    }

    // 計算中獎畫面 [欄][列] = 是否擊中
    public List<List<Boolean>> calculateScreenHitPosition(List<List<Integer>> hitScreen, int hitNumber, ConstMathSlot.GameHitDirectionType gameHitDirectionType) {
        // 1. 創建空間
        List<List<Boolean>> result = new ArrayList<>();

        // 2. 計算擊中位置
        for (int columnIndex = 0; columnIndex < hitScreen.size(); columnIndex++) {
            List<Boolean> hitPositionPerColumn = new ArrayList<>();

            for (int rowIndex = 0; rowIndex < hitScreen.get(columnIndex).size(); rowIndex++) {
                if (columnIndex <= hitNumber - 1 && hitScreen.get(columnIndex).get(rowIndex) >= 0) {
                    hitPositionPerColumn.add(true);
                }else {
                    hitPositionPerColumn.add(false);
                }
            }

            result.add(hitPositionPerColumn);
        }

        // 3. 若方向為右到左，需反轉
        if (gameHitDirectionType.equals(ConstMathSlot.GameHitDirectionType.RIGHT_TO_LEFT)) {
            return ListUtil.inverse2DimensionBooleanList(result);
        }

        // 4. 回傳
        return result;
    }
}
