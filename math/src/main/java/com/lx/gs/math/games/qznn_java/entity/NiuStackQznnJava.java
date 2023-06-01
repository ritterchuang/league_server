package com.lx.gs.math.games.qznn_java.entity;

import com.lx.gs.math.core.card.cardWallCtr.ICard;
import com.lx.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import com.lx.gs.math.core.card.stackCtr.niuStackCtr.NiuStackCommon;
import com.lx.gs.math.games.qznn_java.entity.ConstQznnJava.NiuTypeEnumQznnJava;

import java.util.List;

// 搶莊牛牛的牛型
public class NiuStackQznnJava extends NiuStackCommon {
    private NiuTypeEnumQznnJava niuTypeEnumCustom; // 搶莊牛牛的牛型

    public NiuStackQznnJava(List<ICard> handCardList, List<ICard> liftCardList, ConstNiu.NiuTypeEnumCommon niuTypeEnumCommon, boolean isNiu, NiuTypeEnumQznnJava niuTypeEnumCustom) {
        super(handCardList, liftCardList, niuTypeEnumCommon, isNiu);
        this.niuTypeEnumCustom = niuTypeEnumCustom;
    }

    public NiuTypeEnumQznnJava getNiuTypeEnumCustom() {
        return niuTypeEnumCustom;
    }
}
