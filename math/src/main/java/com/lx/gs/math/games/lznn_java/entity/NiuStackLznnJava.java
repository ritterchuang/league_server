package com.lx.gs.math.games.lznn_java.entity;

import com.lx.gs.math.core.card.cardWallCtr.ICard;
import com.lx.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import com.lx.gs.math.core.card.stackCtr.niuStackCtr.lzNiuStackCtr.LzNiuStackCommon;

import java.util.List;

// 賴子牛牛的牛型
public class NiuStackLznnJava extends LzNiuStackCommon {
    private ConstLznnJava.NiuTypeEnumLznnJava niuTypeEnumCustom; // 賴子牛牛的牛型

    public NiuStackLznnJava(List<ICard> handCardList, List<ICard> liftCardList, ConstNiu.NiuTypeEnumCommon niuTypeEnumCommon, boolean isNiu, ConstLznnJava.NiuTypeEnumLznnJava niuTypeEnumCustom) {
        super(handCardList, liftCardList, niuTypeEnumCommon, isNiu);
        this.niuTypeEnumCustom = niuTypeEnumCustom;
    }

    public ConstLznnJava.NiuTypeEnumLznnJava getNiuTypeEnumCustom() {
        return niuTypeEnumCustom;
    }
}
