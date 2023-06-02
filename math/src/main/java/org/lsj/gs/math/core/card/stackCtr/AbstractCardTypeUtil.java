package org.lsj.gs.math.core.card.stackCtr;

import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;

import java.util.*;
import java.util.stream.Collectors;

// 抽象牌型工具包
public abstract class AbstractCardTypeUtil {

    // 計算點數總和
    public int calculateCardPointSum(List<ICard> cardList) {
        // 1. 取得參數
        int maxPoint = 10;

        // 2. 回傳
        return cardList.stream().mapToInt(card -> Math.min(card.getPoint(), maxPoint)).sum();
    }

    // 計算點數總和
    public int calculateCardPointSumModuleBy10(List<ICard> cardList) {
        // 1. 取得參數
        int maxPoint = 10;

        // 2. 回傳
        return cardList.stream().mapToInt(card -> Math.min(card.getPoint(), maxPoint)).sum() % maxPoint;
    }

    // 計算點數分類
    protected Map<Integer, Long> calculatePointCategoryCount(List<ICard> cardList){
        return cardList.stream().collect(Collectors.groupingBy(ICard::getPoint, Collectors.counting()));
    }

    // 判斷順金
    protected boolean checkShunJin(List<ICard> cardList) {
        return (this.checkTongHua(cardList) && this.checkShunZi(cardList));
    }

    // 判斷炸彈
    protected boolean checkBomb(List<ICard> cardList) {
        // 1. 取得參數
        long bombCount = 4L;

        // 2. 點數分類
        Map<Integer, Long> pointCategoryCountMap = this.calculatePointCategoryCount(cardList);

        // 3. 判斷
        return pointCategoryCountMap.values().stream().anyMatch(count -> count == bombCount);
    }

    // 判斷氫彈
    protected boolean checkHydrogenBomb(List<ICard> cardList) {
        // 1. 取得參數
        long bombCount = 5L;

        // 2. 點數分類
        Map<Integer, Long> pointCategoryCountMap = this.calculatePointCategoryCount(cardList);

        // 3. 判斷
        return pointCategoryCountMap.values().stream().anyMatch(count -> count == bombCount);
    }

    // 判斷同花
    protected boolean checkTongHua(List<ICard> cardList){
        // 1. 取得第一張花色
        int type = cardList.get(0).getType();

        // 2. 遍歷一致性
        return cardList.stream().allMatch(card -> card.getType() == type);
    }

    // 計算是否順子
    protected boolean checkShunZi(List<ICard> cardList){
        // 1. 複製
        List<ICard> cardListCopy = new ArrayList<>();
        cardList.forEach(iCard -> cardListCopy.add(iCard));

        // 2. 排序
        cardListCopy.sort(Comparator.comparing(ICard::getPoint));

        // 3. 判斷AK順
        if(this.checkShunZiAK(cardList)){
            return true;
        }

        // 4. 判斷其他順
        int startPoint = cardListCopy.get(0).getPoint();
        for(int cardIndex = 0; cardIndex < cardListCopy.size(); cardIndex++){
            if(cardListCopy.get(cardIndex).getPoint() != (startPoint + cardIndex)){
                return false;
            }
        }
        return true;
    }

    // 判斷AK順
    public boolean checkShunZiAK(List<ICard> cardList){
        // 1. 防呆
        if(Objects.isNull(cardList) || cardList.size() <= 1 || cardList.size() >= 14){
            return false;
        }

        // 2. 複製
        List<ICard> cardListCopy = new ArrayList<>();
        cardList.forEach(iCard -> cardListCopy.add(iCard));

        // 3. 排序
        cardListCopy.sort(Comparator.comparing(ICard::getPoint));

        // 4. 判斷第一張為A
        if(!(cardListCopy.get(0).getPoint() == ConstMathCard.PointType.P_A.getPoint())){
            return false;
        }

        // 5. 判斷最後一張為K
        if(!(cardListCopy.get(cardList.size() - 1).getPoint() == ConstMathCard.PointType.P_K.getPoint())){
            return false;
        }

        // 6. 判斷其他牌
        for (int cardIndex = (cardListCopy.size() - 2); cardIndex >= 1; cardIndex--) {
            if(cardListCopy.get(cardIndex).getPoint() != (ConstMathCard.PointType.P_Q.getPoint() - (cardListCopy.size() - 2 - cardIndex))){
                return false;
            }
        }

        return true;
    }
}
