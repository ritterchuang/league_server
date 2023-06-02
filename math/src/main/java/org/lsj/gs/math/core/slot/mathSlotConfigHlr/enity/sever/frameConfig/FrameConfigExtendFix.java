package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig;

import java.util.List;

// 客製遊戲畫面類型額外設定
public class FrameConfigExtendFix extends FrameConfigExtend {
    private final List<Integer> screenRowPerColumnList; // 每欄列數 [欄] = 列數

    public FrameConfigExtendFix(List<Integer> screenRowPerColumnList) {
        this.screenRowPerColumnList = screenRowPerColumnList;
    }

    public List<Integer> getScreenRowPerColumnList() {
        return screenRowPerColumnList;
    }
}
