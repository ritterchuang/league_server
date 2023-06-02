package org.lsj.gs.math.core.card.stackCtr.pjStackCtr;

import org.lsj.gs.math.core.AbstractModule;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.AbstractStack;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 牌九牌型計算器
public class PjStackCtr extends AbstractModule {
    private final PjStackCtrConfig config; // 牌型計算器設定檔
    private final PjTypeUtil pjTypeUtil; // 三公牌型工具包
    private Map<Integer, AbstractStack> selectResult; // 選牌結果 <座位索引, 玩家選牌結果>

    public PjStackCtr(PjStackCtrConfig config, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(gamePlayerHlr, poolCtr, tableUtil);
        this.config = config;
        this.pjTypeUtil = new PjTypeUtil();

        this.reset();
    }

    // 計算牌九 牌型結果
    public PjStack calculatePjStack(List<ICard> cardList) {
        ConstPjStack.PjTypeEnumCommon pjTypeEnumCommon = this.calculatePjTypeEnumCommon(cardList);
        return new PjStack(cardList, pjTypeEnumCommon);
    }

    // 計算牌型
    private ConstPjStack.PjTypeEnumCommon calculatePjTypeEnumCommon(List<ICard> cardList) {
        // 1. 計算對牌結果
        ConstPjStack.PjTypeEnumCommon pjComboType = this.pjTypeUtil.calculatePjComboType(cardList);
        if (pjComboType.getPjClassTypeEnumCommon().equals(ConstPjStack.PjClassTypeEnumCommon.COMBO)) {
            return pjComboType;
        }

        // 2. 計算單牌結果
        return this.pjTypeUtil.calculatePjSingleType(cardList);
    }


    /* 接收選牌訊息相關 */

    // 玩家是否選過
    public boolean isPlayerSelect(int chairIndex) {
        return !Objects.isNull(this.selectResult.get(chairIndex));
    }


    /* 結束選牌相關 */

    // 是否選擇完畢
    public boolean isFinishSelect() {
        return this.selectResult.size() == super.gamePlayerHlr.getPlayerCount();
    }

    // 結束選擇
    public void finishSelect(Map<Integer, List<ICard>> playerHandCardList) {
        super.gamePlayerHlr.getAllGamePlayerChairIndexList().stream().filter(chairIndex -> Objects.isNull(this.selectResult.get(chairIndex))).collect(Collectors.toList()).
                forEach(chairIndex -> this.selectResult.put(chairIndex, this.calculatePjStack(playerHandCardList.get(chairIndex))));
    }


    /* 選牌相關 */

    // 取得所有玩家選牌結果
    public Map<Integer, AbstractStack> getAllPlayerSelectResult() { return this.selectResult; }

    // 取得所有玩家選牌卡牌
    public Map<Integer, List<ICard>> getAllPlayerSelectCardList() {
        Map<Integer, List<ICard>> result = new HashMap<>();
        this.selectResult.keySet().forEach(chair -> result.put(chair, this.selectResult.get(chair).getHandCardList()));
        return result;
    }

    // 取得所有玩家牌型
    public int[] getAllPlayerSelectTypeArray() {
        // 1. 建立空間
        List<ConstPjStack.PjTypeEnumCommon> playerSgTypeList = new ArrayList<>();
        Stream.iterate(0, i -> 0).limit(this.config.getMaxUser()).forEach((i -> playerSgTypeList.add(ConstPjStack.PjTypeEnumCommon.INVALID)));

        // 2. 設定值
        this.selectResult.keySet().forEach(chair -> playerSgTypeList.set(chair, ((PjStack)this.selectResult.get(chair)).getPjTypeEnumCommon()));

        return playerSgTypeList.stream().mapToInt(ConstPjStack.PjTypeEnumCommon::getType).toArray();
    }

    // 取得指定位置的選牌牌型
    public int getPlayerSelectType(int chairIndex) {
        if (Objects.isNull(this.selectResult.get(chairIndex))) {
            return ConstPjStack.PjTypeEnumCommon.INVALID.getType();
        }

        return ((PjStack)this.selectResult.get(chairIndex)).getPjTypeEnumCommon().getType();
    }

    // 設定指定位置的選牌結果
    public void setPlayerSelectResult(int chairIndex, PjStack pjStack) { this.selectResult.put(chairIndex, pjStack); }

    // 取得真人選牌結果
    public PjStack getHumanPlayerSelectResult() { return (PjStack) this.selectResult.get(super.gamePlayerHlr.getHumanChairIndex()); }

    // 計算每個位置最大牌型
    public Map<Integer, AbstractStack> calculatePjStack(Map<Integer, List<ICard>> allPlayerCardList) {
        return allPlayerCardList.keySet().stream().collect(Collectors.toMap(
                chairId -> chairId, chairId -> this.calculatePjStack(allPlayerCardList.get(chairId))
        ));
    }


    @Override
    public void reset() {
        this.selectResult = new HashMap<>();
    }
}
