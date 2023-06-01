package org.lsj.gs.mathStr.core.module.stn;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.line.GameCtrResultExtendLine;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.line.LineCtrWinResult;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.way.GameCtrResultExtendWay;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.way.WayCtrWinResult;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResult;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.slot.GameResultExtendSlotJava;
import org.lsj.utils.MathUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 抽象基礎統計物件
public class TemplateStnSlot extends TemplateStn implements IStn {
    protected double totalWin; // 總得分
    protected int totalRoundPerGameState; // 狀態累積局數
    protected double totalOdds; // 倍率總和
    protected double totalOddsSquare; // 倍率平方和
    protected double maxOdds; // 最大倍率
    protected Map<Integer, Map<Integer, Integer>> payComboIdToHitNumberCountMap; // 賠率表ID與連線數次數對應表 [payComboId][hitNumber] = count
    protected Map<Integer, Map<Integer, Double>> payComboIdToHitNumberWinMap; // 賠率表ID與連線數得分對應表 [payComboId][hitNumber] = win

    protected Map<ConstMathSlot.SpecialFeatureType, Integer> specialFeatureTypeToCountMap; // 特殊事件次數對應表 [specialFeatureType] = count
    protected Map<ConstMathSlot.SpecialFeatureType, Double> specialFeatureTypeToWinMap; // 特殊事件得分對應表 [specialFeatureType] = win

    public TemplateStnSlot() {
        super();
        this.totalWin = 0.0;
        this.totalRoundPerGameState = 0;
        this.payComboIdToHitNumberCountMap = new HashMap<>();
        this.payComboIdToHitNumberWinMap = new HashMap<>();
        this.specialFeatureTypeToCountMap = new HashMap<>();
        this.specialFeatureTypeToWinMap = new HashMap<>();
    }

    // 更新統計資訊
    public void update(BoardGtrResult boardGtrResult, int gameStateIndex) {
        // 1. 更新父類別統計資訊
        super.update(boardGtrResult);
        super.updateTie(boardGtrResult);

        // 2. 更新虎機共用統計資訊
        GameResultExtendSlotJava gameResultExtendSlotJava = (GameResultExtendSlotJava) boardGtrResult.getGameResultExtend();

        // 3. 取得對應 gameState
        GameStateHlrResult gameStateHlrResult = gameResultExtendSlotJava.getGameFlowHlrResult().getGameStateHlrResultList().get(gameStateIndex);

        // 4. 計算倍率和與倍率平方和
        double odds = gameStateHlrResult.getTotalWin() / gameResultExtendSlotJava.getValidBet();
        this.totalOdds += odds;
        this.totalOddsSquare += MathUtil.multiply(odds, odds);

        // 5. 更新最大倍率
        if (odds > this.maxOdds) {
            this.maxOdds = odds;
        }

        // 6. 更新得分
        this.totalWin = MathUtil.add(this.totalWin, gameStateHlrResult.getTotalWin());

        // 7. 更新場次
        this.updateTotalRoundPerGameStateMap(gameStateHlrResult);

        // 8. 更新特殊事件資訊
        this.updateGameStateIdToSpecialFeatureTypeMap(gameStateHlrResult);
    }

