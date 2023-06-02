package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.progressResultPgr;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.progressResultPgr.enity.*;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResultExtendCascade;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResultExtendRound;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResultExtendTriggerRound;

// 遊戲進度結果包裝者
public class ProgressResultPgr {

    // 包裝客製遊戲進度結果
    public ProgressResult packageProgressResult(ProgressHlrResult progressHlrResult) {
        return new ProgressResult(
                progressHlrResult.isMaxProgress(),
                this.calculateProgressType(progressHlrResult),
                this.calculateProgressResultExtend(progressHlrResult));
    }

    // 依照使用類型轉換給客端
    private ConstMathSlot.ClientProgressType calculateProgressType(ProgressHlrResult progressHlrResult) {
        switch (progressHlrResult.getProgressType()) {
            case ROUND: return ConstMathSlot.ClientProgressType.ROUND;
            case TRIGGER_ROUND: return ConstMathSlot.ClientProgressType.TRIGGER_ROUND;
            case CASCADE: return ConstMathSlot.ClientProgressType.CASCADE;
            default: return ConstMathSlot.ClientProgressType.INVALID;
        }
    }

    // 計算客製遊戲進度結果
    public ProgressResultExtend calculateProgressResultExtend(ProgressHlrResult progressHlrResult) {

        switch (progressHlrResult.getProgressType()) {
            case ROUND: return new ProgressResultExtendRound(((ProgressHlrResultExtendRound)progressHlrResult.getProgressHlrResultExtend()).getRoundProgress());
            case TRIGGER_ROUND: return new ProgressResultExtendTriggerRound(((ProgressHlrResultExtendTriggerRound)progressHlrResult.getProgressHlrResultExtend()).getRoundProgress());
            case CASCADE: return new ProgressResultExtendCascade(((ProgressHlrResultExtendCascade)progressHlrResult.getProgressHlrResultExtend()).getCascadeProgress());
            default: return new ProgressResultExtend();
        }
    }
}
