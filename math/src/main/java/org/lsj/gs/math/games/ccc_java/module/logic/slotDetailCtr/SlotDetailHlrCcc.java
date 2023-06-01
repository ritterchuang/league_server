package org.lsj.gs.math.games.ccc_java.module.logic.slotDetailCtr;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultNormal;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.roundResultExtend.RoundHlrResultExtend;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.ReelStopResultExtendStopByDependentReelIndex;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetail;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetailHlrConfig;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.module.AbstractSlotDetailHlr;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.module.ISlotDetailHlr;
import org.lsj.gs.math.games.ccc_java.entity.config.SlotDetailHlrConfigExtendCcc;
import org.lsj.gs.math.games.ccc_java.entity.result.roundResult.RoundHlrResultExtendCccBaseGame;
import org.lsj.gs.math.games.ccc_java.entity.result.slotDetail.SlotDetailCccBaseGame;
import org.lsj.gs.math.games.ccc_java.entity.result.slotDetail.SlotDetailCccReSpin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// 虎機詳細記錄計算者777
public class SlotDetailHlrCcc extends AbstractSlotDetailHlr implements ISlotDetailHlr {
    private final ConstMathSlot.SlotDetailType slotDetailType; // 虎機詳細記錄類型
    private final SlotDetailHlrConfigExtendCcc extendConfig; // 虎機詳細記錄額外設定
    private final int emptySymbolId; // 空白標誌識別碼

    public SlotDetailHlrCcc(SlotDetailHlrConfig slotDetailHlrConfig, ITableUtil tableUtil1) {
        super(slotDetailHlrConfig, tableUtil1);
        this.slotDetailType = slotDetailHlrConfig.getSlotDetailType();
        this.extendConfig = (SlotDetailHlrConfigExtendCcc) slotDetailHlrConfig.getSlotDetailHlrConfigExtend();
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
        Optional<SlotDetailCccReSpin> slotDetailCccReSpin = this.calculateReSpinDetail(gameStateHlrResult);
        if (slotDetailCccReSpin.isPresent()) {
            result.add(slotDetailCccReSpin.get());
        }

        // 4. 回傳
        return result;
    }

    // 計算基礎遊戲詳細記錄
    private SlotDetailCccBaseGame calculatePureBaseGameDetail(SpinRequest spinRequest, GameStateHlrResult gameStateHlrResult) {
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
        return new SlotDetailCccBaseGame(spinCost, screenColumn_1, screenColumn_2, screenColumn_3, baseGameTotalWin);
    }


    // 計算重轉遊戲詳細記錄
    private Optional<SlotDetailCccReSpin> calculateReSpinDetail(GameStateHlrResult gameStateHlrResult) {
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
        return Optional.of(new SlotDetailCccReSpin(screenColumn_1, screenColumn_2, screenColumn_3, reSpinTotalWin));
    }

    // 取得重轉畫面標誌
    private String calculateReSpinSymbol(RoundHlrResultExtend roundHlrResultExtend) {
        // 1. 創建空間
        StringBuilder stringBuilder = new StringBuilder();

        // 2. 轉型局處理者額外結果
        RoundHlrResultExtendCccBaseGame roundHlrResultExtendCccBaseGame = (RoundHlrResultExtendCccBaseGame) roundHlrResultExtend;

        // 3. 依照設定檔取得標誌
        int reSpinSymbolId = this.calculateReSpinSymbolId(roundHlrResultExtendCccBaseGame);

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
    private int calculateReSpinSymbolId(RoundHlrResultExtendCccBaseGame roundHlrResultExtendCccBaseGame) {
        // 1. 取得停輪位置
        int reelStopIndex = ((ReelStopResultExtendStopByDependentReelIndex)roundHlrResultExtendCccBaseGame.getScreenGtrResult().getReelCtrResult().getReelStopResultExtend()).getReelStopIndexList().get(
                ((ReelStopResultExtendStopByDependentReelIndex)roundHlrResultExtendCccBaseGame.getScreenGtrResult().getReelCtrResult().getReelStopResultExtend()).getReelStopIndexList().size() - 1);

        // 2. 回傳停輪標誌
        return roundHlrResultExtendCccBaseGame.getReSpinStrip().get(reelStopIndex);
    }
}
