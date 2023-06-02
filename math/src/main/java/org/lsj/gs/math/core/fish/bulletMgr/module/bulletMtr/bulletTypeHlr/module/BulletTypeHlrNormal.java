package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.enity.client.BulletTypeExtend;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.enity.client.BulletTypeExtendNormal;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.enity.sever.BulletConfigExtend;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.enity.sever.BulletConfigExtendNormal;

import java.util.Objects;

// 子彈類型處理者一般
public class BulletTypeHlrNormal implements IBulletTypeHlr {
    private final ConstMathFish.BulletType bulletType; // 子彈類型
    private final BulletConfigExtendNormal config; // 子彈類型額外設定

    public BulletTypeHlrNormal(ConstMathFish.BulletType bulletType, BulletConfigExtend bulletConfigExtend) {
        this.bulletType = bulletType;
        this.config = (BulletConfigExtendNormal)bulletConfigExtend;
    }

    //* 計算相關 *//
    // 計算獎勵客製子彈資訊
    @Override
    public BulletTypeExtend calculateAwardBulletTypeExtend() {
        return new BulletTypeExtendNormal();
    }

    //* 子彈資訊相關 *//
    // 取得子彈類型
    @Override
    public ConstMathFish.BulletType getBulletType() {
        return this.bulletType;
    }

    //* 檢查相關 *//
    // 檢查子彈完整性
    @Override
    public ConstMathCommon.TableProtocolCode checkBulletCompleteness(ClientBullet clientBullet) {
        // 1. 檢驗子彈類型一致性
        if(!(clientBullet.getBulletType().equals(this.bulletType))){
            return ConstMathCommon.TableProtocolCode.FISH_BULLET_NOT_CONSISTENCY;
        }

        // 2. 檢驗客製子彈類型訊息完整性
        if(Objects.isNull(clientBullet.getBulletTypeExtend()) || !(clientBullet.getBulletTypeExtend().checkComplete())){
            return ConstMathCommon.TableProtocolCode.FISH_BULLET_NOT_COMPLETE;
        }

        // 3. 回傳正確代碼
        return ConstMathCommon.TableProtocolCode.COMMON_SUCCESS;
    }
}
