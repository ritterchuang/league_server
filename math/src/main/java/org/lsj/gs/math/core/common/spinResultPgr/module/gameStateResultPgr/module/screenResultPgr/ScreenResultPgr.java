package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr;

import org.lsj.gs.math.core.common.spinResultPgr.ConstPgrSlot;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.ClientScreenPgrConfig;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.ScreenResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.StopBox;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.SymbolBox;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.clientReelResult.ClientReelResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.clientReelResult.ClientReelStopResultExtendStopByDependentReelBox;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.clientReelResult.ClientReelStopResultExtendStopByDependentReelIndex;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfigExtendFix;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfigExtendReelDependent;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.ReelCtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.ReelStopResultExtendStopByDependentReelIndex;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 畫面包裝者
public class ScreenResultPgr {
    private final ClientScreenPgrConfig config; // 客端畫面包裝設定
    private final ITableUtil tableUtil; // 牌桌工具包

    public ScreenResultPgr(ClientScreenPgrConfig config, ITableUtil tableUtil) {
        this.config = config;
        this.tableUtil = tableUtil;
    }

    // 計算畫面結果
    public ScreenResult calculateScreenResult(ScreenGtrResult screenGtrResult) {
        // 1. 計算客製輪帶表結果 TODO 待作掉落
        ClientReelResult clientReelResult = this.calculateClientReelResult(screenGtrResult.getReelCtrResult());

        // 2. 取得畫面結果
        FrameResult frameResult = screenGtrResult.getFrameResult();

        // 3. 計算含破框畫面
        List<List<SymbolBox>> screenSymbolBoxListWithDamp = this.calculateScreenSymbolBoxListWithDamp(screenGtrResult);

        // 4. 計算算分範圍畫面
        List<List<SymbolBox>> screenSymbolBoxListWithScore = this.calculateScreenSymbolBoxListWithScore(screenGtrResult);

        // 5. 包裝回傳
        return new ScreenResult(clientReelResult, frameResult, screenSymbolBoxListWithDamp, screenSymbolBoxListWithScore);
    }


    //* 計算 畫面標誌物件列表 *//

    // 計算含破框畫面
    private List<List<SymbolBox>> calculateScreenSymbolBoxListWithDamp(ScreenGtrResult screenGtrResult) {
        if (screenGtrResult.getReelCtrResult().getReelStopType().equals(ConstMathSlot.ReelStopType.STOP_BY_DEPENDENT_REEL_INDEX)) {
            return this.calculateSymbolBoxListWithDampInReelDependentStopIndexAndFixFrame(screenGtrResult); // TODO 之後有其他畫面類型?
        }

        return Collections.emptyList(); // TODO 其餘類型
    }

    //計算算分範圍畫面
    private List<List<SymbolBox>> calculateScreenSymbolBoxListWithScore(ScreenGtrResult screenGtrResult) {
        if (screenGtrResult.getReelCtrResult().getReelStopType().equals(ConstMathSlot.ReelStopType.STOP_BY_DEPENDENT_REEL_INDEX)) {
            return this.calculateSymbolBoxListWithScoreInReelDependentStopIndexAndFixFrame(screenGtrResult); // TODO 之後有其他畫面類型?
        }

        return Collections.emptyList(); // TODO 其餘類型
    }

    // 計算含破框畫面 相依滾輪和固定畫面
    private List<List<SymbolBox>> calculateSymbolBoxListWithDampInReelDependentStopIndexAndFixFrame(ScreenGtrResult screenGtrResult) {
        // 1. 取出參數
        List<List<Integer>> screenWithDamp = screenGtrResult.calculateScreenSymbolWithDamp();
        List<Integer> stopIndexList = ((ReelStopResultExtendStopByDependentReelIndex)screenGtrResult.getReelCtrResult().getReelStopResultExtend()).getReelStopIndexList();
        List<Integer> screenRowList = screenGtrResult.getFrameResult().getFrameResultExtend().getScreenRowList();

        // 2. 依序計算停輪位置
        List<List<Integer>> positionIdList = this.calculateScreenPositionIdListWithDampScreen(screenGtrResult, stopIndexList, screenRowList);

        // 3. 回傳 畫面資訊
        return this.calculateSimpleScreenSymbolBoxList(screenWithDamp, positionIdList);
    }

