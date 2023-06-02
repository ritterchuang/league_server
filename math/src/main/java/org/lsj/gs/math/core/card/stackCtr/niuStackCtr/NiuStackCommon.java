package org.lsj.gs.math.core.card.stackCtr.niuStackCtr;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.AbstractStack;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu.NiuTypeEnumCommon;

import java.util.List;

// 通用牛型
public class NiuStackCommon extends AbstractNiuStack {

    public NiuStackCommon(List<ICard> handCardList, List<ICard> liftCardList, NiuTypeEnumCommon typeEnumCommon, boolean isNiu) {
        super(handCardList, liftCardList, typeEnumCommon, isNiu);
    }

    @Override
    public int compareTo(AbstractStack abstractStack) {
        // 1. 牌型不同的比較
        if(super.niuTypeEnumCommon.getType() != ((NiuStackCommon) abstractStack).niuTypeEnumCommon.getType()){
            return super.compareToDifferentType((NiuStackCommon) abstractStack);
        }else{
            // 2. 牌型相同的比較
            return this.compareToSameType((NiuStackCommon) abstractStack);
        }
    }

    // 牌型相同的比較
    private int compareToSameType(NiuStackCommon stack){
        switch(super.niuTypeEnumCommon){
            case SHUNJIN_NIU: return super.compareShunZi(stack, super.cardTypeUtil);
            case BOMB: return super.compareBomb(stack);
            case HULU_NIU: return super.compareHuluNiu(stack);
            case SHUNZI_NIU: return super.compareShunZi(stack, super.cardTypeUtil);
            default: return super.compareMaxCard(stack);
        }
    }
}
