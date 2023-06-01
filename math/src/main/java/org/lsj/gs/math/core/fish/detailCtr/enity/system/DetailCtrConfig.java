package org.lsj.gs.math.core.fish.detailCtr.enity.system;

import java.util.Map;

// 詳細記錄計算者設定
public class DetailCtrConfig {
    private final Map<Integer, String> bulletIndexNameMap; // <子彈代碼, 子彈名稱> 對應表
    private final Map<Integer, String> targetIndexNameMap; // <目標代碼, 目標名稱> 對應表

    public DetailCtrConfig(Map<Integer, String> bulletIndexNameMap, Map<Integer, String> targetIndexNameMap) {
        this.bulletIndexNameMap = bulletIndexNameMap;
        this.targetIndexNameMap = targetIndexNameMap;
    }

    public Map<Integer, String> getBulletIndexNameMap() {
        return bulletIndexNameMap;
    }

    public Map<Integer, String> getTargetIndexNameMap() {
        return targetIndexNameMap;
    }
}
