package org.lsj.gs.math.core.card.stackCtr.pjStackCtr;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.AbstractCardTypeUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 牌九牌型工具包
public class PjTypeUtil extends AbstractCardTypeUtil {
    private final Map<List<ConstPjStack.PjCardTypeEnumCommon>, ConstPjStack.PjTypeEnumCommon> pjComboTypeMap;

    public PjTypeUtil() {
        this.pjComboTypeMap = this.createPjComboType();
    }

    //* 初始化 *//

    // 創建 牌九對牌牌型
    private Map<List<ConstPjStack.PjCardTypeEnumCommon>, ConstPjStack.PjTypeEnumCommon> createPjComboType() {
        Map<List<ConstPjStack.PjCardTypeEnumCommon>, ConstPjStack.PjTypeEnumCommon> result = new HashMap<>();

        result.put(List.of(ConstPjStack.PjCardTypeEnumCommon.GJQ, ConstPjStack.PjCardTypeEnumCommon.DP ), ConstPjStack.PjTypeEnumCommon.DGJ);
        result.put(List.of(ConstPjStack.PjCardTypeEnumCommon.Z72, ConstPjStack.PjCardTypeEnumCommon.TP ), ConstPjStack.PjTypeEnumCommon.TGJ);
        result.put(List.of(ConstPjStack.PjCardTypeEnumCommon.Z81, ConstPjStack.PjCardTypeEnumCommon.DP ), ConstPjStack.PjTypeEnumCommon.DG );
        result.put(List.of(ConstPjStack.PjCardTypeEnumCommon.Z82, ConstPjStack.PjCardTypeEnumCommon.DP ), ConstPjStack.PjTypeEnumCommon.DG );
        result.put(List.of(ConstPjStack.PjCardTypeEnumCommon.RP,  ConstPjStack.PjCardTypeEnumCommon.DP ), ConstPjStack.PjTypeEnumCommon.DG );
        result.put(List.of(ConstPjStack.PjCardTypeEnumCommon.Z81, ConstPjStack.PjCardTypeEnumCommon.TP ), ConstPjStack.PjTypeEnumCommon.TG );
        result.put(List.of(ConstPjStack.PjCardTypeEnumCommon.Z82, ConstPjStack.PjCardTypeEnumCommon.TP ), ConstPjStack.PjTypeEnumCommon.TG );
        result.put(List.of(ConstPjStack.PjCardTypeEnumCommon.RP,  ConstPjStack.PjCardTypeEnumCommon.TP ), ConstPjStack.PjTypeEnumCommon.TG );
        result.put(List.of(ConstPjStack.PjCardTypeEnumCommon.Z91, ConstPjStack.PjCardTypeEnumCommon.DP ), ConstPjStack.PjTypeEnumCommon.DW );
        result.put(List.of(ConstPjStack.PjCardTypeEnumCommon.Z92, ConstPjStack.PjCardTypeEnumCommon.DP ), ConstPjStack.PjTypeEnumCommon.DW );
        result.put(List.of(ConstPjStack.PjCardTypeEnumCommon.Z91, ConstPjStack.PjCardTypeEnumCommon.TP ), ConstPjStack.PjTypeEnumCommon.TW );
        result.put(List.of(ConstPjStack.PjCardTypeEnumCommon.Z92, ConstPjStack.PjCardTypeEnumCommon.TP ), ConstPjStack.PjTypeEnumCommon.TW );
        result.put(List.of(ConstPjStack.PjCardTypeEnumCommon.Z51, ConstPjStack.PjCardTypeEnumCommon.Z52), ConstPjStack.PjTypeEnumCommon.ZW );
        result.put(List.of(ConstPjStack.PjCardTypeEnumCommon.Z72, ConstPjStack.PjCardTypeEnumCommon.Z71), ConstPjStack.PjTypeEnumCommon.Z7 );
        result.put(List.of(ConstPjStack.PjCardTypeEnumCommon.Z81, ConstPjStack.PjCardTypeEnumCommon.Z82), ConstPjStack.PjTypeEnumCommon.Z8 );
        result.put(List.of(ConstPjStack.PjCardTypeEnumCommon.Z91, ConstPjStack.PjCardTypeEnumCommon.Z92), ConstPjStack.PjTypeEnumCommon.Z9 );
        result.put(List.of(ConstPjStack.PjCardTypeEnumCommon.LLL, ConstPjStack.PjCardTypeEnumCommon.LLL), ConstPjStack.PjTypeEnumCommon.SLL);
        result.put(List.of(ConstPjStack.PjCardTypeEnumCommon.GJQ, ConstPjStack.PjCardTypeEnumCommon.GJQ), ConstPjStack.PjTypeEnumCommon.SGJ);
        result.put(List.of(ConstPjStack.PjCardTypeEnumCommon.HTS, ConstPjStack.PjCardTypeEnumCommon.HTS), ConstPjStack.PjTypeEnumCommon.SHT);
        result.put(List.of(ConstPjStack.PjCardTypeEnumCommon.FT,  ConstPjStack.PjCardTypeEnumCommon.FT ), ConstPjStack.PjTypeEnumCommon.SFT);
        result.put(List.of(ConstPjStack.PjCardTypeEnumCommon.BD,  ConstPjStack.PjCardTypeEnumCommon.BD ), ConstPjStack.PjTypeEnumCommon.SBD);
        result.put(List.of(ConstPjStack.PjCardTypeEnumCommon.CS,  ConstPjStack.PjCardTypeEnumCommon.CS ), ConstPjStack.PjTypeEnumCommon.SCS);
        result.put(List.of(ConstPjStack.PjCardTypeEnumCommon.MP,  ConstPjStack.PjCardTypeEnumCommon.MP ), ConstPjStack.PjTypeEnumCommon.SM );
        result.put(List.of(ConstPjStack.PjCardTypeEnumCommon.EP,  ConstPjStack.PjCardTypeEnumCommon.EP ), ConstPjStack.PjTypeEnumCommon.SE );
        result.put(List.of(ConstPjStack.PjCardTypeEnumCommon.RP,  ConstPjStack.PjCardTypeEnumCommon.RP ), ConstPjStack.PjTypeEnumCommon.SR );
        result.put(List.of(ConstPjStack.PjCardTypeEnumCommon.DP,  ConstPjStack.PjCardTypeEnumCommon.DP ), ConstPjStack.PjTypeEnumCommon.SD );
        result.put(List.of(ConstPjStack.PjCardTypeEnumCommon.TP,  ConstPjStack.PjCardTypeEnumCommon.TP ), ConstPjStack.PjTypeEnumCommon.ST );
        result.put(List.of(ConstPjStack.PjCardTypeEnumCommon.DS,  ConstPjStack.PjCardTypeEnumCommon.ES ), ConstPjStack.PjTypeEnumCommon.ZZ );

        return result;
    }


