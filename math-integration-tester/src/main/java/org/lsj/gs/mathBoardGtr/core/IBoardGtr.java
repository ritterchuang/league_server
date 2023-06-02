package org.lsj.gs.mathBoardGtr.core;

import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.StnResult;

// 局產生器父介面
public interface IBoardGtr {
    // 產生局結果
    BoardGtrResult calculateOneBoardResult();

    // 產生統計結果
    StnResult calculateStatisticResult();

    // 更新局結果
    void update(BoardGtrResult boardGtrResult);

    // 判斷能否繼續執行遊戲
    boolean canPlay();
}
