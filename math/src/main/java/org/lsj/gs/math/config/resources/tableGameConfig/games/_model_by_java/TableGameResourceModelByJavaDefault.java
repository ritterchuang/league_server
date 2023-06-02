package org.lsj.gs.math.config.resources.tableGameConfig.games._model_by_java;

import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigFish;
import org.lsj.gs.math.config.resources.tableGameConfig.core.AbstractTableGameResourceFish;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.BulletConfigBase;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever.BulletCostExchange;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever.bulletCostConfigExtend.BulletCostConfigExtendRatio;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever.bulletCostListConfigExtend.BulletCostListConfigExtendTen;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever.bulletCostListConfigExtend.BulletCostListConfigExtendTenMultiTen;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletRtpUseTypeHlr.enity.sever.BulletRtpUseConfigExtendNone;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.enity.sever.BulletConfigExtendNormal;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitCtrConfig;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendFixedOdds;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.AwardBulletGtrConfigExtendNone;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.rtpChoiceHlr.enity.RtpChoiceHlrConfigExtendCompanyAdjust;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureCtrConfigExtend.SpecialFeatureCtrConfigExtendNone;
import org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.server.RobotBeginMoneyLimitBase;
import org.lsj.gs.math.games._model_by_java.enity.ConstModelByJava;

import java.util.HashMap;
import java.util.Map;

// 模板捕魚牌桌遊戲設定產生器
public class TableGameResourceModelByJavaDefault extends AbstractTableGameResourceFish {
    public TableGameConfigFish create() {
        // 1. 計算遊戲調控設定
        GameAdjustConfig gameAdjustConfig = super.createGameAdjustConfig();

        // 2. 計算亂數產生器演算法設定
        RngAlgorithmConfig rngAlgorithmConfig = super.createRngAlgorithmConfig();

        // 3. 計算子彈設定對應表
        Map<Integer, Map<Integer, BulletConfigBase>> playToBulletConfigMap = new HashMap<>(){
            {
                put(1, new HashMap<>(){
                    {
                        put(ConstModelByJava.BulletEnumModelByJava.NORMAL.getBulletMechanismCode(), new BulletConfigBase(
                                ConstMathFish.BulletType.NORMAL, new BulletConfigExtendNormal(),
                                ConstMathFish.BulletCostType.RATIO, new BulletCostConfigExtendRatio(ConstMathFish.BulletCostListType.TEN_MULTI_TEN, new BulletCostListConfigExtendTenMultiTen(), new BulletCostExchange(1.0, 1.0)),
                                ConstMathFish.BulletAmountType.INFINITE,
                                ConstMathFish.BulletRtpUseType.NONE, new BulletRtpUseConfigExtendNone()));
                    }
                });
                put(2, new HashMap<>(){
                    {
                        put(ConstModelByJava.BulletEnumModelByJava.NORMAL.getBulletMechanismCode(), new BulletConfigBase(
                                ConstMathFish.BulletType.NORMAL, new BulletConfigExtendNormal(),
                                ConstMathFish.BulletCostType.RATIO, new BulletCostConfigExtendRatio(ConstMathFish.BulletCostListType.TEN, new BulletCostListConfigExtendTen(), new BulletCostExchange(1.0, 1.0)),
                                ConstMathFish.BulletAmountType.INFINITE,
                                ConstMathFish.BulletRtpUseType.NONE, new BulletRtpUseConfigExtendNone()));
                    }
                });
            }
        };

        // 4. 計算打擊設定對應表
        Map<Integer, Map<Integer, HitCtrConfig>> hitCtrConfigMap = new HashMap<>() {{
            put(1, new HashMap<>() {{
                put(1, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(2),
                        ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
            }});
        }};

        // 5. 計算機器人起始金額上下限倍數
        RobotBeginMoneyLimitBase robotBeginMoneyLimitBase = new RobotBeginMoneyLimitBase(500, 5000);

        return new TableGameConfigFish(
                gameAdjustConfig,
                rngAlgorithmConfig,
                playToBulletConfigMap,
                hitCtrConfigMap,
                robotBeginMoneyLimitBase
        );
    }
}
