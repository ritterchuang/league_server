package com.lx.gs.math.core.common.logic;

import com.lx.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import com.lx.gs.math.config.entity.tableGameConfig.TableGameConfigFish;
import com.lx.gs.math.core.common.ConstMathCommon;
import com.lx.gs.math.core.common.gameAdjust.entity.PreGameAdjustResult;
import com.lx.gs.math.core.common.gameAdjust.entity.PreGameAdjustResultFish;
import com.lx.gs.math.core.common.gameAdjust.entity.UidScore;
import com.lx.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import com.lx.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import com.lx.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultFish;
import com.lx.gs.math.core.common.logic.gameBetLogResultCtr.module.IGameBetLogResultCtrFish;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;
import com.lx.gs.math.core.common.table.module.ITableBase;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.core.fish.bulletMgr.BulletMgr;
import com.lx.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import com.lx.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import com.lx.gs.math.core.fish.bulletMgr.entity.server.BulletMgrConfig;
import com.lx.gs.math.core.fish.clientHitResultPgr.ClientHitResultPgr;
import com.lx.gs.math.core.fish.clientHitResultPgr.IClientHitResultPgr;
import com.lx.gs.math.core.fish.clientHitResultPgr.basicResultCtr.module.BasicResultCtr;
import com.lx.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.module.DisplayResultCollectionCtr;
import com.lx.gs.math.core.fish.detailCtr.enity.system.DetailCtrConfig;
import com.lx.gs.math.core.fish.detailCtr.module.DetailCtr;
import com.lx.gs.math.core.fish.detailCtr.module.IDetailCtr;
import com.lx.gs.math.core.fish.hitCtrMgr.entity.client.ClientHit;
import com.lx.gs.math.core.fish.hitCtrMgr.entity.client.ClientHitResult;
import com.lx.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import com.lx.gs.math.core.fish.hitCtrMgr.entity.server.HitCtrMgrConfig;
import com.lx.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;
import com.lx.gs.math.core.fish.hitCtrMgr.module.HitCtrMgr;
import com.lx.gs.math.core.fish.mathFishConfigHlr.MathFishConfigHlr;
import com.lx.gs.math.core.fish.mathFishConfigHlr.entity.client.MathFishConfig;
import com.lx.gs.math.core.fish.mathFishConfigHlr.entity.server.MathFishConfigHlrConfig;

import java.util.Map;
import java.util.Optional;

// 抽象邏輯魚機
public abstract class AbstractLogicFish extends AbstractLogic implements ILogicFish, ILogic {
    protected final BulletMgr bulletMgr; // 子彈管理器
    protected final HitCtrMgr hitCtrMgr; // 打擊計算器管理者
    protected final IDetailCtr detailCtr; // 詳細記錄計算者
    protected final IGameBetLogResultCtrFish gameBetLogResultCtrFish; // 遊戲投注紀錄計算器魚機
    protected final IClientHitResultPgr clientHitResultPgr; // 客端打擊結果包裝者
    protected final MathFishConfigHlr mathFishConfigHlr; // 數值設定處理器

    public AbstractLogicFish(ITableBase table,
                             TableFieldConfig tableFieldConfig,
                             TableGameConfigFish tableGameConfig,
                             GamePlayerHlr gamePlayerHlr,
                             PoolCtr poolCtr,
                             IGameBetLogResultCtrFish gameBetLogResultCtrFish,
                             ITableUtil tableUtil){
        // 1. 初始化
        super(table, tableFieldConfig, gamePlayerHlr, poolCtr, gameBetLogResultCtrFish, tableUtil);

        // 2. 建立模組設定檔
        BulletMgrConfig bulletMgrConfig = table.getTableConfigMgr().getModuleConfigMgrFish().createBulletMgrConfig(tableGameConfig);
        HitCtrMgrConfig hitCtrMgrConfig = new HitCtrMgrConfig(tableGameConfig.getHitCtrConfigMap());
        DetailCtrConfig detailCtrConfig = table.getTableConfigMgr().getModuleConfigMgrFish().createDetailCtrConfig();
        MathFishConfigHlrConfig mathFishConfigHlrConfig = table.getTableConfigMgr().getModuleConfigMgrFish().createMathFishConfigHlrConfig(tableGameConfig);

        // 3. 初始化模組
        this.bulletMgr = new BulletMgr(bulletMgrConfig, gamePlayerHlr, poolCtr, tableUtil);
        this.hitCtrMgr = new HitCtrMgr(hitCtrMgrConfig, gamePlayerHlr, poolCtr, tableUtil);
        this.detailCtr = new DetailCtr(detailCtrConfig);
        this.gameBetLogResultCtrFish = gameBetLogResultCtrFish;
        this.clientHitResultPgr = new ClientHitResultPgr(new BasicResultCtr(), new DisplayResultCollectionCtr());
        this.mathFishConfigHlr = new MathFishConfigHlr(mathFishConfigHlrConfig, gamePlayerHlr, poolCtr, tableUtil);
    }


    //* 檢查機制 *//
    // 檢查客端子彈資訊
    @Override
    public ConstMathCommon.TableProtocolCode checkClientBullet(ClientBullet clientBullet) {
        return this.bulletMgr.checkClientBullet(clientBullet);
    }

    // 檢查客端目標資訊
    @Override
    public ConstMathCommon.TableProtocolCode checkClientHit(ClientBullet clientBullet, ClientTarget clientTarget, ClientHit clientHit) {
        return this.hitCtrMgr.checkClientHit(clientBullet, clientTarget, clientHit);
    }


    //* 當前資訊 *//
    // 取得數值設定
    @Override
    public MathFishConfig getMathFishConfig() {
        return this.mathFishConfigHlr.getMathFishConfig(this.bulletMgr, this.hitCtrMgr, this.clientHitResultPgr);
    }

