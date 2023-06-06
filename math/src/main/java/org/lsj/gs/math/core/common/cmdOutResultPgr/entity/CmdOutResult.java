package org.lsj.gs.math.core.common.cmdOutResultPgr.entity;

import org.lsj.gs.math.core.common.cmdOutResultPgr.entity.cmdOutJpSpin.AbstractCmdOutJpSpin;
import org.lsj.gs.math.core.common.cmdOutResultPgr.entity.cmdOutNgSpin.AbstractCmdOutNgSpin;
import org.lsj.gs.math.core.common.cmdOutResultPgr.entity.cmdOutFgSpin.AbstractCmdOutFgSpin;

// Cmd結果
public class CmdOutResult {
    private AbstractCmdOutNgSpin ngSpin; // 一般遊戲結果
    private AbstractCmdOutFgSpin fgSpin; // 免費遊戲結果
    private AbstractCmdOutJpSpin jpSpin; // 獎勵遊戲結果

    public CmdOutResult(AbstractCmdOutNgSpin ngSpin, AbstractCmdOutFgSpin fgSpin, AbstractCmdOutJpSpin jpSpin) {
        this.ngSpin = ngSpin;
        this.fgSpin = fgSpin;
        this.jpSpin = jpSpin;
    }

    public AbstractCmdOutNgSpin getNgSpin() {
        return ngSpin;
    }

    public AbstractCmdOutFgSpin getFgSpin() {
        return fgSpin;
    }

    public AbstractCmdOutJpSpin getJpSpin() {
        return jpSpin;
    }
}
