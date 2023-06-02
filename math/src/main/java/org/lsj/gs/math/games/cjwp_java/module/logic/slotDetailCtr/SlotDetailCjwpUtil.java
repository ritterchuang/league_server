package org.lsj.gs.math.games.cjwp_java.module.logic.slotDetailCtr;

import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResultExtendCjwp;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.DampCtrResult;
import org.lsj.utils.ListUtil;
import org.lsj.utils.StringUtil;

import java.util.List;

public class SlotDetailCjwpUtil {

    // 計算黃金位置列表
    public String calculateGoldenPositionListPerColumn(DampCtrResult dampCtrResult, CascadeHlrResultExtendCjwp resultExtendCjwp, int columnIndex) {
        // 1. 創建空間
        StringBuilder stringBuilder = new StringBuilder();

        // 2. 無觸發特色
        List<List<Boolean>> goldenPositionBeforeCascade = resultExtendCjwp.getBeforeCascadeGoldenPosition();

        // 3. 取出算分位置
        List<Boolean> goldenPositionBeforeCascadeCopy = ListUtil.copy2DimensionBooleanList(goldenPositionBeforeCascade).get(columnIndex);

        // 4. 移除上破框資訊
        if (dampCtrResult.isExistUpperDamp()) {
            List<Integer> upperDampPerColumn = dampCtrResult.getUpperDampSymbolIdList().get(columnIndex);
            for (int index = 0; index < upperDampPerColumn.size(); index++) {
                goldenPositionBeforeCascadeCopy.remove(0);
            }
        }

        // 5. 移除下破框資訊
        if (dampCtrResult.isExistLowerDamp()) {
            List<Integer> lowerDampPerColumn = dampCtrResult.getLowerDampSymbolIdList().get(columnIndex);
            for (int index = 0; index < lowerDampPerColumn.size(); index++) {
                goldenPositionBeforeCascadeCopy.remove(goldenPositionBeforeCascadeCopy.size() - 1);
            }
        }

        // 6. 添加結果
        for (int index = 0; index < goldenPositionBeforeCascadeCopy.size(); index++) {
            stringBuilder.append(goldenPositionBeforeCascadeCopy.get(index));
            if (index != goldenPositionBeforeCascadeCopy.size() - 1) {
                stringBuilder.append(StringUtil.getInstance().getCommaString());
            }
        }

        // 7. 回傳
        return stringBuilder.toString();
    }
}
