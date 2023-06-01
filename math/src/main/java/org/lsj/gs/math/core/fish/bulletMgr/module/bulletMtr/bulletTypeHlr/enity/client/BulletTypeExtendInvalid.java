package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.enity.client;

// 非法客製子彈訊息
public class BulletTypeExtendInvalid extends BulletTypeExtend{

    // 檢驗完整性
    @Override
    public boolean checkComplete() {
        return false;
    }
}
