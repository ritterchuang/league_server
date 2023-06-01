package org.lsj.gs.math.games.xzcq_java.module.gameStateHlr;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfig;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultNormal;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.roundResultExtend.RoundHlrResultExtend;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.roundResultExtend.RoundHlrResultExtendDefault;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.AbstractGameStateHlr;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.IGameStateHlr;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.IFiniteAwardPoolHlr;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.DampScreenBox;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.result.FiniteAwardPoolResultExtendBaseReSpin;
import org.lsj.gs.math.core.common.logic.logicSlot.ILogicSlot;
import org.lsj.gs.math.core.common.logic.logicSlot.LogicSlotNormal;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtilSlot;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtrResult;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.commonInputHlr.enity.result.CommonInputResult;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.GameCtrResult;
import org.lsj.gs.math.core.slot.gameStateInputHlrMgr.enity.result.GameStateInputResult;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfigExtendFix;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfigExtendReelDependent;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelStripBox;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.RoundConfigNormal;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.roundNormalGameConfig.RoundNormalGameConfigExtendXzcqBaseGame;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResultUnionCluster;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResultExtendFix;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.DampCtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.ReelCtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.ReelStopResultExtendStopByDependentReelIndex;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;
import org.lsj.gs.math.games.xzcq_java.entity.config.GameStateConfigExtendXzcqBaseGame;
import org.lsj.gs.math.games.xzcq_java.entity.result.gameStateResult.GameStateHlrResultExtendXzcqBaseGame;
import org.lsj.gs.math.games.xzcq_java.entity.result.roundResult.RoundHlrResultExtendXzcqBaseGame;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

// 小豬傳奇基礎遊戲處理者
public class GameStateHlrXzcqBaseGame extends AbstractGameStateHlr implements IGameStateHlr {
    private final LogicSlotNormal logic; // 牌桌邏輯
    private final GameStateConfigExtendXzcqBaseGame gameStateConfigExtend; // 遊戲狀態額外設定
    private final RoundNormalGameConfigExtendXzcqBaseGame roundConfigExtend; // 遊戲場次額外設定
    private final IFiniteAwardPoolHlr finiteAwardPoolHlr; // 有限獎項處理者

    public GameStateHlrXzcqBaseGame(int conditionId, int gameStateId, ITableUtilSlot tableUtil, ILogicSlot logic, IFiniteAwardPoolHlr finiteAwardPoolHlr, GameStateConfig gameStateConfig) {
        super(conditionId, gameStateId, gameStateConfig, tableUtil);
        this.logic = (LogicSlotNormal)logic;
        this.gameStateConfigExtend = (GameStateConfigExtendXzcqBaseGame) gameStateConfig.getGameStateConfigExtend();
        this.roundConfigExtend = (RoundNormalGameConfigExtendXzcqBaseGame) ((RoundConfigNormal)gameStateConfig.getRoundConfig()).getRoundNormalGameConfigExtend();
        this.finiteAwardPoolHlr = finiteAwardPoolHlr;
    }

