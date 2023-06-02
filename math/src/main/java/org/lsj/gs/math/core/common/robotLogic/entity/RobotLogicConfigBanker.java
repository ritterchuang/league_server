package org.lsj.gs.math.core.common.robotLogic.entity;

import java.util.List;

// 搶莊類機器人邏輯設定
public class RobotLogicConfigBanker {
    private final List<List<Integer>> vieRateWeight; // 搶莊倍數權重 [選項數-1][權重值]
    private final List<List<Integer>> betRateWeight; // 下注倍數權重 [選項數-1][權重值]
    private final long vieBankerMilliTimeSec; // 搶莊毫秒時間
    private final long betMilliTimeSec; // 下注毫秒時間
    private final long selectMilliTimeSec; // 選牌毫秒時間

    public RobotLogicConfigBanker(List<List<Integer>> vieRateWeight,
                                  List<List<Integer>> betRateWeight,
                                  long vieBankerMilliTimeSec,
                                  long betMilliTimeSec,
                                  long selectMilliTimeSec) {
        this.vieRateWeight = vieRateWeight;
        this.betRateWeight = betRateWeight;
        this.vieBankerMilliTimeSec = vieBankerMilliTimeSec;
        this.betMilliTimeSec = betMilliTimeSec;
        this.selectMilliTimeSec = selectMilliTimeSec;
    }

    public List<List<Integer>> getVieRateWeight() {
        return vieRateWeight;
    }
    public List<List<Integer>> getBetRateWeight() {
        return betRateWeight;
    }
    public long getVieBankerMilliTimeSec() {
        return vieBankerMilliTimeSec;
    }
    public long getBetMilliTimeSec() {
        return betMilliTimeSec;
    }
    public long getSelectMilliTimeSec() {
        return selectMilliTimeSec;
    }
}
