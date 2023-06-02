package org.lsj.gs.mathStr.core.entity.playStr.playStrResult;

import org.lsj.gs.mathStr.core.entity.ConstStr;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;

import java.util.List;

// 遊戲模擬器結果父類別
public class PlayStrResult {
    protected final ConstStr.GameType gameType; // 遊戲類型
    protected final List<BoardGtrResult> boardGtrResultList; // 局產生器結果陣列

    public PlayStrResult(ConstStr.GameType gameType, List<BoardGtrResult> boardGtrResultList) {
        this.gameType = gameType;
        this.boardGtrResultList = boardGtrResultList;
    }

    public ConstStr.GameType getGameType() {
        return gameType;
    }

    public List<BoardGtrResult> getBoardGtrResultList() {
        return boardGtrResultList;
    }
}
