package org.lsj.gs.math.core.fish.mathFishConfigHlr;

import org.lsj.gs.math.core.AbstractModule;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.BulletConfigDynamic;
import org.lsj.gs.math.core.fish.clientHitResultPgr.IClientHitResultPgr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.HitCtrMgr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.IHitCtr;
import org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.client.*;
import org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.client.robotHitResult.RobotHitResult;
import org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.server.MathFishConfigHlrConfig;
import org.lsj.utils.MathUtil;

import java.util.HashMap;
import java.util.Map;

// 數值設定處理器
public class MathFishConfigHlr extends AbstractModule {
    private final MathFishConfigHlrConfig config; // 數值設定處理器的設定

    public MathFishConfigHlr(MathFishConfigHlrConfig mathFishConfigHlrConfig, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(gamePlayerHlr, poolCtr, tableUtil);
        this.config = mathFishConfigHlrConfig;
    }

    // 取得數值設定
    public MathFishConfig getMathFishConfig(BulletMgr bulletMgr, HitCtrMgr hitCtrMgr, IClientHitResultPgr clientHitResultWpr){
        // 1. 計算機器人玩家設定
        RobotPlayerConfig robotPlayerConfig = this.calculateRobotPlayerConfig();

        // 2. 計算機器人遊戲設定
        RobotGameConfig robotGameConfig = this.calculateRobotGameConfig(bulletMgr, hitCtrMgr, clientHitResultWpr);

        // 3. 計算客端子彈設定
        ClientBulletConfig clientBulletConfig = this.calculateClientBulletConfig(bulletMgr);

        // 4. 計算客端打擊設定
        ClientHitConfig clientHitConfig = this.calculateClientHitConfig();

        // 5. 封裝
        return new MathFishConfig(robotPlayerConfig, robotGameConfig, clientBulletConfig, clientHitConfig);
    }

    // 計算機器人玩家設定
    private RobotPlayerConfig calculateRobotPlayerConfig() {
        return new RobotPlayerConfig(
                MathUtil.moneyScale(MathUtil.multiply(this.config.getRobotBeginMoneyLimitBase().getLowerLimitBase(), this.config.getBase())),
                MathUtil.moneyScale(MathUtil.multiply(this.config.getRobotBeginMoneyLimitBase().getUpperLimitBase(), this.config.getBase())));
    }

    // 計算機器人遊戲設定
    private RobotGameConfig calculateRobotGameConfig(BulletMgr bulletMgr, HitCtrMgr hitCtrMgr, IClientHitResultPgr clientHitResultWpr) {
        // 1. 初始化機器人子彈擊殺目標結果對應表
        Map<Integer, Map<Integer, RobotHitResult>> robotHitResultMap = new HashMap<>();

        // 2. 遍歷所有打擊組合
        this.config.getHitCtrConfigMap().forEach((bulletIndex, targetIndexConfigMap) ->
                robotHitResultMap.put(bulletIndex, this.calculateRobotTargetHitResultMap(bulletIndex, bulletMgr, hitCtrMgr.getTargetHitCtrMap(bulletIndex), clientHitResultWpr))
        );

        // 3. 封裝
        return new RobotGameConfig(robotHitResultMap);
    }

    // 計算機器人目標結果對應表
    private Map<Integer, RobotHitResult> calculateRobotTargetHitResultMap(int bulletIndex, BulletMgr bulletMgr, Map<Integer, IHitCtr> targetIndexHitCtrMap, IClientHitResultPgr clientHitResultWpr) {
        Map<Integer, RobotHitResult> robotTargetHitResultMap = new HashMap<>();

        targetIndexHitCtrMap.forEach((targetIndex, hitCtr) ->
                robotTargetHitResultMap.put(targetIndex, hitCtr.calculateRobotHitResult(bulletIndex, bulletMgr, clientHitResultWpr))
        );

        return robotTargetHitResultMap;
    }

    // 計算子彈設定
    private ClientBulletConfig calculateClientBulletConfig(BulletMgr bulletMgr) {
        // 1. 初始化子彈結果對應表
        Map<Integer, BulletConfigDynamic> bulletConfigDynamicMap = new HashMap<>();

        // 2. 遍歷所有子彈
        this.config.getBulletConfigMap().forEach(
                (bulletIndex, bulletConfig) -> bulletConfigDynamicMap.put(bulletIndex,
                        // 3. 生成子彈成本列表
                        new BulletConfigDynamic(bulletConfig,
                                bulletMgr.getBulletMtr(bulletIndex).getBulletCostList(this.config.getBase())))
        );



        // 4. 封裝
        return new ClientBulletConfig(bulletConfigDynamicMap);
    }

    // 計算客端打擊設定
    private ClientHitConfig calculateClientHitConfig() {
        return new ClientHitConfig(this.config.getHitCtrConfigMap());
    }

    // 重設
    @Override
    public void reset() {
        // 有需求才做
    }
}
