package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.ClientHit;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.hitTypeExtend.HitTypeExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.hitTypeExtend.HitTypeExtendMultiTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitCtrConfig;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendMultiTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.module.HitCtrUtilMultiTarget;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

// 多重目標打擊計算器
public class HitCtrMultiTarget extends AbstractHitCtr implements IHitCtr {
    private final ConstMathFish.HitType hitType; // 打擊類型
    private final HitTypeConfigExtendMultiTarget config; // 客製打擊計算器設定
    private final HitCtrUtilMultiTarget hitCtrUtil; // 打擊計算工具包

    public HitCtrMultiTarget(HitCtrConfig hitCtrConfig, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(hitCtrConfig, gamePlayerHlr, poolCtr, tableUtil);
        this.hitType = hitCtrConfig.getHitType();
        this.config = (HitTypeConfigExtendMultiTarget) hitCtrConfig.getHitTypeConfigExtend();
        this.hitCtrUtil = new HitCtrUtilMultiTarget(this.config);
    }

    // 取得真人打擊結果
    @Override
    public HitResult calculateHitResult(Bullet bullet, ClientTarget target, ClientHit hit, BulletMgr bulletMgr, ConstMathCommon.ShuffleType shuffleType, GameAdjustParameter gameAdjustParameter) {
        return super.calculateHitResult(bullet, target, hit, 1, bulletMgr, this.hitCtrUtil, shuffleType, gameAdjustParameter);
    }


    //* 檢驗相關 *//
    // 檢驗完整性
    @Override
    public boolean checkComplete(HitTypeExtend hitTypeExtend) {
        // 1. 轉型
        HitTypeExtendMultiTarget hitTypeExtendMultiTarget = (HitTypeExtendMultiTarget) hitTypeExtend;

        // 2. 取得目標數量對應表
        Map<Integer, Integer> targetCountMap = hitTypeExtendMultiTarget.getTargetCountMap();

        // 3. 檢驗目標數量對應表存在性
        if (Objects.isNull(targetCountMap)) {
            return false;
        }

        // 4. 檢驗目標存在性
        if (!this.checkAllTargetExist(targetCountMap)) {
            return false;
        }

        // 5. 檢驗數量正確性
        if (targetCountMap.values().stream().anyMatch(count -> count <= 0)) {
            return false;
        }

        return true;
    }

    // 檢驗目標存在性
    private boolean checkAllTargetExist(Map<Integer, Integer> targetCountMap) {
        boolean allTargetExistFlag = true;

        // 1. 取得定義列表
        Integer[] definedTargetArray = this.config.getTargetOddsMap().keySet().toArray(Integer[]::new);

        // 2. 檢驗目標存在性
        for (Integer targetIndex : targetCountMap.keySet()) {
            if (Arrays.stream(definedTargetArray).noneMatch(definedTargetIndex -> Objects.equals(definedTargetIndex, targetIndex))) {
                allTargetExistFlag = false;
                break;
            }
        }

        // 3. 回傳
        return allTargetExistFlag;
    }
}