    // 計算獎勵子彈預期分數
    @Override
    public double calculateAwardBulletExpectTotalWin(HitResult hitResult) {
        return this.bulletMgr.calculateAwardBulletExpectTotalWin(hitResult);
    }

    // 轉換子彈資訊
    @Override
    public Bullet changeClientBullet2Bullet(ClientBullet clientBullet) {
        return this.bulletMgr.changeClientBullet2Bullet(clientBullet);
    }

    // 取得當前子彈
    @Override
    public Bullet getCurrentBullet() {
        return this.bulletMgr.getCurrentBullet();
    }

    // 取得當前目標
    @Override
    public ClientTarget getCurrentTarget() {
        return this.hitCtrMgr.getCurrentTarget();
    }

    // 取得當前打擊
    @Override
    public ClientHit getCurrentHit() {
        return this.hitCtrMgr.getCurrentHit();
    }

    // 取得客製子彈成本
    @Override
    public double getClientBulletCost(ClientBullet clientBullet) {
        return this.bulletMgr.getBulletMtr(clientBullet.getBulletIndex()).getBulletCost(clientBullet);
    }


    // 更新獎勵子彈結果
    @Override
    public void updateAwardBullet(HitResult hitResult, Bullet currentBullet) {
        this.bulletMgr.updateAwardBullet(hitResult.getAwardBulletType(), hitResult.getAwardBulletExtend(), currentBullet);
    }


    //* 設定資訊相關 *//
    // 設定預遊戲結果
    @Override
    public void setCurrentPreGameResult(PreGameAdjustResult preGameAdjustResult) {
        this.hitCtrMgr.setCurrentPreGameResult(preGameAdjustResult);
    }

    // 設定子彈資訊
    @Override
    public void setCurrentBullet(Bullet bullet) {
        this.bulletMgr.setCurrentBullet(bullet);
    }

    // 設定目標資訊
    @Override
    public void setCurrentTarget(ClientTarget target) {
        this.hitCtrMgr.setCurrentTarget(target);
    }

    // 設定打擊資訊
    @Override
    public void setCurrentHit(ClientHit hit) {
        this.hitCtrMgr.setCurrentHit(hit);
    }


    //* 結果相關 *//
    // 計算打擊結果
    @Override
    public HitResult calculateHitResult(Bullet bullet, ClientTarget target, ClientHit hit, ConstMathCommon.ShuffleType shuffleType, GameAdjustParameter gameAdjustParameter) {
        return this.hitCtrMgr.calculateHitResult(bullet, target, hit, this.bulletMgr, shuffleType, gameAdjustParameter);
    }

    // 包裝客戶打擊結果
    @Override
    public ClientHitResult packageClientHitResult(HitResult hitResult) {
        return this.clientHitResultPgr.packageClientHitResult(hitResult);
    }

    // 取得打擊結果
    @Override
    public HitResult getHitResult() {
        return ((PreGameAdjustResultFish) this.hitCtrMgr.getCurrentPreGameResult()).getHitResult();
    }

    // 計算玩家得分對應表
    @Override
    public Map<Integer, UidScore> calculateUidScoreMap(HitResult hitResult) {
        return this.hitCtrMgr.calculateUidScoreMap(super.getHumanGamePlayer(), this.getCurrentBullet() ,hitResult);
    }

    // 取得真人得分
    @Override
    public UidScore getHumanUidScore() {
        return this.calculateUidScoreMap(this.getHitResult()).get(super.getHumanChairIndex());
    }

    // 計算下注紀錄結果
    @Override
    public IGameBetLogResultFish getGameBetLogResultFish(UidScore uidScore) {
        return this.gameBetLogResultCtrFish.calculateGameBetLogResultFish(super.table.getRoundId(), uidScore); }

    // 計算錯誤子彈使用時機玩家下注紀錄結果
    @Override
    public IGameBetLogResultFish calculateErrorBulletUsageGameBetLogResult() {
        return this.gameBetLogResultCtrFish.calculateErrorBulletUsageGameBetLogResultFish(super.table.getRoundId());
    }

    // 計算返還結果
    @Override
    public Optional<IGameBetLogResultFish> calculateFishReturnResult() {
        return this.gameBetLogResultCtrFish.calculateReturnGameBetLogResultFish(super.table.getRoundId(), this.hitCtrMgr, this.bulletMgr, this.detailCtr);
    }


    //* 詳細記錄相關 *//
    // 重置局資訊
    @Override
    public void resetOneBoard() {
        this.gameBetLogResultCtrFish.resetDetail();
    }

    // 添加目標詳細記錄
    @Override
    public void addDetailFish(Bullet bullet, ClientTarget target, HitResult hitResult) {
        this.detailCtr.calculateDetailFish(bullet, target, hitResult).ifPresent(this.gameBetLogResultCtrFish::addDetail);
    }

    // 添加特殊事件詳細記錄
    @Override
    public void addDetailSpecialFeature(Bullet bullet, HitResult hitResult) {
        this.detailCtr.calculateDetailSpecialFeature(bullet, hitResult).ifPresent(this.gameBetLogResultCtrFish::addDetail);
    }

    // 添加子彈打擊詳細記錄
    @Override
    public void addDetailBulletHit(Bullet bullet) {
        this.detailCtr.calculateDetailBulletHit(bullet).ifPresent(this.gameBetLogResultCtrFish::addDetail);
    }

    // 添加獎勵子彈詳細記錄
    @Override
    public void addDetailAwardBullet(Bullet bullet, HitResult hitResult) {
        this.detailCtr.calculateDetailAwardBullet(bullet, hitResult).ifPresent(this.gameBetLogResultCtrFish::addDetail);
    }
}
