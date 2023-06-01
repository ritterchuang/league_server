package org.lsj.gs.math.core.fish.detailCtr.module;

import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.detailCtr.enity.client.*;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;

import java.util.Optional;

// 詳細記錄計算者介面
public interface IDetailCtr {

    //* 結果相關詳細記錄 *//
    // 計算目標詳細記錄
    Optional<DetailFish> calculateDetailFish(Bullet bullet, ClientTarget target, HitResult hitResult);

    // 計算特殊事件詳細記錄
    Optional<DetailSpecialFeature> calculateDetailSpecialFeature(Bullet bullet, HitResult hitResult);

    //* 子彈相關詳細記錄 *//
    // 計算子彈打擊詳細記錄
    Optional<DetailBulletHit> calculateDetailBulletHit(Bullet bullet);

    // 計算獎勵子彈詳細記錄
    Optional<DetailAwardBullet> calculateDetailAwardBullet(Bullet bullet, HitResult hitResult);

    // 計算返還詳細記錄
    Optional<DetailFishReturn> calculateDetailFishReturn(BulletMgr bulletMgr);
}
