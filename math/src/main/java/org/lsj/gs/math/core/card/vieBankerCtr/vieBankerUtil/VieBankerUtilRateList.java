package org.lsj.gs.math.core.card.vieBankerCtr.vieBankerUtil;

import org.lsj.gs.math.core.card.vieBankerCtr.VieBankerCtrConfig;
import org.lsj.gs.math.core.card.vieBankerCtr.VieBankerCtrConfigRateList;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 搶莊工具包 搶莊牛牛
public class VieBankerUtilRateList implements IVieBankerUtil{

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
        VieBankerCtrConfigRateList configNn = (VieBankerCtrConfigRateList) config;
        int maxConfigRate = configNn.getVieRateList().get(configNn.getVieRateList().size() - 1);
        if (maxVieRate == maxConfigRate) {
            if (candidateList.stream().noneMatch(i -> i == gamePlayerHlr.getHumanChairIndex())) {
                return candidateList.get(tableUtil.getMainRandomUtil().getRandomIntWithAccuracy(candidateList.size(), ConstMathCommon.AccuracyType.A32768));
            } else {
                return gamePlayerHlr.getHumanChairIndex();
            }
        }

        // 5. 真人不搶則必不為莊
        if (maxVieRate == configNn.getVieRateList().get(0)) {
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
        return (maxVieRate == 0) ? 1 : maxVieRate;
    }

    // 計算指定玩家可搶莊列表
    private List<Integer> calculateCanVieRateListByPlayer(GamePlayerHlr gamePlayerHlr, VieBankerCtrConfig config, int chairIndex){
        VieBankerCtrConfigRateList configNn = (VieBankerCtrConfigRateList) config;

        // 1. 取得設定
        double money = gamePlayerHlr.getPlayerBeginMoney(chairIndex);
        double baseScore = configNn.getBaseScore();
        List<Integer> vieRateList = configNn.getVieRateList();
        List<Integer> vieThreshHold = configNn.getVieThresholdList();

        // 2. 計算押莊列表
        List<Integer> result = new ArrayList<>();
        for (int vieRateIndex = 0; vieRateIndex < vieRateList.size(); vieRateIndex++) {
            if (baseScore * vieThreshHold.get(vieRateIndex) <= money) {
                result.add(vieRateList.get(vieRateIndex));
            }else {
                break;
            }
        }

        // 3. 回傳
        return result;
    }


}
