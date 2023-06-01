package org.lsj.gs.math.games.lmjjc_java.module.gameStateHlr;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfig;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultCascade;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.roundResultExtend.RoundHlrResultExtend;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.roundResultExtend.RoundHlrResultExtendDefault;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.AbstractGameStateHlr;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.IGameStateHlr;
import org.lsj.gs.math.core.common.logic.logicSlot.ILogicSlot;
import org.lsj.gs.math.core.common.logic.logicSlot.LogicSlotCascade;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtilSlot;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtrResult;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.result.CascadeClusterHlrResult;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.commonInputHlr.enity.result.CommonInputResult;
import org.lsj.gs.math.core.slot.gameStateInputHlrMgr.enity.result.GameStateInputResult;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.RoundConfigCascade;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.roundCascadeGameConfig.RoundCascadeGameConfigExtendLmjjcBaseGame;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResultUnionCluster;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;
import org.lsj.gs.math.games.lmjjc_java.entity.config.GameStateConfigExtendLmjjcBaseGame;
import org.lsj.gs.math.games.lmjjc_java.entity.result.gameStateResult.GameStateHlrResultExtendLmjjcBaseGame;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;

// 羅馬競技場 基本遊戲處理者
public class GameStateHlrLmjjcBaseGame extends AbstractGameStateHlr implements IGameStateHlr {
    private final LogicSlotCascade logic; // 牌桌邏輯
    private final GameStateConfigExtendLmjjcBaseGame gameStateConfigExtend; // 遊戲狀態額外設定
    private final RoundCascadeGameConfigExtendLmjjcBaseGame roundConfigExtend; // 消除局額外設定

    public GameStateHlrLmjjcBaseGame(int conditionId, int gameStateId, ITableUtilSlot tableUtil, ILogicSlot logic, GameStateConfig gameStateConfig) {
        super(conditionId, gameStateId, gameStateConfig, tableUtil);

        this.logic = (LogicSlotCascade) logic;
        this.gameStateConfigExtend = (GameStateConfigExtendLmjjcBaseGame) gameStateConfig.getGameStateConfigExtend();
        this.roundConfigExtend = (RoundCascadeGameConfigExtendLmjjcBaseGame) ((RoundConfigCascade)gameStateConfig.getRoundConfig()).getRoundCascadeGameConfigExtend();
    }

    // 計算遊戲狀態結果
    @Override
    public GameStateHlrResult calculateGameStateHlrResult(int gameStateResultIndex, ConstMathSlot.ReelRtpType reelRtpType, SpinRequest spinRequest, GameStateHlrResult beforeGameStateHlrResult) {
        ArrayList<RoundHlrResult> roundHlrResultList = new ArrayList<>();

        // 1. 設定初始場次
        int roundCount = this.logic.getDefaultRound(super.conditionId, beforeGameStateHlrResult);

        for (int roundIndex = 0; roundIndex < roundCount; roundIndex++) {
            // 2. 產出消除結果集合
            CascadeClusterHlrResult cascadeClusterHlrResult = this.logic.getCascadeClusterHlrResult(super.conditionId, spinRequest, reelRtpType);

            // 3. 計算特殊事件結果
            SpecialFeatureHlrResultCluster specialFeatureHlrResultCluster = this.logic.getSpecialFeatureHlrResultCluster(
                    super.conditionId,
                    spinRequest,
                    cascadeClusterHlrResult.getCascadeHlrResultList().get(cascadeClusterHlrResult.getCascadeHlrResultList().size() - 1).getScreenGtrResult());

            // 4. 計算 局額外類型 [消除]
            ConstMathSlot.RoundCascadeGameType roundCascadeGameType = ConstMathSlot.RoundCascadeGameType.DEFAULT;

            // 5. 計算 局額外資訊
            RoundHlrResultExtend roundHlrResultExtend = new RoundHlrResultExtendDefault();

            // 6. 計算進度
            ProgressHlrResult progressHlrResult = this.logic.getProgressHlrResult(super.conditionId, roundIndex, beforeGameStateHlrResult, roundHlrResultList, specialFeatureHlrResultCluster);

            // 7. 計算聽牌結果
            ReadyHandHlrResultUnionCluster readyHandHlrResultUnionCluster = this.logic.getReadyHandHlrResultUnionCluster(super.conditionId, cascadeClusterHlrResult.getCascadeHlrResultList().get(0).getScreenGtrResult().getScreenSymbol(), progressHlrResult);

            // 8. 計算總得分[ 表演池倍數給分 ]
            double totalWin = MathUtil.add(cascadeClusterHlrResult.getTotalWin(), specialFeatureHlrResultCluster.getTotalWin());

            // 9. 計算累積得分結果
            AccumulateWinCtrResult accumulateWinCtrResult = this.logic.getAccumulateWinCtrResult(roundIndex, totalWin, beforeGameStateHlrResult, roundHlrResultList);

            // 10. 封裝 局結果
            RoundHlrResultCascade roundHlrResultCascade = new RoundHlrResultCascade(
                    roundIndex,
                    MathUtil.moneyScale(totalWin),
                    specialFeatureHlrResultCluster,
                    progressHlrResult,
                    readyHandHlrResultUnionCluster,
                    accumulateWinCtrResult,
                    cascadeClusterHlrResult,
                    roundCascadeGameType,
                    roundHlrResultExtend
            );

            // 11. 加入 局結果
            roundHlrResultList.add(roundHlrResultCascade);
        }

        // 12. 計算共同輸入結果
        CommonInputResult commonInputResult = this.logic.getCommonInputResult(roundHlrResultList);

        // 13. 計算遊戲輸入結果
        GameStateInputResult gameStateInputResult = this.logic.getGameStateInputResult(super.conditionId, roundHlrResultList);

        // 14. 回傳 遊戲狀態結果
        return new GameStateHlrResult(
                super.conditionId,
                gameStateResultIndex,
                super.gameStateId,
                MathUtil.moneyScale(super.calculateStateTotalWin(roundHlrResultList)),
                super.config.getGameStateType(),
                new GameStateHlrResultExtendLmjjcBaseGame(),
                super.config.getRoundType(),
                roundHlrResultList,
                commonInputResult,
                gameStateInputResult);
    }
}
