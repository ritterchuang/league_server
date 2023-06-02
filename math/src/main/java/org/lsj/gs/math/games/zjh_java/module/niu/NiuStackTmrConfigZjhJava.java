package org.lsj.gs.math.games.zjh_java.module.niu;

import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import org.lsj.gs.math.games.zjh_java.entity.ConstZjhJava;

import java.util.Map;

// 炸金花的牛型轉換器設定
public class NiuStackTmrConfigZjhJava {
    private final Map<ConstNiu.NiuTypeEnumCommon, ConstZjhJava.NiuTypeEnumZjhJava> niuTypeTransformMap; // 牛型轉換表

    public NiuStackTmrConfigZjhJava(Map<ConstNiu.NiuTypeEnumCommon, ConstZjhJava.NiuTypeEnumZjhJava> niuTypeTransformMap) {
        this.niuTypeTransformMap = niuTypeTransformMap;
    }

    public Map<ConstNiu.NiuTypeEnumCommon, ConstZjhJava.NiuTypeEnumZjhJava> getNiuTypeTransformMap() {
        return niuTypeTransformMap;
    }
}
