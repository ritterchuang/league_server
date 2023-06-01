package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtr;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.config.CascadeClusterHlrConfig;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.config.CascadeClusterHlrConfigExtendDgry;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.result.CascadeClusterHlrResult;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.result.CascadeClusterHlrResultExtendDgry;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.CascadeHlrFactory;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.ICascadeHlr;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.utils.MathUtil;

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
