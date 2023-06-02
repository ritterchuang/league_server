package org.lsj.gs.math.core.slot.screenGtrMgr.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrConfig;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.FrameCtrFactory;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.IFrameCtr;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.IReelCtr;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.ReelCtrFactory;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.ReelCtrResult;

import java.util.List;

// 畫面產生器
public class ScreenGtr {
    private final ScreenGtrConfig config; // 設定檔
    private final ITableUtil tableUtil; // 牌桌工具包
    private final IFrameCtr frameCtr; // 畫面結果計算者
    private final IReelCtr reelCtr; // 輪帶表結果計算者

    public ScreenGtr(ScreenGtrConfig config, ITableUtil tableUtil) {
        this.config = config;
        this.tableUtil = tableUtil;
        this.frameCtr = new FrameCtrFactory().create(config.generateFrameCtrConfig(), tableUtil);
        this.reelCtr = new ReelCtrFactory().create(config.generateReelCtrConfig(), tableUtil);
    }

    // 取得畫面生產結果
    public ScreenGtrResult generateScreenGtrResult(ConstMathSlot.ReelRtpType reelRtpType) {
        // 1. 取得畫面結果
        FrameResult frameResult = this.frameCtr.calculateFrameResult();

        // 2. 取得輪帶表結果
        ReelCtrResult reelCtrResult = this.reelCtr.calculateReelCtrResult(reelRtpType, frameResult);

        // 3. 取得畫面標誌
        List<List<Integer>> screenSymbol = this.reelCtr.calculateScreenSymbol(frameResult, reelCtrResult);

        // 4. 回傳
        return new ScreenGtrResult(screenSymbol, frameResult, reelCtrResult);
    }

    // 取得畫面生產結果
    public ScreenGtrResult generateScreenGtrResultBySpecifyReelId(int reelId) {
        // 1. 取得畫面結果
        FrameResult frameResult = this.frameCtr.calculateFrameResult();

        // 2. 取得輪帶表結果
        ReelCtrResult reelCtrResult = this.reelCtr.calculateReelCtrResultBySpecifyReelId(reelId, frameResult);

        // 3. 取得畫面標誌
        List<List<Integer>> screenSymbol = this.reelCtr.calculateScreenSymbol(frameResult, reelCtrResult);

        // 4. 回傳
        return new ScreenGtrResult(screenSymbol, frameResult, reelCtrResult);
    }
}
