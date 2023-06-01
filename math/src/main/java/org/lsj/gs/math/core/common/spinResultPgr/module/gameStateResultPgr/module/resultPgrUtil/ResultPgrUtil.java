package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.resultPgrUtil;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.SymbolBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 結果包裝者工具包
public class ResultPgrUtil {

    // 計算 目標座標ID 列表
    public List<List<Integer>> calculateTargetPositionId(List<List<Boolean>> targetList, List<List<Integer>> positionIdList) {
        if (targetList.size() != positionIdList.size()) {
            return Collections.emptyList();
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int columnIndex = 0; columnIndex < targetList.size(); columnIndex++) {
            if (targetList.get(columnIndex).size() != positionIdList.get(columnIndex).size()) {
                return Collections.emptyList();
            }

            List<Integer> resultPerColumn = new ArrayList<>();
            for (int index = 0; index < targetList.get(columnIndex).size(); index++) {
                if (targetList.get(columnIndex).get(index)) {
                    resultPerColumn.add(positionIdList.get(columnIndex).get(index));
                }
            }

            result.add(resultPerColumn);
        }

        return result;
    }

    // 計算畫面位置座標列表
    public List<List<Integer>> calculatePositionIdList(List<List<SymbolBox>> symbolBoxList) {
        if (symbolBoxList.isEmpty()) {
            return Collections.emptyList();
        }

        List<List<Integer>> result = new ArrayList<>();
        for (List<SymbolBox> symbolBoxes : symbolBoxList) {
            List<Integer> resultPerColumn = new ArrayList<>();
            for (SymbolBox symbolBox : symbolBoxes) {
                resultPerColumn.add(symbolBox.getId());
            }

            result.add(resultPerColumn);
        }

        return result;
    }
}
