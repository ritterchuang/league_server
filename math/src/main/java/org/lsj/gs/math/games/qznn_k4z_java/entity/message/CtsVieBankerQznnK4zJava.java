package org.lsj.gs.math.games.qznn_k4z_java.entity.message;

public class CtsVieBankerQznnK4zJava extends AbstractCtsMessageQznnK4zJava {
    private int rate; // 倍數

    // 原始建構子提供JSON解析用
    public CtsVieBankerQznnK4zJava() {};

    public CtsVieBankerQznnK4zJava(int rate){
        super();
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}
