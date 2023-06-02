package org.lsj.gs.math.core.common.logic.logicSlot;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigSlot;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.entity.PreGameAdjustResult;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import org.lsj.gs.math.core.common.gameFlowHlr.enity.result.GameFlowHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.common.gamePlayerHlr.entity.GamePlayer;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.AbstractLogic;
import org.lsj.gs.math.core.common.logic.ILogic;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultSlot;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.IGameBetLogResultCtrSlot;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.spinResultPgr.SpinResultPgr;
import org.lsj.gs.math.core.common.table.entity.message.fish.GamePlayerResult;
import org.lsj.gs.math.core.common.table.entity.message.slot.ClientSpinRequest;
import org.lsj.gs.math.core.common.table.module.ITableBase;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtr;
import org.lsj.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtrResult;
import org.lsj.gs.math.core.slot.animationCtr.IAnimationCtr;
import org.lsj.gs.math.core.slot.animationCtr.enity.config.AnimationCtrConfig;
import org.lsj.gs.math.core.slot.animationCtr.enity.result.AnimationResult;
import org.lsj.gs.math.core.slot.animationCtr.module.AnimationCtrFactory;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.ClientSpinRequestHlr;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.ClientSpinRequestHlrConfig;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.commonInputHlr.ICommonInputHlr;
import org.lsj.gs.math.core.slot.commonInputHlr.enity.config.CommonInputHlrConfig;
import org.lsj.gs.math.core.slot.commonInputHlr.enity.result.CommonInputResult;
import org.lsj.gs.math.core.slot.commonInputHlr.module.CommonInputHlrFactory;
import org.lsj.gs.math.core.slot.gameStateInputHlrMgr.GameStateInputHlrMgr;
import org.lsj.gs.math.core.slot.gameStateInputHlrMgr.enity.config.GameStateInputHlrMgrConfig;
import org.lsj.gs.math.core.slot.gameStateInputHlrMgr.enity.result.GameStateInputResult;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.MathSlotConfigHlr;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.client.MathSlotConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.MathSlotConfigHlrConfig;
import org.lsj.gs.math.core.slot.moduleConfigMgr.AbstractModuleConfigMgrSlot;
import org.lsj.gs.math.core.slot.oneBoardResultMgr.OneBoardResultMgr;
import org.lsj.gs.math.core.slot.oneBoardResultMgr.enity.LastPlayedProgressResult;
import org.lsj.gs.math.core.slot.oneBoardResultMgr.enity.SlotCutReturn;
import org.lsj.gs.math.core.slot.oneBoardResultMgr.module.OneBoardResultHlr;
import org.lsj.gs.math.core.slot.oneBoardResultMgr.module.SlotCutReturnHlr;
import org.lsj.gs.math.core.slot.paymentHlrMgr.PaymentHlrMgr;
import org.lsj.gs.math.core.slot.paymentHlrMgr.enity.PaymentHlrMgrConfig;
import org.lsj.gs.math.core.slot.progressHlrMgr.ProgressHlrMgr;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressHlrMgrConfig;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.ReadyHandHlrMgr;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.config.ReadyHandHlrMgrConfig;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResultUnionCluster;
import org.lsj.gs.math.core.slot.reelRtpStateHlr.ReelRtpStateHlr;
import org.lsj.gs.math.core.slot.reelRtpStateHlr.ReelRtpStateHlrConfig;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.SlotDetailHlrMgr;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetailHlrMgrConfig;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.SpecialFeatureHlrMgr;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrMgrConfig;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;
import org.lsj.utils.MathUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// 抽象邏輯老虎機
public abstract class AbstractLogicSlot extends AbstractLogic implements ILogicSlot, ILogic {
    protected final IGameBetLogResultCtrSlot gameBetLogResultCtrSlot; // 遊戲投注紀錄計算器魚機

    protected final MathSlotConfigHlr mathSlotConfigHlr; // 數值設定處理器
    protected final OneBoardResultMgr oneBoardResultMgr; // 一局結果管理者
    protected final PaymentHlrMgr paymentHlrMgr; // 成本處理器管理者
    protected final ReelRtpStateHlr reelRtpStateHlr; // 高低表處理者
    protected final ICommonInputHlr commonInputHlr; // 共同輸入處理者
    protected final GameStateInputHlrMgr gameStateInputHlrMgr; // 遊戲額外輸入處理器管理者
    protected final SlotDetailHlrMgr slotDetailHlrMgr; // 虎機詳細記錄處理者

