package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeProgressHlr;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.eliminateCtr.enity.result.EliminateCtrResult;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfig;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfigExtendCascade;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.input.AbstractProgressHlrTotalInput;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.input.ProgressHlrTotalInputCascade;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.CascadeProgress;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResultExtendCascade;
import org.lsj.gs.math.core.slot.progressHlrMgr.module.progressUtil.IProgressHlrUtil;
import org.lsj.gs.math.core.slot.progressHlrMgr.module.progressUtil.ProgressHlrUtil;

import java.util.List;

// 消除進度處理者
public class CascadeProgressHlr implements ICascadeProgressHlr, IProgressHlrUtil {
    private final ConstMathSlot.ProgressType progressType; // 遊戲進度類型
    private final ProgressConfigExtendCascade configExtend; // 遊戲進度額外設定
    private final ProgressHlrUtil progressHlrUtil; // 進度處理工具包
    private final ITableUtil tableUtil; // 牌桌工具包

    public CascadeProgressHlr(ProgressConfig progressConfig, ITableUtil tableUtil) {
        this.progressType = progressConfig.getProgressType();
        this.configExtend = (ProgressConfigExtendCascade) progressConfig.getProgressConfigExtend();
        this.progressHlrUtil = new ProgressHlrUtil();
        this.tableUtil = tableUtil;
    }

    //* CascadeHlr 使用方法 *//
    // 計算遊戲進度處理者結果
    public ProgressHlrResult calculateProgressHlrResult(int cascadeIndex, List<CascadeHlrResult> preCascadeHlrResultList, EliminateCtrResult currentEliminateCtrResult) {
        // 1. 取得進度處理者參數
        ProgressHlrTotalInputCascade inputCascade = new ProgressHlrTotalInputCascade(cascadeIndex, preCascadeHlrResultList, currentEliminateCtrResult);

        // 2. 計算 當前場次
        int currentCascadeTimes = this.progressHlrUtil.calculateCurrentProgress(inputCascade);

        // 3. 計算總場次
        int totalCascadeTimes = this.calculateTotalProgress(inputCascade);

        // 4. 計算增加場次
        int addCascadeTimes = this.calculateAddProgress(totalCascadeTimes, inputCascade);

        // 5. 計算最大場次標誌
        boolean isMaxProgress = this.calculateMaxProgress(totalCascadeTimes);

        // 6. 回傳結果
        return new ProgressHlrResult(
                isMaxProgress,
                this.progressType,
                new ProgressHlrResultExtendCascade(
                        new CascadeProgress(currentCascadeTimes, addCascadeTimes, totalCascadeTimes)));
    }


    //* ProgressHlrUtl 相關 *//
    @Override
    public int calculateCurrentProgress(AbstractProgressHlrTotalInput progressHlrTotalInput) {
        return this.progressHlrUtil.calculateCurrentProgress(progressHlrTotalInput);
    }

    @Override
    public int calculateTotalProgress(AbstractProgressHlrTotalInput progressHlrTotalInput) {
        ProgressHlrTotalInputCascade inputCascade = (ProgressHlrTotalInputCascade) progressHlrTotalInput;

        if (inputCascade.getProcessIndex() == 0) {
            return this.configExtend.getDefaultCascadeTimes();
        }

        ProgressHlrResultExtendCascade preCascadeResult = (ProgressHlrResultExtendCascade)(inputCascade.getPreCascadeHlrResultList().get(inputCascade.getProcessIndex() - 1).getProgressHlrResult().getProgressHlrResultExtend());
        return preCascadeResult.getCascadeProgress().getTotalTimes() + preCascadeResult.getCascadeProgress().getAddTimes();
    }

    @Override
    public int calculateAddProgress(int totalProcess, AbstractProgressHlrTotalInput progressHlrTotalInput) {
        // 1. 取出特殊事件列表
        ProgressHlrTotalInputCascade inputCascade = (ProgressHlrTotalInputCascade) progressHlrTotalInput;
        EliminateCtrResult currentEliminateCtrResult = inputCascade.getCurrentEliminateCtrResult();

        // 2. 若有消除，增加消除次數
        if (currentEliminateCtrResult.getEliminatePosition().stream().flatMap(List::stream).anyMatch(x -> x)) {
            return Math.min(this.configExtend.getAddCascadeTimes(), this.configExtend.getMaxCascadeTimes() - totalProcess);
        }

        // 3. 若無滿足，回傳0
        return 0;
    }

    @Override
    public boolean calculateMaxProgress(int totalProcess) {
        return this.progressHlrUtil.calculateMaxProgress(totalProcess, this.configExtend.getMaxCascadeTimes());
    }
}
