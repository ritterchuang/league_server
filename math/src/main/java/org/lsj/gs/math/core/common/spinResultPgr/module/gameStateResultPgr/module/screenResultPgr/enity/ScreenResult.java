package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.clientReelResult.ClientReelResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResult;

import java.util.List;

// 客端畫面結果
public class ScreenResult {
    private final ClientReelResult reelResult; // 輪帶表結果
    @JsonIgnore
    private final FrameResult frameResult; // 畫面結果
    @JsonIgnore
    private final List<List<SymbolBox>> screenSymbolBoxWithDamp; // 含破框畫面

    private final List<List<SymbolBox>> screenSymbolBox; // 可視範圍畫面

    public ScreenResult(ClientReelResult reelResult, FrameResult frameResult, List<List<SymbolBox>> screenSymbolBoxWithDamp, List<List<SymbolBox>> screenSymbolBox) {
        this.reelResult = reelResult;
        this.frameResult = frameResult;
        this.screenSymbolBoxWithDamp = screenSymbolBoxWithDamp;
        this.screenSymbolBox = screenSymbolBox;
    }

    public ClientReelResult getReelResult() {
        return reelResult;
    }

    public FrameResult getFrameResult() {
        return frameResult;
    }

    public List<List<SymbolBox>> getScreenSymbolBoxWithDamp() {
        return screenSymbolBoxWithDamp;
    }

    public List<List<SymbolBox>> getScreenSymbolBox() {
        return screenSymbolBox;
    }
}
