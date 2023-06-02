package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr;

import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.roundCascade.ClientCascadeConfigExtendMjhl;
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
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.enity.CascadeResultExtendMjhl;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.enity.EliminateResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.module.CascadeScreenResultPgr;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.module.CascadeScreenResultPgrMjhl;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.module.EliminateResultPgr;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.ScreenResultPgr;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.ScreenResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.SymbolBox;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.specialFeatureResultClusterPgr.specialFeatureResultCluster.SpecialFeatureResultCluster;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResultExtend;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResultExtendMjhl;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.cascadeConfig.cascadeConfigExtend.CascadeConfigExtendMjhl;
import org.lsj.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;

// 消除結果包裝者 麻將胡了
public class CascadeResultPgrMjhl implements ICascadeResultPgr{
    private final CascadeResultPgrConfig config; // 消除結果包裝者設定
    private final ClientCascadeConfigExtendMjhl clientConfigExtendMjhl; // 客端消除額外設定 麻將胡了
    private final CascadeConfigExtendMjhl configExtendMjhl; // 消除額外設定 麻將胡了

    private final ScreenResultPgr screenResultPgr; // 畫面包裝者
    private final CascadeScreenResultPgr cascadeScreenResultPgrMjhl; // 消除畫面包裝者
    private final IGameResultPgr gameResultPgr; // 遊戲算分結果包裝者

    private final AccumulateWinResultPgr accumulateWinResultPgr; // 累積得分包裝者
    private final ProgressResultPgr progressResultPgr; // 遊戲進度包裝者

    private final EliminateResultPgr eliminateResultPgr; // 消除結果包裝者

    private final ITableUtil tableUtil; // 牌桌工具包
    private final ResultPgrUtil resultPgrUtil; // 結果工具包

