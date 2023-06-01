package com.lx.gs.math.core.fish.clientHitResultPgr;

import com.lx.gs.math.core.fish.hitCtrMgr.entity.client.ClientHitResult;
import com.lx.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;

// 客端打擊結果包裝者介面
public interface IClientHitResultPgr {

    // 包裝客端打擊結果
    ClientHitResult packageClientHitResult(HitResult hitResult);
}
