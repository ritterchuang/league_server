package org.lsj.gs.skillTreeGtr;

// 個人期望差水池計算器
public class PersonExpectDiffPoolCtr {
    private double expectWinDiff; // 期望贏分差距
    private double expectBetDiff; // 期望押注差距

    public PersonExpectDiffPoolCtr(double expectWinDiff, double expectBetDiff) {
        this.expectWinDiff = expectWinDiff;
        this.expectBetDiff = expectBetDiff;
    }

    public void updateExpectWinDiff(double expectWinDiff){
        this.expectWinDiff += expectWinDiff;
    }

    public void updateExpectBetDiff(double expectBetDiff){
        this.expectBetDiff += expectBetDiff;
    }

    public double getExpectWinDiff() {
        return expectWinDiff;
    }

    public double getExpectBetDiff() {
        return expectBetDiff;
    }

    public void setExpectWinDiff(double expectWinDiff) {
        this.expectWinDiff = expectWinDiff;
    }

    public void setExpectBetDiff(double expectBetDiff) {
        this.expectBetDiff = expectBetDiff;
    }
}
