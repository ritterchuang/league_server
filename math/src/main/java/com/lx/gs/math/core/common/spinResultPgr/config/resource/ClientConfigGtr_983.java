package com.lx.gs.math.core.common.spinResultPgr.config.resource;

import com.lx.gs.math.core.common.spinResultPgr.ConstPgrSlot;
import com.lx.gs.math.core.common.spinResultPgr.config.IClientConfigGtr;
import com.lx.gs.math.core.common.spinResultPgr.config.enity.ClientConfig;
import com.lx.gs.math.core.common.spinResultPgr.config.enity.ClientGameStateConfig;
import com.lx.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientScreenConfig.ClientScreenConfig;
import com.lx.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientSymbolHitConfig.ClientSymbolHitConfig;
import com.lx.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientSymbolHitConfig.ClientSymbolHitConfigExtendPositionId;
import com.lx.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.roundCascade.*;
import com.lx.gs.math.core.slot.ConstMathSlot;

import java.util.List;

// 983 客端設定產生器
public class ClientConfigGtr_983 implements IClientConfigGtr {

    public ClientConfig generate() {

        ClientGameStateConfig gameState_0 = new ClientGameStateConfig(
                ConstMathSlot.GameStateType.DEVELOP_HJ_BASEGAME,
                ConstMathSlot.RoundType.CASCADE,
                new ClientRoundConfigCascade(
                        ConstMathSlot.RoundCascadeGameType.DEVELOP_HJ_BASEGAME,
                        new ClientSymbolHitConfig(ConstPgrSlot.ClientSymbolHitType.POSITION_ID, new ClientSymbolHitConfigExtendPositionId()),
                        new ClientCascadeClusterConfig(
                                ConstMathSlot.CascadeClusterType.MJHL,
                                new ClientCascadeClusterConfigExtend(),
                                new ClientCascadeConfig(
                                        ConstMathSlot.CascadeType.MJHL,
                                        new ClientCascadeConfigExtendMjhl(
                                                new ClientScreenConfig(ConstPgrSlot.ScreenOffsetType.OFFSET_ONE, 1.0)
                                        ),
                                        ConstPgrSlot.AddSymbolOrderType.REVERSE,
                                        new ClientScreenConfig(ConstPgrSlot.ScreenOffsetType.OFFSET_ONE, 1.0),
                                        new ClientSymbolHitConfig(ConstPgrSlot.ClientSymbolHitType.POSITION_ID, new ClientSymbolHitConfigExtendPositionId())
                                )
                        )
                )
        );

        return new ClientConfig(List.of(gameState_0));
    }
}
