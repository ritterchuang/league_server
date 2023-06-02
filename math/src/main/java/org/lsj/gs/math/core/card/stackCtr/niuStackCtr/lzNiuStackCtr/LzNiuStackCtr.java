package org.lsj.gs.math.core.card.stackCtr.niuStackCtr.lzNiuStackCtr;

import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtr;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.AbstractStack;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.AbstractNiuStack;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.AbstractNiuStackCtr;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu.NiuTypeEnumCommon;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.NiuStackCtrConfig;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.PreNiuResult;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 賴子牛型計算器
public class LzNiuStackCtr extends AbstractNiuStackCtr {
    private final NiuStackCtrConfig config; // 設定檔
    private final CardWallCtr cardWallCtr; // 牌牆計算器
    private final LaiziCardUtil laiziCardUtil; // 賴子工具包

    public LzNiuStackCtr(NiuStackCtrConfig config, CardWallCtr cardWallCtr, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(gamePlayerHlr, poolCtr, tableUtil);
        this.config = config;
        this.cardWallCtr = cardWallCtr;
        this.laiziCardUtil = new LaiziCardUtil();
    }

    /* 接收選牌訊息相關 */
    // 玩家是否選過
    public boolean isPlayerSelect(int chairIndex) {
        return !Objects.isNull(super.selectResult.get(chairIndex));
    }

    // 取得已選牌玩家座位索引
    public List<Integer> getSelectedPlayerList() {
        return super.gamePlayerHlr
                .getAllGamePlayerChairIndexList()
                .stream()
                .filter(chairIndex -> !Objects.isNull(super.selectResult.get(chairIndex)))
                .collect(Collectors.toList());
    }


    /* 結束選牌相關 */
    // 是否選擇完畢
    public boolean isFinishSelect() {
        return super.selectResult.size() == super.gamePlayerHlr.getPlayerCount();
    }

    // 結束選擇
    public void finishSelect(Map<Integer, List<ICard>> playerHandCardList) {
        super.gamePlayerHlr.getAllGamePlayerChairIndexList().stream().filter(chairIndex -> Objects.isNull(super.selectResult.get(chairIndex))).collect(Collectors.toList()).
                forEach(chairIndex -> super.selectResult.put(chairIndex, this.calculateNiuStack(playerHandCardList.get(chairIndex))));
    }


    /* 選牌相關 */
    // 取得所有玩家選牌結果
    public Map<Integer, AbstractStack> getAllPlayerSelectResult() { return super.selectResult; }

    // 取得所有玩家選牌卡牌
    public Map<Integer, List<ICard>> getAllPlayerSelectCardList() {
        return super.selectResult.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey, entry -> super.selectResult.get(entry.getKey()).getHandCardList()
        ));
    }

    // 取得所有玩家牌型
    public int[] getAllPlayerSelectTypeArray() {
        // 1. 建立空間
        List<NiuTypeEnumCommon> playerNiuTypeList = new ArrayList<>();
        Stream.iterate(0, i -> 0).limit(this.config.getMaxUser()).forEach((i -> playerNiuTypeList.add(NiuTypeEnumCommon.INVALID)));

        // 2. 設定值
        super.selectResult.keySet().forEach(chair -> playerNiuTypeList.set(chair, ((AbstractNiuStack) super.selectResult.get(chair)).getNiuTypeEnumCommon()));

        return playerNiuTypeList.stream().mapToInt(NiuTypeEnumCommon::getType).toArray();
    }

    // 設定指定位置的選牌結果
    public void setPlayerSelectResult(int chairIndex, AbstractNiuStack abstractNiuStack) { super.selectResult.put(chairIndex, abstractNiuStack); }

    // 取得指定位置的選牌牌型
    public int getPlayerSelectType(int chairIndex) {
        if (Objects.isNull(super.selectResult.get(chairIndex))) {
            return NiuTypeEnumCommon.INVALID.getType();
        }

        return ((AbstractNiuStack)super.selectResult.get(chairIndex)).getNiuTypeEnumCommon().getType();
    }

    // 取得真人選牌結果
    public AbstractNiuStack getHumanPlayerSelectResult() { return (AbstractNiuStack) super.selectResult.get(super.gamePlayerHlr.getHumanChairIndex()); }

    // 取得真人選牌牌型
    public int getHumanPlayerSelectType() {
        if (Objects.isNull(super.selectResult.get(super.gamePlayerHlr.getHumanChairIndex()))) {
            return NiuTypeEnumCommon.INVALID.getType();
        }

        return ((AbstractNiuStack)super.selectResult.get(super.gamePlayerHlr.getHumanChairIndex())).getNiuTypeEnumCommon().getType();
    }

    // 計算選牌結果
    @Override
    public AbstractNiuStack calculateNiuStack(List<ICard> cardList) {
        // 1. 列出所有可能結果
        List<List<ICard>> laiziPossibleCardList = this.laiziCardUtil.calculatePossibleCardList(cardList, this.cardWallCtr);

        // 2. 計算所有牌型
        List<LzNiuStackCommon> niuStackCommonPossibleList = this.calculateLzNiuStackCommonList(laiziPossibleCardList);

        // 3. 由大至小牌序
        Collections.sort(niuStackCommonPossibleList);
        Collections.reverse(niuStackCommonPossibleList);

        // 4. 回傳
        return niuStackCommonPossibleList.get(0);
    }

    // 計算所有可能結果
    private List<LzNiuStackCommon> calculateLzNiuStackCommonList(List<List<ICard>> laiziExhaustedCardList) {
        List<LzNiuStackCommon> result = new ArrayList<>();

        for (List<ICard> cardList : laiziExhaustedCardList) {
            PreNiuResult preNiuResult = super.cardTypeUtilNiu.calculatePreNiuResult(cardList);
            NiuTypeEnumCommon niuTypeEnumCommon = super.calculateNiuTypeByPreNiuResult(preNiuResult, this.config.getNiuTypeRateConfig());
            result.add(new LzNiuStackCommon(preNiuResult.getNiuCardList(), preNiuResult.getLiftCardList(), niuTypeEnumCommon, preNiuResult.isNiu()));
        }

        return result;
    }

    // 是否為最大牌型 賴子不實做
    public boolean isMaxStack(List<ICard> cardList) {
       return false;
    }


    /* 提牌相關 */
    // 取得所有玩家提牌
    public Map<Integer, List<ICard>> getAllPlayerLiftCardMap() {
        return super.selectResult.keySet().stream().collect(Collectors.toMap(
                chairIndex -> chairIndex,
                chairIndex -> ((AbstractNiuStack)super.selectResult.get(chairIndex)).getLiftCardList())
        );
    }

    // 取得指定位置的提牌
    public List<ICard> getPlayerLiftCardList(int chairIndex) {
        return ((AbstractNiuStack)super.selectResult.get(chairIndex)).getLiftCardList();
    }

    // 重設
    @Override
    public void reset() {
        super.reset();
    }
}