    // 計算算分畫面 相依滾輪和固定畫面
    private List<List<SymbolBox>> calculateSymbolBoxListWithScoreInReelDependentStopIndexAndFixFrame(ScreenGtrResult screenGtrResult) {
        // 1. 取得計算參數
        List<List<Integer>> screen = screenGtrResult.getScreenSymbol();
        List<Integer> stopIndexList = ((ReelStopResultExtendStopByDependentReelIndex)screenGtrResult.getReelCtrResult().getReelStopResultExtend()).getReelStopIndexList();
        List<Integer> screenRowList = screenGtrResult.getFrameResult().getFrameResultExtend().getScreenRowList();

        // 2. 計算可視區域位置ID列表
        List<List<Integer>> positionIdList = this.calculateScreenPositionIdListWithScoreScreen(screenGtrResult, stopIndexList, screenRowList);

        // 3. 回傳 畫面資訊
        return this.calculateSimpleScreenSymbolBoxList(screen, positionIdList);
    }

    // 計算含破框 畫面標誌位置ID
    private List<List<Integer>> calculateScreenPositionIdListWithDampScreen(ScreenGtrResult screenGtrResult, List<Integer> stopIndexList, List<Integer> screenRowList) {
        // 1. 計算畫面含破框
        List<List<Integer>> screenWithDamp = screenGtrResult.calculateScreenSymbolWithDamp();

        // 2. 由上往下添加座位 Id
        List<List<Integer>> positionIdList = new ArrayList<>();
        for (int columnIndex = 0; columnIndex < screenWithDamp.size(); columnIndex++) {
            int currentStopIndex = stopIndexList.get(columnIndex);
            int stripLength = ((ReelConfigExtendReelDependent)this.config.getReelConfig().getReelStripBoxList().get(screenGtrResult.getReelCtrResult().getReelId()).getReelConfigExtend()).getReelStripList().get(columnIndex).size();

            // 2.1 添加上破框
            List<Integer> positionIdListPerColumn = new ArrayList<>();
            for (int upperDampIndex = 0; upperDampIndex < this.config.getDampConfig().getUpperDampType().getDampAmount(); upperDampIndex++) {
                int offsetAmount = this.config.getDampConfig().getUpperDampType().getDampAmount() - upperDampIndex;
                int positionId = (currentStopIndex - offsetAmount + stripLength) % stripLength;
                positionIdListPerColumn.add(positionId);
            }

            // 2.2 添加算分區
            for (int rowIndex = 0; rowIndex < screenRowList.get(columnIndex); rowIndex++) {
                int positionId = (currentStopIndex + rowIndex + stripLength) % stripLength;
                positionIdListPerColumn.add(positionId);
            }

            // 2.3 添加下破框
            for (int lowerDampIndex = 0; lowerDampIndex < this.config.getDampConfig().getLowerDampType().getDampAmount(); lowerDampIndex++) {
                int positionId = (currentStopIndex + screenRowList.get(columnIndex) + lowerDampIndex + stripLength) % stripLength;
                positionIdListPerColumn.add(positionId);
            }

            positionIdList.add(positionIdListPerColumn);
        }

        // 3. 回傳
        return positionIdList;
    }

    // 計算算分 畫面標誌位置ID
    private List<List<Integer>> calculateScreenPositionIdListWithScoreScreen(ScreenGtrResult screenGtrResult, List<Integer> stopIndexList, List<Integer> screenRowList) {
        // 1. 計算畫面含破框
        List<List<Integer>> screenWithDamp = screenGtrResult.calculateScreenSymbolWithDamp();

        // 2. 由上往下添加座位 Id
        List<List<Integer>> positionIdList = new ArrayList<>();
        for (int columnIndex = 0; columnIndex < screenWithDamp.size(); columnIndex++) {
            int currentStopIndex = stopIndexList.get(columnIndex);
            int stripLength = ((ReelConfigExtendReelDependent)this.config.getReelConfig().getReelStripBoxList().get(screenGtrResult.getReelCtrResult().getReelId()).getReelConfigExtend()).getReelStripList().get(columnIndex).size();

            List<Integer> positionIdListPerColumn = new ArrayList<>();

            for (int rowIndex = 0; rowIndex < screenRowList.get(columnIndex); rowIndex++) {
                int positionId = (currentStopIndex + rowIndex + stripLength) % stripLength;
                positionIdListPerColumn.add(positionId);
            }

            positionIdList.add(positionIdListPerColumn);
        }

        // 3. 回傳
        return positionIdList;
    }

