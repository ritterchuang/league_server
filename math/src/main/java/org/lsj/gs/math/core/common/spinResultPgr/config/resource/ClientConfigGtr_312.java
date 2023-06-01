package org.lsj.gs.math.core.common.spinResultPgr.config.resource;

import org.lsj.gs.math.core.common.spinResultPgr.ConstPgrSlot;
import org.lsj.gs.math.core.common.spinResultPgr.config.IClientConfigGtr;
import org.lsj.gs.math.core.common.spinResultPgr.config.enity.ClientConfig;
import org.lsj.gs.math.core.common.spinResultPgr.config.enity.ClientGameStateConfig;
import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientScreenConfig.ClientScreenConfig;
import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientSymbolHitConfig.ClientSymbolHitConfig;
import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientSymbolHitConfig.ClientSymbolHitConfigExtendPositionId;
import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.roundCascade.*;
import org.lsj.gs.math.core.slot.ConstMathSlot;

import java.util.List;

// 312 客端設定產生器
public class ClientConfigGtr_312 implements IClientConfigGtr {

    public ClientConfig generate() {

        ClientGameStateConfig gameState_0 = new ClientGameStateConfig(
                ConstMathSlot.GameStateType.DGRY_BASEGAME,
                ConstMathSlot.RoundType.CASCADE,
                new ClientRoundConfigCascade(
                        ConstMathSlot.RoundCascadeGameType.DGRY_BASEGAME,
                        new ClientSymbolHitConfig(ConstPgrSlot.ClientSymbolHitType.POSITION_ID, new ClientSymbolHitConfigExtendPositionId()),
                        new ClientCascadeClusterConfig(
                                ConstMathSlot.CascadeClusterType.DGRY,
                                new ClientCascadeClusterConfigExtend(),
                                new ClientCascadeConfig(
                                        ConstMathSlot.CascadeType.DGRY,
                                        new ClientCascadeConfigExtendDgry(
                                                new ClientScreenConfig(ConstPgrSlot.ScreenOffsetType.OFFSET_ONE, 0.0)
                                        ),
                                        ConstPgrSlot.AddSymbolOrderType.REVERSE,
                                        new ClientScreenConfig(ConstPgrSlot.ScreenOffsetType.OFFSET_ONE, 0.0),
                                        new ClientSymbolHitConfig(ConstPgrSlot.ClientSymbolHitType.POSITION_ID, new ClientSymbolHitConfigExtendPositionId())
                                )
                        )
                )
        );

        ClientGameStateConfig gameState_1 = new ClientGameStateConfig(
                ConstMathSlot.GameStateType.DGRY_FREEGAME,
                ConstMathSlot.RoundType.CASCADE,
                new ClientRoundConfigCascade(
                        ConstMathSlot.RoundCascadeGameType.DGRY_FREEGAME,
                        new ClientSymbolHitConfig(ConstPgrSlot.ClientSymbolHitType.POSITION_ID, new ClientSymbolHitConfigExtendPositionId()),
                        new ClientCascadeClusterConfig(
                                ConstMathSlot.CascadeClusterType.DGRY,
                                new ClientCascadeClusterConfigExtend(),
                                new ClientCascadeConfig(
                                        ConstMathSlot.CascadeType.DGRY,
                                        new ClientCascadeConfigExtendDgry(
                                                new ClientScreenConfig(ConstPgrSlot.ScreenOffsetType.OFFSET_ONE, 0.0)
                                        ),
                                        ConstPgrSlot.AddSymbolOrderType.REVERSE,
                                        new ClientScreenConfig(ConstPgrSlot.ScreenOffsetType.OFFSET_ONE, 0.0),
                                        new ClientSymbolHitConfig(ConstPgrSlot.ClientSymbolHitType.POSITION_ID, new ClientSymbolHitConfigExtendPositionId())
                                )
                        )
                )
        );

        ClientGameStateConfig gameState_2 = new ClientGameStateConfig(
                ConstMathSlot.GameStateType.DGRY_BONUSGAME,
                ConstMathSlot.RoundType.CASCADE,
                new ClientRoundConfigCascade(
                        ConstMathSlot.RoundCascadeGameType.DGRY_BONUSGAME,
                        new ClientSymbolHitConfig(ConstPgrSlot.ClientSymbolHitType.POSITION_ID, new ClientSymbolHitConfigExtendPositionId()),
                        new ClientCascadeClusterConfig(
                                ConstMathSlot.CascadeClusterType.DGRY,
                                new ClientCascadeClusterConfigExtend(),
                                new ClientCascadeConfig(
                                        ConstMathSlot.CascadeType.DGRY,
                                        new ClientCascadeConfigExtendDgry(
                                                new ClientScreenConfig(ConstPgrSlot.ScreenOffsetType.OFFSET_ONE, 0.0)
                                        ),
                                        ConstPgrSlot.AddSymbolOrderType.REVERSE,
                                        new ClientScreenConfig(ConstPgrSlot.ScreenOffsetType.OFFSET_ONE, 0.0),
                                        new ClientSymbolHitConfig(ConstPgrSlot.ClientSymbolHitType.POSITION_ID, new ClientSymbolHitConfigExtendPositionId())
                                )
                        )
                )
        );

        ClientGameStateConfig gameState_3 = new ClientGameStateConfig(
                ConstMathSlot.GameStateType.DGRY_FREEGAME,
                ConstMathSlot.RoundType.CASCADE,
                new ClientRoundConfigCascade(
                        ConstMathSlot.RoundCascadeGameType.DGRY_FREEGAME,
                        new ClientSymbolHitConfig(ConstPgrSlot.ClientSymbolHitType.POSITION_ID, new ClientSymbolHitConfigExtendPositionId()),
                        new ClientCascadeClusterConfig(
                                ConstMathSlot.CascadeClusterType.DGRY,
                                new ClientCascadeClusterConfigExtend(),
                                new ClientCascadeConfig(
                                        ConstMathSlot.CascadeType.DGRY,
                                        new ClientCascadeConfigExtendDgry(
                                                new ClientScreenConfig(ConstPgrSlot.ScreenOffsetType.OFFSET_ONE, 0.0)
                                        ),
                                        ConstPgrSlot.AddSymbolOrderType.REVERSE,
                                        new ClientScreenConfig(ConstPgrSlot.ScreenOffsetType.OFFSET_ONE, 0.0),
                                        new ClientSymbolHitConfig(ConstPgrSlot.ClientSymbolHitType.POSITION_ID, new ClientSymbolHitConfigExtendPositionId())
                                )
                        )
                )
        );

        ClientGameStateConfig gameState_4 = new ClientGameStateConfig(
                ConstMathSlot.GameStateType.DGRY_BONUSGAME,
                ConstMathSlot.RoundType.CASCADE,
                new ClientRoundConfigCascade(
                        ConstMathSlot.RoundCascadeGameType.DGRY_BONUSGAME,
                        new ClientSymbolHitConfig(ConstPgrSlot.ClientSymbolHitType.POSITION_ID, new ClientSymbolHitConfigExtendPositionId()),
                        new ClientCascadeClusterConfig(
                                ConstMathSlot.CascadeClusterType.DGRY,
                                new ClientCascadeClusterConfigExtend(),
                                new ClientCascadeConfig(
                                        ConstMathSlot.CascadeType.DGRY,
                                        new ClientCascadeConfigExtendDgry(
                                                new ClientScreenConfig(ConstPgrSlot.ScreenOffsetType.OFFSET_ONE, 0.0)
                                        ),
                                        ConstPgrSlot.AddSymbolOrderType.REVERSE,
                                        new ClientScreenConfig(ConstPgrSlot.ScreenOffsetType.OFFSET_ONE, 0.0),
                                        new ClientSymbolHitConfig(ConstPgrSlot.ClientSymbolHitType.POSITION_ID, new ClientSymbolHitConfigExtendPositionId())
                                )
                        )
                )
        );


        return new ClientConfig(List.of(gameState_0, gameState_1, gameState_2, gameState_3, gameState_4));
    }
}
