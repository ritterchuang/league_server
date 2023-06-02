package org.lsj.gs.math.core.card.vieBankerCtr;

import org.lsj.gs.math.core.AbstractModule;
import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.vieBankerCtr.vieBankerUtil.IVieBankerUtil;
import org.lsj.gs.math.core.card.vieBankerCtr.vieBankerUtil.VieBankerUtilMaxRate;
import org.lsj.gs.math.core.card.vieBankerCtr.vieBankerUtil.VieBankerUtilRateList;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 搶莊計算器
public class VieBankerCtr extends AbstractModule {
    final VieBankerCtrConfig config; // 設定檔
    IVieBankerUtil vieBankerUtil; // 搶莊工具包

    Map<Integer, List<Integer>> canVieRateListMap; // 可搶莊倍數 <玩家座位索引, 玩家搶莊倍數列表>
    Map<Integer, Integer> vieRateListMap; // 搶莊倍數 <玩家座位索引, 玩家搶莊倍數>
    Map<Integer, Integer> timeOutVieRateMap; // 超時搶莊倍數 <玩家座位索引, 玩家搶莊倍數>
    List<Integer> vieCandidateList; // 莊家候選人

    int bankerChairIndex; // 莊家座位
    int bankerRate; // 莊家倍數

    public VieBankerCtr(VieBankerCtrConfig config, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(gamePlayerHlr, poolCtr, tableUtil);
        this.config = config;
        this.vieBankerUtil = this.create(config.getBankerType());
        this.canVieRateListMap = this.vieBankerUtil.calculateCanVieRateListMap(gamePlayerHlr, config);

        this.vieRateListMap = new HashMap<>();
        this.timeOutVieRateMap = new HashMap<>();
        this.vieCandidateList = new ArrayList<>();
        this.bankerChairIndex = -1;
        this.bankerRate = -1;
    }

    //* 可搶莊倍數相關 *//

    // 搶莊工具包實體建置
    private IVieBankerUtil create(ConstMathCard.BankerType bankerType) {
        switch (bankerType) {
            case MAX_RATE: return new VieBankerUtilMaxRate();
            default: return new VieBankerUtilRateList();
        }
    }

    // 取得指定玩家可搶莊倍數列表
    public List<Integer> getPlayerCanVieRateList(int chairIndex) { return this.canVieRateListMap.get(chairIndex); }

    // 取得真人可搶莊倍數列表
    public int[] getHumanCanVieRateArray() {
        return this.getPlayerCanVieRateList(super.gamePlayerHlr.getHumanChairIndex()).stream()
                .mapToInt(canCieRate -> canCieRate).toArray();
    }


    //* 接收搶莊訊息相關 *//

    // 判斷重複搶莊
    public boolean isPlayerVied(int chairIndex){
        return !Objects.isNull(this.vieRateListMap.get(chairIndex));
    }

    // 判斷合法搶莊倍數
    public boolean isValidVieRate ( int chairIndex, int rate){
        if (Objects.isNull(this.canVieRateListMap.get(chairIndex))) { return false;}

        return this.canVieRateListMap.get(chairIndex).stream().anyMatch(canVieRate -> canVieRate == rate);
    }

    // 更新搶莊倍數
    public void setPlayerVieRate(int chairIndex, int rate) {
        this.vieRateListMap.put(chairIndex, rate);
    }


    //* 結束搶莊相關 *//

    // 是否完成搶莊
    public boolean isFinishVie() {
        return this.vieRateListMap.size() == super.gamePlayerHlr.getPlayerCount();
    }

    // 結束搶莊
    public void finishVie() {
        // 1. 未搶莊給予倍數並記錄
        super.gamePlayerHlr.getAllGamePlayerMap().keySet().stream()
                .filter(chairIndex -> Objects.isNull(this.vieRateListMap.get(chairIndex)))
                .forEach(entry -> this.timeOutVieRateMap.put(entry, this.canVieRateListMap.get(entry).get(0))
        );

        // 2. 紀錄未搶玩家倍數設定
        this.vieRateListMap.putAll(this.timeOutVieRateMap);

        // 3. 決定最大倍數
        int maxVieRate = this.vieRateListMap.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).get().getValue();

        // 4. 決定莊家位置
        int bankChair = this.vieBankerUtil.calculateBankChair(this.vieRateListMap, this.config, super.gamePlayerHlr, super.tableUtil);

        // 5. 更換倍數
        int bankerRate = this.vieBankerUtil.calculateBankerRate(maxVieRate, this.config);

        // 6. 設定莊家動畫表演列表
        this.calculateVieCandidateList(maxVieRate, this.vieRateListMap);

        // 7. 設定莊家結果
        this.setBankerResult(bankChair, bankerRate);
    }


    //* 搶莊結果相關 *//

    // 取得莊家位置
    public int getBankerChairIndex () {
        return bankerChairIndex;
    }

    // 取得莊家倍數
    public int getBankerRate () {
        return bankerRate;
    }

    // 是否為莊家
    public boolean isBankerChairIndex(int chairIndex) { return chairIndex == this.bankerChairIndex; }

    // 取得所有玩家搶莊倍數列表
    public int[] getVieRateArray() {
        // 1. 創建空間
        List<Integer> result = new ArrayList<>();
        Stream.iterate(-1, i -> -1).limit(this.config.getMaxUser()).forEach(result::add);

        // 2. 設定值
        this.vieRateListMap.forEach(result::set);

        return result.stream().mapToInt(value -> value).toArray();
    }

    // 取得真人最大搶莊倍數
    public int getHumanMaxVieRate() {
        List<Integer> humanCanVieRates = this.canVieRateListMap.get(super.gamePlayerHlr.getHumanChairIndex());

        return humanCanVieRates.get(humanCanVieRates.size() - 1);
    }

    // 設定莊家結果
    private void setBankerResult(int bankChair, int bankerRate) {
        this.bankerChairIndex = bankChair;
        this.bankerRate = bankerRate;
    }



    //* 超時搶莊相關 *//

    // 取得超時表演列表
    public Map<Integer, Integer> getTimeOutVieRateMap() {
        return timeOutVieRateMap;
    }


    //* 候選搶莊相關 *//

    // 是否表演候選搶莊
    public boolean isShowVieBankAnimation() {
        return this.vieCandidateList.size() > 1;
    }

    // 取得搶莊候選名單
    public int[] getVieCandidateArrayMessage() { return vieCandidateList.stream().mapToInt(chairIndex -> chairIndex).toArray(); }

    // 計算搶莊候選列表
    private void calculateVieCandidateList(int maxVieRate, Map<Integer, Integer> vieRateListMap) {
        // 1. 沒人搶莊，全部表演
        if (maxVieRate == 0) {
            this.vieCandidateList = new ArrayList<>(vieRateListMap.keySet());
            return;
        }

        // 2. 只要有搶，就表演
        this.vieCandidateList = vieRateListMap.entrySet().stream().filter(entry -> entry.getValue() > 0).map(Map.Entry::getKey).collect(Collectors.toList());
    }

    // 重設
    @Override
    public void reset() {
        this.canVieRateListMap = this.vieBankerUtil.calculateCanVieRateListMap(super.gamePlayerHlr, this.config);
        this.vieRateListMap.clear();
        this.timeOutVieRateMap.clear();
        this.vieCandidateList.clear();
        this.bankerChairIndex = -1;
        this.bankerRate = -1;
    }
}
