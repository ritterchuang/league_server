package org.lsj.gs.math.games.xjtb_java.module.gameStateHlr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfig;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultNormal;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.roundResultExtend.RoundHlrResultExtend;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.roundResultExtend.RoundHlrResultExtendDefault;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.AbstractGameStateHlr;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.IGameStateHlr;
import org.lsj.gs.math.core.common.logic.logicSlot.ILogicSlot;
import org.lsj.gs.math.core.common.logic.logicSlot.LogicSlotNormal;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtilSlot;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtrResult;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.commonInputHlr.enity.result.CommonInputResult;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.GameCtrResult;
import org.lsj.gs.math.core.slot.gameStateInputHlrMgr.enity.result.GameStateInputResult;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.RoundConfigNormal;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.roundNormalGameConfig.RoundNormalGameConfigExtendXjtbFreeGame;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResultExtendTriggerRound;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResultUnionCluster;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;
import org.lsj.gs.math.games.xjtb_java.entity.config.GameStateConfigExtendXjtbFreeGame;
import org.lsj.gs.math.games.xjtb_java.entity.result.gameStateResult.GameStateHlrResultExtendXjtbBaseGame;
import org.lsj.gs.math.games.xjtb_java.entity.result.roundResult.RoundHlrResultExtendXjtbFreeGame;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.List;

// 仙境探寶免費遊戲處理者
public class GameStateHlrXjtbFreeGame extends AbstractGameStateHlr implements IGameStateHlr {
    private final LogicSlotNormal logic; // 牌桌邏輯
    private final GameStateConfigExtendXjtbFreeGame gameStateConfigExtend; // 遊戲狀態額外設定
    private final RoundNormalGameConfigExtendXjtbFreeGame roundConfigExtend; // 遊戲場次額外設定

    public GameStateHlrXjtbFreeGame(int conditionId, int gameStateId, ITableUtilSlot tableUtil, ILogicSlot logic, GameStateConfig gameStateConfig) {
        super(conditionId, gameStateId, gameStateConfig, tableUtil);
        this.logic = (LogicSlotNormal) logic;
        this.gameStateConfigExtend = (GameStateConfigExtendXjtbFreeGame) gameStateConfig.getGameStateConfigExtend();
        this.roundConfigExtend = (RoundNormalGameConfigExtendXjtbFreeGame) ((RoundConfigNormal)gameStateConfig.getRoundConfig()).getRoundNormalGameConfigExtend();
    }

    // 計算遊戲狀態結果
    @Override
    public GameStateHlrResult calculateGameStateHlrResult(int gameStateResultIndex, ConstMathSlot.ReelRtpType reelRtpType, SpinRequest spinRequest, GameStateHlrResult beforeGameStateHlrResult) {
        ArrayList<RoundHlrResult> roundHlrResultList = new ArrayList<>();

        // 1. 設定初始場次
        int roundCount = this.logic.getDefaultRound(super.conditionId, beforeGameStateHlrResult);

        for (int roundIndex = 0; roundIndex < roundCount; roundIndex++) {
            // 2. 產出畫面結果
            ScreenGtrResult screenGtrResult = this.logic.getScreenGtrResult(super.conditionId, reelRtpType);

            // 3. 計算倍數矩陣
            List<List<Integer>> multiplierMatrix = this.calculateMultiplierMatrix(screenGtrResult.getScreenSymbol());

            // 4. 計算特殊事件結果
            SpecialFeatureHlrResultCluster specialFeatureHlrResultCluster = this.logic.getSpecialFeatureHlrResultCluster(super.conditionId, spinRequest, screenGtrResult);

            // 5. 計算畫面算分結果
            GameCtrResult gameCtrResult = this.logic.getGameCtrResultWithScreenMultiplier(super.conditionId, spinRequest, screenGtrResult, this.calculateScreenMultiplier(multiplierMatrix));

            // 6. 計算 局額外資訊
            RoundHlrResultExtend roundHlrResultExtend = this.calculateRoundHlrResultExtend(gameCtrResult, multiplierMatrix);

            // 7. 計算 局額外類型
            ConstMathSlot.RoundNormalGameType roundNormalGameType = super.calculateRoundNormalGameType(roundHlrResultExtend);

            // 8. 計算進度
            ProgressHlrResult progressHlrResult = this.logic.getProgressHlrResult(super.conditionId, roundIndex, beforeGameStateHlrResult, roundHlrResultList, specialFeatureHlrResultCluster);

            // 9. 計算聽牌結果
            ReadyHandHlrResultUnionCluster readyHandHlrResultUnionCluster = this.logic.getReadyHandHlrResultUnionCluster(super.conditionId, screenGtrResult.getScreenSymbol(), progressHlrResult);

            // 10. 更新場次
            roundCount = this.calculateTotalRound(roundCount, progressHlrResult);

            // 11. 計算總得分[ 表演池倍數給分 ]
            double totalWin = super.calculateRoundTotalWin(gameCtrResult, specialFeatureHlrResultCluster, roundHlrResultExtend);

            // 12. 計算累積得分結果
            AccumulateWinCtrResult accumulateWinCtrResult = this.logic.getAccumulateWinCtrResult(roundIndex, totalWin, beforeGameStateHlrResult, roundHlrResultList);

            // 13. 封裝 局結果
            RoundHlrResultNormal roundResultNormal = new RoundHlrResultNormal(roundIndex,
                    MathUtil.moneyScale(totalWin),
                    specialFeatureHlrResultCluster,
                    progressHlrResult,
                    readyHandHlrResultUnionCluster,
                    accumulateWinCtrResult,
                    screenGtrResult,
                    gameCtrResult,
                    roundNormalGameType,
                    roundHlrResultExtend
                    );

            // 14. 加入 局結果
            roundHlrResultList.add(roundResultNormal);
        }

        // 15. 計算共同輸入結果
        CommonInputResult commonInputResult = this.logic.getCommonInputResult(roundHlrResultList);

        // 16. 計算遊戲輸入結果
        GameStateInputResult gameStateInputResult = this.logic.getGameStateInputResult(super.conditionId, roundHlrResultList);

        // 17. 回傳 遊戲狀態結果
        return new GameStateHlrResult(
                super.conditionId,
                gameStateResultIndex,
                super.gameStateId,
                MathUtil.moneyScale(super.calculateStateTotalWin(roundHlrResultList)),
                super.config.getGameStateType(),
                new GameStateHlrResultExtendXjtbBaseGame(),
                super.config.getRoundType(),
                roundHlrResultList,
                commonInputResult,
                gameStateInputResult);
    }

