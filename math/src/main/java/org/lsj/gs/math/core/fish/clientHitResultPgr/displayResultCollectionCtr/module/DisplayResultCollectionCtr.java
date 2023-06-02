package org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.module;

import org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.enity.DisplayResult;
import org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.enity.DisplayResultCollection;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;

import java.util.ArrayList;
import java.util.List;

// 表演結果聚合計算者
public class DisplayResultCollectionCtr implements IDisplayResultCollectionCtr {
    private final DisplayHitResultUtil displayHitResultUtil; // 客製表演打擊工具包
    private final DisplaySpecialFeatureResultUtil displaySpecialFeatureResultUtil; // 客製表演特殊事件工具包

    public DisplayResultCollectionCtr() {
        this.displayHitResultUtil = new DisplayHitResultUtil();
        this.displaySpecialFeatureResultUtil = new DisplaySpecialFeatureResultUtil();
    }

    // 計算客端表演資訊
    @Override
    public DisplayResultCollection calculateDisplayResultCollection(HitResult hitResult) {
        // 1. 計算額外打擊客製表演列表
        List<DisplayResult> hitDisplayResultList = this.displayHitResultUtil.calculateHitDisplayResultList(hitResult.getKillCount(), hitResult.getBasicWin(), hitResult.getHitType(), hitResult.getHitResultExtend());

        // 2. 計算特殊事件客製表演列表
        List<DisplayResult> SpecialFeatureDisplayResultList = this.displaySpecialFeatureResultUtil.calculateSpecialFeatureDisplayResultList(hitResult.getSpecialFeatureEnumType(), hitResult.getSpecialFeatureResultExtend());

        // 3. 合併客製表演列表
        List<DisplayResult> aggregateDisplayResultList = this.aggregateDisplayResult(hitDisplayResultList, SpecialFeatureDisplayResultList);

        // 4. 回傳
        return new DisplayResultCollection(aggregateDisplayResultList.toArray(DisplayResult[]::new));
    }

    // 聚合表演列表
    private List<DisplayResult> aggregateDisplayResult(List<DisplayResult> hitDisplayResultList, List<DisplayResult> SpecialFeatureDisplayResultList) {
        // 1. 創建空間
        List<DisplayResult> result = new ArrayList<>();

        // 2. 添加打擊事件客製表演
        result.addAll(hitDisplayResultList);

        // 3. 添加特殊事件客製表演
        result.addAll(SpecialFeatureDisplayResultList);

        // 4. 回傳
        return result;
    }
}
