package org.lsj.gs.math.games.brnn_java.module.chartCtr;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtilBaiRen;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.IRandomUtilNotSupportSetRngData;
import org.lsj.test.utils.ReflectionUtil;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 百人牛牛路途計算者測試
@ExtendWith(MockitoExtension.class)
class ChartCtrBrnnJavaTest {
    private ChartCtrBrnnJava chartCtr; // 百人牛牛路途計算者
    @Mock
    ITableUtilBaiRen mockTableUtil; // 百人牌桌工具包
    @Mock
    IRandomUtilNotSupportSetRngData mockRandomUtil;

    // 給定4個路圖結果，當取得路圖陣列，回傳正確路圖
    @Test
    void test_given_4chartResult_when_getChartArray_then_return_chartArrayWithLength4AndCorrectValue() throws NoSuchFieldException, IllegalAccessException {
        // 1. given
        Mockito.when(this.mockTableUtil.getNotSupportSetRngDataRandomUtil()).thenReturn(mockRandomUtil);
        Mockito.when(this.mockRandomUtil.getRandomIntWithAccuracy(Mockito.anyInt(), Mockito.any())).thenReturn(0);
        this.chartCtr = new ChartCtrBrnnJava(mockTableUtil);
        ReflectionUtil.setMockFinalFieldToTarget(chartCtr, "chartList", this.generateChartList());

        // 2. when
        int[][] actualResult = this.chartCtr.getChartArray();
        int[][] expectResult = new int[][]{
                {1, 0, 0, 1},
                {1, 1, 0, 1},
                {1, 0, 1, 1},
                {1, 0, 1, 0}
        };

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    private List<Object> generateChartList() {
        return List.of(
                new int[]{1, 0, 0, 1},
                new int[]{1, 1, 0, 1},
                new int[]{1, 0, 1, 1},
                new int[]{1, 0, 1, 0}
        );
    }
}