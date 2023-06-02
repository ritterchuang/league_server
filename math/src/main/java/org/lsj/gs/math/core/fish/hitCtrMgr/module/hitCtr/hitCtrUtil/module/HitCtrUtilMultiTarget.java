package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.module;

import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.hitTypeExtend.HitTypeExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.hitTypeExtend.HitTypeExtendMultiTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendMultiTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendMultiTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.HitOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.hitOddsInfoExtend.HitOddsInfoExtend;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 多重目標打擊計算器工具包
public class HitCtrUtilMultiTarget implements IHitCtrUtil{
    private final HitTypeConfigExtendMultiTarget config; // 客製打擊計算器設定

    public HitCtrUtilMultiTarget(HitTypeConfigExtendMultiTarget config) {
        this.config = config;
    }


    // 計算多重目標打擊倍數資訊
    @Override
    public HitOddsInfo calculateHitOddsInfo(HitTypeExtend hitTypeExtend, ClientTarget target) {
        // 1. 取得轉型結果
        HitTypeExtendMultiTarget targetExtend = (HitTypeExtendMultiTarget)hitTypeExtend;

        // 2. 取得魚種MAP
        Map<Integer, Integer> targetCountMap = targetExtend.getTargetCountMap();

        // 3. target強制改為1隻
        targetCountMap.put(target.getTargetIndex(),1);

        // 4. 計算倍數加總
        List<Integer> targetOdds = new ArrayList<>();
        targetCountMap.forEach((targetIndex, count) -> targetOdds.add(config.getTargetOddsMap().get(targetIndex) * count));
        int basicOdds = targetOdds.stream().mapToInt(Integer::intValue).sum();

        // 5. 回傳
        return new HitOddsInfo(basicOdds, new HitOddsInfoExtend());
    }

    // 計算多重目標客製打擊結果
    @Override
    public HitResultExtendMultiTarget calculateHitResultExtend(boolean killFlag, HitOddsInfo hitOddsInfo, HitTypeExtend hitTypeExtend, Bullet bullet) {
        // 1. 無擊殺，回傳空殼
        if (!killFlag) {
            return new HitResultExtendMultiTarget();
        }

        // 2. 取得轉型結果
        HitTypeExtendMultiTarget targetExtend = (HitTypeExtendMultiTarget)hitTypeExtend;

        // 3. 計算目標得分對應表 <目標索引, 得分>
        Map<Integer, Double> result = new HashMap<>();
        targetExtend.getTargetCountMap().forEach((targetIndex, count) ->
                result.put(targetIndex, MathUtil.multiply(this.config.getTargetOddsMap().get(targetIndex), bullet.getBet()))
        );

        // 4. 封裝
        return new HitResultExtendMultiTarget(result);
    }
}
