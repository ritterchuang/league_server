package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.gameResult;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.gameResult.winResult.ConnectWinResult;
import org.lsj.gs.math.core.slot.ConstMathSlot;

import java.util.ArrayList;
import java.util.List;

// 客端客製算分額外結果
public class GameResultExtendConnectGame extends GameResultExtend {
    private final List<ConnectWinResult> connectWinResultList; // 路贏分結果列表

    public GameResultExtendConnectGame(ConstMathSlot.GameHitDirectionType gameHitDirectionType) {
        super(gameHitDirectionType);
        this.connectWinResultList = new ArrayList<>();
    }

    public GameResultExtendConnectGame(ConstMathSlot.GameHitDirectionType gameHitDirectionType, List<ConnectWinResult> connectWinResultList) {
        super(gameHitDirectionType);
        this.connectWinResultList = connectWinResultList;
    }

    public List<ConnectWinResult> getConnectWinResultList() {
        return connectWinResultList;
    }
}
