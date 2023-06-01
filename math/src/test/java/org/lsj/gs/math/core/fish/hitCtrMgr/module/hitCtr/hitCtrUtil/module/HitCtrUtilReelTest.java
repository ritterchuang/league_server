package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.MathRandomUtil;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.hitTypeExtend.HitTypeExtendReel;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendReel;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendReel;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.HitOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.hitOddsInfoExtend.HitOddsInfoExtend;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 滾輪目標打擊計算器工具包測試
@ExtendWith(MockitoExtension.class)
class HitCtrUtilReelTest {
    HitCtrUtilReel hitCtrUtilReel; // 滾輪打擊計算器工具包
    @Mock
    HitTypeConfigExtendReel mockConfig; // 滾輪打擊設定檔
    @Mock
    ITableUtil mockTableUtil; // 牌桌工具包
    @Spy
    MathRandomUtil spyRandomUtil; // 隨機工具包
    @Mock
    Bullet mockBullet; // 子彈

    @BeforeEach
    void setUp() {
        this.hitCtrUtilReel = new HitCtrUtilReel(mockConfig, mockTableUtil);
    }

    // 給定一種倍數區間設定，回傳正確結果
    @Test
    void test_given_onlyOneOddsRange_when_calculateHitOddsInfo_then_correct_Result() {
        // 1. given
        HitTypeExtendReel hitTypeExtendReel = new HitTypeExtendReel();
        Mockito.when(this.mockTableUtil.getMainRandomUtil()).thenReturn(this.spyRandomUtil);
        Mockito.when(this.mockConfig.getOddsRangeList()).thenReturn(new ArrayList<>(){
            {
                add(new int[]{906, 906});
            }
        });
        Mockito.when(this.mockConfig.getOddsRangeWeightList()).thenReturn(new ArrayList<>(){
            {
                add(1);
            }
        });

        // 2. when
        HitOddsInfo expectResult = new HitOddsInfo(906, new HitOddsInfoExtend());
        HitOddsInfo actualResult = this.hitCtrUtilReel.calculateHitOddsInfo(hitTypeExtendReel, new ClientTarget());

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定未擊殺，回傳空殼
    @Test
    void test_given_noKill_when_calculateHitResultExtend_then_return_empty_result() {
        // 1. given
        HitTypeExtendReel hitTypeExtendReel = new HitTypeExtendReel();

        // 2. when
        HitResultExtendReel expectResult = new HitResultExtendReel();
        HitResultExtendReel actualResult = this.hitCtrUtilReel.calculateHitResultExtend(false, new HitOddsInfo(), hitTypeExtendReel, mockBullet);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定擊殺906，回傳正確結果
    @Test
    void test_given_kill_906_when_calculateHitResultExtend_then_return_correct_result() {
        // 1. given
        HitTypeExtendReel hitTypeExtendReel = new HitTypeExtendReel();
        HitOddsInfo hitOddsInfo = new HitOddsInfo(906, new HitOddsInfoExtend());

        // 2. when
        HitResultExtendReel expectResult = new HitResultExtendReel(6, 0, 9);
        HitResultExtendReel actualResult = this.hitCtrUtilReel.calculateHitResultExtend(true, hitOddsInfo, hitTypeExtendReel, mockBullet);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定擊殺53，回傳正確結果
    @Test
    void test_given_kill_053_when_calculateHitResultExtend_then_return_correct_result() {
        // 1. given
        HitTypeExtendReel hitTypeExtendReel = new HitTypeExtendReel();
        HitOddsInfo hitOddsInfo = new HitOddsInfo(53, new HitOddsInfoExtend());

        // 2. when
        HitResultExtendReel expectResult = new HitResultExtendReel(3, 5, 0);
        HitResultExtendReel actualResult = this.hitCtrUtilReel.calculateHitResultExtend(true, hitOddsInfo, hitTypeExtendReel, mockBullet);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }
}