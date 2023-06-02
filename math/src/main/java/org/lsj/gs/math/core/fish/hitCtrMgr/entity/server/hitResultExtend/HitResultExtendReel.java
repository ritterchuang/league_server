package org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend;

// 滾輪客製處理結果
public class HitResultExtendReel extends HitResultExtend{
    private final int firstNumber; // 滾輪百位數
    private final int secondNumber; // 滾輪十位數
    private final int thirdNumber; // 滾輪個位數

    public HitResultExtendReel(int firstNumber, int secondNumber, int thirdNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.thirdNumber = thirdNumber;
    }

    public HitResultExtendReel() {
        this.firstNumber = 0;
        this.secondNumber = 0;
        this.thirdNumber = 0;
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public int getThirdNumber() {
        return thirdNumber;
    }
}
