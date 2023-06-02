package org.lsj.gs.math.core.fish.bulletMgr.entity.server;

import org.lsj.gs.math.core.fish.bulletMgr.entity.client.BulletConfigBase;

import java.util.Map;

// 子彈管理器設定
public class BulletMgrConfig {
    private final Map<Integer, BulletConfigBase> bulletConfigMap; // 子彈設定對應表 <子彈索引, 子彈設定>
    private final double base; // 底注

    public BulletMgrConfig(Map<Integer, BulletConfigBase> bulletConfigMap, double base) {
        this.bulletConfigMap = bulletConfigMap;
        this.base = base;
    }

    public Map<Integer, BulletConfigBase> getBulletConfigMap() {
        return bulletConfigMap;
    }

    public double getBase() {
        return base;
    }
}
