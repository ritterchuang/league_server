package org.lsj.gs.math.games.zjh_java.module.niu;

import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.AbstractNiuStack;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import org.lsj.gs.math.games.zjh_java.entity.ConstZjhJava;
import org.lsj.gs.math.games.zjh_java.entity.NiuStackZjhJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

// 炸金花的牛型轉換器
public class NiuStackTmrZjhJava {
    private final Map<ConstNiu.NiuTypeEnumCommon, ConstZjhJava.NiuTypeEnumZjhJava> niuTypeTransformMap; // 牛型轉換表

    public NiuStackTmrZjhJava(Map<ConstNiu.NiuTypeEnumCommon, ConstZjhJava.NiuTypeEnumZjhJava> niuTypeTransformMap) {
        this.niuTypeTransformMap = niuTypeTransformMap;
    }

    // 轉換牛型結構
    public NiuStackZjhJava transformNiuStack(AbstractNiuStack abstractNiuStack){
        return new NiuStackZjhJava(
                abstractNiuStack.getHandCardList(),
                abstractNiuStack.getLiftCardList(),
                abstractNiuStack.getNiuTypeEnumCommon(),
                this.niuTypeTransformMap.get(abstractNiuStack.getNiuTypeEnumCommon()),
                abstractNiuStack.isNiu());
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
