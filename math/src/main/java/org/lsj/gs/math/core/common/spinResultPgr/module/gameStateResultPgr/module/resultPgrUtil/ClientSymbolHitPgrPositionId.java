package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.resultPgrUtil;

import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientSymbolHitConfig.ClientSymbolHitConfig;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.resultPgrUtil.symbolHitResult.SymbolHitResultExtend;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.resultPgrUtil.symbolHitResult.SymbolHitResultExtendPositionId;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.ScreenResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.SymbolBox;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.ArrayList;
import java.util.List;

// 客端畫面打擊處理者 輪帶表位置ID
public class ClientSymbolHitPgrPositionId implements IClientSymbolHitPgr {
    private final ClientSymbolHitConfig config; // 客端打擊設定
    private final ResultPgrUtil resultPgrUtil; // 結果工具包
    private final ITableUtil tableUtil; // 牌桌工具包

    public ClientSymbolHitPgrPositionId(ClientSymbolHitConfig config, ITableUtil tableUtil) {
        this.config = config;
        this.resultPgrUtil = new ResultPgrUtil();
        this.tableUtil = tableUtil;
    }


    // 計算畫面打擊額外結果
    @Override
    public SymbolHitResultExtend calculateSymbolHitResultExtend(ScreenResult screenResult, List<List<Boolean>> targetPositionList) {
        return new SymbolHitResultExtendPositionId(this.resultPgrUtil.calculateTargetPositionId(targetPositionList, this.calculatePositionIdList(screenResult.getScreenSymbolBox())));
    }

    // 計算畫面標誌 位置ID 列表
    private List<List<Integer>> calculatePositionIdList(List<List<SymbolBox>> screenSymbolBoxList) {
        List<List<Integer>> result = new ArrayList<>();

        for (List<SymbolBox> symbolBoxes : screenSymbolBoxList) {
            List<Integer> resultPerColumn = new ArrayList<>();
            for (SymbolBox symbolBox : symbolBoxes) {
                resultPerColumn.add(symbolBox.getId());

            }

            result.add(resultPerColumn);
        }

        return result;
    }
}
