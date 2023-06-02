package org.lsj.gs.math.core.common.robotLogic.entity;

import java.util.List;

// 通比類機器人邏輯設定
public class RobotLogicConfigTonbi {
    private final List<List<Integer>> betRateWeight; // 下注倍數權重 [選項數-1][權重值]
    private final long betMilliTimeSec; // 下注毫秒時間
    private final long selectMilliTimeSec; // 選牌毫秒時間

    public RobotLogicConfigTonbi(List<List<Integer>> betRateWeight,
                                 long betMilliTimeSec,
                                 long selectMilliTimeSec) {
        this.betRateWeight = betRateWeight;
        this.betMilliTimeSec = betMilliTimeSec;
        this.selectMilliTimeSec = selectMilliTimeSec;
    }

    public List<List<Integer>> getBetRateWeight() {
        return betRateWeight;
    }
    public long getBetMilliTimeSec() {
        return betMilliTimeSec;
    }
    public long getSelectMilliTimeSec() {
        return selectMilliTimeSec;
    }
}
