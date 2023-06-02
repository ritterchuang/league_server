package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.module;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.enity.AddSymbolResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.ScreenResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.SymbolBox;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.utils.JsonUtil;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.List;

// 消除畫面結果包裝者
public abstract class CascadeScreenResultPgr implements ICascadeScreenResultPgr{
    private CascadeScreenResultPgrConfig config; // 消除畫面包裝者設定黨
    private ITableUtil tableUtil; // 牌桌工具包
    protected AddSymbolResultPgr addSymbolResultPgr; // 新增標誌處理者

    public CascadeScreenResultPgr(CascadeScreenResultPgrConfig config, ITableUtil tableUtil) {
        this.config = config;
        this.tableUtil = tableUtil;
        this.addSymbolResultPgr = new AddSymbolResultPgr(this.config.createAddSymbolResultPgrConfig(), tableUtil);
    }

    // 計算可視&破框畫面
    protected List<List<SymbolBox>> calculateScreenSymbolBoxWithDamp(List<List<SymbolBox>> screenSymbolBoxAfterEliminate, AddSymbolResult addSymbolResult) {
        List<List<SymbolBox>> screenSymbolBoxWithDamp = new ArrayList<>();
        for (int columnIndex = 0; columnIndex < screenSymbolBoxAfterEliminate.size(); columnIndex++) {
            screenSymbolBoxWithDamp.add(JsonUtil.getInstance().deepCopyList(screenSymbolBoxAfterEliminate.get(columnIndex), SymbolBox.class));
            List<SymbolBox> addSymbolListPerColumn = addSymbolResult.getAddSymbolBoxList().get(columnIndex);

            for (SymbolBox symbolBox : addSymbolListPerColumn) {
                screenSymbolBoxWithDamp.get(columnIndex).add(0, symbolBox);
            }
        }
        return screenSymbolBoxWithDamp;
    }

    // 計算可視區域畫面
    protected List<List<SymbolBox>> calculateScreenSymbolBoxWithScore(List<List<SymbolBox>> screenSymbolBoxWithDamp) {
        List<List<SymbolBox>> screenSymbolBox = new ArrayList<>();
        for (int columnIndex = 0; columnIndex < screenSymbolBoxWithDamp.size(); columnIndex++) {
            screenSymbolBox.add(JsonUtil.getInstance().deepCopyList(screenSymbolBoxWithDamp.get(columnIndex), SymbolBox.class));

            if (this.config.getDampConfig().getUpperDampType().equals(ConstMathSlot.DampType.ONE_DAMP)) {
                screenSymbolBox.get(columnIndex).remove(0);
            }
            if (this.config.getDampConfig().getLowerDampType().equals(ConstMathSlot.DampType.ONE_DAMP)) {
                screenSymbolBox.get(columnIndex).remove(screenSymbolBox.get(columnIndex).size() - 1);
            }
        }
        return screenSymbolBox;
    }

    // 計算偏移量
    protected List<Double> calculateOffsetList(ScreenResult lastScreenResult) {
        List<Double> result = new ArrayList<>();

        double offset = MathUtil.multiply(this.config.getClientScreenConfig().getDampZoomRatio(), this.config.getClientScreenConfig().getScreenOffsetType().getOffset());
        for (int columnIndex = 0; columnIndex < lastScreenResult.getScreenSymbolBoxWithDamp().size(); columnIndex++) {
            result.add(offset);
        }

        return result;
    }
}
