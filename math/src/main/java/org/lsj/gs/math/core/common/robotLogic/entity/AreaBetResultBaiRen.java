package org.lsj.gs.math.core.common.robotLogic.entity;

// 百人機器人區域下注結果
public class AreaBetResultBaiRen extends AbstractRobotResultBaiRen {
    private final int chairIndex; // 座位索引
    private final int areaId; // 區域識別碼
    private final int betAmount; // 押注金額

    public AreaBetResultBaiRen(int milliTimeSec, int chairIndex, int areaId, int betAmount) {
        super(milliTimeSec);
        this.chairIndex = chairIndex;
        this.areaId = areaId;
        this.betAmount = betAmount;
    }

    public int getChairIndex() {
        return chairIndex;
    }

    public int getAreaId() {
        return areaId;
    }

    public int getBetAmount() {
        return betAmount;
    }
}
