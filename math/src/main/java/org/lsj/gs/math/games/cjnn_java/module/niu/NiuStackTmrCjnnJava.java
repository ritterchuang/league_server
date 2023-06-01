package org.lsj.gs.math.games.cjnn_java.module.niu;

import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.AbstractNiuStack;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import org.lsj.gs.math.games.cjnn_java.entity.ConstCjnnJava;
import org.lsj.gs.math.games.cjnn_java.entity.NiuStackCjnnJava;

import java.util.Map;
import java.util.stream.IntStream;

// 新超級牛牛的牛型轉換器
public class NiuStackTmrCjnnJava {
    private final Map<ConstNiu.NiuTypeEnumCommon, ConstCjnnJava.NiuTypeEnumCjnnJava> niuTypeTransformMap; // 牛型轉換表

    public NiuStackTmrCjnnJava(Map<ConstNiu.NiuTypeEnumCommon, ConstCjnnJava.NiuTypeEnumCjnnJava> niuTypeTransformMap) {
        this.niuTypeTransformMap = niuTypeTransformMap;
    }

    // 轉換牛型結構
    public NiuStackCjnnJava transformNiuStack(AbstractNiuStack abstractNiuStack){
        return new NiuStackCjnnJava(
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
