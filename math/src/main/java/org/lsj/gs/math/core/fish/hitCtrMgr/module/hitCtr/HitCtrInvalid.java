package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.ClientHit;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.hitTypeExtend.HitTypeExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitCtrConfig;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;

// 非法打擊計算器
public class HitCtrInvalid extends AbstractHitCtr implements IHitCtr {
    private final ConstMathFish.HitType hitType; // 打擊類型

    public HitCtrInvalid(HitCtrConfig hitCtrConfig, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(hitCtrConfig, gamePlayerHlr, poolCtr, tableUtil);
        this.hitType = hitCtrConfig.getHitType();
    }

    //* 真人打擊結果相關 *//
    // 取得真人打擊結果
    @Override
    public HitResult calculateHitResult(Bullet bullet, ClientTarget target, ClientHit hit, BulletMgr bulletMgr, ConstMathCommon.ShuffleType shuffleType, GameAdjustParameter gameAdjustParameter) {
        return new HitResult();
    }


    //* 檢驗相關 *//
    // 檢驗完整性
    @Override
    public boolean checkComplete(HitTypeExtend hitTypeExtend) {
        return false;
    }
}
