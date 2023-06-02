package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.random.IRandomUtilMain;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.hitTypeExtend.HitTypeExtendMultiTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendMultiTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendMultiTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.HitOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.hitOddsInfoExtend.HitOddsInfoExtend;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 多重目標打擊計算器工具包測試
@ExtendWith(MockitoExtension.class)
class HitCtrUtilMultiTargetTest {
    HitCtrUtilMultiTarget hitCtrUtilMultiTarget; // 多重打擊計算器工具包
    @Mock
    HitTypeConfigExtendMultiTarget mockConfig; // 多重打擊設定檔
    @Mock
    Bullet mockBullet; // 子彈
    @Mock
    IRandomUtilMain mockUtil;

    @BeforeEach
    void setUp() {
        this.hitCtrUtilMultiTarget = new HitCtrUtilMultiTarget(mockConfig);
    }

    @Test
    void test_given_hitTypeExtendMultiTarget_when_calculateHitOddsInfo_then_correctResult() {
        // 1. given
        HitTypeExtendMultiTarget hitTypeExtendMultiTarget = new HitTypeExtendMultiTarget(new HashMap<>(){
            {
                put(1, 3);
                put(2, 7);
                put(3, 2);
                put(4, 2);
            }
        });
        Mockito.when(this.mockConfig.getTargetOddsMap()).thenReturn(
                new HashMap<>(){
                    {
                        put(1, 3);
                        put(2, 8);
                        put(3, 13);
                        put(4, 1);
                    }
                }
        );

        // 2. when
        HitOddsInfo expectResult = new HitOddsInfo(92, new HitOddsInfoExtend());
        HitOddsInfo actualResult = this.hitCtrUtilMultiTarget.calculateHitOddsInfo(hitTypeExtendMultiTarget, new ClientTarget(4));

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定未擊殺，回傳空殼
    @Test
    void test_given_noKill_when_calculateHitResultExtend_then_return_emptyResult() {
        // 1. given
        HitTypeExtendMultiTarget hitTypeExtendMultiTarget = new HitTypeExtendMultiTarget(new HashMap<>(){
            {
                put(1, 3);
                put(2, 7);
                put(3, 2);
            }
        });

        // 2. when
        HitResultExtendMultiTarget expectResult = new HitResultExtendMultiTarget();
        HitResultExtendMultiTarget actualResult = this.hitCtrUtilMultiTarget.calculateHitResultExtend(false, new HitOddsInfo(), hitTypeExtendMultiTarget, mockBullet);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定擊殺，回傳正確
    @Test
    void test_given_kill_when_calculateHitResultExtend_then_return_correctResult() {
        // 1. given
        HitTypeExtendMultiTarget hitTypeExtendMultiTarget = new HitTypeExtendMultiTarget(new HashMap<>(){
            {
                put(1, 3);
                put(2, 7);
                put(3, 2);
            }
        });
        HitOddsInfo hitOddsInfo = new HitOddsInfo(91, new HitOddsInfoExtend());
        Mockito.when(this.mockBullet.getBet()).thenReturn(3.0);
        Mockito.when(this.mockConfig.getTargetOddsMap()).thenReturn(
                new HashMap<>(){
                    {
                        put(1, 3);
                        put(2, 8);
                        put(3, 13);
                    }
                }
        );

        // 2. when
        HitResultExtendMultiTarget expectResult = new HitResultExtendMultiTarget(new HashMap<>(){
            {
                put(1, 9.0);
                put(2, 24.0);
                put(3, 39.0);
            }
        });
        HitResultExtendMultiTarget actualResult = this.hitCtrUtilMultiTarget.calculateHitResultExtend(true, hitOddsInfo, hitTypeExtendMultiTarget, mockBullet);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }
}