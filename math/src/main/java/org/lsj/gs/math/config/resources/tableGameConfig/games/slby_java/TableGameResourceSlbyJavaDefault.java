package org.lsj.gs.math.config.resources.tableGameConfig.games.slby_java;

import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigFish;
import org.lsj.gs.math.config.resources.tableGameConfig.core.AbstractTableGameResourceFish;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.BulletConfigBase;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever.BulletCostExchange;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever.bulletCostConfigExtend.BulletCostConfigExtendFree;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever.bulletCostConfigExtend.BulletCostConfigExtendRatio;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever.bulletCostListConfigExtend.BulletCostListConfigExtendTen;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever.bulletCostListConfigExtend.BulletCostListConfigExtendTenMultiTen;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletRtpUseTypeHlr.enity.sever.BulletRtpUseConfigExtendHave;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletRtpUseTypeHlr.enity.sever.BulletRtpUseConfigExtendNone;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.enity.sever.BulletConfigExtendNormal;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitCtrConfig;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendDragonTreasure;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendFixedOdds;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendRandomOdds;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendWheel;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.AwardBulletGtrConfigExtendNone;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.AwardBulletGtrConfigExtendOneType;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.awardBulletCtrConfigExtend.AwardBulletCtrConfigExtendNormal;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.rtpChoiceHlr.enity.RtpChoiceHlrConfigExtendBullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.rtpChoiceHlr.enity.RtpChoiceHlrConfigExtendCompanyAdjust;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureCtrConfigExtend.SpecialFeatureCtrConfigExtendNone;
import org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.server.RobotBeginMoneyLimitBase;
import org.lsj.gs.math.games.slby_java.enity.ConstSlbyJava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// 神龍捕魚預設牌桌遊戲設定產生器
public class TableGameResourceSlbyJavaDefault extends AbstractTableGameResourceFish {
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
                        put(ConstSlbyJava.BulletEnumSlbyJava.NORMAL.getBulletMechanismCode(), new BulletConfigBase(
                                ConstMathFish.BulletType.NORMAL, new BulletConfigExtendNormal(),
                                ConstMathFish.BulletCostType.RATIO, new BulletCostConfigExtendRatio(ConstMathFish.BulletCostListType.TEN_MULTI_TEN, new BulletCostListConfigExtendTenMultiTen(), new BulletCostExchange(1.0, 1.0)),
                                ConstMathFish.BulletAmountType.INFINITE,
                                ConstMathFish.BulletRtpUseType.NONE, new BulletRtpUseConfigExtendNone()
                        ));
                        put(ConstSlbyJava.BulletEnumSlbyJava.BAZOOKA.getBulletMechanismCode(), new BulletConfigBase(
                                ConstMathFish.BulletType.NORMAL, new BulletConfigExtendNormal(),
                                ConstMathFish.BulletCostType.FREE, new BulletCostConfigExtendFree(),
                                ConstMathFish.BulletAmountType.FINITE,
                                ConstMathFish.BulletRtpUseType.HAVE, new BulletRtpUseConfigExtendHave(1.0)
                        ));
                        put(ConstSlbyJava.BulletEnumSlbyJava.MISSILE.getBulletMechanismCode(), new BulletConfigBase(
                                ConstMathFish.BulletType.NORMAL, new BulletConfigExtendNormal(),
                                ConstMathFish.BulletCostType.FREE, new BulletCostConfigExtendFree(),
                                ConstMathFish.BulletAmountType.FINITE,
                                ConstMathFish.BulletRtpUseType.HAVE, new BulletRtpUseConfigExtendHave(2.0)
                        ));
                    }
                });
                put(2, new HashMap<>(){
                    {
                        put(ConstSlbyJava.BulletEnumSlbyJava.NORMAL.getBulletMechanismCode(), new BulletConfigBase(
                                ConstMathFish.BulletType.NORMAL, new BulletConfigExtendNormal(),
                                ConstMathFish.BulletCostType.RATIO, new BulletCostConfigExtendRatio(ConstMathFish.BulletCostListType.TEN, new BulletCostListConfigExtendTen(), new BulletCostExchange(1.0, 1.0)),
                                ConstMathFish.BulletAmountType.INFINITE,
                                ConstMathFish.BulletRtpUseType.NONE, new BulletRtpUseConfigExtendNone()
                        ));
                        put(ConstSlbyJava.BulletEnumSlbyJava.BAZOOKA.getBulletMechanismCode(), new BulletConfigBase(
                                ConstMathFish.BulletType.NORMAL, new BulletConfigExtendNormal(),
                                ConstMathFish.BulletCostType.FREE, new BulletCostConfigExtendFree(),
                                ConstMathFish.BulletAmountType.FINITE,
                                ConstMathFish.BulletRtpUseType.HAVE, new BulletRtpUseConfigExtendHave(1.0)
                        ));
                        put(ConstSlbyJava.BulletEnumSlbyJava.MISSILE.getBulletMechanismCode(), new BulletConfigBase(
                                ConstMathFish.BulletType.NORMAL, new BulletConfigExtendNormal(),
                                ConstMathFish.BulletCostType.FREE, new BulletCostConfigExtendFree(),
                                ConstMathFish.BulletAmountType.FINITE,
                                ConstMathFish.BulletRtpUseType.HAVE, new BulletRtpUseConfigExtendHave(2.0)
                        ));
                    }
                });
            }
        };

        // 4. 計算打擊設定對應表
        Map<Integer, Map<Integer, HitCtrConfig>> hitCtrConfigMap = new HashMap<>() {{
            put(ConstSlbyJava.BulletEnumSlbyJava.NORMAL.getBulletMechanismCode(), new HashMap<>() {{
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
                put(18, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(50), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(19, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(80), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(20, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(100), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(21, new HitCtrConfig(ConstMathFish.HitType.RANDOM_ODDS, new HitTypeConfigExtendRandomOdds(new int[]{50, 75, 100, 125, 150}, new int[]{1, 1, 1, 1, 1}), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));

                put(22, new HitCtrConfig(ConstMathFish.HitType.RANDOM_ODDS,
                        new HitTypeConfigExtendRandomOdds(new int[]{100, 150, 200, 250, 300}, new int[]{36, 36, 12, 10, 6}),
                        ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.COMPANY_ADJUST,
                        new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(23, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS,
                        new HitTypeConfigExtendFixedOdds(10),
                        ConstMathFish.SpecialFeatureEnumType.NONE,
                        new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.ONE_TYPE,
                        new AwardBulletGtrConfigExtendOneType(ConstMathFish.AwardBulletCtrType.NORMAL,
                                new AwardBulletCtrConfigExtendNormal(),
                                ConstSlbyJava.BulletEnumSlbyJava.BAZOOKA.getBulletMechanismCode(),
                                999,
                                new int[]{20, 40, 60, 80, 100},
                                new int[]{1, 1, 1, 1, 1}),
                        ConstMathFish.RtpChoiceType.COMPANY_ADJUST,
                        new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(24, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS,
                        new HitTypeConfigExtendFixedOdds(10),
                        ConstMathFish.SpecialFeatureEnumType.NONE,
                        new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.ONE_TYPE,
                        new AwardBulletGtrConfigExtendOneType(ConstMathFish.AwardBulletCtrType.NORMAL,
                                new AwardBulletCtrConfigExtendNormal(),
                                ConstSlbyJava.BulletEnumSlbyJava.MISSILE.getBulletMechanismCode(),
                                25,
                                new int[]{25},
                                new int[]{1}),
                        ConstMathFish.RtpChoiceType.COMPANY_ADJUST,
                        new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(25, new HitCtrConfig(ConstMathFish.HitType.WHEEL,
                        new HitTypeConfigExtendWheel(new int[]{40, 200, 160, 120, 80, 160, 120, 160}, new int[]{10, 10, 4, 5, 10, 3, 5, 3}),
                        ConstMathFish.SpecialFeatureEnumType.NONE,
                        new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.COMPANY_ADJUST,
                        new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(26, new HitCtrConfig(ConstMathFish.HitType.DRAGON_TREASURE,
                        new HitTypeConfigExtendDragonTreasure(
                                new int[]{1, 2, 3, 4, 5, 6, 7},
                                new int[][]{{3, 4, 5, 6, 7}, {10, 15, 30, 30, 15}},
                                new HashMap<>() {{
                                    put(3, new ArrayList<>() {{
                                        add(new int[]{5, 5, 6});
                                        add(new int[]{5, 5, 7});
                                        add(new int[]{6, 6, 4});
                                        add(new int[]{6, 6, 5});
                                        add(new int[]{6, 6, 7});
                                        add(new int[]{7, 7, 4});
                                        add(new int[]{7, 7, 5});
                                        add(new int[]{7, 7, 6});
                                        add(new int[]{7, 7, 1});
                                        add(new int[]{7, 7, 2});
                                        add(new int[]{7, 7, 3});
                                        add(new int[]{6, 6, 3});
                                    }});
                                    put(4, new ArrayList<>() {{
                                        add(new int[]{4, 4, 5, 6});
                                        add(new int[]{4, 4, 5, 7});
                                        add(new int[]{4, 4, 6, 7});
                                        add(new int[]{5, 5, 4, 6});
                                        add(new int[]{5, 5, 4, 7});
                                        add(new int[]{5, 5, 6, 7});
                                        add(new int[]{6, 6, 4, 5});
                                        add(new int[]{6, 6, 4, 7});
                                        add(new int[]{6, 6, 5, 7});
                                        add(new int[]{7, 7, 4, 5});
                                        add(new int[]{7, 7, 4, 6});
                                        add(new int[]{7, 7, 5, 6});
                                        add(new int[]{4, 4, 1, 2});
                                        add(new int[]{4, 4, 1, 3});
                                        add(new int[]{4, 4, 2, 3});
                                        add(new int[]{5, 5, 1, 2});
                                        add(new int[]{5, 5, 1, 3});
                                        add(new int[]{5, 5, 1, 4});
                                        add(new int[]{5, 5, 2, 3});
                                        add(new int[]{5, 5, 2, 4});
                                        add(new int[]{6, 6, 1, 2});
                                        add(new int[]{6, 6, 1, 3});
                                        add(new int[]{6, 6, 1, 4});
                                        add(new int[]{6, 6, 2, 3});
                                        add(new int[]{6, 6, 2, 4});
                                        add(new int[]{7, 7, 1, 2});
                                        add(new int[]{7, 7, 1, 3});
                                        add(new int[]{7, 7, 2, 3});
                                    }});
                                    put(5, new ArrayList<>() {{
                                        add(new int[]{3, 3, 4, 5, 6});
                                        add(new int[]{3, 3, 4, 5, 7});
                                        add(new int[]{3, 3, 4, 6, 7});
                                        add(new int[]{3, 3, 5, 6, 7});
                                        add(new int[]{4, 4, 3, 5, 6});
                                        add(new int[]{4, 4, 3, 5, 7});
                                        add(new int[]{4, 4, 3, 6, 7});
                                        add(new int[]{4, 4, 5, 6, 7});
                                        add(new int[]{5, 5, 3, 4, 6});
                                        add(new int[]{5, 5, 3, 4, 7});
                                        add(new int[]{5, 5, 3, 6, 7});
                                        add(new int[]{5, 5, 4, 6, 7});
                                        add(new int[]{6, 6, 3, 4, 5});
                                        add(new int[]{6, 6, 3, 4, 7});
                                        add(new int[]{6, 6, 3, 5, 7});
                                        add(new int[]{6, 6, 4, 5, 7});
                                        add(new int[]{7, 7, 3, 4, 5});
                                        add(new int[]{7, 7, 3, 4, 6});
                                        add(new int[]{7, 7, 3, 5, 6});
                                        add(new int[]{7, 7, 4, 5, 6});
                                        add(new int[]{3, 3, 1, 2, 4});
                                        add(new int[]{3, 3, 1, 2, 5});
                                        add(new int[]{3, 3, 1, 2, 6});
                                        add(new int[]{3, 3, 1, 2, 7});
                                        add(new int[]{3, 3, 2, 4, 5});
                                        add(new int[]{4, 4, 1, 2, 3});
                                        add(new int[]{4, 4, 1, 2, 5});
                                        add(new int[]{4, 4, 1, 2, 6});
                                        add(new int[]{4, 4, 1, 2, 7});
                                        add(new int[]{5, 5, 1, 2, 3});
                                        add(new int[]{5, 5, 1, 2, 4});
                                        add(new int[]{5, 5, 1, 2, 6});
                                        add(new int[]{5, 5, 1, 2, 7});
                                        add(new int[]{6, 6, 1, 2, 3});
                                        add(new int[]{6, 6, 1, 2, 4});
                                        add(new int[]{6, 6, 1, 2, 5});
                                        add(new int[]{6, 6, 1, 2, 7});
                                        add(new int[]{7, 7, 1, 2, 3});
                                        add(new int[]{7, 7, 1, 2, 4});
                                        add(new int[]{7, 7, 1, 2, 5});
                                        add(new int[]{7, 7, 1, 2, 6});
                                    }});
                                    put(6, new ArrayList<>() {{
                                        add(new int[]{1, 1, 4, 5, 6, 7});
                                        add(new int[]{1, 1, 3, 5, 6, 7});
                                        add(new int[]{1, 1, 2, 5, 6, 7});
                                        add(new int[]{2, 2, 3, 4, 5, 6});
                                        add(new int[]{2, 2, 3, 4, 5, 7});
                                        add(new int[]{2, 2, 4, 5, 6, 7});
                                        add(new int[]{3, 3, 2, 4, 5, 6});
                                        add(new int[]{3, 3, 2, 4, 5, 7});
                                        add(new int[]{3, 3, 2, 5, 6, 7});
                                        add(new int[]{3, 3, 4, 5, 6, 7});
                                        add(new int[]{4, 4, 1, 2, 6, 7});
                                        add(new int[]{4, 4, 1, 2, 5, 7});
                                        add(new int[]{4, 4, 2, 3, 5, 6});
                                        add(new int[]{4, 4, 2, 3, 5, 7});
                                        add(new int[]{4, 4, 2, 3, 6, 7});
                                        add(new int[]{4, 4, 3, 5, 6, 7});
                                        add(new int[]{5, 5, 1, 2, 3, 6});
                                        add(new int[]{5, 5, 1, 2, 3, 7});
                                        add(new int[]{5, 5, 1, 2, 4, 6});
                                        add(new int[]{5, 5, 1, 2, 4, 7});
                                        add(new int[]{5, 5, 2, 3, 4, 6});
                                        add(new int[]{5, 5, 2, 3, 4, 7});
                                        add(new int[]{5, 5, 2, 4, 6, 7});
                                        add(new int[]{5, 5, 3, 4, 6, 7});
                                        add(new int[]{6, 6, 1, 2, 3, 4});
                                        add(new int[]{6, 6, 1, 2, 3, 5});
                                        add(new int[]{6, 6, 1, 2, 4, 5});
                                        add(new int[]{6, 6, 1, 2, 4, 7});
                                        add(new int[]{6, 6, 2, 3, 4, 5});
                                        add(new int[]{6, 6, 2, 3, 4, 7});
                                        add(new int[]{6, 6, 2, 3, 5, 7});
                                        add(new int[]{6, 6, 3, 4, 5, 7});
                                        add(new int[]{7, 7, 1, 2, 3, 4});
                                        add(new int[]{7, 7, 1, 2, 3, 5});
                                        add(new int[]{7, 7, 1, 2, 4, 5});
                                        add(new int[]{7, 7, 1, 2, 4, 6});
                                        add(new int[]{7, 7, 1, 2, 5, 6});
                                        add(new int[]{7, 7, 2, 3, 4, 5});
                                        add(new int[]{7, 7, 2, 3, 4, 6});
                                        add(new int[]{7, 7, 2, 4, 5, 6});
                                        add(new int[]{7, 7, 3, 4, 5, 6});
                                    }});
                                    put(7, new ArrayList<>() {{
                                        add(new int[]{1, 1, 2, 3, 4, 5, 6});
                                        add(new int[]{1, 1, 2, 3, 4, 5, 7});
                                        add(new int[]{1, 1, 2, 3, 4, 6, 7});
                                        add(new int[]{1, 1, 2, 3, 5, 6, 7});
                                        add(new int[]{1, 1, 2, 4, 5, 6, 7});
                                        add(new int[]{1, 1, 3, 4, 5, 6, 7});
                                        add(new int[]{2, 2, 1, 3, 4, 5, 6});
                                        add(new int[]{2, 2, 1, 3, 4, 5, 7});
                                        add(new int[]{2, 2, 1, 3, 5, 6, 7});
                                        add(new int[]{2, 2, 1, 4, 5, 6, 7});
                                        add(new int[]{2, 2, 3, 4, 5, 6, 7});
                                        add(new int[]{3, 3, 1, 2, 4, 5, 6});
                                        add(new int[]{3, 3, 1, 2, 4, 5, 7});
                                        add(new int[]{3, 3, 1, 2, 4, 6, 7});
                                        add(new int[]{3, 3, 1, 4, 5, 6, 7});
                                        add(new int[]{3, 3, 2, 4, 5, 6, 7});
                                        add(new int[]{4, 4, 1, 2, 3, 5, 6});
                                        add(new int[]{5, 5, 1, 2, 3, 4, 6});
                                        add(new int[]{6, 6, 1, 2, 3, 4, 5});
                                        add(new int[]{7, 7, 1, 2, 3, 4, 5});
                                        add(new int[]{1, 2, 3, 4, 5, 6, 7});
                                    }});
                                }},
                                new HashMap<>() {{
                                    put(3, new int[]{5, 10, 5, 10, 15, 15, 15, 5, 10, 5, 10, 10});
                                    put(4, new int[]{15, 15, 15, 15, 15, 15, 15, 15, 15, 20, 20, 20, 10, 10, 10, 10, 10, 10, 10, 15, 10, 15, 15, 15, 15, 15, 15, 15});
                                    put(5, new int[]{5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 5, 10, 5, 5, 5, 5, 5, 5, 5});
                                    put(6, new int[]{10, 10, 10, 10, 10, 5, 10, 10, 5, 5, 10, 10, 10, 10, 5, 5, 10, 10, 10, 10, 10, 5, 5, 5, 10, 10, 10, 5, 5, 5, 5, 5, 10, 10, 5, 5, 5, 5, 5, 5, 5});
                                    put(7, new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 20, 10, 10, 10, 20, 20, 10, 10, 10, 20, 120});
                                }},
                                new int[][]{{2, 3, 4, 5, 6, 7}, {18, 24, 24, 24, 24, 6}}
                        ),
                        ConstMathFish.SpecialFeatureEnumType.NONE,
                        new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.COMPANY_ADJUST,
                        new RtpChoiceHlrConfigExtendCompanyAdjust()));
            }});
            put(ConstSlbyJava.BulletEnumSlbyJava.BAZOOKA.getBulletMechanismCode(), new HashMap<>() {{
                put(1, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(2), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));

                put(2, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(2), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(3, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(3), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(4, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(4), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(5, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(5), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(6, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(6), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(7, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(7), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(8, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(8), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(9, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(9), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(10, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(10), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));

                put(11, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(12), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(12, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(15), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(13, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(18), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(14, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(20), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(15, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(25), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(16, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(30), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));

                put(17, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(40), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(18, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(50), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(19, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(80), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(20, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(100), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(21, new HitCtrConfig(ConstMathFish.HitType.RANDOM_ODDS, new HitTypeConfigExtendRandomOdds(new int[]{50, 75, 100, 125, 150}, new int[]{1, 1, 1, 1, 1}), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));

                put(22, new HitCtrConfig(ConstMathFish.HitType.RANDOM_ODDS,
                        new HitTypeConfigExtendRandomOdds(new int[]{100, 150, 200, 250, 300}, new int[]{36, 36, 12, 10, 6}),
                        ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE,
                        new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.BULLET,
                        new RtpChoiceHlrConfigExtendBullet()));
                put(23, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS,
                        new HitTypeConfigExtendFixedOdds(10),
                        ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.ONE_TYPE,
                        new AwardBulletGtrConfigExtendOneType(ConstMathFish.AwardBulletCtrType.NORMAL, new AwardBulletCtrConfigExtendNormal(), ConstSlbyJava.BulletEnumSlbyJava.BAZOOKA.getBulletMechanismCode(), 999, new int[]{20, 40, 60, 80, 100}, new int[]{1, 1, 1, 1, 1}),
                        ConstMathFish.RtpChoiceType.BULLET,
                        new RtpChoiceHlrConfigExtendBullet()));
                put(24, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS,
                        new HitTypeConfigExtendFixedOdds(60),
                        ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE,
                        new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.BULLET,
                        new RtpChoiceHlrConfigExtendBullet()));
                put(25, new HitCtrConfig(ConstMathFish.HitType.WHEEL,
                        new HitTypeConfigExtendWheel(new int[]{40, 200, 160, 120, 80, 160, 120, 160}, new int[]{10, 10, 4, 5, 10, 3, 5, 3}),
                        ConstMathFish.SpecialFeatureEnumType.NONE,
                        new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE,
                        new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.BULLET,
                        new RtpChoiceHlrConfigExtendBullet()));
                put(26, new HitCtrConfig(ConstMathFish.HitType.DRAGON_TREASURE,
                        new HitTypeConfigExtendDragonTreasure(
                                new int[]{1, 2, 3, 4, 5, 6, 7},
                                new int[][]{{3, 4, 5, 6, 7}, {10, 15, 30, 30, 15}},
                                new HashMap<>() {{
                                    put(3, new ArrayList<>() {{
                                        add(new int[]{5, 5, 6});
                                        add(new int[]{5, 5, 7});
                                        add(new int[]{6, 6, 4});
                                        add(new int[]{6, 6, 5});
                                        add(new int[]{6, 6, 7});
                                        add(new int[]{7, 7, 4});
                                        add(new int[]{7, 7, 5});
                                        add(new int[]{7, 7, 6});
                                        add(new int[]{7, 7, 1});
                                        add(new int[]{7, 7, 2});
                                        add(new int[]{7, 7, 3});
                                        add(new int[]{6, 6, 3});
                                    }});
                                    put(4, new ArrayList<>() {{
                                        add(new int[]{4, 4, 5, 6});
                                        add(new int[]{4, 4, 5, 7});
                                        add(new int[]{4, 4, 6, 7});
                                        add(new int[]{5, 5, 4, 6});
                                        add(new int[]{5, 5, 4, 7});
                                        add(new int[]{5, 5, 6, 7});
                                        add(new int[]{6, 6, 4, 5});
                                        add(new int[]{6, 6, 4, 7});
                                        add(new int[]{6, 6, 5, 7});
                                        add(new int[]{7, 7, 4, 5});
                                        add(new int[]{7, 7, 4, 6});
                                        add(new int[]{7, 7, 5, 6});
                                        add(new int[]{4, 4, 1, 2});
                                        add(new int[]{4, 4, 1, 3});
                                        add(new int[]{4, 4, 2, 3});
                                        add(new int[]{5, 5, 1, 2});
                                        add(new int[]{5, 5, 1, 3});
                                        add(new int[]{5, 5, 1, 4});
                                        add(new int[]{5, 5, 2, 3});
                                        add(new int[]{5, 5, 2, 4});
                                        add(new int[]{6, 6, 1, 2});
                                        add(new int[]{6, 6, 1, 3});
                                        add(new int[]{6, 6, 1, 4});
                                        add(new int[]{6, 6, 2, 3});
                                        add(new int[]{6, 6, 2, 4});
                                        add(new int[]{7, 7, 1, 2});
                                        add(new int[]{7, 7, 1, 3});
                                        add(new int[]{7, 7, 2, 3});
                                    }});
                                    put(5, new ArrayList<>() {{
                                        add(new int[]{3, 3, 4, 5, 6});
                                        add(new int[]{3, 3, 4, 5, 7});
                                        add(new int[]{3, 3, 4, 6, 7});
                                        add(new int[]{3, 3, 5, 6, 7});
                                        add(new int[]{4, 4, 3, 5, 6});
                                        add(new int[]{4, 4, 3, 5, 7});
                                        add(new int[]{4, 4, 3, 6, 7});
                                        add(new int[]{4, 4, 5, 6, 7});
                                        add(new int[]{5, 5, 3, 4, 6});
                                        add(new int[]{5, 5, 3, 4, 7});
                                        add(new int[]{5, 5, 3, 6, 7});
                                        add(new int[]{5, 5, 4, 6, 7});
                                        add(new int[]{6, 6, 3, 4, 5});
                                        add(new int[]{6, 6, 3, 4, 7});
                                        add(new int[]{6, 6, 3, 5, 7});
                                        add(new int[]{6, 6, 4, 5, 7});
                                        add(new int[]{7, 7, 3, 4, 5});
                                        add(new int[]{7, 7, 3, 4, 6});
                                        add(new int[]{7, 7, 3, 5, 6});
                                        add(new int[]{7, 7, 4, 5, 6});
                                        add(new int[]{3, 3, 1, 2, 4});
                                        add(new int[]{3, 3, 1, 2, 5});
                                        add(new int[]{3, 3, 1, 2, 6});
                                        add(new int[]{3, 3, 1, 2, 7});
                                        add(new int[]{3, 3, 2, 4, 5});
                                        add(new int[]{4, 4, 1, 2, 3});
                                        add(new int[]{4, 4, 1, 2, 5});
                                        add(new int[]{4, 4, 1, 2, 6});
                                        add(new int[]{4, 4, 1, 2, 7});
                                        add(new int[]{5, 5, 1, 2, 3});
                                        add(new int[]{5, 5, 1, 2, 4});
                                        add(new int[]{5, 5, 1, 2, 6});
                                        add(new int[]{5, 5, 1, 2, 7});
                                        add(new int[]{6, 6, 1, 2, 3});
                                        add(new int[]{6, 6, 1, 2, 4});
                                        add(new int[]{6, 6, 1, 2, 5});
                                        add(new int[]{6, 6, 1, 2, 7});
                                        add(new int[]{7, 7, 1, 2, 3});
                                        add(new int[]{7, 7, 1, 2, 4});
                                        add(new int[]{7, 7, 1, 2, 5});
                                        add(new int[]{7, 7, 1, 2, 6});
                                    }});
                                    put(6, new ArrayList<>() {{
                                        add(new int[]{1, 1, 4, 5, 6, 7});
                                        add(new int[]{1, 1, 3, 5, 6, 7});
                                        add(new int[]{1, 1, 2, 5, 6, 7});
                                        add(new int[]{2, 2, 3, 4, 5, 6});
                                        add(new int[]{2, 2, 3, 4, 5, 7});
                                        add(new int[]{2, 2, 4, 5, 6, 7});
                                        add(new int[]{3, 3, 2, 4, 5, 6});
                                        add(new int[]{3, 3, 2, 4, 5, 7});
                                        add(new int[]{3, 3, 2, 5, 6, 7});
                                        add(new int[]{3, 3, 4, 5, 6, 7});
                                        add(new int[]{4, 4, 1, 2, 6, 7});
                                        add(new int[]{4, 4, 1, 2, 5, 7});
                                        add(new int[]{4, 4, 2, 3, 5, 6});
                                        add(new int[]{4, 4, 2, 3, 5, 7});
                                        add(new int[]{4, 4, 2, 3, 6, 7});
                                        add(new int[]{4, 4, 3, 5, 6, 7});
                                        add(new int[]{5, 5, 1, 2, 3, 6});
                                        add(new int[]{5, 5, 1, 2, 3, 7});
                                        add(new int[]{5, 5, 1, 2, 4, 6});
                                        add(new int[]{5, 5, 1, 2, 4, 7});
                                        add(new int[]{5, 5, 2, 3, 4, 6});
                                        add(new int[]{5, 5, 2, 3, 4, 7});
                                        add(new int[]{5, 5, 2, 4, 6, 7});
                                        add(new int[]{5, 5, 3, 4, 6, 7});
                                        add(new int[]{6, 6, 1, 2, 3, 4});
                                        add(new int[]{6, 6, 1, 2, 3, 5});
                                        add(new int[]{6, 6, 1, 2, 4, 5});
                                        add(new int[]{6, 6, 1, 2, 4, 7});
                                        add(new int[]{6, 6, 2, 3, 4, 5});
                                        add(new int[]{6, 6, 2, 3, 4, 7});
                                        add(new int[]{6, 6, 2, 3, 5, 7});
                                        add(new int[]{6, 6, 3, 4, 5, 7});
                                        add(new int[]{7, 7, 1, 2, 3, 4});
                                        add(new int[]{7, 7, 1, 2, 3, 5});
                                        add(new int[]{7, 7, 1, 2, 4, 5});
                                        add(new int[]{7, 7, 1, 2, 4, 6});
                                        add(new int[]{7, 7, 1, 2, 5, 6});
                                        add(new int[]{7, 7, 2, 3, 4, 5});
                                        add(new int[]{7, 7, 2, 3, 4, 6});
                                        add(new int[]{7, 7, 2, 4, 5, 6});
                                        add(new int[]{7, 7, 3, 4, 5, 6});
                                    }});
                                    put(7, new ArrayList<>() {{
                                        add(new int[]{1, 1, 2, 3, 4, 5, 6});
                                        add(new int[]{1, 1, 2, 3, 4, 5, 7});
                                        add(new int[]{1, 1, 2, 3, 4, 6, 7});
                                        add(new int[]{1, 1, 2, 3, 5, 6, 7});
                                        add(new int[]{1, 1, 2, 4, 5, 6, 7});
                                        add(new int[]{1, 1, 3, 4, 5, 6, 7});
                                        add(new int[]{2, 2, 1, 3, 4, 5, 6});
                                        add(new int[]{2, 2, 1, 3, 4, 5, 7});
                                        add(new int[]{2, 2, 1, 3, 5, 6, 7});
                                        add(new int[]{2, 2, 1, 4, 5, 6, 7});
                                        add(new int[]{2, 2, 3, 4, 5, 6, 7});
                                        add(new int[]{3, 3, 1, 2, 4, 5, 6});
                                        add(new int[]{3, 3, 1, 2, 4, 5, 7});
                                        add(new int[]{3, 3, 1, 2, 4, 6, 7});
                                        add(new int[]{3, 3, 1, 4, 5, 6, 7});
                                        add(new int[]{3, 3, 2, 4, 5, 6, 7});
                                        add(new int[]{4, 4, 1, 2, 3, 5, 6});
                                        add(new int[]{5, 5, 1, 2, 3, 4, 6});
                                        add(new int[]{6, 6, 1, 2, 3, 4, 5});
                                        add(new int[]{7, 7, 1, 2, 3, 4, 5});
                                        add(new int[]{1, 2, 3, 4, 5, 6, 7});
                                    }});
                                }},
                                new HashMap<>() {{
                                    put(3, new int[]{5, 10, 5, 10, 15, 15, 15, 5, 10, 5, 10, 10});
                                    put(4, new int[]{15, 15, 15, 15, 15, 15, 15, 15, 15, 20, 20, 20, 10, 10, 10, 10, 10, 10, 10, 15, 10, 15, 15, 15, 15, 15, 15, 15});
                                    put(5, new int[]{5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 5, 10, 5, 5, 5, 5, 5, 5, 5});
                                    put(6, new int[]{10, 10, 10, 10, 10, 5, 10, 10, 5, 5, 10, 10, 10, 10, 5, 5, 10, 10, 10, 10, 10, 5, 5, 5, 10, 10, 10, 5, 5, 5, 5, 5, 10, 10, 5, 5, 5, 5, 5, 5, 5});
                                    put(7, new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 20, 10, 10, 10, 20, 20, 10, 10, 10, 20, 120});
                                }},
                                new int[][]{{2, 3, 4, 5, 6, 7}, {18, 24, 24, 24, 24, 6}}
                        ),
                        ConstMathFish.SpecialFeatureEnumType.NONE,
                        new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.BULLET,
                        new RtpChoiceHlrConfigExtendBullet()));
            }});
            put(ConstSlbyJava.BulletEnumSlbyJava.MISSILE.getBulletMechanismCode(), new HashMap<>() {{
                put(1, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(2), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));

                put(2, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(2), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(3, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(3), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(4, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(4), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(5, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(5), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(6, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(6), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(7, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(7), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(8, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(8), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(9, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(9), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(10, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(10), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));

                put(11, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(12), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(12, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(15), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(13, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(18), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(14, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(20), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(15, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(25), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(16, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(30), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));

                put(17, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(40), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(18, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(50), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(19, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(80), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));

                put(20, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(100), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));

                put(21, new HitCtrConfig(ConstMathFish.HitType.RANDOM_ODDS, new HitTypeConfigExtendRandomOdds(new int[]{50, 75, 100, 125, 150}, new int[]{1, 1, 1, 1, 1}), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(22, new HitCtrConfig(ConstMathFish.HitType.RANDOM_ODDS,
                        new HitTypeConfigExtendRandomOdds(new int[]{100, 150, 200, 250, 300}, new int[]{36, 36, 12, 10, 6}),
                        ConstMathFish.SpecialFeatureEnumType.NONE,
                        new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.BULLET,
                        new RtpChoiceHlrConfigExtendBullet()));
                put(23, new HitCtrConfig(ConstMathFish.HitType.RANDOM_ODDS,
                        new HitTypeConfigExtendRandomOdds(new int[]{30, 50, 70, 90, 110}, new int[]{1, 1, 1, 1, 1}),
                        ConstMathFish.SpecialFeatureEnumType.NONE,
                        new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE,
                        new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.BULLET,
                        new RtpChoiceHlrConfigExtendBullet()));
                put(24, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS,
                        new HitTypeConfigExtendFixedOdds(60),
                        ConstMathFish.SpecialFeatureEnumType.NONE,
                        new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE,
                        new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.BULLET,
                        new RtpChoiceHlrConfigExtendBullet()));
                put(25, new HitCtrConfig(ConstMathFish.HitType.WHEEL,
                        new HitTypeConfigExtendWheel(new int[]{40, 200, 160, 120, 80, 160, 120, 160}, new int[]{10, 10, 4, 5, 10, 3, 5, 3}),
                        ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE,
                        new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.BULLET,
                        new RtpChoiceHlrConfigExtendBullet()));
                put(26, new HitCtrConfig(ConstMathFish.HitType.DRAGON_TREASURE,
                        new HitTypeConfigExtendDragonTreasure(
                                new int[]{1, 2, 3, 4, 5, 6, 7},
                                new int[][]{{3, 4, 5, 6, 7}, {10, 15, 30, 30, 15}},
                                new HashMap<>() {{
                                    put(3, new ArrayList<>() {{
                                        add(new int[]{5, 5, 6});
                                        add(new int[]{5, 5, 7});
                                        add(new int[]{6, 6, 4});
                                        add(new int[]{6, 6, 5});
                                        add(new int[]{6, 6, 7});
                                        add(new int[]{7, 7, 4});
                                        add(new int[]{7, 7, 5});
                                        add(new int[]{7, 7, 6});
                                        add(new int[]{7, 7, 1});
                                        add(new int[]{7, 7, 2});
                                        add(new int[]{7, 7, 3});
                                        add(new int[]{6, 6, 3});
                                    }});
                                    put(4, new ArrayList<>() {{
                                        add(new int[]{4, 4, 5, 6});
                                        add(new int[]{4, 4, 5, 7});
                                        add(new int[]{4, 4, 6, 7});
                                        add(new int[]{5, 5, 4, 6});
                                        add(new int[]{5, 5, 4, 7});
                                        add(new int[]{5, 5, 6, 7});
                                        add(new int[]{6, 6, 4, 5});
                                        add(new int[]{6, 6, 4, 7});
                                        add(new int[]{6, 6, 5, 7});
                                        add(new int[]{7, 7, 4, 5});
                                        add(new int[]{7, 7, 4, 6});
                                        add(new int[]{7, 7, 5, 6});
                                        add(new int[]{4, 4, 1, 2});
                                        add(new int[]{4, 4, 1, 3});
                                        add(new int[]{4, 4, 2, 3});
                                        add(new int[]{5, 5, 1, 2});
                                        add(new int[]{5, 5, 1, 3});
                                        add(new int[]{5, 5, 1, 4});
                                        add(new int[]{5, 5, 2, 3});
                                        add(new int[]{5, 5, 2, 4});
                                        add(new int[]{6, 6, 1, 2});
                                        add(new int[]{6, 6, 1, 3});
                                        add(new int[]{6, 6, 1, 4});
                                        add(new int[]{6, 6, 2, 3});
                                        add(new int[]{6, 6, 2, 4});
                                        add(new int[]{7, 7, 1, 2});
                                        add(new int[]{7, 7, 1, 3});
                                        add(new int[]{7, 7, 2, 3});
                                    }});
                                    put(5, new ArrayList<>() {{
                                        add(new int[]{3, 3, 4, 5, 6});
                                        add(new int[]{3, 3, 4, 5, 7});
                                        add(new int[]{3, 3, 4, 6, 7});
                                        add(new int[]{3, 3, 5, 6, 7});
                                        add(new int[]{4, 4, 3, 5, 6});
                                        add(new int[]{4, 4, 3, 5, 7});
                                        add(new int[]{4, 4, 3, 6, 7});
                                        add(new int[]{4, 4, 5, 6, 7});
                                        add(new int[]{5, 5, 3, 4, 6});
                                        add(new int[]{5, 5, 3, 4, 7});
                                        add(new int[]{5, 5, 3, 6, 7});
                                        add(new int[]{5, 5, 4, 6, 7});
                                        add(new int[]{6, 6, 3, 4, 5});
                                        add(new int[]{6, 6, 3, 4, 7});
                                        add(new int[]{6, 6, 3, 5, 7});
                                        add(new int[]{6, 6, 4, 5, 7});
                                        add(new int[]{7, 7, 3, 4, 5});
                                        add(new int[]{7, 7, 3, 4, 6});
                                        add(new int[]{7, 7, 3, 5, 6});
                                        add(new int[]{7, 7, 4, 5, 6});
                                        add(new int[]{3, 3, 1, 2, 4});
                                        add(new int[]{3, 3, 1, 2, 5});
                                        add(new int[]{3, 3, 1, 2, 6});
                                        add(new int[]{3, 3, 1, 2, 7});
                                        add(new int[]{3, 3, 2, 4, 5});
                                        add(new int[]{4, 4, 1, 2, 3});
                                        add(new int[]{4, 4, 1, 2, 5});
                                        add(new int[]{4, 4, 1, 2, 6});
                                        add(new int[]{4, 4, 1, 2, 7});
                                        add(new int[]{5, 5, 1, 2, 3});
                                        add(new int[]{5, 5, 1, 2, 4});
                                        add(new int[]{5, 5, 1, 2, 6});
                                        add(new int[]{5, 5, 1, 2, 7});
                                        add(new int[]{6, 6, 1, 2, 3});
                                        add(new int[]{6, 6, 1, 2, 4});
                                        add(new int[]{6, 6, 1, 2, 5});
                                        add(new int[]{6, 6, 1, 2, 7});
                                        add(new int[]{7, 7, 1, 2, 3});
                                        add(new int[]{7, 7, 1, 2, 4});
                                        add(new int[]{7, 7, 1, 2, 5});
                                        add(new int[]{7, 7, 1, 2, 6});
                                    }});
                                    put(6, new ArrayList<>() {{
                                        add(new int[]{1, 1, 4, 5, 6, 7});
                                        add(new int[]{1, 1, 3, 5, 6, 7});
                                        add(new int[]{1, 1, 2, 5, 6, 7});
                                        add(new int[]{2, 2, 3, 4, 5, 6});
                                        add(new int[]{2, 2, 3, 4, 5, 7});
                                        add(new int[]{2, 2, 4, 5, 6, 7});
                                        add(new int[]{3, 3, 2, 4, 5, 6});
                                        add(new int[]{3, 3, 2, 4, 5, 7});
                                        add(new int[]{3, 3, 2, 5, 6, 7});
                                        add(new int[]{3, 3, 4, 5, 6, 7});
                                        add(new int[]{4, 4, 1, 2, 6, 7});
                                        add(new int[]{4, 4, 1, 2, 5, 7});
                                        add(new int[]{4, 4, 2, 3, 5, 6});
                                        add(new int[]{4, 4, 2, 3, 5, 7});
                                        add(new int[]{4, 4, 2, 3, 6, 7});
                                        add(new int[]{4, 4, 3, 5, 6, 7});
                                        add(new int[]{5, 5, 1, 2, 3, 6});
                                        add(new int[]{5, 5, 1, 2, 3, 7});
                                        add(new int[]{5, 5, 1, 2, 4, 6});
                                        add(new int[]{5, 5, 1, 2, 4, 7});
                                        add(new int[]{5, 5, 2, 3, 4, 6});
                                        add(new int[]{5, 5, 2, 3, 4, 7});
                                        add(new int[]{5, 5, 2, 4, 6, 7});
                                        add(new int[]{5, 5, 3, 4, 6, 7});
                                        add(new int[]{6, 6, 1, 2, 3, 4});
                                        add(new int[]{6, 6, 1, 2, 3, 5});
                                        add(new int[]{6, 6, 1, 2, 4, 5});
                                        add(new int[]{6, 6, 1, 2, 4, 7});
                                        add(new int[]{6, 6, 2, 3, 4, 5});
                                        add(new int[]{6, 6, 2, 3, 4, 7});
                                        add(new int[]{6, 6, 2, 3, 5, 7});
                                        add(new int[]{6, 6, 3, 4, 5, 7});
                                        add(new int[]{7, 7, 1, 2, 3, 4});
                                        add(new int[]{7, 7, 1, 2, 3, 5});
                                        add(new int[]{7, 7, 1, 2, 4, 5});
                                        add(new int[]{7, 7, 1, 2, 4, 6});
                                        add(new int[]{7, 7, 1, 2, 5, 6});
                                        add(new int[]{7, 7, 2, 3, 4, 5});
                                        add(new int[]{7, 7, 2, 3, 4, 6});
                                        add(new int[]{7, 7, 2, 4, 5, 6});
                                        add(new int[]{7, 7, 3, 4, 5, 6});
                                    }});
                                    put(7, new ArrayList<>() {{
                                        add(new int[]{1, 1, 2, 3, 4, 5, 6});
                                        add(new int[]{1, 1, 2, 3, 4, 5, 7});
                                        add(new int[]{1, 1, 2, 3, 4, 6, 7});
                                        add(new int[]{1, 1, 2, 3, 5, 6, 7});
                                        add(new int[]{1, 1, 2, 4, 5, 6, 7});
                                        add(new int[]{1, 1, 3, 4, 5, 6, 7});
                                        add(new int[]{2, 2, 1, 3, 4, 5, 6});
                                        add(new int[]{2, 2, 1, 3, 4, 5, 7});
                                        add(new int[]{2, 2, 1, 3, 5, 6, 7});
                                        add(new int[]{2, 2, 1, 4, 5, 6, 7});
                                        add(new int[]{2, 2, 3, 4, 5, 6, 7});
                                        add(new int[]{3, 3, 1, 2, 4, 5, 6});
                                        add(new int[]{3, 3, 1, 2, 4, 5, 7});
                                        add(new int[]{3, 3, 1, 2, 4, 6, 7});
                                        add(new int[]{3, 3, 1, 4, 5, 6, 7});
                                        add(new int[]{3, 3, 2, 4, 5, 6, 7});
                                        add(new int[]{4, 4, 1, 2, 3, 5, 6});
                                        add(new int[]{5, 5, 1, 2, 3, 4, 6});
                                        add(new int[]{6, 6, 1, 2, 3, 4, 5});
                                        add(new int[]{7, 7, 1, 2, 3, 4, 5});
                                        add(new int[]{1, 2, 3, 4, 5, 6, 7});
                                    }});
                                }},
                                new HashMap<>() {{
                                    put(3, new int[]{5, 10, 5, 10, 15, 15, 15, 5, 10, 5, 10, 10});
                                    put(4, new int[]{15, 15, 15, 15, 15, 15, 15, 15, 15, 20, 20, 20, 10, 10, 10, 10, 10, 10, 10, 15, 10, 15, 15, 15, 15, 15, 15, 15});
                                    put(5, new int[]{5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 5, 10, 5, 5, 5, 5, 5, 5, 5});
                                    put(6, new int[]{10, 10, 10, 10, 10, 5, 10, 10, 5, 5, 10, 10, 10, 10, 5, 5, 10, 10, 10, 10, 10, 5, 5, 5, 10, 10, 10, 5, 5, 5, 5, 5, 10, 10, 5, 5, 5, 5, 5, 5, 5});
                                    put(7, new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 20, 10, 10, 10, 20, 20, 10, 10, 10, 20, 120});
                                }},
                                new int[][]{{2, 3, 4, 5, 6, 7}, {18, 24, 24, 24, 24, 6}}
                        ),
                        ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.BULLET,
                        new RtpChoiceHlrConfigExtendBullet()));
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
