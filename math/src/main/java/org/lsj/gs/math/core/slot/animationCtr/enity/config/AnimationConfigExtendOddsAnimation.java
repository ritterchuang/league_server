package org.lsj.gs.math.core.slot.animationCtr.enity.config;

import org.lsj.gs.math.core.slot.ConstMathSlot;

import java.util.Map;

// 動畫額外設定倍數動畫
public class AnimationConfigExtendOddsAnimation extends AnimationConfigExtend{
    private final Map<ConstMathSlot.OddsWinType, Integer> oddsWinTypeToOddsMap; // <獎項類型, 獎項倍數> 對應表

    public AnimationConfigExtendOddsAnimation(Map<ConstMathSlot.OddsWinType, Integer> oddsWinTypeToOddsMap) {
        this.oddsWinTypeToOddsMap = oddsWinTypeToOddsMap;
    }

    public Map<ConstMathSlot.OddsWinType, Integer> getOddsWinTypeToOddsMap() {
        return oddsWinTypeToOddsMap;
    }
}
