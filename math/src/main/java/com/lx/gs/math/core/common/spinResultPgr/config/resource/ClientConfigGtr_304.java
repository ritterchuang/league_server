package com.lx.gs.math.core.common.spinResultPgr.config.resource;

import com.lx.gs.math.core.common.spinResultPgr.ConstPgrSlot;
import com.lx.gs.math.core.common.spinResultPgr.config.IClientConfigGtr;
import com.lx.gs.math.core.common.spinResultPgr.config.enity.ClientConfig;
import com.lx.gs.math.core.common.spinResultPgr.config.enity.ClientGameStateConfig;
import com.lx.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientScreenConfig.ClientScreenConfig;
import com.lx.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientSymbolHitConfig.ClientSymbolHitConfig;
import com.lx.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientSymbolHitConfig.ClientSymbolHitConfigExtendPositionId;
import com.lx.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.roundNormal.ClientRoundConfigNormal;
import com.lx.gs.math.core.slot.ConstMathSlot;

import java.util.List;

// 304 客端設定產生器
public class ClientConfigGtr_304 implements IClientConfigGtr {

    public ClientConfig generate() {

        ClientGameStateConfig gameState_0 = new ClientGameStateConfig(
                ConstMathSlot.GameStateType.WL_BASEGAME,
                ConstMathSlot.RoundType.NORMAL,
                new ClientRoundConfigNormal(
                        ConstMathSlot.RoundNormalGameType.WL_BASEGAME,
                        new ClientScreenConfig(ConstPgrSlot.ScreenOffsetType.OFFSET_ONE, 1.0),
                        new ClientSymbolHitConfig(ConstPgrSlot.ClientSymbolHitType.POSITION_ID, new ClientSymbolHitConfigExtendPositionId())
                )
        );

        ClientGameStateConfig gameState_1 = new ClientGameStateConfig(
                ConstMathSlot.GameStateType.WL_FREEGAME,
                ConstMathSlot.RoundType.NORMAL,
                new ClientRoundConfigNormal(
                        ConstMathSlot.RoundNormalGameType.WL_FREEGAME,
                        new ClientScreenConfig(ConstPgrSlot.ScreenOffsetType.OFFSET_ONE, 1.0),
                        new ClientSymbolHitConfig(ConstPgrSlot.ClientSymbolHitType.POSITION_ID, new ClientSymbolHitConfigExtendPositionId())
                )
        );

        return new ClientConfig(List.of(gameState_0, gameState_1));
    }
}
