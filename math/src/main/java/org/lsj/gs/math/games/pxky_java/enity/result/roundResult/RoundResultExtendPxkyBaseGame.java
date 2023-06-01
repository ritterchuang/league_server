package org.lsj.gs.math.games.pxky_java.enity.result.roundResult;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.gameResult.GameResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundResult.RoundResultExtend;

import java.util.List;

// 客端客製遊戲局額外結果豼貅開運基礎遊戲
public class RoundResultExtendPxkyBaseGame extends RoundResultExtend {
    private final List<Integer> reSpinStrip; // 重轉輪帶表
    private final int reSpinStopIndex; // 重轉停輪位置
    private final GameResult reSpinGameResult; // 重轉算分結果

    public RoundResultExtendPxkyBaseGame(double totalWin, List<Integer> reSpinStrip, int reSpinStopIndex, GameResult reSpinGameResult) {
        super(totalWin);
        this.reSpinStrip = reSpinStrip;
        this.reSpinStopIndex = reSpinStopIndex;
        this.reSpinGameResult = reSpinGameResult;
    }

    public double getTotalWin() {
        return totalWin;
    }

    public List<Integer> getReSpinStrip() {
        return reSpinStrip;
    }

    public int getReSpinStopIndex() {
        return reSpinStopIndex;
    }

    public GameResult getReSpinGameResult() {
        return reSpinGameResult;
    }
}
