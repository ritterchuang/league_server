package org.lsj.gs.math.core.slot.commonInputHlr.module;

import org.lsj.gs.math.core.common.gameFlowHlr.enity.result.GameFlowHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.commonInputHlr.ICommonInputHlr;
import org.lsj.gs.math.core.slot.commonInputHlr.enity.config.CommonInputConfigExtendNone;
import org.lsj.gs.math.core.slot.commonInputHlr.enity.config.CommonInputHlrConfig;
import org.lsj.gs.math.core.slot.commonInputHlr.enity.result.CommonInputResult;
import org.lsj.gs.math.core.slot.commonInputHlr.enity.result.CommonInputResultExtendNone;

import java.util.List;

// 公用輸入處理者None
public class CommonInputHlrNone implements ICommonInputHlr {
    private final ConstMathSlot.CommonInputType commonInputType; // 通用輸入類型
    private final CommonInputConfigExtendNone configExtend; // 通用輸入額外設定
    private final ITableUtil tableUtil; // 牌桌工具包

    public CommonInputHlrNone(CommonInputHlrConfig commonInputHlrConfig, ITableUtil tableUtil) {
        this.commonInputType = commonInputHlrConfig.getCommonInputType();
        this.configExtend = (CommonInputConfigExtendNone) commonInputHlrConfig.getCommonInputConfigExtend();
        this.tableUtil = tableUtil;
    }

    // 計算通用輸入 TODO 全都算完一起算
    @Override
    public CommonInputResult calculateCommonInput(List<RoundHlrResult> roundHlrResultList) {
        return new CommonInputResult(this.commonInputType, new CommonInputResultExtendNone());
    }

    // 應用通用輸入 TODO
    @Override
    public void apply(GameFlowHlrResult gameFlowHlrResult) {
    }
}
