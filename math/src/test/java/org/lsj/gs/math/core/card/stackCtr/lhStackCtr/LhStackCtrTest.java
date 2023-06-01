package org.lsj.gs.math.core.card.stackCtr.lhStackCtr;

import org.lsj.gs.math.core.card.cardWallCtr.CardPoker;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 龍虎牌型計算者測試
class LhStackCtrTest {
    private LhStackCtr lhStackCtr; // 龍虎牌型計算者

    // 給定押注區域牌列表，當計算牌型，回傳正確押注區域牌型
    @Test
    void test_given_areaIdToCardListMap_when_calculateStackMap_then_correctAreaIdToStackMap() throws NoSuchFieldException, IllegalAccessException {
        // 1.given
        this.lhStackCtr = new LhStackCtr();

        // 2. when
        Map<Integer, LhStack> actualResult = this.lhStackCtr.calculateStackMap(this.generateAreaIdToCardListMap());
        Map<Integer, LhStack> expectResult = this.generateAreaIdToStackMap();

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 產出 押注區域, 牌列表對應表
    private Map<Integer, List<ICard>> generateAreaIdToCardListMap() {
        Map<Integer, Integer> cardValueWeightMap = this.generateCardValueWeightMap();

        Map<Integer, List<ICard>> result = new HashMap<>();

        result.put(0, List.of(new CardPoker(111, 1, cardValueWeightMap)));
        result.put(1, List.of(new CardPoker(112, 1, cardValueWeightMap)));

        return result;
    }

    // 產出 押注區域, 牌型 對應表
    private Map<Integer, LhStack> generateAreaIdToStackMap() {
        Map<Integer, LhStack> result = new HashMap<>();

        Map<Integer, Integer> cardValueWeightMap = this.generateCardValueWeightMap();

        result.put(0, new LhStack(List.of(new CardPoker(111, 1, cardValueWeightMap))));
        result.put(1, new LhStack(List.of(new CardPoker(112, 1, cardValueWeightMap))));

        return result;
    }

    // 創建牌權重
    private Map<Integer, Integer> generateCardValueWeightMap() {
        return new HashMap<>(){{
            put(1,1);
            put(2,2);
            put(3,3);
            put(4,4);
            put(5,5);
            put(6,6);
            put(7,7);
            put(8,8);
            put(9,9);
            put(10,10);
            put(11,11);
            put(12,12);
            put(13,13);
        }};
    }
}