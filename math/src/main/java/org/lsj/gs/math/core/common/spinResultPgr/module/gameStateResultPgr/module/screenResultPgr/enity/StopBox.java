package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity;

// 停止物件
public class StopBox {
    private final int positionId; // 座標ID
    private final double offset; // 偏移量

    public StopBox(int positionId, double offset) {
        this.positionId = positionId;
        this.offset = offset;
    }

    public int getPositionId() {
        return positionId;
    }

    public double getOffset() {
        return offset;
    }
}
