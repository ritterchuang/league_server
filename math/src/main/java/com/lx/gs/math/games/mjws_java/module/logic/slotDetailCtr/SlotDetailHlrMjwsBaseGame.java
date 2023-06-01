package com.lx.gs.math.games.mjws_java.module.logic.slotDetailCtr;

import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultCascade;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.core.slot.ConstMathSlot;
import com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResultExtendMjhl;
import com.lx.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import com.lx.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResultExtendCascade;
import com.lx.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.DampCtrResult;
import com.lx.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetail;
import com.lx.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetailHlrConfig;
import com.lx.gs.math.core.slot.slotDetailHlrMgr.module.AbstractSlotDetailHlr;
import com.lx.gs.math.core.slot.slotDetailHlrMgr.module.ISlotDetailHlr;
import com.lx.gs.math.games.mjws_java.enity.config.SlotDetailHlrConfigExtendMjwsBaseGame;
import com.lx.gs.math.games.mjws_java.enity.result.slotDetail.SlotDetailMjwsBaseGame;
import com.lx.gs.math.games.mjws_java.enity.result.slotDetail.SlotDetailMjwsCascade;

import java.util.ArrayList;
import java.util.List;

// 虎機詳細記錄計算者 麻將無雙 基本遊戲
public class SlotDetailHlrMjwsBaseGame extends AbstractSlotDetailHlr implements ISlotDetailHlr {
    private final ConstMathSlot.SlotDetailType slotDetailType; // 虎機詳細記錄類型
    private final SlotDetailHlrConfigExtendMjwsBaseGame extendConfig; // 虎機詳細記錄額外設定
    private final SlotDetailMjwsUtil slotDetailMjwsUtil; // 詳細記錄工具包

    public SlotDetailHlrMjwsBaseGame(SlotDetailHlrConfig slotDetailHlrConfig, ITableUtil tableUtil1) {
        super(slotDetailHlrConfig, tableUtil1);
        this.slotDetailType = slotDetailHlrConfig.getSlotDetailType();
        this.extendConfig = (SlotDetailHlrConfigExtendMjwsBaseGame) slotDetailHlrConfig.getSlotDetailHlrConfigExtend();
        this.slotDetailMjwsUtil = new SlotDetailMjwsUtil();
    }

    // 計算虎機詳細記錄
    @Override
    public List<SlotDetail> calculateSlotDetail(SpinRequest spinRequest, GameStateHlrResult gameStateHlrResult) {
        // 1. 宣告空間
        List<SlotDetail> result = new ArrayList<>();

        // 2. 計算局資訊
        result.add(this.calculateDetailBaseGame(spinRequest, gameStateHlrResult));

        // 3. 計算消除資訊
        List<SlotDetail> slotDetailCascadeList = this.calculateDetailCascadeList(gameStateHlrResult);
        slotDetailCascadeList.forEach(cascadeDetail -> result.add(cascadeDetail));

        // 4. 回傳
        return result;
    }

    // 計算一般遊戲詳細記錄
    private SlotDetailMjwsBaseGame calculateDetailBaseGame(SpinRequest spinRequest, GameStateHlrResult gameStateHlrResult) {
        // 1. 取得轉型局結果
        RoundHlrResultCascade RoundHlrResultCascade = ((RoundHlrResultCascade) gameStateHlrResult.getRoundHlrResultList().get(0));

        // 2. 取得成本
        double spinCost = spinRequest.getPlayerCost();

        // 3. 取得總得分
        double totalWin = RoundHlrResultCascade.getTotalWin();

        // 4. 回傳
        return new SlotDetailMjwsBaseGame(spinCost, totalWin);
    }

    // 計算消除詳細記錄列表
    private List<SlotDetail> calculateDetailCascadeList(GameStateHlrResult gameStateHlrResult) {
        List<SlotDetail> result = new ArrayList<>();

        List<CascadeHlrResult> cascadeHlrResultList = ((RoundHlrResultCascade)gameStateHlrResult.getRoundHlrResultList().get(0)).getCascadeClusterHlrResult().getCascadeHlrResultList();

        cascadeHlrResultList.forEach(cascadeHlrResult -> result.add(this.calculateDetailCascade(cascadeHlrResult)));

        return result;
    }

    // 計算消除詳細記錄
    private SlotDetailMjwsCascade calculateDetailCascade(CascadeHlrResult cascadeHlrResult) {
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
        String goldenPosition_2 = this.slotDetailMjwsUtil.calculateGoldenPositionListPerColumn(dampCtrResult, cascadeHlrResultExtend, 1);

        // 11. 取得第三欄黃金位置
        String goldenPosition_3 = this.slotDetailMjwsUtil.calculateGoldenPositionListPerColumn(dampCtrResult, cascadeHlrResultExtend, 2);

        // 12. 取得第四欄黃金位置
        String goldenPosition_4 = this.slotDetailMjwsUtil.calculateGoldenPositionListPerColumn(dampCtrResult, cascadeHlrResultExtend, 3);

        // 13. 回傳
        return new SlotDetailMjwsCascade(
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
}