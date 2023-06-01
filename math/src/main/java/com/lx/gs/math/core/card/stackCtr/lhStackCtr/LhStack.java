package com.lx.gs.math.core.card.stackCtr.lhStackCtr;

import com.lx.gs.math.core.card.cardWallCtr.ICard;
import com.lx.gs.math.core.card.stackCtr.AbstractStack;

import java.util.List;

// 龍虎牌型
public class LhStack extends AbstractStack {

    public LhStack(List<ICard> handCardList) {
        super(handCardList);
    }

    @Override
    public int compareTo(AbstractStack abstractStack) {
        return super.compareCardPoint(this.handCardList.get(0), abstractStack.getHandCardList().get(0));
    }
}
