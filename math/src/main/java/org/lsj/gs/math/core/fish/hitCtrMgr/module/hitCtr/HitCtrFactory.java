package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr;

import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitCtrConfig;

// 打擊計算器工廠
public class HitCtrFactory {

    // 創建打擊結果計算者
    public IHitCtr createHitCtr(HitCtrConfig hitCtrConfig, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil){
        switch(hitCtrConfig.getHitType()){
            case FIXED_ODDS: return new HitCtrFixedOdds(hitCtrConfig, gamePlayerHlr, poolCtr, tableUtil);
            case RANDOM_ODDS: return new HitCtrRandomOdds(hitCtrConfig, gamePlayerHlr, poolCtr, tableUtil);
            case MULTI_TARGET: return new HitCtrMultiTarget(hitCtrConfig, gamePlayerHlr, poolCtr, tableUtil);
            case WHEEL: return new HitCtrWheel(hitCtrConfig, gamePlayerHlr, poolCtr, tableUtil);
            case RED_ENVELOPE: return new HitCtrRedEnvelope(hitCtrConfig, gamePlayerHlr, poolCtr, tableUtil);
            case DRAGON_TREASURE: return new HitCtrDragonTreasure(hitCtrConfig, gamePlayerHlr, poolCtr, tableUtil);
            case DOUBLE_WHEEL: return new HitCtrDoubleWheel(hitCtrConfig, gamePlayerHlr, poolCtr, tableUtil);
            case TREASURE_BOX: return new HitCtrTreasureBox(hitCtrConfig, gamePlayerHlr, poolCtr, tableUtil);
            case REEL: return new HitCtrReel(hitCtrConfig, gamePlayerHlr, poolCtr, tableUtil);
            case TRIPLE_WHEEL: return new HitCtrTripleWheel(hitCtrConfig, gamePlayerHlr, poolCtr, tableUtil);
            default: return new HitCtrInvalid(hitCtrConfig, gamePlayerHlr, poolCtr, tableUtil);
        }
    }

}
