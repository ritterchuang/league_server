package org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.fish;

import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.GameResultExtend;

// 金蟾捕魚客製遊戲結果
public class GameResultExtendFishJava extends GameResultExtend {
    private final int bulletIndex; // 子彈索引
    private final int targetIndex; // 目標索引
    private final double validBet; // 有效押注
    private final double score; // 玩家淨利
    private final double credit; // 玩家有效投注
    private final HitResult hitResult; // 玩家打擊結果

    public GameResultExtendFishJava(int bulletIndex, int targetIndex, double validBet, double score, double credit, HitResult hitResult) {
        this.bulletIndex = bulletIndex;
        this.targetIndex = targetIndex;
        this.validBet = validBet;
        this.score = score;
        this.credit = credit;
        this.hitResult = hitResult;
    }

    public int getBulletIndex() {
        return bulletIndex;
    }

    public int getTargetIndex() {
        return targetIndex;
    }

    public double getValidBet() {
        return validBet;
    }

    public double getScore() {
        return score;
    }

    public double getCredit() {
        return credit;
    }

    public HitResult getHitResult() {
        return hitResult;
    }
}
