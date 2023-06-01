package org.lsj.gs.math.games.lmjjc_java.module.logic.slotDetailCtr;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultCascade;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResultExtendMjhl;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResultExtendCascade;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResultExtendRound;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.RoundProgress;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.DampCtrResult;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetail;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetailHlrConfig;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.module.AbstractSlotDetailHlr;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.module.ISlotDetailHlr;
import org.lsj.gs.math.games.lmjjc_java.entity.config.SlotDetailHlrConfigExtendLmjjcFreeGame;
import org.lsj.gs.math.games.lmjjc_java.entity.result.slotDetail.SlotDetailLmjjcCascade;
import org.lsj.gs.math.games.lmjjc_java.entity.result.slotDetail.SlotDetailLmjjcFreeGame;

import java.util.ArrayList;
import java.util.List;

// 虎機詳細記錄計算者 羅馬競技場 免費遊戲
public class SlotDetailHlrLmjjcFreeGame extends AbstractSlotDetailHlr implements ISlotDetailHlr {
    private final ConstMathSlot.SlotDetailType slotDetailType; // 虎機詳細記錄類型
    private final SlotDetailHlrConfigExtendLmjjcFreeGame extendConfig; // 虎機詳細記錄額外設定
    private final SlotDetailLmjjcUtil slotDetailLmjjcUtil; // 詳細記錄工具包

    public SlotDetailHlrLmjjcFreeGame(SlotDetailHlrConfig slotDetailHlrConfig, ITableUtil tableUtil1) {
        super(slotDetailHlrConfig, tableUtil1);
        this.slotDetailType = slotDetailHlrConfig.getSlotDetailType();
        this.extendConfig = (SlotDetailHlrConfigExtendLmjjcFreeGame) slotDetailHlrConfig.getSlotDetailHlrConfigExtend();
        this.slotDetailLmjjcUtil = new SlotDetailLmjjcUtil();
    }

    // 計算虎機詳細記錄
    @Override
    public List<SlotDetail> calculateSlotDetail(SpinRequest spinRequest, GameStateHlrResult gameStateHlrResult) {
        // 1. 宣告空間
        List<SlotDetail> result = new ArrayList<>();

        // 2. 取出局資訊
        List<RoundHlrResult> roundHlrResultList = gameStateHlrResult.getRoundHlrResultList();

        // 3. 添加結果
        for (int index = 0; index < roundHlrResultList.size(); index++) {
            // 3.1 添加局結果
            RoundHlrResultCascade roundHlrResultCascade = (RoundHlrResultCascade) roundHlrResultList.get(index);
            result.add(this.calculateDetailFreeGame(roundHlrResultCascade));

            // 3.2 添加消除結果
            List<CascadeHlrResult> cascadeHlrResultList = roundHlrResultCascade.getCascadeClusterHlrResult().getCascadeHlrResultList();
            for (int cascadeIndex = 0; cascadeIndex < cascadeHlrResultList.size(); cascadeIndex++) {
                result.add(this.calculateDetailCascade(cascadeHlrResultList.get(cascadeIndex)));
            }
        }

        // 4. 回傳
        return result;
    }

    // 計算免費遊戲詳細記錄
    private SlotDetailLmjjcFreeGame calculateDetailFreeGame(RoundHlrResultCascade roundHlrResultCascade) {
        // 1. 取得當前場次
        int currentRound = this.calculateCurrentRound(roundHlrResultCascade.getProgressHlrResult());

        // 2. 取得總場次
        int totalRound = this.calculateTotalRound(roundHlrResultCascade.getProgressHlrResult());

        // 3. 取得總得分
        double totalWin = roundHlrResultCascade.getTotalWin();

        // 4. 回傳
        return new SlotDetailLmjjcFreeGame(currentRound, totalRound, totalWin);
    }

    // 計算消除詳細記錄
    private SlotDetailLmjjcCascade calculateDetailCascade(CascadeHlrResult cascadeHlrResult) {
        // 1. 取得計算資訊
        List<List<Integer>> screenSymbol = cascadeHlrResult.getScreenGtrResult().getScreenSymbol();
        DampCtrResult dampCtrResult = cascadeHlrResult.getScreenGtrResult().getReelCtrResult().getReelStopResultExtend().getDampCtrResult();
        CascadeHlrResultExtendMjhl cascadeHlrResultExtend = (CascadeHlrResultExtendMjhl) cascadeHlrResult.getCascadeHlrResultExtend();

        // 2. 取得當前消除次數
        ProgressHlrResultExtendCascade progressHlrResultExtendCascade = (ProgressHlrResultExtendCascade) cascadeHlrResult.getProgressHlrResult().getProgressHlrResultExtend();
        int cascadeIndex = progressHlrResultExtendCascade.getCascadeProgress().getCurrentTimes();

        // 3. 取得消除倍數
        int multiplier = cascadeHlrResultExtend.getCascadeMultiplier();

        // 4. 計算消除得分
        double totalWin = cascadeHlrResult.getTotalWin();

        // 5. 取得第一欄結果
        String screenColumn_1 = super.calculateScreenColumn(screenSymbol.get(0));

        // 6. 取得第二欄結果
        String screenColumn_2 = super.calculateScreenColumn(screenSymbol.get(1));

        // 7. 取得第三欄結果
        String screenColumn_3 = super.calculateScreenColumn(screenSymbol.get(2));

        // 8. 取得第四欄結果
        String screenColumn_4 = super.calculateScreenColumn(screenSymbol.get(3));

        // 9. 取得第五欄結果
        String screenColumn_5 = super.calculateScreenColumn(screenSymbol.get(4));

        // 10. 取得第二欄黃金位置
        String goldenPosition_2 = this.slotDetailLmjjcUtil.calculateGoldenPositionListPerColumn(dampCtrResult, cascadeHlrResultExtend, 1);

        // 11. 取得第三欄黃金位置
        String goldenPosition_3 = this.slotDetailLmjjcUtil.calculateGoldenPositionListPerColumn(dampCtrResult, cascadeHlrResultExtend, 2);

        // 12. 取得第四欄黃金位置
        String goldenPosition_4 = this.slotDetailLmjjcUtil.calculateGoldenPositionListPerColumn(dampCtrResult, cascadeHlrResultExtend, 3);

        // 13. 回傳
        return new SlotDetailLmjjcCascade(
                cascadeIndex,
                multiplier,
                totalWin,
                screenColumn_1,
                screenColumn_2,
                screenColumn_3,
                screenColumn_4,
                screenColumn_5,
                goldenPosition_2,
                goldenPosition_3,
                goldenPosition_4
        );
    }

    // 計算當前場次
    private int calculateCurrentRound(ProgressHlrResult progressHlrResult) {
        RoundProgress roundProgress = ((ProgressHlrResultExtendRound)(progressHlrResult.getProgressHlrResultExtend())).getRoundProgress();

        return roundProgress.getCurrentRound();
    }

    // 計算當前場次
    private int calculateTotalRound(ProgressHlrResult progressHlrResult) {
        RoundProgress roundProgress = ((ProgressHlrResultExtendRound)(progressHlrResult.getProgressHlrResultExtend())).getRoundProgress();

        return roundProgress.getTotalRound();
    }
}