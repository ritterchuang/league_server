package org.lsj.gs.math.core.card.vieBankerCtr;

import org.lsj.gs.math.core.card.ConstMathCard;

import java.util.List;

// 搶莊計算器設定 倍數列表
public class VieBankerCtrConfigRateList extends VieBankerCtrConfig{
    private final List<Integer> vieRateList; // 搶莊倍數列表
    private final List<Integer> vieThresholdList; // 搶莊倍數攜帶金額門檻列表

    public VieBankerCtrConfigRateList(ConstMathCard.BankerType bankerType, int maxUser, double baseScore, List<Integer> vieRateList, List<Integer> vieThresholdList) {
        super(bankerType, maxUser, baseScore);
        this.vieRateList = vieRateList;
        this.vieThresholdList = vieThresholdList;
    }

    public List<Integer> getVieRateList() {
        return vieRateList;
    }

    public List<Integer> getVieThresholdList() {
        return vieThresholdList;
    }
}