    //* 計算牌型 *//

    // 計算對牌類型
    public ConstPjStack.PjTypeEnumCommon calculatePjComboType(List<ICard> cardList) {
        List<ConstPjStack.PjCardTypeEnumCommon> cardTypeList = this.calculatePjCardTypeList(cardList);

        for (Map.Entry<List<ConstPjStack.PjCardTypeEnumCommon>, ConstPjStack.PjTypeEnumCommon> entry: this.pjComboTypeMap.entrySet()) {
            boolean flagOne = cardTypeList.get(0).equals(entry.getKey().get(0)) && cardTypeList.get(1).equals(entry.getKey().get(1));
            boolean flagTwo = cardTypeList.get(1).equals(entry.getKey().get(0)) && cardTypeList.get(0).equals(entry.getKey().get(1));
            if (flagOne || flagTwo) {
                return entry.getValue();
            }
        }

        return ConstPjStack.PjTypeEnumCommon.INVALID;
    }

    // 取得牌型定義
    private List<ConstPjStack.PjCardTypeEnumCommon> calculatePjCardTypeList(List<ICard> cardList) {
        List<ConstPjStack.PjCardTypeEnumCommon> result = new ArrayList<>();

        cardList.forEach(card -> result.add(ConstPjStack.PjCardTypeEnumCommon.fromCode(card.getValue())));

        return result;
    }

    // 計算單牌牌型
    public ConstPjStack.PjTypeEnumCommon calculatePjSingleType(List<ICard> cardList) {
        // 1. 計算牌九點數總和
        int pjPointSum = this.calculatePjPointSumModuleBy10(cardList);

        // 2. 回傳單牌牌型
        return ConstPjStack.PjTypeEnumCommon.fromCode(pjPointSum);
    }

    // 計算牌九點數和
    private int calculatePjPointSumModuleBy10(List<ICard> cardList) {
        // 1. 取得參數
        int maxPoint = 10;

        // 2. 計算點數和
        int pointSum = cardList.stream().mapToInt(card -> Math.min(card.getPoint(), maxPoint)).sum();

        // 3. 計算牌型和
        int typeSum = cardList.stream().mapToInt(card -> Math.min(card.getType(), maxPoint)).sum();

        // 4. 回傳總和餘數
        return (pointSum + typeSum) % maxPoint;
    }
}
