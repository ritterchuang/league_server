package org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase;

// 客製測項設定 魚機指定打擊方式 擊殺判斷
public class TestCaseConfigEFishSpecifyHitKillFlag extends AbstractTestCaseConfigExtend {
    private final int bulletId; // 子彈識別碼
    private final int targetId; // 目標識別碼
    private final boolean killFlag; // 擊殺標誌

    public TestCaseConfigEFishSpecifyHitKillFlag(int bulletId, int targetId, boolean killFlag) {
        this.bulletId = bulletId;
        this.targetId = targetId;
        this.killFlag = killFlag;
    }

    public int getBulletId() {
        return bulletId;
    }

    public int getTargetId() {
        return targetId;
    }

    public boolean isKillFlag() {
        return killFlag;
    }

    @Override
    public String systemPrintString() {
        return this.bulletId + "_" +
                this.targetId + "_" +
                ((this.killFlag) ? 1 : 0);
    }
}
