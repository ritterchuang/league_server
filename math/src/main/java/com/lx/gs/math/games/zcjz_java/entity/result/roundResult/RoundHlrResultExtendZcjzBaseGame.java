package com.lx.gs.math.games.zcjz_java.entity.result.roundResult;

import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.roundResultExtend.RoundHlrResultExtend;
import com.lx.gs.math.core.slot.gameCtrMgr.enity.result.GameCtrResult;
import com.lx.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;

import java.util.List;

// 客製遊戲局額外結果招財金豬基礎遊戲
public class RoundHlrResultExtendZcjzBaseGame extends RoundHlrResultExtend {
    private final List<Integer> reSpinStrip; // 重轉輪帶表
    private final ScreenGtrResult screenGtrResult; // 重轉畫面
    private final GameCtrResult reSpinGameCtrResult; // 重轉算分結果

    public RoundHlrResultExtendZcjzBaseGame(double totalWin, List<Integer> reSpinStrip, ScreenGtrResult screenGtrResult, GameCtrResult reSpinGameCtrResult) {
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
