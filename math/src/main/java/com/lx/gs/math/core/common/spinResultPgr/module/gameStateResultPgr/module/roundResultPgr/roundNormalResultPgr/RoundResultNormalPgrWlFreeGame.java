package com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundNormalResultPgr;

import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultNormal;
import com.lx.gs.math.core.common.spinResultPgr.enity.pgrConfig.ClientRoundResultPgrConfig;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.accumulateWinResultPgr.AccumulateWinResult;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.gameResult.GameResult;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.progressResultPgr.enity.ProgressResult;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.readyHandResultPgr.ReadyHandResultCluster;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundResult.RoundResultExtend;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundResult.RoundResultExtendDefault;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundResult.RoundResultNormal;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.ScreenResult;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.SymbolBox;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.specialFeatureResultClusterPgr.specialFeatureResultCluster.SpecialFeatureResultCluster;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.core.slot.ConstMathSlot;
import com.lx.gs.math.games.wl_java.enity.result.roundResult.RoundHlrResultExtendWlFreeGame;
import com.lx.gs.math.games.wl_java.enity.result.roundResult.RoundResultExtendWlFreeGame;

import java.util.ArrayList;
import java.util.List;

// 客製局結果包裝者一般 旺來免費遊戲
public class RoundResultNormalPgrWlFreeGame extends AbstractRoundResultNormalPgr {

    public RoundResultNormalPgrWlFreeGame(ClientRoundResultPgrConfig config, ITableUtil tableUtil) {
        super(config, tableUtil);
    }

    // 包裝局結果
    public RoundResultNormal packageRoundResult(RoundHlrResult roundHlrResult) {
        // 1. 轉型
        RoundHlrResultNormal roundHlrResultNormal = (RoundHlrResultNormal) roundHlrResult;

        // 2. 計算畫面結果
        ScreenResult screenResult = super.packageScreenResult(roundHlrResultNormal.getScreenGtrResult());

        // 3. 計算得分結果
        GameResult gameResult = super.packageGameResult(screenResult, roundHlrResultNormal.getGameCtrResult());

        // 4. 計算特殊事件結果
        SpecialFeatureResultCluster specialFeatureResultCluster = super.packageSpecialFeatureResultCluster(screenResult, roundHlrResult.getSpecialFeatureHlrResultCluster());

        // 5. 計算聽牌結果
        ReadyHandResultCluster readyHandResultCluster = super.packageReadyHandResultCluster(roundHlrResult.getReadyHandHlrResultUnionCluster());

        // 6. 計算進度結果
        ProgressResult progressResult = super.packageProgressResult(roundHlrResult.getProgressHlrResult());

        // 7. 計算累積得分結果
        AccumulateWinResult accumulateWinResult = super.packageAccumulateWinResult(roundHlrResult.getAccumulateWinCtrResult());

        // 8. 計算額外局結果
        RoundResultExtend roundResultExtend = this.packageRoundResultExtend(screenResult, roundHlrResultNormal);

        // 9. 回傳
        return new RoundResultNormal(
                roundHlrResult.getRoundIndex(),
                roundHlrResult.getTotalWin(),
                screenResult, gameResult, specialFeatureResultCluster,
                progressResult,
                readyHandResultCluster,
                accumulateWinResult,
                roundHlrResultNormal.getRoundNormalGameType(),
                roundResultExtend);
    }

    public RoundResultExtend packageRoundResultExtend(ScreenResult screenResult, RoundHlrResultNormal roundHlrResultNormal) {
        if (roundHlrResultNormal.getRoundNormalGameType().equals(ConstMathSlot.RoundNormalGameType.DEFAULT)) {
            return new RoundResultExtendDefault();
        }

        // 1. 取得畫面倍數陣列
        List<List<Integer>> multiplierMatrix = ((RoundHlrResultExtendWlFreeGame)roundHlrResultNormal.getRoundHlrResultExtend()).getMultiplierMatrix();

        // 2. 取得畫面位置ID
        List<List<SymbolBox>> screenPositionIdList = screenResult.getScreenSymbolBox();

        // 3. 計算畫面倍數結果
        List<List<int[]>> positionIdToMultiplierList = new ArrayList<>();
        for (int columnIndex = 0; columnIndex < multiplierMatrix.size(); columnIndex++) {
            List<int[]> positionIdToMultiplierListPerColumn = new ArrayList<>();

            for (int rowIndex = 0; rowIndex < multiplierMatrix.get(columnIndex).size(); rowIndex++) {
                if (multiplierMatrix.get(columnIndex).get(rowIndex) > 0) {
                    positionIdToMultiplierListPerColumn.add(
                            new int[]{screenPositionIdList.get(columnIndex).get(rowIndex).getId(),
                                    multiplierMatrix.get(columnIndex).get(rowIndex)});
                }
            }

            positionIdToMultiplierList.add(positionIdToMultiplierListPerColumn);
        }

        // 4. 回傳
        return new RoundResultExtendWlFreeGame(positionIdToMultiplierList);
    }
}
