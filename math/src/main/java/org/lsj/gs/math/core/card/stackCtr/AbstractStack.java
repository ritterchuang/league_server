package org.lsj.gs.math.core.card.stackCtr;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;

import java.util.*;
import java.util.stream.Collectors;

// 抽象牌型
public abstract class AbstractStack implements Comparable<AbstractStack>{
    protected List<ICard> handCardList; // 手牌

    public AbstractStack(List<ICard> handCardList) {
        this.handCardList = handCardList;
    }

    public abstract int compareTo(AbstractStack abstractStack);

    public List<ICard> getHandCardList() {
        return handCardList;
    }

    //* 計算資訊相關 *//

    // 計算指定數量的牌值
    public int calculateSpecifyCountPointValue(List<ICard> cardList, int specifyCount) {
        Optional<Map.Entry<Integer, Long>> firstCountEntry =
                cardList.stream().collect(Collectors.groupingBy(ICard::getPoint, Collectors.counting())).entrySet().stream()
                        .filter(entry -> entry.getValue() == specifyCount).findFirst();

        if(firstCountEntry.isPresent()){
            return firstCountEntry.get().getKey();
        }

        return 0;
    }

    // 計算最大牌
    public ICard calculateMaxCard() {
        return this.calculateMaxCard(this.handCardList);
    }

    // 計算最大牌
    public ICard calculateMaxCard(List<ICard> cardList) {
        // 1. 複製
        List<ICard> cardListCopy = new ArrayList<>();
        cardList.forEach(iCard -> cardListCopy.add(iCard));

        // 2. 排序
        cardListCopy.sort(Comparator.comparing(ICard::getWeight).thenComparing(ICard::getType)
                .reversed());

        // 3. 回傳
        return cardListCopy.get(0);
    }


    //* 比較相關 *//

    // 比較順子
    protected int compareShunZi(AbstractStack stack, AbstractCardTypeUtil cardTypeUtil) {
        // 1. 複製
        List<ICard> mineCardListCopy = new ArrayList<>();
        List<ICard> theirCardListCopy = new ArrayList<>();
        this.handCardList.forEach(iCard -> mineCardListCopy.add(iCard));
        stack.handCardList.forEach(icard -> theirCardListCopy.add(icard));


        // 2. 牌組排序
        mineCardListCopy.sort(Comparator.comparing(ICard::getPoint));
        theirCardListCopy.sort(Comparator.comparing(ICard::getPoint));

        // 3. 判斷AK順
        boolean isShunZiAKMine = cardTypeUtil.checkShunZiAK(mineCardListCopy);
        boolean isShunZiAKTheir = cardTypeUtil.checkShunZiAK(theirCardListCopy);

        // 4. 比較AK順
        if(isShunZiAKMine && !isShunZiAKTheir){
            return 1;
        }

        if(!isShunZiAKMine && isShunZiAKTheir){
            return -1;
        }

        // 5. 剩餘判斷
        return this.compareMaxCard(stack);
    }

    // 比較最大牌大小
    public int compareMaxCard(AbstractStack stack) {
        // 1. 取得最大牌
        ICard maxCardMine = this.calculateMaxCard(this.handCardList);
        ICard maxCardTheirs = this.calculateMaxCard(stack.getHandCardList());

        // 2. 比較最大牌點數與花色
        return this.compareCard(maxCardMine, maxCardTheirs);
    }

    // 比較點數與花色大小
    public int compareCard(ICard card1, ICard card2) {
        // 1. 先比較點數
        if (card1.getWeight() > card2.getWeight()) {
            return 1;
        }

        if (card1.getWeight() < card2.getWeight()) {
            return -1;
        }

        // 2. 再比較花色
        return Integer.compare(card1.getType(), card2.getType());
    }

    // 比較點數大小
    public int compareCardPoint(ICard card1, ICard card2) {
        return Integer.compare(card1.getWeight(), card2.getWeight());
    }
}
