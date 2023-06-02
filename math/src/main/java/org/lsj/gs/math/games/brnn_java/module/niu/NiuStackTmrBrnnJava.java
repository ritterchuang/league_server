package org.lsj.gs.math.games.brnn_java.module.niu;

import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.AbstractNiuStack;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import org.lsj.gs.math.games.brnn_java.entity.ConstBrnnJava;
import org.lsj.gs.math.games.brnn_java.entity.NiuStackBrnnJava;

import java.util.Map;
import java.util.stream.IntStream;

// 新百人牛牛的牛型轉換器
public class NiuStackTmrBrnnJava {
    private final Map<ConstNiu.NiuTypeEnumCommon, ConstBrnnJava.NiuTypeEnumBrnnJava> niuTypeTransformMap; // 牛型轉換表

    public NiuStackTmrBrnnJava(Map<ConstNiu.NiuTypeEnumCommon, ConstBrnnJava.NiuTypeEnumBrnnJava> niuTypeTransformMap) {
        this.niuTypeTransformMap = niuTypeTransformMap;
    }

    // 轉換牛型結構
    public NiuStackBrnnJava transformNiuStack(AbstractNiuStack abstractNiuStack){
        return new NiuStackBrnnJava(
                abstractNiuStack.getHandCardList(),
                abstractNiuStack.getLiftCardList(),
                abstractNiuStack.getNiuTypeEnumCommon(),
                abstractNiuStack.isNiu(), this.niuTypeTransformMap.get(abstractNiuStack.getNiuTypeEnumCommon())
        );
    }

    // 轉換牛型編碼陣列
    public int[] transformNiuTypeArray(int[] niuTypeCommonArray){
        return IntStream.of(niuTypeCommonArray)
                .map(this::transformNiuType)
                .toArray();
    }

    // 轉換牛型編碼
    public int transformNiuType(int niuTypeCommon){
        return this.niuTypeTransformMap.get(ConstNiu.NiuTypeEnumCommon.fromType(niuTypeCommon)).getType();
    }
}
