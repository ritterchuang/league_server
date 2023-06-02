package org.lsj.gs.math.core.slot.moduleConfigMgr;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigSlot;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfig;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.config.GameCtrConfig;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.config.GameCtrMgrConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.dampConfig.DampConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.RoundConfigNormal;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrConfig;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrMgrConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 老虎機模組設定管理器 一般
public class ModuleConfigMgrSlotNormal extends AbstractModuleConfigMgrSlot {

    public ModuleConfigMgrSlotNormal(TableFieldConfig config) {
        super(config);
    }

    // 取得虎機算分者管理器設定
    public GameCtrMgrConfig createGameCtrMgrConfig(TableGameConfigSlot tableGameConfigSlot) {
        Map<Integer, GameCtrConfig> conditionIdToGameCtrConfig = new HashMap<>();

        List<GameStateConfig> gameStateConfigList = tableGameConfigSlot.getGameStateConfigCluster().getGameStateConfigList();
        for (int gameStateIndex = 0; gameStateIndex < gameStateConfigList.size(); gameStateIndex++) {
            RoundConfigNormal roundConfigNormal = (RoundConfigNormal) gameStateConfigList.get(gameStateIndex).getRoundConfig();
            conditionIdToGameCtrConfig.put(gameStateIndex + 1,
                    new GameCtrConfig(roundConfigNormal.getFrameConfig(),
                            roundConfigNormal.getSymbolConfig(),
                            roundConfigNormal.getPayTableConfig(),
                            roundConfigNormal.getGameConfig()));
        }

        return new GameCtrMgrConfig(conditionIdToGameCtrConfig);
    }

    // 取得虎機畫面生產者管理器設定
    public ScreenGtrMgrConfig createScreenGtrMgrConfig(TableGameConfigSlot tableGameConfigSlot) {
        Map<Integer, ScreenGtrConfig> conditionIdToScreenGtrConfig = new HashMap<>();

        List<GameStateConfig> gameStateConfigList = tableGameConfigSlot.getGameStateConfigCluster().getGameStateConfigList();
        for (int gameStateIndex = 0; gameStateIndex < gameStateConfigList.size(); gameStateIndex++) {
            RoundConfigNormal roundConfigNormal = (RoundConfigNormal) gameStateConfigList.get(gameStateIndex).getRoundConfig();
            conditionIdToScreenGtrConfig.put(gameStateIndex + 1,
                    new ScreenGtrConfig(
                            roundConfigNormal.getFrameConfig(),
                            roundConfigNormal.getSymbolConfig(),
                            roundConfigNormal.getReelConfig(),
                            new DampConfig(
                                    ConstMathSlot.DampType.ONE_DAMP,
                                    ConstMathSlot.DampType.ONE_DAMP
                            ))); // TODO
        }

        return new ScreenGtrMgrConfig(conditionIdToScreenGtrConfig);
    }

}
