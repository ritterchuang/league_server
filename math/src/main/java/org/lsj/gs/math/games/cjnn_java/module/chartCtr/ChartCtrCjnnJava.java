package org.lsj.gs.math.games.cjnn_java.module.chartCtr;

import org.lsj.gs.math.core.baiRen.ConstMathNiu;
import org.lsj.gs.math.core.card.stackCtr.AbstractStack;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.NiuStackCommon;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtilBaiRen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 新超級牛牛路圖計算器
public class ChartCtrCjnnJava {
    private final int KEEP_ROUND = 10;
    private List<Object> chartList; // 路線圖
    private final ITableUtilBaiRen tableUtil; // 百人牌桌工具包介面

    public ChartCtrCjnnJava(ITableUtilBaiRen tableUtil) {
        this.chartList = new ArrayList<>();
        this.tableUtil = tableUtil;
        this.chartList = this.generateChartList();
    }

    // 更新路線圖 TODO 是否抽?
    public void updateChart(Map<Integer, AbstractStack> areaStackMap){
        if(this.chartList.size() >= this.KEEP_ROUND){
            this.chartList.remove(0);
        }

        this.chartList.add(this.calculateStackMapCharResult(areaStackMap));
    }

    // 依照 押注區域牌型，計算路圖 TODO 待確認傳入型別
    private int[] calculateStackMapCharResult(Map<Integer, AbstractStack> areaStackMap) {
        // 1. 轉型
        Map<Integer, NiuStackCommon> areaNiuStackCommonMap = areaStackMap.entrySet().stream().collect(
                Collectors.toMap(Map.Entry::getKey, entry -> (NiuStackCommon) entry.getValue()));

        // 2. 計算各區域結果
        return IntStream.range(0, ConstMathNiu.BetAreaEnum.getBetAreaCount())
                .map(areaId -> ConstMathNiu.ChartEnum.calculateChartCode(
                                areaNiuStackCommonMap.get(ConstMathNiu.BetAreaEnum.getBankerAreaId()),
                                areaNiuStackCommonMap.get(areaId))
                        .getCode())
                .toArray();
    }

    // 產生路圖列表
    private List<Object> generateChartList() {
        // 1. 決定產出局數
        int randomRound = this.tableUtil.getNotSupportSetRngDataRandomUtil().getRandomIntWithAccuracy(this.KEEP_ROUND, ConstMathCommon.AccuracyType.A32768);

        // 2. 產生路圖
        return IntStream.range(0, randomRound)
                .mapToObj(roundIndex -> this.generateChart()).collect(Collectors.toList());
    }

    // 產生一局路圖 int[]，各區勝率為 50 %
    private Object generateChart() {
        return IntStream.range(0, ConstMathNiu.BetAreaEnum.getBetAreaCount())
                .map(betAreaId ->
                        this.tableUtil.getNotSupportSetRngDataRandomUtil().getRandomIntWithAccuracy(
                                ConstMathNiu.ChartEnum.getChartTypeCount(),
                                ConstMathCommon.AccuracyType.A32768))
                .toArray();
    }

    // 取得路圖列表
    public List<Object> getChartList() {
        return chartList;
    }

    // 取得路圖陣列
    public int[][] getChartArray(){
        return this.chartList.toArray(int[][]::new);
    }

    // 重設
    public void reset() {}
}
