package org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.enity.DisplayResult;
import org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.enity.displayResultExtend.DisplayResultExtendRedEnvelope;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureResultExtend.SpecialFeatureResultExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureResultExtend.SpecialFeatureResultExtendRedEnvelope;

import java.util.Collections;
import java.util.List;

// 客製表演特殊事件工具包
public class DisplaySpecialFeatureResultUtil {

    // 計算特殊事件表演結果
    public List<DisplayResult> calculateSpecialFeatureDisplayResultList(ConstMathFish.SpecialFeatureEnumType specialFeatureEnumType, SpecialFeatureResultExtend specialFeatureResultExtend) {
        switch (specialFeatureEnumType) {
            case RED_ENVELOPE: return this.calculateSpecialFeatureRedEnvelopeDisplayResultList(specialFeatureResultExtend);
            default: return Collections.emptyList();
        }
    }

    // 計算特殊事件紅包表演結果
    private List<DisplayResult> calculateSpecialFeatureRedEnvelopeDisplayResultList(SpecialFeatureResultExtend specialFeatureResultExtend) {
        // 1. 取得轉型結果
        SpecialFeatureResultExtendRedEnvelope specialFeatureResultExtendRedEnvelope = (SpecialFeatureResultExtendRedEnvelope) specialFeatureResultExtend;

        // 2. 無擊殺，不包裝
        if (!specialFeatureResultExtendRedEnvelope.isKillFlag()) {
            return Collections.emptyList();
        }

        // 3. 包裝表演結果列表
        return List.of(new DisplayResult(ConstMathFish.DisplayType.RED_ENVELOPE, new DisplayResultExtendRedEnvelope(specialFeatureResultExtendRedEnvelope)));
    }
}
