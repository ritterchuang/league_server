package org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.client.robotHitResult;

// 機器人子彈擊殺結果
public class RobotHitResult {
    private final double robotPresentProbability; // 表演機率
    private final RobotPresentResult[] robotPresentResultArray; // 擊殺表演結果列表

    public RobotHitResult() {
        this.robotPresentProbability = 0.0;
        this.robotPresentResultArray = new RobotPresentResult[0];
    }

    public RobotHitResult(double robotPresentProbability, RobotPresentResult[] robotPresentResultArray) {
        this.robotPresentProbability = robotPresentProbability;
        this.robotPresentResultArray = robotPresentResultArray;
    }

    public double getRobotPresentProbability() {
        return robotPresentProbability;
    }

    public RobotPresentResult[] getRobotPresentResultArray() {
        return robotPresentResultArray;
    }

}
