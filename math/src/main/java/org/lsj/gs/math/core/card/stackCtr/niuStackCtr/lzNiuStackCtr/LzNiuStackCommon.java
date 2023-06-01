package org.lsj.gs.math.core.card.stackCtr.niuStackCtr.lzNiuStackCtr;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.AbstractStack;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.AbstractNiuStack;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu.NiuTypeEnumCommon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// 賴子通用牛型
public class LzNiuStackCommon extends AbstractNiuStack {

    public LzNiuStackCommon(List<ICard> handCardList, List<ICard> liftCardList, NiuTypeEnumCommon typeEnumCommon, boolean isNiu) {
        super(handCardList, liftCardList, typeEnumCommon, isNiu);
    }

    @Override
    public int compareTo(AbstractStack abstractStack) {
        // 1. 牌型不同的比較
        if(this.niuTypeEnumCommon.getType() != ((LzNiuStackCommon) abstractStack).niuTypeEnumCommon.getType()){
            return super.compareToDifferentType((LzNiuStackCommon) abstractStack);
        }else{
            // 2. 牌型相同的比較
            return this.compareToSameType((LzNiuStackCommon) abstractStack);
        }
    }

    // 牌型相同的比較
    private int compareToSameType(LzNiuStackCommon stack){
        switch(this.niuTypeEnumCommon){
            case SHUNJIN_NIU: return this.compareShunZi(stack);
            case BOMB: return super.compareBomb(stack);
            case HULU_NIU: return super.compareHuluNiu(stack);
            case SHUNZI_NIU: return this.compareShunZi(stack);
            default: return this.compareMaxCard(stack);
        }
    }

    // 賴子 比牌
    private int compareMaxCard(LzNiuStackCommon stack) {
        // 1. 複製
        List<ICard> mineCardList = new ArrayList<>(super.handCardList);
        List<ICard> theirCardList = new ArrayList<>(stack.getHandCardList());

        // 2. 排序
        mineCardList.sort(Comparator.comparing(ICard::getWeight).thenComparing(ICard::getType)
                .reversed());
        theirCardList.sort(Comparator.comparing(ICard::getWeight).thenComparing(ICard::getType)
                .reversed());

        // 3. 比較
        for (int cardIndex = 0; cardIndex < mineCardList.size(); cardIndex++) {
            int compareResult = super.compareCard(mineCardList.get(cardIndex), theirCardList.get(cardIndex));
            if (compareResult != 0) {
                return compareResult;
            }
        }

        // 4. 防呆回傳
        return 0;
    }

    // 賴子順子 比牌
    private int compareShunZi(LzNiuStackCommon stack) {
        // 1. 複製
        List<ICard> mineCardListCopy = new ArrayList<>(super.handCardList);
        List<ICard> theirCardListCopy = new ArrayList<>(stack.getHandCardList());

        // 2. 牌組排序
        mineCardListCopy.sort(Comparator.comparing(ICard::getPoint));
        theirCardListCopy.sort(Comparator.comparing(ICard::getPoint));

        // 3. 判斷AK順
        boolean isShunZiAKMine = this.cardTypeUtil.checkShunZiAK(mineCardListCopy);
        boolean isShunZiAKTheir = this.cardTypeUtil.checkShunZiAK(theirCardListCopy);

        // 4. 比較AK順
        if(isShunZiAKMine && !isShunZiAKTheir){
            return 1;
        }

        if(!isShunZiAKMine && isShunZiAKTheir){
            return -1;
        }

        // 5. 剩餘判斷
        return this.compareMaxCard(stack);
    }
}
