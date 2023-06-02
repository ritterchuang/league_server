package org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.playTypeBaiRenConfigExtend;

// 遊玩模式百人額外設定
public class PlayTypeBaiRenConfigExtend {
    private final int maxBetCount; // 最大下注次數

    public PlayTypeBaiRenConfigExtend(int maxBetCount) {
        this.maxBetCount = maxBetCount;
    }

    public int getMaxBetCount() {
        return maxBetCount;
    }
}
