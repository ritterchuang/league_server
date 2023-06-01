package org.lsj.gs.math.games.hhdz_java.module.chartCtr;

import org.lsj.gs.math.core.card.stackCtr.jhStackCtr.JhStack;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtilBaiRen;
import org.lsj.gs.math.games.hhdz_java.entity.ConstHhdzJava;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 新紅黑大戰路圖計算器
public class ChartCtrHhdzJava {
    private final int KEEP_ROUND = 80;
    private List<Object> chartList; // 路線圖
    private final ITableUtilBaiRen tableUtil; // 百人牌桌工具包介面

    public ChartCtrHhdzJava(ITableUtilBaiRen tableUtil) {
        this.chartList = new ArrayList<>();
        this.tableUtil = tableUtil;
        this.chartList = this.generateChartList();
    }

    // 更新路線圖
    public void updateChart(Map<Integer, JhStack> stackMap){
        if(this.chartList.size() >= this.KEEP_ROUND){
            this.chartList.remove(0);
        }

        this.chartList.add(
                ConstHhdzJava.ChartEnumHhdzJava.transformCardTypeToChartType(
                        ConstHhdzJava.BetAreaIdHhdzJava.calculateWinBetAreaIdHhdzJava(stackMap).getCode()
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

    // 產生一局路圖 紅勝率 50% 黑勝率 50%
    private Object generateChart() {
        int randomIndex = this.tableUtil.getNotSupportSetRngDataRandomUtil().getArrayIndexByWeightWithAccuracy(
                new ArrayList<>(){{
                    add(50);
                    add(50);
                }},
                ConstMathCommon.AccuracyType.A32768
        );

        return ConstHhdzJava.ChartEnumHhdzJava.transformCardTypeToChartType(randomIndex);
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
