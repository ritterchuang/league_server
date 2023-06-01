package org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.module;

import org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.enity.DisplayResultCollection;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;

// 客端表演資訊計算者介面
public interface IDisplayResultCollectionCtr {

    // 計算客端表演資訊
    DisplayResultCollection calculateDisplayResultCollection(HitResult hitResult);
}
