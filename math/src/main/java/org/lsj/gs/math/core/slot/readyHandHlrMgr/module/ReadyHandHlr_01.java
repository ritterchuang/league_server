package org.lsj.gs.math.core.slot.readyHandHlrMgr.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.readyHandConfig.ReadyHandConfig;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.config.ReadyHandHlrConfig;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResult;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResultCluster;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.readyHandHlrExtendResult.ReadyHandHlrExtendResultReadyHand01;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// 聽牌處理者 01
// 畫面出現兩顆 FreeGame_01 後，後面的輪軸放慢，前面的輪軸有出現的標誌閃爍
public class ReadyHandHlr_01 extends AbstractReadyHandHlr implements IReadyHandHlr{
    private final ReadyHandConfig config; // 設定檔
    private final ITableUtil tableUtil; // 牌桌工具包
    private final ConstMathSlot.SymbolAttribute targetSymbolAttribute; // 目標標誌
    private final int targetSymbolCount; // 目標標誌個數

    public ReadyHandHlr_01(ReadyHandHlrConfig config, ITableUtil tableUtil) {
        super(config.getFrameConfig(), config.getSymbolConfig());

        this.config = config.getReadyHandConfig();
        this.tableUtil = tableUtil;

        this.targetSymbolAttribute = ConstMathSlot.SymbolAttribute.FREE_GAME_01;
        this.targetSymbolCount = 2;
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
    private ReadyHandHlrResult calculateReadyHandHlrResultPerColumn(List<List<Integer>> screenSymbol, int targetColumnIndex) {
        // 1. 宣告空間
        int totalCount = 0;

        // 2. 計算畫面出現指定標誌個數
        for (int columnIndex = 0; columnIndex < targetColumnIndex; columnIndex++) {
            totalCount += super.calculateTargetSymbolCountPerColumn(screenSymbol, this.targetSymbolAttribute, columnIndex);
        }

        // 3. 不滿足個數，不表演
        if (totalCount < this.targetSymbolCount) {
            return new ReadyHandHlrResult();
        }

        // 4. 滿足個數回傳該軸表演資訊
        return new ReadyHandHlrResult(
                this.config.getReadyHandType(),
                new ReadyHandHlrExtendResultReadyHand01(
                        List.of(super.symbolConfig.getTargetSymbolId(this.targetSymbolAttribute)),
                        targetColumnIndex));
    }
}
