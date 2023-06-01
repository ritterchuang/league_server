package com.lx.gs.math.games.sdzw_java.module.gameStateHlr;

import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfig;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultNormal;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.roundResultExtend.RoundHlrResultExtend;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.roundResultExtend.RoundHlrResultExtendDefault;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.AbstractGameStateHlr;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.IGameStateHlr;
import com.lx.gs.math.core.common.logic.logicSlot.ILogicSlot;
import com.lx.gs.math.core.common.logic.logicSlot.LogicSlotNormal;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtilSlot;
import com.lx.gs.math.core.slot.ConstMathSlot;
import com.lx.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtrResult;
import com.lx.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import com.lx.gs.math.core.slot.commonInputHlr.enity.result.CommonInputResult;
import com.lx.gs.math.core.slot.gameCtrMgr.enity.result.GameCtrResult;
import com.lx.gs.math.core.slot.gameStateInputHlrMgr.enity.result.GameStateInputResult;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.RoundConfigNormal;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.roundNormalGameConfig.RoundNormalGameConfigExtendSdzwBaseGame;
import com.lx.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import com.lx.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResultUnionCluster;
import com.lx.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import com.lx.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;
import com.lx.gs.math.games.sdzw_java.enity.config.GameStateConfigExtendSdzwBaseGame;
import com.lx.gs.math.games.sdzw_java.enity.result.gameStateResult.GameStateHlrResultExtendSdzwBaseGame;
import com.lx.utils.MathUtil;

import java.util.ArrayList;

// 聖誕任務虎機 基本遊戲處理者
public class GameStateHlrSdzwBaseGame extends AbstractGameStateHlr implements IGameStateHlr {
    private final LogicSlotNormal logic; // 牌桌邏輯
    private final GameStateConfigExtendSdzwBaseGame gameStateConfigExtend; // 遊戲狀態額外設定
    private final RoundNormalGameConfigExtendSdzwBaseGame roundConfigExtend; // 遊戲場次額外設定

    public GameStateHlrSdzwBaseGame(int conditionId, int gameStateId, ITableUtilSlot tableUtil, ILogicSlot logic, GameStateConfig gameStateConfig) {
        super(conditionId, gameStateId, gameStateConfig, tableUtil);
        this.logic = (LogicSlotNormal) logic;
        this.gameStateConfigExtend = (GameStateConfigExtendSdzwBaseGame) gameStateConfig.getGameStateConfigExtend();
        this.roundConfigExtend = (RoundNormalGameConfigExtendSdzwBaseGame) ((RoundConfigNormal)gameStateConfig.getRoundConfig()).getRoundNormalGameConfigExtend();
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

            // 4. 計算特殊事件結果
            SpecialFeatureHlrResultCluster specialFeatureHlrResultCluster = this.logic.getSpecialFeatureHlrResultCluster(super.conditionId, spinRequest, screenGtrResult);

            // 5. 計算畫面算分結果
            GameCtrResult gameCtrResult = this.logic.getGameCtrResult(super.conditionId, spinRequest, screenGtrResult);

            // 6. 計算 局額外資訊
            RoundHlrResultExtend roundHlrResultExtend = new RoundHlrResultExtendDefault();

            // 7. 計算 局額外類型
            ConstMathSlot.RoundNormalGameType roundNormalGameType = super.calculateRoundNormalGameType(roundHlrResultExtend);

            // 8. 計算進度
            ProgressHlrResult progressHlrResult = this.logic.getProgressHlrResult(super.conditionId, roundIndex, beforeGameStateHlrResult, roundHlrResultList, specialFeatureHlrResultCluster);

            // 9. 計算聽牌結果
            ReadyHandHlrResultUnionCluster readyHandHlrResultUnionCluster = this.logic.getReadyHandHlrResultUnionCluster(super.conditionId, screenGtrResult.getScreenSymbol(), progressHlrResult);

            // 10. 計算總得分[ 表演池倍數給分 ]
            double totalWin = super.calculateRoundTotalWin(gameCtrResult, specialFeatureHlrResultCluster, roundHlrResultExtend);

            // 11. 計算累積得分結果
            AccumulateWinCtrResult accumulateWinCtrResult = this.logic.getAccumulateWinCtrResult(roundIndex, totalWin, beforeGameStateHlrResult, roundHlrResultList);

            // 12. 封裝 局結果
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
                new GameStateHlrResultExtendSdzwBaseGame(),
                super.config.getRoundType(),
                roundHlrResultList,
                commonInputResult,
                gameStateInputResult);
    }
}
