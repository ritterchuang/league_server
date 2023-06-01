package org.lsj.gs.math.core.baiRen.betAreaCtr;

import java.util.List;
import java.util.Map;

// 押注區域計算器遊戲設定
public class BetAreaCtrGameConfig {
    private final List<Integer> chipsOddsList; // 下注籌碼倍數列表
    private final Map<Integer, Integer> areasTopLimitOddsMap; // 押注區域限紅倍數對應表 <區域識別碼, 押注區域限紅倍數>
    private final int[][] cantBetTogetherList; // 不可同時下注列表 [cantIndex][areaIndex] = areaId

    public BetAreaCtrGameConfig(
            List<Integer> chipsOddsList,
            Map<Integer, Integer> areasTopLimitOddsMap,
            int[][] cantBetTogetherList) {
        this.chipsOddsList = chipsOddsList;
        this.areasTopLimitOddsMap = areasTopLimitOddsMap;
        this.cantBetTogetherList = cantBetTogetherList;
    }

    public List<Integer> getChipsOddsList() {
        return chipsOddsList;
    }

    public Map<Integer, Integer> getAreasTopLimitOddsMap() {
        return areasTopLimitOddsMap;
    }

    public int[][] getCantBetTogetherArray() {
        return cantBetTogetherList;
    }
}
