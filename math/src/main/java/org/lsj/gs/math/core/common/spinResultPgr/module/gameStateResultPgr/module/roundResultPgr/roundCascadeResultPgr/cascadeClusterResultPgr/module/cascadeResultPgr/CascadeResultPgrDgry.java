package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr;

import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.roundCascade.ClientCascadeConfigExtendDgry;
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
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.enity.CascadeResultExtendDgry;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.enity.EliminateResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.module.CascadeScreenResultPgr;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.module.CascadeScreenResultPgrDgry;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.module.EliminateResultPgr;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.ScreenResultPgr;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.ScreenResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.SymbolBox;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.specialFeatureResultClusterPgr.specialFeatureResultCluster.SpecialFeatureResultCluster;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.cascadeConfig.cascadeConfigExtend.CascadeConfigExtendDgry;
import org.lsj.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;

// 消除結果包裝者 帝国榮耀
public class CascadeResultPgrDgry implements ICascadeResultPgr{
    private final CascadeResultPgrConfig config; // 消除結果包裝者設定
    private final ClientCascadeConfigExtendDgry clientConfigExtendDgry; // 客端消除額外設定
    private final CascadeConfigExtendDgry configExtendDgry; // 消除額外設定 麻將胡了

    private final ScreenResultPgr screenResultPgr; // 畫面包裝者
    private final CascadeScreenResultPgr cascadeScreenResultPgrDgry; // 消除畫面包裝者
    private final IGameResultPgr gameResultPgr; // 遊戲算分結果包裝者

    private final AccumulateWinResultPgr accumulateWinResultPgr; // 累積得分包裝者
    private final ProgressResultPgr progressResultPgr; // 遊戲進度包裝者

    private final EliminateResultPgr eliminateResultPgr; // 消除結果包裝者

    private final ITableUtil tableUtil; // 牌桌工具包
    private final ResultPgrUtil resultPgrUtil; // 結果工具包

    public CascadeResultPgrDgry(CascadeResultPgrConfig cascadeResultPgrConfig, ProgressResultPgr progressResultPgr, AccumulateWinResultPgr accumulateWinResultPgr, ITableUtil tableUtil) {
        this.config = cascadeResultPgrConfig;
        this.clientConfigExtendDgry = (ClientCascadeConfigExtendDgry) cascadeResultPgrConfig.getConfig().getClientCascadeConfigExtend();
        this.configExtendDgry = (CascadeConfigExtendDgry) cascadeResultPgrConfig.getCascadeConfig().getCascadeConfigExtend();

        this.tableUtil = tableUtil;
        this.resultPgrUtil = new ResultPgrUtil();

        this.screenResultPgr = new ScreenResultPgr(cascadeResultPgrConfig.createClientScreenPgrConfig(), tableUtil);
        this.cascadeScreenResultPgrDgry = new CascadeScreenResultPgrDgry(cascadeResultPgrConfig.createCascadeScreenResultPgrConfig(), tableUtil);
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
            CascadeResultExtendDgry cascadeResultExtendDgry = this.packageCascadeResultExtend(screenResult, eliminateResult);

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
                    cascadeResultExtendDgry));
        }

        // 10. 回傳
        return result;
    }

    // 計算畫面結果
    private ScreenResult calculateScreenResult(int cascadeIndex, List<CascadeHlrResult> cascadeHlrResultList, List<CascadeResult> cascadeResult) {
        if (cascadeIndex == 0) {
            return this.screenResultPgr.calculateScreenResult(cascadeHlrResultList.get(cascadeIndex).getScreenGtrResult());
        }

        return this.cascadeScreenResultPgrDgry.calculateCascadeScreenResult(
                cascadeResult.get(cascadeIndex - 1).getScreenResult(),
                cascadeResult.get(cascadeIndex - 1).getCascadeResultExtend(),
                cascadeHlrResultList.get(cascadeIndex).getScreenGtrResult());
    }

    // 包裝消除額外資訊
    private CascadeResultExtendDgry packageCascadeResultExtend(ScreenResult screenResult, EliminateResult eliminateResult) {
        // 1. 修改畫面
        List<List<SymbolBox>> screenSymbolBoxAfterEliminate = this.calculateScreenSymbolBoxAfterEliminate(eliminateResult, screenResult);

        // 2. 回傳
        return new CascadeResultExtendDgry(0.0, screenSymbolBoxAfterEliminate);
    }


    // 計算消除後，剩餘畫面標誌
    private List<List<SymbolBox>> calculateScreenSymbolBoxAfterEliminate(EliminateResult eliminateResult, ScreenResult screenResult) {
        // 1. 無消除，回傳原畫面
        if (eliminateResult.getEliminatePositionIdList().isEmpty()) {
            return screenResult.getScreenSymbolBoxWithDamp();
        }

        // 2. 計算消除後剩餘畫面
        List<List<SymbolBox>> result = new ArrayList<>();
        for (int columnIndex = 0; columnIndex < screenResult.getScreenSymbolBoxWithDamp().size(); columnIndex++) {
            List<SymbolBox> symbolBoxListPerColumn = JsonUtil.getInstance().deepCopyList(screenResult.getScreenSymbolBoxWithDamp().get(columnIndex), SymbolBox.class);
            List<Integer> eliminatePositionIdListPerColumn = eliminateResult.getEliminatePositionIdList().get(columnIndex);

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
