package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrWay.wayHitUtil;

import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayTableConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// 抽象 way 工具包父類別
public abstract class AbstractWayUtil {
    protected final SymbolConfig symbolConfig; // 標誌設定
    protected final PayTableConfig payTableConfig; // 賠率設定

    public AbstractWayUtil(SymbolConfig symbolConfig, PayTableConfig payTableConfig) {
        this.symbolConfig = symbolConfig;
        this.payTableConfig = payTableConfig;
    }

    // 計算簡化後畫面 [移除 -1][移除未擊中欄數]
    public List<List<Integer>> calculateSimplifyHitScreen(List<List<Integer>> hitScreen, int hitNumber) {
        // 1. 畫面防呆
        if (hitScreen.isEmpty()) {
            return Collections.emptyList();
        }

        // 2. 連線數防呆
        if (hitScreen.size() < hitNumber || hitNumber <= 0) {
            return Collections.emptyList();
        }

        // 3. 宣告空間
        List<List<Integer>> result = new ArrayList<>();

        // 4. 計算簡化後畫面
        for (int columnIndex = 0; columnIndex < hitNumber; columnIndex++) {
            result.add(hitScreen.get(columnIndex).stream().filter(symbolId -> symbolId >= 0).collect(Collectors.toList()));
        }

        // 5. 回傳
        return result;
    }

    // 計算總堆疊數
    public int calculateTotalHitCount(List<List<Integer>> simplifyHitScreen, int hitNumber) {
        // 1. 畫面防呆
        if (simplifyHitScreen.isEmpty()) {
            return 0;
        }

        // 2. 連線數防呆
        if (simplifyHitScreen.size() < hitNumber || hitNumber <= 0) {
            return 0;
        }

        // 3. 計算總堆疊數
        int result = 1;
        for (int columnIndex = 0; columnIndex < hitNumber; columnIndex++) {
            result = result * simplifyHitScreen.get(columnIndex).size();
        }

        // 4. 回傳
        return result;
    }
}
