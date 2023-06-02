package org.lsj.gs.math.core.slot.animationCtr.module;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.animationCtr.IAnimationCtr;
import org.lsj.gs.math.core.slot.animationCtr.enity.config.AnimationConfigExtendOddsAnimation;
import org.lsj.gs.math.core.slot.animationCtr.enity.config.AnimationCtrConfig;
import org.lsj.gs.math.core.slot.animationCtr.enity.result.AnimationResult;
import org.lsj.gs.math.core.slot.animationCtr.enity.result.AnimationResultExtendOddsAnimation;
import org.lsj.utils.MathUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 動畫計算者倍數 TODO 參數設定 還要一個不做事的CTR
public class AnimationCtrOddsAnimation implements IAnimationCtr {
    private final ConstMathSlot.AnimationType animationType; // 動畫類型
    private final AnimationConfigExtendOddsAnimation config; // 動畫類型額外設定
    private final Map<ConstMathSlot.OddsWinType, int[]> oddsWinTypeToOddsRangeMap; // <倍數類型, 倍數區間> 對應表

    public AnimationCtrOddsAnimation(AnimationCtrConfig animationCtrConfig) {
        this.animationType = animationCtrConfig.getAnimationType();
        this.config = (AnimationConfigExtendOddsAnimation) animationCtrConfig.getConfigExtend();
        this.oddsWinTypeToOddsRangeMap = this.calculateOddsWinTypeToOddsRange();
    }

    // 計算動畫結果
    @Override
    public AnimationResult calculateAnimationResult(double playerBet, double totalWin) {
        // 1. 計算此局總倍數
        double odds = MathUtil.divide(totalWin, playerBet);

        // 2. 無得獎
        if (odds == 0.0) {
            return new AnimationResult(this.animationType, new AnimationResultExtendOddsAnimation(ConstMathSlot.OddsWinType.NO_WIN));
        }

        // 3. 計算獎項級距
        for (Map.Entry<ConstMathSlot.OddsWinType, int[]> entry: this.oddsWinTypeToOddsRangeMap.entrySet()) {
            if (entry.getValue()[0] <= odds && odds < entry.getValue()[1]) {
                return new AnimationResult(this.animationType, new AnimationResultExtendOddsAnimation(entry.getKey()));
            }
        }

        // 4. 未設定小獎
        return new AnimationResult(this.animationType, new AnimationResultExtendOddsAnimation(ConstMathSlot.OddsWinType.REGULAR_WIN));
    }

    // 計算 倍數類型與倍數區間對應表
    private Map<ConstMathSlot.OddsWinType, int[]> calculateOddsWinTypeToOddsRange() {
        // 1. 宣告空間
        Map<ConstMathSlot.OddsWinType, int[]> result = new LinkedHashMap<>();

        // 2. 依照 倍數 排序過 Map
        List<Map.Entry<ConstMathSlot.OddsWinType, Integer>> sortedEntryList = this.config.getOddsWinTypeToOddsMap().entrySet().stream().
                sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());

        // 3. 計算倍數區間對應表
        for (int entryIndex = 0; entryIndex < sortedEntryList.size(); entryIndex++) {
            if (entryIndex == sortedEntryList.size() - 1) {
                result.put(sortedEntryList.get(entryIndex).getKey(), new int[]{sortedEntryList.get(entryIndex).getValue(), Integer.MAX_VALUE});
            }else {
                result.put(sortedEntryList.get(entryIndex).getKey(), new int[]{sortedEntryList.get(entryIndex).getValue(), sortedEntryList.get(entryIndex + 1).getValue()});
            }
        }
        
        return result;
    }
}
