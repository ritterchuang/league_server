package org.lsj.gs.math.games.qznn_k4z_java.module.niu;

import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.AbstractNiuStack;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import org.lsj.gs.math.games.qznn_k4z_java.entity.ConstQznnK4zJava;
import org.lsj.gs.math.games.qznn_k4z_java.entity.NiuStackQznnK4zJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

// 看四張搶莊牛牛的牛型轉換器
public class NiuStackTmrQznnK4zJava {
    private final Map<ConstNiu.NiuTypeEnumCommon, ConstQznnK4zJava.NiuTypeEnumQznnK4zJava> niuTypeTransformMap; // 牛型轉換表

    public NiuStackTmrQznnK4zJava(Map<ConstNiu.NiuTypeEnumCommon, ConstQznnK4zJava.NiuTypeEnumQznnK4zJava> niuTypeTransformMap) {
        this.niuTypeTransformMap = niuTypeTransformMap;
    }

    // 轉換
    public NiuStackQznnK4zJava transformNiuStack(AbstractNiuStack abstractNiuStack){
        return new NiuStackQznnK4zJava(
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
