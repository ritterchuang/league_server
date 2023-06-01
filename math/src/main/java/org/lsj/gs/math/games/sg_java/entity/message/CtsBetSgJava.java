package org.lsj.gs.math.games.sg_java.entity.message;

public class CtsBetSgJava extends AbstractCtsMessageSgJava {
    private int rate; // 下注倍數

    // 原始建構子提供JSON解析用
    public CtsBetSgJava(){}

    public CtsBetSgJava(int rate){
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}
