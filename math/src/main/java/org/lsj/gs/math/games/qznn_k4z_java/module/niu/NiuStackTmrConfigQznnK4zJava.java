package org.lsj.gs.math.games.qznn_k4z_java.module.niu;

import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import org.lsj.gs.math.games.qznn_k4z_java.entity.ConstQznnK4zJava;

import java.util.Map;

// 新看四張搶莊牛牛的牛型轉換器
public class NiuStackTmrConfigQznnK4zJava {
    private final Map<ConstNiu.NiuTypeEnumCommon, ConstQznnK4zJava.NiuTypeEnumQznnK4zJava> niuTypeTransformMap; // 牛型轉換表

    public NiuStackTmrConfigQznnK4zJava(Map<ConstNiu.NiuTypeEnumCommon, ConstQznnK4zJava.NiuTypeEnumQznnK4zJava> niuTypeTransformMap) {
        this.niuTypeTransformMap = niuTypeTransformMap;
    }

    public Map<ConstNiu.NiuTypeEnumCommon, ConstQznnK4zJava.NiuTypeEnumQznnK4zJava> getNiuTypeTransformMap() {
        return niuTypeTransformMap;
    }
}
