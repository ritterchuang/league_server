package org.lsj.gs.mathStr.core.module.stn;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultCascade;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.line.GameCtrResultExtendLine;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.way.GameCtrResultExtendWay;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.slot.GameResultExtendSlotJava;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 基礎統計物件 消除
public class TemplateStnSlotCascade extends TemplateStnSlot implements IStn {

    private int totalCascadeTimesPerGameState; // 總消除次數

    private double firstCascadeWin; // 第一次消除得分
    private Map<Integer, Map<Integer, Integer>> firstCascadePayComboIdToHitNumberCountMap; // 第一次賠率表ID與連線數次數對應表 [payComboId][hitNumber] = count
    private Map<Integer, Map<Integer, Double>> firstCascadePayComboIdToHitNumberWinMap; // 第一次賠率表ID與連線數得分對應表 [payComboId][hitNumber] = win

    private double leftCascadeWin; // 剩餘消除得分
    private Map<Integer, Map<Integer, Integer>> leftCascadePayComboIdToHitNumberCountMap; // 剩餘賠率表ID與連線數次數對應表 [payComboId][hitNumber] = count
    private Map<Integer, Map<Integer, Double>> leftCascadePayComboIdToHitNumberWinMap; // 剩餘賠率表ID與連線數得分對應表 [payComboId][hitNumber] = win


    public TemplateStnSlotCascade() {
        super();
        this.totalCascadeTimesPerGameState = 0;

        this.firstCascadeWin = 0.0;
        this.firstCascadePayComboIdToHitNumberCountMap = new HashMap<>();
        this.firstCascadePayComboIdToHitNumberWinMap = new HashMap<>();

        this.leftCascadeWin = 0.0;
        this.leftCascadePayComboIdToHitNumberCountMap = new HashMap<>();
        this.leftCascadePayComboIdToHitNumberWinMap = new HashMap<>();
    }

    // 更新統計資訊
    public void update(BoardGtrResult boardGtrResult, int gameStateIndex) {
        // 1. 更新父類別統計資訊
        super.update(boardGtrResult, gameStateIndex);

        // 2. 更新虎機得分資訊
        this.updateCascadeGameResult(boardGtrResult, gameStateIndex);
    }

    // 更新虎機得分資訊 TODO 僅先做 way
    private void updateCascadeGameResult(BoardGtrResult boardGtrResult, int gameStateIndex) {
        List<RoundHlrResult> roundHlrResultList = ((GameResultExtendSlotJava)boardGtrResult.getGameResultExtend()).getGameFlowHlrResult().getGameStateHlrResultList().get(gameStateIndex).getRoundHlrResultList();

        for (int roundIndex = 0; roundIndex < roundHlrResultList.size(); roundIndex++) {
            RoundHlrResultCascade roundHlrResultCascade = (RoundHlrResultCascade) roundHlrResultList.get(roundIndex);
            List<CascadeHlrResult> cascadeHlrResultList = roundHlrResultCascade.getCascadeClusterHlrResult().getCascadeHlrResultList();

            // 更新消除次數
            this.totalCascadeTimesPerGameState += cascadeHlrResultList.size();

            for (int cascadeIndex = 0; cascadeIndex < cascadeHlrResultList.size(); cascadeIndex++) {
                if(cascadeHlrResultList.get(cascadeIndex).getGameCtrResult().getGameHitType().equals(ConstMathSlot.GameHitType.LINE_GAME)) {
                    GameCtrResultExtendLine gameCtrResultExtendLine = (GameCtrResultExtendLine) cascadeHlrResultList.get(cascadeIndex).getGameCtrResult().getGameCtrResultExtend();

                    // 更新所有得分資訊
                    super.updateLineGame(gameCtrResultExtendLine, super.payComboIdToHitNumberCountMap, super.payComboIdToHitNumberWinMap);

                    // 更新第一次消除資訊
                    this.updateFirstCascadeLineGameResult(cascadeHlrResultList.get(cascadeIndex), cascadeIndex);

                    // 更新之後消除資訊
                    this.updateLeftCascadeLineGameResult(cascadeHlrResultList.get(cascadeIndex), cascadeIndex);

                } else {
                    GameCtrResultExtendWay gameCtrResultExtendWay = (GameCtrResultExtendWay) cascadeHlrResultList.get(cascadeIndex).getGameCtrResult().getGameCtrResultExtend();

                    // 更新所有得分資訊
                    super.updateWayGame(gameCtrResultExtendWay, super.payComboIdToHitNumberCountMap, super.payComboIdToHitNumberWinMap);

                    // 更新第一次消除資訊
                    this.updateFirstCascadeWayGameResult(cascadeHlrResultList.get(cascadeIndex), cascadeIndex);

                    // 更新之後消除資訊
                    this.updateLeftCascadeWayGameResult(cascadeHlrResultList.get(cascadeIndex), cascadeIndex);
                }
            }

        }
    }

