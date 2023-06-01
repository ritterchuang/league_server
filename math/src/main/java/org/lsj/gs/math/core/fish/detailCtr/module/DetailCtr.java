package org.lsj.gs.math.core.fish.detailCtr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.detailCtr.enity.client.*;
import org.lsj.gs.math.core.fish.detailCtr.enity.system.DetailCtrConfig;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.client.awardBullet.AwardBulletExtendOneType;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureResultExtend.SpecialFeatureResultExtendRedEnvelope;
import org.lsj.utils.MathUtil;

import java.util.Optional;

// 詳細記錄計算者
public class DetailCtr implements IDetailCtr{
    private final DetailCtrConfig config; // 詳細記錄計算者設定

    public DetailCtr(DetailCtrConfig config) {
        this.config = config;
    }

    //* 結果相關詳細記錄 *//
    // 計算目標詳細記錄
    @Override
    public Optional<DetailFish> calculateDetailFish(Bullet bullet, ClientTarget target, HitResult hitResult) {
        // 1. 未擊殺，回傳空
        if (!hitResult.isKillFlag()) {
            return Optional.empty();
        }

        // 2. 包裝擊殺資訊
        return Optional.of(new DetailFish(
                                    this.getTargetKind(target.getTargetIndex()),
                                    MathUtil.divide(hitResult.getBasicWin(), bullet.getBet()),
                                    hitResult.getKillCount(),
                                    this.getBulletKind(bullet.getBulletIndex()),
                                    bullet.getBet(),
                                    bullet.getCost()
        ));
    }

    // 計算特殊事件詳細記錄
    @Override
    public Optional<DetailSpecialFeature> calculateDetailSpecialFeature(Bullet bullet, HitResult hitResult) {
        // 1. 紅包設定
        if (hitResult.getSpecialFeatureEnumType().equals(ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE)) {
            SpecialFeatureResultExtendRedEnvelope specialFeatureResultExtend = (SpecialFeatureResultExtendRedEnvelope) hitResult.getSpecialFeatureResultExtend();
            // 2. 無觸發，不包裝
            if (!specialFeatureResultExtend.isKillFlag()) {
                return Optional.empty();
            }

            // 3. 包裝特殊事件詳細記錄
            return Optional.of(new DetailSpecialFeature(
                    ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE.getName(),
                    specialFeatureResultExtend.getKillCount(),
                    MathUtil.divide(specialFeatureResultExtend.getBasicWin(), bullet.getBet()),
                    this.getBulletKind(bullet.getBulletIndex()),
                    bullet.getBet(),
                    bullet.getCost()
            ));
        }

        // 4. 防呆回傳
        return Optional.empty();
    }


    //* 子彈相關詳細記錄 *//
    // 計算子彈打擊詳細記錄
    @Override
    public Optional<DetailBulletHit> calculateDetailBulletHit(Bullet bullet) {
        return Optional.of(
                new DetailBulletHit(
                        this.getBulletKind(bullet.getBulletIndex()),
                        1,
                        bullet.getBet(),
                        bullet.getCost()));
    }

    // 計算獎勵子彈詳細記錄
    @Override
    public Optional<DetailAwardBullet> calculateDetailAwardBullet(Bullet bullet, HitResult hitResult) {
        // 1. 單一類型獎勵子彈設定
        if (hitResult.getAwardBulletType().equals(ConstMathFish.AwardBulletType.ONE_TYPE)) {
            AwardBulletExtendOneType awardBulletExtendOneType = (AwardBulletExtendOneType) hitResult.getAwardBulletExtend();
            // 2. 無獲得獎勵子彈，不包裝
            if (awardBulletExtendOneType.getAmount() <= 0) {
                return Optional.empty();
            }

            // 3. 包裝獎勵子彈詳細記錄
            return Optional.of(new DetailAwardBullet(
                    this.getBulletKind(awardBulletExtendOneType.getObtainedBullet().getBulletIndex()),
                    awardBulletExtendOneType.getAmount(),
                    bullet.getBet()
            ));
        }

        // 4. 防呆回傳
        return Optional.empty();
    }

    // 計算返還詳細記錄
    @Override
    public Optional<DetailFishReturn> calculateDetailFishReturn(BulletMgr bulletMgr) {
        // 1. 取得返還金額
        double returnValue = bulletMgr.calculateReturnValue();

        // 2. 有返還金額，包裝祥紀錄
        if (returnValue > 0) {
            return Optional.of(
                    new DetailFishReturn(
                        this.getBulletKind(bulletMgr.getObtainedAwardBulletIndex()),
                        bulletMgr.getRecordAwardBulletAmount(),
                        bulletMgr.getObtainedAwardBulletBet(),
                        bulletMgr.getObtainedAwardBulletCost()
            ));
        }

        // 3. 防呆回傳
        return Optional.empty();
    }

    // 取得目標名稱
    private String getTargetKind(int targetIndex) {
        return this.config.getTargetIndexNameMap().get(targetIndex);
    }

    // 取得子彈名稱
    private String getBulletKind(int bulletIndex) {
        return this.config.getBulletIndexNameMap().get(bulletIndex);
    }
}