    // 計算遊戲狀態結果
    @Override
    public GameStateHlrResult calculateGameStateHlrResult(int gameStateResultIndex, ConstMathSlot.ReelRtpType reelRtpType, SpinRequest spinRequest, GameStateHlrResult beforeGameStateHlrResult) {
        ArrayList<RoundHlrResult> roundHlrResultList = new ArrayList<>();

        // 1. 計算此次有限表演結果
        FiniteAwardPoolResultExtendBaseReSpin finiteAwardResult = ((FiniteAwardPoolResultExtendBaseReSpin)this.finiteAwardPoolHlr.calculateFiniteAwardResult(reelRtpType).getFiniteAwardPoolResultExtend());

        // 2. 設定初始場次
        int roundCount = this.logic.getDefaultRound(super.conditionId, beforeGameStateHlrResult);

        for (int roundIndex = 0; roundIndex < roundCount; roundIndex++) {
            // 3. 產出畫面結果
            ScreenGtrResult screenGtrResultBaseGame = this.calculateScreenGtrResult(finiteAwardResult.getDampScreenBoxList().get(0));

            // 4. 計算特殊事件結果
            SpecialFeatureHlrResultCluster specialFeatureHlrResultCluster = this.logic.getSpecialFeatureHlrResultCluster(super.conditionId, spinRequest, screenGtrResultBaseGame);

            // 5. 計算畫面算分結果
            GameCtrResult gameCtrResult = this.logic.getGameCtrResult(super.conditionId, spinRequest, screenGtrResultBaseGame);

            // 6. 計算 局額外資訊
            RoundHlrResultExtend roundHlrResultExtend = this.calculateRoundHlrResultExtend(super.conditionId, finiteAwardResult.getDampScreenBoxList());

            // 7. 計算 局額外類型
            ConstMathSlot.RoundNormalGameType roundNormalGameType = super.calculateRoundNormalGameType(roundHlrResultExtend);

            // 8. 計算進度
            ProgressHlrResult progressHlrResult = this.logic.getProgressHlrResult(super.conditionId, roundIndex, beforeGameStateHlrResult, roundHlrResultList, specialFeatureHlrResultCluster);

            // 9. 計算總得分[ 表演池倍數給分 ]
            double totalWin = MathUtil.multiply(finiteAwardResult.getTotalOdds(), spinRequest.getPlayerCreditBase());

            // 10. 計算累積得分結果
            AccumulateWinCtrResult accumulateWinCtrResult = this.logic.getAccumulateWinCtrResult(roundIndex, totalWin, beforeGameStateHlrResult, roundHlrResultList);

            // 11. 封裝 局結果
            RoundHlrResultNormal roundResultNormal = new RoundHlrResultNormal(roundIndex,
                    MathUtil.moneyScale(totalWin),
                    specialFeatureHlrResultCluster,
                    progressHlrResult,
                    new ReadyHandHlrResultUnionCluster(),
                    accumulateWinCtrResult,
                    screenGtrResultBaseGame,
                    gameCtrResult,
                    roundNormalGameType,
                    roundHlrResultExtend
                    );

            // 12. 加入 局結果
            roundHlrResultList.add(roundResultNormal);
        }

        // 13. 計算共同輸入結果
        CommonInputResult commonInputResult = this.logic.getCommonInputResult(roundHlrResultList);

        // 14. 計算遊戲輸入結果
        GameStateInputResult gameStateInputResult = this.logic.getGameStateInputResult(super.conditionId, roundHlrResultList);

        // 15. 回傳 遊戲狀態結果
        return new GameStateHlrResult(
                super.conditionId,
                gameStateResultIndex,
                super.gameStateId,
                MathUtil.moneyScale(super.calculateStateTotalWin(roundHlrResultList)),
                super.config.getGameStateType(),
                new GameStateHlrResultExtendXzcqBaseGame(),
                super.config.getRoundType(),
                roundHlrResultList,
                commonInputResult,
                gameStateInputResult);
    }


    //* 轉換倍數表演資訊製畫面生產結果 *//
    // 給定畫面，包裝畫面結果
    private ScreenGtrResult calculateScreenGtrResult(DampScreenBox dampScreenBox) {
        // 1. 取得畫面標誌
        List<List<Integer>> screenSymbol = dampScreenBox.getScreenSymbol();

        // 2. 計算畫面結果
        FrameResult frameResult = this.calculateFrameResult();

        // 3. 計算輪帶表結果
        ReelCtrResult reelCtrResult = this.calculateReelResult(dampScreenBox);

        // 4. 回傳
        return new ScreenGtrResult(screenSymbol, frameResult, reelCtrResult);
    }

    // 計算畫面結果
    private FrameResult calculateFrameResult() {
        // 1. 取得畫面設定
        FrameConfig frameConfig = this.config.getRoundConfig().getFrameConfig();

        // 2. 取得畫面額外設定
        FrameConfigExtendFix configExtend = (FrameConfigExtendFix) frameConfig.getFrameConfigExtend();

        // 3. 包裝結果
        return new FrameResult(frameConfig.getFrameType(), new FrameResultExtendFix(configExtend.getScreenRowPerColumnList()));
    }

