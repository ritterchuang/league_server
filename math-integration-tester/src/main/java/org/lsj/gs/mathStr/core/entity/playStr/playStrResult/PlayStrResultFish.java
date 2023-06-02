package org.lsj.gs.mathStr.core.entity.playStr.playStrResult;

import org.lsj.gs.mathStr.core.entity.ConstStr;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrReturnResult;

import java.util.List;

// 遊戲模擬器結果魚機
public class PlayStrResultFish extends PlayStrResult {
    private final BoardGtrReturnResult returnResult; // 遊戲返還結果

    public PlayStrResultFish(List<BoardGtrResult> boardGtrResultList, BoardGtrReturnResult returnResult) {
        super(ConstStr.GameType.FISH, boardGtrResultList);
        this.returnResult = returnResult;
    }

    public BoardGtrReturnResult getReturnResult() {
        return returnResult;
    }
}
