package org.lsj.gs.math.games.zjh_java.entity.message;

public class CtsVieBankerZjhJava extends AbstractCtsMessageZjhJava {
    private int rate; // 倍數

    // 原始建構子提供JSON解析用
    public CtsVieBankerZjhJava() {};

    public CtsVieBankerZjhJava(int rate){
        super();
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}
