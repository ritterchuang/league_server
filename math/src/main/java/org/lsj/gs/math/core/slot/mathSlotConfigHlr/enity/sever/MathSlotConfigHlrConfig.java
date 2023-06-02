package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfigCluster;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.betSpinConfig.BetSpinConfigCluster;

import java.util.List;

// 數值老虎機設定處理器的設定
public class MathSlotConfigHlrConfig {
    private final List<ConstMathSlot.BetType> betTypeList; // 押注類型列表
    private final List<ConstMathSlot.SpinType> spinTypeList; // 玩法類型列表
    private final BetSpinConfigCluster betSpinConfigCluster; // 服務端押注設定
    private final GameStateConfigCluster gameStateConfigCluster; // 客製遊戲狀態設定集合
    private final double base; // 房間底住

    public MathSlotConfigHlrConfig(List<ConstMathSlot.BetType> betTypeList, List<ConstMathSlot.SpinType> spinTypeList, BetSpinConfigCluster betSpinConfigCluster, GameStateConfigCluster gameStateConfigCluster, double base) {
        this.betTypeList = betTypeList;
        this.spinTypeList = spinTypeList;
        this.betSpinConfigCluster = betSpinConfigCluster;
        this.gameStateConfigCluster = gameStateConfigCluster;
        this.base = base;
    }

    public List<ConstMathSlot.BetType> getBetTypeList() {
        return betTypeList;
    }

    public List<ConstMathSlot.SpinType> getSpinTypeList() {
        return spinTypeList;
    }

    public BetSpinConfigCluster getBetConfigCluster() {
        return betSpinConfigCluster;
    }

    public GameStateConfigCluster getGameStateConfigCluster() {
        return gameStateConfigCluster;
    }

    public double getBase() {
        return base;
    }
}
