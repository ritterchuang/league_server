package org.lsj.gs.mathBoardGtr.core.slot;

import org.lsj.gs.mathBoardGtr.config.entity.BoardGtrConfig;
import org.lsj.gs.mathBoardGtr.config.entity.ConstBoardGtr;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.ConstPlayGameField;
import org.lsj.gs.mathBoardGtr.config.module.BoardGtrConfigFactory;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class BoardGtrSlotNormalTest {
    BoardGtrSlotNormal boardGtr;

    @BeforeEach
    void setUp() {
        // 1. 準備建構參數
        BoardGtrConfig boardGtrConfig = new BoardGtrConfigFactory().create(
                ConstPlayGameField.PlayGameFieldResource.R993_MODEL_HJ_JAVA_NORMAL,
                ConstBoardGtr.BoardGtrControlAlgorithmType.NATURE);

        // 2. 建構局產生器
        this.boardGtr = new BoardGtrSlotNormal(
                boardGtrConfig.getGamePlayer(),
                new PoolCtr(boardGtrConfig.getPoolCtrConfig()),
                boardGtrConfig.getPlayGameFieldConfig(),
                boardGtrConfig.getControlAlgorithmConfig()
        );
    }

    // 給定第一次使用的亂數，執行計算一局結果，則使用後的亂數與第一次相同(即可確保每局亂數重置性)
    @Test
    void given_first_used_rnd_when_calculateOneBoardResult_then_used_the_same_rnd() {
        // 1. given
        this.boardGtr.calculateOneBoardResult();
        ArrayList<Integer> usedRndList = new ArrayList<>(this.boardGtr.tableUtil.getMainRandomUtil().getRandomNumberUsedList());
        this.boardGtr.tableUtil.getMainRandomUtil().setRandomNumberList(usedRndList);

        // 2. when
        this.boardGtr.calculateOneBoardResult();

        // 3. then
        Assertions.assertEquals(usedRndList, this.boardGtr.tableUtil.getMainRandomUtil().getRandomNumberUsedList());
    }
}