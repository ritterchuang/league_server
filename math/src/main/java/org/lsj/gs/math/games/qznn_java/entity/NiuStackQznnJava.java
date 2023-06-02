package org.lsj.gs.math.games.qznn_java.entity;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.NiuStackCommon;

import java.util.List;

// 搶莊牛牛的牛型
public class NiuStackQznnJava extends NiuStackCommon {
    private ConstQznnJava.NiuTypeEnumQznnJava niuTypeEnumCustom; // 搶莊牛牛的牛型

    public NiuStackQznnJava(List<ICard> handCardList, List<ICard> liftCardList, ConstNiu.NiuTypeEnumCommon niuTypeEnumCommon, boolean isNiu, ConstQznnJava.NiuTypeEnumQznnJava niuTypeEnumCustom) {
        super(handCardList, liftCardList, niuTypeEnumCommon, isNiu);
        this.niuTypeEnumCustom = niuTypeEnumCustom;
    }

    public ConstQznnJava.NiuTypeEnumQznnJava getNiuTypeEnumCustom() {
        return niuTypeEnumCustom;
    }
}
