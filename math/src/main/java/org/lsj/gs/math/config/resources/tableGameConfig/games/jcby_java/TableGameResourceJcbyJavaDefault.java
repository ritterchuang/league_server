package org.lsj.gs.math.config.resources.tableGameConfig.games.jcby_java;

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
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendMultiTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendRandomOdds;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.RobotHitResultLimitBase;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.AwardBulletGtrConfigExtendNone;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.rtpChoiceHlr.enity.RtpChoiceHlrConfigExtendCompanyAdjust;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureCtrConfigExtend.SpecialFeatureCtrConfigExtendNone;
import org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.server.RobotBeginMoneyLimitBase;
import org.lsj.gs.math.games.jcby_java.entity.ConstJcbyJava;

import java.util.HashMap;
import java.util.Map;

// 金蟬捕魚牌桌遊戲設定產生器
public class TableGameResourceJcbyJavaDefault extends AbstractTableGameResourceFish {
    public TableGameConfigFish create() {
        // 1. 計算遊戲調控設定
        GameAdjustConfig gameAdjustConfig = super.createGameAdjustConfig();;

        // 2. 計算亂數產生器演算法設定
        RngAlgorithmConfig rngAlgorithmConfig = super.createRngAlgorithmConfig();

        // 3. 計算子彈設定對應表
        Map<Integer, Map<Integer, BulletConfigBase>> playToBulletConfigMap = new HashMap<>(){
            {
                put(1, new HashMap<>(){
                    {
                        put(ConstJcbyJava.BulletEnumJcbyJava.NORMAL.getBulletMechanismCode(), new BulletConfigBase(
                                ConstMathFish.BulletType.NORMAL, new BulletConfigExtendNormal(),
                                ConstMathFish.BulletCostType.RATIO, new BulletCostConfigExtendRatio(ConstMathFish.BulletCostListType.TEN_MULTI_TEN, new BulletCostListConfigExtendTenMultiTen(), new BulletCostExchange(1.0, 1.0)),
                                ConstMathFish.BulletAmountType.INFINITE,
                                ConstMathFish.BulletRtpUseType.NONE, new BulletRtpUseConfigExtendNone()));
                    }
                });
                put(2, new HashMap<>(){
                    {
                        put(ConstJcbyJava.BulletEnumJcbyJava.NORMAL.getBulletMechanismCode(), new BulletConfigBase(
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
            put(ConstJcbyJava.BulletEnumJcbyJava.NORMAL.getBulletMechanismCode(), new HashMap<>() {{
                put(1, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(2), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(2, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(2), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(3, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(3), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(4, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(4), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(5, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(5), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(6, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(6), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(7, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(7), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(8, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(8), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(9, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(9), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(10, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(10), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(11, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(12), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(12, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(15), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(13, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(18), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(14, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(20), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(15, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(25), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(16, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(30), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(17, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(40), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(18, new HitCtrConfig(ConstMathFish.HitType.RANDOM_ODDS, new HitTypeConfigExtendRandomOdds(new int[]{80, 88, 100, 128, 138, 150}, new int[]{15, 25, 24, 25, 1, 10}), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(19, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(50), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(20, new HitCtrConfig(ConstMathFish.HitType.RANDOM_ODDS, new HitTypeConfigExtendRandomOdds(new int[]{1, 3, 6, 8, 18, 20}, new int[]{10, 20, 20, 20, 10, 20}), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(21, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(70), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(22, new HitCtrConfig(ConstMathFish.HitType.RANDOM_ODDS, new HitTypeConfigExtendRandomOdds(new int[]{60, 80, 100, 128, 168, 180}, new int[]{10, 20, 20, 20, 20, 10}), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(23, new HitCtrConfig(ConstMathFish.HitType.RANDOM_ODDS, new HitTypeConfigExtendRandomOdds(new int[]{100, 125, 150, 168, 200, 288}, new int[]{20, 20, 20, 18, 20, 2}), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(24, new HitCtrConfig(ConstMathFish.HitType.MULTI_TARGET, new HitTypeConfigExtendMultiTarget(new HashMap<>() {{
                    put(1, 2);
                    put(2, 2);
                    put(3, 3);
                    put(4, 4);
                    put(5, 5);
                    put(6, 6);
                    put(7, 7);
                    put(8, 8);
                    put(9, 9);
                    put(10, 10);
                    put(11, 12);
                    put(12, 15);
                    put(13, 18);
                    put(14, 20);
                    put(15, 25);
                    put(16, 30);
                    put(17, 40);
                    put(18, 80);
                    put(19, 50);
                    put(20, 1);
                    put(21, 70);
                    put(22, 60);
                    put(23, 100);
                    put(24, 100);
                    put(25, 100);
                }}, new RobotHitResultLimitBase(100, 200)), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(25, new HitCtrConfig(ConstMathFish.HitType.MULTI_TARGET, new HitTypeConfigExtendMultiTarget(new HashMap<>() {{
                    put(1, 2);
                    put(2, 2);
                    put(3, 3);
                    put(4, 4);
                    put(5, 5);
                    put(6, 6);
                    put(7, 7);
                    put(8, 8);
                    put(9, 9);
                    put(10, 10);
                    put(11, 12);
                    put(12, 15);
                    put(13, 18);
                    put(14, 20);
                    put(15, 25);
                    put(16, 30);
                    put(17, 40);
                    put(18, 80);
                    put(19, 50);
                    put(20, 1);
                    put(21, 70);
                    put(22, 60);
                    put(23, 100);
                    put(24, 100);
                    put(25, 100);
                }}, new RobotHitResultLimitBase(200, 500)), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
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
