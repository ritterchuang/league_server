package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr;

import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.roundCascade.ClientCascadeConfigExtendCjwp;
import org.lsj.gs.math.core.common.spinResultPgr.enity.pgrConfig.CascadeResultPgrConfig;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.accumulateWinResultPgr.AccumulateWinResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.accumulateWinResultPgr.AccumulateWinResultPgr;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.GameResultPgrFactory;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.IGameResultPgr;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.gameResult.GameResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.progressResultPgr.ProgressResultPgr;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.progressResultPgr.enity.ProgressResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.readyHandResultPgr.ReadyHandResultCluster;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.resultPgrUtil.ResultPgrUtil;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.enity.CascadeResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.enity.CascadeResultExtendCjwp;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.enity.EliminateResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.module.CascadeScreenResultPgr;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.module.CascadeScreenResultPgrCjwp;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.module.EliminateResultPgr;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.ScreenResultPgr;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.ScreenResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.SymbolBox;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.specialFeatureResultClusterPgr.specialFeatureResultCluster.SpecialFeatureResultCluster;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResultExtendCjwp;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.cascadeConfig.cascadeConfigExtend.CascadeConfigExtendCjwp;
import org.lsj.utils.JsonUtil;
import org.lsj.utils.ListUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 消除結果包裝者 超級王牌
public class CascadeResultPgrCjwp implements ICascadeResultPgr{
    private final CascadeResultPgrConfig config; // 消除結果包裝者設定
    private final ClientCascadeConfigExtendCjwp clientCascadeConfigExtendCjwp; // 客端消除額外設定 超級王牌
    private final CascadeConfigExtendCjwp cascadeConfigExtendCjwp; // 消除額外設定 超級王牌

    private final ScreenResultPgr screenResultPgr; // 畫面包裝者
    private final CascadeScreenResultPgr cascadeScreenResultPgrCjwp; // 消除畫面包裝者
    private final IGameResultPgr gameResultPgr; // 遊戲算分結果包裝者

    private final AccumulateWinResultPgr accumulateWinResultPgr; // 累積得分包裝者
    private final ProgressResultPgr progressResultPgr; // 遊戲進度包裝者

    private final EliminateResultPgr eliminateResultPgr; // 消除結果包裝者

    private final ITableUtil tableUtil; // 牌桌工具包
    private final ResultPgrUtil resultPgrUtil; // 結果工具包

    public CascadeResultPgrCjwp(CascadeResultPgrConfig cascadeResultPgrConfig, ProgressResultPgr progressResultPgr, AccumulateWinResultPgr accumulateWinResultPgr, ITableUtil tableUtil) {
        this.config = cascadeResultPgrConfig;
        this.clientCascadeConfigExtendCjwp = (ClientCascadeConfigExtendCjwp) cascadeResultPgrConfig.getConfig().getClientCascadeConfigExtend();
        this.cascadeConfigExtendCjwp = (CascadeConfigExtendCjwp) cascadeResultPgrConfig.getCascadeConfig().getCascadeConfigExtend();

        this.tableUtil = tableUtil;
        this.resultPgrUtil = new ResultPgrUtil();

        this.screenResultPgr = new ScreenResultPgr(cascadeResultPgrConfig.createClientScreenPgrConfig(), tableUtil);
        this.cascadeScreenResultPgrCjwp = new CascadeScreenResultPgrCjwp(cascadeResultPgrConfig.createCascadeScreenResultPgrConfig(), tableUtil);
        this.gameResultPgr = new GameResultPgrFactory().create(
                cascadeResultPgrConfig.createGameResultPgrConfig(),
                tableUtil);

        this.progressResultPgr = progressResultPgr;
        this.accumulateWinResultPgr = accumulateWinResultPgr;

        this.eliminateResultPgr = new EliminateResultPgr(resultPgrUtil, tableUtil);
    }

