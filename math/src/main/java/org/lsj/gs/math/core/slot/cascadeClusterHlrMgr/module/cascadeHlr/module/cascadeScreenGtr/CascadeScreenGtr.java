package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeScreenGtr;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.eliminateCtr.enity.result.EliminateCtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.FrameCtrFactory;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.IFrameCtr;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.IReelCtr;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.ReelCtrFactory;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.ReelCtrResult;

import java.util.List;

// 消除畫面產生器
public class CascadeScreenGtr {
    private final CascadeScreenGtrConfig config; // 消除畫面產生器設定檔
    private final ITableUtil tableUtil; // 牌桌工具包
    private final IFrameCtr frameCtr; // 畫面結果計算者
    private final IReelCtr reelCtr; // 輪帶表結果計算者

    public CascadeScreenGtr(CascadeScreenGtrConfig config, ITableUtil tableUtil) {
        this.config = config;
        this.tableUtil = tableUtil;
        this.frameCtr = new FrameCtrFactory().create(config.generateFrameCtrConfig(), tableUtil);
        this.reelCtr = new ReelCtrFactory().create(config.generateReelCtrConfig(), tableUtil);
    }

    /* 消除相關 */
    // 產生消除畫面結果
    public ScreenGtrResult generateCascadeScreenGtrResult(
            ConstMathSlot.ReelRtpType reelRtpType,
            ScreenGtrResult beforeScreenGtrResult,
            EliminateCtrResult eliminateCtrResult) {

        // 1. 取得畫面結果
        FrameResult frameResult = this.frameCtr.calculateFrameResult();

        // 2. 取得輪帶表結果
        ReelCtrResult reelCtrResult = this.reelCtr.calculateCascadeReelCtrResult(reelRtpType, beforeScreenGtrResult, eliminateCtrResult);

        // 3. 取得畫面標誌
        List<List<Integer>> screenSymbol = this.reelCtr.calculateScreenSymbol(frameResult, reelCtrResult);

        // 4. 回傳
        return new ScreenGtrResult(screenSymbol, frameResult, reelCtrResult);
    }

    // 產生消除畫面結果(Damp標誌不掉落)
    public ScreenGtrResult generateCascadeNoDampScreenGtrResult(
            ConstMathSlot.ReelRtpType reelRtpType,
            ScreenGtrResult beforeScreenGtrResult,
            EliminateCtrResult eliminateCtrResult) {

        // 1. 取得畫面結果
        FrameResult frameResult = this.frameCtr.calculateFrameResult();

        // 2. 取得輪帶表結果(Damp標誌不掉落)
        ReelCtrResult reelCtrResult = this.reelCtr.calculateNoDampCascadeReelCtrResult(reelRtpType, beforeScreenGtrResult, eliminateCtrResult);

        // 3. 取得畫面標誌
        List<List<Integer>> screenSymbol = this.reelCtr.calculateScreenSymbol(frameResult, reelCtrResult);

        // 4. 回傳
        return new ScreenGtrResult(screenSymbol, frameResult, reelCtrResult);
    }
}
