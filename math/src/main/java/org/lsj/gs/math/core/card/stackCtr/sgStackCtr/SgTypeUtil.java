package org.lsj.gs.math.core.card.stackCtr.sgStackCtr;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.AbstractCardTypeUtil;

import java.util.List;

// 三公牌型工具包
public class SgTypeUtil extends AbstractCardTypeUtil {

    // 是否至尊
    public boolean isZhiZun(List<ICard> cardList) {
        // 1. 取得參數
        int cardPoint = 3;
        int cardCount = 3;

        // 2. 判斷是否至尊
        return cardList.stream().allMatch(card -> card.getPoint() == cardPoint)
                && cardList.size() == cardCount;
    }

    // 是否大三公
    public boolean isDaSanGong(List<ICard> cardList) {
        // 1. 取得參數
        int cardPoint = cardList.get(0).getPoint();
        int cardCount = 3;

        // 2. 判斷是否大三公
        return cardList.stream().allMatch(card -> card.getPoint() == cardPoint)
                && cardList.size() == cardCount;

    }

    // 是否三公
    public boolean isSanGong(List<ICard> cardList) {
        // 1. 取得參數
        int minCardPoint = 11;
        int cardCount = 3;

        // 2. 判斷是否至尊
        return cardList.stream().allMatch(card -> card.getPoint() >= minCardPoint)
                && cardList.size() == cardCount;
    }
}
