package org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.commonInputHlr.enity.config.CommonInputConfig;

import java.util.List;
import java.util.Map;

// 服務端遊戲端遊戲狀態設定集合
public class GameStateConfigCluster {
    @JsonIgnore
    private final Map<ConstMathSlot.ReelRtpType, Double> reelRtpTypeToDoubleMap; // 高低表設定
    @JsonIgnore
    private final CommonInputConfig commonInputConfig; // 通用輸入設定
    private final Map<ConstMathSlot.BetType, Map<ConstMathSlot.SpinType, Integer>> betSpinTypeToGameStateIdMap; // 押注玩法遊戲識別碼對應表
    private final List<GameStateConfig> gameStateConfigList; // 遊戲狀態設定列表

    public GameStateConfigCluster(Map<ConstMathSlot.ReelRtpType, Double> reelRtpTypeToDoubleMap, CommonInputConfig commonInputConfig, Map<ConstMathSlot.BetType, Map<ConstMathSlot.SpinType, Integer>> betSpinTypeToGameStateIdMap, List<GameStateConfig> gameStateConfigList) {
        this.reelRtpTypeToDoubleMap = reelRtpTypeToDoubleMap;
        this.commonInputConfig = commonInputConfig;
        this.betSpinTypeToGameStateIdMap = betSpinTypeToGameStateIdMap;
        this.gameStateConfigList = gameStateConfigList;
    }

    public Map<ConstMathSlot.ReelRtpType, Double> getReelRtpTypeToDoubleMap() {
        return reelRtpTypeToDoubleMap;
    }

    public CommonInputConfig getCommonInputConfig() {
        return commonInputConfig;
    }

    public Map<ConstMathSlot.BetType, Map<ConstMathSlot.SpinType, Integer>> getBetSpinTypeToGameStateIdMap() {
        return betSpinTypeToGameStateIdMap;
    }

    public List<GameStateConfig> getGameStateConfigList() {
        return gameStateConfigList;
    }
}
