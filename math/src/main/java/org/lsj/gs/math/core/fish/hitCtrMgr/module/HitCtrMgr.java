package org.lsj.gs.math.core.fish.hitCtrMgr.module;

import org.lsj.gs.math.core.AbstractModule;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.entity.PreGameAdjustResult;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import org.lsj.gs.math.core.common.gamePlayerHlr.entity.GamePlayer;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.ClientHit;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitCtrConfig;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitCtrMgrConfig;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.HitCtrFactory;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.IHitCtr;
import org.lsj.utils.MathUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// 打擊計算器管理者
public class HitCtrMgr extends AbstractModule {
    private final HitCtrMgrConfig hitCtrMgrConfig; // 打擊計算器管理者設定
    private final Map<Integer, Map<Integer, IHitCtr>> hitCtrMap; // 打擊計算器對應表 <子彈索引, <目標索引, 打擊計算器>>
    private ClientTarget currentTarget; // 當前目標
    private ClientHit currentHit; // 當前打擊
    private PreGameAdjustResult currentPreGameAdjustResult; // 當前預遊戲結果

    public HitCtrMgr(HitCtrMgrConfig hitCtrMgrConfig, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(gamePlayerHlr, poolCtr, tableUtil);
        this.hitCtrMgrConfig = hitCtrMgrConfig;

        this.hitCtrMap = new HashMap<>();
        hitCtrMgrConfig.getHitCtrConfigMap().forEach((bulletIndex, targetIndexConfigMap) ->
                this.hitCtrMap.put(bulletIndex, this.createTargetIndexHitCtrMap(targetIndexConfigMap))
        );
    }

    // 建立目標索引與打擊計算器對應表
    private Map<Integer, IHitCtr> createTargetIndexHitCtrMap(Map<Integer, HitCtrConfig> targetIndexConfigMap){
        Map<Integer, IHitCtr> result = new HashMap<>();

        targetIndexConfigMap.forEach((targetIndex, hitCtrConfig) ->
                result.put(targetIndex, new HitCtrFactory().createHitCtr(hitCtrConfig, gamePlayerHlr, poolCtr, tableUtil))
        );

        return result;
    }

    // 取得打擊結果
    public HitResult calculateHitResult(Bullet bullet, ClientTarget target, ClientHit hit, BulletMgr bulletMgr, ConstMathCommon.ShuffleType shuffleType, GameAdjustParameter gameAdjustParameter) {
        return this.hitCtrMap.get(bullet.getBulletIndex()).get(target.getTargetIndex()).calculateHitResult(bullet, target, hit, bulletMgr, shuffleType, gameAdjustParameter);
    }



    //* 檢驗打擊資訊 *//
    // 檢查客端目標資訊
    public ConstMathCommon.TableProtocolCode checkClientHit(ClientBullet clientBullet, ClientTarget clientTarget, ClientHit clientHit) {
        // 1. 檢查打擊存在性
        ConstMathCommon.TableProtocolCode tableProtocolCode = this.checkHitExistence(clientBullet, clientTarget);
        if (!tableProtocolCode.equals(ConstMathCommon.TableProtocolCode.COMMON_SUCCESS)) {
            return tableProtocolCode;
        }

        // 2. 檢查打擊完整性
        return this.checkHitComplete(clientBullet, clientTarget, clientHit);
    }

    // 檢查打擊資訊存在性
    private ConstMathCommon.TableProtocolCode checkHitExistence(ClientBullet clientBullet, ClientTarget clientTarget) {
        // 1. 取得該子彈下的目標打擊對應表
        Map<Integer, HitCtrConfig> targetHitCtrConfigMap = this.hitCtrMgrConfig.getHitCtrConfigMap().get(clientBullet.getBulletIndex());
        Map<Integer, IHitCtr> targetHitCtrMap = this.hitCtrMap.get(clientBullet.getBulletIndex());

        // 2. 檢驗該子彈下的目標打擊對應表存在性
        if(Objects.isNull(targetHitCtrConfigMap) || Objects.isNull(targetHitCtrMap)){
            return ConstMathCommon.TableProtocolCode.FISH_HIT_NOT_EXIST;
        }

        // 3. 取得打擊設定
        HitCtrConfig hitCtrConfig = targetHitCtrConfigMap.get(clientTarget.getTargetIndex());
        IHitCtr hitCtr = targetHitCtrMap.get(clientTarget.getTargetIndex());

        // 4. 檢驗打擊組合存在性
        if(Objects.isNull(hitCtrConfig) || Objects.isNull(hitCtr)){
            return ConstMathCommon.TableProtocolCode.FISH_HIT_NOT_EXIST;
        }

        // 5. 回傳正確代碼
        return ConstMathCommon.TableProtocolCode.COMMON_SUCCESS;
    }

