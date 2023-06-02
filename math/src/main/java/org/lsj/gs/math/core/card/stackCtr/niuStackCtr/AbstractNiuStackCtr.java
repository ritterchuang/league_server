package org.lsj.gs.math.core.card.stackCtr.niuStackCtr;

import org.lsj.gs.math.core.AbstractModule;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.AbstractStack;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 抽象牛型計算器
public abstract class AbstractNiuStackCtr extends AbstractModule {
    protected Map<Integer, AbstractStack> selectResult; // 選牌結果 <座位索引, 玩家選牌結果>
    protected CardTypeUtilNiu cardTypeUtilNiu; // 牛牌型工具包

    public AbstractNiuStackCtr(GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(gamePlayerHlr, poolCtr, tableUtil);
        this.selectResult = new HashMap<>();
        this.cardTypeUtilNiu = new CardTypeUtilNiu();
    }

    // 選擇最大牌型
    public abstract AbstractNiuStack calculateNiuStack(List<ICard> cardList);

    // 計算每個位置最大牌型
    public Map<Integer, AbstractStack> calculateNiuStack(Map<Integer, List<ICard>> allPlayerCardList) {
        return allPlayerCardList.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey, entry -> this.calculateNiuStack(allPlayerCardList.get(entry.getKey()))));
    }

    // 計算牌型由預處理結果
    protected ConstNiu.NiuTypeEnumCommon calculateNiuTypeByPreNiuResult(PreNiuResult preNiuResult, Map<ConstNiu.NiuTypeEnumCommon, Integer> niuTypeToRateConfig) {
        // 1. 判斷氫彈牛
        if (niuTypeToRateConfig.containsKey(ConstNiu.NiuTypeEnumCommon.HYDROGEN_BOMB) && this.cardTypeUtilNiu.checkHydrogenBomb(preNiuResult.getNiuCardList())) {
            return ConstNiu.NiuTypeEnumCommon.HYDROGEN_BOMB;
        }

        // 2. 判斷小牛牛
        if (niuTypeToRateConfig.containsKey(ConstNiu.NiuTypeEnumCommon.SMALL_NIU) && this.cardTypeUtilNiu.checkSmallNiu(preNiuResult.getNiuCardList())) {
            return ConstNiu.NiuTypeEnumCommon.SMALL_NIU;
        }

        // 3. 判斷順金牛
        if (niuTypeToRateConfig.containsKey(ConstNiu.NiuTypeEnumCommon.SHUNJIN_NIU) && this.cardTypeUtilNiu.checkShunJinNiu(preNiuResult.getNiuCardList())) {
            return ConstNiu.NiuTypeEnumCommon.SHUNJIN_NIU;
        }

        // 4. 判斷四炸彈
        if (niuTypeToRateConfig.containsKey(ConstNiu.NiuTypeEnumCommon.BOMB) && this.cardTypeUtilNiu.checkBomb(preNiuResult.getNiuCardList())) {
            return ConstNiu.NiuTypeEnumCommon.BOMB;
        }

        // 5. 判斷五花牛
        if (niuTypeToRateConfig.containsKey(ConstNiu.NiuTypeEnumCommon.FLOWER_5) && this.cardTypeUtilNiu.checkFiveFlower(preNiuResult.getNiuCardList())) {
            return ConstNiu.NiuTypeEnumCommon.FLOWER_5;
        }

        // 6. 判斷葫蘆牛
        if (niuTypeToRateConfig.containsKey(ConstNiu.NiuTypeEnumCommon.HULU_NIU) && this.cardTypeUtilNiu.checkHuluNiu(preNiuResult.getNiuCardList())) {
            return ConstNiu.NiuTypeEnumCommon.HULU_NIU;
        }

        // 7. 判斷同花牛
        if (niuTypeToRateConfig.containsKey(ConstNiu.NiuTypeEnumCommon.TONGHUA_NIU) && this.cardTypeUtilNiu.checkTongHuaNiu(preNiuResult.getNiuCardList())) {
            return ConstNiu.NiuTypeEnumCommon.TONGHUA_NIU;
        }

        // 8. 判斷順子牛
        if (niuTypeToRateConfig.containsKey(ConstNiu.NiuTypeEnumCommon.SHUNZI_NIU) && this.cardTypeUtilNiu.checkShunZiNiu(preNiuResult.getNiuCardList())) {
            return ConstNiu.NiuTypeEnumCommon.SHUNZI_NIU;
        }

        // 9. 判斷四花牛
        if (niuTypeToRateConfig.containsKey(ConstNiu.NiuTypeEnumCommon.FLOWER_4) && this.cardTypeUtilNiu.checkFourFlower(preNiuResult.getNiuCardList())) {
            return ConstNiu.NiuTypeEnumCommon.FLOWER_4;
        }

        // 10. 判斷無牛
        if (!preNiuResult.isNiu()) {
            return ConstNiu.NiuTypeEnumCommon.NIU_0;
        }

        // 11. 判斷牛幾
        return ConstNiu.NiuTypeEnumCommon.calculateNiuCount(this.cardTypeUtilNiu.calculateCardPointSumModuleBy10(preNiuResult.getNiuCardList()));
    }

    // 重設
    @Override
    public void reset() {
        this.selectResult = new HashMap<>();
    }
}
