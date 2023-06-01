package org.lsj.gs.math.games.brnn_java.module.niu;

import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import org.lsj.gs.math.games.brnn_java.entity.ConstBrnnJava;

import java.util.Map;

// 新百人牛牛的牛型轉換器設定
public class NiuStackTmrConfigBrnnJava {
    private final Map<ConstNiu.NiuTypeEnumCommon, ConstBrnnJava.NiuTypeEnumBrnnJava> niuTypeTransformMap; // 牛型轉換表

    public NiuStackTmrConfigBrnnJava(Map<ConstNiu.NiuTypeEnumCommon, ConstBrnnJava.NiuTypeEnumBrnnJava> niuTypeTransformMap) {
        this.niuTypeTransformMap = niuTypeTransformMap;
    }

    public Map<ConstNiu.NiuTypeEnumCommon, ConstBrnnJava.NiuTypeEnumBrnnJava> getNiuTypeTransformMap() {
        return niuTypeTransformMap;
    }
}