    // 更新 way
    protected void updateWayGame(GameCtrResultExtendWay gameCtrResultExtendWay, Map<Integer, Map<Integer, Integer>> payComboIdToHitNumberCountMap, Map<Integer, Map<Integer, Double>> payComboIdToHitNumberWinMap) {
        // 1. 遍歷所有得分
        for (int wayWinIndex = 0; wayWinIndex < gameCtrResultExtendWay.getWayCtrWinResultList().size(); wayWinIndex++) {
            WayCtrWinResult wayCtrWinResult = gameCtrResultExtendWay.getWayCtrWinResultList().get(wayWinIndex);

            // 2. 不存在 payComboId，新增
            Map<Integer, Integer> hitNumberToCountMap;
            Map<Integer, Double> hitNumberToWinMap;
            if (payComboIdToHitNumberCountMap.containsKey(wayCtrWinResult.getPayComboId()) == false) {
                hitNumberToCountMap = new HashMap<>() {
                    {
                        put(wayCtrWinResult.getHitNumber(), 1);
                    }
                };
                hitNumberToWinMap = new HashMap<>() {
                    {
                        put(wayCtrWinResult.getHitNumber(), wayCtrWinResult.getTotalWin());
                    }
                };
            }
            else {
                hitNumberToCountMap = payComboIdToHitNumberCountMap.get(wayCtrWinResult.getPayComboId());
                hitNumberToWinMap = payComboIdToHitNumberWinMap.get(wayCtrWinResult.getPayComboId());
                // 4. 存在 payComboId，不存在 hitNumber，新增
                if (hitNumberToCountMap.containsKey(wayCtrWinResult.getHitNumber()) == false) {
                    hitNumberToCountMap.put(wayCtrWinResult.getHitNumber(), 1);
                    hitNumberToWinMap.put(wayCtrWinResult.getHitNumber(), wayCtrWinResult.getTotalWin());
                }else {
                    // 5. 存在 payComboId，存在 hitNumber，更新
                    int currentCount = hitNumberToCountMap.get(wayCtrWinResult.getHitNumber());
                    hitNumberToCountMap.put(wayCtrWinResult.getHitNumber(), currentCount + 1);

                    double currentWin = hitNumberToWinMap.get(wayCtrWinResult.getHitNumber());
                    hitNumberToWinMap.put(wayCtrWinResult.getHitNumber(), MathUtil.add(currentWin, wayCtrWinResult.getTotalWin()));
                }
            }
            payComboIdToHitNumberCountMap.put(wayCtrWinResult.getPayComboId(), hitNumberToCountMap);
            payComboIdToHitNumberWinMap.put(wayCtrWinResult.getPayComboId(), hitNumberToWinMap);
        }
    }

    // 更新 Line
    protected void updateLineGame(GameCtrResultExtendLine gameCtrResultExtendLine, Map<Integer, Map<Integer, Integer>> payComboIdToHitNumberCountMap, Map<Integer, Map<Integer, Double>> payComboIdToHitNumberWinMap) {
        // 1. 遍歷所有得分
        for (int LineWinIndex = 0; LineWinIndex < gameCtrResultExtendLine.getLineCtrWinResultList().size(); LineWinIndex++) {
            LineCtrWinResult lineCtrWinResult = gameCtrResultExtendLine.getLineCtrWinResultList().get(LineWinIndex);

            // 2. 不存在 payComboId，新增
            Map<Integer, Integer> hitNumberToCountMap;
            Map<Integer, Double> hitNumberToWinMap;
            if (payComboIdToHitNumberCountMap.containsKey(lineCtrWinResult.getPayComboId()) == false) {
                hitNumberToCountMap = new HashMap<>() {
                    {
                        put(lineCtrWinResult.getHitNumber(), 1);
                    }
                };
                hitNumberToWinMap = new HashMap<>() {
                    {
                        put(lineCtrWinResult.getHitNumber(), lineCtrWinResult.getTotalWin());
                    }
                };
            }
            else {
                hitNumberToCountMap = payComboIdToHitNumberCountMap.get(lineCtrWinResult.getPayComboId());
                hitNumberToWinMap = payComboIdToHitNumberWinMap.get(lineCtrWinResult.getPayComboId());
                // 4. 存在 payComboId，不存在 hitNumber，新增
                if (hitNumberToCountMap.containsKey(lineCtrWinResult.getHitNumber()) == false) {
                    hitNumberToCountMap.put(lineCtrWinResult.getHitNumber(), 1);
                    hitNumberToWinMap.put(lineCtrWinResult.getHitNumber(), lineCtrWinResult.getTotalWin());
                }else {
                    // 5. 存在 payComboId，存在 hitNumber，更新
                    int currentCount = hitNumberToCountMap.get(lineCtrWinResult.getHitNumber());
                    hitNumberToCountMap.put(lineCtrWinResult.getHitNumber(), currentCount + 1);

                    double currentWin = hitNumberToWinMap.get(lineCtrWinResult.getHitNumber());
                    hitNumberToWinMap.put(lineCtrWinResult.getHitNumber(), MathUtil.add(currentWin, lineCtrWinResult.getTotalWin()));
                }
            }
            payComboIdToHitNumberCountMap.put(lineCtrWinResult.getPayComboId(), hitNumberToCountMap);
            payComboIdToHitNumberWinMap.put(lineCtrWinResult.getPayComboId(), hitNumberToWinMap);
        }
    }

