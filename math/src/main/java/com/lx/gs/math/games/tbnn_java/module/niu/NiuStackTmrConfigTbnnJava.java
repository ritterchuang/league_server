package com.lx.gs.math.games.tbnn_java.module.niu;

import com.lx.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import com.lx.gs.math.games.tbnn_java.entity.ConstTbnnJava;

import java.util.Map;

// 搶莊牛牛的牛型轉換器設定
public class NiuStackTmrConfigTbnnJava {
    private final Map<ConstNiu.NiuTypeEnumCommon, ConstTbnnJava.NiuTypeEnumTbnnJava> niuTypeTransformMap; // 牛型轉換表

    public NiuStackTmrConfigTbnnJava(Map<ConstNiu.NiuTypeEnumCommon, ConstTbnnJava.NiuTypeEnumTbnnJava> niuTypeTransformMap) {
        this.niuTypeTransformMap = niuTypeTransformMap;
    }

    public Map<ConstNiu.NiuTypeEnumCommon, ConstTbnnJava.NiuTypeEnumTbnnJava> getNiuTypeTransformMap() {
        return niuTypeTransformMap;
    }
}
