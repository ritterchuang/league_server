package org.lsj.gs.math.core.slot.readyHandHlrMgr.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.readyHandConfig.ReadyHandConfig;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.config.ReadyHandHlrConfig;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResult;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResultCluster;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.readyHandHlrExtendResult.ReadyHandHlrExtendResultReadyHand05;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// 聽牌處理者 05
// 畫面第1、2、3軸出現 BonusGame_01後，第4輪放慢，前面的輪軸有出現的標誌閃爍
public class ReadyHandHlr_05 extends AbstractReadyHandHlr implements IReadyHandHlr{
    private final ReadyHandConfig config; // 設定檔
    private final ITableUtil tableUtil; // 牌桌工具包
    private final List<ConstMathSlot.SymbolAttribute> targetSymbolAttributeList; // 目標標誌列表
    private final int targetColumnIndex; // 指定瞇牌軸

    public ReadyHandHlr_05(ReadyHandHlrConfig config, ITableUtil tableUtil) {
        super(config.getFrameConfig(), config.getSymbolConfig());

        this.config = config.getReadyHandConfig();
        this.tableUtil = tableUtil;

        this.targetSymbolAttributeList = List.of(ConstMathSlot.SymbolAttribute.FREE_GAME_01, ConstMathSlot.SymbolAttribute.WILD_01);
        this.targetColumnIndex = 3;
    }

    // 計算聽牌結果集合
    @Override
    public Optional<ReadyHandHlrResultCluster> calculateReadyHandHlrResultCluster(List<List<Integer>> screenSymbol, ProgressHlrResult progressHlrResult) {
        // 1. 滿足聽牌限制，不做聽牌
        if (this.config.getReadyHandLimitType().equals(ConstMathSlot.ReadyHandLimitType.MAX_TRIGGER_LIMIT)
        && progressHlrResult.isMaxProgress() == true) {
            return Optional.empty();

        }

        // 2. 計算聽牌結果
        List<ReadyHandHlrResult> readyHandHlrResultList = new ArrayList<>();
        for (int columnIndex = 0; columnIndex < screenSymbol.size(); columnIndex++) {
            readyHandHlrResultList.add(this.calculateReadyHandHlrResultPerColumn(screenSymbol, columnIndex));
        }

        // 3. 封裝結果
        return Optional.of(new ReadyHandHlrResultCluster(readyHandHlrResultList));
    }

    // 計算指定軸聽牌結果
    private ReadyHandHlrResult calculateReadyHandHlrResultPerColumn(List<List<Integer>> screenSymbol, int currentColumnIndex) {
        // 1. 非次要目標欄，接回傳 default
        if (currentColumnIndex != this.targetColumnIndex) {
            return new ReadyHandHlrResult();
        }

        // 2. 判斷前面幾軸是否都有出現目標標誌
        for (int columnIndex = 0; columnIndex < this.targetColumnIndex; columnIndex++) {
            if (super.calculateTargetSymbolCountPerColumn(screenSymbol, this.targetSymbolAttributeList, columnIndex) == 0) {
                return new ReadyHandHlrResult();
            }
        }

        // 3. 回傳
        return new ReadyHandHlrResult(
                this.config.getReadyHandType(),
                new ReadyHandHlrExtendResultReadyHand05(
                        super.calculateTargetSymbolIdList(this.targetSymbolAttributeList),
                        currentColumnIndex));

    }
}
