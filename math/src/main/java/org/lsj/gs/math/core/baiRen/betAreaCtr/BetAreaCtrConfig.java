package org.lsj.gs.math.core.baiRen.betAreaCtr;

import java.util.List;
import java.util.Map;

// 押注區域計算器設定
public class BetAreaCtrConfig {
    private final double baseScore; // 底注
    private final List<Integer> chipsOddsList; // 下注籌碼倍數列表
    private final Map<Integer, Integer> areasTopLimitOddsMap; // 押注區域限紅倍數對應表 <區域識別碼, 押注區域限紅倍數>
    private final int[][] cantBetTogetherArray; // 不可同時下注列表 [cantIndex][areaIndex] = areaId
    private final int betAreaCount; // 區域個數

    public BetAreaCtrConfig(double baseScore,
                            List<Integer> chipsOddsList,
                            Map<Integer, Integer> areasTopLimitOddsMap,
                            int[][] cantBetTogetherArray,
                            int betAreaCount) {
        this.baseScore = baseScore;
        this.chipsOddsList = chipsOddsList;
        this.areasTopLimitOddsMap = areasTopLimitOddsMap;
        this.cantBetTogetherArray = cantBetTogetherArray;
        this.betAreaCount = betAreaCount;
    }

    public double getBaseScore() {
        return baseScore;
    }

    public List<Integer> getChipsOddsList() {
        return chipsOddsList;
    }

    public Map<Integer, Integer> getAreasTopLimitOddsMap() {
        return areasTopLimitOddsMap;
    }

    public int[][] getCantBetTogetherArray() {
        return cantBetTogetherArray;
    }

    public int getBetAreaCount() {
        return betAreaCount;
    }
}
