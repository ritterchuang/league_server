package org.lsj.gs.math.core.card.stackCtr.bjlStackCtr;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.AbstractStack;

import java.util.ArrayList;
import java.util.List;

// 百家牌型
public class BjlStack extends AbstractStack{
    private final CardTypeUtilBjl cardTypeUtilBjl; // 百家牌型工具包

    public BjlStack(List<ICard> handCardList) {
        super(handCardList);
        this.cardTypeUtilBjl = new CardTypeUtilBjl();
    }

    public BjlStack() {
        super(new ArrayList<>());
        this.cardTypeUtilBjl = new CardTypeUtilBjl();
    }

    @Override
    public int compareTo(AbstractStack abstractStack) {
        return Integer.compare(
                this.cardTypeUtilBjl.calculateCardPointSumModuleBy10(super.handCardList)
                , this.cardTypeUtilBjl.calculateCardPointSumModuleBy10(abstractStack.getHandCardList()));
    }
}
