package org.lsj.gs.math.core.card.stackCtr.ebgStackCtr;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.AbstractStack;

import java.util.List;

// 二八槓牌型
public class EbgStack extends AbstractStack {
    private final ConstEbgStack.EbgTypeEnumCommon ebgTypeEnumCommon; // 二八槓型列舉

    public EbgStack(List<ICard> handCardList, ConstEbgStack.EbgTypeEnumCommon ebgTypeEnumCommon) {
        super(handCardList);
        this.ebgTypeEnumCommon = ebgTypeEnumCommon;
    }

    @Override
    public int compareTo(AbstractStack abstractStack) {
        if (this.ebgTypeEnumCommon.getType() != ((EbgStack)abstractStack).getEbgTypeEnumCommon().getType()) {
            return  this.compareEbgType((EbgStack)abstractStack);
        }

        return 0;
    }

    // 比較二八牌型大小
    private int compareEbgType(EbgStack ebgStack) {
        return Integer.compare(this.ebgTypeEnumCommon.getType(), ebgStack.ebgTypeEnumCommon.getType());
    }

    public ConstEbgStack.EbgTypeEnumCommon getEbgTypeEnumCommon() {
        return ebgTypeEnumCommon;
    }
}