    // 更新特殊事件次數
    private void updateGameStateIdToSpecialFeatureTypeMap(GameStateHlrResult gameStateHlrResult) {
        // 1. 取得場次結果
        List<RoundHlrResult> roundHlrResultList = gameStateHlrResult.getRoundHlrResultList();

        // 2. 遍歷所有場次
        for (int roundIndex = 0; roundIndex < roundHlrResultList.size(); roundIndex++) {
            // 3. 取得特殊事件結果
            List<SpecialFeatureHlrResult> specialFeatureHlrResultList = roundHlrResultList.get(roundIndex).getSpecialFeatureHlrResultCluster().getSpecialFeatureHlrResultList();

            // 3.1 遍歷所有特殊事件
            for (int specialResultIndex = 0; specialResultIndex < specialFeatureHlrResultList.size(); specialResultIndex++) {
                ConstMathSlot.SpecialFeatureType specialFeatureType = specialFeatureHlrResultList.get(specialResultIndex).getSpecialFeatureType();
                if (this.specialFeatureTypeToCountMap.containsKey(specialFeatureType) == false) {
                    this.specialFeatureTypeToCountMap.put(specialFeatureType, 1);
                    this.specialFeatureTypeToWinMap.put(specialFeatureType, specialFeatureHlrResultList.get(specialResultIndex).getTotalWin());
                }else {
                    int currentCount = this.specialFeatureTypeToCountMap.get(specialFeatureType);
                    this.specialFeatureTypeToCountMap.put(specialFeatureType, currentCount + 1);

                    double currentWin = this.specialFeatureTypeToWinMap.get(specialFeatureType);
                    this.specialFeatureTypeToWinMap.put(specialFeatureType, MathUtil.add(currentWin, specialFeatureHlrResultList.get(specialResultIndex).getTotalWin()));
                }
            }
        }
    }

    // 更新場次
    private void updateTotalRoundPerGameStateMap(GameStateHlrResult gameStateHlrResult) {
        this.totalRoundPerGameState = this.totalRoundPerGameState + gameStateHlrResult.getRoundHlrResultList().size();
    }

    public double getTotalWin() {
        return totalWin;
    }

    public double getTotalOdds() {
        return totalOdds;
    }

    public double getTotalOddsSquare() {
        return totalOddsSquare;
    }

    public double getMaxOdds() {
        return maxOdds;
    }

    public int getTotalRoundPerGameState() {
        return totalRoundPerGameState;
    }

    public Map<Integer, Map<Integer, Integer>> getPayComboIdToHitNumberCountMap() {
        return payComboIdToHitNumberCountMap;
    }

    public Map<Integer, Map<Integer, Double>> getPayComboIdToHitNumberWinMap() {
        return payComboIdToHitNumberWinMap;
    }

    public Map<ConstMathSlot.SpecialFeatureType, Integer> getSpecialFeatureTypeToCountMap() {
        return specialFeatureTypeToCountMap;
    }

    public Map<ConstMathSlot.SpecialFeatureType, Double> getSpecialFeatureTypeToWinMap() {
        return specialFeatureTypeToWinMap;
    }

    // totalOdds、totalOddsSquare的getter
}
