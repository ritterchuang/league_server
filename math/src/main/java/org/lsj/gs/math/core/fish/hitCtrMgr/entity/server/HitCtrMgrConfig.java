package org.lsj.gs.math.core.fish.hitCtrMgr.entity.server;

import java.util.Map;

// 打擊計算器管理者的設定
public class HitCtrMgrConfig {
    private final Map<Integer, Map<Integer, HitCtrConfig>> hitCtrConfigMap; // 打擊計算器對應表 <子彈索引, <目標索引, 打擊設定>>

    public HitCtrMgrConfig(Map<Integer, Map<Integer, HitCtrConfig>> hitCtrConfigMap) {
        this.hitCtrConfigMap = hitCtrConfigMap;
    }

    public Map<Integer, Map<Integer, HitCtrConfig>> getHitCtrConfigMap() {
        return hitCtrConfigMap;
    }
}
