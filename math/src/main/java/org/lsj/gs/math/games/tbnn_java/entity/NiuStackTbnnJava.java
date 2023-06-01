package org.lsj.gs.math.games.tbnn_java.entity;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.NiuStackCommon;

import java.util.List;

// 通比牛牛的牛型
public class NiuStackTbnnJava extends NiuStackCommon {
    private ConstTbnnJava.NiuTypeEnumTbnnJava niuTypeEnumCustom; // 通比牛牛的牛型

    public NiuStackTbnnJava(List<ICard> handCardList, List<ICard> liftCardList, ConstNiu.NiuTypeEnumCommon niuTypeEnumCommon, boolean isNiu, ConstTbnnJava.NiuTypeEnumTbnnJava niuTypeEnumCustom) {
        super(handCardList, liftCardList, niuTypeEnumCommon, isNiu);
        this.niuTypeEnumCustom = niuTypeEnumCustom;
    }

    public ConstTbnnJava.NiuTypeEnumTbnnJava getNiuTypeEnumCustom() {
        return niuTypeEnumCustom;
    }
}
