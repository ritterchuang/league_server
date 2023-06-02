package org.lsj.gs.math.games.swzs_java.module.logic.slotDetailCtr;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultNormal;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResultExtendTriggerRound;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.RoundProgress;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetail;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetailHlrConfig;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.module.AbstractSlotDetailHlr;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.module.ISlotDetailHlr;
import org.lsj.gs.math.games.swzs_java.entity.config.SlotDetailHlrConfigExtendSwzsFreeGame;
import org.lsj.gs.math.games.swzs_java.entity.result.slotDetail.SlotDetailSwzsFreeGame;

import java.util.ArrayList;
import java.util.List;

// 虎機詳細記錄計算者 死亡之書虎機免費遊戲
public class SlotDetailHlrSwzsFreeGame extends AbstractSlotDetailHlr implements ISlotDetailHlr {
    private final ConstMathSlot.SlotDetailType slotDetailType; // 虎機詳細記錄類型
    private final SlotDetailHlrConfigExtendSwzsFreeGame extendConfig; // 虎機詳細記錄額外設定

    public SlotDetailHlrSwzsFreeGame(SlotDetailHlrConfig slotDetailHlrConfig, ITableUtil tableUtil1) {
        super(slotDetailHlrConfig, tableUtil1);
        this.slotDetailType = slotDetailHlrConfig.getSlotDetailType();
        this.extendConfig = (SlotDetailHlrConfigExtendSwzsFreeGame) slotDetailHlrConfig.getSlotDetailHlrConfigExtend();
    }

    // 計算虎機詳細記錄
    @Override
    public List<SlotDetail> calculateSlotDetail(SpinRequest spinRequest, GameStateHlrResult gameStateHlrResult) {
        // 1. 創建空殼
        List<SlotDetail> result = new ArrayList<>();

        // 2. 取得所有場結果
        List<RoundHlrResult> roundHlrResultList = gameStateHlrResult.getRoundHlrResultList();

        // 3. 遍歷所有場結果
        roundHlrResultList.forEach(roundHlrResult -> result.add(this.calculateFreeGameDetailPerRound(roundHlrResult)));

        // 4. 回傳
        return result;
    }

    // 計算基礎遊戲詳細記錄
    private SlotDetailSwzsFreeGame calculateFreeGameDetailPerRound(RoundHlrResult roundHlrResult) {
        // 1. 取得轉型局結果
        RoundHlrResultNormal roundHlrResultNormal = ((RoundHlrResultNormal) roundHlrResult);

        // 2. 取得第一欄結果
        String screenColumn_1 = super.calculateScreenColumn(roundHlrResultNormal.getScreenGtrResult().getScreenSymbol().get(0));

        // 3. 取得第二欄結果
        String screenColumn_2 = super.calculateScreenColumn(roundHlrResultNormal.getScreenGtrResult().getScreenSymbol().get(1));

        // 4. 取得第四欄結果
        String screenColumn_3 = super.calculateScreenColumn(roundHlrResultNormal.getScreenGtrResult().getScreenSymbol().get(2));

        // 5. 取得第四欄結果
        String screenColumn_4 = super.calculateScreenColumn(roundHlrResultNormal.getScreenGtrResult().getScreenSymbol().get(3));

        // 6. 取得第四欄結果
        String screenColumn_5 = super.calculateScreenColumn(roundHlrResultNormal.getScreenGtrResult().getScreenSymbol().get(4));

        // 7. 取得總得分
        double totalWin = roundHlrResultNormal.getTotalWin();

        // 8. 取得當前場次
        int currentRound = this.calculateCurrentRound(roundHlrResult.getProgressHlrResult());

        // 9. 取得總場次
        int totalRound = this.calculateTotalRound(roundHlrResult.getProgressHlrResult());

        // 10. 回傳
        return new SlotDetailSwzsFreeGame(
                currentRound, totalRound,
                totalWin,
                screenColumn_1, screenColumn_2, screenColumn_3, screenColumn_4, screenColumn_5);
    }

    // 計算當前場次
    private int calculateCurrentRound(ProgressHlrResult progressHlrResult) {
        RoundProgress roundProgress = ((ProgressHlrResultExtendTriggerRound)(progressHlrResult.getProgressHlrResultExtend())).getRoundProgress();

        return roundProgress.getCurrentRound();
    }

    // 計算當前場次
    private int calculateTotalRound(ProgressHlrResult progressHlrResult) {
        RoundProgress roundProgress = ((ProgressHlrResultExtendTriggerRound)(progressHlrResult.getProgressHlrResultExtend())).getRoundProgress();

        return roundProgress.getTotalRound();
    }
}
