package org.lsj.gs.math.games.qzpj_java.entity.message;

public class CtsVieBankerQzpjJava extends AbstractCtsMessageQzpjJava {
    private int rate; // 倍數

    // 原始建構子提供JSON解析用
    public CtsVieBankerQzpjJava() {};

    public CtsVieBankerQzpjJava(int rate){
        super();
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}
