package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result;

import java.util.List;
import java.util.Map;

// 消除處理者額外結果 超級王牌
public class CascadeHlrResultExtendCjwp extends CascadeHlrResultExtend{
    private final List<List<Boolean>> beforeCascadeGoldenPosition; // 消除前黃金位置
    private final List<List<Boolean>> afterCascadeGoldenPosition; // 消除後黃金位置
    private final List<List<Boolean>> changeToBigWildPosition; // 得分後，轉變大wild位置
    private final List<List<Boolean>> changeToSmallWildPosition; // 得分後，轉變小wild位置
    private final Map<Integer,Map<Integer,List<List<Boolean>>>> bigWildTransformPosition; // 本局畫面產生後，大wild位置對應Random Wild產生位置對照表
    private final List<List<Integer>> dampScreenSymbolAfterCascade; // 消除後剩餘畫面
    private final int cascadeMultiplier; // 消除倍數

    public CascadeHlrResultExtendCjwp(List<List<Boolean>> beforeCascadeGoldenPosition, List<List<Boolean>> afterCascadeGoldenPosition, List<List<Boolean>> changeToBigWildPosition, List<List<Boolean>> changeToSmallWildPosition, Map<Integer,Map<Integer,List<List<Boolean>>>> bigWildTransformPosition, List<List<Integer>> dampScreenSymbolAfterCascade, int cascadeMultiplier) {
        this.beforeCascadeGoldenPosition = beforeCascadeGoldenPosition;
        this.afterCascadeGoldenPosition = afterCascadeGoldenPosition;
        this.changeToBigWildPosition = changeToBigWildPosition;
        this.changeToSmallWildPosition = changeToSmallWildPosition;
        this.bigWildTransformPosition = bigWildTransformPosition;
        this.dampScreenSymbolAfterCascade = dampScreenSymbolAfterCascade;
        this.cascadeMultiplier = cascadeMultiplier;
    }

    public List<List<Boolean>> getBeforeCascadeGoldenPosition() {
        return beforeCascadeGoldenPosition;
    }

    public List<List<Boolean>> getAfterCascadeGoldenPosition() {
        return afterCascadeGoldenPosition;
    }

    public List<List<Boolean>> getChangeToBigWildPosition() {
        return changeToBigWildPosition;
    }

    public List<List<Boolean>> getChangeToSmallWildPosition() {
        return changeToSmallWildPosition;
    }

    public Map<Integer,Map<Integer,List<List<Boolean>>>> getBigWildTransformPosition() {
        return bigWildTransformPosition;
    }

    public List<List<Integer>> getDampScreenSymbolAfterCascade() {
        return dampScreenSymbolAfterCascade;
    }

    public int getCascadeMultiplier() {
        return cascadeMultiplier;
    }
}
