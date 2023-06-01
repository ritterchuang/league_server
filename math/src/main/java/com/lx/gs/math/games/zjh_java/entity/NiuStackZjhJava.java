package com.lx.gs.math.games.zjh_java.entity;

import com.lx.gs.math.core.card.cardWallCtr.ICard;
import com.lx.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import com.lx.gs.math.core.card.stackCtr.niuStackCtr.NiuStackCommon;

import java.util.List;

// 炸金花的牛型
public class NiuStackZjhJava extends NiuStackCommon {
    private ConstZjhJava.NiuTypeEnumZjhJava typeEnumCustom; // 炸金花的牛型

    public NiuStackZjhJava(List<ICard> handCardList, List<ICard> liftCardList, ConstNiu.NiuTypeEnumCommon niuTypeEnumCommon, ConstZjhJava.NiuTypeEnumZjhJava typeEnumCustom, boolean isNiu) {
        super(handCardList, liftCardList, niuTypeEnumCommon, isNiu);
        this.typeEnumCustom = typeEnumCustom;
    }

    public ConstZjhJava.NiuTypeEnumZjhJava getTypeEnumCustom() {
        return typeEnumCustom;
    }
}