    protected final IAnimationCtr animationCtr; // 動畫計算者
    protected final AccumulateWinCtr accumulateWinCtr; // 累積得分計算者

    protected final ClientSpinRequestHlr clientSpinRequestHlr; // 押注檢查者

    protected final SpecialFeatureHlrMgr specialFeatureHlrMgr; // 特殊事件處理器管理者
    protected final ProgressHlrMgr progressHlrMgr; // 遊戲進度處理器管理者
    protected final ReadyHandHlrMgr readyHandHlrMgr; // 聽牌處理器管理者

    public AbstractLogicSlot(ITableBase table, TableFieldConfig tableFieldConfig, TableGameConfigSlot tableGameConfigSlot, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, IGameBetLogResultCtrSlot gameBetLogResultCtrSlot, ITableUtil tableUtil) {
        // 1. 初始化
        super(table, tableFieldConfig, gamePlayerHlr, poolCtr, gameBetLogResultCtrSlot, tableUtil);

        // 2. 取得共用模組設定檔
        AbstractModuleConfigMgrSlot abstractModuleConfigMgrSlot = table.getTableConfigMgr().getModuleConfigMgrSlot();
        MathSlotConfigHlrConfig mathSlotConfigHlrConfig = abstractModuleConfigMgrSlot.createMathSlotConfigHlrConfig(tableGameConfigSlot);
        PaymentHlrMgrConfig paymentHlrMgrConfig = abstractModuleConfigMgrSlot.createPaymentHlrMgrConfig(tableGameConfigSlot);
        ReelRtpStateHlrConfig reelRtpStateHlrConfig = abstractModuleConfigMgrSlot.createReelRtpStateHlrConfig(tableGameConfigSlot);
        CommonInputHlrConfig commonInputHlrConfig = abstractModuleConfigMgrSlot.createCommonInputHlrMgrConfig(tableGameConfigSlot);
        GameStateInputHlrMgrConfig gameStateInputHlrMgrConfig = abstractModuleConfigMgrSlot.createGameInputHlrMgrConfig(tableGameConfigSlot);
        SlotDetailHlrMgrConfig slotDetailHlrMgrConfig = abstractModuleConfigMgrSlot.createSlotDetailHlrMgrConfig(tableGameConfigSlot);

        AnimationCtrConfig animationCtrConfig = abstractModuleConfigMgrSlot.createAnimationConfig(tableGameConfigSlot);

        ClientSpinRequestHlrConfig clientSpinRequestHlrConfig = abstractModuleConfigMgrSlot.createClientSpinRequestHlrConfig(tableGameConfigSlot);

        SpecialFeatureHlrMgrConfig specialFeatureHlrMgrConfig = abstractModuleConfigMgrSlot.createSpecialFeatureHlrMgrConfig(tableGameConfigSlot);
        ProgressHlrMgrConfig progressHlrMgrConfig = abstractModuleConfigMgrSlot.createProgressHlrMgrConfig(tableGameConfigSlot);
        ReadyHandHlrMgrConfig readyHandHlrMgrConfig = abstractModuleConfigMgrSlot.createReadyHandHlrMgrConfig(tableGameConfigSlot);

        // 3. 初始化模組
        this.gameBetLogResultCtrSlot = gameBetLogResultCtrSlot;

        this.mathSlotConfigHlr =  new MathSlotConfigHlr(mathSlotConfigHlrConfig, gamePlayerHlr, poolCtr, tableUtil);
        this.oneBoardResultMgr = new OneBoardResultMgr(new OneBoardResultHlr(), new SlotCutReturnHlr(), gamePlayerHlr, poolCtr, tableUtil);
        this.paymentHlrMgr = new PaymentHlrMgr(paymentHlrMgrConfig, tableUtil);
        this.reelRtpStateHlr = new ReelRtpStateHlr(
                reelRtpStateHlrConfig,
                gamePlayerHlr,
                poolCtr,
                tableUtil);
        this.commonInputHlr = new CommonInputHlrFactory().create(commonInputHlrConfig, tableUtil);
        this.gameStateInputHlrMgr = new GameStateInputHlrMgr(gameStateInputHlrMgrConfig, tableUtil);
        this.slotDetailHlrMgr = new SlotDetailHlrMgr(slotDetailHlrMgrConfig, tableUtil);

        this.animationCtr = new AnimationCtrFactory().create(animationCtrConfig);
        this.accumulateWinCtr = new AccumulateWinCtr();

        this.clientSpinRequestHlr = new ClientSpinRequestHlr(clientSpinRequestHlrConfig, tableUtil);

        this.specialFeatureHlrMgr = new SpecialFeatureHlrMgr(specialFeatureHlrMgrConfig, tableUtil); // 特殊事件處理者
        this.progressHlrMgr = new ProgressHlrMgr(progressHlrMgrConfig, tableUtil); // 遊戲進度管理者
        this.readyHandHlrMgr = new ReadyHandHlrMgr(readyHandHlrMgrConfig, tableUtil);
    }


