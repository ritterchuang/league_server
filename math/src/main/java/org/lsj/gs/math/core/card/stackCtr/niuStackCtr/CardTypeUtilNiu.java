package org.lsj.gs.math.core.card.stackCtr.niuStackCtr;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.AbstractCardTypeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

// 牛型工具包
public class CardTypeUtilNiu extends AbstractCardTypeUtil {

    // 計算提牌結果
    public PreNiuResult calculatePreNiuResult(List<ICard> cardList) {
        // 1. 判斷是否有牛
        boolean isNiu = false;
        List<ICard> niuCardList = new ArrayList<>();
        List<ICard> liftCardList = new ArrayList<>();
        for (int firstCardIndex = 0; firstCardIndex < 3; firstCardIndex++) {
            for (int secondCardIndex = firstCardIndex + 1; secondCardIndex < cardList.size(); secondCardIndex++) {
                for (int thirdCardIndex = secondCardIndex + 1; thirdCardIndex < cardList.size(); thirdCardIndex++) {
                    if (super.calculateCardPointSumModuleBy10(Arrays.asList(cardList.get(firstCardIndex), cardList.get(secondCardIndex), cardList.get(thirdCardIndex))) == 0) {
                        isNiu = true;

                        // 2. 加入牛牌
                        niuCardList.add(cardList.get(firstCardIndex));
                        niuCardList.add(cardList.get(secondCardIndex));
                        niuCardList.add(cardList.get(thirdCardIndex));

                        // 3. 加入剩餘牌
                        for (int l = 0; l < cardList.size(); l++) {
                            if (l != firstCardIndex && l != secondCardIndex && l != thirdCardIndex) {
                                liftCardList.add(cardList.get(l));
                                niuCardList.add(cardList.get(l));
                            }
                        }
                        break;
                    }
                }
                if (isNiu) {
                    break;
                }
            }
            if (isNiu) {
                break;
            }
        }

        // 4. 取得牛牛分組結果
        if (!isNiu) {
            niuCardList = cardList;
        }

        // 5. 取得最後結果
        PreNiuResult result = new PreNiuResult();
        result.setNiu(isNiu);
        result.setNiuCardList(niuCardList);
        result.setLiftCardList(liftCardList);

        return result;
    }

    // 判斷氫彈牛
    public boolean checkHydrogenBomb(List<ICard> cardList) {
        return super.checkHydrogenBomb(cardList);
    }

    // 判斷五小牛
    public boolean checkSmallNiu(List<ICard> cardList) {
        // 1. 取得參數
        int maxPoint = 4;
        int maxSum = 10;

        // 2. 判斷
        return cardList.stream().filter(card -> card.getPoint() <= maxPoint).count() == cardList.size()
                && super.calculateCardPointSum(cardList) <= maxSum;
    }

    // 判斷順金牛
    public boolean checkShunJinNiu(List<ICard> cardList) {
        return super.checkShunJin(cardList);
    }

    // 判斷炸彈
    public boolean checkBomb(List<ICard> cardList) {
        return super.checkBomb(cardList);
    }

    // 判斷五花牛
    public boolean checkFiveFlower(List<ICard> cardList) {
        // 1. 取得參數
        int flowerCount = 5;
        int maxPoint = 11;

        // 2. 判斷是否5花牛
        return cardList.stream().filter(card -> card.getPoint() >= maxPoint).count() == flowerCount;
    }

    // 判斷葫蘆牛
    public boolean checkHuluNiu(List<ICard> cardList) {
        // 1. 取得參數
        int categorySize = 2;
        long huluCount = 3L;

        // 2. 點數分類
        Map<Integer, Long> pointCategoryCountMap = super.calculatePointCategoryCount(cardList);

        // 3. 判斷
        return (pointCategoryCountMap.size() == categorySize &&
                pointCategoryCountMap.values().stream().anyMatch(count -> count == huluCount));
    }

    // 判斷同花牛
    public boolean checkTongHuaNiu(List<ICard> cardList) {
        return super.checkTongHua(cardList);
    }

    // 判斷順子牛
    public boolean checkShunZiNiu(List<ICard> cardList) {
        return this.checkShunZi(cardList);
    }

    // 判斷四花牛
    public boolean checkFourFlower(List<ICard> cardList) {
        // 1. 取得參數
        int flowerCount = 4;
        int maxPoint = 11;

        // 2. 判斷是否四花牛
        return cardList.stream().filter(card -> card.getPoint() >= maxPoint).count() == flowerCount
                && super.calculateCardPointSumModuleBy10(cardList) == 0;
    }
}
