package org.lsj.gs.math.core.common.logic.logicSlot;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.entity.PreGameAdjustResult;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import org.lsj.gs.math.core.common.gameFlowHlr.enity.result.GameFlowHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.common.logic.ILogic;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultSlot;
import org.lsj.gs.math.core.common.spinResultPgr.SpinResultPgr;
import org.lsj.gs.math.core.common.table.entity.message.fish.GamePlayerResult;
import org.lsj.gs.math.core.common.table.entity.message.slot.ClientSpinRequest;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtrResult;
import org.lsj.gs.math.core.slot.animationCtr.enity.result.AnimationResult;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.commonInputHlr.enity.result.CommonInputResult;
import org.lsj.gs.math.core.slot.gameStateInputHlrMgr.enity.result.GameStateInputResult;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.client.MathSlotConfig;
import org.lsj.gs.math.core.slot.oneBoardResultMgr.enity.LastPlayedProgressResult;
import org.lsj.gs.math.core.slot.oneBoardResultMgr.enity.SlotCutReturn;

import java.util.List;
import java.util.Map;
import java.util.Optional;

// 老虎機邏輯介面
public interface ILogicSlot extends ILogic {
    //* 檢查機制 *//
    ConstMathCommon.TableProtocolCode checkSpinRequest(ClientSpinRequest clientSpinRequest); // 檢查押注資訊

    //* 取得當前資訊 *//
    MathSlotConfig getMathSlotConfig(); // 取得數值設定
    SpinRequest getSpinRequest(); // 取得服務端押注請求
    GameFlowHlrResult getGameFlowerResult(); // 取得已應用流程結果
    GamePlayerResult getAfterSpinBetGamePlayerResult(); // 取得Spin後餘額 [攜入金額 - 成本]
    double getSpinCost(ClientSpinRequest clientSpinRequest); // 取得押注成本
    double getPlayerBet(); // 取得玩家押注


    //* 結果相關 *//
    CommonInputResult getCommonInputResult(List<RoundHlrResult> roundHlrResultList); // 取得遊戲共同輸入結果
    GameStateInputResult getGameStateInputResult(int conditionId, List<RoundHlrResult> roundHlrResultList); // 取得遊戲額外輸入結果
    ConstMathSlot.ReelRtpType getReelHEType(ConstMathCommon.ShuffleType shuffleType, GameAdjustParameter gameAdjustParameter); // 取得高低表類型
    AnimationResult getAnimationResult(double playerBet, double totalWin); // 取得動畫表演結果
    AccumulateWinCtrResult getAccumulateWinCtrResult(int currentRoundIndex, double currentRoundWin, GameStateHlrResult beforeGameStateHlrResult, List<RoundHlrResult> roundHlrResultList); // 計算累積得分
    IGameBetLogResultSlot getGameBetLogResultSlot(UidScore uidScore); // 取得遊戲結果
    Map<Integer, UidScore> calculateUidScoreMap(GameFlowHlrResult gameFlowHlrResult); // 計算玩家得分對應表


    //* 設定資訊相關 *//
    void setRoundId(String roundId); // 更新局號
    void setCurrentSpinRequest(ClientSpinRequest clientSpinRequest); // 設定押注資訊
    void setAfterSpinBetGamePlayerResult(GamePlayerResult afterSpinGamePlayerResult); // 設定Spin後餘額 [攜入金額 - 成本]
    void setCurrentPreGameResult(PreGameAdjustResult preGameAdjustResult); // 設定預遊戲結果


    //* 斷線重連相關 *//
    void updateLastPlayedProgressResult(LastPlayedProgressResult lastPlayedProgressResult); // 更新玩家最新撥放進度
    Optional<SlotCutReturn> getSlotCutReturn(SpinResultPgr spinResultPgr); // 取得斷線重連資訊


    //* 詳細記錄相關 *//
    void resetOneBoard(); // 重置局資訊
    void addDetailSlot(SpinRequest spinRequest, GameFlowHlrResult gameFlowHlrResult); // 添加詳細記錄
}
