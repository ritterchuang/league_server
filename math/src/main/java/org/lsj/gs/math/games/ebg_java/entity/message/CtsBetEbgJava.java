package org.lsj.gs.math.games.ebg_java.entity.message;

public class CtsBetEbgJava extends AbstractCtsMessageEbgJava {
    private int rate; // 下注倍數

    // 原始建構子提供JSON解析用
    public CtsBetEbgJava(){}

    public CtsBetEbgJava(int rate){
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}
