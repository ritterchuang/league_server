package org.lsj.gs.math.games.tbnn_java.entity.message;

public class CtsBetTbnnJava extends AbstractCtsMessageTbnnJava {
    private int rate; // 下注倍數

    // 原始建構子提供JSON解析用
    public CtsBetTbnnJava(){}

    public CtsBetTbnnJava(int rate){
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}
