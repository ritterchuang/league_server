package org.lsj.gs.math.core.card.vieBankerCtr.vieBankerUtil;

import org.lsj.gs.math.core.card.vieBankerCtr.VieBankerCtrConfig;
import org.lsj.gs.math.core.card.vieBankerCtr.VieBankerCtrConfigMaxRate;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 搶莊工具包 搶莊二八槓
public class VieBankerUtilMaxRate implements IVieBankerUtil{

    // 計算所有玩家可搶莊倍數列表
    @Override
    public Map<Integer, List<Integer>> calculateCanVieRateListMap(GamePlayerHlr gamePlayerHlr, VieBankerCtrConfig config) {
        return gamePlayerHlr.getAllGamePlayerMap().keySet().stream().collect(Collectors.toMap(
                chairIndex -> chairIndex, chairIndex -> this.calculateCanVieRateListByPlayer(gamePlayerHlr, config, chairIndex)
        ));
    }

    // 計算莊家位置
    @Override
    public int calculateBankChair(Map<Integer, Integer> vieRateListMap, VieBankerCtrConfig config, GamePlayerHlr gamePlayerHlr, ITableUtil tableUtil) {
        // 1. 計算當前最大倍數
        int maxVieRate = vieRateListMap.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).get().getValue();

        // 2. 整理與最大倍數相同名單
        List<Integer> candidateList = vieRateListMap.entrySet().stream().filter(entry -> entry.getValue() == maxVieRate).map(Map.Entry::getKey).collect(Collectors.toList());

        // 3. 只有一個，直接回傳
        if (candidateList.size() == 1) {
            return candidateList.get(0);
        }

        // 4. 真人搶三倍則必為莊
        VieBankerCtrConfigMaxRate configEbg = (VieBankerCtrConfigMaxRate) config;
        int maxConfigRate = configEbg.getMaxVieRate();
        if (maxVieRate == maxConfigRate) {
            if (candidateList.stream().noneMatch(i -> i == gamePlayerHlr.getHumanChairIndex())) {
                return candidateList.get(tableUtil.getMainRandomUtil().getRandomIntWithAccuracy(candidateList.size(), ConstMathCommon.AccuracyType.A32768));
            } else {
                return gamePlayerHlr.getHumanChairIndex();
            }
        }

        // 5. 真人不搶則必不為莊
        if (maxVieRate < configEbg.getMinVieRate()) {
            if (candidateList.stream().noneMatch(i -> i == gamePlayerHlr.getHumanChairIndex())) {
                return candidateList.get(tableUtil.getMainRandomUtil().getRandomIntWithAccuracy(candidateList.size(), ConstMathCommon.AccuracyType.A32768));
            } else {
                List<Integer> filterVieResult = candidateList.stream().filter(i -> i != gamePlayerHlr.getHumanChairIndex()).collect(Collectors.toList());
                return filterVieResult.get(tableUtil.getMainRandomUtil().getRandomIntWithAccuracy(filterVieResult.size(), ConstMathCommon.AccuracyType.A32768));
            }
        }

        // 6. 隨機決定莊家
        return candidateList.get(tableUtil.getMainRandomUtil().getRandomIntWithAccuracy(candidateList.size(), ConstMathCommon.AccuracyType.A32768));
    }

    // 計算莊家倍數
    @Override
    public int calculateBankerRate(int maxVieRate, VieBankerCtrConfig config) {
        VieBankerCtrConfigMaxRate configEbg = (VieBankerCtrConfigMaxRate) config;

        return (maxVieRate == 0) ? (configEbg.getMinVieRate() * configEbg.getVieRateGroup()) : maxVieRate;
    }

    // 計算指定玩家可搶莊列表
    private List<Integer> calculateCanVieRateListByPlayer(GamePlayerHlr gamePlayerHlr, VieBankerCtrConfig config, int chairIndex){
        VieBankerCtrConfigMaxRate configEbg = (VieBankerCtrConfigMaxRate) config;

        // 1. 取得設定
        double money = gamePlayerHlr.getPlayerBeginMoney(chairIndex);
        double baseScore = configEbg.getBaseScore();
        int minVieRate = configEbg.getMinVieRate() * configEbg.getVieRateGroup();
        int maxVieRate = Math.min(configEbg.getMaxVieRate(), (int)(money / baseScore));

        // 2. 計算搶莊門檻
        List<Integer> rateThresholdList = this.calculateVieRateThresholdList(maxVieRate, minVieRate, configEbg.getVieRateGroup());

        // 3. 計算押莊列表
        List<Integer> result =  this.calculateCanVieRateList(maxVieRate, minVieRate, rateThresholdList, money);
        return result;
    }

    // 計算搶莊門檻列表
    private List<Integer> calculateVieRateThresholdList(int maxVieRate, int minVieRate, int vieRateGroup) {
        List<Integer> result = new ArrayList<>();

        for (int index = 0; index < vieRateGroup; index++) {
            result.add((index * (maxVieRate - minVieRate) / vieRateGroup) + minVieRate);
        }

        return result;
    }

    // 計算搶莊列表
    private List<Integer> calculateCanVieRateList(int maxVieRate, int minVieRate, List<Integer> rateThresholdList, double money) {
        List<Integer> result = new ArrayList<>();

        result.add(0);

        if (money >= minVieRate) {
            result.add(minVieRate);
        }

        if (rateThresholdList.get(1) > minVieRate) {
            result.add(rateThresholdList.get(1));
        }

        if (rateThresholdList.get(2) > minVieRate && rateThresholdList.get(2) > rateThresholdList.get(1)) {
            result.add(rateThresholdList.get(2));
        }

        if (maxVieRate > minVieRate && maxVieRate > rateThresholdList.get(2)) {
            result.add(maxVieRate);
        }

        return result;
    }
}
