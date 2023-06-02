package org.lsj.gs.math.games.qznn_ksz_java.entity.message;

public class CtsSelectQznnKszJava extends AbstractCtsMessageQznnKszJava {
    private int[] cards; // 手牌

    // 原始建構子提供JSON解析用
    public CtsSelectQznnKszJava() {};

    public CtsSelectQznnKszJava(int[] cards) {
        this.cards = cards;
    }

    public int[] getCards() {
        return cards;
    }
}
