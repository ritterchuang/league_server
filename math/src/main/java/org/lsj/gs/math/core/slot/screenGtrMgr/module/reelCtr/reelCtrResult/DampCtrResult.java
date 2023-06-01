package org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult;

import java.util.Collections;
import java.util.List;

// 破框計篹者結果
public class DampCtrResult {
    private final List<List<Integer>> upperDampSymbolIdList; // 上破框資訊 [col][idx] = symbolId
    private final List<List<Integer>> lowerDampSymbolIdList; // 下破框資訊 [col][idx] = symbolId

    public DampCtrResult(List<List<Integer>> upperDampSymbolIdList, List<List<Integer>> lowerDampSymbolIdList) {
        this.upperDampSymbolIdList = upperDampSymbolIdList;
        this.lowerDampSymbolIdList = lowerDampSymbolIdList;
    }

    public DampCtrResult() {
        this.upperDampSymbolIdList = Collections.emptyList();
        this.lowerDampSymbolIdList = Collections.emptyList();
    }

    public boolean isExistUpperDamp() {
        return this.upperDampSymbolIdList.isEmpty() == false;
    }

    public boolean isExistLowerDamp() {
        return this.lowerDampSymbolIdList.isEmpty() == false;
    }

    public List<List<Integer>> getUpperDampSymbolIdList() {
        return upperDampSymbolIdList;
    }

    public List<List<Integer>> getLowerDampSymbolIdList() {
        return lowerDampSymbolIdList;
    }
}
