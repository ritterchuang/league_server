package org.lsj.gs.math.games.lznn_java.entity.message;

public class CtsBetLznnJava extends AbstractCtsMessageLznnJava {
    private int rate; // 下注倍數

    // 原始建構子提供JSON解析用
    public CtsBetLznnJava(){}

    public CtsBetLznnJava(int rate){
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}
