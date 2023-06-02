package org.lsj.gs.mathStr.core.entity.playStr;

public class StnResult {
    private final int gameIndex; // 遊戲識別碼
    private final int fieldIndex; // 房間識別碼
    private final int userIndex; // 玩家識別碼
    private final int poolStatisticTypeIndex; // 水池統計類型

    public StnResult(int gameIndex, int fieldIndex, int userIndex, int poolStatisticTypeIndex) {
        this.gameIndex = gameIndex;
        this.fieldIndex = fieldIndex;
        this.userIndex = userIndex;
        this.poolStatisticTypeIndex = poolStatisticTypeIndex;
    }

    public int getGameIndex() {
        return gameIndex;
    }

    public int getFieldIndex() {
        return fieldIndex;
    }

    public int getPlayerIndex() {
        return userIndex;
    }

    public int getPoolStatisticTypeIndex() {
        return poolStatisticTypeIndex;
    }
}
