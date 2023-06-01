package org.lsj.gs.math.core.slot.reelRtpStateHlr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.AdjustParameter;
import org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.entity.PoolConfig;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.pool.AgencyPool;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 高低表處理者測試
@ExtendWith(MockitoExtension.class)
class ReelRtpStateHlrTest {
    ReelRtpStateHlr reelRtpStateHlr; // 高低表處理者
    @Mock
    ReelRtpStateHlrConfig mockConfig; // 高低表處理者設定
    @Mock
    GamePlayerHlr mockGamePlayerHlr; // 遊戲玩家處理器
    @Mock
    PoolCtr mockPoolCtr; // 水池控制器
    @Mock
    ITableUtil mockTableUtil; // 牌桌工具包

    // 給定較高rtp，當計算高低表，回傳低表
    @Test
    void test_given_bothHighRtp_when_calculateReelRtpType_then_return_low() {
        // 1. given
        Mockito.when(this.mockPoolCtr.getPoolConfig()).thenReturn(new PoolConfig(new AgencyPool(), null));
        Mockito.when(this.mockConfig.getReelRtpTypeToDoubleMap()).thenReturn(new HashMap<>(){
            {
                put(ConstMathSlot.ReelRtpType.HIGH, 1.1);
                put(ConstMathSlot.ReelRtpType.LOW, 1.1);
            }
        });
        this.reelRtpStateHlr = new ReelRtpStateHlr(this.mockConfig, this.mockGamePlayerHlr, this.mockPoolCtr, this.mockTableUtil);

        // 2. when
        ConstMathCommon.ShuffleType shuffleType = ConstMathCommon.ShuffleType.NATURE;
        GameAdjustParameter gameAdjustParameter = new GameAdjustParameter(ConstMathCommon.PersonAdjustType.NORMAL, new AdjustParameter(0.05));
        ConstMathSlot.ReelRtpType expectResult = ConstMathSlot.ReelRtpType.LOW;

        // 3. then
        ConstMathSlot.ReelRtpType actualResult = this.reelRtpStateHlr.calculateReelRtpType(shuffleType, gameAdjustParameter);
        assertEquals(expectResult, actualResult);
    }

    // 給定較低rtp，當計算高低表，回傳低表
    @Test
    void test_given_bothLowerRtp_when_calculateReelRtpType_then_return_low() {
        // 1. given
        Mockito.when(this.mockPoolCtr.getPoolConfig()).thenReturn(new PoolConfig(new AgencyPool(), null));
        Mockito.when(this.mockConfig.getReelRtpTypeToDoubleMap()).thenReturn(new HashMap<>(){
            {
                put(ConstMathSlot.ReelRtpType.HIGH, 0.7);
                put(ConstMathSlot.ReelRtpType.LOW, 0.7);
            }
        });
        this.reelRtpStateHlr = new ReelRtpStateHlr(this.mockConfig, this.mockGamePlayerHlr, this.mockPoolCtr, this.mockTableUtil);

        // 2. when
        ConstMathCommon.ShuffleType shuffleType = ConstMathCommon.ShuffleType.NATURE;
        GameAdjustParameter gameAdjustParameter = new GameAdjustParameter(ConstMathCommon.PersonAdjustType.NORMAL, new AdjustParameter(0.05));
        ConstMathSlot.ReelRtpType expectResult = ConstMathSlot.ReelRtpType.LOW;

        // 3. then
        ConstMathSlot.ReelRtpType actualResult = this.reelRtpStateHlr.calculateReelRtpType(shuffleType, gameAdjustParameter);
        assertEquals(expectResult, actualResult);
    }
}