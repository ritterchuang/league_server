package org.lsj.gs.math.config.resources.tableGameConfig.games.rycs_java;

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
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendDoubleWheel;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendFixedOdds;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendReel;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendTreasureBox;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.AwardBulletGtrConfigExtendNone;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.AwardBulletGtrConfigExtendOneType;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.awardBulletCtrConfigExtend.AwardBulletCtrConfigExtendNormal;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.rtpChoiceHlr.enity.RtpChoiceHlrConfigExtendBullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.rtpChoiceHlr.enity.RtpChoiceHlrConfigExtendCompanyAdjust;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureCtrConfigExtend.SpecialFeatureCtrConfigExtendNone;
import org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.server.RobotBeginMoneyLimitBase;
import org.lsj.gs.math.games.rycs_java.enity.ConstRycsJava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 人魚傳說預設牌桌遊戲設定產生器
public class TableGameResourceRycsJavaDefault extends AbstractTableGameResourceFish {
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
                        put(ConstRycsJava.BulletEnumRycsJava.NORMAL.getBulletMechanismCode(), new BulletConfigBase(
                                ConstMathFish.BulletType.NORMAL, new BulletConfigExtendNormal(),
                                ConstMathFish.BulletCostType.RATIO, new BulletCostConfigExtendRatio(ConstMathFish.BulletCostListType.TEN_MULTI_TEN, new BulletCostListConfigExtendTenMultiTen(), new BulletCostExchange(1.0, 1.0)),
                                ConstMathFish.BulletAmountType.INFINITE,
                                ConstMathFish.BulletRtpUseType.NONE, new BulletRtpUseConfigExtendNone()));
                        put(ConstRycsJava.BulletEnumRycsJava.TRIDENT.getBulletMechanismCode(), new BulletConfigBase(
                                ConstMathFish.BulletType.NORMAL, new BulletConfigExtendNormal(),
                                ConstMathFish.BulletCostType.FREE, new BulletCostConfigExtendFree(),
                                ConstMathFish.BulletAmountType.FINITE,
                                ConstMathFish.BulletRtpUseType.HAVE, new BulletRtpUseConfigExtendHave(4.0)
                        ));
                    }
                });
                put(2, new HashMap<>(){
                    {
                        put(ConstRycsJava.BulletEnumRycsJava.NORMAL.getBulletMechanismCode(), new BulletConfigBase(
                                ConstMathFish.BulletType.NORMAL, new BulletConfigExtendNormal(),
                                ConstMathFish.BulletCostType.RATIO, new BulletCostConfigExtendRatio(ConstMathFish.BulletCostListType.TEN, new BulletCostListConfigExtendTen(), new BulletCostExchange(1.0, 1.0)),
                                ConstMathFish.BulletAmountType.INFINITE,
                                ConstMathFish.BulletRtpUseType.NONE, new BulletRtpUseConfigExtendNone()));
                        put(ConstRycsJava.BulletEnumRycsJava.TRIDENT.getBulletMechanismCode(), new BulletConfigBase(
                                ConstMathFish.BulletType.NORMAL, new BulletConfigExtendNormal(),
                                ConstMathFish.BulletCostType.FREE, new BulletCostConfigExtendFree(),
                                ConstMathFish.BulletAmountType.FINITE,
                                ConstMathFish.BulletRtpUseType.HAVE, new BulletRtpUseConfigExtendHave(4.0)
                        ));
                    }
                });
            }
        };

        // 4. 計算打擊設定對應表
        Map<Integer, Map<Integer, HitCtrConfig>> hitCtrConfigMap = new HashMap<>() {{
            put(ConstRycsJava.BulletEnumRycsJava.NORMAL.getBulletMechanismCode(), new HashMap<>() {{
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
                put(19, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(100), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(20, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS,
                        new HitTypeConfigExtendFixedOdds(10),
                        ConstMathFish.SpecialFeatureEnumType.NONE,
                        new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.ONE_TYPE,
                        new AwardBulletGtrConfigExtendOneType(ConstMathFish.AwardBulletCtrType.NORMAL,
                                new AwardBulletCtrConfigExtendNormal(),
                                ConstRycsJava.BulletEnumRycsJava.TRIDENT.getBulletMechanismCode(),
                                10,
                                new int[]{10},
                                new int[]{1}),
                        ConstMathFish.RtpChoiceType.COMPANY_ADJUST,
                        new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(21, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(100), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(22, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(30), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(23, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(30), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(24, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(30), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(25, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(30), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(26, new HitCtrConfig(ConstMathFish.HitType.REEL,
                        new HitTypeConfigExtendReel(new ArrayList<>(){
                            {
                                add(0);
                                add(0);
                                add(0);
                            }
                        }, new ArrayList<>(){
                            {
                                add(new int[]{50,80});
                                add(new int[]{100,150});
                                add(new int[]{250, 350});
                                add(new int[]{400, 550});
                                add(new int[]{666, 666});
                                add(new int[]{777, 777});
                                add(new int[]{888, 888});
                                add(new int[]{999, 999});
                            }
                        }, new ArrayList<>(){
                            {
                                add(40);
                                add(120);
                                add(65);
                                add(30);
                                add(10);
                                add(5);
                                add(2);
                                add(1);
                            }
                        }),
                        ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(27, new HitCtrConfig(ConstMathFish.HitType.TREASURE_BOX,
                        new HitTypeConfigExtendTreasureBox(
                                new ArrayList<>(){
                                    {
                                        add(new int[]{40, 80});
                                        add(new int[]{100, 120});
                                        add(new int[]{150, 200});
                                        add(new int[]{300, 350});
                                        add(new int[]{450, 600});
                                    }
                                },
                                List.of(1, 20, 15, 10, 5),
                                new HashMap<>(){
                                    {
                                        put(10, new int[]{2, 2, 6});
                                        put(11, new int[]{5, 6});
                                        put(12, new int[]{2, 5, 5});
                                        put(13, new int[]{5, 8});
                                        put(14, new int[]{6, 8});
                                        put(15, new int[]{2, 5, 8});
                                        put(16, new int[]{2, 6, 8});
                                        put(17, new int[]{2, 2, 5, 8});
                                        put(18, new int[]{5, 5, 8});
                                        put(19, new int[]{5, 6, 8});
                                    }
                                },
                                new HashMap<>(){
                                    {
                                        put(10, new int[]{10});
                                        put(20, new int[]{10, 10});
                                        put(30, new int[]{10, 20});
                                        put(40, new int[]{15, 25});
                                        put(50, new int[]{10, 15, 25});
                                        put(60, new int[]{15, 20, 25});
                                        put(70, new int[]{15, 25, 30});
                                        put(80, new int[]{20, 30, 30});
                                        put(90, new int[]{15, 20, 25, 30});
                                    }
                                },
                                new ArrayList<>(){
                                    {
                                        add(new int[]{20, 20, 30, 30});
                                        add(new int[]{15, 25, 30, 30});
                                        add(new int[]{20, 25, 25, 30});
                                        add(new int[]{10, 30, 30, 30});

                                        add(new int[]{10, 10, 25, 25, 30});
                                        add(new int[]{10, 15, 25, 25, 25});
                                        add(new int[]{10, 15, 20, 25, 30});
                                        add(new int[]{10, 20, 20, 25, 25});
                                        add(new int[]{15, 15, 20, 25, 25});
                                        add(new int[]{15, 15, 20, 20, 30});

                                        add(new int[]{10, 10, 15, 15, 20, 30});
                                        add(new int[]{10, 10, 15, 15, 25, 25});
                                    }
                                },
                                new ArrayList<>(){
                                    {
                                        add(5);
                                        add(5);
                                        add(5);
                                        add(5);

                                        add(10);
                                        add(10);
                                        add(10);
                                        add(10);
                                        add(10);
                                        add(10);

                                        add(1);
                                        add(1);
                                    }
                                }),
                        ConstMathFish.SpecialFeatureEnumType.NONE,
                        new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE,
                        new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.COMPANY_ADJUST,
                        new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(28, new HitCtrConfig(ConstMathFish.HitType.DOUBLE_WHEEL,
                        new HitTypeConfigExtendDoubleWheel(
                                new int[]{2, 5, 8, 10, 2, 5, 8, 10},
                                new int[]{10, 15, 20, 25, 30, 35, 40, 50},
                                new int[]{30, 20, 10, 3, 30, 20, 10, 2},
                                new HashMap<>(){
                                    {
                                        put(2, new int[]{5, 5, 5, 10, 10, 0, 0, 0});
                                        put(5, new int[]{5, 10, 10, 10, 5, 5, 1, 0});
                                        put(8, new int[]{5, 5, 10, 15, 10, 5, 0, 0});
                                        put(10, new int[]{0, 0, 0, 0, 20, 15, 10, 5});
                                    }
                                }
                        ),
                        ConstMathFish.SpecialFeatureEnumType.NONE,
                        new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE,
                        new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.COMPANY_ADJUST,
                        new RtpChoiceHlrConfigExtendCompanyAdjust()));
            }});
            put(ConstRycsJava.BulletEnumRycsJava.TRIDENT.getBulletMechanismCode(), new HashMap<>() {{
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
                put(19, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(100), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(20, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS,
                        new HitTypeConfigExtendFixedOdds(50),
                        ConstMathFish.SpecialFeatureEnumType.NONE,
                        new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(21, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(100), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(22, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(30), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(23, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(30), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(24, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(30), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(25, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(30), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(26, new HitCtrConfig(ConstMathFish.HitType.REEL,
                        new HitTypeConfigExtendReel(new ArrayList<>(){
                            {
                                add(0);
                                add(0);
                                add(0);
                            }
                        }, new ArrayList<>(){
                            {
                                add(new int[]{50,80});
                                add(new int[]{100,150});
                                add(new int[]{250, 350});
                                add(new int[]{400, 550});
                                add(new int[]{666, 666});
                                add(new int[]{777, 777});
                                add(new int[]{888, 888});
                                add(new int[]{999, 999});
                            }
                        }, new ArrayList<>(){
                            {
                                add(40);
                                add(120);
                                add(65);
                                add(30);
                                add(10);
                                add(5);
                                add(2);
                                add(1);
                            }
                        }),
                        ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(27, new HitCtrConfig(ConstMathFish.HitType.TREASURE_BOX,
                        new HitTypeConfigExtendTreasureBox(
                                new ArrayList<>(){
                                    {
                                        add(new int[]{40, 80});
                                        add(new int[]{100, 120});
                                        add(new int[]{150, 200});
                                        add(new int[]{300, 350});
                                        add(new int[]{450, 600});
                                    }
                                },
                                List.of(1, 20, 15, 10, 5),
                                new HashMap<>(){
                                    {
                                        put(10, new int[]{2, 2, 6});
                                        put(11, new int[]{5, 6});
                                        put(12, new int[]{2, 5, 5});
                                        put(13, new int[]{5, 8});
                                        put(14, new int[]{6, 8});
                                        put(15, new int[]{2, 5, 8});
                                        put(16, new int[]{2, 6, 8});
                                        put(17, new int[]{2, 2, 5, 8});
                                        put(18, new int[]{5, 5, 8});
                                        put(19, new int[]{5, 6, 8});
                                    }
                                },
                                new HashMap<>(){
                                    {
                                        put(10, new int[]{10});
                                        put(20, new int[]{10, 10});
                                        put(30, new int[]{10, 20});
                                        put(40, new int[]{15, 25});
                                        put(50, new int[]{10, 15, 25});
                                        put(60, new int[]{15, 20, 25});
                                        put(70, new int[]{15, 25, 30});
                                        put(80, new int[]{20, 30, 30});
                                        put(90, new int[]{15, 20, 25, 30});
                                    }
                                },
                                new ArrayList<>(){
                                    {
                                        add(new int[]{20, 20, 30, 30});
                                        add(new int[]{15, 25, 30, 30});
                                        add(new int[]{20, 25, 25, 30});
                                        add(new int[]{10, 30, 30, 30});

                                        add(new int[]{10, 10, 25, 25, 30});
                                        add(new int[]{10, 15, 25, 25, 25});
                                        add(new int[]{10, 15, 20, 25, 30});
                                        add(new int[]{10, 20, 20, 25, 25});
                                        add(new int[]{15, 15, 20, 25, 25});
                                        add(new int[]{15, 15, 20, 20, 30});

                                        add(new int[]{10, 10, 15, 15, 20, 30});
                                        add(new int[]{10, 10, 15, 15, 25, 25});
                                    }
                                },
                                new ArrayList<>(){
                                    {
                                        add(5);
                                        add(5);
                                        add(5);
                                        add(5);

                                        add(10);
                                        add(10);
                                        add(10);
                                        add(10);
                                        add(10);
                                        add(10);

                                        add(1);
                                        add(1);
                                    }
                                }),
                        ConstMathFish.SpecialFeatureEnumType.NONE,
                        new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE,
                        new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(28, new HitCtrConfig(ConstMathFish.HitType.DOUBLE_WHEEL,
                        new HitTypeConfigExtendDoubleWheel(
                                new int[]{2, 5, 8, 10, 2, 5, 8, 10},
                                new int[]{10, 15, 20, 25, 30, 35, 40, 50},
                                new int[]{30, 20, 10, 3, 30, 20, 10, 2},
                                new HashMap<>(){
                                    {
                                        put(2, new int[]{5, 5, 5, 10, 10, 0, 0, 0});
                                        put(5, new int[]{5, 10, 10, 10, 5, 5, 1, 0});
                                        put(8, new int[]{5, 5, 10, 15, 10, 5, 0, 0});
                                        put(10, new int[]{0, 0, 0, 0, 20, 15, 10, 5});
                                    }
                                }
                        ),
                        ConstMathFish.SpecialFeatureEnumType.NONE,
                        new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE,
                        new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
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
