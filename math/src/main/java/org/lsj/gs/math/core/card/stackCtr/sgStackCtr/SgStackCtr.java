package org.lsj.gs.math.core.card.stackCtr.sgStackCtr;

import org.lsj.gs.math.core.AbstractModule;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.AbstractStack;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 三公牌型計算器
public class SgStackCtr extends AbstractModule {
    private final SgStackCtrConfig config; // 牌型計算器設定檔
    private final SgTypeUtil sgTypeUtil; // 三公牌型工具包
    private Map<Integer, AbstractStack> selectResult; // 選牌結果 <座位索引, 玩家選牌結果>

    public SgStackCtr(SgStackCtrConfig config, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(gamePlayerHlr, poolCtr, tableUtil);
        this.config = config;
        this.sgTypeUtil = new SgTypeUtil();

        this.reset();
    }

    // 計算三公牌型結果
    public SgStack calculateSgStack(List<ICard> cardList) {
        ConstSgStack.SgTypeEnumCommon sgTypeEnumCommon = this.calculateSgType(cardList);
        return new SgStack(cardList, sgTypeEnumCommon);
    }

    // 計算三公牌型
    private ConstSgStack.SgTypeEnumCommon calculateSgType(List<ICard> cardList) {
        // 1. 是否至尊
        if (this.sgTypeUtil.isZhiZun(cardList)) {
            return ConstSgStack.SgTypeEnumCommon.ZHIZUN;
        }

        // 2. 是否大三公
        if (this.sgTypeUtil.isDaSanGong(cardList)) {
            return ConstSgStack.SgTypeEnumCommon.DASANGONG;
        }

        // 3. 是否三公
        if (this.sgTypeUtil.isSanGong(cardList)) {
            return ConstSgStack.SgTypeEnumCommon.SANGONG;
        }

        // 4. 計算點數
        return ConstSgStack.SgTypeEnumCommon.calculateSgCount(this.sgTypeUtil.calculateCardPointSumModuleBy10(cardList));
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
                forEach(chairIndex -> this.selectResult.put(chairIndex, this.calculateSgStack(playerHandCardList.get(chairIndex))));
    }


    /* 選牌相關 */

    // 取得所有玩家選牌結果
    public Map<Integer, AbstractStack> getAllPlayerSelectResult() { return this.selectResult; }

    // 取得所有玩家選牌卡牌
    public Map<Integer, List<ICard>> getAllPlayerSelectCardList() {
        return this.selectResult.keySet().stream().collect(Collectors.toMap(
                chairId -> chairId, chairId -> this.selectResult.get(chairId).getHandCardList()
        ));
    }

    // 取得所有玩家牌型 TODO
    public int[] getAllPlayerSelectTypeArray() {
        // 1. 建立空間
        List<ConstSgStack.SgTypeEnumCommon> playerSgTypeList = new ArrayList<>();
        Stream.iterate(0, i -> 0).limit(this.config.getMaxUser()).forEach((i -> playerSgTypeList.add(ConstSgStack.SgTypeEnumCommon.NONE)));

        // 2. 設定值
        this.selectResult.keySet().forEach(chair -> playerSgTypeList.set(chair, ((SgStack)this.selectResult.get(chair)).getSgTypeEnumCommon()));

        return playerSgTypeList.stream().mapToInt(ConstSgStack.SgTypeEnumCommon::getType).toArray();
    }

    // 取得指定位置的選牌牌型
    public int getPlayerSelectType(int chairIndex) {
        if (Objects.isNull(this.selectResult.get(chairIndex))) {
            return ConstSgStack.SgTypeEnumCommon.NONE.getType();
        }

        return ((SgStack)this.selectResult.get(chairIndex)).getSgTypeEnumCommon().getType();
    }

    // 設定指定位置的選牌結果
    public void setPlayerSelectResult(int chairIndex, SgStack sgStack) { this.selectResult.put(chairIndex, sgStack); }

    // 取得真人選牌結果
    public SgStack getHumanPlayerSelectResult() { return (SgStack) this.selectResult.get(super.gamePlayerHlr.getHumanChairIndex()); }

    // 計算每個位置最大牌型
    public Map<Integer, AbstractStack> calculateSgStack(Map<Integer, List<ICard>> allPlayerCardList) {
        return allPlayerCardList.keySet().stream().collect(Collectors.toMap(
                chairId -> chairId, chairId -> this.calculateSgStack(allPlayerCardList.get(chairId))
        ));
    }


    @Override
    public void reset() {
        this.selectResult = new HashMap<>();
    }
}
