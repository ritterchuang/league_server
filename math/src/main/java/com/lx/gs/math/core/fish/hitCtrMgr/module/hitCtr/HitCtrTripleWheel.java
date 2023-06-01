package com.lx.gs.math.core.fish.hitCtrMgr.module.hitCtr;

import com.lx.gs.math.core.common.ConstMathCommon;
import com.lx.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import com.lx.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.core.fish.ConstMathFish;
import com.lx.gs.math.core.fish.bulletMgr.BulletMgr;
import com.lx.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import com.lx.gs.math.core.fish.hitCtrMgr.entity.client.ClientHit;
import com.lx.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import com.lx.gs.math.core.fish.hitCtrMgr.entity.client.hitTypeExtend.HitTypeExtend;
import com.lx.gs.math.core.fish.hitCtrMgr.entity.server.HitCtrConfig;
import com.lx.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;
import com.lx.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendTripleWheel;
import com.lx.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.module.HitCtrUtilTripleWheel;

// 雙重輪盤目標打擊計算器
public class HitCtrTripleWheel extends AbstractHitCtr implements IHitCtr {
    private final ConstMathFish.HitType hitType; // 打擊類型
    private final HitTypeConfigExtendTripleWheel config; // 客製打擊計算器設定
    private final HitCtrUtilTripleWheel hitCtrUtil; // 打擊計算工具包

    public HitCtrTripleWheel(HitCtrConfig hitCtrConfig, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(hitCtrConfig, gamePlayerHlr, poolCtr, tableUtil);
        this.hitType = hitCtrConfig.getHitType();
        this.config = (HitTypeConfigExtendTripleWheel) hitCtrConfig.getHitTypeConfigExtend();
        this.hitCtrUtil = new HitCtrUtilTripleWheel(this.config, tableUtil);
    }

    // 取得真人打擊結果
    @Override
    public HitResult calculateHitResult(Bullet bullet, ClientTarget target, ClientHit hit, BulletMgr bulletMgr, ConstMathCommon.ShuffleType shuffleType, GameAdjustParameter gameAdjustParameter) {
        return super.calculateHitResult(bullet, target, hit, 1, bulletMgr, this.hitCtrUtil, shuffleType, gameAdjustParameter);
    }

    // 檢驗完整性
    @Override
    public boolean checkComplete(HitTypeExtend hitTypeExtend) {
        return true;
    }
}