    // 更新第一次消除資訊 Way
    private void updateFirstCascadeWayGameResult(CascadeHlrResult cascadeHlrResult, int cascadeIndex) {
        if (cascadeIndex == 0) {
            GameCtrResultExtendWay gameCtrResultExtendWay = (GameCtrResultExtendWay) cascadeHlrResult.getGameCtrResult().getGameCtrResultExtend();
            this.firstCascadeWin += cascadeHlrResult.getTotalWin();
            super.updateWayGame(gameCtrResultExtendWay, this.firstCascadePayComboIdToHitNumberCountMap, this.firstCascadePayComboIdToHitNumberWinMap);
        }
    }

    // 更新第一次消除資訊 Line
    private void updateFirstCascadeLineGameResult(CascadeHlrResult cascadeHlrResult, int cascadeIndex) {
        if (cascadeIndex == 0) {
            GameCtrResultExtendLine gameCtrResultExtendLine = (GameCtrResultExtendLine) cascadeHlrResult.getGameCtrResult().getGameCtrResultExtend();
            this.firstCascadeWin += cascadeHlrResult.getTotalWin();
            super.updateLineGame(gameCtrResultExtendLine, this.firstCascadePayComboIdToHitNumberCountMap, this.firstCascadePayComboIdToHitNumberWinMap);
        }
    }

    // 更新之後消除資訊 Way
    private void updateLeftCascadeWayGameResult(CascadeHlrResult cascadeHlrResult, int cascadeIndex) {
        if (cascadeIndex != 0) {
            GameCtrResultExtendWay gameCtrResultExtendWay = (GameCtrResultExtendWay) cascadeHlrResult.getGameCtrResult().getGameCtrResultExtend();
            this.leftCascadeWin += cascadeHlrResult.getTotalWin();
            super.updateWayGame(gameCtrResultExtendWay, this.leftCascadePayComboIdToHitNumberCountMap, this.leftCascadePayComboIdToHitNumberWinMap);
        }
    }

    // 更新之後消除資訊 Line
    private void updateLeftCascadeLineGameResult(CascadeHlrResult cascadeHlrResult, int cascadeIndex) {
        if (cascadeIndex != 0) {
            GameCtrResultExtendLine gameCtrResultExtendLine = (GameCtrResultExtendLine) cascadeHlrResult.getGameCtrResult().getGameCtrResultExtend();
            this.leftCascadeWin += cascadeHlrResult.getTotalWin();
            super.updateLineGame(gameCtrResultExtendLine, this.leftCascadePayComboIdToHitNumberCountMap, this.leftCascadePayComboIdToHitNumberWinMap);
        }
    }


    public int getTotalCascadeTimesPerGameState() {
        return totalCascadeTimesPerGameState;
    }

    public double getFirstCascadeWin() {
        return firstCascadeWin;
    }

    public Map<Integer, Map<Integer, Integer>> getFirstCascadePayComboIdToHitNumberCountMap() {
        return firstCascadePayComboIdToHitNumberCountMap;
    }

    public Map<Integer, Map<Integer, Double>> getFirstCascadePayComboIdToHitNumberWinMap() {
        return firstCascadePayComboIdToHitNumberWinMap;
    }

    public double getLeftCascadeWin() {
        return leftCascadeWin;
    }

    public Map<Integer, Map<Integer, Integer>> getLeftCascadePayComboIdToHitNumberCountMap() {
        return leftCascadePayComboIdToHitNumberCountMap;
    }

    public Map<Integer, Map<Integer, Double>> getLeftCascadePayComboIdToHitNumberWinMap() {
        return leftCascadePayComboIdToHitNumberWinMap;
    }
}
