package org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.enity;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.enity.displayResultExtend.DisplayResultExtend;
import org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.enity.displayResultExtend.DisplayResultExtendInvalid;

// 客端表演集合
public class DisplayResult {
    private final ConstMathFish.DisplayType displayType; // 客製表演結果類型
    private final DisplayResultExtend displayResultExtend; // 客製表演結果

    public DisplayResult() {
        this.displayType = ConstMathFish.DisplayType.INVALID;
        this.displayResultExtend = new DisplayResultExtendInvalid();
    }

    public DisplayResult(ConstMathFish.DisplayType displayType, DisplayResultExtend displayResultExtend) {
        this.displayType = displayType;
        this.displayResultExtend = displayResultExtend;
    }

    public ConstMathFish.DisplayType getDisplayType() {
        return displayType;
    }

    public DisplayResultExtend getDisplayResultExtend() {
        return displayResultExtend;
    }
}
