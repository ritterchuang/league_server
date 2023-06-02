package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.client;

// 非法子彈成本訊息
public class BulletCostExtendInvalid extends BulletCostExtend {
    // 原始建構子提供JSON解析用
    public BulletCostExtendInvalid(){}

    // 檢驗完整性
    @Override
    public boolean checkComplete() {
        return false;
    }
}
