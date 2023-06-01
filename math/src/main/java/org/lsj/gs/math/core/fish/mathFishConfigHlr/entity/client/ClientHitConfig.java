package org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.client;

import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitCtrConfig;

import java.util.Map;

// 客端打擊設定
public class ClientHitConfig {
    Map<Integer, Map<Integer, HitCtrConfig>> hitConfigMap; // 打擊設定對應表 <子彈索引, <目標索引, 打擊設定>>

    public ClientHitConfig(Map<Integer, Map<Integer, HitCtrConfig>> hitConfigMap) {
        this.hitConfigMap = hitConfigMap;
    }

    public Map<Integer, Map<Integer, HitCtrConfig>> getHitConfigMap() {
        return hitConfigMap;
    }
}
