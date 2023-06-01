package org.lsj.gs.math.games.qznn_ksz_java.entity.message;

public class CtsVieBankerQznnKszJava extends AbstractCtsMessageQznnKszJava {
    private int rate; // 倍數

    // 原始建構子提供JSON解析用
    public CtsVieBankerQznnKszJava() {};

    public CtsVieBankerQznnKszJava(int rate){
        super();
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}
