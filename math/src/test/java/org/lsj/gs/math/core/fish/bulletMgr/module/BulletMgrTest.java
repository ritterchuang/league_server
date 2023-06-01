package org.lsj.gs.math.core.fish.bulletMgr.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.BulletConfigBase;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.BulletMgrConfig;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever.bulletCostConfigExtend.BulletCostConfigExtendFree;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletRtpUseTypeHlr.enity.sever.BulletRtpUseConfigExtendHave;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.enity.sever.BulletConfigExtendNormal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 子彈管理器測試
@ExtendWith(MockitoExtension.class)
class BulletMgrTest {

    BulletMgr bulletMgr; // 子彈管理器
    @Mock BulletMgrConfig config; // 子彈管理器設定
    @Mock
    GamePlayerHlr mockGamePlayerHlr; // 遊戲玩家處理器
    @Mock
    PoolCtr mockPoolCtr; // 水池控制器
    @Mock ITableUtil mockTableUtil; // 牌桌工具包


    // 給定不存在客製子彈，檢查客製子彈時，預期例外狀況
    @Test
    void test_given_nonExistBullet_when_checkClientBullet_then_return_errorCode() {
        // 1. given
        Mockito.when(config.getBulletConfigMap()).thenReturn(this.generateBulletConfigBase());
        this.bulletMgr = new BulletMgr(config, mockGamePlayerHlr, mockPoolCtr, mockTableUtil);

        // 2. when
        ConstMathCommon.TableProtocolCode tableProtocolCode = this.bulletMgr.checkClientBullet(new ClientBullet());

        // 3. then
        assertEquals(tableProtocolCode, ConstMathCommon.TableProtocolCode.FISH_BULLET_INDEX_NOT_EXIST);
    }

    // 產出子彈基礎設定
    private Map<Integer, BulletConfigBase> generateBulletConfigBase() {
        return new HashMap<>(){
            {put(1, new BulletConfigBase(
                    ConstMathFish.BulletType.NORMAL,
                    new BulletConfigExtendNormal(),
                    ConstMathFish.BulletCostType.FREE,
                    new BulletCostConfigExtendFree(),
                    ConstMathFish.BulletAmountType.FINITE,
                    ConstMathFish.BulletRtpUseType.HAVE,
                    new BulletRtpUseConfigExtendHave(1.0)));}
        };
    }
}