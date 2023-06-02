package org.lsj.gs.math.games.dgry_java.entity.config;

import org.lsj.gs.math.games.dgry_java.entity.ConstDgryJava;

import java.util.List;

// 帝国榮耀額外遊戲局額外設定(表演類型)
public class DgryBonusGameDisplayType {
    private final ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava outcomeType; // 結果類型
    private final ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava iconType; // 標誌類型
    private final boolean getHit; // 表演扣血
    private final double score; // 表演得分
    private final int multiplier; // 表演倍數
    private final int beforeLifePoint; // 起始血量
    private final int afterLifePoint; // 結束血量
    private final List<ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava> otherIcons;

    public DgryBonusGameDisplayType(){
        this.outcomeType = null;
        this.iconType = null;
        this.getHit = true;
        this.score = 0;
        this.multiplier = 0;
        this.beforeLifePoint = 0;
        this.afterLifePoint = 0;
        this.otherIcons = null;
    }
    public DgryBonusGameDisplayType(ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava outcomeType, ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava iconType, boolean getHit, double Score, int multiplier, int beforeLifePoint, int afterLifePoint, List<ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava> otherIcons) {
        this.outcomeType = outcomeType;
        this.iconType = iconType;
        this.getHit = getHit;
        this.score = Score;
        this.multiplier = multiplier;
        this.beforeLifePoint = beforeLifePoint;
        this.afterLifePoint = afterLifePoint;
        this.otherIcons = otherIcons;
    }

    public DgryBonusGameDisplayType(ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava outcomeType, boolean getHit, double Score, int multiplier, int beforeLifePoint, int afterLifePoint, List<ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava> otherIcons) {
        this.outcomeType = outcomeType;
        this.iconType = outcomeType;
        this.getHit = getHit;
        this.score = Score;
        this.multiplier = multiplier;
        this.beforeLifePoint = beforeLifePoint;
        this.afterLifePoint = afterLifePoint;
        this.otherIcons = otherIcons;
    }

    public ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava getOutcomeType() { return outcomeType; }
    public ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava getIconType() { return iconType; }
    public boolean isGetHit() { return getHit; }
    public double getScore() { return score; }
    public int getMultiplier() { return multiplier; }
    public int getBeforeLifePoint() { return beforeLifePoint; }
    public int getAfterLifePoint() { return afterLifePoint; }
    public List<ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava> getOtherIcons() { return otherIcons; }
}
