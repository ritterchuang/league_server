package com.lx.gs.math.games.qznn_ksz_java.entity;

import com.lx.gs.math.core.card.cardWallCtr.ICard;
import com.lx.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import com.lx.gs.math.core.card.stackCtr.niuStackCtr.NiuStackCommon;

import java.util.List;

// 看三張搶莊牛牛的牛型
public class NiuStackQznnKszJava extends NiuStackCommon {
    private ConstQznnKszJava.NiuTypeEnumQznnKszJava niuTypeEnumCustom; // 搶莊牛牛的牛型

    public NiuStackQznnKszJava(List<ICard> handCardList, List<ICard> liftCardList, ConstNiu.NiuTypeEnumCommon niuTypeEnumCommon, boolean isNiu, ConstQznnKszJava.NiuTypeEnumQznnKszJava niuTypeEnumCustom) {
        super(handCardList, liftCardList, niuTypeEnumCommon, isNiu);
        this.niuTypeEnumCustom = niuTypeEnumCustom;
    }

    public ConstQznnKszJava.NiuTypeEnumQznnKszJava getNiuTypeEnumCustom() {
        return niuTypeEnumCustom;
    }
}
