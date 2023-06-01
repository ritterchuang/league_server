package org.lsj.gs.math.config.resources.tableGameConfig.games.szzb_java;

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
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.*;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.AwardBulletGtrConfigExtendNone;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.AwardBulletGtrConfigExtendOneType;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.awardBulletCtrConfigExtend.AwardBulletCtrConfigExtendNormal;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.rtpChoiceHlr.enity.RtpChoiceHlrConfigExtendBullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.rtpChoiceHlr.enity.RtpChoiceHlrConfigExtendCompanyAdjust;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureCtrConfigExtend.SpecialFeatureCtrConfigExtendNone;
import org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.server.RobotBeginMoneyLimitBase;
import org.lsj.gs.math.games.szzb_java.enity.ConstSzzbJava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 世足爭霸預設牌桌遊戲設定產生器
public class TableGameResourceSzzbJavaDefault extends AbstractTableGameResourceFish {
    public TableGameConfigFish create() {
        // 1. 計算遊戲調控設定
        GameAdjustConfig gameAdjustConfig = super.createGameAdjustConfig();

        // 2. 計算亂數產生器演算法設定
        RngAlgorithmConfig rngAlgorithmConfig = super.createRngAlgorithmConfig();

        // 3. 計算子彈設定對應表
        Map<Integer, Map<Integer, BulletConfigBase>> bulletIdToFieldIndexBulletConfigMap = new HashMap<>(){
            {
                put(1, new HashMap<>(){
                    {
                        put(ConstSzzbJava.BulletEnumSzzbJava.NORMAL.getBulletMechanismCode(), new BulletConfigBase(
                                ConstMathFish.BulletType.NORMAL, new BulletConfigExtendNormal(),
                                ConstMathFish.BulletCostType.RATIO, new BulletCostConfigExtendRatio(ConstMathFish.BulletCostListType.TEN_MULTI_TEN, new BulletCostListConfigExtendTenMultiTen(), new BulletCostExchange(1.0, 1.0)),
                                ConstMathFish.BulletAmountType.INFINITE,
                                ConstMathFish.BulletRtpUseType.NONE, new BulletRtpUseConfigExtendNone()
                        ));
                        put(ConstSzzbJava.BulletEnumSzzbJava.LIGHTNING_COMBO_KICKS.getBulletMechanismCode(), new BulletConfigBase(
                                ConstMathFish.BulletType.NORMAL, new BulletConfigExtendNormal(),
                                ConstMathFish.BulletCostType.FREE, new BulletCostConfigExtendFree(),
                                ConstMathFish.BulletAmountType.FINITE,
                                ConstMathFish.BulletRtpUseType.HAVE, new BulletRtpUseConfigExtendHave(1.0)
                        ));
                        put(ConstSzzbJava.BulletEnumSzzbJava.BEAST_SHOT.getBulletMechanismCode(), new BulletConfigBase(
                                ConstMathFish.BulletType.NORMAL, new BulletConfigExtendNormal(),
                                ConstMathFish.BulletCostType.FREE, new BulletCostConfigExtendFree(),
                                ConstMathFish.BulletAmountType.FINITE,
                                ConstMathFish.BulletRtpUseType.HAVE, new BulletRtpUseConfigExtendHave(8.0)
                        ));
                    }
                });
                put(2, new HashMap<>(){
                    {
                        put(ConstSzzbJava.BulletEnumSzzbJava.NORMAL.getBulletMechanismCode(), new BulletConfigBase(
                                ConstMathFish.BulletType.NORMAL, new BulletConfigExtendNormal(),
                                ConstMathFish.BulletCostType.RATIO, new BulletCostConfigExtendRatio(ConstMathFish.BulletCostListType.TEN, new BulletCostListConfigExtendTen(), new BulletCostExchange(1.0, 1.0)),
                                ConstMathFish.BulletAmountType.INFINITE,
                                ConstMathFish.BulletRtpUseType.NONE, new BulletRtpUseConfigExtendNone()));
                        put(ConstSzzbJava.BulletEnumSzzbJava.LIGHTNING_COMBO_KICKS.getBulletMechanismCode(), new BulletConfigBase(
                                ConstMathFish.BulletType.NORMAL, new BulletConfigExtendNormal(),
                                ConstMathFish.BulletCostType.FREE, new BulletCostConfigExtendFree(),
                                ConstMathFish.BulletAmountType.FINITE,
                                ConstMathFish.BulletRtpUseType.HAVE, new BulletRtpUseConfigExtendHave(1.0)
                        ));
                        put(ConstSzzbJava.BulletEnumSzzbJava.BEAST_SHOT.getBulletMechanismCode(), new BulletConfigBase(
                                ConstMathFish.BulletType.NORMAL, new BulletConfigExtendNormal(),
                                ConstMathFish.BulletCostType.FREE, new BulletCostConfigExtendFree(),
                                ConstMathFish.BulletAmountType.FINITE,
                                ConstMathFish.BulletRtpUseType.HAVE, new BulletRtpUseConfigExtendHave(8.0)
                        ));
                    }
                });
            }
        };

        // 4. 計算打擊設定對應表
        Map<Integer, Map<Integer, HitCtrConfig>> hitCtrConfigMap = new HashMap<>() {{
            put(ConstSzzbJava.BulletEnumSzzbJava.NORMAL.getBulletMechanismCode(), new HashMap<>() {{
                put(1, new HitCtrConfig(ConstMathFish.HitType.TREASURE_BOX,
                        new HitTypeConfigExtendTreasureBox(
                                new ArrayList<>(){
                                    {
                                        add(new int[]{88, 120});
                                        add(new int[]{121, 188});
                                        add(new int[]{189, 288});
                                        add(new int[]{289, 388});
                                        add(new int[]{389, 488});
                                        add(new int[]{489, 588});
                                        add(new int[]{589, 688});
                                        add(new int[]{689, 788});
                                        add(new int[]{789, 888});
                                    }
                                },
                                List.of(20,100,100,100,40,40,40,20,20),
                                new HashMap<>(){
                                    {
                                        put(10, new int[]{5, 5});
                                        put(11, new int[]{5, 6});
                                        put(12, new int[]{7, 5});
                                        put(13, new int[]{5, 8});
                                        put(14, new int[]{6, 8});
                                        put(15, new int[]{7, 8});
                                        put(16, new int[]{8, 8});
                                        put(17, new int[]{9, 8});
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
                        ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));

                put(2, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(5), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(3, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(18), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(4, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(35), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(5, new HitCtrConfig(ConstMathFish.HitType.RANDOM_ODDS, new HitTypeConfigExtendRandomOdds(new int[]{50,70,90,100,140,160,180,200,250,300,350,400,450,500}, new int[]{0,100,100,150,150,100,100,100,100,70,30,30,30,30}), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));

                put(6, new HitCtrConfig(ConstMathFish.HitType.TREASURE_BOX,
                        new HitTypeConfigExtendTreasureBox(
                                new ArrayList<>(){
                                    {
                                        add(new int[]{66, 120});
                                        add(new int[]{121, 166});
                                        add(new int[]{167, 266});
                                        add(new int[]{267, 366});
                                        add(new int[]{367, 466});
                                        add(new int[]{467, 566});
                                        add(new int[]{567, 666});
                                    }
                                },
                                List.of(20,100,100,40,30,30,20),
                                new HashMap<>(){
                                    {
                                        put(10, new int[]{5, 5});
                                        put(11, new int[]{5, 6});
                                        put(12, new int[]{7, 5});
                                        put(13, new int[]{5, 8});
                                        put(14, new int[]{6, 8});
                                        put(15, new int[]{7, 8});
                                        put(16, new int[]{8, 8});
                                        put(17, new int[]{9, 8});
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
                        ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));

                put(7, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(4), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(8, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(16), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(9, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(32), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(10, new HitCtrConfig(ConstMathFish.HitType.RANDOM_ODDS, new HitTypeConfigExtendRandomOdds(new int[]{30,50,70,90,100,125,150,175,200,225,250,275,300}, new int[]{0,30,100,100,140,140,100,100,80,80,80,40,20}), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(11, new HitCtrConfig(ConstMathFish.HitType.RED_ENVELOPE,
                        new HitTypeConfigExtendRedEnvelope(
                                5,
                                new int[]{88, 98, 118, 128, 158, 168, 178, 188, 218, 258, 268, 288},
                                new int[][]{{1},{100}},
                                new HashMap<>() {{
                                    put(1, new ArrayList<>(){{
                                        add(new int[]{88});
                                        add(new int[]{98});
                                        add(new int[]{118});
                                        add(new int[]{128});
                                        add(new int[]{158});
                                        add(new int[]{168});
                                        add(new int[]{178});
                                        add(new int[]{188});
                                        add(new int[]{218});
                                        add(new int[]{258});
                                        add(new int[]{268});
                                        add(new int[]{288});
                                    }});
                                }},
                                new HashMap<>() {{
                                    put(1, new int[]{30, 60, 70, 80, 100, 100, 70, 70, 70, 70, 70, 40});
                                }}
                        ),
                        ConstMathFish.SpecialFeatureEnumType.NONE,new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));

                put(12, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(2), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(13, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(10), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(14, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(25), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(15, new HitCtrConfig(ConstMathFish.HitType.RANDOM_ODDS, new HitTypeConfigExtendRandomOdds(new int[]{50,75,85,100,110,120,130,140,150}, new int[]{100,100,100,100,100,100,100,100,100}), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(16, new HitCtrConfig(ConstMathFish.HitType.RED_ENVELOPE,
                        new HitTypeConfigExtendRedEnvelope(
                                5,
                                new int[]{5,10,20,30,50,75,100},
                                new int[][]{{4,5},{60,40}},
                                new HashMap<>() {{
                                    put(4, new ArrayList<>(){{
                                        add(new int[]{20,50,50,75});
                                        add(new int[]{20,30,50,75});
                                        add(new int[]{10,10,50,75});
                                        add(new int[]{5,10,20,75});
                                        add(new int[]{10,10,20,50});
                                    }});
                                    put(5, new ArrayList<>(){{
                                        add(new int[]{50,50,75,100,100});
                                        add(new int[]{30,50,75,75,100});
                                        add(new int[]{20,30,50,75,100});
                                        add(new int[]{10,50,50,75,75});
                                        add(new int[]{20,30,30,75,75});
                                        add(new int[]{10,30,50,50,75});
                                    }});
                                }},
                                new HashMap<>() {{
                                    put(4, new int[]{100,100,100,90,30});
                                    put(5, new int[]{40,50,90,70,70,20});
                                }}
                        ),
                        ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(17, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(3), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(18, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(12), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(19, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(28), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(20, new HitCtrConfig(ConstMathFish.HitType.RANDOM_ODDS, new HitTypeConfigExtendRandomOdds(new int[]{50,75,90,100,120,140,160,180,200}, new int[]{55,70,80,90,110,130,120,110,100}), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));

                put(21, new HitCtrConfig(ConstMathFish.HitType.MULTI_TARGET, new HitTypeConfigExtendMultiTarget(new HashMap<>() {{
                    put(1, 258);
                    put(2, 5);
                    put(3, 18);
                    put(4, 35);
                    put(5, 141);
                    put(6, 201);
                    put(7, 4);
                    put(8, 16);
                    put(9, 32);
                    put(10, 123);
                    put(11, 160);
                    put(12, 2);
                    put(13, 10);
                    put(14, 25);
                    put(15, 95);
                    put(16, 176);
                    put(17, 3);
                    put(18, 12);
                    put(19, 28);
                    put(20, 114);
                    put(21, 1);
                    put(22, 1);
                    put(23, 50);
                    put(24, 70);
                }}, new RobotHitResultLimitBase(100, 500)), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(22, new HitCtrConfig(ConstMathFish.HitType.MULTI_TARGET, new HitTypeConfigExtendMultiTarget(new HashMap<>() {{
                    put(1, 258);
                    put(2, 5);
                    put(3, 18);
                    put(4, 35);
                    put(5, 141);
                    put(6, 201);
                    put(7, 4);
                    put(8, 16);
                    put(9, 32);
                    put(10, 123);
                    put(11, 160);
                    put(12, 2);
                    put(13, 10);
                    put(14, 25);
                    put(15, 95);
                    put(16, 176);
                    put(17, 3);
                    put(18, 12);
                    put(19, 28);
                    put(20, 114);
                    put(21, 1);
                    put(22, 1);
                    put(23, 50);
                    put(24, 70);
                }}, new RobotHitResultLimitBase(100, 500)), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));

                put(23, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(10),
                        ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.ONE_TYPE,
                        new AwardBulletGtrConfigExtendOneType(ConstMathFish.AwardBulletCtrType.NORMAL,
                                new AwardBulletCtrConfigExtendNormal(),
                                ConstSzzbJava.BulletEnumSzzbJava.BEAST_SHOT.getBulletMechanismCode(),
                                5,
                                new int[]{5},
                                new int[]{1}),
                        ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(24, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(10),
                        ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.ONE_TYPE,
                        new AwardBulletGtrConfigExtendOneType(ConstMathFish.AwardBulletCtrType.NORMAL,
                                new AwardBulletCtrConfigExtendNormal(),
                                ConstSzzbJava.BulletEnumSzzbJava.LIGHTNING_COMBO_KICKS.getBulletMechanismCode(),
                                999,
                                new int[]{20, 40, 60, 80, 100},
                                new int[]{1, 1, 1, 1, 1}),
                        ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
            }});
            put(ConstSzzbJava.BulletEnumSzzbJava.LIGHTNING_COMBO_KICKS.getBulletMechanismCode(), new HashMap<>() {{
                put(1, new HitCtrConfig(ConstMathFish.HitType.TREASURE_BOX,
                        new HitTypeConfigExtendTreasureBox(
                                new ArrayList<>(){
                                    {
                                        add(new int[]{88, 120});
                                        add(new int[]{121, 188});
                                        add(new int[]{189, 288});
                                        add(new int[]{289, 388});
                                        add(new int[]{389, 488});
                                        add(new int[]{489, 588});
                                        add(new int[]{589, 688});
                                        add(new int[]{689, 788});
                                        add(new int[]{789, 888});
                                    }
                                },
                                List.of(20,100,100,100,40,40,40,20,20),
                                new HashMap<>(){
                                    {
                                        put(10, new int[]{5, 5});
                                        put(11, new int[]{5, 6});
                                        put(12, new int[]{7, 5});
                                        put(13, new int[]{5, 8});
                                        put(14, new int[]{6, 8});
                                        put(15, new int[]{7, 8});
                                        put(16, new int[]{8, 8});
                                        put(17, new int[]{9, 8});
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
                        ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));

                put(2, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(5), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(3, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(18), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(4, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(35), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(5, new HitCtrConfig(ConstMathFish.HitType.RANDOM_ODDS, new HitTypeConfigExtendRandomOdds(new int[]{50,70,90,100,140,160,180,200,250,300,350,400,450,500}, new int[]{0,100,100,150,150,100,100,100,100,70,30,30,30,30}), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));

                put(6, new HitCtrConfig(ConstMathFish.HitType.TREASURE_BOX,
                        new HitTypeConfigExtendTreasureBox(
                                new ArrayList<>(){
                                    {
                                        add(new int[]{66, 120});
                                        add(new int[]{121, 166});
                                        add(new int[]{167, 266});
                                        add(new int[]{267, 366});
                                        add(new int[]{367, 466});
                                        add(new int[]{467, 566});
                                        add(new int[]{567, 666});
                                    }
                                },
                                List.of(20,100,100,40,30,30,20),
                                new HashMap<>(){
                                    {
                                        put(10, new int[]{5, 5});
                                        put(11, new int[]{5, 6});
                                        put(12, new int[]{7, 5});
                                        put(13, new int[]{5, 8});
                                        put(14, new int[]{6, 8});
                                        put(15, new int[]{7, 8});
                                        put(16, new int[]{8, 8});
                                        put(17, new int[]{9, 8});
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
                        ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));

                put(7, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(4), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(8, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(16), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(9, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(32), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(10, new HitCtrConfig(ConstMathFish.HitType.RANDOM_ODDS, new HitTypeConfigExtendRandomOdds(new int[]{30,50,70,90,100,125,150,175,200,225,250,275,300}, new int[]{0,30,100,100,140,140,100,100,80,80,80,40,20}), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(11, new HitCtrConfig(ConstMathFish.HitType.RED_ENVELOPE,
                        new HitTypeConfigExtendRedEnvelope(
                                5,
                                new int[]{88, 98, 118, 128, 158, 168, 178, 188, 218, 258, 268, 288},
                                new int[][]{{1},{100}},
                                new HashMap<>() {{
                                    put(1, new ArrayList<>(){{
                                        add(new int[]{88});
                                        add(new int[]{98});
                                        add(new int[]{118});
                                        add(new int[]{128});
                                        add(new int[]{158});
                                        add(new int[]{168});
                                        add(new int[]{178});
                                        add(new int[]{188});
                                        add(new int[]{218});
                                        add(new int[]{258});
                                        add(new int[]{268});
                                        add(new int[]{288});
                                    }});
                                }},
                                new HashMap<>() {{
                                    put(1, new int[]{30, 60, 70, 80, 100, 100, 70, 70, 70, 70, 70, 40});
                                }}
                        ),
                        ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(12, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(2), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(13, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(10), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(14, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(25), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(15, new HitCtrConfig(ConstMathFish.HitType.RANDOM_ODDS, new HitTypeConfigExtendRandomOdds(new int[]{50,75,85,100,110,120,130,140,150}, new int[]{100,100,100,100,100,100,100,100,100}), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(16, new HitCtrConfig(ConstMathFish.HitType.RED_ENVELOPE,
                        new HitTypeConfigExtendRedEnvelope(
                                5,
                                new int[]{5,10,20,30,50,75,100},
                                new int[][]{{4,5},{60,40}},
                                new HashMap<>() {{
                                    put(4, new ArrayList<>(){{
                                        add(new int[]{20,50,50,75});
                                        add(new int[]{20,30,50,75});
                                        add(new int[]{10,10,50,75});
                                        add(new int[]{5,10,20,75});
                                        add(new int[]{10,10,20,50});
                                    }});
                                    put(5, new ArrayList<>(){{
                                        add(new int[]{50,50,75,100,100});
                                        add(new int[]{30,50,75,75,100});
                                        add(new int[]{20,30,50,75,100});
                                        add(new int[]{10,50,50,75,75});
                                        add(new int[]{20,30,30,75,75});
                                        add(new int[]{10,30,50,50,75});
                                    }});
                                }},
                                new HashMap<>() {{
                                    put(4, new int[]{100,100,100,90,30});
                                    put(5, new int[]{40,50,90,70,70,20});
                                }}
                        ),
                        ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(17, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(3), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(18, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(12), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(19, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(28), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(20, new HitCtrConfig(ConstMathFish.HitType.RANDOM_ODDS, new HitTypeConfigExtendRandomOdds(new int[]{50,75,90,100,120,140,160,180,200}, new int[]{55,70,80,90,110,130,120,110,100}), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));

                put(21, new HitCtrConfig(ConstMathFish.HitType.MULTI_TARGET, new HitTypeConfigExtendMultiTarget(new HashMap<>() {{
                    put(1, 258);
                    put(2, 5);
                    put(3, 18);
                    put(4, 35);
                    put(5, 141);
                    put(6, 201);
                    put(7, 4);
                    put(8, 16);
                    put(9, 32);
                    put(10, 123);
                    put(11, 160);
                    put(12, 2);
                    put(13, 10);
                    put(14, 25);
                    put(15, 95);
                    put(16, 176);
                    put(17, 3);
                    put(18, 12);
                    put(19, 28);
                    put(20, 114);
                    put(21, 1);
                    put(22, 1);
                    put(23, 50);
                    put(24, 70);
                }}, new RobotHitResultLimitBase(100, 500)), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(22, new HitCtrConfig(ConstMathFish.HitType.MULTI_TARGET, new HitTypeConfigExtendMultiTarget(new HashMap<>() {{
                    put(1, 258);
                    put(2, 5);
                    put(3, 18);
                    put(4, 35);
                    put(5, 141);
                    put(6, 201);
                    put(7, 4);
                    put(8, 16);
                    put(9, 32);
                    put(10, 123);
                    put(11, 160);
                    put(12, 2);
                    put(13, 10);
                    put(14, 25);
                    put(15, 95);
                    put(16, 176);
                    put(17, 3);
                    put(18, 12);
                    put(19, 28);
                    put(20, 114);
                    put(21, 1);
                    put(22, 1);
                    put(23, 50);
                    put(24, 70);
                }}, new RobotHitResultLimitBase(100, 500)), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));

                put(23, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(50),
                        ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(24, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(10),
                        ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.ONE_TYPE,
                        new AwardBulletGtrConfigExtendOneType(ConstMathFish.AwardBulletCtrType.NORMAL,
                                new AwardBulletCtrConfigExtendNormal(),
                                ConstSzzbJava.BulletEnumSzzbJava.LIGHTNING_COMBO_KICKS.getBulletMechanismCode(),
                                999,
                                new int[]{20, 40, 60, 80, 100},
                                new int[]{1, 1, 1, 1, 1}),
                        ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
            }});
            put(ConstSzzbJava.BulletEnumSzzbJava.BEAST_SHOT.getBulletMechanismCode(), new HashMap<>() {{
                put(1, new HitCtrConfig(ConstMathFish.HitType.TREASURE_BOX,
                        new HitTypeConfigExtendTreasureBox(
                                new ArrayList<>(){
                                    {
                                        add(new int[]{88, 120});
                                        add(new int[]{121, 188});
                                        add(new int[]{189, 288});
                                        add(new int[]{289, 388});
                                        add(new int[]{389, 488});
                                        add(new int[]{489, 588});
                                        add(new int[]{589, 688});
                                        add(new int[]{689, 788});
                                        add(new int[]{789, 888});
                                    }
                                },
                                List.of(20,100,100,100,40,40,40,20,20),
                                new HashMap<>(){
                                    {
                                        put(10, new int[]{5, 5});
                                        put(11, new int[]{5, 6});
                                        put(12, new int[]{7, 5});
                                        put(13, new int[]{5, 8});
                                        put(14, new int[]{6, 8});
                                        put(15, new int[]{7, 8});
                                        put(16, new int[]{8, 8});
                                        put(17, new int[]{9, 8});
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
                        ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));

                put(2, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(5), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(3, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(18), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(4, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(35), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(5, new HitCtrConfig(ConstMathFish.HitType.RANDOM_ODDS, new HitTypeConfigExtendRandomOdds(new int[]{50,70,90,100,140,160,180,200,250,300,350,400,450,500}, new int[]{0,100,100,150,150,100,100,100,100,70,30,30,30,30}), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));

                put(6, new HitCtrConfig(ConstMathFish.HitType.TREASURE_BOX,
                        new HitTypeConfigExtendTreasureBox(
                                new ArrayList<>(){
                                    {
                                        add(new int[]{66, 120});
                                        add(new int[]{121, 166});
                                        add(new int[]{167, 266});
                                        add(new int[]{267, 366});
                                        add(new int[]{367, 466});
                                        add(new int[]{467, 566});
                                        add(new int[]{567, 666});
                                    }
                                },
                                List.of(20,100,100,40,30,30,20),
                                new HashMap<>(){
                                    {
                                        put(10, new int[]{5, 5});
                                        put(11, new int[]{5, 6});
                                        put(12, new int[]{7, 5});
                                        put(13, new int[]{5, 8});
                                        put(14, new int[]{6, 8});
                                        put(15, new int[]{7, 8});
                                        put(16, new int[]{8, 8});
                                        put(17, new int[]{9, 8});
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
                        ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));

                put(7, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(4), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(8, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(16), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(9, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(32), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(10, new HitCtrConfig(ConstMathFish.HitType.RANDOM_ODDS, new HitTypeConfigExtendRandomOdds(new int[]{30,50,70,90,100,125,150,175,200,225,250,275,300}, new int[]{0,30,100,100,140,140,100,100,80,80,80,40,20}), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(11, new HitCtrConfig(ConstMathFish.HitType.RED_ENVELOPE,
                        new HitTypeConfigExtendRedEnvelope(
                                5,
                                new int[]{88, 98, 118, 128, 158, 168, 178, 188, 218, 258, 268, 288},
                                new int[][]{{1},{100}},
                                new HashMap<>() {{
                                    put(1, new ArrayList<>(){{
                                        add(new int[]{88});
                                        add(new int[]{98});
                                        add(new int[]{118});
                                        add(new int[]{128});
                                        add(new int[]{158});
                                        add(new int[]{168});
                                        add(new int[]{178});
                                        add(new int[]{188});
                                        add(new int[]{218});
                                        add(new int[]{258});
                                        add(new int[]{268});
                                        add(new int[]{288});
                                    }});
                                }},
                                new HashMap<>() {{
                                    put(1, new int[]{30, 60, 70, 80, 100, 100, 70, 70, 70, 70, 70, 40});
                                }}
                        ),
                        ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(12, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(2), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(13, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(10), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(14, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(25), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(15, new HitCtrConfig(ConstMathFish.HitType.RANDOM_ODDS, new HitTypeConfigExtendRandomOdds(new int[]{50,75,85,100,110,120,130,140,150}, new int[]{100,100,100,100,100,100,100,100,100}), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));

                put(16, new HitCtrConfig(ConstMathFish.HitType.RED_ENVELOPE,
                        new HitTypeConfigExtendRedEnvelope(
                                5,
                                new int[]{5,10,20,30,50,75,100},
                                new int[][]{{4,5},{60,40}},
                                new HashMap<>() {{
                                    put(4, new ArrayList<>(){{
                                        add(new int[]{20,50,50,75});
                                        add(new int[]{20,30,50,75});
                                        add(new int[]{10,10,50,75});
                                        add(new int[]{5,10,20,75});
                                        add(new int[]{10,10,20,50});
                                    }});
                                    put(5, new ArrayList<>(){{
                                        add(new int[]{50,50,75,100,100});
                                        add(new int[]{30,50,75,75,100});
                                        add(new int[]{20,30,50,75,100});
                                        add(new int[]{10,50,50,75,75});
                                        add(new int[]{20,30,30,75,75});
                                        add(new int[]{10,30,50,50,75});
                                    }});
                                }},
                                new HashMap<>() {{
                                    put(4, new int[]{100,100,100,90,30});
                                    put(5, new int[]{40,50,90,70,70,20});
                                }}
                        ),
                        ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(17, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(3), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(18, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(12), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(19, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(28), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(20, new HitCtrConfig(ConstMathFish.HitType.RANDOM_ODDS, new HitTypeConfigExtendRandomOdds(new int[]{50,75,90,100,120,140,160,180,200}, new int[]{55,70,80,90,110,130,120,110,100}), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));

                put(21, new HitCtrConfig(ConstMathFish.HitType.MULTI_TARGET, new HitTypeConfigExtendMultiTarget(new HashMap<>() {{
                    put(1, 258);
                    put(2, 5);
                    put(3, 18);
                    put(4, 35);
                    put(5, 141);
                    put(6, 201);
                    put(7, 4);
                    put(8, 16);
                    put(9, 32);
                    put(10, 123);
                    put(11, 160);
                    put(12, 2);
                    put(13, 10);
                    put(14, 25);
                    put(15, 95);
                    put(16, 176);
                    put(17, 3);
                    put(18, 12);
                    put(19, 28);
                    put(20, 114);
                    put(21, 1);
                    put(22, 1);
                    put(23, 50);
                    put(24, 70);
                }}, new RobotHitResultLimitBase(100, 500)), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(22, new HitCtrConfig(ConstMathFish.HitType.MULTI_TARGET, new HitTypeConfigExtendMultiTarget(new HashMap<>() {{
                    put(1, 258);
                    put(2, 5);
                    put(3, 18);
                    put(4, 35);
                    put(5, 141);
                    put(6, 201);
                    put(7, 4);
                    put(8, 16);
                    put(9, 32);
                    put(10, 123);
                    put(11, 160);
                    put(12, 2);
                    put(13, 10);
                    put(14, 25);
                    put(15, 95);
                    put(16, 176);
                    put(17, 3);
                    put(18, 12);
                    put(19, 28);
                    put(20, 114);
                    put(21, 1);
                    put(22, 1);
                    put(23, 50);
                    put(24, 70);
                }}, new RobotHitResultLimitBase(100, 500)), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));

                put(23, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(50),
                        ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(24, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(70),
                        ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
            }});
        }};

        // 5. 計算機器人起始金額上下限倍數
        RobotBeginMoneyLimitBase robotBeginMoneyLimitBase = new RobotBeginMoneyLimitBase(500, 5000);

        return new TableGameConfigFish(
                gameAdjustConfig,
                rngAlgorithmConfig,
                bulletIdToFieldIndexBulletConfigMap,
                hitCtrConfigMap,
                robotBeginMoneyLimitBase
        );
    }
}