    // 計算輪帶結果
    private ReelCtrResult calculateReelResult(DampScreenBox dampScreenBox) {
        // 1. 取得輪帶表ID
        int reelId = 0;

        // 2. 取得輪帶設定
        ReelConfig reelConfig = ((RoundConfigNormal)this.config.getRoundConfig()).getReelConfig();

        // 3. 取得停輪類型
        ConstMathSlot.ReelStopType reelStopType = reelConfig.getReelStripBoxList().get(reelId).getReelStopType();

        // 4. 依照畫面取得停輪位置
        List<Integer> reelStopIndexList = this.calculateReelStopIndexList(dampScreenBox, reelConfig.getReelStripBoxList().get(reelId));

        // 5. 回傳  TODO 使用正確 dampCtrResult
        return new ReelCtrResult(reelId, reelStopType, new ReelStopResultExtendStopByDependentReelIndex(new DampCtrResult(), reelStopIndexList));
    }

    // 計算停輪位置列表
    private List<Integer> calculateReelStopIndexList(DampScreenBox dampScreenBox, ReelStripBox reelStripBox) {
        List<Integer> result = new ArrayList<>();

        Stream.iterate(0, i -> i + 1).limit(dampScreenBox.getScreenSymbol().size()).forEach(i -> result.add(this.calculateStopIndex(i, dampScreenBox, reelStripBox)));

       return result;
    }

    // 計算停輪位置
    private int calculateStopIndex(int columnIndex, DampScreenBox dampScreenBox, ReelStripBox reelStripBox) {
        // 1. 取得單軸輪代表
        List<Integer> stripPerColumn = ((ReelConfigExtendReelDependent)reelStripBox.getReelConfigExtend()).getReelStripList().get(columnIndex);

        // 2. 取得該軸畫面
        List<Integer> screenPerColumn = Arrays.asList(dampScreenBox.getUpperDampSymbol().get(columnIndex),
                                                        dampScreenBox.getScreenSymbol().get(columnIndex).get(0),
                                                        dampScreenBox.getLowerDampSymbol().get(columnIndex));

        // 3. 回傳滿足畫面的第一個指標
        return (Collections.indexOfSubList(stripPerColumn, screenPerColumn) + 1) % stripPerColumn.size();
    }


    //* 計算客端重轉資訊 *//
    // 計算 ReSpin 結果
    private RoundHlrResultExtend calculateRoundHlrResultExtend(int conditionId, List<DampScreenBox> dampScreenBoxList) {
        // 1. 若無 reSpin，回傳空的結果
        if (dampScreenBoxList.size() == 1) {
            return new RoundHlrResultExtendDefault();
        }

        // 2. 計算 ReSpin 畫面結果
        ScreenGtrResult reSpinScreenGtrResult = this.calculateScreenGtrResult(dampScreenBoxList.get(1));

        // 3. 計算 ReSpin 算分結果
        GameCtrResult reSpinGameCtrResult = this.logic.getGameCtrResult(conditionId, this.logic.getSpinRequest(), reSpinScreenGtrResult);

        // 4. 取得使用輪帶表
       List<Integer> reSpinReelStrip = this.getReSpinReelStrip(reSpinScreenGtrResult.getReelCtrResult().getReelId());

        // 5. 回傳
        return new RoundHlrResultExtendXzcqBaseGame(
                MathUtil.moneyScale(reSpinGameCtrResult.getTotalWin()),
                reSpinReelStrip,
                reSpinScreenGtrResult,
                reSpinGameCtrResult
        );
    }

    // 取得使用重轉輪帶表
    private List<Integer> getReSpinReelStrip(int reSpinReelId) {
        // 1. 取得局額外設定
        RoundConfigNormal roundConfigNormal = (RoundConfigNormal) this.config.getRoundConfig();

        // 2. 取得輪帶表額外設定
        ReelConfigExtendReelDependent reelConfigExtendReelDependent = (ReelConfigExtendReelDependent) roundConfigNormal.getReelConfig().getReelStripBoxList().get(reSpinReelId).getReelConfigExtend();

        // 3. 回傳
        return reelConfigExtendReelDependent.getReelStripList().get(reelConfigExtendReelDependent.getReelStripList().size() - 1);
    }
}