    // 計算畫面 簡易 symbolBox 列表 [不支援 stack symbol]
    private List<List<SymbolBox>> calculateSimpleScreenSymbolBoxList(List<List<Integer>> screenSymbolList, List<List<Integer>> positionIdList) {
        List<List<SymbolBox>> result = new ArrayList<>();

        for (int columnIndex = 0; columnIndex < screenSymbolList.size(); columnIndex++) {

            List<SymbolBox> resultPerColumn = new ArrayList<>();

            for (int index = 0; index < screenSymbolList.get(columnIndex).size(); index++) {
                int positionId = positionIdList.get(columnIndex).get(index);
                int symbolId = screenSymbolList.get(columnIndex).get(index);
                resultPerColumn.add(new SymbolBox(positionId, symbolId, columnIndex));
            }

            result.add(resultPerColumn);
        }

        return result;
    }


    //* 計算 客端滾輪結果 *//

    // 計算客端滾輪結果
    private ClientReelResult calculateClientReelResult(ReelCtrResult reelCtrResult) {
        if (reelCtrResult.getReelStopType().equals(ConstMathSlot.ReelStopType.STOP_BY_DEPENDENT_REEL_INDEX)) {
            return this.calculateClientReelResultStopByIndex(reelCtrResult);
        }

        return this.calculateClientReelResultStopByScreen();
    }

    // 計算客端滾輪結果 相依滾輪 固定畫面
    private ClientReelResult calculateClientReelResultStopByIndex(ReelCtrResult reelCtrResult) {
        // 1. 宣告空間
        List<Integer> clientStopIndexList = new ArrayList<>();

        // 2. 取得數值停輪位置
        List<Integer> systemStopIndexList = ((ReelStopResultExtendStopByDependentReelIndex) reelCtrResult.getReelStopResultExtend()).getReelStopIndexList();

        // 3. 取得破框格數
        int dampAmount = this.config.getDampConfig().getLowerDampType().getDampAmount();

        // 4. 計算客製化停輪位置
        for (int columnIndex = 0; columnIndex < systemStopIndexList.size(); columnIndex++) {
            clientStopIndexList.add(this.calculateClientStopIndexFixFrame(reelCtrResult.getReelId(), columnIndex, systemStopIndexList.get(columnIndex), dampAmount));
        }

        // 5. 回傳
        switch (this.config.getClientScreenConfig().getScreenOffsetType()) {
            case OFFSET_NONE: return new ClientReelResult(reelCtrResult.getReelId(), ConstPgrSlot.ClientReelStopType.STOP_BY_DEPENDENT_REEL_INDEX, new ClientReelStopResultExtendStopByDependentReelIndex(clientStopIndexList));
            default: return new ClientReelResult(reelCtrResult.getReelId(), ConstPgrSlot.ClientReelStopType.STOP_BY_DEPENDENT_REEL_BOX, new ClientReelStopResultExtendStopByDependentReelBox(this.calculateStopBoxFixFrame(clientStopIndexList)));
        }
    }

    // 計算客製停輪位置
    private int calculateClientStopIndexFixFrame(int reelStripId, int columnIndex, int systemStopIndex, int dampAmount) {
        // 1. 取得對應欄 輪帶表
        List<Integer> specifyColumnStrip = ((ReelConfigExtendReelDependent)this.config.getReelConfig().getReelStripBoxList().get(reelStripId).getReelConfigExtend())
                .getReelStripList().get(columnIndex);

        // 2. 計算偏移量 TODO frameResult 視固定職，抽到富類別
        int shiftAmount = ((FrameConfigExtendFix)this.config.getFrameConfig().getFrameConfigExtend()).getScreenRowPerColumnList().get(columnIndex) - 1 + dampAmount;

        // 3. 回傳
        return (systemStopIndex + shiftAmount) % specifyColumnStrip.size();
    }

    // 計算客製停輪位置
    private List<StopBox> calculateStopBoxFixFrame(List<Integer> clientStopIndexList) {
        List<StopBox> result = new ArrayList<>();

        clientStopIndexList.forEach(x -> result.add(new StopBox(x, MathUtil.multiply(this.config.getClientScreenConfig().getScreenOffsetType().getOffset(), this.config.getClientScreenConfig().getDampZoomRatio()))));

        return result;
    }

    // TODO 之後有第一場掉落再做
    private ClientReelResult calculateClientReelResultStopByScreen() {
        return null;
    }
}
