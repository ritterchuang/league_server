package org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.client.robotHitResult;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.ClientHitResult;

// 機器人表演結果
public class RobotPresentResult {
    private final ClientHitResult hitResult; // 客端打擊結果
    private final int weight; // 權重
    @JsonIgnore
    private final double appearAndPresentProbability; // 出現並死亡率

    public RobotPresentResult(ClientHitResult hitResult, int weight, double appearAndPresentProbability) {
        this.hitResult = hitResult;
        this.weight = weight;
        this.appearAndPresentProbability = appearAndPresentProbability;
    }

    public ClientHitResult getHitResult() {
        return hitResult;
    }

    public int getWeight() {
        return weight;
    }

    public double getAppearAndPresentProbability() {
        return appearAndPresentProbability;
    }
}
