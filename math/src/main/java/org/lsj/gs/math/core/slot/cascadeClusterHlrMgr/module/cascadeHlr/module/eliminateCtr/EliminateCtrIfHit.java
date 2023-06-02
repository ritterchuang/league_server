package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.eliminateCtr;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.eliminateCtr.enity.config.EliminateCtrConfig;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.eliminateCtr.enity.result.EliminateCtrResult;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.GameCtrResult;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.eliminationConfig.EliminationConfigExtendNormal;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;
import org.lsj.utils.ListUtil;

import java.util.Collections;
import java.util.List;

// 消除計算者 如果沒得分則不消除
public class EliminateCtrIfHit implements IEliminateCtr {
    private final ConstMathSlot.EliminationType eliminationType; // 消除類型
    private final EliminationConfigExtendNormal configExtend; // 消除額外設定
    private final SymbolConfig symbolConfig; // 標誌設定
    private final ITableUtil tableUtil; // 牌桌工具包

    public EliminateCtrIfHit(EliminateCtrConfig eliminateCtrConfig, ITableUtil tableUtil) {
        this.eliminationType = eliminateCtrConfig.getEliminationConfig().getEliminationType();
        this.symbolConfig = eliminateCtrConfig.getSymbolConfig();
        this.configExtend = (EliminationConfigExtendNormal) eliminateCtrConfig.getEliminationConfig().getEliminationConfigExtend();
        this.tableUtil = tableUtil;
    }

    // 計算消除計算者結果
    @Override
    public EliminateCtrResult calculateEliminationCtrResult(GameCtrResult gameCtrResult,
                                                            SpecialFeatureHlrResultCluster specialFeatureHlrResultCluster,
                                                            ScreenGtrResult screenGtrResult) {
        if(gameCtrResult.getTotalWin() > 0) {
            // 1. 計算一般消除結果(得分與特殊事件)
            List<List<Boolean>> eliminatePosition = this.calculateEliminatePosition(gameCtrResult.getIntegralHitPosition(), specialFeatureHlrResultCluster.getIntegralHitPosition());

            // 2. 計算含破框資訊消除結果(得分與特殊事件)
            List<List<Boolean>> eliminatePositionDamp = this.calculateEliminatePosition(gameCtrResult.getIntegralDampHitPosition(), specialFeatureHlrResultCluster.getIntegralDampHitPosition());

            return new EliminateCtrResult(this.eliminationType, eliminatePosition, eliminatePositionDamp);
        }
        // 3. 回傳結果
        return new EliminateCtrResult(this.eliminationType, Collections.emptyList(), Collections.emptyList());
    }

    // 計算消除位置 TODO 父類別?
    private List<List<Boolean>> calculateEliminatePosition(List<List<Boolean>> targetArrayA, List<List<Boolean>> targetArrayB) {
        if (targetArrayA.isEmpty() && targetArrayB.isEmpty()) {
            return Collections.emptyList();
        }

        if (targetArrayA.isEmpty()) {
            return targetArrayB;
        }

        if (targetArrayB.isEmpty()) {
            return targetArrayA;
        }

        return ListUtil.unionSameSize2DimensionBooleanList(targetArrayA, targetArrayB);
    }
}
