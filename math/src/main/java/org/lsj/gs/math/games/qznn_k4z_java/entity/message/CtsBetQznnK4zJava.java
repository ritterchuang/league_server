package org.lsj.gs.math.games.qznn_k4z_java.entity.message;

public class CtsBetQznnK4zJava extends AbstractCtsMessageQznnK4zJava {
    private int rate; // 下注倍數

    // 原始建構子提供JSON解析用
    public CtsBetQznnK4zJava(){}

    public CtsBetQznnK4zJava(int rate){
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}
