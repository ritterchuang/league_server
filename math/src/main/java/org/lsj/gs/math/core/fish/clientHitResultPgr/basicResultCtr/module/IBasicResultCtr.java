package org.lsj.gs.math.core.fish.clientHitResultPgr.basicResultCtr.module;

import org.lsj.gs.math.core.fish.clientHitResultPgr.basicResultCtr.enity.BasicResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;

// 基礎結果運算者介面
public interface IBasicResultCtr {

    // 計算打擊魚基本結果
    BasicResult calculateBasicResult(HitResult hitResult);
}
