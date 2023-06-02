package org.lsj.gs.math.games.sg_java.entity.message;

public class CtsVieBankerSgJava extends AbstractCtsMessageSgJava {
    private int rate; // 倍數

    // 原始建構子提供JSON解析用
    public CtsVieBankerSgJava() {};

    public CtsVieBankerSgJava(int rate){
        super();
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}
