package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.clientReelResult;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.enity.AddSymbolResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.SymbolBox;

import java.util.List;

// 客端滾輪結果 相依滾輪停輪物件
public class ClientReelStopResultExtendStopByScreenSymbolBox extends ClientReelStopResultExtend {
    private final List<List<SymbolBox>> reelStopScreenSymbolBoxList; // 停輪物件列表 [columnIndex][index] = symbolBox
    private final List<Double> offsetList; // 偏移量列表
    private final AddSymbolResult addSymbolResult; // 新增標誌列表

    public ClientReelStopResultExtendStopByScreenSymbolBox(List<List<SymbolBox>> reelStopScreenSymbolBoxList, List<Double> offsetList, AddSymbolResult addSymbolResult) {
        this.reelStopScreenSymbolBoxList = reelStopScreenSymbolBoxList;
        this.offsetList = offsetList;
        this.addSymbolResult = addSymbolResult;
    }

    public List<List<SymbolBox>> getReelStopScreenSymbolBoxList() {
        return reelStopScreenSymbolBoxList;
    }

    public List<Double> getOffsetList() {
        return offsetList;
    }

    public AddSymbolResult getAddSymbolResult() {
        return addSymbolResult;
    }
}
