package org.lsj.gs.math.games.dgry_java.entity.config;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfigExtend;
import org.lsj.gs.math.core.slot.ConstMathSlot;

import java.util.List;
import java.util.Map;

// 帝国榮耀額外遊戲局額外設定
public class GameStateConfigExtendDgryBonusGame extends GameStateConfigExtend {
    private final List<DgryBonusGameDisplayType> insertDisplayList; // 插入用結果列表
    private final List<Integer> insertDisplayWeightList; // 插入用結果權重列表
    private final List<DgryBonusGameDisplayType> noDamageDisplayList; // 結果(不扣血)列表
    private final Map<ConstMathSlot.ReelRtpType, List<Integer>> noDamageDisplayWeightList; // 結果(不扣血)權重列表
    private final List<Integer> noDamageDisplayCountList; // 結果(不扣血)數量列表
    private final Map<ConstMathSlot.ReelRtpType, List<Integer>>  noDamageDisplayCountWeightList; // 結果(不扣血)數量權重列表
    private final List<DgryBonusGameDisplayType> takeDamageDisplayList; // 結果(會扣血)列表
    private final  Map<ConstMathSlot.ReelRtpType, List<Integer>> takeDamageDisplayWeightList; // 結果(會扣血)權重列表
    private final List<Integer> multiplierList; // 畫面倍數列表
    private final Map<ConstMathSlot.ReelRtpType, List<Integer>>  multiplierWeightList; // 畫面倍數權重列表
    private final List<Integer> displayTimesRange; // 表演次數範圍
    private final int lifePoint; // 初始血量

    public GameStateConfigExtendDgryBonusGame(List<DgryBonusGameDisplayType> insertDisplayList, List<Integer> insertDisplayWeightList, List<DgryBonusGameDisplayType> noDamageDisplayList, Map<ConstMathSlot.ReelRtpType, List<Integer>> noDamageDisplayWeightList, List<Integer> noDamageDisplayCountList, Map<ConstMathSlot.ReelRtpType, List<Integer>>  noDamageDisplayCountWeightList, List<DgryBonusGameDisplayType> takeDamageDisplayList, Map<ConstMathSlot.ReelRtpType, List<Integer>> takeDamageDisplayWeightList, List<Integer> multiplierList, Map<ConstMathSlot.ReelRtpType, List<Integer>>  multiplierWeightList, List<Integer> displayTimesRange, int lifePoint) {
        this.insertDisplayList = insertDisplayList;
        this.insertDisplayWeightList = insertDisplayWeightList;
        this.noDamageDisplayList = noDamageDisplayList;
        this.noDamageDisplayWeightList = noDamageDisplayWeightList;
        this.noDamageDisplayCountList = noDamageDisplayCountList;
        this.noDamageDisplayCountWeightList = noDamageDisplayCountWeightList;
        this.takeDamageDisplayList = takeDamageDisplayList;
        this.takeDamageDisplayWeightList = takeDamageDisplayWeightList;
        this.multiplierList = multiplierList;
        this.multiplierWeightList = multiplierWeightList;
        this.displayTimesRange = displayTimesRange;
        this.lifePoint = lifePoint;
    }

    public List<DgryBonusGameDisplayType> getInsertDisplayList() {
        return insertDisplayList;
    }

    public List<Integer> getInsertDisplayWeightList() {
        return insertDisplayWeightList;
    }

    public List<DgryBonusGameDisplayType> getNoDamageDisplayList() {
        return noDamageDisplayList;
    }

    public Map<ConstMathSlot.ReelRtpType, List<Integer>> getNoDamageDisplayWeightList() {
        return noDamageDisplayWeightList;
    }
    public List<Integer> getNoDamageDisplayCountList() {
        return noDamageDisplayCountList;
    }
    public Map<ConstMathSlot.ReelRtpType, List<Integer>>  getNoDamageDisplayCountWeightList() {
        return noDamageDisplayCountWeightList;
    }
    public List<DgryBonusGameDisplayType> getTakeDamageDisplayList() { return takeDamageDisplayList; }
    public Map<ConstMathSlot.ReelRtpType, List<Integer>> getTakeDamageDisplayWeightList() { return takeDamageDisplayWeightList; }
    public List<Integer> getMultiplierList() {
        return multiplierList;
    }
    public Map<ConstMathSlot.ReelRtpType, List<Integer>>  getMultiplierWeightList() {
        return multiplierWeightList;
    }
    public List<Integer> getDisplayTimesRange() {
        return displayTimesRange;
    }
    public int getLifePoint() {
        return lifePoint;
    }
}
