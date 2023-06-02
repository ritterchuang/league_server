package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrConnect.connectCtrUtil;

import org.lsj.gs.math.core.slot.gameCtrMgr.module.commonUtil.AbstractCtrCommonUtil;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrConnect.screenPartionUtil.ScreenPositionCluster;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayTableConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;

import java.util.ArrayList;
import java.util.List;

// 連接 計算共同工具包
public class ConnectCtrCommonUtil extends AbstractCtrCommonUtil {

    public ConnectCtrCommonUtil(SymbolConfig symbolConfig, PayTableConfig payTableConfig) {
        super(symbolConfig, payTableConfig);
    }

    // 計算中獎畫面 [欄][列] = 是否擊中
    public List<List<Boolean>> calculateScreenHitPosition(List<List<Integer>> screenSymbol, ScreenPositionCluster screenPositionCluster) {
        // 1. 創建空間
        List<List<Boolean>> result = new ArrayList<>();

        // 2. 計算擊中位置
        for (int columnIndex = 0; columnIndex < screenSymbol.size(); columnIndex++) {
            // 2.1 建立空間
            List<Boolean> hitPositionPerColumn = new ArrayList<>();

            for (int rowIndex = 0; rowIndex < screenSymbol.get(columnIndex).size(); rowIndex++) {
                // 2.2 取得當前位置
                int[] currentPosition = new int[]{columnIndex, rowIndex};

                // 2.3 計算擊中結果
                if (screenPositionCluster.isContainPosition(currentPosition)) {
                    hitPositionPerColumn.add(true);
                }else {
                    hitPositionPerColumn.add(false);
                }
            }

            result.add(hitPositionPerColumn);
        }

        // 3. 回傳
        return result;
    }
}
