package org.lsj.gs.math.core.card.stackCtr.niuStackCtr.lzNiuStackCtr;

import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtr;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

// 賴子牛型工具包
public class LaiziCardUtil {
    private final int jokerType = 5; // 賴子牌型
    private final int maxCardType = 4; // 最大牌型


    // 產出所有 賴子牌
    public List<List<ICard>> calculatePossibleCardList(List<ICard> cardList, CardWallCtr cardWallCtr) {
        // 無賴子，直接回傳原牌組
        if (cardList.stream().noneMatch(card -> card.getType() == this.jokerType)) {
            return List.of(cardList);
        }

        // 有賴子同花，添加最大花色、同花花色
        if (this.checkLzTongHua(cardList)) {
            return this.calculateMaxAndTongHuaExhaustedCardList(cardList, cardWallCtr);
        }

        // 有賴子，添加最大花色
        return this.calculateMaxExhaustedCardList(cardList, cardWallCtr);
    }

    // 檢查賴子同花
    private boolean checkLzTongHua(List<ICard> cardList) {
        // 無賴子，一定不成立
        if (cardList.stream().noneMatch(card -> card.getType() == this.jokerType)) {
            return false;
        }

        // 計算各類型記數
        Map<Integer, Long> typeToCountMap = cardList.stream().map(ICard::getType).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // 非賴子牌型數 + 賴子數 = 總數 則有同花
        return typeToCountMap.entrySet()
                .stream()
                .anyMatch(entry ->
                        entry.getKey() != this.jokerType &&
                                entry.getValue() + typeToCountMap.get(this.jokerType) == cardList.size());
    }

    // 窮舉所有 牌組可能
    private List<List<ICard>> calculateExhaustedCardList(List<List<ICard>> targetCardList, List<ICard> possibleCardList) {
        List<List<ICard>> result = new ArrayList<>();

        for (List<ICard> cardList : targetCardList) {
            for (ICard card : possibleCardList) {
                List<ICard> temp = new ArrayList<>(cardList);
                temp.add(card);
                result.add(temp);
            }
        }

        return result;
    }

    // 列出賴子同花可能牌
    private List<ICard> generateLaiziPossibleCardList(ICard laiziCard, int tongHuaCardType, CardWallCtr cardWallCtr) {
        // 1. 若同花，與最大牌色一致，回傳
        if (tongHuaCardType == this.maxCardType) {
            return cardWallCtr.generateLaiziCardList(this.maxCardType, laiziCard);
        }

        // 2. 回傳所有牌可能
        List<ICard> maxCardList = cardWallCtr.generateLaiziCardList(this.maxCardType, laiziCard);
        maxCardList.addAll(cardWallCtr.generateLaiziCardList(tongHuaCardType, laiziCard));
        return maxCardList;
    }

    // 列出可能同花所有 牌組
    private List<List<ICard>> calculateMaxAndTongHuaExhaustedCardList(List<ICard> cardList, CardWallCtr cardWallCtr) {
        // 1. 將手牌分類
        List<ICard> normalCardList = cardList.stream().filter(card -> card.getType() != this.jokerType).collect(Collectors.toList());
        List<ICard> jokerCardList = cardList.stream().filter(card -> card.getType() == this.jokerType).collect(Collectors.toList());

        // 2. 依序窮舉所有牌組
        List<List<ICard>> result = List.of(normalCardList);
        for (ICard laiziCard : jokerCardList) {
            List<ICard> possibleCardList = this.generateLaiziPossibleCardList(laiziCard, this.calculateTongHuaCardType(cardList), cardWallCtr);
            result = this.calculateExhaustedCardList(result, possibleCardList);
        }

        // 3. 回傳
        return result;
    }

    // 計算同花牌色
    private int calculateTongHuaCardType(List<ICard> cardList) {
        return cardList.stream().filter(card -> card.getType() != this.jokerType).findFirst().get().getType();
    }

    // 列出最大所有 牌組
    private List<List<ICard>> calculateMaxExhaustedCardList(List<ICard> cardList, CardWallCtr cardWallCtr) {
        // 1. 將手牌分類
        List<ICard> normalCardList = cardList.stream().filter(card -> card.getType() != this.jokerType).collect(Collectors.toList());
        List<ICard> jokerCardList = cardList.stream().filter(card -> card.getType() == this.jokerType).collect(Collectors.toList());

        // 2. 依序窮舉所有牌組
        List<List<ICard>> result = List.of(normalCardList);
        for (ICard laiziCard : jokerCardList) {
            List<ICard> possibleCardList = cardWallCtr.generateLaiziCardList(this.maxCardType, laiziCard);
            result = this.calculateExhaustedCardList(result, possibleCardList);
        }

        // 3. 回傳
        return result;
    }
}
