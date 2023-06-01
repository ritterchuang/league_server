package org.lsj.gs.math.core.fish.bulletMgr;

import org.lsj.gs.math.core.AbstractModule;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.BulletMgrConfig;
import org.lsj.gs.math.core.fish.bulletMgr.module.awardBulletHlr.enity.AwardBulletInfo;
import org.lsj.gs.math.core.fish.bulletMgr.module.awardBulletHlr.module.AwardBulletHlr;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.BulletMtr;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.client.awardBullet.AwardBulletExtend;
import org.lsj.utils.MathUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// 子彈管理器
public class BulletMgr extends AbstractModule {
    private final BulletMgrConfig bulletMgrConfig; // 子彈管理器設定
    private final Map<Integer, BulletMtr> bulletMtrMap; // 子彈中間者對應表
    private final AwardBulletHlr awardBulletHlr; // 獎勵子彈處理者
    private Bullet currentBullet; // 當前子彈
    
    public BulletMgr(BulletMgrConfig bulletMgrConfig, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(gamePlayerHlr, poolCtr, tableUtil);
        this.bulletMgrConfig = bulletMgrConfig;
        this.bulletMtrMap = this.calculateBulletMtrMap(this.bulletMgrConfig);
        this.awardBulletHlr = new AwardBulletHlr();
    }

    // 計算子彈中間者對應表
    private Map<Integer, BulletMtr> calculateBulletMtrMap(BulletMgrConfig bulletMgrConfig) {
        // 1. 初始化子彈結果對應表
        Map<Integer, BulletMtr> result = new HashMap<>();

        // 2. 遍歷所有子彈
        bulletMgrConfig.getBulletConfigMap().forEach(
                (bulletIndex, bulletConfig) -> result.put(bulletIndex,
                        // 3. 生成子彈中介者列表
                        new BulletMtr(bulletMgrConfig.getBase(), bulletMgrConfig.getBulletConfigMap().get(bulletIndex)))
        );

        return result;
    }

    //* 檢驗機制 *//
    // 檢查客端子彈資訊
    public ConstMathCommon.TableProtocolCode checkClientBullet(ClientBullet clientBullet){
        // 1. 檢查子彈存在性
        ConstMathCommon.TableProtocolCode tableProtocolCode = this.checkBulletExistence(clientBullet, this.awardBulletHlr);
        if (tableProtocolCode != ConstMathCommon.TableProtocolCode.COMMON_SUCCESS) {
            return tableProtocolCode;
        }

        // 2. 檢查子彈完備性
        return this.bulletMtrMap.get(clientBullet.getBulletIndex()).checkBulletCompleteness(clientBullet);
    }

    private ConstMathCommon.TableProtocolCode checkBulletExistence(ClientBullet clientBullet, AwardBulletHlr awardBulletHlr) {
        // 1. 檢查中介者存在性
        if (Objects.isNull(this.bulletMtrMap.get(clientBullet.getBulletIndex()))) {
            return ConstMathCommon.TableProtocolCode.FISH_BULLET_INDEX_NOT_EXIST;
        }

        // 2. 檢查子彈存在性
        return this.bulletMtrMap.get(clientBullet.getBulletIndex()).checkBulletExistence(clientBullet, awardBulletHlr);
    }


    //* 子彈資訊 *//
    // 取得子彈中介者
    public BulletMtr getBulletMtr(int bulletIndex) {
        return this.bulletMtrMap.get(bulletIndex);
    }

    // 轉換子彈
    public Bullet changeClientBullet2Bullet(ClientBullet clientBullet) {
        // 1. 取得子彈中介者
        BulletMtr bulletMtr = this.getBulletMtr(clientBullet.getBulletIndex());

        // 2. 回傳服務端子彈
        return bulletMtr.changeClientBullet2Bullet(clientBullet, this.awardBulletHlr);
    }

    // 取得服務端子彈
    public Bullet getCurrentBullet() {
        return currentBullet;
    }

    // 設定服務端子彈
    public void setCurrentBullet(Bullet currentBullet) {
        this.currentBullet = currentBullet;
    }


    //* 獎勵子彈 *//
    // 計算獎勵客製子彈
    public ClientBullet calculateAwardClientBullet(int bulletIndex) {
        return this.bulletMtrMap.get(bulletIndex).calculateAwardClientBullet(bulletIndex);
    }

    // 更新獎勵子彈資訊
    public void updateAwardBullet(ConstMathFish.AwardBulletType awardBulletType, AwardBulletExtend awardBulletExtend, Bullet bullet) {
        this.awardBulletHlr.update(awardBulletType, awardBulletExtend, bullet);
    }

    // 取得獎勵子彈資訊
    public AwardBulletInfo getObtainedAwardBulletInfo() {
        return this.awardBulletHlr.getObtainedAwardBulletInfo();
    }

    // 取得獎勵子彈代碼
    public int getObtainedAwardBulletIndex() {
        return this.awardBulletHlr.getObtainedAwardBulletIndex();
    }

    // 取得獎勵子彈個數
    public int getRecordAwardBulletAmount() {
        return this.awardBulletHlr.getObtainedAwardBulletAmount();
    }

    // 取得獎勵子彈價值
    public double getObtainedAwardBulletBet() {
        return MathUtil.moneyScale(
                MathUtil.multiply(this.awardBulletHlr.getObtainedAwardBulletBaseBet(),
                        this.bulletMtrMap.get(this.getObtainedAwardBulletIndex()).getBulletRtp()));
    }

    // 取得獎勵子彈成本
    public double getObtainedAwardBulletCost() {
        return this.awardBulletHlr.getObtainedAwardBulletCost(this.bulletMtrMap);
    }

    // 獎勵子彈返還金額
    public double calculateReturnValue() {
        return this.awardBulletHlr.calculateReturnValue(this.bulletMtrMap);
    }

    // 計算獎勵子彈期望得分
    public double calculateAwardBulletExpectTotalWin(HitResult hitResult) {
        return this.awardBulletHlr.calculateAwardBulletExpectTotalWin(hitResult, this.currentBullet.getBet(), this.bulletMtrMap);
    }

    // 重設
    @Override
    public void reset() {}
}
