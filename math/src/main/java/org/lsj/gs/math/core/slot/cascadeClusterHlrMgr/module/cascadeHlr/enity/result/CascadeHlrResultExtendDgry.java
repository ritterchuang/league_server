package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result;

import java.util.List;

// 消除處理者額外結果 帝国榮耀 TODO
public class CascadeHlrResultExtendDgry extends CascadeHlrResultExtend{
    private final List<List<Integer>> dampScreenSymbolAfterCascade; // 消除後剩餘畫面
    private final int screenMultiplier; // 畫面倍數

    public CascadeHlrResultExtendDgry(List<List<Integer>> dampScreenSymbolAfterCascade, int screenMultiplier) {
        this.dampScreenSymbolAfterCascade = dampScreenSymbolAfterCascade;
        this.screenMultiplier = screenMultiplier;
    }

    public List<List<Integer>> getDampScreenSymbolAfterCascade() { return dampScreenSymbolAfterCascade; }
    public int getScreenMultiplier() { return screenMultiplier; }
}