    // 計算畫面可能倍數
    private List<List<Integer>> calculateMultiplierMatrix(List<List<Integer>> screenSymbol) {
        // 1. 宣告空間
        List<List<Integer>> result = new ArrayList<>();

        // 2. 掃畫面，計算倍數
        for (int columnIndex = 0; columnIndex < screenSymbol.size(); columnIndex++) {
            // 2.1 宣告每欄空間
            List<Integer> multiplierListPerColumn = new ArrayList<>();

            for (int rowIndex = 0; rowIndex < screenSymbol.get(columnIndex).size(); rowIndex++) {
                // 2.2 取得畫面標誌
                int symbolId = screenSymbol.get(columnIndex).get(rowIndex);

                // 2.3 計算倍數
                if (symbolId == this.roundConfigExtend.getTargetSymbolId()) {
                    int multiplierIndex = super.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(this.roundConfigExtend.getWeightList().get(columnIndex), ConstMathCommon.AccuracyType.MILLION);
                    multiplierListPerColumn.add(this.roundConfigExtend.getMultiplierList().get(columnIndex).get(multiplierIndex));
                }else {
                    multiplierListPerColumn.add(0);
                }
            }

            result.add(multiplierListPerColumn);
        }

        // 3. 回傳
        return result;
    }

    // 計算得分額外倍數
    private int calculateScreenMultiplier(List<List<Integer>> multiplierMatrix) {
        int result = 1;

        if (multiplierMatrix.isEmpty()) {
            return result;
        }

        for (int columnIndex = 0; columnIndex < multiplierMatrix.size(); columnIndex++) {
            for (int rowIndex = 0; rowIndex < multiplierMatrix.get(columnIndex).size(); rowIndex++) {
                if (multiplierMatrix.get(columnIndex).get(rowIndex) > 0) {
                    result = result * multiplierMatrix.get(columnIndex).get(rowIndex);
                }
            }
        }

        return result;
    }

    // 計算局額外結果
    private RoundHlrResultExtend calculateRoundHlrResultExtend(GameCtrResult gameCtrResult, List<List<Integer>> multiplierMatrix) {
        // 1. 無中獎畫面，回傳預設
        if (gameCtrResult.getTotalWin() == 0) {
            return new RoundHlrResultExtendDefault();
        }

        // 2. 無倍數，回傳預設
        if (multiplierMatrix.stream().flatMap(List::stream).mapToInt(x -> x).sum() == 0) {
            return new RoundHlrResultExtendDefault();
        }

        // 3. 回傳
        return new RoundHlrResultExtendXjtbFreeGame(multiplierMatrix);
    }

    // 計算總場次
    private int calculateTotalRound(int currentTotalRound, ProgressHlrResult progressHlrResult) {
        ProgressHlrResultExtendTriggerRound progressExtendResult = (ProgressHlrResultExtendTriggerRound) progressHlrResult.getProgressHlrResultExtend();

        return currentTotalRound + progressExtendResult.getRoundProgress().getAddRound();
    }
}
