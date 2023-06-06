package org.lsj.gs.math.core.common.cmdOutResultPgr;

import org.lsj.gs.math.core.common.cmdOutResultPgr.entity.CmdOutResult;
import org.lsj.gs.math.core.common.cmdOutResultPgr.entity.cmdOutFgSpin.CmdOutFgSpinDefault;
import org.lsj.gs.math.core.common.cmdOutResultPgr.entity.cmdOutJpSpin.CmdOutJpSpinDefault;
import org.lsj.gs.math.core.common.cmdOutResultPgr.entity.cmdOutNgSpin.CmdOutNgSpinDefault;
import org.lsj.gs.math.core.common.cmdOutResultPgr.entity.cmdOutNgSpin.CmdOutNgSpinLucky777;
import org.lsj.gs.math.core.common.gameFlowHlr.enity.result.GameFlowHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultNormal;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.ReelCtrReelDependent;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.DampCtrResult;

import java.util.ArrayList;
import java.util.List;

// CmdOut結果封裝器
public class CmdOutResultPgr {

    // 封裝 CmdOut結果 TODO byGame處理器
    public CmdOutResult packageCmdOutResult(GameFlowHlrResult gameFlowHlrResult) {
        return new CmdOutResult(
                new CmdOutNgSpinLucky777(
                        this.calculateReelSymbolArray(gameFlowHlrResult),
                        gameFlowHlrResult.getTotalWin(),
                        new int[]{},
                        2
                ),
                new CmdOutFgSpinDefault(),
                new CmdOutJpSpinDefault()
        );
    }

    private int[] calculateReelSymbolArray(GameFlowHlrResult gameFlowHlrResult){
        List<Integer> resultList = new ArrayList<>();

        // 1. 取得 上破框
        List<Integer> upperDampList = new ArrayList<>();
        upperDampList.add(((RoundHlrResultNormal)(gameFlowHlrResult.getGameStateHlrResultList().get(0).getRoundHlrResultList().get(0)))
                .getScreenGtrResult().getReelCtrResult().getReelStopResultExtend().getDampCtrResult().getUpperDampSymbolIdList().get(0).get(0));
        upperDampList.add(((RoundHlrResultNormal)(gameFlowHlrResult.getGameStateHlrResultList().get(0).getRoundHlrResultList().get(0)))
                .getScreenGtrResult().getReelCtrResult().getReelStopResultExtend().getDampCtrResult().getUpperDampSymbolIdList().get(1).get(0));
        upperDampList.add(((RoundHlrResultNormal)(gameFlowHlrResult.getGameStateHlrResultList().get(0).getRoundHlrResultList().get(0)))
                .getScreenGtrResult().getReelCtrResult().getReelStopResultExtend().getDampCtrResult().getUpperDampSymbolIdList().get(2).get(0));


        // 2. 取得 主圖標
        List<Integer> mainSymbolList = new ArrayList<>();
        mainSymbolList.add(((RoundHlrResultNormal)(gameFlowHlrResult.getGameStateHlrResultList().get(0).getRoundHlrResultList().get(0)))
                .getScreenGtrResult().getScreenSymbol().get(0).get(0));
        mainSymbolList.add(((RoundHlrResultNormal)(gameFlowHlrResult.getGameStateHlrResultList().get(0).getRoundHlrResultList().get(0)))
                .getScreenGtrResult().getScreenSymbol().get(1).get(0));
        mainSymbolList.add(((RoundHlrResultNormal)(gameFlowHlrResult.getGameStateHlrResultList().get(0).getRoundHlrResultList().get(0)))
                .getScreenGtrResult().getScreenSymbol().get(2).get(0));

        // 3. 取得 下破框
        List<Integer> lowerDampList = new ArrayList<>();
        lowerDampList.add(((RoundHlrResultNormal)(gameFlowHlrResult.getGameStateHlrResultList().get(0).getRoundHlrResultList().get(0)))
                .getScreenGtrResult().getReelCtrResult().getReelStopResultExtend().getDampCtrResult().getLowerDampSymbolIdList().get(0).get(0));
        lowerDampList.add(((RoundHlrResultNormal)(gameFlowHlrResult.getGameStateHlrResultList().get(0).getRoundHlrResultList().get(0)))
                .getScreenGtrResult().getReelCtrResult().getReelStopResultExtend().getDampCtrResult().getLowerDampSymbolIdList().get(1).get(0));
        lowerDampList.add(((RoundHlrResultNormal)(gameFlowHlrResult.getGameStateHlrResultList().get(0).getRoundHlrResultList().get(0)))
                .getScreenGtrResult().getReelCtrResult().getReelStopResultExtend().getDampCtrResult().getLowerDampSymbolIdList().get(2).get(0));

        // 4. 加入結果列表
        resultList.addAll(upperDampList);
        resultList.addAll(mainSymbolList);
        resultList.addAll(lowerDampList);

        // 5. 回傳陣列型態
        return resultList.stream().mapToInt(i -> i).toArray();
    }
}
