package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletAmountHlr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import org.lsj.gs.math.core.fish.bulletMgr.module.awardBulletHlr.module.AwardBulletHlr;

// 子彈數量處理者介面
public interface IBulletAmountHlr {
    // 檢查子彈存在性
    ConstMathCommon.TableProtocolCode checkBulletExistence(ClientBullet clientBullet, AwardBulletHlr awardBulletHlr);
}
