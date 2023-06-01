package org.lsj.gs.math.core.common.table.entity.message.fish;

import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.ClientHitResult;

// 服務端給客端的打擊結果資訊
public class StcGetHitResultData {
    private final int fishSeq; // 序號
    private final GamePlayerResult gamePlayerResult; // 遊戲玩家結果
    private final ClientHitResult hitResult; // 打擊結果

    // 原始建構子提供JSON解析用
    public StcGetHitResultData() {
        this.fishSeq = -1;
        this.gamePlayerResult = null;
        this.hitResult = null;
    }

    public StcGetHitResultData(int fishSeq, GamePlayerResult gamePlayerResult, ClientHitResult hitResult) {
        this.fishSeq = fishSeq;
        this.gamePlayerResult = gamePlayerResult;
        this.hitResult = hitResult;
    }

    public int getFishSeq() {
        return fishSeq;
    }

    public GamePlayerResult getGamePlayerResult() {
        return gamePlayerResult;
    }

    public ClientHitResult getHitResult() {
        return hitResult;
    }
}
