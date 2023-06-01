package org.lsj.gs.math.core.slot.gameCtrMgr.module.commonUtil;

import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayTableConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.DampCtrResult;
import org.lsj.utils.ListUtil;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

// 虎機抽象計算共同工具包 
public abstract class AbstractCtrCommonUtil {
    protected final SymbolConfig symbolConfig; // 標誌設定
    protected final PayTableConfig payTableConfig; // 賠率設定
    protected final DampScreenHitPositionUtil dampScreenHitPositionUtil; // 破框畫面位置工具包

    public AbstractCtrCommonUtil(SymbolConfig symbolConfig, PayTableConfig payTableConfig) {
        this.symbolConfig = symbolConfig;
        this.payTableConfig = payTableConfig;
        this.dampScreenHitPositionUtil = new DampScreenHitPositionUtil();
    }

    // 取得賠率表 目標標誌列表 [index] = 標誌
    public List<Integer> getPayComboTargetSymbolIdList(int payComboId) {
        if (this.payTableConfig.getPayComboList().size() < payComboId || payComboId < 0) {
            return Collections.emptyList();
        }

        return this.payTableConfig.getPayComboList().get(payComboId).getSymbolIdList();
    }

    // 取得賠率表Id
    public int getPayComboIdBySymbolList(List<Integer> symbolIdList) {
        if (symbolIdList.isEmpty()) {
            return -1;
        }

        return IntStream.range(0, this.payTableConfig.getPayComboList().size())
                .filter(payComboId -> ListUtil.isSameListWithoutOrder(this.payTableConfig.getPayComboList().get(payComboId).getSymbolIdList(), symbolIdList))
                .findAny()
                .orElse(-1);
    }

    // 計算打擊倍數
    public int calculateHitOdds(int payComboId, int hitNumber) {
        if (this.payTableConfig.getPayComboList().size() < payComboId || payComboId < 0) {
            return 0;
        }

        if (this.payTableConfig.getPayComboList().get(payComboId).getPayOddsList().size() < hitNumber || hitNumber <= 0) {
            return 0;
        }

        return this.payTableConfig.getPayComboList().get(payComboId).getPayOddsList().get(hitNumber - 1);
    }

    // 計算破框中獎畫面
    public List<List<Boolean>> calculateDampScreenHitPosition(List<List<Boolean>> screenPosition, DampCtrResult dampCtrResult) {
        return this.dampScreenHitPositionUtil.calculateDampScreenHitPosition(screenPosition, dampCtrResult);
    }
}
