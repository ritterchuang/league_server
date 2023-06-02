package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.module;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.enity.AddSymbolResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.SymbolBox;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.addSymbolCtrResultExtend.AddSymbolCtrResult;

import java.util.ArrayList;
import java.util.List;

// 新增標誌結果包裝者
public class AddSymbolResultPgr {
    private final AddSymbolResultPgrConfig config; // 新增標誌包裝者設定
    private final ITableUtil tableUtil; // 牌桌工具包

    public AddSymbolResultPgr(AddSymbolResultPgrConfig config, ITableUtil tableUtil) {
        this.config = config;
        this.tableUtil = tableUtil;
    }

    // TODO 支援其他
    // 包裝新增標誌結果
    public AddSymbolResult packageAddSymbolResult(List<List<SymbolBox>> lastScreenSymbolBoxList, AddSymbolCtrResult addSymbolCtrResult) {
        if (addSymbolCtrResult.getAddSymbolList().isEmpty()) {
            return new AddSymbolResult();
        }

        List<Integer> nextPositionIdList = new ArrayList<>();
        for (List<SymbolBox> symbolBoxes : lastScreenSymbolBoxList) {
            int maxPositionId = symbolBoxes.stream().mapToInt(SymbolBox::getId).max().getAsInt();
            nextPositionIdList.add(maxPositionId + 1);
        }

        //TODO 依照 config 倒續
        List<List<SymbolBox>> addSymbolBoxList = new ArrayList<>();
        for (int columnIndex = 0; columnIndex < addSymbolCtrResult.getAddSymbolList().size(); columnIndex++) {
            List<SymbolBox> addSymbolListPerColumn = new ArrayList<>();
            for (int addIndex = 0; addIndex < addSymbolCtrResult.getAddSymbolList().get(columnIndex).size(); addIndex++) {
                int addSymbolId = addSymbolCtrResult.getAddSymbolList().get(columnIndex).
                        get(addSymbolCtrResult.getAddSymbolList().get(columnIndex).size() - 1 - addIndex);
                int nextPositionId = nextPositionIdList.get(columnIndex);

                addSymbolListPerColumn.add(new SymbolBox(nextPositionId + addIndex, addSymbolId, columnIndex));
            }

            addSymbolBoxList.add(addSymbolListPerColumn);
        }

        return new AddSymbolResult(addSymbolBoxList);
    }
}
