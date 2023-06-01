package org.lsj.gs.math.config.entity.tableGameConfig;

import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfigCluster;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.animationCtr.enity.config.AnimationConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.betSpinConfig.BetSpinConfigCluster;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.gameFlowConfig.GameFlowConfig;

import java.util.List;

// 老虎機牌桌遊戲設定
public class TableGameConfigSlot extends AbstractTableGameConfig {
    private final List<ConstMathSlot.BetType> betTypeList; // 押注類型列表
    private final List<ConstMathSlot.SpinType> spinTypeList; // 玩法類型列表
    private final BetSpinConfigCluster betSpinConfigCluster; // 客端投注設定集合
    private final ConstMathSlot.LogicType logicType; // 邏輯類型
    private final GameStateConfigCluster gameStateConfigCluster; // 遊戲狀態設定集合
    private final GameFlowConfig gameFlowConfig; // 遊戲流程設定
    private final AnimationConfig animationConfig; // 動畫設定

    public TableGameConfigSlot(
            GameAdjustConfig gameAdjustConfig,
            RngAlgorithmConfig rngAlgorithmConfig,
            List<ConstMathSlot.BetType> betTypeList,
            List<ConstMathSlot.SpinType> spinTypeList,
            BetSpinConfigCluster betSpinConfigCluster,
            ConstMathSlot.LogicType logicType,
            GameStateConfigCluster gameStateConfigCluster,
            GameFlowConfig gameFlowConfig,
            AnimationConfig animationConfig) {
        super(gameAdjustConfig, rngAlgorithmConfig);
        this.betTypeList = betTypeList;
        this.spinTypeList = spinTypeList;
        this.betSpinConfigCluster = betSpinConfigCluster;
        this.logicType = logicType;
        this.gameStateConfigCluster = gameStateConfigCluster;
        this.gameFlowConfig = gameFlowConfig;
        this.animationConfig = animationConfig;
    }

    public List<ConstMathSlot.BetType> getBetTypeList() {
        return betTypeList;
    }

    public List<ConstMathSlot.SpinType> getSpinTypeList() {
        return spinTypeList;
    }

    public BetSpinConfigCluster getBetSpinConfigCluster() {
        return betSpinConfigCluster;
    }

    public ConstMathSlot.LogicType getLogicType() {
        return logicType;
    }

    public GameStateConfigCluster getGameStateConfigCluster() {
        return gameStateConfigCluster;
    }

    public GameFlowConfig getGameFlowConfig() {
        return gameFlowConfig;
    }

    public AnimationConfig getAnimationConfig() {
        return animationConfig;
    }
}
