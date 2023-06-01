package com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.eliminateCtr;

import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.eliminateCtr.enity.config.EliminateCtrConfig;

// 新增消除計算者工廠
public class EliminateCtrFactory {

    public IEliminateCtr create(EliminateCtrConfig eliminateCtrConfig, ITableUtil tableUtil) {
        switch(eliminateCtrConfig.getEliminationConfig().getEliminationType()){
            case IF_HIT: return new EliminateCtrIfHit(eliminateCtrConfig, tableUtil);
            case NORMAL:
            default: return new EliminateCtrNormal(eliminateCtrConfig, tableUtil);
        }
    }
}
