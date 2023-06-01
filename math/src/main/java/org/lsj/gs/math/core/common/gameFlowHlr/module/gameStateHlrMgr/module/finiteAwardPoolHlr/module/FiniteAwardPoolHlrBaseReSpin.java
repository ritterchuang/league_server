package org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.IFiniteAwardPoolHlr;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.BaseScreenReSpinScreenBox;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.DampScreenBox;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.OddsBox;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.config.FiniteAwardPoolConfig;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.config.FiniteAwardPoolConfigExtend;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.config.FiniteAwardPoolConfigExtendBaseReSpin;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.result.FiniteAwardPoolResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.result.FiniteAwardPoolResultExtendBaseReSpin;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtilSlot;
import org.lsj.gs.math.core.slot.ConstMathSlot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 倍數表演處理者 基礎與重轉
public class FiniteAwardPoolHlrBaseReSpin implements IFiniteAwardPoolHlr {
    private final ConstMathSlot.FiniteAwardPoolType finiteAwardPoolType; // 倍數表演類型
    private final Map<ConstMathSlot.ReelRtpType, FiniteAwardPoolConfigExtend> reelHETypeToOddsPlayConfigExtendMap; // <高低表類型, 倍數表演額外設定> 對應表
    private final ITableUtilSlot tableUtilSlot; // 牌桌工具包

    public FiniteAwardPoolHlrBaseReSpin(FiniteAwardPoolConfig finiteAwardPoolConfig, ITableUtilSlot tableUtilSlot) {
        this.finiteAwardPoolType = finiteAwardPoolConfig.getFiniteAwardPoolType();
        this.reelHETypeToOddsPlayConfigExtendMap = finiteAwardPoolConfig.getReelRtpTypeToConfigExtendMap();
        this.tableUtilSlot = tableUtilSlot;
    }

    // 計算倍數表演結果
    @Override
    public FiniteAwardPoolResult calculateFiniteAwardResult(ConstMathSlot.ReelRtpType reelRtpType) {
        // 1. 取得對應設定檔
        FiniteAwardPoolConfigExtendBaseReSpin configExtend = (FiniteAwardPoolConfigExtendBaseReSpin) this.reelHETypeToOddsPlayConfigExtendMap.get(reelRtpType);

        // 2. 取得倍數索引
        int oddsIndex = this.tableUtilSlot.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(configExtend.getOddsBoxList().stream().map(OddsBox::getWeight).collect(Collectors.toList()), ConstMathCommon.AccuracyType.BILLION);

        // 3. 取得畫面索引
        int screenIndex = this.tableUtilSlot.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(configExtend.getOddsIndexToScreenListMap().get(oddsIndex).stream().map(BaseScreenReSpinScreenBox::getWeight).collect(Collectors.toList()), ConstMathCommon.AccuracyType.BILLION);

        // 4. 回傳結果
        return new FiniteAwardPoolResult(finiteAwardPoolType, new FiniteAwardPoolResultExtendBaseReSpin(configExtend.getOddsBoxList().get(oddsIndex).getOdds(), this.calculateDampScreenBoxList(configExtend.getOddsIndexToScreenListMap().get(oddsIndex).get(screenIndex).getScreenList(), configExtend)));
    }

    //* 計算 整體畫面 [Damp + 算分畫面]*//
    // 計算有Damp的畫面列表
    private List<DampScreenBox> calculateDampScreenBoxList(List<int[]> noDampScreenList, FiniteAwardPoolConfigExtendBaseReSpin configExtend) {
        // 1. 創建空間
        List<DampScreenBox> result = new ArrayList<>();

        // 2. 計算整體畫面
        for (int screenIndex = 0; screenIndex < noDampScreenList.size(); screenIndex++) {
            // 2.1 計算 damp
            List<int[]> dampList = this.calculateDampList(noDampScreenList.get(screenIndex), configExtend);

            // 2.2 計算整體畫面
            DampScreenBox dampScreenBox = this.calculateDampScreenBox(dampList, noDampScreenList.get(screenIndex));

            // 2.3 添加畫面
            result.add(dampScreenBox);
        }

        // 3. 回傳
        return result;
    }

    // 計算 Damp 與 畫面
    private DampScreenBox calculateDampScreenBox(List<int[]> dampList, int[] noDampScreen) {
        // 1. 計算上 Damp
        List<Integer> upperDamp = dampList.stream().map(damp -> damp[0]).collect(Collectors.toList());

        // 2. 計算下 Damp
        List<Integer> lowerDamp = dampList.stream().map(damp -> damp[1]).collect(Collectors.toList());

        // 3. 計算畫面
        List<List<Integer>> screenSymbol = new ArrayList<>();
        Stream.iterate(0, i -> i + 1).limit(noDampScreen.length).forEach((i -> screenSymbol.add(List.of(noDampScreen[i]))));

        return new DampScreenBox(upperDamp, lowerDamp, screenSymbol);
    }


    //** 計算 Damp **//
    // 計算damp列表
    private List<int[]> calculateDampList(int[] noDampScreen, FiniteAwardPoolConfigExtendBaseReSpin configExtend) {
        // 1. 創建空間
        List<int[]> result = new ArrayList<>();

        // 2. 計算 mainSymbol
        int mainSymbolIndex = this.calculateMainSymbolIndex(noDampScreen, configExtend);

        // 3. 計算Damp列表
        for (int columnIndex = 0; columnIndex < noDampScreen.length; columnIndex++) {
            if (noDampScreen[columnIndex] != configExtend.getEmptySymbolId()) {
                result.add(this.calculateSymbolDamp(configExtend));
            }else {
                result.add(this.calculateEmptyDamp(mainSymbolIndex, configExtend));
            }
        }

        // 4. 回傳
        return result;
    }

    // 計算主要 symbol
    private int calculateMainSymbolIndex(int[] noDampScreen, FiniteAwardPoolConfigExtendBaseReSpin configExtend) {
        // 1. 若全為空白，任選非空白
        if (Arrays.stream(noDampScreen).filter(symbolId -> symbolId == configExtend.getEmptySymbolId()).toArray().length == noDampScreen.length) {
            return this.tableUtilSlot.getMainRandomUtil().getRandomElement(new ArrayList<>(configExtend.getSymbolToDampSymbolWeightListMap().keySet()),
                    ConstMathCommon.AccuracyType.A32768);
        }

        // 2. 找出主要 symbol
        int[] mainSymbolCandidates = Arrays.stream(noDampScreen).filter(symbolId -> symbolId != configExtend.getEmptySymbolId()).toArray();
        switch (mainSymbolCandidates.length) {
            case 1:
            case 2: return  this.tableUtilSlot.getMainRandomUtil().getRandomElement(Arrays.stream(mainSymbolCandidates).boxed().collect(Collectors.toList()), ConstMathCommon.AccuracyType.A32768);
            default: return configExtend.getEmptySymbolId();
        }
    }

    // 計算一般標誌Damp
    private int[] calculateSymbolDamp(FiniteAwardPoolConfigExtendBaseReSpin configExtend) {
        return new int[]{configExtend.getEmptySymbolId(), configExtend.getEmptySymbolId()};
    }

    // 計算空白標誌Damp
    private int[] calculateEmptyDamp(int mainSymbolId, FiniteAwardPoolConfigExtendBaseReSpin configExtend) {
        return configExtend.getDampSymbolPairList().get(
                this.tableUtilSlot.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(
                        configExtend.getSymbolToDampSymbolWeightListMap().get(mainSymbolId), ConstMathCommon.AccuracyType.BILLION));

    }
}
