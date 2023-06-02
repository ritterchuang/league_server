package org.lsj.gs.math.games.hjxb_java.module.logic.slotDetailCtr;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultNormal;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfigExtendFix;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResultExtendTriggerRound;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.RoundProgress;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetail;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetailHlrConfig;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.module.AbstractSlotDetailHlr;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.module.ISlotDetailHlr;
import org.lsj.gs.math.games.hjxb_java.entity.config.SlotDetailHlrConfigExtendHjxbFreeGame;
import org.lsj.gs.math.games.hjxb_java.entity.result.roundResult.RoundHlrResultExtendHjxbFreeGame;
import org.lsj.gs.math.games.hjxb_java.entity.result.slotDetail.SlotDetailHjxbFreeGame;
import org.lsj.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

// 虎機詳細記錄計算者黃金西部
public class SlotDetailHlrHjxbFreeGame extends AbstractSlotDetailHlr implements ISlotDetailHlr {
    private final ConstMathSlot.SlotDetailType slotDetailType; // 虎機詳細記錄類型
    private final SlotDetailHlrConfigExtendHjxbFreeGame extendConfig; // 虎機詳細記錄額外設定

    public SlotDetailHlrHjxbFreeGame(SlotDetailHlrConfig slotDetailHlrConfig, ITableUtil tableUtil1) {
        super(slotDetailHlrConfig, tableUtil1);
        this.slotDetailType = slotDetailHlrConfig.getSlotDetailType();
        this.extendConfig = (SlotDetailHlrConfigExtendHjxbFreeGame) slotDetailHlrConfig.getSlotDetailHlrConfigExtend();
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
    private SlotDetailHjxbFreeGame calculateFreeGameDetailPerRound(RoundHlrResult roundHlrResult) {
        // 1. 取得轉型局結果
        RoundHlrResultNormal roundHlrResultNormal = ((RoundHlrResultNormal) roundHlrResult);

        // 2. 取得第一欄結果
        String screenColumn_1 = super.calculateScreenColumn(roundHlrResultNormal.getScreenGtrResult().getScreenSymbol().get(0));

        // 3. 取得第二欄結果
        String screenColumn_2 = super.calculateScreenColumn(roundHlrResultNormal.getScreenGtrResult().getScreenSymbol().get(1));

        // 4. 取得第三欄結果
        String screenColumn_3 = super.calculateScreenColumn(roundHlrResultNormal.getScreenGtrResult().getScreenSymbol().get(2));

        // 5. 取得第四欄結果
        String screenColumn_4 = super.calculateScreenColumn(roundHlrResultNormal.getScreenGtrResult().getScreenSymbol().get(3));

        // 6. 取得第五欄結果
        String screenColumn_5 = super.calculateScreenColumn(roundHlrResultNormal.getScreenGtrResult().getScreenSymbol().get(4));

        // 7. 計算第二欄倍數結果
        String multiplierList_2 = this.calculateMultiplierListPerColumn(roundHlrResultNormal, 1);

        // 8. 計算第三欄倍數結果
        String multiplierList_3 = this.calculateMultiplierListPerColumn(roundHlrResultNormal, 2);

        // 9. 計算第四欄倍數結果
        String multiplierList_4 = this.calculateMultiplierListPerColumn(roundHlrResultNormal, 3);

        // 10. 取得總得分
        double roundTotalWin = roundHlrResultNormal.getTotalWin();

        // 11. 取得當前場次
        int currentRound = this.calculateCurrentRound(roundHlrResult.getProgressHlrResult());

        // 12. 取得總場次
        int totalRound = this.calculateTotalRound(roundHlrResult.getProgressHlrResult());

        // 13. 回傳
        return new SlotDetailHjxbFreeGame(
                currentRound, totalRound,
                roundTotalWin,
                screenColumn_1, screenColumn_2, screenColumn_3, screenColumn_4, screenColumn_5,
                multiplierList_2, multiplierList_3, multiplierList_4);
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

    // 計算倍數列表
    private String calculateMultiplierListPerColumn(RoundHlrResultNormal roundHlrResultNormal, int columnIndex) {
        // 1. 創建空間
        StringBuilder stringBuilder = new StringBuilder();

        // 2. 無觸發特色
        if (roundHlrResultNormal.getRoundNormalGameType().equals(ConstMathSlot.RoundNormalGameType.DEFAULT)) {
            int rowPerColumn = ((FrameConfigExtendFix)super.frameConfig.getFrameConfigExtend()).getScreenRowPerColumnList().get(columnIndex);
            for (int rowIndex = 0; rowIndex < rowPerColumn; rowIndex++) {
                stringBuilder.append(0);
                if (rowIndex != rowPerColumn - 1) {
                    stringBuilder.append(StringUtil.getInstance().getCommaString());
                }
            }
            return stringBuilder.toString();
        }

        // 3. 取出額外結果
        RoundHlrResultExtendHjxbFreeGame roundHlrResultExtendHjxbFreeGame = (RoundHlrResultExtendHjxbFreeGame) roundHlrResultNormal.getRoundHlrResultExtend();
        List<Integer> multiplierListPerColumn = roundHlrResultExtendHjxbFreeGame.getMultiplierMatrix().get(columnIndex);
        for (int rowIndex = 0; rowIndex < multiplierListPerColumn.size(); rowIndex++) {
            stringBuilder.append(multiplierListPerColumn.get(rowIndex));
            if (rowIndex != multiplierListPerColumn.size() - 1) {
                stringBuilder.append(StringUtil.getInstance().getCommaString());
            }
        }
        return stringBuilder.toString();
    }
}
