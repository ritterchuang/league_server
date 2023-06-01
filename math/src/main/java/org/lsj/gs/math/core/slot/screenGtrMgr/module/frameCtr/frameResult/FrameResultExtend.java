package org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult;

import java.util.List;

// 客端客製畫面結果
public class FrameResultExtend {
    private final List<Integer> screenRowList; // 每欄列數

    public FrameResultExtend(List<Integer> screenRowPerColumnList) {
        this.screenRowList = screenRowPerColumnList;
    }

    public List<Integer> getScreenRowList() {
        return screenRowList;
    }
}
