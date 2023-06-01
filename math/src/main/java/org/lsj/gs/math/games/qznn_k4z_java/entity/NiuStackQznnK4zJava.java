package org.lsj.gs.math.games.qznn_k4z_java.entity;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.NiuStackCommon;

import java.util.List;

// 新看四張搶莊牛牛的牛型
public class NiuStackQznnK4zJava extends NiuStackCommon {
    private ConstQznnK4zJava.NiuTypeEnumQznnK4zJava niuTypeEnumCustom; // 搶莊牛牛的牛型

    public NiuStackQznnK4zJava(List<ICard> handCardList, List<ICard> liftCardList, ConstNiu.NiuTypeEnumCommon niuTypeEnumCommon, boolean isNiu, ConstQznnK4zJava.NiuTypeEnumQznnK4zJava niuTypeEnumCustom) {
        super(handCardList, liftCardList, niuTypeEnumCommon, isNiu);
        this.niuTypeEnumCustom = niuTypeEnumCustom;
    }

    public ConstQznnK4zJava.NiuTypeEnumQznnK4zJava getNiuTypeEnumCustom() {
        return niuTypeEnumCustom;
    }
}