    // 包裝消除結果列表
    public List<CascadeResult> packageCascadeResultList(List<CascadeHlrResult> cascadeHlrResultList) {
        // 1. 創建空間
        List<CascadeResult> result = new ArrayList<>();

        for (int cascadeIndex = 0; cascadeIndex < cascadeHlrResultList.size(); cascadeIndex++) {
            // 2. 計算畫面結果
            ScreenResult screenResult = this.calculateScreenResult(cascadeIndex, cascadeHlrResultList, result);

            ScreenResult lastRoundScreenResult = screenResult;
            // 3. 計算上一局畫面結果
            if(cascadeIndex > 0) {
                lastRoundScreenResult = this.calculateScreenResult(cascadeIndex-1, cascadeHlrResultList, result);
            }

            // 3. 計算消除結果
            EliminateResult eliminateResult = this.eliminateResultPgr.packageEliminateResult(screenResult, cascadeHlrResultList.get(cascadeIndex).getEliminateCtrResult());

            // 4. 封裝額外結果
            CascadeResultExtendCjwp cascadeResultExtendCjwp = this.packageCascadeResultExtend(cascadeHlrResultList, cascadeIndex, screenResult, eliminateResult, lastRoundScreenResult);

            // 5. 計算進度結果
            ProgressResult progressResult = this.progressResultPgr.packageProgressResult(cascadeHlrResultList.get(cascadeIndex).getProgressHlrResult());

            // 6. 計算累積得分
            AccumulateWinResult accumulateWinResult = this.accumulateWinResultPgr.packageAccumulateWinResult(cascadeHlrResultList.get(cascadeIndex).getAccumulateWinCtrResult());

            // 7. 包裝得分結果
            GameResult gameResult = this.gameResultPgr.packageGameResult(screenResult, cascadeHlrResultList.get(cascadeIndex).getGameCtrResult());

            // 8. 計算總得分
            double totalWin = gameResult.getTotalWin();

            // 9. 包裝
            result.add(new CascadeResult(
                    totalWin,
                    screenResult,
                    gameResult,
                    new SpecialFeatureResultCluster(),
                    accumulateWinResult,
                    progressResult,
                    new ReadyHandResultCluster(),
                    eliminateResult,
                    this.config.getConfig().getCascadeType(),
                    cascadeResultExtendCjwp));
        }

        // 10. 回傳
        return result;
    }

    // 計算畫面結果
    private ScreenResult calculateScreenResult(int cascadeIndex, List<CascadeHlrResult> cascadeHlrResultList, List<CascadeResult> cascadeResult) {
        if (cascadeIndex == 0) {
            return this.screenResultPgr.calculateScreenResult(cascadeHlrResultList.get(cascadeIndex).getScreenGtrResult());
        }

        return this.cascadeScreenResultPgrCjwp.calculateCascadeScreenResult(
                cascadeResult.get(cascadeIndex - 1).getScreenResult(),
                cascadeResult.get(cascadeIndex - 1).getCascadeResultExtend(),
                cascadeHlrResultList.get(cascadeIndex).getScreenGtrResult());
    }

    // 包裝消除額外資訊
    private CascadeResultExtendCjwp packageCascadeResultExtend(List<CascadeHlrResult> cascadeHlrResultList, int cascadeIndex, ScreenResult screenResult, EliminateResult eliminateResult, ScreenResult lastRoundScreenResult) {
        // 1. 轉型
        CascadeHlrResultExtendCjwp resultExtendCjwp = (CascadeHlrResultExtendCjwp) cascadeHlrResultList.get(cascadeIndex).getCascadeHlrResultExtend();

        // 2. 計算消除額外結果
        List<List<Integer>> positionIdList = this.resultPgrUtil.calculatePositionIdList(screenResult.getScreenSymbolBoxWithDamp());
        List<List<Integer>> LastRoundPositionIdList = this.resultPgrUtil.calculatePositionIdList(lastRoundScreenResult.getScreenSymbolBoxWithDamp());


        List<List<Integer>> beforeCascadeGoldenPosition = this.resultPgrUtil.calculateTargetPositionId(resultExtendCjwp.getBeforeCascadeGoldenPosition(), positionIdList);
        List<List<Integer>> afterCascadeGoldenPosition = this.resultPgrUtil.calculateTargetPositionId(resultExtendCjwp.getAfterCascadeGoldenPosition(), positionIdList);
        List<List<Integer>> changeToBigWildPosition = this.resultPgrUtil.calculateTargetPositionId(resultExtendCjwp.getChangeToBigWildPosition(), positionIdList);
        List<List<Integer>> changeToSmallWildPosition = this.resultPgrUtil.calculateTargetPositionId(resultExtendCjwp.getChangeToSmallWildPosition(), positionIdList);

        // 3. 計算 Random Wild 結果
        Map<Integer, Map<Integer, List<List<Integer>>>> bigWildTransformPosition = new HashMap<>();
        if(cascadeIndex > 0) {
            bigWildTransformPosition = this.calculateTargetTransformPositionId(resultExtendCjwp.getBigWildTransformPosition(), ((CascadeHlrResultExtendCjwp)cascadeHlrResultList.get(cascadeIndex - 1).getCascadeHlrResultExtend()).getChangeToBigWildPosition(), positionIdList, LastRoundPositionIdList);
        }

        // 4. 修改畫面
        List<List<SymbolBox>> screenSymbolBoxAfterEliminate = this.calculateScreenSymbolBoxAfterEliminate(eliminateResult, changeToBigWildPosition, changeToSmallWildPosition, screenResult);

        // 5. 回傳
        return new CascadeResultExtendCjwp(0.0, beforeCascadeGoldenPosition, afterCascadeGoldenPosition, changeToBigWildPosition, changeToSmallWildPosition, bigWildTransformPosition, resultExtendCjwp.getCascadeMultiplier(), screenSymbolBoxAfterEliminate);
    }


