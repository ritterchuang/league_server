package org.lsj.gs.math.games.lhd_java.module.chartCtr;

import org.lsj.gs.math.core.card.stackCtr.lhStackCtr.LhStack;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtilBaiRen;
import org.lsj.gs.math.games.lhd_java.entity.ConstLhdJava;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 新龍虎鬥路圖計算器
public class ChartCtrLhdJava {
    private final int KEEP_ROUND = 80;
    private List<Object> chartList; // 路線圖
    private final ITableUtilBaiRen tableUtil; // 百人牌桌工具包介面

    public ChartCtrLhdJava(ITableUtilBaiRen tableUtil) {
        this.chartList = new ArrayList<>();
        this.tableUtil = tableUtil;
        this.chartList = this.generateChartList();
    }

    // 更新路線圖
    public void updateChart(Map<Integer, LhStack> stackMap){
        if(this.chartList.size() >= this.KEEP_ROUND){
            this.chartList.remove(0);
        }
        this.chartList.add(
                ConstLhdJava.LhChartEnumLhdJava.transformCardTypeToChartType(
                    ConstLhdJava.BetAreaIdLhdJava.calculateWinBetAreaIdLhdJava(stackMap).getCode()
                )
        );
    }

    // 產生路圖列表
    private List<Object> generateChartList() {
        // 1. 決定產出局數
        int randomRound = this.tableUtil.getNotSupportSetRngDataRandomUtil().getRandomIntWithAccuracy(this.KEEP_ROUND, ConstMathCommon.AccuracyType.A32768);

        // 2. 產生路圖
        return IntStream.range(0, randomRound)
                .mapToObj(roundIndex -> this.generateChart()).collect(Collectors.toList());
    }

    // 產生一局路圖 龍勝率 49.5% 虎勝率 49.5% 和勝率 1.0%
    private Object generateChart() {
        int randomIndex = this.tableUtil.getNotSupportSetRngDataRandomUtil().getArrayIndexByWeightWithAccuracy(
                new ArrayList<Integer>(){{
                    add(495);
                    add(495);
                    add(10);
                }},
                ConstMathCommon.AccuracyType.A32768
        );

        return ConstLhdJava.LhChartEnumLhdJava.transformCardTypeToChartType(randomIndex);
    }

    // 取得路圖列表
    public List<Object> getChartList() {
        return chartList;
    }

    // 取得路圖陣列
    public int[] getChartArray(){
        return this.chartList.stream().mapToInt(chartType -> (int)chartType).toArray();
    }

    // 重設
    public void reset() {}
}
