package org.lsj.gs.math.core.fish.clientHitResultPgr;

import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.ClientHitResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;

// 客端打擊結果包裝者介面
public interface IClientHitResultPgr {

    // 包裝客端打擊結果
    ClientHitResult packageClientHitResult(HitResult hitResult);
}
