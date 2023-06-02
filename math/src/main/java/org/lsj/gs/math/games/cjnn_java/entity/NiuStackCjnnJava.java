package org.lsj.gs.math.games.cjnn_java.entity;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.NiuStackCommon;

import java.util.List;

// 新超級牛牛的牛型
public class NiuStackCjnnJava extends NiuStackCommon {
    private ConstCjnnJava.NiuTypeEnumCjnnJava niuTypeEnumCustom; // 新超級牛牛的牛型

    public NiuStackCjnnJava(List<ICard> handCardList, List<ICard> liftCardList, ConstNiu.NiuTypeEnumCommon typeEnumCommon, boolean isNiu, ConstCjnnJava.NiuTypeEnumCjnnJava niuTypeEnumCustom) {
        super(handCardList, liftCardList, typeEnumCommon, isNiu);
        this.niuTypeEnumCustom = niuTypeEnumCustom;
    }

    public ConstCjnnJava.NiuTypeEnumCjnnJava getNiuTypeEnumCustom() {
        return niuTypeEnumCustom;
    }
}
