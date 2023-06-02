package org.lsj.gs.math.core.card.stackCtr.ebgStackCtr;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.AbstractCardTypeUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 二八槓牌型工具包
public class EbgTypeUtil extends AbstractCardTypeUtil {
    private final int baiBanValue = 47; // 白板牌值
    private final Map<Integer, Map<Integer, ConstEbgStack.EbgTypeEnumCommon>> pointToEbgTypeMap; // [點數和][最大點數] = 二八槓牌型

    public EbgTypeUtil() {
        this.pointToEbgTypeMap = this.createEbgType();
    }


    //* 牌型條件判斷 *//

    // 是否寶牌
    public boolean checkBaoType(List<ICard> cardList) {
        return cardList.get(0).getValue() == cardList.get(1).getValue();
    }

    // 是否有白板
    public boolean checkBaiBan(List<ICard> cardList) {
        return cardList.stream().anyMatch(iCard -> iCard.getValue() == this.baiBanValue);
    }


    //* 計算牌型 *//

    // 計算寶牌
    public ConstEbgStack.EbgTypeEnumCommon calculateBaoType(List<ICard> cardList) {
        if (cardList.stream().allMatch(iCard -> iCard.getValue() == this.baiBanValue)) {
            return ConstEbgStack.EbgTypeEnumCommon.KING;
        }

        switch (cardList.get(0).getPoint()) {
            case 1: return ConstEbgStack.EbgTypeEnumCommon.BAO_1;
            case 2: return ConstEbgStack.EbgTypeEnumCommon.BAO_2;
            case 3: return ConstEbgStack.EbgTypeEnumCommon.BAO_3;
            case 4: return ConstEbgStack.EbgTypeEnumCommon.BAO_4;
            case 5: return ConstEbgStack.EbgTypeEnumCommon.BAO_5;
            case 6: return ConstEbgStack.EbgTypeEnumCommon.BAO_6;
            case 7: return ConstEbgStack.EbgTypeEnumCommon.BAO_7;
            case 8: return ConstEbgStack.EbgTypeEnumCommon.BAO_8;
            case 9: return ConstEbgStack.EbgTypeEnumCommon.BAO_9;
            default: return ConstEbgStack.EbgTypeEnumCommon.INVALID;
        }
    }

    // 計算 半點牌型
    public ConstEbgStack.EbgTypeEnumCommon calculateHalfType(List<ICard> cardList) {

        int point = cardList.stream().filter(iCard -> iCard.getValue() != this.baiBanValue).findFirst().get().getPoint();

        switch (point) {
            case 1: return ConstEbgStack.EbgTypeEnumCommon.ONE_AND_HALF;
            case 2: return ConstEbgStack.EbgTypeEnumCommon.TWO_AND_HALF;
            case 3: return ConstEbgStack.EbgTypeEnumCommon.THREE_AND_HALF;
            case 4: return ConstEbgStack.EbgTypeEnumCommon.FOUR_AND_HALF;
            case 5: return ConstEbgStack.EbgTypeEnumCommon.FIVE_AND_HALF;
            case 6: return ConstEbgStack.EbgTypeEnumCommon.SIX_AND_HALF;
            case 7: return ConstEbgStack.EbgTypeEnumCommon.SEVEN_AND_HALF;
            case 8: return ConstEbgStack.EbgTypeEnumCommon.EIGHT_AND_HALF;
            case 9: return ConstEbgStack.EbgTypeEnumCommon.NINE_AND_HALF;
            default: return ConstEbgStack.EbgTypeEnumCommon.INVALID;
        }
    }

    // 計算一般牌型
    public ConstEbgStack.EbgTypeEnumCommon calculateCommonType(List<ICard> cardList) {
        int pointSum = super.calculateCardPointSumModuleBy10(cardList);

        int cardPoint = cardList.get(0).getPoint();

        return this.pointToEbgTypeMap.get(pointSum).get(cardPoint);
    }


