package org.lsj.gs.math.core.baiRen.mathConfigHlr.entity;

import java.util.ArrayList;
import java.util.List;

// 數值設定
public class BaiRenMathConfig {
    private final double betTimeSec; // 下注狀態時間秒數
    private final double endTimeSec; // 結算狀態時間秒數
    private final List<Integer> roomBetTopLimitList; // 房間押注上下限陣列

    // 原始建構子提供JSON解析用
    public BaiRenMathConfig() {
        this.betTimeSec = 0.0;
        this.endTimeSec = 0.0;
        this.roomBetTopLimitList = new ArrayList<>(){};
    }

    public BaiRenMathConfig(double betTimeSec, double endTimeSec, List<Integer> roomBetTopLimitList) {
        this.betTimeSec = betTimeSec;
        this.endTimeSec = endTimeSec;
        this.roomBetTopLimitList = roomBetTopLimitList;
    }

    public double getBetTimeSec() {
        return betTimeSec;
    }

    public double getEndTimeSec() {
        return endTimeSec;
    }

    public List<Integer> getRoomBetTopLimitList() {
        return roomBetTopLimitList;
    }
}
