package com.lx.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.enity.displayResultExtend;

import com.lx.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendReel;
import com.lx.utils.MathUtil;

// 客製表演滾輪
public class DisplayResultExtendReel extends AbstractDisplayResultExtendValue{
    private final int firstNumber; // 滾輪百位數
    private final int secondNumber; // 滾輪十位數
    private final int thirdNumber; // 滾輪個位數

    public DisplayResultExtendReel(int killCount, double basicWin, HitResultExtendReel hitResultExtendReel) {
        super(killCount, basicWin, MathUtil.moneyScale(MathUtil.multiply(killCount, basicWin)));
        this.firstNumber = hitResultExtendReel.getFirstNumber();
        this.secondNumber = hitResultExtendReel.getSecondNumber();
        this.thirdNumber = hitResultExtendReel.getThirdNumber();
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
