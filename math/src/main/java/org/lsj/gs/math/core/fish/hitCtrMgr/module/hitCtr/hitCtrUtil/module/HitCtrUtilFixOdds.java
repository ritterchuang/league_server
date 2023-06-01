package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.hitTypeExtend.HitTypeExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendFixedOdds;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendFixedOdds;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.HitOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.hitOddsInfoExtend.HitOddsInfoExtend;

// 固定倍數打擊計算器工具包
public class HitCtrUtilFixOdds implements IHitCtrUtil{
    private final HitTypeConfigExtendFixedOdds config; // 客製打擊計算器設定
    private final ITableUtil tableUtil; // 牌桌工具包

    public HitCtrUtilFixOdds(HitTypeConfigExtendFixedOdds hitTypeConfigExtendFixedOdds, ITableUtil tableUtil) {
        this.config = hitTypeConfigExtendFixedOdds;
        this.tableUtil = tableUtil;
    }


    // 計算固定倍數打擊倍數資訊
    @Override
    public HitOddsInfo calculateHitOddsInfo(HitTypeExtend hitTypeExtend, ClientTarget target) {
        // 1. 計算目標倍數
        int basicOdds = this.config.getOdds();

        // 2. 回傳
        return new HitOddsInfo(basicOdds, new HitOddsInfoExtend());
    }

    // 計算固定倍數客製打擊結果
    @Override
    public HitResultExtendFixedOdds calculateHitResultExtend(boolean killFlag, HitOddsInfo hitOddsInfo, HitTypeExtend hitTypeExtend, Bullet bullet) {
        return new HitResultExtendFixedOdds();
    }

}
