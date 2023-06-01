package com.lx.gs.math.games.qznn_ksz_java.module.niu;

import com.lx.gs.math.core.card.stackCtr.niuStackCtr.AbstractNiuStack;
import com.lx.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import com.lx.gs.math.games.qznn_ksz_java.entity.ConstQznnKszJava;
import com.lx.gs.math.games.qznn_ksz_java.entity.NiuStackQznnKszJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

// 看三張搶莊牛牛的牛型轉換器
public class NiuStackTmrQznnKszJava {
    private final Map<ConstNiu.NiuTypeEnumCommon, ConstQznnKszJava.NiuTypeEnumQznnKszJava> niuTypeTransformMap; // 牛型轉換表

    public NiuStackTmrQznnKszJava(Map<ConstNiu.NiuTypeEnumCommon, ConstQznnKszJava.NiuTypeEnumQznnKszJava> niuTypeTransformMap) {
        this.niuTypeTransformMap = niuTypeTransformMap;
    }

    // 轉換
    public NiuStackQznnKszJava transformNiuStack(AbstractNiuStack abstractNiuStack){
        return new NiuStackQznnKszJava(
                abstractNiuStack.getHandCardList(),
                abstractNiuStack.getLiftCardList(),
                abstractNiuStack.getNiuTypeEnumCommon(),
                abstractNiuStack.isNiu(), this.niuTypeTransformMap.get(abstractNiuStack.getNiuTypeEnumCommon())
        );
    }

    // 轉換牛型編碼陣列
    public int[] transformNiuTypeArray(int[] niuTypeCommonArray){
        // 1. 建立空間
        List<Integer> niuTypeList = new ArrayList<>();

        // 2. 轉型
        Arrays.stream(niuTypeCommonArray).forEach(niuTypeCommon -> niuTypeList.add(this.transformNiuType(niuTypeCommon)));

        // 3. 回傳
        return niuTypeList.stream().mapToInt(i->i).toArray();
    }

    // 轉換牛型編碼
    public int transformNiuType(int niuTypeCommon){
        return this.niuTypeTransformMap.get(ConstNiu.NiuTypeEnumCommon.fromType(niuTypeCommon)).getType();
    }
}
