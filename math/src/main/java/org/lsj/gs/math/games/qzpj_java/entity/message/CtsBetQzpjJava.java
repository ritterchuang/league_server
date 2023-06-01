package org.lsj.gs.math.games.qzpj_java.entity.message;

public class CtsBetQzpjJava extends AbstractCtsMessageQzpjJava {
    private int rate; // 下注倍數

    // 原始建構子提供JSON解析用
    public CtsBetQzpjJava(){}

    public CtsBetQzpjJava(int rate){
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}
