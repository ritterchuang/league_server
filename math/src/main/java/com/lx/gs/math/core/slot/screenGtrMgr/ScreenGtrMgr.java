package com.lx.gs.math.core.slot.screenGtrMgr;

import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.core.slot.ConstMathSlot;
import com.lx.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrMgrConfig;
import com.lx.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import com.lx.gs.math.core.slot.screenGtrMgr.module.ScreenGtr;

import java.util.HashMap;
import java.util.Map;

// 遊戲畫面產生器管理器
public class ScreenGtrMgr {
    private final ScreenGtrMgrConfig config; // 設定檔
    private final Map<Integer, ScreenGtr> screenGtrMap; // 畫面產生器對應表
    private final ITableUtil tableUtil; // 牌桌工具包

    public ScreenGtrMgr(ScreenGtrMgrConfig screenGtrMgrConfig, ITableUtil tableUtil) {
        this.config = screenGtrMgrConfig;
        this.tableUtil = tableUtil;
        this.screenGtrMap = this.createScreenGtrMap();
    }

    // 取得畫面生產結果
    public ScreenGtrResult getScreenGtrResult(int conditionId, ConstMathSlot.ReelRtpType reelRtpType) {
        return this.screenGtrMap.get(conditionId).generateScreenGtrResult(reelRtpType);
    }

    // 取得畫面生產結果
    public ScreenGtrResult generateScreenGtrResultBySpecifyReelId(int conditionId, int reelId) {
        return this.screenGtrMap.get(conditionId).generateScreenGtrResultBySpecifyReelId(reelId);
    }

    // 創建畫面產生器對應表
    private Map<Integer, ScreenGtr> createScreenGtrMap() {
        Map<Integer, ScreenGtr> result = new HashMap<>();

        this.config.getConditionIdToScreenGtrConfigMap().forEach(
                (key, value) -> result.put(key, new ScreenGtr(value, this.tableUtil)));

        return result;
    }
}
