package org.lsj.gs.mathStr.core.module.stn.agencyStn;

import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.playStrResult.PlayStrResult;
import org.lsj.gs.mathStr.core.entity.playStr.playStrResult.PlayStrResultFish;
import org.lsj.gs.mathStr.core.module.stn.IStn;

// 代理統計更新者
public class AgencyStnUtr {

    // 更新統計者
    public void updateStn(IStn stn, PlayStrResult playStrResult) {
        switch (playStrResult.getGameType()) {
            case CARD: this.updateCard(stn, playStrResult); break;
            case FISH: this.updateFish(stn, playStrResult); break;
            case SLOT: this.updateSlot(stn, playStrResult); break;
            default: return;
        }
    }

    // 卡牌統計更新
    private void updateCard(IStn stn, PlayStrResult playStrResult) {
        // 更新局資訊
        for(BoardGtrResult boardGtrResult : playStrResult.getBoardGtrResultList()){
            stn.update(boardGtrResult);
        }
    }

    // 魚機統計更新
    private void updateFish(IStn stn, PlayStrResult playStrResult) {
        // 1. 更新局資訊
        for(BoardGtrResult boardGtrResult : playStrResult.getBoardGtrResultList()){
            stn.update(boardGtrResult);
        }

        // 2. 更新返還資訊
        stn.updateReturnResult(((PlayStrResultFish) playStrResult).getReturnResult());
    }

    // 老虎機機統計更新
    private void updateSlot(IStn stn, PlayStrResult playStrResult) {
        // 更新局資訊
        for(BoardGtrResult boardGtrResult : playStrResult.getBoardGtrResultList()){
            stn.update(boardGtrResult);
        }
    }
}
