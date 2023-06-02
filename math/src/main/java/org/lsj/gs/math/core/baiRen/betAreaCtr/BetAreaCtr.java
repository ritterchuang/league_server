package org.lsj.gs.math.core.baiRen.betAreaCtr;

import org.lsj.gs.math.core.AbstractModule;
import org.lsj.gs.math.core.baiRen.baseCtr.BaseCtr;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.utils.MathUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 押注區域計算器
public class BetAreaCtr extends AbstractModule {
    private final BetAreaCtrConfig config; // 押注區域計算器設定
    private final BaseCtr baseCtr; // 底注計算器
    private List<Integer> chipsList; // 下注籌碼列表
    private Map<Integer, Integer> areasTopLimitMap; // 押注區域限紅對應表 <區域識別碼, 押注區域限紅>
    private final Map<Integer, Map<Integer, Integer>> chairToAreaBetMap; // 玩家押注區域金額對應表 <玩家座位索引, <區域識別碼, 押注金額>>

    public BetAreaCtr(BetAreaCtrConfig config, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(gamePlayerHlr, poolCtr, tableUtil);
        this.config = config;
        this.baseCtr = new BaseCtr();
        this.chipsList = this.calculateChipsList();
        this.areasTopLimitMap = this.calculateAreasTopLimitMap();
        this.chairToAreaBetMap = new HashMap<>();
    }


    //* 檢驗相關 *//

    // 驗證下注區域合法性
    public boolean isValidArea(int area) {
        return Objects.nonNull(this.config.getAreasTopLimitOddsMap().get(area));
    }

    // 驗證下注金額列表合法性
    public boolean isValidChips(int chips) {
        return this.chipsList.stream().anyMatch(i -> i == chips);
    }

    // 驗證下注單點限額
    public boolean isAreaBetToTopLimit(int chairIndex, int area, int chips) {
        return (this.getPlayerAreaBetAmount(chairIndex, area) + chips) <= this.areasTopLimitMap.get(area);
    }

    // 驗證下注區域可行性
    public boolean canBetToArea(int chairIndex, int area) {
        // 1. 遍歷不可同時押注設定
        for (int[] cantBetTogetherArray: this.config.getCantBetTogetherArray()) {
            // 2. 判斷不可同時押注性
            if(this.calculateCantBetTogetherFlag(chairIndex, area, cantBetTogetherArray)){
                return false;
            }
        }

        return true;
    }

    // 不可同時押注性 true: 滿足不可同時押注性 則不給押注
    private boolean calculateCantBetTogetherFlag(int chairIndex, int betArea, int[] cantBetTogetherArray){
        // 1. 判斷是否在設定區內
        if(Arrays.stream(cantBetTogetherArray).noneMatch(i -> i == betArea)){
            return false;
        }

        // 2. 判斷其他區域內是否有下注
        int otherBetAmount = 0;
        for (Integer cantBetArea: Arrays.stream(cantBetTogetherArray).filter(cantBetArea -> cantBetArea != betArea).boxed().collect(Collectors.toList())) {
            otherBetAmount += this.getPlayerAreaBetAmount(chairIndex, cantBetArea);
        }

        return otherBetAmount > 0;
    }

    // 取得指定玩家區域押注
    private int getPlayerAreaBetAmount(int chairIndex, int area){
        if(Objects.isNull(this.chairToAreaBetMap.get(chairIndex)) || Objects.isNull(this.chairToAreaBetMap.get(chairIndex).get(area))){
            return 0;
        }else{
            return this.chairToAreaBetMap.get(chairIndex).get(area);
        }
    }


    //* 下注區域相關 *//

    // 更新投注區域
    public void updateAreaBet(int chairIndex, int areaId, int betAmount){
        // 1. 創建玩家押注區域
        if(Objects.isNull(this.chairToAreaBetMap.get(chairIndex))){
            this.chairToAreaBetMap.put(chairIndex, new HashMap<>());
        }

        // 2. 創建玩家押注區域
        if(Objects.isNull(this.chairToAreaBetMap.get(chairIndex).get(areaId))){
            this.chairToAreaBetMap.get(chairIndex).put(areaId, betAmount);
        }else{
            // 3. 更新玩家押注區域
            this.chairToAreaBetMap.get(chairIndex).put(
                    areaId,
                    this.chairToAreaBetMap.get(chairIndex).get(areaId) + betAmount
            );
        }
    }

