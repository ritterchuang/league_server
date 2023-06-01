package com.lx.gs.math.games.olldbz_java.module.logic.slotDetailCtr;

import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultNormal;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.core.slot.ConstMathSlot;
import com.lx.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import com.lx.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetail;
import com.lx.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetailHlrConfig;
import com.lx.gs.math.core.slot.slotDetailHlrMgr.module.AbstractSlotDetailHlr;
import com.lx.gs.math.core.slot.slotDetailHlrMgr.module.ISlotDetailHlr;
import com.lx.gs.math.games.olldbz_java.entity.config.SlotDetailHlrConfigExtendOlldbzBaseGame;
import com.lx.gs.math.games.olldbz_java.entity.result.slotDetail.SlotDetailOlldbzBaseGame;

import java.util.ArrayList;
import java.util.List;

// 虎機詳細記錄計算者 歐賴利的寶藏虎機 基本遊戲
public class SlotDetailHlrOlldbzBaseGame extends AbstractSlotDetailHlr implements ISlotDetailHlr {
    private final ConstMathSlot.SlotDetailType slotDetailType; // 虎機詳細記錄類型
    private final SlotDetailHlrConfigExtendOlldbzBaseGame extendConfig; // 虎機詳細記錄額外設定

    public SlotDetailHlrOlldbzBaseGame(SlotDetailHlrConfig slotDetailHlrConfig, ITableUtil tableUtil1) {
        super(slotDetailHlrConfig, tableUtil1);
        this.slotDetailType = slotDetailHlrConfig.getSlotDetailType();
        this.extendConfig = (SlotDetailHlrConfigExtendOlldbzBaseGame) slotDetailHlrConfig.getSlotDetailHlrConfigExtend();
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
    private SlotDetailOlldbzBaseGame calculatePureBaseGameDetail(SpinRequest spinRequest, GameStateHlrResult gameStateHlrResult) {
        // 1. 取得轉型局結果
        RoundHlrResultNormal roundHlrResultNormal = ((RoundHlrResultNormal) gameStateHlrResult.getRoundHlrResultList().get(0));

        // 2. 取得成本
        double spinCost = spinRequest.getPlayerCost();

        // 3. 取得第一欄結果
        String screenColumn_1 = super.calculateScreenColumn(roundHlrResultNormal.getScreenGtrResult().getScreenSymbol().get(0));

        // 4. 取得第二欄結果
        String screenColumn_2 = super.calculateScreenColumn(roundHlrResultNormal.getScreenGtrResult().getScreenSymbol().get(1));

        // 5. 取得第四欄結果
        String screenColumn_3 = super.calculateScreenColumn(roundHlrResultNormal.getScreenGtrResult().getScreenSymbol().get(2));

        // 5. 取得第四欄結果
        String screenColumn_4 = super.calculateScreenColumn(roundHlrResultNormal.getScreenGtrResult().getScreenSymbol().get(3));

        // 5. 取得第四欄結果
        String screenColumn_5 = super.calculateScreenColumn(roundHlrResultNormal.getScreenGtrResult().getScreenSymbol().get(4));

        // 6. 取得總得分
        double baseGameTotalWin = roundHlrResultNormal.getTotalWin();

        // 7. 回傳
        return new SlotDetailOlldbzBaseGame(spinCost, baseGameTotalWin, screenColumn_1, screenColumn_2, screenColumn_3, screenColumn_4, screenColumn_5);
    }
}