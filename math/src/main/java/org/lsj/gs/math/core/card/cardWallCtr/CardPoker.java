package org.lsj.gs.math.core.card.cardWallCtr;



import org.lsj.gs.math.core.card.ConstMathCard;

import java.util.Map;

// 卡牌
public class CardPoker implements ICard{
    private int number; // 編碼
    private int value; // 牌值
    private int type; // 花色
    private int point; // 點數
    private int weight; // 權重
    private ConstMathCard.CardState cardState; // 狀態

    public CardPoker() {}

    public CardPoker(int value, int countIndex, Map<Integer, Integer> cardValueWeightMap) {
        this.number = value * 10 + countIndex;
        this.value = value;
        this.type = value / 100;
        this.point = value % 100;
        this.weight = cardValueWeightMap.get(value % 100);
        this.cardState = ConstMathCard.CardState.UN_TAKEN;
    }

    public int getNumber() {
        return number;
    }

    public int getValue() {
        return value;
    }

    public int getType() {
        return type;
    }

    public int getPoint() {
        return point;
    }

    public int getWeight() {
        return weight;
    }

    public ConstMathCard.CardState getCardState() {
        return cardState;
    }

    public void setCardState(ConstMathCard.CardState cardState) {
        this.cardState = cardState;
    }

    public boolean isState(ConstMathCard.CardState cardState) {
        return this.cardState == cardState;
    }
}
