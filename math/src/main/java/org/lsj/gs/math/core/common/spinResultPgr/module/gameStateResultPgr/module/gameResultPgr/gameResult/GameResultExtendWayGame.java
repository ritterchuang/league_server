package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.gameResult;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.gameResult.winResult.WayWinResult;
import org.lsj.gs.math.core.slot.ConstMathSlot;

import java.util.ArrayList;
import java.util.List;

// 客端客製算分額外結果
public class GameResultExtendWayGame extends GameResultExtend {
    private final List<WayWinResult> wayWinResultList; // 路贏分結果列表

    public GameResultExtendWayGame(ConstMathSlot.GameHitDirectionType gameHitDirectionType) {
        super(gameHitDirectionType);
        this.wayWinResultList = new ArrayList<>();
    }

    public GameResultExtendWayGame(ConstMathSlot.GameHitDirectionType gameHitDirectionType, List<WayWinResult> wayWinResultList) {
        super(gameHitDirectionType);
        this.wayWinResultList = wayWinResultList;
    }
}
