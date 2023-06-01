package org.lsj.gs.math.games.xzcq_java.entity.result.roundResult;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.roundResultExtend.RoundHlrResultExtend;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.GameCtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;

import java.util.List;

// 客製遊戲局額外結果小豬傳奇基礎遊戲
public class RoundHlrResultExtendXzcqBaseGame extends RoundHlrResultExtend {
    private final List<Integer> reSpinStrip; // 重轉輪帶表
    private final ScreenGtrResult screenGtrResult; // 重轉畫面
    private final GameCtrResult reSpinGameCtrResult; // 重轉算分結果

    public RoundHlrResultExtendXzcqBaseGame(double totalWin, List<Integer> reSpinStrip, ScreenGtrResult screenGtrResult, GameCtrResult reSpinGameCtrResult) {
        super(totalWin);
        this.reSpinStrip = reSpinStrip;
        this.screenGtrResult = screenGtrResult;
        this.reSpinGameCtrResult = reSpinGameCtrResult;
    }

    public List<Integer> getReSpinStrip() {
        return reSpinStrip;
    }

    public ScreenGtrResult getScreenGtrResult() {
        return screenGtrResult;
    }

    public GameCtrResult getReSpinGameCtrResult() {
        return reSpinGameCtrResult;
    }
}
