package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.hitTypeExtend.HitTypeExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendRedEnvelope;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendRedEnvelope;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.HitOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.hitOddsInfoExtend.HitOddsInfoExtendRedEnvelope;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// 紅包打擊計算器工具包
public class HitCtrUtilRedEnvelope implements IHitCtrUtil{
    private final HitTypeConfigExtendRedEnvelope config; // 客製打擊計算器設定
    private final ITableUtil tableUtil; // 牌桌工具包

    public HitCtrUtilRedEnvelope(HitTypeConfigExtendRedEnvelope hitTypeConfigExtendRedEnvelope, ITableUtil tableUtil) {
        this.config = hitTypeConfigExtendRedEnvelope;
        this.tableUtil = tableUtil;
    }


    // 計算紅包打擊倍數資訊
    @Override
    public HitOddsInfo calculateHitOddsInfo(HitTypeExtend hitTypeExtend, ClientTarget target) {
        // 1. 計算獲獎個數
        int awardCount = this.config.getAwardCountAndWeightArray()[0][
                this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(
                        Arrays.stream(this.config.getAwardCountAndWeightArray()[1]).boxed().collect(Collectors.toList()), ConstMathCommon.AccuracyType.MILLION)];

        // 2. 計算表演陣列
        int[] showOddsArray = this.config.getAwardCountToAwardOddsListMap().get(awardCount).get(
                this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(
                        Arrays.stream(this.config.getAwardCountToAwardWeightArrayMap().get(awardCount)).boxed().collect(Collectors.toList()), ConstMathCommon.AccuracyType.MILLION)
                );

        // 3. 計算基礎倍數
        int basicOdds = Arrays.stream(showOddsArray).sum();

        // 4. 封裝回傳
        return new HitOddsInfo(basicOdds, new HitOddsInfoExtendRedEnvelope(awardCount,showOddsArray));
    }

    // 計算紅包客製打擊結果
    @Override
    public HitResultExtendRedEnvelope calculateHitResultExtend(boolean killFlag, HitOddsInfo hitOddsInfo, HitTypeExtend hitTypeExtend, Bullet bullet) {
        // 1. 未擊殺回傳空殼
        if (!killFlag) {
            return new HitResultExtendRedEnvelope();
        }

        // 2. 計算獲獎個數
        int awardCount = ((HitOddsInfoExtendRedEnvelope)hitOddsInfo.getHitOddsInfoExtend()).getAwardCount(); // 獲獎個數

        // 3. 計算表演倍數列表
        int[] showOddsArray = this.calculateShowOddsArray(hitOddsInfo);

        // 4. 計算表演贏分列表
        double[] showWinArray = this.calculateShowWinArray(showOddsArray, bullet);

        // 5. 回傳
        return new HitResultExtendRedEnvelope(awardCount, showOddsArray, showWinArray);
    }

    // 計算表演倍數列表
    private int[] calculateShowOddsArray(HitOddsInfo hitOddsInfo) {
        // 1. 複製已獲獎倍數列表
        List<Integer> result = Arrays.stream(((HitOddsInfoExtendRedEnvelope)hitOddsInfo.getHitOddsInfoExtend()).getShowOddsArray()).boxed().collect(Collectors.toList());

        // 2. 已獲獎倍數洗牌
        this.tableUtil.getMainRandomUtil().shuffleList(result);

        // 3. 調整表演個數
        this.adjustShowOddsList(result, this.config.getShowCount());

        // 4. 轉型回傳
        return result.stream().mapToInt(i->i).toArray();
    }

    // 調整表演個數
    protected void adjustShowOddsList(List<Integer> showOddsList, int showCount) {
        // 1. 滿足表演個數不調整
        if (showOddsList.size() == showCount) {
            return;
        }

        // 2. 過多表演個數，移除
        if (showOddsList.size() > showCount) {
            int removeTime = showOddsList.size() - showCount;
            for (int removeIndex = 0; removeIndex < removeTime; removeIndex++) {
                showOddsList.remove(showOddsList.size() - 1);
            }
            return;
        }

        // 3. 過少表演個數，添加
        int addTime = showCount - showOddsList.size();
        for (int addIndex = 0; addIndex < addTime; addIndex++) {
            // 3.1 決定表演倍數
            int showOdds = this.tableUtil.getMainRandomUtil().getRandomElement(Arrays.stream(this.config.getAwardOddsArray()).boxed().collect(Collectors.toList()), ConstMathCommon.AccuracyType.A32768);

            // 3.2 添加表演倍數
            showOddsList.add(showOdds);
        }
    }

    // 計算表演贏分列表
    private double[] calculateShowWinArray(int[] showOddsList, Bullet bullet) {
        List<Double> showWinList = new ArrayList<>();
        Arrays.stream(showOddsList).boxed().forEach(odds -> showWinList.add(MathUtil.moneyScale(MathUtil.multiply(odds, bullet.getBet()))));

        return showWinList.stream().mapToDouble(d->d).toArray();
    }
}
