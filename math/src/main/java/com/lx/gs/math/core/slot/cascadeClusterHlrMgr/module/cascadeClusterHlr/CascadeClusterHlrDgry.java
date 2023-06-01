package com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr;

import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.core.slot.ConstMathSlot;
import com.lx.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtr;
import com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.config.CascadeClusterHlrConfig;
import com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.config.CascadeClusterHlrConfigExtendDgry;
import com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.result.CascadeClusterHlrResult;
import com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.result.CascadeClusterHlrResultExtendDgry;
import com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.CascadeHlrFactory;
import com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.ICascadeHlr;
import com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import com.lx.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import com.lx.utils.MathUtil;

import java.util.List;

// 消除集合處理者 帝国榮耀
public class CascadeClusterHlrDgry implements ICascadeClusterHlr{
    private final CascadeClusterHlrConfig config; // 消除集合處理者設定黨
    private final ConstMathSlot.CascadeClusterType cascadeClusterType; // 消除集合類型
    private final CascadeClusterHlrConfigExtendDgry configExtend; // 消除集合額外設定
    private final ICascadeHlr cascadeHlr; // 消除處理者
    private final ITableUtil tableUtil; // 牌桌工具包


    public CascadeClusterHlrDgry(CascadeClusterHlrConfig config, AccumulateWinCtr accumulateWinCtr, ITableUtil tableUtil) {
        this.config = config;
        this.cascadeClusterType = config.getCascadeClusterType();
        this.configExtend = (CascadeClusterHlrConfigExtendDgry) config.getHlrConfigExtend();
        this.tableUtil = tableUtil;
        this.cascadeHlr = new CascadeHlrFactory().create(config.getCascadeHlrConfig(), accumulateWinCtr, tableUtil);
    }

    @Override
    public CascadeClusterHlrResult calculateCascadeClusterHlrResult(SpinRequest spinRequest, ConstMathSlot.ReelRtpType reelRtpType) {
        // 1. 計算 消除結果 列表
        List<CascadeHlrResult> cascadeHlrResultList = this.cascadeHlr.calculateCascadeHlrResultList(spinRequest, reelRtpType);

        // 2. 計算總得分
        double totalWin = this.calculateTotalWin(cascadeHlrResultList);

        // 3. 封裝回傳
        return new CascadeClusterHlrResult(MathUtil.moneyScale(totalWin), this.cascadeClusterType, new CascadeClusterHlrResultExtendDgry(), cascadeHlrResultList);
    }

    // 計算總得分
    private double calculateTotalWin(List<CascadeHlrResult> cascadeHlrResultList) {
        double result = 0.0;

        for (CascadeHlrResult cascadeHlrResult : cascadeHlrResultList) {
            result = MathUtil.add(result, cascadeHlrResult.getTotalWin());
        }

        return result;
    }
}
