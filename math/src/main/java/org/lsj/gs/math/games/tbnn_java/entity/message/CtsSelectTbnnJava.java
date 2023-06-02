package org.lsj.gs.math.games.tbnn_java.entity.message;

public class CtsSelectTbnnJava extends AbstractCtsMessageTbnnJava {
    private int[] cards; // 手牌
    private int type; // 選牌類型，1為攤牌，將自動選擇最大牌型
    private int code; // 選牌類型，1為攤牌，將自動選擇最大牌型

    // 原始建構子提供JSON解析用
    public CtsSelectTbnnJava() {};

    public CtsSelectTbnnJava(int[] cards, int type) {
        this.cards = cards;
        this.type = type;
    }

    public int[] getCards() {
        return cards;
    }

    public int getType() {
        return type;
    }
}
