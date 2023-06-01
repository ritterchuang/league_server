package com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.module;

import com.lx.gs.math.core.common.spinResultPgr.ConstPgrSlot;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.enity.AddSymbolResult;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.enity.CascadeResultExtend;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.enity.CascadeResultExtendMjhl;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.ScreenResult;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.SymbolBox;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.clientReelResult.ClientReelResult;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.clientReelResult.ClientReelStopResultExtendStopByScreenSymbolBox;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import com.lx.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.ReelStopResultExtendStopByScreen;

import java.util.List;

// 消除畫面結果包裝者
public class CascadeScreenResultPgrMjhl extends CascadeScreenResultPgr{

    public CascadeScreenResultPgrMjhl(CascadeScreenResultPgrConfig config, ITableUtil tableUtil) {
        super(config, tableUtil);
    }

    @Override
    public ScreenResult calculateCascadeScreenResult(ScreenResult lastScreenResult, CascadeResultExtend lastCascadeResultExtend, ScreenGtrResult currentScreenGtrResult) {
        // 1. 轉型上次消除結果
        CascadeResultExtendMjhl cascadeResultExtendMjhl = (CascadeResultExtendMjhl) lastCascadeResultExtend;

        // 2. 計算新增標誌結果
        AddSymbolResult addSymbolResult = this.addSymbolResultPgr.packageAddSymbolResult(lastScreenResult.getScreenSymbolBoxWithDamp(), ((ReelStopResultExtendStopByScreen)currentScreenGtrResult.getReelCtrResult().getReelStopResultExtend()).getAddSymbolCtrResult());

        // 3. 計算偏移量
        List<Double> offSetList = this.calculateOffsetList(lastScreenResult);

        // 4. 計算此次消除前畫面
        List<List<SymbolBox>> screenSymbolBoxWithDamp = this.calculateScreenSymbolBoxWithDamp(cascadeResultExtendMjhl.getScreenSymbolBoxAfterEliminate(), addSymbolResult);

        // 5. 計算可視區域畫面
        List<List<SymbolBox>> screenSymbolBox = this.calculateScreenSymbolBoxWithScore(screenSymbolBoxWithDamp);

        // 6. 回傳
        return new ScreenResult(
                new ClientReelResult(currentScreenGtrResult.getReelCtrResult().getReelId(),
                        ConstPgrSlot.ClientReelStopType.STOP_BY_SCREEN_SYMBOL_BOX,
                        new ClientReelStopResultExtendStopByScreenSymbolBox(screenSymbolBoxWithDamp, offSetList, addSymbolResult)),
                lastScreenResult.getFrameResult(),
                screenSymbolBoxWithDamp,
                screenSymbolBox);
    }
}
