package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.hitTypeExtend.HitTypeExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendTreasureBox;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendTreasureBox;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.HitOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.hitOddsInfoExtend.HitOddsInfoExtend;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// 連擊寶箱打擊計算器工具包
public class HitCtrUtilTreasureBox implements IHitCtrUtil{
    private final HitTypeConfigExtendTreasureBox config; // 客製打擊計算器設定
    private final int minSplitPresentOdds; // 加總最小表演倍數
    private final int maxSplitPresentOdds; // 加總最大表演倍數
    private final ITableUtil tableUtil; // 牌桌工具包

    public HitCtrUtilTreasureBox(HitTypeConfigExtendTreasureBox hitTypeConfigExtendTreasureBox, ITableUtil tableUtil) {
        this.config = hitTypeConfigExtendTreasureBox;
        this.minSplitPresentOdds = 10;
        this.maxSplitPresentOdds = 100;
        this.tableUtil = tableUtil;
    }


    // 計算連擊寶箱倍數資訊
    @Override
    public HitOddsInfo calculateHitOddsInfo(HitTypeExtend hitTypeExtend, ClientTarget target) {
        // 1. 計算目標倍數區間
        int[] targetOddsRange = this.config.getOddsRangeList().get(this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(this.config.getOddsRangeWeightList(), ConstMathCommon.AccuracyType.A32768));

        // 2. 取得目標倍數
        int basicOdds = targetOddsRange[0] + this.tableUtil.getMainRandomUtil().getRandomIntWithAccuracy(targetOddsRange[1] - targetOddsRange[0] + 1, ConstMathCommon.AccuracyType.A32768);

        // 3. 回傳
        return new HitOddsInfo(basicOdds, new HitOddsInfoExtend());
    }

    // 計算 連擊寶箱 客製打擊結果
    @Override
    public HitResultExtendTreasureBox calculateHitResultExtend(boolean killFlag, HitOddsInfo hitOddsInfo, HitTypeExtend hitTypeExtend, Bullet bullet) {
        // 1. 未擊殺回傳空殼
        if (!killFlag) {
            return new HitResultExtendTreasureBox();
        }

        // 2. 計算倍數表演資訊
        List<Integer> showOddsList = this.calculateShowOddsList(hitOddsInfo.getBasicOdds());

        // 3. 計算贏分表演資訊
        List<Double> showWinList = new ArrayList<>();
        showOddsList.forEach(odds -> showWinList.add(MathUtil.moneyScale(MathUtil.multiply(odds, bullet.getBet()))));

        // 4. 回傳
        return new HitResultExtendTreasureBox(showOddsList, showWinList);
    }

    // 依照倍數計算 表演倍數列表
    private List<Integer> calculateShowOddsList(int basicOdds) {
        // 1. 最小可加倍數 防呆
        if (basicOdds < this.minSplitPresentOdds) {
            return List.of(basicOdds);
        }

        // 2. 取得可拆解低倍數
        int lowOdds = (basicOdds % this.minSplitPresentOdds) + this.minSplitPresentOdds;

        // 3. 取得可拆解中倍數
        int mediumOdds = (basicOdds - lowOdds) % this.maxSplitPresentOdds;

        // 4. 取得可拆解高倍數
        int highOddsTimes = (basicOdds - lowOdds) / this.maxSplitPresentOdds;

        // 5. 回傳
        return this.calculateSplitPresentOddsList(lowOdds, mediumOdds, highOddsTimes);
    }

    // 取得低倍數拆解
    private List<Integer> calculateSplitPresentOddsList(int lowOdds, int mediumOdds, int highOddsTimes) {
        // 1. 創建空間
        List<Integer> result = new ArrayList<>();

        // 2. 計算低倍數組合可能
        if (this.config.getLowOddsToCombinationMap().containsKey(lowOdds)) {
            result.addAll(Arrays.stream(this.config.getLowOddsToCombinationMap().get(lowOdds)).boxed().collect(Collectors.toList()));
        }

        // 3. 計算中倍數組合可能
        if (this.config.getMediumOddsToCombinationMap().containsKey(mediumOdds)) {
            result.addAll(Arrays.stream(this.config.getMediumOddsToCombinationMap().get(mediumOdds)).boxed().collect(Collectors.toList()));
        }

        // 4. 計算高倍數組合可能
        result.addAll(this.calculateHighOddsCombinationList(highOddsTimes));

        // 5. 打亂排序
        this.tableUtil.getMainRandomUtil().shuffleList(result);

        // 6. 回傳
        return result;
    }

    // 取得高倍數拆解
    private List<Integer> calculateHighOddsCombinationList(int highOddsTimes) {
        List<Integer> result = new ArrayList<>();

        for (int count = 0; count < highOddsTimes; count++) {
            int highOddsCombinationIndex = this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(this.config.getHighOddsCombinationWeightList(), ConstMathCommon.AccuracyType.A32768);

            result.addAll(Arrays.stream(this.config.getHighOddsCombinationList().get(highOddsCombinationIndex)).boxed().collect(Collectors.toList()));
        }

        return result;
    }
}
