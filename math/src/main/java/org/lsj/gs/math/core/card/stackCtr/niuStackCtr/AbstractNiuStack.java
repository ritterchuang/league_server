package org.lsj.gs.math.core.card.stackCtr.niuStackCtr;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.AbstractStack;

import java.util.List;

// 抽象牛型
public abstract class AbstractNiuStack extends AbstractStack {
    protected List<ICard> liftCardList; // 提牌
    protected ConstNiu.NiuTypeEnumCommon niuTypeEnumCommon; // 牛型列舉
    protected boolean isNiu; // 是否有牛
    protected final CardTypeUtilNiu cardTypeUtil; // 牛牌型工具包

    public AbstractNiuStack(List<ICard> handCardList, List<ICard> liftCardList, ConstNiu.NiuTypeEnumCommon niuTypeEnumCommon, boolean isNiu) {
        super(handCardList);
        this.liftCardList = liftCardList;
        this.niuTypeEnumCommon = niuTypeEnumCommon;
        this.isNiu = isNiu;
        this.cardTypeUtil = new CardTypeUtilNiu();
    }

    public abstract int compareTo(AbstractStack abstractStack);

    public List<ICard> getLiftCardList() {
        return liftCardList;
    }

    public ConstNiu.NiuTypeEnumCommon getNiuTypeEnumCommon() {
        return niuTypeEnumCommon;
    }

    public boolean isNiu() {
        return isNiu;
    }

    //* 牌型不同的比較 *//

    // 牌型不同的比較
    protected int compareToDifferentType(AbstractNiuStack abstractNiuStack){
        return Integer.compare(this.niuTypeEnumCommon.getType(), abstractNiuStack.getNiuTypeEnumCommon().getType());
    }

    //* 牌型相同的比較 *//

    // 比較炸彈大小
    protected int compareBomb(AbstractNiuStack abstractNiuStack) {
        return Integer.compare(
                super.calculateSpecifyCountPointValue(super.getHandCardList(), 4),
                super.calculateSpecifyCountPointValue(abstractNiuStack.getHandCardList(), 4)
        );
    }

    // 比較葫蘆牛大小
    protected int compareHuluNiu(AbstractNiuStack abstractNiuStack) {
        return Integer.compare(
                super.calculateSpecifyCountPointValue(super.getHandCardList(), 3),
                super.calculateSpecifyCountPointValue(abstractNiuStack.getHandCardList(), 3)
        );
    }
}
