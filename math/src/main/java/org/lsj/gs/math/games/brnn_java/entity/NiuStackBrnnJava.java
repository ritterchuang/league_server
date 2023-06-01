package org.lsj.gs.math.games.brnn_java.entity;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.NiuStackCommon;

import java.util.List;

// 新百人牛牛的牛型
public class NiuStackBrnnJava extends NiuStackCommon {
    private ConstBrnnJava.NiuTypeEnumBrnnJava niuTypeEnumCustom; // 新百人牛牛的牛型

    public NiuStackBrnnJava(List<ICard> handCardList, List<ICard> liftCardList, ConstNiu.NiuTypeEnumCommon typeEnumCommon, boolean isNiu, ConstBrnnJava.NiuTypeEnumBrnnJava niuTypeEnumCustom) {
        super(handCardList, liftCardList, typeEnumCommon, isNiu);
        this.niuTypeEnumCustom = niuTypeEnumCustom;
    }

    public ConstBrnnJava.NiuTypeEnumBrnnJava getNiuTypeEnumCustom() {
        return niuTypeEnumCustom;
    }
}
