package org.lsj.gs.math.core.common.table.module.tableUtil.random;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.accuracy.IRandomAccuracy;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.accuracy.RandomA32768;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.accuracy.RandomBillion;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.accuracy.RandomMillion;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.wpr.IRandomWpr;
import org.lsj.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

// 抽象亂數工具包
public abstract class AbstractRandomUtil implements IRandomUtil{
    protected final IRandomWpr randomWpr; // 封裝亂數產生器
    private final Map<ConstMathCommon.AccuracyType, IRandomAccuracy> randomAccuracyMap; // 精準度隨機亂數產生器
    private final String ALPHANUMERIC = "0123456789abcdefghijklmnopqrstuvwxyz"; // 英數字定義
    private static final Logger LOG = LoggerFactory.getLogger(AbstractRandomUtil.class);
    
    public AbstractRandomUtil(IRandomWpr randomWpr) {
        this.randomWpr = randomWpr;
        this.randomAccuracyMap = new HashMap<>(){{
            put(ConstMathCommon.AccuracyType.A32768, new RandomA32768(randomWpr));
            put(ConstMathCommon.AccuracyType.MILLION, new RandomMillion(randomWpr));
            put(ConstMathCommon.AccuracyType.BILLION, new RandomBillion(randomWpr));
        }};
    }

    /* 產生亂數相關 */
    // 產生指定精準度的上界隨機整數 [0, bound)
    @Override
    public int getRandomIntWithAccuracy(int bound, ConstMathCommon.AccuracyType accuracyType) {
        if(bound == 0){
            LOG.error("getRandomIntWithAccuracy: bound is 0 zero");
            return 0;
        }

        return this.randomAccuracyMap.get(accuracyType).getRandomInt(bound);
    }

    // 產生指定精準度的隨機小數
    @Override
    public double getRandomDoubleWithAccuracy(ConstMathCommon.AccuracyType accuracyType) {
        return this.randomAccuracyMap.get(accuracyType).getRandomDouble();
    }


    /* 擊中相關 */
    // 指定精準度的擊中判斷
    @Override
    public boolean isHitWithAccuracy(double prob, ConstMathCommon.AccuracyType accuracyType) {
        return this.getRandomDoubleWithAccuracy(accuracyType) < prob;
    }


    /* 陣列取值 */
    // 指定精準度的陣列均勻取值
    @Override
    public <T> T getRandomElement(List<T> elementList, ConstMathCommon.AccuracyType accuracyType){
        // 1. 防呆
        if(Objects.isNull(elementList) || elementList.size() == 0){
            return null;
        }

        // 2. 建立均勻權重陣列
        List<Integer> uniformWeightList = new ArrayList<>();
        elementList.forEach(element -> uniformWeightList.add(1));

        // 3. 取得隨機陣列值
        int randomIndex = this.getArrayIndexByWeightWithAccuracy(uniformWeightList, accuracyType);

        return elementList.get(randomIndex);
    }

    // 取得指定精準度的隨機陣列索引
    @Override
    public int getArrayIndexByWeightWithAccuracy(List<Integer> weight, ConstMathCommon.AccuracyType accuracyType) {
        int errorMessage = -1;
        // 1. 空陣列防呆
        if (Objects.isNull(weight) || weight.size() == 0) {
            return errorMessage;
        }

        // 2. 負權重防呆
        if(weight.stream().anyMatch(integer -> integer < 0)){
            return errorMessage;
        }

        // 3. 總權重零防呆
        if (weight.stream().allMatch(integer -> integer == 0)) {
            return errorMessage;
        }

        // 4. 計算總權重
        int totalWeight = weight.stream().mapToInt(i -> i).sum();

        // 5. 產生亂數
        int randomInt = this.getRandomIntWithAccuracy(totalWeight, accuracyType);

        // 6. 決定索引
        for (int index = 0; index < weight.size(); index++)
            if (randomInt < weight.get(index))
                return index;
            else
                randomInt = randomInt - weight.get(index);

        return errorMessage;
    }


    /* 交換相關 */
    // 交換陣列元素位置
    @Override
    public void swap(Object[] array, int index1, int index2) {
        Object object1 = array[index1];
        array[index1] = array[index2];
        array[index2] = object1;
    }

    // 交換陣列元素位置
    @Override
    public <T> void swapList(List<T> list, int index1, int index2) {
        T object1 = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, object1);
    }

    // 物件洗牌
    @Override
    public <T> void shuffleList(List<T> list) {
        for (int frontIndex = 0; frontIndex < (list.size() - 1); frontIndex++) {
            int remainListTargetIndex = this.getRandomIntWithAccuracy((list.size() - frontIndex), ConstMathCommon.AccuracyType.A32768);
            this.swapList(list, frontIndex, (frontIndex + remainListTargetIndex));
        }
    }


    /* 隨機字串 */
    // 隨機英數字
    @Override
    public String randomAlphanumeric(int count){
        // 1. 防呆
        if(count <= 0){
            return StringUtil.getInstance().getEmptyString();
        }

        // 2. 依照指定字數產生字串
        StringBuilder stringBuilder = new StringBuilder();
        for(int countIndex = 0; countIndex < count; countIndex++){
            stringBuilder.append(this.ALPHANUMERIC.charAt(this.getRandomIntWithAccuracy(this.ALPHANUMERIC.length(), ConstMathCommon.AccuracyType.A32768)));
        }

        // 3. 回傳字串
        return stringBuilder.toString();
    }
}
