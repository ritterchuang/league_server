package com.lx.gs.math.games.pxky_java.module.logic.slotDetailCtr;

import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultNormal;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.roundResultExtend.RoundHlrResultExtend;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.core.slot.ConstMathSlot;
import com.lx.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import com.lx.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.ReelStopResultExtendStopByDependentReelIndex;
import com.lx.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetail;
import com.lx.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetailHlrConfig;
import com.lx.gs.math.core.slot.slotDetailHlrMgr.module.AbstractSlotDetailHlr;
import com.lx.gs.math.core.slot.slotDetailHlrMgr.module.ISlotDetailHlr;
import com.lx.gs.math.games.pxky_java.enity.config.SlotDetailHlrConfigExtendPxky;
import com.lx.gs.math.games.pxky_java.enity.result.roundResult.RoundHlrResultExtendPxkyBaseGame;
import com.lx.gs.math.games.pxky_java.enity.result.slotDetail.SlotDetailPxkyBaseGame;
import com.lx.gs.math.games.pxky_java.enity.result.slotDetail.SlotDetailPxkyReSpin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// 虎機詳細記錄計算者貔貅開運
public class SlotDetailHlrPxky extends AbstractSlotDetailHlr implements ISlotDetailHlr {
    private final ConstMathSlot.SlotDetailType slotDetailType; // 虎機詳細記錄類型
    private final SlotDetailHlrConfigExtendPxky extendConfig; // 虎機詳細記錄額外設定
    private final int emptySymbolId; // 空白標誌識別碼

    public SlotDetailHlrPxky(SlotDetailHlrConfig slotDetailHlrConfig, ITableUtil tableUtil1) {
        super(slotDetailHlrConfig, tableUtil1);
        this.slotDetailType = slotDetailHlrConfig.getSlotDetailType();
        this.extendConfig = (SlotDetailHlrConfigExtendPxky) slotDetailHlrConfig.getSlotDetailHlrConfigExtend();
        this.emptySymbolId = this.calculateEmptySymbolId();
    }

    // 計算虎機詳細記錄
    @Override
    public List<SlotDetail> calculateSlotDetail(SpinRequest spinRequest, GameStateHlrResult gameStateHlrResult) {
        // 1. 創建空殼
        List<SlotDetail> result = new ArrayList<>();

        // 2. 計算一般遊戲
        result.add(this.calculatePureBaseGameDetail(spinRequest, gameStateHlrResult));

        // 3. 計算重轉遊戲
        Optional<SlotDetailPxkyReSpin> slotDetailPxkyReSpin = this.calculateReSpinDetail(gameStateHlrResult);
        slotDetailPxkyReSpin.ifPresent(result::add);

        // 4. 回傳
        return result;
    }

    // 計算基礎遊戲詳細記錄
    private SlotDetailPxkyBaseGame calculatePureBaseGameDetail(SpinRequest spinRequest, GameStateHlrResult gameStateHlrResult) {
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

        // 6. 取得總得分
        double baseGameTotalWin = roundHlrResultNormal.getGameCtrResult().getTotalWin();

        // 7. 回傳
        return new SlotDetailPxkyBaseGame(spinCost, screenColumn_1, screenColumn_2, screenColumn_3, baseGameTotalWin);
    }


    // 計算重轉遊戲詳細記錄
    private Optional<SlotDetailPxkyReSpin> calculateReSpinDetail(GameStateHlrResult gameStateHlrResult) {
        // 1. 取得轉型局結果
        RoundHlrResultNormal roundHlrResultNormal = ((RoundHlrResultNormal) gameStateHlrResult.getRoundHlrResultList().get(0));

        // 2. 無重轉資訊，回傳空
        if (roundHlrResultNormal.getRoundNormalGameType().equals(ConstMathSlot.RoundNormalGameType.DEFAULT))
            return Optional.empty();

        // 3. 取得第一欄結果
        String screenColumn_1 = super.calculateScreenColumn(((RoundHlrResultNormal) gameStateHlrResult.getRoundHlrResultList().get(0)).getScreenGtrResult().getScreenSymbol().get(0));

        // 4. 取得第二欄結果
        String screenColumn_2 = super.calculateScreenColumn(((RoundHlrResultNormal) gameStateHlrResult.getRoundHlrResultList().get(0)).getScreenGtrResult().getScreenSymbol().get(1));

        // 5. 取得第三欄結果
        String screenColumn_3 = this.calculateReSpinSymbol(roundHlrResultNormal.getRoundHlrResultExtend());

        // 6. 取得重轉得分
        double reSpinTotalWin = roundHlrResultNormal.getRoundHlrResultExtend().getTotalWin();

        // 7. 回傳
        return Optional.of(new SlotDetailPxkyReSpin(screenColumn_1, screenColumn_2, screenColumn_3, reSpinTotalWin));
    }

    // 取得重轉畫面標誌
    private String calculateReSpinSymbol(RoundHlrResultExtend roundHlrResultExtend) {
        // 1. 創建空間
        StringBuilder stringBuilder = new StringBuilder();

        // 2. 轉型局處理者額外結果
        RoundHlrResultExtendPxkyBaseGame roundHlrResultExtendPxkyBaseGame = (RoundHlrResultExtendPxkyBaseGame) roundHlrResultExtend;

        // 3. 依照設定檔取得標誌
        int reSpinSymbolId = this.calculateReSpinSymbolId(roundHlrResultExtendPxkyBaseGame);

        // 4. 添加標誌名稱
        stringBuilder.append(this.extendConfig.getSymbolIdToStringMap().get(reSpinSymbolId));

        // 5. 回傳
        return stringBuilder.toString();
    }

    // 計算空白標誌識別碼
    private int calculateEmptySymbolId() {
        for (int symbolId = 0; symbolId < this.extendConfig.getSymbolConfig().getSymbolAttributeList().size(); symbolId++) {
            if (this.extendConfig.getSymbolConfig().getSymbolAttributeTypeList().get(symbolId).equals(ConstMathSlot.SymbolAttributeType.EMPTY_SYMBOL)) {
                return symbolId;
            }
        }

        return 0;
    }

    // 計算重轉標誌
    private int calculateReSpinSymbolId(RoundHlrResultExtendPxkyBaseGame roundHlrResultExtendPxkyBaseGame) {
        // 1. 取得停輪位置
        int reelStopIndex = ((ReelStopResultExtendStopByDependentReelIndex)roundHlrResultExtendPxkyBaseGame.getScreenGtrResult().getReelCtrResult().getReelStopResultExtend()).getReelStopIndexList().get(
                ((ReelStopResultExtendStopByDependentReelIndex)roundHlrResultExtendPxkyBaseGame.getScreenGtrResult().getReelCtrResult().getReelStopResultExtend()).getReelStopIndexList().size() - 1);

        // 2. 回傳停輪標誌
        return roundHlrResultExtendPxkyBaseGame.getReSpinStrip().get(reelStopIndex);
    }
}
