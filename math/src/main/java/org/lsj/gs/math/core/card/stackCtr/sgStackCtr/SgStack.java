package org.lsj.gs.math.core.card.stackCtr.sgStackCtr;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.AbstractStack;

import java.util.List;

// 三公牌型
public class SgStack extends AbstractStack {
    private final ConstSgStack.SgTypeEnumCommon sgTypeEnumCommon; // 三公牌型列舉

    public SgStack(List<ICard> handCardList, ConstSgStack.SgTypeEnumCommon sgTypeEnumCommon) {
        super(handCardList);
        this.sgTypeEnumCommon = sgTypeEnumCommon;
    }

    @Override
    public int compareTo(AbstractStack abstractStack) {
        // 1. 不同牌型 比較
        if (this.sgTypeEnumCommon.getType() != ((SgStack)abstractStack).getSgTypeEnumCommon().getType()) {
            return  this.compareToDiffType((SgStack)abstractStack);
        }

        // 2. 相同牌型 比較
        return this.compareToSameType((SgStack)abstractStack);
    }

    // 計算公仔數
    public int getGongZaiCount() {
        int gongZaiPoint = 11;

        return (int) this.handCardList.stream().filter(card -> card.getPoint() >= gongZaiPoint).count();
    }

    // 牌型不同的比較
    private int compareToDiffType(SgStack stack) {
        return this.compareSgType(stack);
    }

    // 牌型相同的比較
    private int compareToSameType(SgStack sgStack){
        // 1. 比較公仔數
        if (this.getGongZaiCount() != sgStack.getGongZaiCount()) {
            return this.compareGongZaiCount(sgStack);
        }

        // 2. 取最大牌
        ICard maxCard = super.calculateMaxCard();
        ICard sgStackMaxCard = sgStack.calculateMaxCard();

        // 3. 比較最大牌點數與花色
        return super.compareCard(maxCard, sgStackMaxCard);
    }

    // 比較公仔數多寡
    private int compareGongZaiCount(SgStack sgStack) {
        return Integer.compare(this.getGongZaiCount(), sgStack.getGongZaiCount());
    }

    // 比較三公牌型大小
    private int compareSgType(SgStack sgStack) {
        return Integer.compare(this.sgTypeEnumCommon.getType(), sgStack.sgTypeEnumCommon.getType());
    }

    public ConstSgStack.SgTypeEnumCommon getSgTypeEnumCommon() {
        return sgTypeEnumCommon;
    }
}
