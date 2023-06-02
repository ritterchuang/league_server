package org.lsj.gs.math.core.card.stackCtr.bjlStackCtr;

import org.lsj.gs.math.core.baiRen.ConstMathBjl;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.AbstractCardTypeUtil;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 百家樂發牌工具包
public class BjlDealUtil extends AbstractCardTypeUtil {
    private final int initialDealCount = 2; // 初始發牌
    private final int tianPoint = 8; // 天牌點數

    private final List<Integer> bank3_noAddCard_playPtList = List.of(8); // 莊家 3 點不補牌時，第三張閒家點數列表
    private final List<Integer> bank4_noAddCard_playPtList = List.of(0, 1, 8, 9); // 莊家 4 點不補牌時，第三張閒家點數列表
    private final List<Integer> bank5_noAddCard_playPtList = List.of(0, 1, 2, 3, 8, 9); // 莊家 5 點不補牌時，第三張閒家點數列表
    private final List<Integer> bank6_noAddCard_playPtList = List.of(0, 1, 2, 3, 4, 5, 8, 9); // 莊家 6 點不補牌時，第三張閒家點數列表

    // 計算百家發牌資訊 <發牌區域, 牌序列表>
    public Map<Integer, List<Integer>> calculateDealAreaIdToCardIndexListMap(List<ICard> unTakenCardList) {
        // 1. 發初始牌
        Map<Integer, List<Integer>> result = IntStream.range(0, ConstMathBjl.DealAreaEnum.getDealAreaCount())
                .boxed()
                .collect(Collectors.toMap(
                        dealAreaId -> dealAreaId, this::calculateInitialCardIndexList
                ));

        // 2. 檢查是否有天牌
        if (this.checkTianCardType(result, unTakenCardList)) {
            return result;
        }

        // 3. 檢查閒家補牌
        if (this.checkAddPlayCard(result, unTakenCardList)) {
            result.get(ConstMathBjl.DealAreaEnum.PLAY.getCode())
                    .add(this.calculateAddCardIndex(result));
        }

        // 4. 檢查莊家補牌
        if (this.checkAddBankCard(result, unTakenCardList)) {
            result.get(ConstMathBjl.DealAreaEnum.BANK.getCode())
                    .add(this.calculateAddCardIndex(result));
        }

        return result;
    }

    // 計算初始牌序 列表
    private List<Integer> calculateInitialCardIndexList(int dealAreaId) {
        return IntStream.range(0, initialDealCount)
                .mapToObj(dealIndex -> dealAreaId * initialDealCount + dealIndex)
                .collect(Collectors.toList());
    }

    // 檢查天牌
    boolean checkTianCardType(Map<Integer, List<Integer>> dealAreaIdToCardIndexListMap, List<ICard> unTakenCardList) {
        return dealAreaIdToCardIndexListMap.values().stream()
                .map(cardIndexList ->
                        cardIndexList.stream().map(unTakenCardList::get).collect(Collectors.toList()))
                .anyMatch(cardList -> super.calculateCardPointSumModuleBy10(cardList) >= tianPoint);
    }

    // 檢查閒家補牌
    boolean checkAddPlayCard(Map<Integer, List<Integer>> dealAreaIdToCardIndexListMap, List<ICard> unTakenCardList) {
        return super.calculateCardPointSumModuleBy10(
                this.getCardListByDealAreaType(
                        dealAreaIdToCardIndexListMap,
                        unTakenCardList,
                        ConstMathBjl.DealAreaEnum.PLAY)) <= 5;
    }

    // 檢查莊家補牌 [反面敘述]
    boolean checkAddBankCard(Map<Integer, List<Integer>> dealAreaIdToCardIndexListMap, List<ICard> unTakenCardList) {
        // 1. 取得莊家點數、閒家第三張點數
        int bankPoint = super.calculateCardPointSumModuleBy10(
                this.getCardListByDealAreaType(
                        dealAreaIdToCardIndexListMap,
                        unTakenCardList,
                        ConstMathBjl.DealAreaEnum.BANK));
        Optional<Integer> playAddPoint = this.getPlayAddPoint(dealAreaIdToCardIndexListMap, unTakenCardList);

        // 2. 莊家 大於 6， 不補牌
        if (bankPoint > 6) {
            return false;
        }

        // 3. 閒家沒補，莊家 6，不補牌
        if (playAddPoint.isEmpty() && bankPoint == 6) {
            return false;
        }

        // 4. 閒家補牌屬於不補牌列表，莊家 6，不補牌
        if (playAddPoint.isPresent()
                && this.bank6_noAddCard_playPtList.stream().anyMatch(noAddPt -> Objects.equals(noAddPt, playAddPoint.get()))
                && bankPoint == 6) {
            return false;
        }

        // 5. 閒家補牌屬於不補牌列表，莊家 5，不補牌
        if (playAddPoint.isPresent()
                && this.bank5_noAddCard_playPtList.stream().anyMatch(noAddPt -> Objects.equals(noAddPt, playAddPoint.get()))
                && bankPoint == 5) {
            return false;
        }

        // 6. 閒家補牌屬於不補牌列表，莊家 4，不補牌
        if (playAddPoint.isPresent()
                && this.bank4_noAddCard_playPtList.stream().anyMatch(noAddPt -> Objects.equals(noAddPt, playAddPoint.get()))
                && bankPoint == 4) {
            return false;
        }

        // 7. 閒家補牌屬於不補牌列表，莊家 3，不補牌
        if (playAddPoint.isPresent()
                && this.bank3_noAddCard_playPtList.stream().anyMatch(noAddPt -> Objects.equals(noAddPt, playAddPoint.get()))
                && bankPoint == 3) {
            return false;
        }

        // 8. 其餘狀況皆 不補牌
        return true;
    }

    // 取得閒家補牌點數
    private Optional<Integer> getPlayAddPoint(
            Map<Integer, List<Integer>> dealAreaIdToCardIndexListMap
            , List<ICard> unTakenCardList) {

        List<ICard> playCardList = this.getCardListByDealAreaType(
                dealAreaIdToCardIndexListMap, unTakenCardList, ConstMathBjl.DealAreaEnum.PLAY);

        if (playCardList.size() == this.initialDealCount) {
            return Optional.empty();
        }

        return Optional.of(playCardList.get(playCardList.size() - 1).getPoint());
    }

    // 依照發牌區域，取點牌列表
    private List<ICard> getCardListByDealAreaType(
            Map<Integer, List<Integer>> dealAreaIdToCardIndexListMap
            , List<ICard> unTakenCardList
            , ConstMathBjl.DealAreaEnum dealAreaEnum) {

        return dealAreaIdToCardIndexListMap.get(dealAreaEnum.getCode()).stream()
                .map(unTakenCardList::get)
                .collect(Collectors.toList());
    }

    // 取得 補牌 Index
    private int calculateAddCardIndex(Map<Integer, List<Integer>> dealAreaIdToCardIndexListMap) {
        return dealAreaIdToCardIndexListMap.values().stream()
                .flatMapToInt(cardIndexList -> cardIndexList.stream().mapToInt(cardIndex -> cardIndex))
                .max().getAsInt() + 1;
    }
}
