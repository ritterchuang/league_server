package org.lsj.gs.math.core.common.table.module.tableUtil.random;

import org.lsj.gs.math.core.common.table.module.tableUtil.random.wpr.IRandomWprMain;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.wpr.MathRandomWpr;
import org.lsj.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

// 數值隨機工具包
public class MathRandomUtil extends AbstractRandomUtil implements IRandomUtilMain{
    private static final Logger LOG = LoggerFactory.getLogger(MathRandomUtil.class);
    private final IRandomWprMain randomWprMain; // 主要封裝亂數產生器

    public MathRandomUtil() {
        super(new MathRandomWpr());
        this.randomWprMain = (IRandomWprMain) super.randomWpr;
    }

    // 設定隨機亂數
    @Override
    public void setRandomNumberList(ArrayList<Integer> randomNumberList){
        this.randomWprMain.setRandomNumberList(new ArrayList<>(randomNumberList));
        LOG.info("setRandomNumberList: {}", randomNumberList);
    }

    // 取得隨機亂數列表
    @Override
    public List<Integer> getRandomNumberList() {
        return this.randomWprMain.getRandomNumberList();
    }

    // 取得隨機亂數
    @Override
    public List<Integer> getRandomNumberUsedList() {
        return this.randomWprMain.getRandomNumberUsedList();
    }

    // 設定亂數陣列
    @Override
    public void setRandomNumberListByString(String rngDataString){
        // 1. 過濾空字串
        if(rngDataString.isEmpty()){
            return;
        }

        // 2. 解析隨數字串
        Integer[] rngDataArray = JsonUtil.getInstance().readValueWithoutException(rngDataString, Integer[].class);

        // 3. 過濾解析失敗物件
        if(Objects.isNull(rngDataArray)){
            return;
        }

        // 4. 設定亂數陣列
        this.setRandomNumberList((ArrayList<Integer>) Arrays.stream(rngDataArray).collect(Collectors.toList()));
    }

    // 是否為設定亂數狀態
    @Override
    public boolean isSetRngDataState(){
        return (this.getRandomNumberList().size() > 0);
    }

    // 重置
    @Override
    public void reset() {
        this.randomWprMain.reset();
    }
}
