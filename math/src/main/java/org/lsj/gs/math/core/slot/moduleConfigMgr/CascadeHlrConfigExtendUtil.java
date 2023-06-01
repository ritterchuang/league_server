package org.lsj.gs.math.core.slot.moduleConfigMgr;

import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.config.CascadeHlrConfigExtend;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.config.CascadeHlrConfigExtendCjwp;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.config.CascadeHlrConfigExtendDgry;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.config.CascadeHlrConfigExtendMjhl;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeScreenGtr.CascadeScreenGtrConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.cascadeConfig.cascadeConfigExtend.CascadeConfigExtendCjwp;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.cascadeConfig.cascadeConfigExtend.CascadeConfigExtendDgry;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.cascadeConfig.cascadeConfigExtend.CascadeConfigExtendMjhl;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.RoundConfigCascade;

// 虎機消除處理者額外設定工具包
public class CascadeHlrConfigExtendUtil {

    // 消除處理者額外設定檔
    public CascadeHlrConfigExtend cascadeHlrConfigExtend (RoundConfigCascade roundConfigCascade) {
        switch (roundConfigCascade.getCascadeClusterConfig().getCascadeConfig().getCascadeType()) {
            case MJHL: return new CascadeHlrConfigExtendMjhl(
                    new CascadeScreenGtrConfig(
                            roundConfigCascade.getFrameConfig(),
                            roundConfigCascade.getSymbolConfig(),
                            ((CascadeConfigExtendMjhl)(roundConfigCascade.getCascadeClusterConfig().getCascadeConfig().getCascadeConfigExtend())).getCascadeReelConfig(),
                            roundConfigCascade.getDampConfig()),
                    roundConfigCascade.getSymbolConfig());
            case DGRY: return new CascadeHlrConfigExtendDgry(
                    new CascadeScreenGtrConfig(
                            roundConfigCascade.getFrameConfig(),
                            roundConfigCascade.getSymbolConfig(),
                            ((CascadeConfigExtendDgry)(roundConfigCascade.getCascadeClusterConfig().getCascadeConfig().getCascadeConfigExtend())).getCascadeReelConfig(),
                            roundConfigCascade.getDampConfig()),
                    roundConfigCascade.getSymbolConfig());
            case CJWP: return new CascadeHlrConfigExtendCjwp(
                    new CascadeScreenGtrConfig(
                            roundConfigCascade.getFrameConfig(),
                            roundConfigCascade.getSymbolConfig(),
                            ((CascadeConfigExtendCjwp)(roundConfigCascade.getCascadeClusterConfig().getCascadeConfig().getCascadeConfigExtend())).getCascadeReelConfig(),
                            roundConfigCascade.getDampConfig()),
                    roundConfigCascade.getSymbolConfig());
            case NONE:
            default: return new CascadeHlrConfigExtend();
        }
    }
}