    // 檢查打擊資訊完整性
    private ConstMathCommon.TableProtocolCode checkHitComplete(ClientBullet clientBullet, ClientTarget clientTarget, ClientHit clientHit) {
        // 1. 取得打擊設定
        HitCtrConfig hitCtrConfig = this.hitCtrMgrConfig.getHitCtrConfigMap().get(clientBullet.getBulletIndex()).get(clientTarget.getTargetIndex());
        IHitCtr hitCtr = this.hitCtrMap.get(clientBullet.getBulletIndex()).get(clientTarget.getTargetIndex());

        // 2. 檢驗打擊類型一致性
        if(!(clientHit.getHitType().equals(hitCtrConfig.getHitType()))){
            return ConstMathCommon.TableProtocolCode.FISH_HIT_NOT_CONSISTENCY;
        }

        // 3. 檢驗客制打擊完整性
        if(Objects.isNull(clientHit.getHitTypeExtend()) || !hitCtr.checkComplete(clientHit.getHitTypeExtend())){
            return ConstMathCommon.TableProtocolCode.FISH_HIT_NOT_COMPLETE;
        }

        // 4. 回傳正確代碼
        return ConstMathCommon.TableProtocolCode.COMMON_SUCCESS;
    }



    //* 當前資訊 *//
    // 設定當前目標
    public void setCurrentTarget(ClientTarget currentTarget) {
        this.currentTarget = currentTarget;
    }

    // 設定當前打擊
    public void setCurrentHit(ClientHit currentHit) {
        this.currentHit = currentHit;
    }

    // 設定當前預發牌結果
    public void setCurrentPreGameResult(PreGameAdjustResult currentPreGameAdjustResult) {
        this.currentPreGameAdjustResult = currentPreGameAdjustResult;
    }

    // 取得當前目標
    public ClientTarget getCurrentTarget() {
        return currentTarget;
    }

    // 取得當前打擊
    public ClientHit getCurrentHit() {
        return currentHit;
    }

    // 取得當前預發牌結果
    public PreGameAdjustResult getCurrentPreGameResult() {
        return currentPreGameAdjustResult;
    }

    // 取得指定子彈的目標打擊Map
    public Map<Integer, IHitCtr> getTargetHitCtrMap(int bulletIndex) {
        return this.hitCtrMap.get(bulletIndex);
    }



    //* 結果相關*//
    // 計算玩家得分對應表
    public Map<Integer, UidScore> calculateUidScoreMap(GamePlayer humanGamePlayer, Bullet bullet, HitResult hitResult) {
        // 1. 取得真人座位索引
        Integer uid = humanGamePlayer.getChairIndex();

        // 2. 計算真人玩家得分
        UidScore uidScore = this.calculateUidScore(humanGamePlayer, bullet, hitResult);

        // 3. 封裝回傳
        return new HashMap<>(){{put(uid, uidScore);}};
    }

    // 計算真人玩家得分
    private UidScore calculateUidScore(GamePlayer humanGamePlayer, Bullet bullet, HitResult hitResult) {
        return new UidScore(
                humanGamePlayer.getChairIndex(),
                humanGamePlayer.getUid(),
                bullet.getCost(),
                bullet.getCost(),
                hitResult.getTotalWin(),
                MathUtil.subtract(hitResult.getTotalWin(), bullet.getCost()),
                0
        );
    }

    //* 返還相關 *//
    // 計算真人返還得分
    public UidScore calculateReturnUidScore(BulletMgr bulletMgr, GamePlayer humanGamePlayer, double returnValue) {
        return new UidScore(
                humanGamePlayer.getChairIndex(),
                humanGamePlayer.getUid(),
                bulletMgr.getObtainedAwardBulletCost(),
                bulletMgr.getObtainedAwardBulletCost(),
                returnValue,
                MathUtil.subtract(returnValue, bulletMgr.getObtainedAwardBulletCost()),
                0
        );
    }

    // 重設
    @Override
    public void reset() {
        // 有需求才做
    }
}
