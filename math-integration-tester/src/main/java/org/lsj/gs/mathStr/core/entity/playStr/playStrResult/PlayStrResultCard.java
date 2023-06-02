package org.lsj.gs.mathStr.core.entity.playStr.playStrResult;

import org.lsj.gs.mathStr.core.entity.ConstStr;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;

import java.util.List;

// 遊戲模擬器結果卡牌
public class PlayStrResultCard extends PlayStrResult {

    public PlayStrResultCard(List<BoardGtrResult> boardGtrResultList) {
        super(ConstStr.GameType.CARD, boardGtrResultList);
    }
}
