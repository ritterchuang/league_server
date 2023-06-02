package org.lsj.gs.math.core.common.gameFlowHlr;

import org.lsj.gs.math.core.AbstractModule;
import org.lsj.gs.math.core.common.gameFlowHlr.enity.config.GameFlowHlrConfig;
import org.lsj.gs.math.core.common.gameFlowHlr.enity.result.GameFlowHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.GameStateHlrMgr;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.systemStateHlr.ConditionStateHlr;
import org.lsj.gs.math.core.common.gameFlowHlr.module.systemStateHlr.conditionHlrMgr.ConditionHlrMgr;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.logicSlot.ILogicSlot;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.animationCtr.enity.result.AnimationResult;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// 遊戲流程處理者
public class GameFlowHlr extends AbstractModule {
    private final GameFlowHlrConfig config; // 遊戲流程設定
    private final ILogicSlot logic; // 邏輯
    private final ConditionStateHlr conditionStateHlr; // 條件狀態處理者
    private final ConditionHlrMgr conditionHlrMgr; // 條件處理者管理器
    private final GameStateHlrMgr gameStateHlrMgr; // 遊戲狀態處理者管理器

    public GameFlowHlr(GameFlowHlrConfig config, ILogicSlot logic, ConditionStateHlr conditionStateHlr, ConditionHlrMgr conditionHlrMgr, GameStateHlrMgr gameStateHlrMgr, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(gamePlayerHlr, poolCtr, tableUtil);
        this.config = config;
        this.logic = logic;
        this.conditionStateHlr = conditionStateHlr;
        this.conditionHlrMgr = conditionHlrMgr;
        this.gameStateHlrMgr = gameStateHlrMgr;
    }

    // 計算遊戲流程結果 [包裝前使用 MoneyScale]
    public GameFlowHlrResult calculateGameFlowHlrResult(ConstMathSlot.ReelRtpType reelRtpType) {
        // 1. 計算遊戲狀態處理者結果
        List<GameStateHlrResult> gameStateHlrResultList = this.calculateGameStateHlrResultList(reelRtpType);

        // 2. 計算 總得分
        double totalWin = this.calculateTotalWin(gameStateHlrResultList);

        // 3. 計算動畫表演
        AnimationResult animationResult = this.logic.getAnimationResult(this.logic.getPlayerBet(), totalWin);

        // 4. 封裝 遊戲流程結果, 回傳
        return new GameFlowHlrResult(MathUtil.moneyScale(totalWin), gameStateHlrResultList, animationResult);
    }

    // 計算總得分
    private double calculateTotalWin(List<GameStateHlrResult> gameStateHlrResultList) {
        double totalWin = 0.0;

        for (int gameStateResultIndex = 0; gameStateResultIndex < gameStateHlrResultList.size(); gameStateResultIndex++) {
            totalWin = MathUtil.add(totalWin, gameStateHlrResultList.get(gameStateResultIndex).getTotalWin());
        }

        return totalWin;
    }

    // 取得遊戲狀態處理者結果列表
    private List<GameStateHlrResult> calculateGameStateHlrResultList(ConstMathSlot.ReelRtpType reelRtpType) {
        // 1. 條件狀態處理者 設定初始狀態
        this.conditionStateHlr.setInitialConditionId();

        // 2. 創建空殼
        List<GameStateHlrResult> result = new ArrayList<>();

        do {
            // 3. 從 條件狀態處理者 取得當前遊戲 狀態ID, 依照此 狀態ID 取得結果
            GameStateHlrResult gameStateHlrResult = this.gameStateHlrMgr.getGameStateHlrResult(
                    this.conditionStateHlr.getCurrentConditionId(),
                    result.size(),
                    reelRtpType,
                    this.logic.getSpinRequest(),
                    this.calculatePreviousGameStateHlrResult(this.conditionStateHlr.getCurrentConditionId(), result));

            // 4. 添加結果
            result.add(gameStateHlrResult);

            // 5. 叫 條件狀態處理者 更新當前遊戲狀態ID
            this.conditionStateHlr.updateCurrentConditionId(this.logic.getSpinRequest(), gameStateHlrResult, this.conditionHlrMgr);

            // 6. 如果當前遊戲 ID 維最後一局，結束
        } while (this.conditionStateHlr.isBoardEnd() == false);

        // 7. 移除 初始遊戲狀態結果
        result.remove(this.config.getBoardInitialConditionId());

        // 8. 回傳結果
        return result;
    }

    // 取得前一把資訊
    private Optional<GameStateHlrResult> calculatePreviousGameStateHlrResult(int currentConditionId, List<GameStateHlrResult> gameStateHlrResultList) {
        if (currentConditionId == this.config.getBoardInitialConditionId()) {
            return Optional.empty();
        }

        return Optional.of(gameStateHlrResultList.get(gameStateHlrResultList.size() - 1));
    }

    @Override
    public void reset() {
    }
}
