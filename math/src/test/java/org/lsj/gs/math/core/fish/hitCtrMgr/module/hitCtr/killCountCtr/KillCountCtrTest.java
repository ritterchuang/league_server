package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.killCountCtr;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.IRandomUtilMain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 擊殺次數計算者測試
@ExtendWith(MockitoExtension.class)
class KillCountCtrTest {
    KillCountCtr killCountCtr; // 擊殺次數計算者
    @Mock ITableUtil mockTableUtil; // 牌桌工具包
    @Mock IRandomUtilMain mockRandomUtilMain; // 主要隨機工具包

    // 給定 rtp 小於 倍數，當打擊次數小於等於1，回傳1
    @Test
    void test_given_rtp_smallerThanOdds_hitCount1_when_calculateKillCount_then_return_1() {
        // 1. given
        this.killCountCtr = new KillCountCtr(mockTableUtil);
        Mockito.when(mockTableUtil.getMainRandomUtil()).thenReturn(mockRandomUtilMain);
        Mockito.when(mockRandomUtilMain.isHitWithAccuracy(Mockito.anyDouble(), Mockito.any())).thenReturn(true);

        // 2. when
        int killCount = this.killCountCtr.calculateKillCount(0.5,2,1);

        // 3. then
        assertEquals(1, killCount);
    }

    // 給定 rtp 大於 倍數，當打擊次數等於2，回傳3
    @Test
    void test_given_rtp_biggerThanOdds_hitCount2_when_calculateKillCount_then_return_4() {
        // 1. given
        this.killCountCtr = new KillCountCtr(mockTableUtil);
        Mockito.when(mockTableUtil.getMainRandomUtil()).thenReturn(mockRandomUtilMain);
        Mockito.when(mockRandomUtilMain.isHitWithAccuracy(Mockito.anyDouble(), Mockito.any())).thenReturn(true);

        // 2. when
        int killCount = this.killCountCtr.calculateKillCount(2,1.5,2);

        // 3. then
        assertEquals(4, killCount);
    }
}

