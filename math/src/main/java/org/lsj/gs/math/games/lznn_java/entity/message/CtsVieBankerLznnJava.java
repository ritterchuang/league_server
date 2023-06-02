package org.lsj.gs.math.games.lznn_java.entity.message;

public class CtsVieBankerLznnJava extends AbstractCtsMessageLznnJava {
    private int rate; // 倍數

    // 原始建構子提供JSON解析用
    public CtsVieBankerLznnJava() {};

    public CtsVieBankerLznnJava(int rate){
        super();
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}
