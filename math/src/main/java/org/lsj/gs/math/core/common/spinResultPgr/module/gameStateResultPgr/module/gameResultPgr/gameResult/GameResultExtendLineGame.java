package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.gameResult;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.gameResult.winResult.LineWinResult;
import org.lsj.gs.math.core.slot.ConstMathSlot;

import java.util.ArrayList;
import java.util.List;

// 客端客製算分額外結果
public class GameResultExtendLineGame extends GameResultExtend {
    private final List<LineWinResult> lineWinResultList; // 路贏分結果列表

    public GameResultExtendLineGame(ConstMathSlot.GameHitDirectionType gameHitDirectionType) {
        super(gameHitDirectionType);
        this.lineWinResultList = new ArrayList<>();
    }

    public GameResultExtendLineGame(ConstMathSlot.GameHitDirectionType gameHitDirectionType, List<LineWinResult> lineWinResultList) {
        super(gameHitDirectionType);
        this.lineWinResultList = lineWinResultList;
    }

    public List<LineWinResult> getLineWinResultList() {
        return lineWinResultList;
    }
}
