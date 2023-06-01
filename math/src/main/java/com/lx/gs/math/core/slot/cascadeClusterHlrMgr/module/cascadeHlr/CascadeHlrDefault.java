package com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr;

import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.core.slot.ConstMathSlot;
import com.lx.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtr;
import com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.config.CascadeHlrConfig;
import com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import com.lx.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;

import java.util.List;

// 消除處理者 預設
public class CascadeHlrDefault extends AbstractCascadeHlr implements ICascadeHlr{
    public CascadeHlrDefault(CascadeHlrConfig cascadeHlrConfig, AccumulateWinCtr accumulateWinCtr, ITableUtil tableUtil) {
        super(cascadeHlrConfig, accumulateWinCtr, tableUtil);
    }
    @Override
    public List<CascadeHlrResult> calculateCascadeHlrResultList(SpinRequest spinRequest, ConstMathSlot.ReelRtpType reelRtpType) {
        return null;
    }
}
