package org.lsj.gs.mathStr.core.module.stn.agencyStn;

import org.lsj.gs.mathStr.config.entity.AgencyStnConfig;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrReturnResult;
import org.lsj.gs.mathStr.core.module.stn.TemplateStn;

// 總體統計者
public class StnTotal extends AbstractStn {
    private final TemplateStn templateStn; // 基礎統計物件

    public StnTotal(AgencyStnConfig config) {
        super(config);
        this.templateStn = new TemplateStn();
    }

    // 更新統計資訊
    @Override
    public void update(BoardGtrResult boardGtrResult) {
        this.templateStn.update(boardGtrResult);
    }

    // 更新返還資訊
    @Override
    public void updateReturnResult(BoardGtrReturnResult boardGtrReturnResult) {
        // 更新統計
        this.templateStn.updateReturnResult(boardGtrReturnResult);
    }

    // 印出統計資訊
    @Override
    public void print() {
        System.out.println("[總體資訊]");
        this.templateStn.print();
    }

    // 取得當前公司利潤率
    public double getCurrentCompanyProfitRate(){
        return this.templateStn.getTotalScore() / this.templateStn.getTotalBet() * -1.0;
    }
}
