package org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.playTypeFishConfigExtend;

// 目標資訊
public class TargetInfo {
    private int targetIndex; // 目標代碼
    private int weight; // 權重

    public TargetInfo() {
    }

    public TargetInfo(int targetIndex, int weight) {
        this.targetIndex = targetIndex;
        this.weight = weight;
    }

    public int getTargetIndex() {
        return targetIndex;
    }

    public int getWeight() {
        return weight;
    }
}
