package org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 客製測項設定 消除虎機 連線數與畫面測項
public class TestCaseConfigESlotCascadeWayGameAndScreen extends AbstractTestCaseConfigExtend {
    private final int payComboId; // 賠率表識別碼
    private final int hitNumber; // 連線數
    private final int targetSymbolId; // 目標標誌
    private final int targetColumnIndex; // 目標軸

    private final ConstMathSlot.GameStateType gameStateType; // 遊戲狀態類型

    public TestCaseConfigESlotCascadeWayGameAndScreen(int payComboId, int hitNumber, int targetSymbolId, int targetColumnIndex, ConstMathSlot.GameStateType gameStateType) {
        this.payComboId = payComboId;
        this.hitNumber = hitNumber;
        this.targetSymbolId = targetSymbolId;
        this.targetColumnIndex = targetColumnIndex;
        this.gameStateType = gameStateType;
    }

    public int getPayComboId() {
        return payComboId;
    }

    public int getHitNumber() {
        return hitNumber;
    }

    public int getTargetSymbolId() {
        return targetSymbolId;
    }

    public ConstMathSlot.GameStateType getGameStateType() {
        return gameStateType;
    }

    public int getTargetColumnIndex() {
        return targetColumnIndex;
    }

    @Override
    public String systemPrintString() {
        return this.payComboId + "_" +
                this.hitNumber + "_" +
                this.targetSymbolId + "_" +
                this.targetColumnIndex + "_" +
                this.gameStateType.name();
    }
}
