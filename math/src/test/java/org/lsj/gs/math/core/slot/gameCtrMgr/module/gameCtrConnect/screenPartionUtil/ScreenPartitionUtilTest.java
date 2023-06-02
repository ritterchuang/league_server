package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrConnect.screenPartionUtil;

import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScreenPartitionUtilTest {
    ScreenPartitionUtil screenPartitionUtil; // 畫面分割工具包

    // 給定6*5矩陣畫面，當計算畫面分割結果，回傳正確
    @Test
    void test_given_6x5MatrixScreen_when_calculateScreenPartitionResult_then_return_correct_partitionResult() {
        // 1. given
        this.screenPartitionUtil = new ScreenPartitionUtil();
        List<List<Integer>> screenSymbol = this.generateScreenSymbol_6x5();
        Map<int[], List<int[]>> positionToConnectPositionListMap = this.generatePositionToConnectPositionListMap_6x5();

        // 2. when
        ScreenPartitionResult actualResult = this.screenPartitionUtil.calculateScreenPartitionResult(positionToConnectPositionListMap, screenSymbol);
        ScreenPartitionResult expectResult = this.generateExpectScreenPartitionResult_6x5();

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    private Map<int[], List<int[]>> generatePositionToConnectPositionListMap_6x5() {
        return new HashMap<>(){
            {
                put(new int[]{0,0}, List.of(new int[]{0,1},new int[]{1,0}));
                put(new int[]{0,1}, List.of(new int[]{0,0}, new int[]{1,1}, new int[]{0,2}));
                put(new int[]{0,2}, List.of(new int[]{0,1}, new int[]{1,2}, new int[]{0,3}));
                put(new int[]{0,3}, List.of(new int[]{0,2}, new int[]{1,3}, new int[]{0,4}));
                put(new int[]{0,4}, List.of(new int[]{0,3}, new int[]{1,4}));

                put(new int[]{1,0}, List.of(new int[]{0,0}, new int[]{1,1}, new int[]{2,0}));
                put(new int[]{1,1}, List.of(new int[]{1,2}, new int[]{0,1}, new int[]{2,1}, new int[]{1,0}));
                put(new int[]{1,2}, List.of(new int[]{1,3}, new int[]{0,2}, new int[]{2,2}, new int[]{1,1}));
                put(new int[]{1,3}, List.of(new int[]{1,4}, new int[]{0,3}, new int[]{2,3}, new int[]{1,2}));
                put(new int[]{1,4}, List.of(new int[]{0,4}, new int[]{1,3}, new int[]{2,4}));

                put(new int[]{2,0}, List.of(new int[]{1,0}, new int[]{2,1}, new int[]{3,0}));
                put(new int[]{2,1}, List.of(new int[]{2,2}, new int[]{1,1}, new int[]{3,1}, new int[]{2,0}));
                put(new int[]{2,2}, List.of(new int[]{2,3}, new int[]{1,2}, new int[]{3,2}, new int[]{2,1}));
                put(new int[]{2,3}, List.of(new int[]{2,4}, new int[]{1,3}, new int[]{3,3}, new int[]{2,2}));
                put(new int[]{2,4}, List.of(new int[]{1,4}, new int[]{2,3}, new int[]{3,4}));

                put(new int[]{3,0}, List.of(new int[]{2,0}, new int[]{3,1}, new int[]{4,0}));
                put(new int[]{3,1}, List.of(new int[]{3,2}, new int[]{2,1}, new int[]{4,1}, new int[]{3,0}));
                put(new int[]{3,2}, List.of(new int[]{3,3}, new int[]{2,2}, new int[]{4,2}, new int[]{3,1}));
                put(new int[]{3,3}, List.of(new int[]{3,4}, new int[]{2,3}, new int[]{4,3}, new int[]{3,2}));
                put(new int[]{3,4}, List.of(new int[]{2,4}, new int[]{3,3}, new int[]{4,4}));

                put(new int[]{4,0}, List.of(new int[]{3,0}, new int[]{4,1}, new int[]{5,0}));
                put(new int[]{4,1}, List.of(new int[]{4,2}, new int[]{3,1}, new int[]{5,1}, new int[]{4,0}));
                put(new int[]{4,2}, List.of(new int[]{4,3}, new int[]{3,2}, new int[]{5,2}, new int[]{4,1}));
                put(new int[]{4,3}, List.of(new int[]{4,4}, new int[]{3,3}, new int[]{5,3}, new int[]{4,2}));
                put(new int[]{4,4}, List.of(new int[]{3,4}, new int[]{4,3}, new int[]{5,4}));

                put(new int[]{5,0}, List.of(new int[]{4,0}, new int[]{5,1}));
                put(new int[]{5,1}, List.of(new int[]{5,2}, new int[]{4,1}, new int[]{5,0}));
                put(new int[]{5,2}, List.of(new int[]{5,3}, new int[]{4,2}, new int[]{5,1}));
                put(new int[]{5,3}, List.of(new int[]{5,4}, new int[]{4,3}, new int[]{5,2}));
                put(new int[]{5,4}, List.of(new int[]{4,4}, new int[]{5,3}));
            }
        };
    }
    private List<List<Integer>> generateScreenSymbol_6x5() {
        return List.of(
                List.of(0,0,0,2,2)
                ,List.of(1,1,0,2,3)
                ,List.of(1,0,0,2,3)
                ,List.of(5,1,4,3,3)
                ,List.of(4,4,4,4,3)
                ,List.of(5,5,4,4,4)
        );
    }
    private ScreenPartitionResult generateExpectScreenPartitionResult_6x5() {
        SymbolScreenPositionCluster symbol_0 = new SymbolScreenPositionCluster(0,
                                                    List.of(new ScreenPositionCluster(List.of(
                                                            new int[]{0,0},
                                                            new int[]{0,1},
                                                            new int[]{0,2},
                                                            new int[]{1,2},
                                                            new int[]{2,2},
                                                            new int[]{2,1}
                                                    ))));
        SymbolScreenPositionCluster symbol_1 = new SymbolScreenPositionCluster(1,
                List.of(new ScreenPositionCluster(List.of(
                                                    new int[]{1,0},
                                                    new int[]{1,1},
                                                    new int[]{2,0})),
                        new ScreenPositionCluster(List.of(
                                new int[]{3,1}))));
        SymbolScreenPositionCluster symbol_2 = new SymbolScreenPositionCluster(2,
                List.of(new ScreenPositionCluster(List.of(
                        new int[]{0,3},
                        new int[]{1,3},
                        new int[]{2,3},
                        new int[]{0,4}))));
        SymbolScreenPositionCluster symbol_3 = new SymbolScreenPositionCluster(3,
                List.of(new ScreenPositionCluster(List.of(
                        new int[]{1,4},
                        new int[]{2,4},
                        new int[]{3,4},
                        new int[]{3,3},
                        new int[]{4,4}))));
        SymbolScreenPositionCluster symbol_4 = new SymbolScreenPositionCluster(4,
                List.of(new ScreenPositionCluster(List.of(
                        new int[]{3,2},
                        new int[]{4,2},
                        new int[]{4,3},
                        new int[]{5,3},
                        new int[]{5,4},
                        new int[]{5,2},
                        new int[]{4,1},
                        new int[]{4,0}))));
        SymbolScreenPositionCluster symbol_5 = new SymbolScreenPositionCluster(5,
                List.of(new ScreenPositionCluster(List.of(
                                new int[]{3,0})),
                        new ScreenPositionCluster(List.of(
                                new int[]{5,0},
                                new int[]{5,1}))));

        return new ScreenPartitionResult(List.of(symbol_0, symbol_2, symbol_1, symbol_3, symbol_5, symbol_4));
    }

    // 給定蜂巢型連接畫面，當計算畫面分割，回傳正確分割
    @Test
    void test_given_honeyCombScreen_when_calculateScreenPartitionResult_then_return_correct_partitionResult() {
        // 1. given
        this.screenPartitionUtil = new ScreenPartitionUtil();
        List<List<Integer>> screenSymbol = this.generateScreenSymbol_honeyComb();
        Map<int[], List<int[]>> positionToConnectPositionListMap = this.generatePositionToConnectPositionListMap_honeyComb();

        // 2. when
        ScreenPartitionResult actualResult = this.screenPartitionUtil.calculateScreenPartitionResult(positionToConnectPositionListMap, screenSymbol);
        ScreenPartitionResult expectResult = this.generateExpectScreenPartitionResult_honeyComb();

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    private Map<int[], List<int[]>> generatePositionToConnectPositionListMap_honeyComb() {
        return new HashMap<>(){
            {
                put(new int[]{0,0}, List.of(new int[]{0,1},new int[]{1,0},new int[]{1,1}));
                put(new int[]{0,1}, List.of(new int[]{0,0}, new int[]{1,1}, new int[]{1,2}, new int[]{0,2}));
                put(new int[]{0,2}, List.of(new int[]{0,1}, new int[]{1,2}, new int[]{1,3}));

                put(new int[]{1,0}, List.of(new int[]{0,0}, new int[]{1,1}, new int[]{2,1}, new int[]{2,0}));
                put(new int[]{1,1}, List.of(new int[]{1,2}, new int[]{0,1}, new int[]{0,0}, new int[]{1,0}, new int[]{2,1}, new int[]{2,2}));
                put(new int[]{1,2}, List.of(new int[]{1,3}, new int[]{0,2}, new int[]{0,1}, new int[]{1,1}, new int[]{2,2}, new int[]{2,3}));
                put(new int[]{1,3}, List.of(new int[]{0,2}, new int[]{1,2}, new int[]{2,3}, new int[]{2,4}));

                put(new int[]{2,0}, List.of(new int[]{1,0}, new int[]{2,1}, new int[]{3,0}));
                put(new int[]{2,1}, List.of(new int[]{2,2}, new int[]{1,1}, new int[]{1,0}, new int[]{2,0}, new int[]{3,0}, new int[]{3,1}));
                put(new int[]{2,2}, List.of(new int[]{2,3}, new int[]{1,2}, new int[]{1,1}, new int[]{2,1}, new int[]{3,1}, new int[]{3,2}));
                put(new int[]{2,3}, List.of(new int[]{2,4}, new int[]{1,3}, new int[]{1,2}, new int[]{2,2}, new int[]{3,2}, new int[]{3,3}));
                put(new int[]{2,4}, List.of(new int[]{1,3}, new int[]{2,3}, new int[]{3,3}));

                put(new int[]{3,0}, List.of(new int[]{2,0}, new int[]{2,1}, new int[]{3,1}, new int[]{4,0}));
                put(new int[]{3,1}, List.of(new int[]{3,2}, new int[]{2,2}, new int[]{2,1}, new int[]{3,0}, new int[]{4,0}, new int[]{4,1}));
                put(new int[]{3,2}, List.of(new int[]{3,3}, new int[]{2,3}, new int[]{2,2}, new int[]{3,1}, new int[]{4,1}, new int[]{4,2}));
                put(new int[]{3,3}, List.of(new int[]{2,4}, new int[]{2,3}, new int[]{3,2}, new int[]{4,2}));

                put(new int[]{4,0}, List.of(new int[]{3,0}, new int[]{3,1}, new int[]{4,1}));
                put(new int[]{4,1}, List.of(new int[]{4,2}, new int[]{3,2}, new int[]{3,1}, new int[]{4,0}));
                put(new int[]{4,2}, List.of(new int[]{3,3}, new int[]{3,2}, new int[]{4,1}));
            }
        };
    }
    private List<List<Integer>> generateScreenSymbol_honeyComb() {
        return List.of(
                List.of(0,2,3)
                ,List.of(0,2,0,3)
                ,List.of(0,1,1,0,3)
                ,List.of(0,0,0,4)
                ,List.of(2,2,4)
        );
    }
    private ScreenPartitionResult generateExpectScreenPartitionResult_honeyComb() {
        SymbolScreenPositionCluster symbol_0 = new SymbolScreenPositionCluster(0,
                List.of(new ScreenPositionCluster(List.of(
                        new int[]{0,0},
                        new int[]{1,0},
                        new int[]{2,0},
                        new int[]{3,0},
                        new int[]{3,1},
                        new int[]{3,2},
                        new int[]{2,3},
                        new int[]{1,2}
                ))));
        SymbolScreenPositionCluster symbol_1 = new SymbolScreenPositionCluster(1,
                List.of(new ScreenPositionCluster(List.of(
                                new int[]{2,1},
                                new int[]{2,2}))));
        SymbolScreenPositionCluster symbol_2 = new SymbolScreenPositionCluster(2,
                List.of(new ScreenPositionCluster(List.of(
                                new int[]{0,1},
                                new int[]{1,1})),
                        new ScreenPositionCluster(List.of(
                                new int[]{4,0},
                                new int[]{4,1}))));
        SymbolScreenPositionCluster symbol_3 = new SymbolScreenPositionCluster(3,
                List.of(new ScreenPositionCluster(List.of(
                        new int[]{0,2},
                        new int[]{1,3},
                        new int[]{2,4}))));
        SymbolScreenPositionCluster symbol_4 = new SymbolScreenPositionCluster(4,
                List.of(new ScreenPositionCluster(List.of(
                        new int[]{3,3},
                        new int[]{4,2}))));

        return new ScreenPartitionResult(List.of(symbol_0, symbol_2, symbol_3, symbol_1, symbol_4));
    }
}