package org.lsj.gs.math.core.slot.moduleConfigMgr;

import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.config.CascadeClusterHlrConfigExtend;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.config.CascadeClusterHlrConfigExtendCjwp;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.config.CascadeClusterHlrConfigExtendDgry;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.config.CascadeClusterHlrConfigExtendMjhl;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.RoundConfigCascade;

// 虎機消除集合處理者額外設定工具包
public class CascadeClusterHlrConfigExtendUtil {

    // 消除集合處理者額外設定檔
    public CascadeClusterHlrConfigExtend cascadeClusterHlrConfigExtend (RoundConfigCascade roundConfigCascade) {
        switch (roundConfigCascade.getCascadeClusterConfig().getCascadeClusterType()) {

            case MJHL: return new CascadeClusterHlrConfigExtendMjhl(
                    roundConfigCascade.getCascadeClusterConfig().getCascadeClusterConfigExtend(),
                    roundConfigCascade.getReelConfig());
            case DGRY: return new CascadeClusterHlrConfigExtendDgry(
                    roundConfigCascade.getCascadeClusterConfig().getCascadeClusterConfigExtend(),
                    roundConfigCascade.getReelConfig());
            case CJWP: return new CascadeClusterHlrConfigExtendCjwp(
                    roundConfigCascade.getCascadeClusterConfig().getCascadeClusterConfigExtend(),
                    roundConfigCascade.getReelConfig());
            default: return new CascadeClusterHlrConfigExtend();
        }
    }
}
