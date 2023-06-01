package org.lsj.gs.math.core.slot.slotDetailHlrMgr.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfig;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetailHlrConfig;
import org.lsj.utils.StringUtil;

import java.util.List;
import java.util.Map;

// 虎機詳細記錄計算者貔貅開運
public abstract class AbstractSlotDetailHlr implements ISlotDetailHlr {
    protected final Map<Integer, String> symbolIdToStringMap; // <標誌識別碼, 標誌名稱> 對應表
    protected final FrameConfig frameConfig; // 畫面設定
    protected final ReelConfig reelConfig; // 輪帶表設定
    protected final ITableUtil tableUtil; // 牌桌工具包

    public AbstractSlotDetailHlr(SlotDetailHlrConfig slotDetailHlrConfig, ITableUtil tableUtil) {
        this.symbolIdToStringMap = slotDetailHlrConfig.getSlotDetailHlrConfigExtend().getSymbolIdToStringMap();
        this.frameConfig = slotDetailHlrConfig.getSlotDetailHlrConfigExtend().getFrameConfig();
        this.reelConfig = slotDetailHlrConfig.getSlotDetailHlrConfigExtend().getReelConfig();
        this.tableUtil = tableUtil;
    }

    // 計算特定欄位畫面
    public String calculateScreenColumn(List<Integer> screenSymbolPerColumn) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int rowIndex = 0; rowIndex < screenSymbolPerColumn.size(); rowIndex++) {
            stringBuilder.append(this.symbolIdToStringMap.get(screenSymbolPerColumn.get(rowIndex)));
            if (rowIndex != screenSymbolPerColumn.size() - 1) {
                stringBuilder.append(StringUtil.getInstance().getCommaString());
            }
        }

        return stringBuilder.toString();
    }
}
