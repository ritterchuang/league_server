package org.lsj.gs.math.core.common.table.entity.message.slot;

import org.lsj.gs.math.core.common.spinResultPgr.enity.SpinResult;
import org.lsj.gs.math.core.common.table.entity.message.fish.GamePlayerResult;

// 服務端給客端的打擊結果資訊
public class StcGetSpinResultData {
    private final GamePlayerResult afterSpinBetGamePlayerResult; // 下注後，玩家結果
    private final GamePlayerResult afterSpinResultGamePlayerResult; // 整局結束後，遊戲玩家結果
    private final SpinResult spinResult; // spin結果
    private final double playerBet; // 玩家算分總投注

    // 原始建構子提供JSON解析用
    public StcGetSpinResultData() {
        this.afterSpinBetGamePlayerResult = null;
        this.afterSpinResultGamePlayerResult = null;
        this.spinResult = null;
        this.playerBet = 0;
    }

    public StcGetSpinResultData(GamePlayerResult afterSpinBetGamePlayerResult, GamePlayerResult afterSpinResultGamePlayerResult, SpinResult spinResult, double playerBet) {
        this.afterSpinBetGamePlayerResult = afterSpinBetGamePlayerResult;
        this.afterSpinResultGamePlayerResult = afterSpinResultGamePlayerResult;
        this.spinResult = spinResult;
        this.playerBet = playerBet;
    }
}
