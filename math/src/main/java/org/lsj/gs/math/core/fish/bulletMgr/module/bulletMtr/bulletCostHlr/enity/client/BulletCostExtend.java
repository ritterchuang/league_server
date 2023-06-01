package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.client;

// 客製子彈成本訊息
public abstract class BulletCostExtend implements IBulletCostExtend{
    // 原始建構子提供JSON解析用
    public BulletCostExtend() {}

    // 抽象檢驗完整性
    @Override
    public abstract boolean checkComplete();
}
