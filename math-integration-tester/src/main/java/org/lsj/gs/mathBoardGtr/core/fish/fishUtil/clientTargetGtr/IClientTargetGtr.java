package org.lsj.gs.mathBoardGtr.core.fish.fishUtil.clientTargetGtr;

import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;

// 客製目標產生者介面
public interface IClientTargetGtr {

    // 依權重產生客製目標
    ClientTarget generateWeightedClientTarget();
}
