package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtr;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.config.CascadeHlrConfig;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeReadyHandHlrMgr.CascadeReadyHandHlrMgr;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeSpecialFeatureHlrMgr.CascadeSpecialFeatureHlrMgr;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.eliminateCtr.EliminateCtrFactory;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.eliminateCtr.IEliminateCtr;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.GameCtrFactory;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.IGameCtr;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResultExtendCascade;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.ScreenGtr;

// 抽象消除處理者
public abstract class AbstractCascadeHlr implements ICascadeHlr{
    protected final CascadeHlrConfig config; // 消除處理者設定
    protected final ITableUtil tableUtil; // 牌桌工具包

    protected final ScreenGtr screenGtr; // 畫面生產器
    protected final IGameCtr gameCtr; // 畫面算分器
    protected final AccumulateWinCtr accumulateWinCtr; // 累積得分計算者

    protected final CascadeSpecialFeatureHlrMgr cascadeSpecialFeatureHlrMgr; // 消除特殊事件處理器管理者
    protected final CascadeReadyHandHlrMgr cascadeReadyHandHlrMgr; // 消除聽牌處理器管理者

    protected final IEliminateCtr eliminationCtr; // 消除位置計算者

    public AbstractCascadeHlr(CascadeHlrConfig cascadeHlrConfig, AccumulateWinCtr accumulateWinCtr, ITableUtil tableUtil) {
        this.config = cascadeHlrConfig;
        this.tableUtil = tableUtil;

        this.screenGtr = new ScreenGtr(cascadeHlrConfig.getScreenGtrConfig(), tableUtil);
        this.gameCtr = new GameCtrFactory().create(cascadeHlrConfig.getGameCtrConfig(), tableUtil);
        this.accumulateWinCtr = accumulateWinCtr;

        this.cascadeSpecialFeatureHlrMgr = new CascadeSpecialFeatureHlrMgr(cascadeHlrConfig.getCascadeSpecialFeatureHlrMgrConfig(), tableUtil);
        this.cascadeReadyHandHlrMgr = new CascadeReadyHandHlrMgr(cascadeHlrConfig.getCascadeReadyHandHlrMgrConfig(), tableUtil);

        this.eliminationCtr = new EliminateCtrFactory().create(cascadeHlrConfig.getEliminateCtrConfig(), tableUtil);
    }

    // 能否繼續消除
    public boolean isLastCascadeTime(ProgressHlrResult progressHlrResult) {
        // 1. 防呆
        if (progressHlrResult.getProgressType().equals(ConstMathSlot.ProgressType.INVALID)) {
            return false;
        }

        // 2. 轉型
        ProgressHlrResultExtendCascade resultExtendCascade = (ProgressHlrResultExtendCascade) progressHlrResult.getProgressHlrResultExtend();

        // 3. 判斷是否最後一次消除 [大於0，可繼續消除]
        return resultExtendCascade.getCascadeProgress().getAddTimes() <= 0;
    }
}
