package org.lsj.gs.math.core.common.spinResultPgr.config.resource;

import org.lsj.gs.math.core.common.spinResultPgr.ConstPgrSlot;
import org.lsj.gs.math.core.common.spinResultPgr.config.IClientConfigGtr;
import org.lsj.gs.math.core.common.spinResultPgr.config.enity.ClientConfig;
import org.lsj.gs.math.core.common.spinResultPgr.config.enity.ClientGameStateConfig;
import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientScreenConfig.ClientScreenConfig;
import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientSymbolHitConfig.ClientSymbolHitConfig;
import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientSymbolHitConfig.ClientSymbolHitConfigExtendPositionBoolean;
import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.roundNormal.ClientRoundConfigNormal;
import org.lsj.gs.math.core.slot.ConstMathSlot;

import java.util.List;

// 301 客端設定產生器
public class ClientConfigGtr_301 implements IClientConfigGtr {

    public ClientConfig generate() {

        ClientGameStateConfig gameState_0 = new ClientGameStateConfig(
                ConstMathSlot.GameStateType.PXKY_BASEGAME,
                ConstMathSlot.RoundType.NORMAL,
                new ClientRoundConfigNormal(
                        ConstMathSlot.RoundNormalGameType.PXKY_BASEGAME,
                        new ClientScreenConfig(ConstPgrSlot.ScreenOffsetType.OFFSET_NONE),
                        new ClientSymbolHitConfig(ConstPgrSlot.ClientSymbolHitType.POSITION_BOOLEAN, new ClientSymbolHitConfigExtendPositionBoolean())
                )
        );

        return new ClientConfig(List.of(gameState_0));
    }
}
