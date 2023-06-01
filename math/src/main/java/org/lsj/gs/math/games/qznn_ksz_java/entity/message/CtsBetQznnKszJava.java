package org.lsj.gs.math.games.qznn_ksz_java.entity.message;

public class CtsBetQznnKszJava extends AbstractCtsMessageQznnKszJava {
    private int rate; // 下注倍數

    // 原始建構子提供JSON解析用
    public CtsBetQznnKszJava(){}

    public CtsBetQznnKszJava(int rate){
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}
