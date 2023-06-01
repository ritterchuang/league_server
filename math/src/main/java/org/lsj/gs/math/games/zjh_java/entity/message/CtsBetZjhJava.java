package org.lsj.gs.math.games.zjh_java.entity.message;

public class CtsBetZjhJava extends AbstractCtsMessageZjhJava {
    private int rate; // 下注倍數

    // 原始建構子提供JSON解析用
    public CtsBetZjhJava(){}

    public CtsBetZjhJava(int rate){
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}
