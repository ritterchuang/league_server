package com.lx.gs.mathStr.core.module.stn;

import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultNormal;
import com.lx.gs.math.core.slot.ConstMathSlot;
import com.lx.gs.math.core.slot.gameCtrMgr.enity.result.line.GameCtrResultExtendLine;
import com.lx.gs.math.core.slot.gameCtrMgr.enity.result.way.GameCtrResultExtendWay;
import com.lx.gs.mathStr.core.entity.playStr.BoardGtrResult;
import com.lx.gs.mathStr.core.entity.playStr.gameResultExtend.slot.GameResultExtendSlotJava;

import java.util.List;

// 基礎統計物件 一般
public class TemplateStnSlotNormal extends TemplateStnSlot implements IStn {

    public TemplateStnSlotNormal() {
        super();
    }

    // 更新統計資訊
    public void update(BoardGtrResult boardGtrResult, int gameStateIndex) {
        // 1. 更新父類別統計資訊
        super.update(boardGtrResult, gameStateIndex);

        // 2. 更新虎機得分資訊
        this.updateGameResult(boardGtrResult, gameStateIndex);
    }

    // 更新虎機得分資訊 TODO 僅先做 way
    private void updateGameResult(BoardGtrResult boardGtrResult, int gameStateIndex) {
        List<RoundHlrResult> roundHlrResultList = ((GameResultExtendSlotJava)boardGtrResult.getGameResultExtend()).getGameFlowHlrResult().getGameStateHlrResultList().get(gameStateIndex).getRoundHlrResultList();

        for (int roundIndex = 0; roundIndex < roundHlrResultList.size(); roundIndex++) {
            RoundHlrResultNormal roundHlrResultNormal = (RoundHlrResultNormal) roundHlrResultList.get(roundIndex);
            if(roundHlrResultNormal.getGameCtrResult().getGameHitType().equals(ConstMathSlot.GameHitType.LINE_GAME)){
                super.updateLineGame((GameCtrResultExtendLine) roundHlrResultNormal.getGameCtrResult().getGameCtrResultExtend(), super.payComboIdToHitNumberCountMap, super.payComboIdToHitNumberWinMap);
            }else {
                super.updateWayGame((GameCtrResultExtendWay) roundHlrResultNormal.getGameCtrResult().getGameCtrResultExtend(), super.payComboIdToHitNumberCountMap, super.payComboIdToHitNumberWinMap);
            }
        }
    }

}
