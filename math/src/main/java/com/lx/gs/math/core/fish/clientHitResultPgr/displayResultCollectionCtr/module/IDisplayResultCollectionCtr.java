package com.lx.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.module;

import com.lx.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.enity.DisplayResultCollection;
import com.lx.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;

// 客端表演資訊計算者介面
public interface IDisplayResultCollectionCtr {

    // 計算客端表演資訊
    DisplayResultCollection calculateDisplayResultCollection(HitResult hitResult);
}
