package org.lsj.gs.mathStr.core.entity.playStr.playStrResult;

import org.lsj.gs.mathStr.core.entity.ConstStr;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;

import java.util.List;

// 遊戲模擬器結果老虎機
public class PlayStrResultSlot extends PlayStrResult {

    public PlayStrResultSlot(List<BoardGtrResult> boardGtrResultList) {
        super(ConstStr.GameType.SLOT, boardGtrResultList);
    }
}
