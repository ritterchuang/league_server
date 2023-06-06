package org.lsj.gs.math.core.slot.moduleConfigMgr;

import org.lsj.enums.GameId;
import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;

// 老虎機模組設定工廠
public class ModuleConfigMgrSlotFactory {

    // 創建 老虎機模組設定管理者
    public AbstractModuleConfigMgrSlot create(TableFieldConfig config) {
        switch (GameId.fromId(config.getGameId())) {
            case LUCKY777:
            case PXKY_JAVA:
            case DYDB_JAVA:
            case SDZW_JAVA:
            case WL_JAVA:
            case BSZX_JAVA:
            case CCC_JAVA:
            case SBXC_JAVA:
            case SN_JAVA:
            case SWZS_JAVA:
            case LLL_JAVA:
            case XZCQ_JAVA:
            case HJXB_JAVA:
            case OLLDBZ_JAVA:
            case XJTB_JAVA:
            case ZCJZ_JAVA:
            case _MODEL_HJ_JAVA: return new ModuleConfigMgrSlotNormal(config);
            case MJWS_JAVA:
            case LMJJC_JAVA:
            case DGRY_JAVA:
            case CJWP_JAVA:
            case _DEVELOP_HJ_JAVA: return new ModuleConfigMgrSlotCascade(config);
            default: return new ModuleConfigMgrSlotDefault(config);
        }
    }
}
