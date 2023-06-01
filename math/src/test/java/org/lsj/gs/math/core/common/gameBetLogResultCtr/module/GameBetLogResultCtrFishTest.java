package org.lsj.gs.math.core.common.gameBetLogResultCtr.module;

import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultFish;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.GameBetLogResultCtrFish;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.detailCtr.module.IDetailCtr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.HitCtrMgr;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

// 下注記錄計算器魚機測試
@ExtendWith(MockitoExtension.class)
class GameBetLogResultCtrFishTest {
    GameBetLogResultCtrFish gameBetLogResultCtrFish; // 下注記錄計算器魚機
    @Mock BulletMgr mockBulletMgr; // 子彈管理者
    @Mock HitCtrMgr mockHitCtrMgr; // 打擊計算器管理者
    @Mock IDetailCtr mockDetailCtr; // 詳細記錄計算者
    @Mock
    GamePlayerHlr mockGamePlayerHlr; // 遊戲玩家處理器
    @Mock
    PoolCtr mockPoolCtr; // 水池控制器
    @Mock ITableUtil mockTableUtil; // 牌桌工具包


    // 給定返還金額0，當計算返還結果，返還空
    @Test
    void test_given_returnValueZero_when_calculateReturnGameBetLogResultFish_then_return_empty() {
        // 1. given
        this.gameBetLogResultCtrFish = new GameBetLogResultCtrFish(123131, 1, null, mockGamePlayerHlr, mockPoolCtr, mockTableUtil);
        Mockito.when(mockBulletMgr.calculateReturnValue()).thenReturn(0.0);

        // 2. when
        Optional<IGameBetLogResultFish> gameBetLogResultFish = this.gameBetLogResultCtrFish.calculateReturnGameBetLogResultFish("", mockHitCtrMgr, mockBulletMgr, mockDetailCtr);

        // 3. then
        assertTrue(gameBetLogResultFish.isEmpty());
    }
}