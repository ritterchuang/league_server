package org.lsj.gs.math.core.card.stackCtr.ebgStackCtr;

import org.lsj.gs.math.core.AbstractModule;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.AbstractStack;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 二八槓牌型計算器
public class EbgStackCtr extends AbstractModule {
    private final EbgStackCtrConfig config; // 牌型計算器設定檔
    private Map<Integer, AbstractStack> selectResult; // 選牌結果 <座位索引, 玩家選牌結果>
    private final EbgTypeUtil ebgTypeUtil; // 三公牌型工具包

    public EbgStackCtr(EbgStackCtrConfig config, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(gamePlayerHlr, poolCtr, tableUtil);
        this.config = config;
        this.ebgTypeUtil = new EbgTypeUtil();

        this.reset();
    }

    // 計算二八槓牌型結果
    public EbgStack calculateEbgStack(List<ICard> cardList) {
        ConstEbgStack.EbgTypeEnumCommon ebgTypeEnumCommon = this.calculateEbgTypeEnumCommon(cardList);
        return new EbgStack(cardList, ebgTypeEnumCommon);
    }

    // 計算二八槓牌型
    private ConstEbgStack.EbgTypeEnumCommon calculateEbgTypeEnumCommon(List<ICard> cardList) {
        // 1. 計算寶牌
        if (this.ebgTypeUtil.checkBaoType(cardList)) {
            return this.ebgTypeUtil.calculateBaoType(cardList);
        }

        // 2. 計算白板牌
        if (this.ebgTypeUtil.checkBaiBan(cardList)) {
            return this.ebgTypeUtil.calculateHalfType(cardList);
        }

        // 3. 計算一般牌
        return this.ebgTypeUtil.calculateCommonType(cardList);
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
                forEach(chairIndex -> this.selectResult.put(chairIndex, this.calculateEbgStack(playerHandCardList.get(chairIndex))));
    }


    /* 選牌相關 */

    // 取得所有玩家選牌結果
    public Map<Integer, AbstractStack> getAllPlayerSelectResult() { return this.selectResult; }

    // 取得所有玩家選牌卡牌
    public Map<Integer, List<ICard>> getAllPlayerSelectCardList() {
        return this.selectResult.keySet()
                .stream()
                .collect(Collectors.toMap(
                        chair -> chair,
                        chair -> this.selectResult.get(chair).getHandCardList()));
    }

    // 取得所有玩家牌型 TODO
    public int[] getAllPlayerSelectTypeArray() {
        // 1. 建立空間
        List<ConstEbgStack.EbgTypeEnumCommon> playerSgTypeList = new ArrayList<>();
        Stream.iterate(0, i -> 0).limit(this.config.getMaxUser()).forEach((i -> playerSgTypeList.add(ConstEbgStack.EbgTypeEnumCommon.INVALID)));

        // 2. 設定值
        this.selectResult.keySet().forEach(chair -> playerSgTypeList.set(chair, ((EbgStack)this.selectResult.get(chair)).getEbgTypeEnumCommon()));

        return playerSgTypeList.stream().mapToInt(ConstEbgStack.EbgTypeEnumCommon::getType).toArray();
    }

    // 取得指定位置的選牌牌型
    public int getPlayerSelectType(int chairIndex) {
        if (Objects.isNull(this.selectResult.get(chairIndex))) {
            return ConstEbgStack.EbgTypeEnumCommon.INVALID.getType();
        }

        return ((EbgStack)this.selectResult.get(chairIndex)).getEbgTypeEnumCommon().getType();
    }

    // 設定指定位置的選牌結果
    public void setPlayerSelectResult(int chairIndex, EbgStack ebgStack) { this.selectResult.put(chairIndex, ebgStack); }

    // 取得真人選牌結果
    public EbgStack getHumanPlayerSelectResult() { return (EbgStack) this.selectResult.get(super.gamePlayerHlr.getHumanChairIndex()); }

    // 計算每個位置最大牌型
    public Map<Integer, AbstractStack> calculateEbgStack(Map<Integer, List<ICard>> allPlayerCardList) {
        return allPlayerCardList.keySet().stream().collect(Collectors.toMap(
                chairIndex -> chairIndex,
                chairIndex -> this.calculateEbgStack(allPlayerCardList.get(chairIndex))));
    }


    @Override
    public void reset() {
        this.selectResult = new HashMap<>();
    }
}
