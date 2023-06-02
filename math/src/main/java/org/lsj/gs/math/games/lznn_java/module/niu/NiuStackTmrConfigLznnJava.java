package org.lsj.gs.math.games.lznn_java.module.niu;

import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import org.lsj.gs.math.games.lznn_java.entity.ConstLznnJava;

import java.util.Map;

// 新賴子牛牛的牛型轉換器設定
public class NiuStackTmrConfigLznnJava {
    private final Map<ConstNiu.NiuTypeEnumCommon, ConstLznnJava.NiuTypeEnumLznnJava> niuTypeTransformMap; // 牛型轉換表

    public NiuStackTmrConfigLznnJava(Map<ConstNiu.NiuTypeEnumCommon, ConstLznnJava.NiuTypeEnumLznnJava> niuTypeTransformMap) {
        this.niuTypeTransformMap = niuTypeTransformMap;
    }

    public Map<ConstNiu.NiuTypeEnumCommon, ConstLznnJava.NiuTypeEnumLznnJava> getNiuTypeTransformMap() {
        return niuTypeTransformMap;
    }
}
