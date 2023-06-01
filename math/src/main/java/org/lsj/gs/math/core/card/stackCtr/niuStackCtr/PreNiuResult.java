package org.lsj.gs.math.core.card.stackCtr.niuStackCtr;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;

import java.util.List;

// 預計算牛型結果
public class PreNiuResult {
    private List<ICard> niuCardList; // 排序後牌
    private List<ICard> liftCardList; // 提牌
    private boolean isNiu; // 是否有牛

    public List<ICard> getNiuCardList() {
        return niuCardList;
    }

    public void setNiuCardList(List<ICard> niuCardList) {
        this.niuCardList = niuCardList;
    }

    public boolean isNiu() {
        return isNiu;
    }

    public void setNiu(boolean niu) {
        isNiu = niu;
    }

    public List<ICard> getLiftCardList() {
        return liftCardList;
    }

    public void setLiftCardList(List<ICard> liftCardList) {
        this.liftCardList = liftCardList;
    }
}
