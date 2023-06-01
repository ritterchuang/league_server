package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.BulletConfigBase;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.bulletMgr.module.awardBulletHlr.module.AwardBulletHlr;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletAmountHlr.BulletAmountHlrFactory;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletAmountHlr.IBulletAmountHlr;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.module.BulletCostHlrFactory;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.module.IBulletCostHlr;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletRtpUseTypeHlr.module.BulletRtpUseHlrFactory;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletRtpUseTypeHlr.module.IBulletRtpUseHlr;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.module.BulletTypeHlrFactory;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.module.IBulletTypeHlr;

// 子彈仲介者
public class BulletMtr {
    private final BulletConfigBase bulletConfigBase; // 子彈基礎
    private final IBulletTypeHlr bulletTypeHlr; // 子彈類型處理者
    private final IBulletAmountHlr bulletAmountHlr; // 子彈數量處理者
    private final IBulletCostHlr bulletCostHlr; // 子彈成本處理者
    private final IBulletRtpUseHlr bulletRtpUseHlr; // 子彈Rtp處理者

    public BulletMtr(double base, BulletConfigBase bulletConfigBase) {
        this.bulletConfigBase = bulletConfigBase;
        this.bulletTypeHlr = new BulletTypeHlrFactory().createBulletTypeHlr(bulletConfigBase.getBulletType(), bulletConfigBase.getBulletConfigExtend());
        this.bulletAmountHlr = new BulletAmountHlrFactory().createBulletAmountHlr(bulletConfigBase.getBulletAmountType());
        this.bulletCostHlr = new BulletCostHlrFactory().createBulletCostHlr(base, bulletConfigBase.getBulletCostType(), bulletConfigBase.getBulletCostConfigExtend());
        this.bulletRtpUseHlr = new BulletRtpUseHlrFactory().createBulletRtpUseHlr(bulletConfigBase.getBulletRtpUseType(), bulletConfigBase.getBulletRtpUseConfigExtend());
    }

    //* 檢查相關 *//
    // 檢查子彈存在性
    public ConstMathCommon.TableProtocolCode checkBulletExistence(ClientBullet clientBullet, AwardBulletHlr awardBulletHlr){
        return this.bulletAmountHlr.checkBulletExistence(clientBullet, awardBulletHlr);
    }

    // 檢查子彈完備性
    public ConstMathCommon.TableProtocolCode checkBulletCompleteness(ClientBullet clientBullet) {
        // 1. 檢查傳入BulletType相關資訊
        ConstMathCommon.TableProtocolCode tableProtocolCode = this.bulletTypeHlr.checkBulletCompleteness(clientBullet);
        if (tableProtocolCode != ConstMathCommon.TableProtocolCode.COMMON_SUCCESS) {
            return tableProtocolCode;
        }

        // 2. 檢查傳入BulletCost相關資訊
        return this.bulletCostHlr.checkBulletCompleteness(clientBullet);
    }

    //* 子彈相關資訊*//
    // 取得子彈使用Rtp
    public double getBulletRtp() {
        return this.bulletRtpUseHlr.getBulletRtp();
    }

    // 取得子彈成本
    public double getBulletCost(ClientBullet clientBullet) { return this.bulletCostHlr.getBulletCost(clientBullet);}

    // 計算子彈客端成本列表
    public double[] getBulletCostList(double base) {
        return this.bulletCostHlr.calculateBulletCostList(base);
    }

    // 計算獎勵客製子彈
    public ClientBullet calculateAwardClientBullet(int awardBulletIndex) {
        return new ClientBullet(
                awardBulletIndex,
                this.bulletTypeHlr.getBulletType(),
                this.bulletTypeHlr.calculateAwardBulletTypeExtend(),
                this.bulletCostHlr.getBulletCostType(),
                this.bulletCostHlr.calculateAwardBulletCostExtend()
        );
    }

    // 轉換Sever子彈
    public Bullet changeClientBullet2Bullet(ClientBullet clientBullet, AwardBulletHlr awardBulletHlr) {
        return new Bullet(
                clientBullet.getBulletIndex(),
                clientBullet.getBulletType(),
                clientBullet.getBulletTypeExtend(),
                clientBullet.getBulletCostType(),
                clientBullet.getBulletCostExtend(),
                this.bulletCostHlr.getBulletCost(clientBullet),
                this.bulletCostHlr.calculateBulletBaseBet(clientBullet, awardBulletHlr)
        );
    }
}
