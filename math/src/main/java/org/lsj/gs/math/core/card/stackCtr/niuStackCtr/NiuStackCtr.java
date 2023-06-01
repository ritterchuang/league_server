package org.lsj.gs.math.core.card.stackCtr.niuStackCtr;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.AbstractStack;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu.NiuTypeEnumCommon;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 牛型計算器
public class NiuStackCtr extends AbstractNiuStackCtr {
    private final NiuStackCtrConfig config; // 設定檔

    public NiuStackCtr(NiuStackCtrConfig config, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(gamePlayerHlr, poolCtr, tableUtil);
        this.config = config;
    }

    /* 接收選牌訊息相關 */
    // 玩家是否選過
    public boolean isPlayerSelect(int chairIndex) {
        return !Objects.isNull(super.selectResult.get(chairIndex));
    }

    // 取得已選牌玩家座位索引
    public List<Integer> getSelectedPlayerList() {
        return super.gamePlayerHlr.getAllGamePlayerChairIndexList().stream().filter(i -> !Objects.isNull(super.selectResult.get(i))).collect(Collectors.toList());
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
        Map<Integer, List<ICard>> result = new HashMap<>();
        super.selectResult.keySet().forEach(chair -> result.put(chair, super.selectResult.get(chair).getHandCardList()));
        return result;
    }

    // 取得所有玩家牌型
    public int[] getAllPlayerSelectTypeArray() {
        // 1. 建立空間
        List<NiuTypeEnumCommon> playerNiuTypeList = new ArrayList<>();
        Stream.iterate(0, i -> 0).limit(this.config.getMaxUser()).forEach((i -> playerNiuTypeList.add(NiuTypeEnumCommon.INVALID)));

        // 2. 設定值
        super.selectResult.keySet().forEach(chair -> playerNiuTypeList.set(chair, ((NiuStackCommon) super.selectResult.get(chair)).getNiuTypeEnumCommon()));

        return playerNiuTypeList.stream().mapToInt(NiuTypeEnumCommon::getType).toArray();
    }

    // 設定指定位置的選牌結果
    public void setPlayerSelectResult(int chairIndex, AbstractNiuStack abstractNiuStack) { super.selectResult.put(chairIndex, abstractNiuStack); }

    // 取得指定位置的選牌牌型
    public int getPlayerSelectType(int chairIndex) {
        if (Objects.isNull(super.selectResult.get(chairIndex))) {
            return NiuTypeEnumCommon.INVALID.getType();
        }

        return ((NiuStackCommon)super.selectResult.get(chairIndex)).getNiuTypeEnumCommon().getType();
    }

    // 取得真人選牌結果
    public NiuStackCommon getHumanPlayerSelectResult() { return (NiuStackCommon) super.selectResult.get(super.gamePlayerHlr.getHumanChairIndex()); }

    // 取得真人選牌牌型
    public int getHumanPlayerSelectType() {
        if (Objects.isNull(super.selectResult.get(super.gamePlayerHlr.getHumanChairIndex()))) {
            return NiuTypeEnumCommon.INVALID.getType();
        }

        return ((NiuStackCommon)super.selectResult.get(super.gamePlayerHlr.getHumanChairIndex())).getNiuTypeEnumCommon().getType();
    }

    // 計算選牌結果
    public AbstractNiuStack calculateNiuStack(List<ICard> cardList) {
        // 1. 判斷是否有牛並選出前三張
        PreNiuResult preNiuResult = super.cardTypeUtilNiu.calculatePreNiuResult(cardList);

        // 2. 決定牌型
        NiuTypeEnumCommon niuTypeEnumCommon = super.calculateNiuTypeByPreNiuResult(preNiuResult, this.config.getNiuTypeRateConfig());

        // 3. 封裝結果
        return new NiuStackCommon(preNiuResult.getNiuCardList(), preNiuResult.getLiftCardList(), niuTypeEnumCommon, preNiuResult.isNiu());
    }

    // 是否為最大牌型
    public boolean isMaxStack(List<ICard> cardList) {
        // 1. 計算玩家傳入結果
        NiuTypeEnumCommon playerChooseType = this.calculatePlayerChoseNiuType(cardList);

        // 2. 計算最佳結果
        NiuTypeEnumCommon bestResultType = this.calculateNiuStack(cardList).getNiuTypeEnumCommon();

        return playerChooseType == bestResultType;
    }

    // 計算牌型
    private NiuTypeEnumCommon calculatePlayerChoseNiuType(List<ICard> cardList) {
        // 1. 取前三張算牛
        int niuPoint = this.cardTypeUtilNiu.calculateCardPointSumModuleBy10(cardList.subList(0,3));

        // 2. 計算是否有牛
        boolean isNiu = (niuPoint == 0);

        // 3. 封裝預算牛結構
        PreNiuResult playerResult = new PreNiuResult();
        playerResult.setNiu(isNiu);
        playerResult.setNiuCardList(cardList);

        // 4. 回傳
        return super.calculateNiuTypeByPreNiuResult(playerResult, this.config.getNiuTypeRateConfig());
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
