package org.lsj.gs.math.games.qznn_java.entity.message;

public class CtsVieBankerQznnJava extends AbstractCtsMessageQznnJava {
    private int rate; // 倍數

    // 原始建構子提供JSON解析用
    public CtsVieBankerQznnJava() {};

    public CtsVieBankerQznnJava(int rate){
        super();
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}
