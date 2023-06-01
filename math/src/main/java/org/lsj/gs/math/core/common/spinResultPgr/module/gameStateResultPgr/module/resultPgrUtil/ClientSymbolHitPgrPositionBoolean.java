package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.resultPgrUtil;

import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientSymbolHitConfig.ClientSymbolHitConfig;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.resultPgrUtil.symbolHitResult.SymbolHitResultExtend;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.resultPgrUtil.symbolHitResult.SymbolHitResultExtendPositionBoolean;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.ScreenResult;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.List;

// 客端畫面打擊處理者 畫面 boolean 位置
public class ClientSymbolHitPgrPositionBoolean implements IClientSymbolHitPgr {
    private final ClientSymbolHitConfig config; // 客端打擊設定
    private final ITableUtil tableUtil; // 牌桌工具包

    public ClientSymbolHitPgrPositionBoolean(ClientSymbolHitConfig config, ITableUtil tableUtil) {
        this.config = config;
        this.tableUtil = tableUtil;
    }

    @Override
    public SymbolHitResultExtend calculateSymbolHitResultExtend(ScreenResult screenResult, List<List<Boolean>> targetPositionList) {
        return new SymbolHitResultExtendPositionBoolean(targetPositionList);
    }
}
