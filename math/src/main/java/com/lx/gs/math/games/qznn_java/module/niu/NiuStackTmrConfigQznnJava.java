package com.lx.gs.math.games.qznn_java.module.niu;

import com.lx.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import com.lx.gs.math.games.qznn_java.entity.ConstQznnJava;

import java.util.Map;

// 搶莊牛牛的牛型轉換器設定
public class NiuStackTmrConfigQznnJava {
    private final Map<ConstNiu.NiuTypeEnumCommon, ConstQznnJava.NiuTypeEnumQznnJava> niuTypeTransformMap; // 牛型轉換表

    public NiuStackTmrConfigQznnJava(Map<ConstNiu.NiuTypeEnumCommon, ConstQznnJava.NiuTypeEnumQznnJava> niuTypeTransformMap) {
        this.niuTypeTransformMap = niuTypeTransformMap;
    }

    public Map<ConstNiu.NiuTypeEnumCommon, ConstQznnJava.NiuTypeEnumQznnJava> getNiuTypeTransformMap() {
        return niuTypeTransformMap;
    }
}
