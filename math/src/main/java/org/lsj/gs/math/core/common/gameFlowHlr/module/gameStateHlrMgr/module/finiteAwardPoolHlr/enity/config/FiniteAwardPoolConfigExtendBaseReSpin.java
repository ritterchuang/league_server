package org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.config;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.BaseScreenReSpinScreenBox;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.OddsBox;

import java.util.List;
import java.util.Map;

// 倍數表演額外設定 基礎畫面與重轉
public class FiniteAwardPoolConfigExtendBaseReSpin extends FiniteAwardPoolConfigExtend {
    private final int emptySymbolId; // 空白標誌識別碼
    private final List<int[]> dampSymbolPairList; // 空白標誌 damp 列表
    private final Map<Integer, List<Integer>> symbolToDampSymbolWeightListMap; // 主要標誌對應 damp 的權重
    private final Map<Integer, List<BaseScreenReSpinScreenBox>> oddsIndexToScreenListMap; // <倍數索引, 畫面列表> 對應表

    public FiniteAwardPoolConfigExtendBaseReSpin(List<OddsBox> oddsBoxList, int emptySymbolId, List<int[]> dampSymbolPairList, Map<Integer, List<Integer>> symbolToDampSymbolWeightListMap, Map<Integer, List<BaseScreenReSpinScreenBox>> oddsIndexToScreenListMap) {
        super(oddsBoxList);
        this.emptySymbolId = emptySymbolId;
        this.dampSymbolPairList = dampSymbolPairList;
        this.symbolToDampSymbolWeightListMap = symbolToDampSymbolWeightListMap;
        this.oddsIndexToScreenListMap = oddsIndexToScreenListMap;
    }

    public int getEmptySymbolId() {
        return emptySymbolId;
    }

    public List<int[]> getDampSymbolPairList() {
        return dampSymbolPairList;
    }

    public Map<Integer, List<Integer>> getSymbolToDampSymbolWeightListMap() {
        return symbolToDampSymbolWeightListMap;
    }

    public Map<Integer, List<BaseScreenReSpinScreenBox>> getOddsIndexToScreenListMap() {
        return oddsIndexToScreenListMap;
    }
}
