package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.MathRandomUtil;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.hitTypeExtend.HitTypeExtendDoubleWheel;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendDoubleWheel;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendDoubleWheel;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.HitOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.hitOddsInfoExtend.HitOddsInfoExtendDoubleWheel;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 雙重轉輪打擊計算器工具包測試
@ExtendWith(MockitoExtension.class)
class HitCtrUtilDoubleWheelTest {
    HitCtrUtilDoubleWheel hitCtrUtilDoubleWheel; // 雙重轉輪打擊計算器工具包
    @Mock
    HitTypeConfigExtendDoubleWheel mockConfig; // 雙重轉輪設定檔
    @Mock
    ITableUtil mockTableUtil; // 牌桌工具包
    @Mock
    MathRandomUtil mockRandomUtil; // 隨機工具包
    @Mock
    Bullet mockBullet; // 子彈

    @BeforeEach
    void setUp() {
        this.hitCtrUtilDoubleWheel = new HitCtrUtilDoubleWheel(mockConfig, mockTableUtil);
    }

    // 給定正確設定，當計算倍數資訊時，回傳倍數120
    @Test
    void test_given_correctSetting_when_calculateHitOddsInfo_then_return_correct_result() {
        // 1. given
        HitTypeExtendDoubleWheel hitTypeExtendDoubleWheel = new HitTypeExtendDoubleWheel();
        Mockito.when(this.mockTableUtil.getMainRandomUtil()).thenReturn(this.mockRandomUtil);
        Mockito.when(this.mockRandomUtil.getArrayIndexByWeightWithAccuracy(Mockito.anyList(), Mockito.any())).thenReturn(2, 1);
        Mockito.when(this.mockConfig.getInSideShowOddsArray()).thenReturn(new int[]{2, 4, 6, 8});
        Mockito.when(this.mockConfig.getOutSideShowOddsArray()).thenReturn(new int[]{10, 20, 30});
        Mockito.when(this.mockConfig.getInSideShowOddsWeightArray()).thenReturn(new int[]{1, 1, 1, 1});
        Mockito.when(this.mockConfig.getInSideOddsToOutSideOddsWeightArrayMap()).thenReturn(new HashMap<>(){
            {
                put(2, new int[]{1, 1, 1});
                put(4, new int[]{1, 1, 1});
                put(6, new int[]{1, 1, 1});
                put(8, new int[]{1, 1, 1});
            }
        });

        // 2. when
        HitOddsInfo expectResult = new HitOddsInfo(120, new HitOddsInfoExtendDoubleWheel(2, 1));
        HitOddsInfo actualResult = this.hitCtrUtilDoubleWheel.calculateHitOddsInfo(hitTypeExtendDoubleWheel, new ClientTarget());

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定未擊殺，回傳空殼
    @Test
    void test_given_noKill_when_calculateHitResultExtend_then_return_empty_result() {
        // 1. given
        HitTypeExtendDoubleWheel hitTypeExtendDoubleWheel = new HitTypeExtendDoubleWheel();

        // 2. when
        HitResultExtendDoubleWheel expectResult = new HitResultExtendDoubleWheel();
        HitResultExtendDoubleWheel actualResult = this.hitCtrUtilDoubleWheel.calculateHitResultExtend(false, new HitOddsInfo(), hitTypeExtendDoubleWheel, mockBullet);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定擊殺，回傳正確資訊
    @Test
    void test_given_kill_when_calculateHitResultExtend_then_return_correct_result() {
        // 1. given
        HitTypeExtendDoubleWheel hitTypeExtendDoubleWheel = new HitTypeExtendDoubleWheel();
        HitOddsInfo hitOddsInfo = new HitOddsInfo(120, new HitOddsInfoExtendDoubleWheel(2, 1));
        Mockito.when(this.mockConfig.getInSideShowOddsArray()).thenReturn(new int[]{2, 4, 6, 8});
        Mockito.when(this.mockConfig.getOutSideShowOddsArray()).thenReturn(new int[]{10, 20, 30});

        // 2. when
        HitResultExtendDoubleWheel expectResult = new HitResultExtendDoubleWheel(2, 1, 6, 20);
        HitResultExtendDoubleWheel actualResult = this.hitCtrUtilDoubleWheel.calculateHitResultExtend(true, hitOddsInfo, hitTypeExtendDoubleWheel, mockBullet);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }
}