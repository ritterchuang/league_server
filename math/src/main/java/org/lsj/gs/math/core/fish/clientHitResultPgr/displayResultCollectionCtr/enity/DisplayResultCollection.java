package org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.enity;

// 客端表演集合
public class DisplayResultCollection {
    private final DisplayResult[] displayResultArray; // 客製表演陣列

    public DisplayResultCollection() {
        this.displayResultArray = new DisplayResult[0];
    }

    public DisplayResultCollection(DisplayResult[] displayResultArray) {
        this.displayResultArray = displayResultArray;
    }

    public DisplayResult[] getDisplayResultArray() {
        return displayResultArray;
    }
}
