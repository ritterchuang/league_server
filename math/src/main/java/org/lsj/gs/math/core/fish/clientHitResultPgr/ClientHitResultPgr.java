package org.lsj.gs.math.core.fish.clientHitResultPgr;

import org.lsj.gs.math.core.fish.clientHitResultPgr.basicResultCtr.enity.BasicResult;
import org.lsj.gs.math.core.fish.clientHitResultPgr.basicResultCtr.module.IBasicResultCtr;
import org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.enity.DisplayResultCollection;
import org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.module.IDisplayResultCollectionCtr;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.ClientHitResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;

// 客端打擊結果包裝者
public class ClientHitResultPgr implements IClientHitResultPgr {
    private final IBasicResultCtr basicResultCtr; // 基礎結果計算者
    private final IDisplayResultCollectionCtr displayResultCollectionCtr; // 客端表演資訊計算者

    public ClientHitResultPgr(IBasicResultCtr basicResultCtr, IDisplayResultCollectionCtr displayResultCollectionCtr) {
        this.basicResultCtr = basicResultCtr;
        this.displayResultCollectionCtr = displayResultCollectionCtr;
    }

    // 包裝客端打擊結果
    @Override
    public ClientHitResult packageClientHitResult(HitResult hitResult) {
        // 1. 計算基礎結果
        BasicResult basicResult = this.basicResultCtr.calculateBasicResult(hitResult);

        // 2. 計算客製結果
        DisplayResultCollection displayResultCollection = this.displayResultCollectionCtr.calculateDisplayResultCollection(hitResult);

        // 3. 封裝
        return new ClientHitResult(
                basicResult.isKillFlag(),
                basicResult.getKillCount(),
                basicResult.getBasicWin(),
                hitResult.getTotalWin(),
                hitResult.getTotalOdds(),
                hitResult.getAwardBulletType(),
                hitResult.getAwardBulletExtend(),
                displayResultCollection);
    }

}