    // 產出牌型
    private Map<Integer, Map<Integer, ConstEbgStack.EbgTypeEnumCommon>> createEbgType() {
        return new HashMap<>(){
            {
                put(0, new HashMap<>(){
                    {
                        put(1, ConstEbgStack.EbgTypeEnumCommon.BS_91);
                        put(9, ConstEbgStack.EbgTypeEnumCommon.BS_91);
                        put(3, ConstEbgStack.EbgTypeEnumCommon.BS_73);
                        put(7, ConstEbgStack.EbgTypeEnumCommon.BS_73);
                        put(4, ConstEbgStack.EbgTypeEnumCommon.BS_64);
                        put(6, ConstEbgStack.EbgTypeEnumCommon.BS_64);

                        put(2, ConstEbgStack.EbgTypeEnumCommon.EBG);
                        put(8, ConstEbgStack.EbgTypeEnumCommon.EBG);
                    }
                });
                put(1, new HashMap<>(){
                    {
                        put(2, ConstEbgStack.EbgTypeEnumCommon.ONE_92);
                        put(9, ConstEbgStack.EbgTypeEnumCommon.ONE_92);
                        put(3, ConstEbgStack.EbgTypeEnumCommon.ONE_83);
                        put(8, ConstEbgStack.EbgTypeEnumCommon.ONE_83);
                        put(4, ConstEbgStack.EbgTypeEnumCommon.ONE_74);
                        put(7, ConstEbgStack.EbgTypeEnumCommon.ONE_74);
                        put(5, ConstEbgStack.EbgTypeEnumCommon.ONE_65);
                        put(6, ConstEbgStack.EbgTypeEnumCommon.ONE_65);
                    }
                });
                put(2, new HashMap<>(){
                    {
                        put(3, ConstEbgStack.EbgTypeEnumCommon.TWO_93);
                        put(9, ConstEbgStack.EbgTypeEnumCommon.TWO_93);
                        put(4, ConstEbgStack.EbgTypeEnumCommon.TWO_84);
                        put(8, ConstEbgStack.EbgTypeEnumCommon.TWO_84);
                        put(5, ConstEbgStack.EbgTypeEnumCommon.TWO_75);
                        put(7, ConstEbgStack.EbgTypeEnumCommon.TWO_75);
                    }
                });
                put(3, new HashMap<>(){
                    {
                        put(4, ConstEbgStack.EbgTypeEnumCommon.THREE_94);
                        put(9, ConstEbgStack.EbgTypeEnumCommon.THREE_94);
                        put(5, ConstEbgStack.EbgTypeEnumCommon.THREE_85);
                        put(8, ConstEbgStack.EbgTypeEnumCommon.THREE_85);
                        put(6, ConstEbgStack.EbgTypeEnumCommon.THREE_76);
                        put(7, ConstEbgStack.EbgTypeEnumCommon.THREE_76);
                        put(1, ConstEbgStack.EbgTypeEnumCommon.THREE_21);
                        put(2, ConstEbgStack.EbgTypeEnumCommon.THREE_21);
                    }
                });
                put(4, new HashMap<>(){
                    {
                        put(5, ConstEbgStack.EbgTypeEnumCommon.FOUR_95);
                        put(9, ConstEbgStack.EbgTypeEnumCommon.FOUR_95);
                        put(6, ConstEbgStack.EbgTypeEnumCommon.FOUR_86);
                        put(8, ConstEbgStack.EbgTypeEnumCommon.FOUR_86);
                        put(1, ConstEbgStack.EbgTypeEnumCommon.FOUR_31);
                        put(3, ConstEbgStack.EbgTypeEnumCommon.FOUR_31);
                    }
                });
                put(5, new HashMap<>(){
                    {
                        put(6, ConstEbgStack.EbgTypeEnumCommon.FIVE_96);
                        put(9, ConstEbgStack.EbgTypeEnumCommon.FIVE_96);
                        put(7, ConstEbgStack.EbgTypeEnumCommon.FIVE_87);
                        put(8, ConstEbgStack.EbgTypeEnumCommon.FIVE_87);
                        put(1, ConstEbgStack.EbgTypeEnumCommon.FIVE_41);
                        put(4, ConstEbgStack.EbgTypeEnumCommon.FIVE_41);
                        put(2, ConstEbgStack.EbgTypeEnumCommon.FIVE_32);
                        put(3, ConstEbgStack.EbgTypeEnumCommon.FIVE_32);
                    }
                });
                put(6, new HashMap<>(){
                    {
                        put(7, ConstEbgStack.EbgTypeEnumCommon.SIX_97);
                        put(9, ConstEbgStack.EbgTypeEnumCommon.SIX_97);
                        put(1, ConstEbgStack.EbgTypeEnumCommon.SIX_51);
                        put(5, ConstEbgStack.EbgTypeEnumCommon.SIX_51);
                        put(2, ConstEbgStack.EbgTypeEnumCommon.SIX_42);
                        put(4, ConstEbgStack.EbgTypeEnumCommon.SIX_42);
                    }
                });
                put(7, new HashMap<>(){
                    {
                        put(8, ConstEbgStack.EbgTypeEnumCommon.SEVEN_98);
                        put(9, ConstEbgStack.EbgTypeEnumCommon.SEVEN_98);
                        put(1, ConstEbgStack.EbgTypeEnumCommon.SEVEN_61);
                        put(6, ConstEbgStack.EbgTypeEnumCommon.SEVEN_61);
                        put(2, ConstEbgStack.EbgTypeEnumCommon.SEVEN_52);
                        put(5, ConstEbgStack.EbgTypeEnumCommon.SEVEN_52);
                        put(3, ConstEbgStack.EbgTypeEnumCommon.SEVEN_43);
                        put(4, ConstEbgStack.EbgTypeEnumCommon.SEVEN_43);
                    }
                });
                put(8, new HashMap<>(){
                    {
                        put(1, ConstEbgStack.EbgTypeEnumCommon.EIGHT_71);
                        put(7, ConstEbgStack.EbgTypeEnumCommon.EIGHT_71);
                        put(2, ConstEbgStack.EbgTypeEnumCommon.EIGHT_62);
                        put(6, ConstEbgStack.EbgTypeEnumCommon.EIGHT_62);
                        put(3, ConstEbgStack.EbgTypeEnumCommon.EIGHT_53);
                        put(5, ConstEbgStack.EbgTypeEnumCommon.EIGHT_53);
                    }
                });
                put(9, new HashMap<>(){
                    {
                        put(1, ConstEbgStack.EbgTypeEnumCommon.NINE_81);
                        put(8, ConstEbgStack.EbgTypeEnumCommon.NINE_81);
                        put(2, ConstEbgStack.EbgTypeEnumCommon.NINE_72);
                        put(7, ConstEbgStack.EbgTypeEnumCommon.NINE_72);
                        put(3, ConstEbgStack.EbgTypeEnumCommon.NINE_63);
                        put(6, ConstEbgStack.EbgTypeEnumCommon.NINE_63);
                        put(4, ConstEbgStack.EbgTypeEnumCommon.NINE_54);
                        put(5, ConstEbgStack.EbgTypeEnumCommon.NINE_54);
                    }
                });
            }
        };
    }

}
