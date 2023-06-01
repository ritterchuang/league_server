package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.MathRandomUtil;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.hitTypeExtend.HitTypeExtendTreasureBox;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendTreasureBox;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendTreasureBox;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.HitOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.hitOddsInfoExtend.HitOddsInfoExtend;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 連擊寶箱打擊計算器工具包測試
@ExtendWith(MockitoExtension.class)
class HitCtrUtilTreasureBoxTest {
    HitCtrUtilTreasureBox hitCtrUtilTreasureBox; // 連擊寶箱打擊計算器工具包
    @Mock
    ITableUtil mockTableUtil; // 牌桌工具包
    @Mock
    MathRandomUtil mockRandomUtil; // 隨機工具包
    @Mock
    Bullet mockBullet; // 子彈

    @BeforeEach
    void setUp() {
        this.hitCtrUtilTreasureBox = new HitCtrUtilTreasureBox(this.generateConfig(), mockTableUtil);
    }

    // 給定連擊寶箱類型，當計算擊殺倍數資訊，計算正確
    @Test
    void test_given_treasureBoxHitTypeExtend_when_calculateHitOddsInfo_return_correctResult() {
        // 1. given
        Mockito.when(mockTableUtil.getMainRandomUtil()).thenReturn(mockRandomUtil);
        Mockito.when(this.mockRandomUtil.getArrayIndexByWeightWithAccuracy(Mockito.anyList(), Mockito.any())).thenReturn(2);


        // 2. when
        HitOddsInfo actualResult = this.hitCtrUtilTreasureBox.calculateHitOddsInfo(new HitTypeExtendTreasureBox(), new ClientTarget());
        HitOddsInfo expectResult = new HitOddsInfo(165, new HitOddsInfoExtend());

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定未擊殺，回傳空殼
    @Test
    void test_given_noKill_when_calculateHitResultExtend_return_empty_result() {
        // 1. given
        HitTypeExtendTreasureBox hitTypeExtendTreasureBox = new HitTypeExtendTreasureBox();

        // 2. when
        HitResultExtendTreasureBox expectResult = new HitResultExtendTreasureBox();
        HitResultExtendTreasureBox actualResult = this.hitCtrUtilTreasureBox.calculateHitResultExtend(false, new HitOddsInfo(), hitTypeExtendTreasureBox, mockBullet);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定擊殺，回傳正確資訊
    @Test
    void test_given_kill_when_calculateHitResultExtend_return_correct_result() {
        // 1. given
        HitTypeExtendTreasureBox hitTypeExtendTreasureBox = new HitTypeExtendTreasureBox();
        HitOddsInfo hitOddsInfo = new HitOddsInfo(165, new HitOddsInfoExtend());
        Mockito.when(mockTableUtil.getMainRandomUtil()).thenReturn(mockRandomUtil);
        Mockito.doNothing().when(this.mockRandomUtil).shuffleList(Mockito.anyList());
        Mockito.when(this.mockRandomUtil.getArrayIndexByWeightWithAccuracy(Mockito.anyList(), Mockito.any())).thenReturn(2);
        Mockito.when(this.mockBullet.getBet()).thenReturn(2.0);

        // 2. when
        HitResultExtendTreasureBox expectResult = new HitResultExtendTreasureBox(
                Arrays.stream(new int[]{2, 5, 8, 10, 15, 25, 20, 25, 25, 30}).boxed().collect(Collectors.toList()),
                Arrays.stream(new double[]{4.0, 10.0, 16.0, 20.0, 30.0, 50.0, 40.0, 50.0, 50.0, 60.0}).boxed().collect(Collectors.toList()));
        HitResultExtendTreasureBox actualResult = this.hitCtrUtilTreasureBox.calculateHitResultExtend(true, hitOddsInfo, hitTypeExtendTreasureBox, mockBullet);

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定擊殺，回傳正確資訊
    @Test
    void test_given_killWithWrongConfig_when_calculateHitResultExtend_return_resultWithoutCompute() {
        // 1. given
        HitTypeExtendTreasureBox hitTypeExtendTreasureBox = new HitTypeExtendTreasureBox();
        HitOddsInfo hitOddsInfo = new HitOddsInfo(5, new HitOddsInfoExtend());
        Mockito.when(this.mockBullet.getBet()).thenReturn(2.0);

        // 2. when
        HitResultExtendTreasureBox expectResult = new HitResultExtendTreasureBox(
                Arrays.stream(new int[]{5}).boxed().collect(Collectors.toList()),
                Arrays.stream(new double[]{10.0}).boxed().collect(Collectors.toList()));
        HitResultExtendTreasureBox actualResult = this.hitCtrUtilTreasureBox.calculateHitResultExtend(true, hitOddsInfo, hitTypeExtendTreasureBox, mockBullet);

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 產出設定檔
    private HitTypeConfigExtendTreasureBox generateConfig() {
        return new HitTypeConfigExtendTreasureBox(
                new ArrayList<>(){
                    {
                        add(new int[]{40, 80});
                        add(new int[]{100, 120});
                        add(new int[]{165, 165});
                        add(new int[]{300, 350});
                        add(new int[]{450, 600});
                    }
                },
                List.of(1, 1, 1, 1, 1),
                new HashMap<>(){
                    {
                        put(10, new int[]{2, 2, 6});
                        put(11, new int[]{5, 6});
                        put(12, new int[]{2, 5, 5});
                        put(13, new int[]{5, 8});
                        put(14, new int[]{6, 8});
                        put(15, new int[]{2, 5, 8});
                        put(16, new int[]{2, 6, 8});
                        put(17, new int[]{2, 2, 5, 8});
                        put(18, new int[]{5, 5, 8});
                        put(19, new int[]{5, 6, 8});
                    }
                },
                new HashMap<>(){
                    {
                        put(20, new int[]{10, 10});
                        put(30, new int[]{10, 20});
                        put(40, new int[]{15, 25});
                        put(50, new int[]{10, 15, 25});
                        put(60, new int[]{15, 20, 25});
                        put(70, new int[]{15, 25, 30});
                        put(80, new int[]{20, 30, 30});
                        put(90, new int[]{15, 20, 25, 30});
                    }
                },
                new ArrayList<>(){
                    {
                        add(new int[]{20, 20, 30, 30});
                        add(new int[]{15, 25, 30, 30});
                        add(new int[]{20, 25, 25, 30});
                        add(new int[]{10, 30, 30, 30});

                        add(new int[]{10, 10, 25, 25, 30});
                        add(new int[]{10, 15, 25, 25, 25});
                        add(new int[]{10, 15, 20, 25, 30});
                        add(new int[]{10, 20, 20, 25, 25});
                        add(new int[]{15, 15, 20, 25, 25});
                        add(new int[]{15, 15, 20, 20, 30});

                        add(new int[]{10, 10, 15, 15, 20, 30});
                        add(new int[]{10, 10, 15, 15, 25, 25});
                    }
                },
                new ArrayList<>(){
                    {
                        add(5);
                        add(5);
                        add(5);
                        add(5);

                        add(10);
                        add(10);
                        add(10);
                        add(10);
                        add(10);
                        add(10);

                        add(1);
                        add(1);
                    }
                });
    }
}