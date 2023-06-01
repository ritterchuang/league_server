package org.lsj.gs.math.core.common.gameAdjust.module.shuffle;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 抽象發牌
public class AbstractShuffle {
    private final AlgorithmTypeCtr algorithmTypeCtr; // 演算法類型計算器
    private ConstMathCommon.ShuffleType shuffleType; // 開牌類型

    public AbstractShuffle(AlgorithmTypeCtr algorithmTypeCtr, ITableUtil tableUtil) {
        this.algorithmTypeCtr = algorithmTypeCtr;
        this.shuffleType = ConstMathCommon.ShuffleType.INVALID;
    }

    public ConstMathCommon.ShuffleType getShuffleType() {
        return this.shuffleType;
    }

    public void setShuffleType(ConstMathCommon.ShuffleType shuffleType) {
        this.shuffleType = shuffleType;
    }
}
