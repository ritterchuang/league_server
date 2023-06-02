package org.lsj.gs.math.core.card.stackCtr.niuStackCtr.brNiuStackCtr;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.AbstractStack;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.*;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 百人牛型計算器
public class NiuBaiRenStackCtr extends AbstractNiuStackCtr {
    private final NiuBaiRenStackCtrConfig config; // 設定檔

    public NiuBaiRenStackCtr(NiuBaiRenStackCtrConfig config, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(gamePlayerHlr, poolCtr, tableUtil);
        this.config = config;
    }

    // 計算選牌結果
    public AbstractNiuStack calculateNiuStack(List<ICard> cardList) {
        // 1. 判斷是否有牛並選出前三張
        PreNiuResult preNiuResult = super.cardTypeUtilNiu.calculatePreNiuResult(cardList);

        // 2. 決定牌型
        ConstNiu.NiuTypeEnumCommon niuTypeEnumCommon = super.calculateNiuTypeByPreNiuResult(preNiuResult, this.config.getNiuTypeToRateConfig());

        // 3. 封裝結果
        return new NiuStackCommon(preNiuResult.getNiuCardList(), preNiuResult.getLiftCardList(), niuTypeEnumCommon, preNiuResult.isNiu());
    }

    // 計算區域牌型對應表
    public Map<Integer, AbstractStack> calculateStackMap(Map<Integer, List<ICard>> unTakenCardListMap) {
        return unTakenCardListMap
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey, entry -> this.calculateNiuStack(entry.getValue())
                ));
    }

    // 計算各區域提牌結果
    public Map<Integer, List<ICard>> getAreaLiftCardList() {
        return super.selectResult.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey, entry -> ((NiuStackCommon)entry.getValue()).getLiftCardList()
        ));
    }

    // 計算各區域牛牛共用牌型
    public int[] getAreaNiuTypeCommonArray() {
        return IntStream
                .range(0, super.selectResult.size())
                .map(areaId ->
                        ((NiuStackCommon) super.selectResult.get(areaId)).getNiuTypeEnumCommon().getType())
                .toArray();
    }

    // 設定區域牛牛牌型
    public void setStackMap(Map<Integer, AbstractStack> stackMap) {
        this.selectResult = stackMap;
    }

    public Map<Integer, AbstractStack> getStackMap() {
        return super.selectResult;
    }

    // 重設
    @Override
    public void reset() {
        super.reset();
    }
}
