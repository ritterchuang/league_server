package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrLine.lineHitUtil;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.commonUtil.AbstractCtrCommonUtil;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayTableConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.utils.ListUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 線 計算共同工具包
public class LineCtrCommonUtil extends AbstractCtrCommonUtil {
    private final List<List<Integer>> linePositionIndexList; // 線中獎位置 [lineId][columnIndex] = rowIndex

    public LineCtrCommonUtil(SymbolConfig symbolConfig, PayTableConfig payTableConfig, List<List<Integer>> linePositionIndexList ) {
        super(symbolConfig, payTableConfig);
        this.linePositionIndexList = linePositionIndexList;
    }

    // 依照線型、畫面取得 一列結果
    public List<Integer> calculateScreenSymbolPerLine(int lineId, List<List<Integer>> screenSymbol) {
        if (this.linePositionIndexList.size() <= lineId || lineId < 0) {
            return Collections.emptyList();
        }

        List<Integer> result = new ArrayList<>();

        for (int columnIndex = 0; columnIndex < this.linePositionIndexList.get(lineId).size(); columnIndex++) {
            int rowIndex = this.linePositionIndexList.get(lineId).get(columnIndex);
            result.add(screenSymbol.get(columnIndex).get(rowIndex));
        }

        return result;
    }

    // 計算中獎畫面 [欄][列] = 是否擊中
    public List<List<Boolean>> calculateScreenHitPosition(
            List<List<Integer>> screenSymbol,
            int lineId,
            int hitNumber,
            ConstMathSlot.GameHitDirectionType gameHitDirectionType) {

        // 1. 檢查打擊畫面
        if (screenSymbol.isEmpty()) {
            return Collections.emptyList();
        }

        // 2. 檢查線型
        if (this.linePositionIndexList.size() <= lineId || lineId < 0) {
            return Collections.emptyList();
        }

        // 3. 檢查連線數
        if (screenSymbol.size() < hitNumber || hitNumber <= 0) {
            return Collections.emptyList();
        }

        // 4. 創建空間
        List<List<Boolean>> result = new ArrayList<>();

        // 5. 計算擊中位置
        for (int columnIndex = 0; columnIndex < screenSymbol.size(); columnIndex++) {
            List<Boolean> hitPositionPerColumn = new ArrayList<>();
            int hitRowIndex = this.linePositionIndexList.get(lineId).get(columnIndex);
            for (int rowIndex = 0; rowIndex < screenSymbol.get(columnIndex).size(); rowIndex++) {
                if (columnIndex <= hitNumber - 1
                && rowIndex == hitRowIndex) {
                    hitPositionPerColumn.add(true);
                }else {
                    hitPositionPerColumn.add(false);
                }
            }

            result.add(hitPositionPerColumn);
        }

        // 6. 若方向為右到左，需反轉
        if (gameHitDirectionType.equals(ConstMathSlot.GameHitDirectionType.RIGHT_TO_LEFT)) {
            return ListUtil.inverse2DimensionBooleanList(result);
        }

        // 7. 回傳
        return result;
    }

}
