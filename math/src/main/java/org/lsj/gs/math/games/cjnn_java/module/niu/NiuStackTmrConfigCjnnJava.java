package org.lsj.gs.math.games.cjnn_java.module.niu;

import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import org.lsj.gs.math.games.cjnn_java.entity.ConstCjnnJava;

import java.util.Map;

// 新超級牛牛的牛型轉換器設定
public class NiuStackTmrConfigCjnnJava {
    private final Map<ConstNiu.NiuTypeEnumCommon, ConstCjnnJava.NiuTypeEnumCjnnJava> niuTypeTransformMap; // 牛型轉換表

    public NiuStackTmrConfigCjnnJava(Map<ConstNiu.NiuTypeEnumCommon, ConstCjnnJava.NiuTypeEnumCjnnJava> niuTypeTransformMap) {
        this.niuTypeTransformMap = niuTypeTransformMap;
    }

    public Map<ConstNiu.NiuTypeEnumCommon, ConstCjnnJava.NiuTypeEnumCjnnJava> getNiuTypeTransformMap() {
        return niuTypeTransformMap;
    }
}
