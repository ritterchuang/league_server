package org.lsj.gs.math.core.card.betCtr;

import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AbstractBetCtrTest {
    @Mock
    GamePlayerHlr mockGamePlayerHlr; // 遊戲玩家處理者
    @Mock
    PoolCtr mockPoolCtr; // 水池計算者
    @Mock
    ITableUtil mockTableUtil; // 牌桌工具包
    @Mock
    AbstractBetCtrConfig mockConfig; // 設定檔

    // 給定押注對應表大小非最大玩家數，當計算押注陣列，無玩家的位置倍數為-1
    @Test
    void test_given_betRateMapSizeNotMaxUser_when_getBetResultArray_then_nonPlayerChairResultIsNegative1() {
        // 1. given
        AbstractBetCtr abstractBetCtr = Mockito.mock(AbstractBetCtr.class,
                Mockito.withSettings().useConstructor(mockConfig, mockGamePlayerHlr, mockPoolCtr, mockTableUtil).defaultAnswer(Mockito.CALLS_REAL_METHODS));
        Mockito.when(mockConfig.getMaxUser()).thenReturn(5);
        abstractBetCtr.betRateMap = this.generateBetRateMap();

        // 2. when
        int[] actualResult = abstractBetCtr.getBetResultArray();
        int[] expectResult = new int[]{5, 3, -1, 11, -1};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    private Map<Integer, Integer> generateBetRateMap() {
        return new HashMap<>(){
            {
                put(0, 5);
                put(1, 3);
                put(3, 11);
            }
        };
    }
}