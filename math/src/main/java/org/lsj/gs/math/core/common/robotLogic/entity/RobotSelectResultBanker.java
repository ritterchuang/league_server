package org.lsj.gs.math.core.common.robotLogic.entity;

// 機器人選牌結果
public class RobotSelectResultBanker extends AbstractRobotResultBanker {
    private int type; // 選牌方式

    public RobotSelectResultBanker(long milliTimeSec, int type) {
        super(milliTimeSec);
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
