package org.lsj.gs.mathBoardGtr.core.fish.fishUtil.clientHitGtr;

import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.ClientHit;
import org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.client.MathFishConfig;

// 客端打擊處理者介面
public interface IClientHitGtr {

    // 計算客製打擊資訊
    ClientHit calculateClientHit(MathFishConfig mathFishConfig, int bulletIndex, int targetIndex);
}
