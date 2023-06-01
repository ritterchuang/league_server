package org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult;

import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.addSymbolCtrResultExtend.AddSymbolCtrResult;

import java.util.List;

// 客製滾輪結果 畫面
public class ReelStopResultExtendStopByScreen extends ReelStopResultExtend {
    private final List<List<Integer>> screenSymbol; // 輪軸停止畫面
    private final AddSymbolCtrResult addSymbolCtrResult; // 新增畫面標誌

    public ReelStopResultExtendStopByScreen(DampCtrResult dampCtrResult, List<List<Integer>> screenSymbol, AddSymbolCtrResult addSymbolCtrResult) {
        super(dampCtrResult);
        this.screenSymbol = screenSymbol;
        this.addSymbolCtrResult = addSymbolCtrResult;
    }

    public List<List<Integer>> getScreenSymbol() {
        return screenSymbol;
    }

    public AddSymbolCtrResult getAddSymbolCtrResult() {
        return addSymbolCtrResult;
    }
}
