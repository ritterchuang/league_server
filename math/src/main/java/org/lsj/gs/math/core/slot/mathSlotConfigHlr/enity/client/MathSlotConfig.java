package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.client;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfigCluster;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.module.clientBetSpinConfigHlr.enity.result.ClientBetSpinConfigCluster;

import java.util.List;

// 數值老虎機設定
public class MathSlotConfig {
    private final List<ConstMathSlot.BetType> betTypeList; // 押注類型列表
    private final List<ConstMathSlot.SpinType> spinTypeList; // 玩法類型列表
    private final ClientBetSpinConfigCluster betSpinConfigCluster; // 押注玩法 與 成本設定對應表
    private final GameStateConfigCluster gameStateConfigCluster; // 客製遊戲狀態設定集合

    public MathSlotConfig(List<ConstMathSlot.BetType> betTypeList, List<ConstMathSlot.SpinType> spinTypeList, ClientBetSpinConfigCluster betSpinConfigCluster, GameStateConfigCluster gameStateConfigCluster) {
        this.betTypeList = betTypeList;
        this.spinTypeList = spinTypeList;
        this.betSpinConfigCluster = betSpinConfigCluster;
        this.gameStateConfigCluster = gameStateConfigCluster;
    }

    public List<ConstMathSlot.BetType> getBetTypeList() {
        return betTypeList;
    }

    public List<ConstMathSlot.SpinType> getSpinTypeList() {
        return spinTypeList;
    }

    public ClientBetSpinConfigCluster getBetSpinConfigCluster() {
        return betSpinConfigCluster;
    }

    public GameStateConfigCluster getGameStateConfigCluster() {
        return gameStateConfigCluster;
    }
}
