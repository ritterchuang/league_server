package org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfigExtendFix;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.FrameCtrConfig;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResultExtendFix;

import java.util.ArrayList;
import java.util.List;

// 畫面計算者固定
public class FrameCtrFix implements IFrameCtr{
    private final FrameCtrConfig config; // 畫面計算者設定檔
    private final ConstMathSlot.FrameType frameType; // 畫面類型
    private final FrameConfigExtendFix configExtend; // 畫面額外設定
    private final ITableUtil tableUtil; // 牌桌工具包

    public FrameCtrFix(FrameCtrConfig frameCtrConfig, ITableUtil tableUtil) {
        this.config = frameCtrConfig;
        this.frameType = frameCtrConfig.getFrameConfig().getFrameType();
        this.configExtend = (FrameConfigExtendFix) frameCtrConfig.getFrameConfig().getFrameConfigExtend();
        this.tableUtil = tableUtil;
    }

    // 計算畫面結果
    @Override
    public FrameResult calculateFrameResult() {
        // 1. 取得畫面類型
        ConstMathSlot.FrameType frameType = this.frameType;

        // 2. 取得額外結果
        FrameResultExtendFix resultExtend = new FrameResultExtendFix(this.calculateScreenRowPerColumnList());

        return new FrameResult(frameType, resultExtend);
    }

    // 計算每欄列數
    private List<Integer> calculateScreenRowPerColumnList() {
        List<Integer> result = new ArrayList<>();

        this.configExtend.getScreenRowPerColumnList().forEach(row -> result.add(row));

        return result;
    }
}
