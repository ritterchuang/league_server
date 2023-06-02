package org.lsj.gs.math.games.ebg_java.entity.message;

public class CtsVieBankerEbgJava extends AbstractCtsMessageEbgJava {
    private int rate; // 倍數

    // 原始建構子提供JSON解析用
    public CtsVieBankerEbgJava() {};

    public CtsVieBankerEbgJava(int rate){
        super();
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}
