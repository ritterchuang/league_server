package com.lx.gs.math.core.common.spinResultPgr.module;

import com.lx.gs.math.config.entity.tableGameConfig.TableGameConfigSlot;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfig;
import com.lx.gs.math.core.common.spinResultPgr.config.ClientConfigGtrFactory;
import com.lx.gs.math.core.common.spinResultPgr.config.enity.ClientConfig;
import com.lx.gs.math.core.common.spinResultPgr.config.enity.ClientGameStateConfig;
import com.lx.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.ClientRoundConfig;
import com.lx.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.roundCascade.ClientCascadeConfig;
import com.lx.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.roundCascade.ClientRoundConfigCascade;
import com.lx.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.roundNormal.ClientRoundConfigNormal;
import com.lx.gs.math.core.common.spinResultPgr.enity.SpinResultPgrConfig;
import com.lx.gs.math.core.common.spinResultPgr.enity.pgrConfig.*;
import com.lx.gs.math.core.slot.ConstMathSlot;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.cascadeConfig.CascadeConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.dampConfig.DampConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.gameConfig.GameConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.RoundConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.RoundConfigCascade;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.RoundConfigNormal;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;

import java.util.HashMap;
import java.util.Map;

// 遊戲結果包裝者設定模組
public class SpinResultPgrConfigModule {

    // 產出遊戲結果包裝者設定
    public SpinResultPgrConfig generateSpinResultPgrConfig(int gameId, TableGameConfigSlot tableGameConfigSlot) {
        // 1. 產出客戶端設定
        ClientConfig clientConfig = new ClientConfigGtrFactory().create(gameId).generate();

        // 2. 組合客戶端包裝者設定
        return this.calculateSpinResultPgrConfig(clientConfig, tableGameConfigSlot);
    }

    // 組合客戶端包裝者設定
    private SpinResultPgrConfig calculateSpinResultPgrConfig(ClientConfig clientConfig, TableGameConfigSlot tableGameConfigSlot) {
        Map<Integer, ClientGameStateResultPgrConfig> conditionIdToPgrConfig = new HashMap<>();

        for (int index = 0; index < clientConfig.getClientGameStateConfigList().size(); index++) {
            conditionIdToPgrConfig.put(
                    index + 1, this.calculateClientGameStateResultPgrConfig(
                            clientConfig.getClientGameStateConfigList().get(index),
                            tableGameConfigSlot.getGameStateConfigCluster().getGameStateConfigList().get(index))
            );

        }
        
        return new SpinResultPgrConfig(conditionIdToPgrConfig);
    }

    // 計算客端遊戲狀態結果包裝者設定
    private ClientGameStateResultPgrConfig calculateClientGameStateResultPgrConfig(ClientGameStateConfig clientGameStateConfig, GameStateConfig gameStateConfig) {
        ConstMathSlot.GameStateType gameStateType = clientGameStateConfig.getGameStateType();

        ConstMathSlot.RoundType roundType = clientGameStateConfig.getRoundType();

        ClientRoundResultPgrConfig clientRoundResultPgrConfig = this.calculateClientRoundResultPgrConfig(roundType, clientGameStateConfig.getClientRoundConfig(), gameStateConfig.getRoundConfig());

        return new ClientGameStateResultPgrConfig(gameStateType, roundType, clientRoundResultPgrConfig);
    }

    // 計算客端局結果包裝者設定
    private ClientRoundResultPgrConfig calculateClientRoundResultPgrConfig(ConstMathSlot.RoundType roundType, ClientRoundConfig clientRoundConfig, RoundConfig roundConfig) {
        if (roundType.equals(ConstMathSlot.RoundType.NORMAL)) {
            ClientRoundConfigNormal clientRoundConfigNormal = (ClientRoundConfigNormal) clientRoundConfig;
            RoundConfigNormal roundConfigNormal = (RoundConfigNormal) roundConfig;
            return this.calculateClientRoundResultNormalPgrConfig(clientRoundConfigNormal, roundConfigNormal);
        }

        ClientRoundConfigCascade clientRoundConfigCascade = (ClientRoundConfigCascade) clientRoundConfig;
        RoundConfigCascade roundConfigCascade = (RoundConfigCascade) roundConfig;
        return this.calculateClientRoundResultCascadePgrConfig(clientRoundConfigCascade, roundConfigCascade);
    }

    // 計算客端局結果包裝者設定 一般
    private ClientRoundResultPgrConfig calculateClientRoundResultNormalPgrConfig(ClientRoundConfigNormal clientRoundConfigNormal, RoundConfigNormal roundConfigNormal) {
        return new ClientRoundResultNormalPgrConfig(
                clientRoundConfigNormal.getRoundNormalGameType(),
                clientRoundConfigNormal.getClientScreenConfig(),
                clientRoundConfigNormal.getClientSymbolHitConfig(),
                roundConfigNormal.getFrameConfig(),
                roundConfigNormal.getReelConfig(),
                roundConfigNormal.getDampConfig(),
                roundConfigNormal.getGameConfig());
    }

    // 計算客端局結果包裝者設定 消除
    private ClientRoundResultPgrConfig calculateClientRoundResultCascadePgrConfig(ClientRoundConfigCascade clientRoundConfigCascade, RoundConfigCascade roundConfigCascade) {
        CascadeClusterResultPgrConfig cascadeClusterResultPgrConfig = new CascadeClusterResultPgrConfig(
                clientRoundConfigCascade.getClientCascadeClusterConfig().getCascadeClusterType(),
                clientRoundConfigCascade.getClientCascadeClusterConfig().getClientCascadeClusterConfigExtend(),
                       this.calculateCascadeResultPgrConfig(clientRoundConfigCascade.getClientCascadeClusterConfig().getClientCascadeConfig(), roundConfigCascade));

        return new ClientRoundResultCascadePgrConfig(clientRoundConfigCascade.getRoundCascadeGameType(), clientRoundConfigCascade.getClientSymbolHitConfig(), cascadeClusterResultPgrConfig);
    }

    // 計算消除包裝者設定
    private CascadeResultPgrConfig calculateCascadeResultPgrConfig(ClientCascadeConfig clientCascadeConfig, RoundConfigCascade roundConfigCascade) {
        CascadeConfig cascadeConfig = roundConfigCascade.getCascadeClusterConfig().getCascadeConfig();

        ReelConfig reelConfig = roundConfigCascade.getReelConfig();
        SymbolConfig symbolConfig = roundConfigCascade.getSymbolConfig();
        GameConfig gameConfig = roundConfigCascade.getGameConfig();
        FrameConfig frameConfig = roundConfigCascade.getFrameConfig();
        DampConfig dampConfig = roundConfigCascade.getDampConfig();

        return new CascadeResultPgrConfig(clientCascadeConfig, cascadeConfig, frameConfig, reelConfig, symbolConfig, dampConfig, gameConfig);
    }
}
