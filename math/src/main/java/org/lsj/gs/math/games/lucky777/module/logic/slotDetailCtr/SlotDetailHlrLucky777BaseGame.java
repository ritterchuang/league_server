package org.lsj.gs.math.games.lucky777.module.logic.slotDetailCtr;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultNormal;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetail;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetailHlrConfig;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.module.AbstractSlotDetailHlr;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.module.ISlotDetailHlr;
import org.lsj.gs.math.games.lucky777.enity.config.SlotDetailHlrConfigExtendLucky777BaseGame;
import org.lsj.gs.math.games.lucky777.enity.result.slotDetail.SlotDetailLucky777BaseGame;

import java.util.ArrayList;
import java.util.List;

// 虎機詳細記錄計算者 Lucky777
public class SlotDetailHlrLucky777BaseGame extends AbstractSlotDetailHlr implements ISlotDetailHlr {
    private final ConstMathSlot.SlotDetailType slotDetailType; // 虎機詳細記錄類型
    private final SlotDetailHlrConfigExtendLucky777BaseGame extendConfig; // 虎機詳細記錄額外設定

    public SlotDetailHlrLucky777BaseGame(SlotDetailHlrConfig slotDetailHlrConfig, ITableUtil tableUtil1) {
        super(slotDetailHlrConfig, tableUtil1);
        this.slotDetailType = slotDetailHlrConfig.getSlotDetailType();
        this.extendConfig = (SlotDetailHlrConfigExtendLucky777BaseGame) slotDetailHlrConfig.getSlotDetailHlrConfigExtend();
    }

    // 計算虎機詳細記錄
    @Override
    public List<SlotDetail> calculateSlotDetail(SpinRequest spinRequest, GameStateHlrResult gameStateHlrResult) {
        // 1. 創建空殼
        List<SlotDetail> result = new ArrayList<>();

        // 2. 計算一般遊戲
        result.add(this.calculatePureBaseGameDetail(spinRequest, gameStateHlrResult));

        // 3. 回傳
        return result;
    }

    // 計算基礎遊戲詳細記錄
    private SlotDetailLucky777BaseGame calculatePureBaseGameDetail(SpinRequest spinRequest, GameStateHlrResult gameStateHlrResult) {
        // 1. 取得轉型局結果
        RoundHlrResultNormal roundHlrResultNormal = ((RoundHlrResultNormal) gameStateHlrResult.getRoundHlrResultList().get(0));

        // 2. 取得成本
        double spinCost = spinRequest.getPlayerCost();

        // 3. 取得第一欄結果
        String screenColumn_1 = super.calculateScreenColumn(roundHlrResultNormal.getScreenGtrResult().getScreenSymbol().get(0));

        // 4. 取得第二欄結果
        String screenColumn_2 = super.calculateScreenColumn(roundHlrResultNormal.getScreenGtrResult().getScreenSymbol().get(1));

        // 5. 取得第三欄結果
        String screenColumn_3 = super.calculateScreenColumn(roundHlrResultNormal.getScreenGtrResult().getScreenSymbol().get(2));

        // 8. 取得總得分
        double baseGameTotalWin = roundHlrResultNormal.getTotalWin();

        // 9. 回傳
        return new SlotDetailLucky777BaseGame(spinCost, baseGameTotalWin, screenColumn_1, screenColumn_2, screenColumn_3);
    }
}