    public CascadeResultPgrMjhl(CascadeResultPgrConfig cascadeResultPgrConfig, ProgressResultPgr progressResultPgr, AccumulateWinResultPgr accumulateWinResultPgr, ITableUtil tableUtil) {
        this.config = cascadeResultPgrConfig;
        this.clientConfigExtendMjhl = (ClientCascadeConfigExtendMjhl) cascadeResultPgrConfig.getConfig().getClientCascadeConfigExtend();
        this.configExtendMjhl = (CascadeConfigExtendMjhl) cascadeResultPgrConfig.getCascadeConfig().getCascadeConfigExtend();

        this.tableUtil = tableUtil;
        this.resultPgrUtil = new ResultPgrUtil();

        this.screenResultPgr = new ScreenResultPgr(cascadeResultPgrConfig.createClientScreenPgrConfig(), tableUtil);
        this.cascadeScreenResultPgrMjhl = new CascadeScreenResultPgrMjhl(cascadeResultPgrConfig.createCascadeScreenResultPgrConfig(), tableUtil);
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

            // 3. 計算消除結果
            EliminateResult eliminateResult = this.eliminateResultPgr.packageEliminateResult(screenResult, cascadeHlrResultList.get(cascadeIndex).getEliminateCtrResult());

            // 4. 封裝額外結果
            CascadeResultExtendMjhl cascadeResultExtendMjhl = this.packageCascadeResultExtend(cascadeHlrResultList.get(cascadeIndex).getCascadeHlrResultExtend(), screenResult, eliminateResult);

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
                    cascadeResultExtendMjhl));
        }

        // 10. 回傳
        return result;
    }

    // 計算畫面結果
    private ScreenResult calculateScreenResult(int cascadeIndex, List<CascadeHlrResult> cascadeHlrResultList, List<CascadeResult> cascadeResult) {
        if (cascadeIndex == 0) {
            return this.screenResultPgr.calculateScreenResult(cascadeHlrResultList.get(cascadeIndex).getScreenGtrResult());
        }

        return this.cascadeScreenResultPgrMjhl.calculateCascadeScreenResult(
                cascadeResult.get(cascadeIndex - 1).getScreenResult(),
                cascadeResult.get(cascadeIndex - 1).getCascadeResultExtend(),
                cascadeHlrResultList.get(cascadeIndex).getScreenGtrResult());
    }

    // 包裝消除額外資訊
    private CascadeResultExtendMjhl packageCascadeResultExtend(CascadeHlrResultExtend cascadeHlrResultExtend, ScreenResult screenResult, EliminateResult eliminateResult) {
        // 1. 轉型
        CascadeHlrResultExtendMjhl resultExtendMjhl = (CascadeHlrResultExtendMjhl) cascadeHlrResultExtend;

        // 2. 計算消除額外結果
        List<List<Integer>> positionIdList = this.resultPgrUtil.calculatePositionIdList(screenResult.getScreenSymbolBoxWithDamp());
        List<List<Integer>> beforeCascadeGoldenPosition = this.resultPgrUtil.calculateTargetPositionId(resultExtendMjhl.getBeforeCascadeGoldenPosition(), positionIdList);
        List<List<Integer>> afterCascadeGoldenPosition = this.resultPgrUtil.calculateTargetPositionId(resultExtendMjhl.getAfterCascadeGoldenPosition(), positionIdList);
        List<List<Integer>> changeToWildPosition = this.resultPgrUtil.calculateTargetPositionId(resultExtendMjhl.getChangeToWildPosition(), positionIdList);

        // 3. 修改畫面
        List<List<SymbolBox>> screenSymbolBoxAfterEliminate = this.calculateScreenSymbolBoxAfterEliminate(eliminateResult, changeToWildPosition, screenResult);

        // 3. 回傳
        return new CascadeResultExtendMjhl(0.0, beforeCascadeGoldenPosition, afterCascadeGoldenPosition, changeToWildPosition, resultExtendMjhl.getCascadeMultiplier(), screenSymbolBoxAfterEliminate);
    }


    // 計算消除後，剩餘畫面標誌
    private List<List<SymbolBox>> calculateScreenSymbolBoxAfterEliminate(EliminateResult eliminateResult, List<List<Integer>> changeToWildPosition, ScreenResult screenResult) {
        // 1. 無消除，回傳原畫面
        if (eliminateResult.getEliminatePositionIdList().isEmpty()) {
            return screenResult.getScreenSymbolBoxWithDamp();
        }

        // 2. 計算消除後剩餘畫面
        List<List<SymbolBox>> result = new ArrayList<>();
        for (int columnIndex = 0; columnIndex < screenResult.getScreenSymbolBoxWithDamp().size(); columnIndex++) {
            List<SymbolBox> symbolBoxListPerColumn = JsonUtil.getInstance().deepCopyList(screenResult.getScreenSymbolBoxWithDamp().get(columnIndex), SymbolBox.class);
            List<Integer> eliminatePositionIdListPerColumn = eliminateResult.getEliminatePositionIdList().get(columnIndex);
            List<Integer> changeWildPositionPerColumn = changeToWildPosition.get(columnIndex);

            // 2.1 將畫面修改為 Wild
            for (int targetPositionId : changeWildPositionPerColumn) {
                for (int index = 0; index < symbolBoxListPerColumn.size(); index++) {
                    if (symbolBoxListPerColumn.get(index).getId() == targetPositionId) {
                        symbolBoxListPerColumn.set(index, new SymbolBox(targetPositionId, this.config.getSymbolConfig().getTargetSymbolId(this.configExtendMjhl.getTransSymbolAttribute()), columnIndex));
                        break;
                    }
                }
            }

            // 2.2 移除滿足消除的 標誌物件
            for (Integer integer : eliminatePositionIdListPerColumn) {
                for (int index = 0; index < symbolBoxListPerColumn.size(); index++) {
                    if (symbolBoxListPerColumn.get(index).getId() == integer) {
                        symbolBoxListPerColumn.remove(index);
                        break;
                    }
                }
            }

            // 2.3 添加
            result.add(symbolBoxListPerColumn);
        }

        // 3. 回傳
        return result;
    }
}
