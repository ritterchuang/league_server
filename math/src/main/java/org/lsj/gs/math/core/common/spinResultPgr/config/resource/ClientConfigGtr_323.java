package org.lsj.gs.math.core.common.spinResultPgr.config.resource;

import org.lsj.gs.math.core.common.spinResultPgr.ConstPgrSlot;
import org.lsj.gs.math.core.common.spinResultPgr.config.IClientConfigGtr;
import org.lsj.gs.math.core.common.spinResultPgr.config.enity.ClientConfig;
import org.lsj.gs.math.core.common.spinResultPgr.config.enity.ClientGameStateConfig;
import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientScreenConfig.ClientScreenConfig;
import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientSymbolHitConfig.ClientSymbolHitConfig;
import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientSymbolHitConfig.ClientSymbolHitConfigExtendPositionId;
import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.roundNormal.ClientRoundConfigNormal;
import org.lsj.gs.math.core.slot.ConstMathSlot;

import java.util.List;

// 323 客端設定產生器
public class ClientConfigGtr_323 implements IClientConfigGtr {

    public ClientConfig generate() {

        ClientGameStateConfig gameState_0 = new ClientGameStateConfig(
                ConstMathSlot.GameStateType.XJTB_BASEGAME,
                ConstMathSlot.RoundType.NORMAL,
                new ClientRoundConfigNormal(
                        ConstMathSlot.RoundNormalGameType.XJTB_BASEGAME,
                        new ClientScreenConfig(ConstPgrSlot.ScreenOffsetType.OFFSET_ONE, 1.0),
                        new ClientSymbolHitConfig(ConstPgrSlot.ClientSymbolHitType.POSITION_ID, new ClientSymbolHitConfigExtendPositionId())
                )
        );

        ClientGameStateConfig gameState_1 = new ClientGameStateConfig(
                ConstMathSlot.GameStateType.XJTB_FREEGAME,
                ConstMathSlot.RoundType.NORMAL,
                new ClientRoundConfigNormal(
                        ConstMathSlot.RoundNormalGameType.XJTB_FREEGAME,
                        new ClientScreenConfig(ConstPgrSlot.ScreenOffsetType.OFFSET_ONE, 1.0),
                        new ClientSymbolHitConfig(ConstPgrSlot.ClientSymbolHitType.POSITION_ID, new ClientSymbolHitConfigExtendPositionId())
                )
        );

        return new ClientConfig(List.of(gameState_0, gameState_1));
    }
}
