package org.lsj.gs.math.games.qznn_ksz_java.module.niu;

import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import org.lsj.gs.math.games.qznn_ksz_java.entity.ConstQznnKszJava;

import java.util.Map;

// 看三張搶莊牛牛的牛型轉換器
public class NiuStackTmrConfigQznnKszJava {
    private final Map<ConstNiu.NiuTypeEnumCommon, ConstQznnKszJava.NiuTypeEnumQznnKszJava> niuTypeTransformMap; // 牛型轉換表

    public NiuStackTmrConfigQznnKszJava(Map<ConstNiu.NiuTypeEnumCommon, ConstQznnKszJava.NiuTypeEnumQznnKszJava> niuTypeTransformMap) {
        this.niuTypeTransformMap = niuTypeTransformMap;
    }

    public Map<ConstNiu.NiuTypeEnumCommon, ConstQznnKszJava.NiuTypeEnumQznnKszJava> getNiuTypeTransformMap() {
        return niuTypeTransformMap;
    }
}