    // 計算消除後，剩餘畫面標誌
    private List<List<SymbolBox>> calculateScreenSymbolBoxAfterEliminate(EliminateResult eliminateResult, List<List<Integer>> changeToBigWildPosition, List<List<Integer>> changeToSmallWildPosition, ScreenResult screenResult) {
        // 1. 無消除，回傳原畫面
        if (eliminateResult.getEliminatePositionIdList().isEmpty()) {
            return screenResult.getScreenSymbolBoxWithDamp();
        }

        // 2. 計算消除後剩餘畫面
        List<List<SymbolBox>> result = new ArrayList<>();
        for (int columnIndex = 0; columnIndex < screenResult.getScreenSymbolBoxWithDamp().size(); columnIndex++) {
            List<SymbolBox> symbolBoxListPerColumn = JsonUtil.getInstance().deepCopyList(screenResult.getScreenSymbolBoxWithDamp().get(columnIndex), SymbolBox.class);
            List<Integer> eliminatePositionIdListPerColumn = eliminateResult.getEliminatePositionIdList().get(columnIndex);
            List<Integer> changeToBigWildPositionPerColumn = changeToBigWildPosition.get(columnIndex);
            List<Integer> changeToSmallWildPositionPerColumn = changeToSmallWildPosition.get(columnIndex);

            // 2.1 將畫面修改為 Big Wild
            for (int targetPositionId : changeToBigWildPositionPerColumn) {
                for (int index = 0; index < symbolBoxListPerColumn.size(); index++) {
                    if (symbolBoxListPerColumn.get(index).getId() == targetPositionId) {
                        symbolBoxListPerColumn.set(index, new SymbolBox(targetPositionId, this.config.getSymbolConfig().getTargetSymbolId(this.cascadeConfigExtendCjwp.getTransSymbolAttribute()[0]), columnIndex));
                        break;
                    }
                }
            }

            // 2.2 將畫面修改為 Small Wild
            for (int targetPositionId : changeToSmallWildPositionPerColumn) {
                for (int index = 0; index < symbolBoxListPerColumn.size(); index++) {
                    if (symbolBoxListPerColumn.get(index).getId() == targetPositionId) {
                        symbolBoxListPerColumn.set(index, new SymbolBox(targetPositionId, this.config.getSymbolConfig().getTargetSymbolId(this.cascadeConfigExtendCjwp.getTransSymbolAttribute()[1]), columnIndex));
                        break;
                    }
                }
            }

            // 2.3 移除滿足消除的 標誌物件
            for (Integer integer : eliminatePositionIdListPerColumn) {
                for (int index = 0; index < symbolBoxListPerColumn.size(); index++) {
                    if (symbolBoxListPerColumn.get(index).getId() == integer) {
                        symbolBoxListPerColumn.remove(index);
                        break;
                    }
                }
            }

            // 2.4 添加
            result.add(symbolBoxListPerColumn);
        }

        // 3. 回傳
        return result;
    }

    // 計算 RandomWild 的 PositionID
    public Map<Integer, Map<Integer, List<List<Integer>>>> calculateTargetTransformPositionId(Map<Integer, Map<Integer, List<List<Boolean>>>> getBigWildTransformPosition, List<List<Boolean>> changeToBigWildPosition, List<List<Integer>> positionIdList, List<List<Integer>> lastRoundPositionIdList) {
        Map<Integer, Map<Integer, List<List<Integer>>>> result = new HashMap<>();

        for (int columnIndex = 0; columnIndex < changeToBigWildPosition.size(); columnIndex++) {

            Map<Integer, List<List<Integer>>> resultPerColumn = new HashMap<>();
            for (int rowIndex = 0; rowIndex < changeToBigWildPosition.get(columnIndex).size(); rowIndex++) {
                // 1. 檢查此位置確實有 BigWild 且觸發 RandomWild
                if(getBigWildTransformPosition.containsKey(columnIndex) && getBigWildTransformPosition.get(columnIndex).containsKey(rowIndex)) {

                    // 2. 計算該 BigWild 的 PositionID
                    // 2.1 建立 false 2D 陣列
                    List<List<Boolean>> bigWildPosition = ListUtil.create2DimensionBooleanList(getBigWildTransformPosition.get(columnIndex).get(rowIndex),false);
                    // 2.2 將 BigWild 位置設為 true
                    bigWildPosition.get(columnIndex).set(rowIndex, true);
                    // 2.3 換算成 PositionID
                    int bigWildPositionID = this.resultPgrUtil.calculateTargetPositionId(bigWildPosition, lastRoundPositionIdList).get(columnIndex).get(0);

                    // 3. 換算 Random Wild 位置成 PositionID, key 用 BigWild 的 PositionID
                    resultPerColumn.put(bigWildPositionID, this.resultPgrUtil.calculateTargetPositionId(getBigWildTransformPosition.get(columnIndex).get(rowIndex), positionIdList));
                }
            }
            result.put(columnIndex, resultPerColumn);
        }
        return result;
    }
}
