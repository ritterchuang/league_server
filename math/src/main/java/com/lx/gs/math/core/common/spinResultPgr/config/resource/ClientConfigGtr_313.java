package com.lx.gs.math.core.common.spinResultPgr.config.resource;

import com.lx.gs.math.core.common.spinResultPgr.ConstPgrSlot;
import com.lx.gs.math.core.common.spinResultPgr.config.IClientConfigGtr;
import com.lx.gs.math.core.common.spinResultPgr.config.enity.ClientConfig;
import com.lx.gs.math.core.common.spinResultPgr.config.enity.ClientGameStateConfig;
import com.lx.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientScreenConfig.ClientScreenConfig;
import com.lx.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientSymbolHitConfig.ClientSymbolHitConfig;
import com.lx.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientSymbolHitConfig.ClientSymbolHitConfigExtendPositionBoolean;
import com.lx.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.roundNormal.ClientRoundConfigNormal;
import com.lx.gs.math.core.slot.ConstMathSlot;

import java.util.List;

public class ClientConfigGtr_313 implements IClientConfigGtr {

    public ClientConfig generate() {

        ClientGameStateConfig gameState_0 = new ClientGameStateConfig(
                ConstMathSlot.GameStateType.LLL_BASEGAME,
                ConstMathSlot.RoundType.NORMAL,
                new ClientRoundConfigNormal(
                        ConstMathSlot.RoundNormalGameType.LLL_BASEGAME,
                        new ClientScreenConfig(ConstPgrSlot.ScreenOffsetType.OFFSET_NONE),
                        new ClientSymbolHitConfig(ConstPgrSlot.ClientSymbolHitType.POSITION_BOOLEAN, new ClientSymbolHitConfigExtendPositionBoolean())
                )
        );

        return new ClientConfig(List.of(gameState_0));
    }
}
