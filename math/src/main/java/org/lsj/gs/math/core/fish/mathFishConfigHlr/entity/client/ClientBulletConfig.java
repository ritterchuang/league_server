package org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.client;

import org.lsj.gs.math.core.fish.bulletMgr.entity.client.BulletConfigDynamic;

import java.util.Map;

// 客端子彈設定
public class ClientBulletConfig {
    private final Map<Integer, BulletConfigDynamic> bulletConfigDynamicMap; // 子彈設定對應表

    public ClientBulletConfig(Map<Integer, BulletConfigDynamic> bulletConfigDynamicMap) {
        this.bulletConfigDynamicMap = bulletConfigDynamicMap;
    }

    public Map<Integer, BulletConfigDynamic> getBulletConfigDynamicMap() {
        return bulletConfigDynamicMap;
    }
}
