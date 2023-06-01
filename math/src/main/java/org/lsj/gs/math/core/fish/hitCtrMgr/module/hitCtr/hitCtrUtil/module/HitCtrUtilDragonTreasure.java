package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.hitTypeExtend.HitTypeExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendDragonTreasure;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendDragonTreasure;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.HitOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.hitOddsInfoExtend.HitOddsInfoExtendDragonTreasure;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// 神龍尋珠打擊計算器工具包
public class HitCtrUtilDragonTreasure implements IHitCtrUtil{
    private final HitTypeConfigExtendDragonTreasure config; // 客製打擊計算器設定
    private final ITableUtil tableUtil; // 牌桌工具包

    public HitCtrUtilDragonTreasure(HitTypeConfigExtendDragonTreasure hitTypeConfigExtendDragonTreasure, ITableUtil tableUtil) {
        this.config = hitTypeConfigExtendDragonTreasure;
        this.tableUtil = tableUtil;
    }


    // 計算神龍尋珠打擊倍數資訊
    @Override
    public HitOddsInfo calculateHitOddsInfo(HitTypeExtend hitTypeExtend, ClientTarget target) {
        // 1. 計算結束顆數
        int endBallCount = this.config.getDragonBallEndCountAndWeightArray()[0][this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(Arrays.stream(this.config.getDragonBallEndCountAndWeightArray()[1]).boxed().collect(Collectors.toList()), ConstMathCommon.AccuracyType.MILLION)];

        // 2. 計算表演陣列
        int[] showOddsWeightArray = this.config.getEndBallCountToShowOddsWeightArrayMap().get(endBallCount);
        int showOddsArrayIndex = this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(Arrays.stream(showOddsWeightArray).boxed().collect(Collectors.toList()), ConstMathCommon.AccuracyType.MILLION);
        int[] showOddsArray = this.config.getEndBallCountToShowOddsListMap().get(endBallCount).get(showOddsArrayIndex);

        // 3. 計算倍數和
        int totalSumOdds = Arrays.stream(showOddsArray).sum();

        // 4. 判斷是否處發神龍大獎
        boolean triggerDragon = Arrays.stream(showOddsArray).distinct().count() == this.config.getDragonBallOddsArray().length;

        // 5. 計算顆數獎勵倍數
        int ballCountAwardMultiplier = showOddsArray.length;

        // 6. 計算神龍獎勵倍數
        int dragonExtraAwardMultiplier = !triggerDragon ? 1 : this.config.getDragonExtraMultiplierAndWeightArray()[0][this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(Arrays.stream(this.config.getDragonExtraMultiplierAndWeightArray()[1]).boxed().collect(Collectors.toList()), ConstMathCommon.AccuracyType.MILLION)];

        // 7. 計算此次倍數
        int basicOdds = totalSumOdds * ballCountAwardMultiplier * dragonExtraAwardMultiplier;

        // 8. 回傳
        return new HitOddsInfo(basicOdds, new HitOddsInfoExtendDragonTreasure(showOddsArray, ballCountAwardMultiplier, dragonExtraAwardMultiplier));
    }

    // 計算神龍尋珠客製打擊結果
    @Override
    public HitResultExtendDragonTreasure calculateHitResultExtend(boolean killFlag, HitOddsInfo hitOddsInfo, HitTypeExtend hitTypeExtend, Bullet bullet) {
        // 1. 未擊殺回傳空殼
        if (!killFlag) {
            return new HitResultExtendDragonTreasure();
        }

        // 2. 取得轉型結果
        HitOddsInfoExtendDragonTreasure hitOddsInfoExtendDragonTreasure = (HitOddsInfoExtendDragonTreasure)hitOddsInfo.getHitOddsInfoExtend();

        // 3. 計算是否觸發神龍
        boolean isTriggerDragon = hitOddsInfoExtendDragonTreasure.getDragonExtraAwardMultiplier() != 1;

        // 4. 計算結束顆數
        int endBallCount = hitOddsInfoExtendDragonTreasure.getShowOddsArray().length;

        // 5. 計算顆數獎勵倍數
        int ballCountAwardMultiplier = hitOddsInfoExtendDragonTreasure.getBallCountAwardMultiplier();

        // 6. 計算神龍倍數
        int dragonExtraMultiplier = hitOddsInfoExtendDragonTreasure.getDragonExtraAwardMultiplier();

        // 7. 計算總倍數
        int totalOdds = hitOddsInfo.getBasicOdds();

        // 8. 計算表演列表
        int[] showOddsArray = this.calculateShowOddsArray(hitOddsInfoExtendDragonTreasure.getShowOddsArray(), isTriggerDragon);

        // 9. 回傳
        return new HitResultExtendDragonTreasure(isTriggerDragon, endBallCount, ballCountAwardMultiplier, dragonExtraMultiplier, totalOdds, showOddsArray);
    }

    // 計算表演倍數列表
    private int[] calculateShowOddsArray(int[] showOddsArray, boolean isTriggerDragon) {
        // 1. 複製表演倍數列表
        List<Integer> showOddsCloneList = Arrays.stream(showOddsArray.clone()).boxed().collect(Collectors.toList());

        // 2. 若有處發神龍大獎，直接打亂回傳
        if (isTriggerDragon) {
            // 2.1 打亂表演結果
            this.tableUtil.getMainRandomUtil().shuffleList(showOddsCloneList);

            // 2.2 回傳
            return showOddsCloneList.stream().mapToInt(x -> x).toArray();
        }

        // 3. 找出重複倍數
        int duplicateOdds = showOddsCloneList.stream().collect(Collectors.groupingBy(Integer::intValue, Collectors.counting())).entrySet().stream().filter(entry -> entry.getValue() > 1).findFirst().get().getKey();

        // 4. 取得不重複倍數列表
        List<Integer> distinctShowOddsList = showOddsCloneList.stream().distinct().collect(Collectors.toList());

        // 5. 打亂不重複倍數列表
        this.tableUtil.getMainRandomUtil().shuffleList(distinctShowOddsList);

        // 6. 添加重複倍數
        distinctShowOddsList.add(duplicateOdds);

        // 7. 計算還欠缺幾個
        int leftCount = this.config.getDragonBallOddsArray().length - distinctShowOddsList.size();

        // 8. 任意添加倍數
        for (int addOddIndex = 0; addOddIndex < leftCount; addOddIndex++) {
            // 8.1 取得倍數位置
            int addOdds = this.tableUtil.getMainRandomUtil().getRandomElement(Arrays.stream(this.config.getDragonBallOddsArray()).boxed().collect(Collectors.toList()), ConstMathCommon.AccuracyType.A32768);

            // 8.2 添加倍數
            distinctShowOddsList.add(addOdds);
        }

        // 9. 回傳
        return distinctShowOddsList.stream().mapToInt(x -> x).toArray();
    }
}