    //* 檢查機制 *//
    // 檢查押注請求
    @Override
    public ConstMathCommon.TableProtocolCode checkSpinRequest(ClientSpinRequest clientSpinRequest) {
        return this.clientSpinRequestHlr.checkClientSpinRequest(clientSpinRequest);
    }


    //* 當前資訊 *//
    // 取得數值設定
    @Override
    public MathSlotConfig getMathSlotConfig() {
        return this.mathSlotConfigHlr.getMathSlotConfig(this.paymentHlrMgr);
    }

    // 取得服務端押注請求
    @Override
    public SpinRequest getSpinRequest() {
         return this.clientSpinRequestHlr.getSpinRequest(this.oneBoardResultMgr.getCurrentSpinRequest(), this.paymentHlrMgr);
    }

    // 取得已應用流程結果
    @Override
    public GameFlowHlrResult getGameFlowerResult() {
        return this.oneBoardResultMgr.getGameFlowHlrResult();
    }

    // 取得押注成本
    @Override
    public double getSpinCost(ClientSpinRequest clientSpinRequest) {
        return this.paymentHlrMgr.getPlayerCost(clientSpinRequest.getPlayCredit(), clientSpinRequest.getBetType(), clientSpinRequest.getSpinType());
    }

    // 取得玩家押注
    @Override
    public double getPlayerBet() {
        return this.paymentHlrMgr.getPlayerBet(this.oneBoardResultMgr.getCurrentSpinRequest().getPlayCredit(), this.oneBoardResultMgr.getCurrentSpinRequest().getBetType(), this.oneBoardResultMgr.getCurrentSpinRequest().getSpinType());
    }

    // 取得押注後餘額
    @Override
    public GamePlayerResult getAfterSpinBetGamePlayerResult() {
        return this.oneBoardResultMgr.getAfterSpinBetGamePlayerResult();
    }


    //* 結果相關 *//
    // 取得共同遊戲額外輸入結果
    @Override
    public CommonInputResult getCommonInputResult(List<RoundHlrResult> roundHlrResultList) {
        return this.commonInputHlr.calculateCommonInput(roundHlrResultList);
    }

    // 取得遊戲額外輸入結果
    @Override
    public GameStateInputResult getGameStateInputResult(int conditionId, List<RoundHlrResult> roundHlrResultList) {
        return this.gameStateInputHlrMgr.getGameStateInputResult(conditionId, roundHlrResultList);
    }

    // 取得高低表模式
    @Override
    public ConstMathSlot.ReelRtpType getReelHEType(ConstMathCommon.ShuffleType shuffleType, GameAdjustParameter gameAdjustParameter) {
        return this.reelRtpStateHlr.calculateReelRtpType(shuffleType, gameAdjustParameter);
    }

    // 計算動畫結果
    @Override
    public AnimationResult getAnimationResult(double playerBet, double totalWin) {
        return this.animationCtr.calculateAnimationResult(playerBet, totalWin);
    }

    // 計算累積得分
    @Override
    public AccumulateWinCtrResult getAccumulateWinCtrResult(int currentRoundIndex, double currentRoundWin, GameStateHlrResult beforeGameStateHlrResult, List<RoundHlrResult> roundHlrResultList) {
        return this.accumulateWinCtr.calculateAccumulateWinCtrResultRound(currentRoundIndex, currentRoundWin, beforeGameStateHlrResult, roundHlrResultList);
    }

    // 取得虎機結果
    @Override
    public IGameBetLogResultSlot getGameBetLogResultSlot(UidScore uidScore) {
        return this.gameBetLogResultCtrSlot.calculateGameBetLogResultSlot(super.table.getRoundId(), uidScore, this.oneBoardResultMgr.getGameFlowHlrResult());
    }

