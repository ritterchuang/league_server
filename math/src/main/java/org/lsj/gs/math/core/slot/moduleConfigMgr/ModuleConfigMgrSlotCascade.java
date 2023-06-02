package org.lsj.gs.math.core.slot.moduleConfigMgr;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigSlot;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfig;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.enity.CascadeClusterHlrMgrConfig;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.config.CascadeClusterHlrConfig;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.config.CascadeHlrConfig;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeReadyHandHlrMgr.enity.CascadeReadyHandHlrConfigCluster;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeReadyHandHlrMgr.enity.CascadeReadyHandHlrMgrConfig;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeSpecialFeatureHlrMgr.enity.config.CascadeSpecialFeatureHlrConfigCluster;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeSpecialFeatureHlrMgr.enity.config.CascadeSpecialFeatureHlrMgrConfig;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.eliminateCtr.enity.config.EliminateCtrConfig;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.config.GameCtrConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.readyHandConfig.ReadyHandConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.readyHandConfig.ReadyHandConfigCluster;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.RoundConfigCascade;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.specialFeatureConfig.SpecialFeatureConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.specialFeatureConfig.SpecialFeatureConfigCluster;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.config.ReadyHandHlrConfig;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrConfig;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 老虎機模組設定管理器 消除類型 TODO
public class ModuleConfigMgrSlotCascade extends AbstractModuleConfigMgrSlot {

    public ModuleConfigMgrSlotCascade(TableFieldConfig config) {
        super(config);
    }

    // 取得 消除集合處理器管理者設定
    public CascadeClusterHlrMgrConfig createCascadeClusterHlrMgrConfig(TableGameConfigSlot tableGameConfigSlot) {
        Map<Integer, CascadeClusterHlrConfig> conditionIdToCascadeClusterHlrConfig = new HashMap<>();

        List<GameStateConfig> gameStateConfigList = tableGameConfigSlot.getGameStateConfigCluster().getGameStateConfigList();
        for (int gameStateIndex = 0; gameStateIndex < gameStateConfigList.size(); gameStateIndex++) {
            RoundConfigCascade roundConfigCascade = (RoundConfigCascade) gameStateConfigList.get(gameStateIndex).getRoundConfig();

            conditionIdToCascadeClusterHlrConfig.put(gameStateIndex + 1,
                    new CascadeClusterHlrConfig(
                            roundConfigCascade.getCascadeClusterConfig().getCascadeClusterType(),
                            this.cascadeClusterHlrConfigExtendUtil.cascadeClusterHlrConfigExtend(roundConfigCascade),
                            new CascadeHlrConfig(
                                    roundConfigCascade.getCascadeClusterConfig().getCascadeConfig().getCascadeType(),
                                    this.cascadeHlrConfigExtendUtil.cascadeHlrConfigExtend(roundConfigCascade),
                                    roundConfigCascade.getCascadeClusterConfig().getCascadeConfig().getCascadeConfigExtend(),
                                    new ScreenGtrConfig(
                                            roundConfigCascade.getFrameConfig(),
                                            roundConfigCascade.getSymbolConfig(),
                                            roundConfigCascade.getReelConfig(),
                                            roundConfigCascade.getDampConfig()),
                                    new GameCtrConfig(
                                            roundConfigCascade.getFrameConfig(),
                                            roundConfigCascade.getSymbolConfig(),
                                            roundConfigCascade.getPayTableConfig(),
                                            roundConfigCascade.getGameConfig()
                                    ),
                                    roundConfigCascade.getCascadeClusterConfig().getCascadeConfig().getProgressConfig(),
                                    this.createCascadeSpecialFeatureHlrMgrConfig(roundConfigCascade.getFrameConfig(), roundConfigCascade.getSymbolConfig(), roundConfigCascade.getSpecialFeatureConfigCluster()),
                                    this.createCascadeReadyHandHlrMgrConfig(roundConfigCascade.getFrameConfig(), roundConfigCascade.getSymbolConfig(), roundConfigCascade.getReadyHandConfigCluster()),
                                    new EliminateCtrConfig(roundConfigCascade.getSymbolConfig(), roundConfigCascade.getCascadeClusterConfig().getCascadeConfig().getEliminateConfig())
                            )
                    ));
        }

        return new CascadeClusterHlrMgrConfig(conditionIdToCascadeClusterHlrConfig);
    }

    // 計算消除特殊事件處理器管理者設定
    private CascadeSpecialFeatureHlrMgrConfig createCascadeSpecialFeatureHlrMgrConfig(FrameConfig frameConfig, SymbolConfig symbolConfig, SpecialFeatureConfigCluster specialFeatureConfigCluster) {
        if (specialFeatureConfigCluster.getSpecialFeatureConfigList().isEmpty() == true) {
            return new CascadeSpecialFeatureHlrMgrConfig(new CascadeSpecialFeatureHlrConfigCluster());
        }

        List<SpecialFeatureHlrConfig> specialFeatureHlrConfigList = new ArrayList<>();
        for (SpecialFeatureConfig specialFeatureConfig: specialFeatureConfigCluster.getSpecialFeatureConfigList()) {
            specialFeatureHlrConfigList.add(new SpecialFeatureHlrConfig(frameConfig, symbolConfig, specialFeatureConfig));
        }

        return new CascadeSpecialFeatureHlrMgrConfig(new CascadeSpecialFeatureHlrConfigCluster(specialFeatureHlrConfigList));
    }

    // 計算消除聽牌事件處理器管理者設定
    private CascadeReadyHandHlrMgrConfig createCascadeReadyHandHlrMgrConfig(FrameConfig frameConfig, SymbolConfig symbolConfig, ReadyHandConfigCluster readyHandConfigCluster) {
        if (readyHandConfigCluster.getReadyHandConfigList().isEmpty() == true) {
            return new CascadeReadyHandHlrMgrConfig(new CascadeReadyHandHlrConfigCluster());
        }

        List<ReadyHandHlrConfig> readyHandHlrConfigList = new ArrayList<>();
        for (ReadyHandConfig readyHandConfig: readyHandConfigCluster.getReadyHandConfigList()) {
            readyHandHlrConfigList.add(new ReadyHandHlrConfig(frameConfig, symbolConfig, readyHandConfig));
        }

        return new CascadeReadyHandHlrMgrConfig(new CascadeReadyHandHlrConfigCluster(readyHandHlrConfigList));
    }
}
