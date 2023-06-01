package org.lsj.gs.math.core.card.cardWallCtr;

import org.lsj.gs.math.core.card.ConstMathCard;

// 牌 介面
public interface ICard {

    int getNumber();

    int getValue();

    int getType();

    int getPoint();

    int getWeight();

    ConstMathCard.CardState getCardState();

    void setCardState(ConstMathCard.CardState cardState);

    boolean isState(ConstMathCard.CardState cardState);
}

