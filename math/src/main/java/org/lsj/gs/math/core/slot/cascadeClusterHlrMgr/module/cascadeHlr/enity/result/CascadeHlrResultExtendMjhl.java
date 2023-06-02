package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result;

import java.util.List;

// 消除處理者額外結果 麻將胡了 TODO
public class CascadeHlrResultExtendMjhl extends CascadeHlrResultExtend{
    private final List<List<Boolean>> beforeCascadeGoldenPosition; // 消除前黃金位置
    private final List<List<Boolean>> afterCascadeGoldenPosition; // 消除後黃金位置
    private final List<List<Boolean>> changeToWildPosition; // 得分後，轉變wild位置
    private final List<List<Integer>> dampScreenSymbolAfterCascade; // 消除後剩餘畫面
    private final int cascadeMultiplier; // 消除倍數

    public CascadeHlrResultExtendMjhl(List<List<Boolean>> beforeCascadeGoldenPosition, List<List<Boolean>> afterCascadeGoldenPosition, List<List<Boolean>> changeToWildPosition, List<List<Integer>> dampScreenSymbolAfterCascade, int cascadeMultiplier) {
        this.beforeCascadeGoldenPosition = beforeCascadeGoldenPosition;
        this.changeToWildPosition = changeToWildPosition;
        this.afterCascadeGoldenPosition = afterCascadeGoldenPosition;
        this.dampScreenSymbolAfterCascade = dampScreenSymbolAfterCascade;
        this.cascadeMultiplier = cascadeMultiplier;
    }

    public List<List<Boolean>> getBeforeCascadeGoldenPosition() {
        return beforeCascadeGoldenPosition;
    }

    public List<List<Boolean>> getChangeToWildPosition() {
        return changeToWildPosition;
    }

    public List<List<Boolean>> getAfterCascadeGoldenPosition() {
        return afterCascadeGoldenPosition;
    }

    public List<List<Integer>> getDampScreenSymbolAfterCascade() {
        return dampScreenSymbolAfterCascade;
    }

    public int getCascadeMultiplier() {
        return cascadeMultiplier;
    }
}