    // 取得所有押注區域金額陣列
    public int[] getAreaBetArray(){
        // 1. 計算所有玩家的區域押注總和對應表
        Map<Integer, Integer> totalBetAreaMap = new HashMap<>();
        this.chairToAreaBetMap.forEach((chairIndex, playerAreaBetMap) ->
                playerAreaBetMap.forEach((areaId, betAmount) -> this.calculateTotalBetAreaMap(totalBetAreaMap, areaId, betAmount))
        );

        // 2. 轉換陣列回傳
        return this.transformMapToArray(totalBetAreaMap);
    }

    // 計算所有押注區域金額對應表
    private void calculateTotalBetAreaMap(Map<Integer, Integer> totalBetAreaMap, int areaId, int betAmount){
        if(Objects.isNull(totalBetAreaMap.get(areaId))) {
            totalBetAreaMap.put(areaId, betAmount);
        }else{
            totalBetAreaMap.put(areaId, betAmount + totalBetAreaMap.get(areaId));
        }
    }

    //* 玩家相關 *//

    // 取得玩家總押注金額
    public int getPlayerTotalAreaBet(int chairIndex){
        return Arrays.stream(this.getPlayerAreaBetArray(chairIndex)).sum();
    }

    // 計算押注後剩餘金額
    public double calculateBeginMoneyAfterBet(double beginMoney, int chairIndex){
        return MathUtil.subtract(
                beginMoney,
                this.getPlayerTotalAreaBet(chairIndex)
        );
    }

    // 取得指定玩家押注區域金額對應表
    public Map<Integer, Integer> getPlayerAreaBetMap(int chairIndex){
        return this.chairToAreaBetMap.get(chairIndex);
    }

    // 取得指定玩家押注區域金額陣列
    public int[] getPlayerAreaBetArray(int chairIndex){
        return this.transformMapToArray(this.chairToAreaBetMap.getOrDefault(chairIndex, new HashMap<>()));
    }

    // 取得指定玩家押注區域識別碼陣列
    public int[] getPlayerAreaIdArray(int chairIndex){
        List<Integer> playerAreaIdList = new ArrayList<>();

        for (Map.Entry<Integer, Integer> areaIdBetAmountMap: this.chairToAreaBetMap.get(chairIndex).entrySet()) {
            if(areaIdBetAmountMap.getValue() > 0){
                playerAreaIdList.add(areaIdBetAmountMap.getKey());
            }
        }

        return playerAreaIdList.stream().mapToInt(i -> i).toArray();
    }

    // 轉換押注對應表為押注區域陣列
    private int[] transformMapToArray(Map<Integer, Integer> map){
        return IntStream.range(0, this.config.getBetAreaCount())
                .map(betAreaIndex -> map.getOrDefault(betAreaIndex, 0))
                .toArray();
    }


    //* 設定相關 *//

    // 計算下注籌碼列表
    private List<Integer> calculateChipsList() {
        List<Integer> result = new ArrayList<>();

        // 1. 計算合理的底注
        int validBase = this.baseCtr.calculateValidBaseInt(this.config.getBaseScore());

        // 2. 計算下注籌碼列表
        this.config.getChipsOddsList().forEach(d ->
                result.add(Math.max((int) Math.round(MathUtil.multiply(d, validBase)), 1))
        );

        return result;
    }

    // 計算押注區域限紅對應表
    private Map<Integer, Integer> calculateAreasTopLimitMap() {
        Map<Integer, Integer> result = new HashMap<>();

        this.config.getAreasTopLimitOddsMap().forEach((areaId, topLimitOdds) ->
                result.put(
                        areaId,
                        Math.max((int) Math.round(MathUtil.multiply(topLimitOdds, this.config.getBaseScore())), 1)
                )
        );

        return result;
    }

    public List<Integer> getChipsList() {
        return chipsList;
    }

    public Map<Integer, Integer> getAreasTopLimitMap() {
        return areasTopLimitMap;
    }

    public List<Integer> getAreasTopLimitList(){
        return Arrays.stream(this.transformMapToArray(this.areasTopLimitMap)).boxed().collect(Collectors.toList());
    }

    public Map<Integer, Map<Integer, Integer>> getChairToAreaBetMap() {
        return chairToAreaBetMap;
    }

    public int getAreaCount() {
        return this.config.getBetAreaCount();
    }

    // 重設
    public void reset() {
        this.chipsList = this.calculateChipsList();
        this.areasTopLimitMap = this.calculateAreasTopLimitMap();
        this.chairToAreaBetMap.clear();
    }
}
