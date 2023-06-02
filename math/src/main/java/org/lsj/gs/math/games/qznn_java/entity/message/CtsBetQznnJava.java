package org.lsj.gs.math.games.qznn_java.entity.message;

public class CtsBetQznnJava extends AbstractCtsMessageQznnJava {
    private int rate; // 下注倍數

    // 原始建構子提供JSON解析用
    public CtsBetQznnJava(){}

    public CtsBetQznnJava(int rate){
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}
