package org.lsj.gs.math.games.qznn_k4z_java.entity.message;

public class CtsSelectQznnK4zJava extends AbstractCtsMessageQznnK4zJava {
    private int[] cards; // 手牌

    // 原始建構子提供JSON解析用
    public CtsSelectQznnK4zJava() {};

    public CtsSelectQznnK4zJava(int[] cards) {
        this.cards = cards;
    }

    public int[] getCards() {
        return cards;
    }
}
