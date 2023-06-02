package org.lsj.gs.math.core.slot.specialFeatureHlrMgr.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrConfig;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResult;
import org.lsj.utils.ListUtil;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// 消除類連續消除4次
public class SpecialFeatureHlr_09 extends AbstractSpecialFeatureHlr implements ISpecialFeatureHlr{
    private final int targetNumber = 4; // 目標消除數量
    private final SpecialFeatureHlrConfig config; // 設定檔
    private final ITableUtil tableUtil; // 牌桌工具包

    public SpecialFeatureHlr_09(SpecialFeatureHlrConfig specialFeatureHlrConfig, ITableUtil tableUtil) {
        super(new ArrayList<>()); // 標誌列表給予空陣列
        this.config = specialFeatureHlrConfig;
        this.tableUtil = tableUtil;
    }


    // 計算特殊事件結果
    @Override
    public Optional<SpecialFeatureHlrResult> calculateSpecialFeatureHlrResult(SpinRequest spinRequest, ScreenGtrResult screenGtrResult, List<CascadeHlrResult> cascadeHlrResultList) {
        // 1. 計算打擊畫面(全部沒中)
        List<List<Boolean>> hitScreen = ListUtil.create2DimensionBooleanList(screenGtrResult.getScreenSymbol(),Boolean.FALSE);

        // 2. 判斷是否達成條件
        if (this.isTrigger(cascadeHlrResultList) == false) {
            return Optional.empty();
        }

        // 3. 計算得分
        double totalWin = MathUtil.multiply(spinRequest.getPlayerCost(), this.config.getSpecialFeatureConfig().getBasePay());

        // 4. 回傳
        return Optional.of(
                new SpecialFeatureHlrResult(
                        this.config.getSpecialFeatureConfig().getSpecialFeatureType(),
                        this.config.getSpecialFeatureConfig().getTriggerEvent(),
                        hitScreen,
                        super.calculateDampScreenHitPosition(hitScreen, screenGtrResult.getReelCtrResult().getReelStopResultExtend().getDampCtrResult()),
                        MathUtil.moneyScale(totalWin)));
    }

    // 判斷是否有觸發條件
    private boolean isTrigger(List<CascadeHlrResult> cascadeHlrResultList) {

        // 1. 如List大小符合設定條件則回傳True
        return cascadeHlrResultList.size() == this.targetNumber;
    }
}
