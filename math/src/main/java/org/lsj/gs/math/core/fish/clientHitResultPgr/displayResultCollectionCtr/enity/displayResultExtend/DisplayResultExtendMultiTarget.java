package org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.enity.displayResultExtend;

import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendMultiTarget;
import org.lsj.utils.MathUtil;

import java.util.Map;

// 客製表演多重目標
public class DisplayResultExtendMultiTarget extends AbstractDisplayResultExtendValue{
    private final Map<Integer, Double> targetWinMap; // 目標得分對應表 <目標索引, 得分>

    public DisplayResultExtendMultiTarget(int killCount, double basicWin, HitResultExtendMultiTarget hitResultExtendMultiTarget) {
        super(killCount, basicWin, MathUtil.moneyScale(MathUtil.multiply(killCount, basicWin)));
        this.targetWinMap = hitResultExtendMultiTarget.getTargetWinMap();
    }

    public Map<Integer, Double> getTargetWinMap() {
        return targetWinMap;
    }
}
