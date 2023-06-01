package org.lsj.gs.math.core.common.logic;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.entity.PreGameAdjustResult;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultFish;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.ClientHit;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.ClientHitResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;
import org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.client.MathFishConfig;

import java.util.Map;
import java.util.Optional;

// 魚機邏輯介面
public interface ILogicFish extends ILogic {
    //* 檢查機制 *//
    ConstMathCommon.TableProtocolCode checkClientBullet(ClientBullet clientBullet); // 檢查客端子彈資訊
    ConstMathCommon.TableProtocolCode checkClientHit(ClientBullet clientBullet, ClientTarget clientTarget, ClientHit clientHit); // 檢查客端打擊資訊

    //* 當前資訊 *//
    MathFishConfig getMathFishConfig(); // 取得數值設定
    Bullet changeClientBullet2Bullet(ClientBullet clientBullet); // 取得系統子彈資訊
    Bullet getCurrentBullet(); // 取得當前子彈
    double calculateAwardBulletExpectTotalWin(HitResult hitResult); // 計算獎勵子彈預期分數
    ClientTarget getCurrentTarget(); // 取得當前目標
    ClientHit getCurrentHit(); // 取得當前打擊
    HitResult getHitResult(); // 取得打擊結果
    double getClientBulletCost(ClientBullet clientBullet); // 取得客製子彈成本


    //* 更新相關 *//
    void updateAwardBullet(HitResult hitResult, Bullet currentBullet); // 更新獎勵子彈結果

    //* 設定資訊相關 *//
    void setCurrentPreGameResult(PreGameAdjustResult preGameAdjustResult); // 設定預遊戲結果
    void setCurrentBullet(Bullet bullet); // 設定當前子彈
    void setCurrentTarget(ClientTarget target); // 設定當前目標
    void setCurrentHit(ClientHit hit); // 設定當前打擊

    //* 結果相關 *//
    HitResult calculateHitResult(Bullet bullet, ClientTarget clientTarget, ClientHit hit, ConstMathCommon.ShuffleType shuffleType, GameAdjustParameter gameAdjustParameter); // 計算打擊結果
    ClientHitResult packageClientHitResult(HitResult hitResult); // 包裝客端打擊結果
    Map<Integer, UidScore> calculateUidScoreMap(HitResult hitResult); // 計算玩家得分對應表
    IGameBetLogResultFish getGameBetLogResultFish(UidScore uidScore); // 取得遊戲結果
    IGameBetLogResultFish calculateErrorBulletUsageGameBetLogResult(); // 計算錯誤子彈使用時機玩家下注紀錄結果
    Optional<IGameBetLogResultFish> calculateFishReturnResult(); // 計算返還結果

    //* 詳細記錄相關 *//
    void resetOneBoard(); // 重置局資訊
    void addDetailFish(Bullet bullet, ClientTarget target, HitResult hitResult); // 添加目標詳細記錄
    void addDetailSpecialFeature(Bullet bullet, HitResult hitResult); // 添加特殊事件詳細記錄
    void addDetailBulletHit(Bullet bullet); // 添加子彈打擊詳細記錄
    void addDetailAwardBullet(Bullet bullet, HitResult hitResult); // 添加獎勵子彈詳細記錄
}
