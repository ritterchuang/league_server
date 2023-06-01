package org.lsj.gs.math.core.card.stackCtr.pjStackCtr;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.AbstractStack;

import java.util.List;

// 牌九牌型
public class PjStack extends AbstractStack {
    private final ConstPjStack.PjTypeEnumCommon pjTypeEnumCommon; // 牌九牌型列舉

    public PjStack(List<ICard> handCardList, ConstPjStack.PjTypeEnumCommon pjTypeEnumCommon) {
        super(handCardList);
        this.pjTypeEnumCommon = pjTypeEnumCommon;
    }

    @Override
    public int compareTo(AbstractStack abstractStack) {
        if (this.pjTypeEnumCommon.getType() != ((PjStack)abstractStack).getPjTypeEnumCommon().getType()) {
            return  this.compareToDiffType((PjStack)abstractStack);
        }

        return this.compareToSameType((PjStack)abstractStack);
    }

    // 牌序手牌
    public List<ICard> sortCardList() {
        // 1. 取得手牌
        ICard card1 = super.handCardList.get(0);
        ICard card2 = super.handCardList.get(1);

        // 2. 取得單牌定義
        ConstPjStack.PjCardTypeEnumCommon card1Type = ConstPjStack.PjCardTypeEnumCommon.fromCode(card1.getValue());
        ConstPjStack.PjCardTypeEnumCommon card2Type = ConstPjStack.PjCardTypeEnumCommon.fromCode(card2.getValue());

        // 3. 回傳大至小牌序
        if (card1Type.getWeight() > card2Type.getWeight()) {
            return List.of(card1, card2);
        }

        return List.of(card2, card1);
    }

    // 比牌
    public int compareCard(ICard card1, ICard card2) {
        // 1. 取得單牌定義
        int card1TypeWeight = ConstPjStack.PjCardTypeEnumCommon.fromCode(card1.getValue()).getWeight();
        int card2TypeWeight = ConstPjStack.PjCardTypeEnumCommon.fromCode(card2.getValue()).getWeight();

        // 2. 比牌
        if (card1TypeWeight == card2TypeWeight) {
            return 0;
        }

        return (card1TypeWeight > card2TypeWeight) ? 1 : -1;
    }

    // 牌型不同的比較
    private int compareToDiffType(PjStack stack) {
        return this.comparePjType(stack);
    }

    // 牌型相同的比較
    private int compareToSameType(PjStack pjStack){
        // 1. 取得排序後結果
        List<ICard> sortCardList = this.sortCardList();
        List<ICard> pjStackSortCardList = pjStack.sortCardList();

        // 2. 比較最大牌
        int maxResult = this.compareCard(sortCardList.get(0), pjStackSortCardList.get(0));
        if (maxResult != 0) {
            return maxResult;
        }

        // 3. 比較次大牌
        return this.compareCard(sortCardList.get(1), pjStackSortCardList.get(1));
    }

    // 比較牌九牌型大小
    private int comparePjType(PjStack pjStack) {
        return Integer.compare(this.pjTypeEnumCommon.getType(), pjStack.pjTypeEnumCommon.getType());
    }

    public ConstPjStack.PjTypeEnumCommon getPjTypeEnumCommon() {
        return pjTypeEnumCommon;
    }

}