    // 取得特殊事件結果列表
    public SpecialFeatureHlrResultCluster getSpecialFeatureHlrResultCluster(int conditionId, SpinRequest spinRequest, ScreenGtrResult screenGtrResult) {
        return this.specialFeatureHlrMgr.getSpecialFeatureHlrResultCluster(conditionId, spinRequest, screenGtrResult);
    }

    // 取得遊戲進度結果
    public ProgressHlrResult getProgressHlrResult(int conditionId, int roundIndex,
                                                  GameStateHlrResult beforeGameStateHlrResult,
                                                  List<RoundHlrResult> preRoundHlrResultList,
                                                  SpecialFeatureHlrResultCluster specialFeatureHlrResultCluster) {
        return this.progressHlrMgr.getProgressHlrResult(conditionId, roundIndex, beforeGameStateHlrResult, preRoundHlrResultList, specialFeatureHlrResultCluster);
    }

    // 取得遊戲聽牌結果
    public ReadyHandHlrResultUnionCluster getReadyHandHlrResultUnionCluster(int conditionId, List<List<Integer>> screenSymbol, ProgressHlrResult progressHlrResult) {
        return this.readyHandHlrMgr.getReadyHandHlrResultUnionCluster(conditionId, screenSymbol, progressHlrResult);
    }

    // 計算玩家得分對應表
    @Override
    public Map<Integer, UidScore> calculateUidScoreMap(GameFlowHlrResult gameFlowHlrResult) {
        // 1. 取得玩家資訊
        int humanChairIndex = super.getHumanChairIndex();
        GamePlayer gamePlayer = super.getHumanGamePlayer();

        // 2. 取得 押注資訊
        SpinRequest spinRequest = this.clientSpinRequestHlr.getSpinRequest(this.oneBoardResultMgr.getCurrentSpinRequest(), this.paymentHlrMgr);

        // 3. 包裝結果
        return new HashMap<>(){
            {
                put(humanChairIndex, new UidScore(humanChairIndex,
                        gamePlayer.getUid(),
                        spinRequest.getPlayerCost(),
                        spinRequest.getPlayerCost(),
                        gameFlowHlrResult.getTotalWin(),
                        MathUtil.subtract(gameFlowHlrResult.getTotalWin(), spinRequest.getPlayerCost()),
                        0));
            }
        };
    }

    // 取得真人得分
    @Override
    public UidScore getHumanUidScore() {
        return this.calculateUidScoreMap(this.getGameFlowerResult()).get(super.getHumanChairIndex());
    }


    //* 設定相關 *//
    // 設定斷線重連局號
    @Override
    public void setRoundId(String roundId) {
        this.oneBoardResultMgr.setRoundId(roundId);
    }

    // 設定此次客端押注請求
    @Override
    public void setCurrentSpinRequest(ClientSpinRequest clientSpinRequest) {
        this.oneBoardResultMgr.setClientSpinRequest(clientSpinRequest);
    }

    // 設定遊戲前餘額
    @Override
    public void setAfterSpinBetGamePlayerResult(GamePlayerResult afterSpinBetGamePlayerResult) {
        this.oneBoardResultMgr.setAfterSpinBetGamePlayerResult(afterSpinBetGamePlayerResult);
    }

    // 設定此次預結果 TODO commonInput 也需要記錄
    @Override
    public void setCurrentPreGameResult(PreGameAdjustResult preGameAdjustResult) {
        this.oneBoardResultMgr.setPreGameAdjustResult(preGameAdjustResult);
    }


    //* 斷線重連相關 *//
    // 更新斷線重連資訊
    @Override
    public void updateLastPlayedProgressResult(LastPlayedProgressResult lastPlayedProgressResult) {
        this.oneBoardResultMgr.updateLastPlayedProgressResult(lastPlayedProgressResult);
    }

    // 取得斷線重連結果
    @Override
    public Optional<SlotCutReturn> getSlotCutReturn(SpinResultPgr spinResultPgr) {
        return this.oneBoardResultMgr.getSlotCutReturn(spinResultPgr);
    }


    //* 重製相關 *//
    // 重製局結果
    @Override
    public void resetOneBoard() {
        this.gameBetLogResultCtrSlot.resetDetail();
    }

    // 添加詳細記錄
    @Override
    public void addDetailSlot(SpinRequest spinRequest, GameFlowHlrResult gameFlowHlrResult) {
        this.slotDetailHlrMgr.getSlotDetailList(spinRequest, gameFlowHlrResult).forEach(this.gameBetLogResultCtrSlot::addDetail);
    }
}
