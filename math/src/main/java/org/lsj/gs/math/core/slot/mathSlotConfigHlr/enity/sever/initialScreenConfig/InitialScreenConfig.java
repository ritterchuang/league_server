package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.initialScreenConfig;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.clientReelResult.ClientReelResult;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResult;

// 初始畫面設定
public class InitialScreenConfig {
    private final ClientReelResult reelResult; // 輪帶表結果
    @JsonIgnore
    private final FrameResult frameResult; // 畫面結果

    private final ConstMathSlot.InitialScreenType initialScreenType; // 初始畫面類型
    private final InitialScreenConfigExtend initialScreenConfigExtend; // 初始畫面額外設定


    public InitialScreenConfig(ClientReelResult reelResult, FrameResult frameResult, ConstMathSlot.InitialScreenType initialScreenType, InitialScreenConfigExtend initialScreenConfigExtend) {
        this.reelResult = reelResult;
        this.frameResult = frameResult;
        this.initialScreenType = initialScreenType;
        this.initialScreenConfigExtend = initialScreenConfigExtend;
    }

    public ClientReelResult getReelResult() {
        return reelResult;
    }

    public FrameResult getFrameResult() {
        return frameResult;
    }

    public ConstMathSlot.InitialScreenType getInitialScreenType() {
        return initialScreenType;
    }

    public InitialScreenConfigExtend getInitialScreenConfigExtend() {
        return initialScreenConfigExtend;
    }
}
