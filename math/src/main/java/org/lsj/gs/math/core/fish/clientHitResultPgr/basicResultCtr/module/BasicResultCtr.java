package org.lsj.gs.math.core.fish.clientHitResultPgr.basicResultCtr.module;

import org.lsj.gs.math.core.fish.clientHitResultPgr.basicResultCtr.enity.BasicResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;

// 基礎結果運算者
public class BasicResultCtr implements IBasicResultCtr{

    // 計算打擊魚基本結果
    @Override
    public BasicResult calculateBasicResult(HitResult hitResult) {
        // 1. 取得 flag
        boolean killFlag = hitResult.isKillFlag();

        // 2. 取得魚擊殺次數
        int killCount = hitResult.getKillCount();

        // 3. 取得魚基本分數
        double basicWin = this.calculateBasicWin(hitResult);

        // 4. 回傳
        return new BasicResult(killFlag, killCount, basicWin);
    }

    // 計算基本贏分
    private double calculateBasicWin(HitResult hitResult) {
        switch (hitResult.getHitType()) {
            case FIXED_ODDS: return hitResult.getBasicWin();
            case RANDOM_ODDS: return hitResult.getBasicWin();
            default: return 0.0;
        }
    }
}
