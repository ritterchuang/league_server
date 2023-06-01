package org.lsj.gs.math.core.fish.bulletMgr.module.awardBulletHlr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.bulletMgr.module.awardBulletHlr.enity.AwardBulletInfo;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.BulletMtr;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.client.awardBullet.AwardBulletExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.client.awardBullet.AwardBulletExtendOneType;
import org.lsj.utils.MathUtil;

import java.util.Map;
import java.util.Objects;

// 獎勵子彈處理者
public class AwardBulletHlr implements IAwardBulletHlr{
    private final AwardBulletInfo obtainedAwardBulletInfo; // 獲得獎勵子彈資訊
    private final MinusAwardBulletOpr minusAwardBulletOpr; // 刪減獎勵子彈際操作者
    private final AddAwardBulletOpr addAwardBulletOpr; // 新增獎勵子彈操作者

    public AwardBulletHlr() {
        this.obtainedAwardBulletInfo = new AwardBulletInfo();
        this.minusAwardBulletOpr = new MinusAwardBulletOpr();
        this.addAwardBulletOpr = new AddAwardBulletOpr();
    }

    /* 更新相關 */
    // 更新紀錄獎勵子彈資訊
    @Override
    public void update(ConstMathFish.AwardBulletType awardBulletType, AwardBulletExtend awardBulletExtend, Bullet bullet) {
        // 1. 扣除使用子彈
        this.minusAwardBulletOpr.operateAwardBulletInfo(this.obtainedAwardBulletInfo, bullet);

        // 2. 新增獲得子彈
        this.addAwardBulletOpr.operateAwardBulletInfo(this.obtainedAwardBulletInfo, bullet, awardBulletType, awardBulletExtend);
    }

    /* 返還相關 */
    // 計算返還金額
    @Override
    public double calculateReturnValue(Map<Integer, BulletMtr> bulletMtrMap) {
        // 1. 取得對應子彈中介者
        BulletMtr bulletMtr = bulletMtrMap.get(this.obtainedAwardBulletInfo.getClientBullet().getBulletIndex());

        // 2. 若無子彈中介者，回傳0
        if (Objects.isNull(bulletMtr)) {
            return 0.0;
        }

        // 3. 計算返還淨利
        double score = MathUtil.multiply(bulletMtr.getBulletRtp(), MathUtil.multiply(this.obtainedAwardBulletInfo.getAmount(), this.obtainedAwardBulletInfo.getBaseBet()));

        // 4. 計算返還子彈成本
        double cost = this.getObtainedAwardBulletCost(bulletMtrMap);

        // 5. 計算返還金額
        return MathUtil.moneyScale(MathUtil.add(score, cost));
    }

    /* 當前資訊相關 */
    // 取得獎勵子彈成本
    @Override
    public double getObtainedAwardBulletCost(Map<Integer, BulletMtr> bulletMtrMap) {
        // 1. 取得對應子彈中介者
        BulletMtr bulletMtr = bulletMtrMap.get(this.obtainedAwardBulletInfo.getClientBullet().getBulletIndex());

        // 2. 若無子彈中介者，回傳0
        if (Objects.isNull(bulletMtr)) {
            return 0.0;
        }

        // 3. 計算返還金額
        return bulletMtr.getBulletCost(this.obtainedAwardBulletInfo.getClientBullet());
    }

    // 取得當前結果獎勵子彈得分
    @Override
    public double calculateAwardBulletExpectTotalWin(HitResult hitResult, double bulletBet, Map<Integer, BulletMtr> bulletMtrMap) {
        switch (hitResult.getAwardBulletType()) {
            case ONE_TYPE: return this.calculateOneTypeAwardBulletValue(hitResult, bulletBet, bulletMtrMap);
            default: return 0.0;
        }
    }

    // 計算 單一獎勵子彈價值
    private double calculateOneTypeAwardBulletValue(HitResult hitResult, double bulletBet, Map<Integer, BulletMtr> bulletMtrMap) {
        // 1. 魚沒死，不會有額外子彈價值
        if (hitResult.isKillFlag() == false) {
            return 0.0;
        }

        // 2. 取得 單一獎勵子彈資訊
        AwardBulletExtendOneType awardBulletExtend = ((AwardBulletExtendOneType)hitResult.getAwardBulletExtend());

        // 3. 取得 獎勵子彈個數
        int awardBulletCount = awardBulletExtend.getAmount();
        if (awardBulletCount <= 0) {
            return 0.0;
        }

        // 4. 取得 獎勵子彈 RTP
        double awardBulletRtp = bulletMtrMap.get(awardBulletExtend.getObtainedBullet().getBulletIndex()).getBulletRtp();
        if (awardBulletRtp <= 0) {
            return 0.0;
        }

        // 5. 計算 單一獎勵子彈價值
        return MathUtil.multiply(bulletBet, MathUtil.multiply(awardBulletRtp, awardBulletCount));
    }

    public AwardBulletInfo getObtainedAwardBulletInfo() {
        return obtainedAwardBulletInfo;
    }

    public int getObtainedAwardBulletIndex() {
        return this.obtainedAwardBulletInfo.getClientBullet().getBulletIndex();
    }

    public int getObtainedAwardBulletAmount() {
        return this.obtainedAwardBulletInfo.getAmount();
    }

    public double getObtainedAwardBulletBaseBet() {
        return this.obtainedAwardBulletInfo.getBaseBet();
    }
}
