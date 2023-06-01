package org.lsj.gs.mathBoardGtr.games.lhd_java;

import org.lsj.gs.mathBoardGtr.config.entity.BoardGtrConfig;
import org.lsj.gs.mathBoardGtr.config.entity.ConstBoardGtr;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.ConstPlayGameField;
import org.lsj.gs.mathBoardGtr.config.module.BoardGtrConfigFactory;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class BoardGtrBaiRenLhdJavaNormalTest {
    BoardGtrBaiRenLhdJavaNormal boardGtr;

    @BeforeEach
    void setUp() {
        // 1. 準備建構參數
        BoardGtrConfig boardGtrConfig = new BoardGtrConfigFactory().create(
                ConstPlayGameField.PlayGameFieldResource.R111_LHD_JAVA_NORMAL,
                ConstBoardGtr.BoardGtrControlAlgorithmType.GRADIENT_NATURE);

        // 2. 建構局產生器
        this.boardGtr = new BoardGtrBaiRenLhdJavaNormal(
                boardGtrConfig.getGamePlayer(),
                new PoolCtr(boardGtrConfig.getPoolCtrConfig()),
                boardGtrConfig.getPlayGameFieldConfig(),
                boardGtrConfig.getControlAlgorithmConfig()
        );
    }

    // 給定執行計算一局結果，執行更新狀態置遊戲開始，則使用的亂數為空(即可確保每局亂數重置性)
    @Test
    void given_calculateOneBoardResult_when_updateGameBeginState_then_used_zero_rnd() {
        // 1. given
        this.boardGtr.calculateOneBoardResult();

        // 2. when
        this.boardGtr.updateGameBeginState();

        // 3. then
        Assertions.assertEquals(new ArrayList<>(), this.boardGtr.getTableUtil().getMainRandomUtil().getRandomNumberUsedList());
    }
}