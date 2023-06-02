package org.lsj.gs.math.config.resources.tableGameConfig.games.csby_java;

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
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendFixedOdds;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendRandomOdds;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendRedEnvelope;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendWheel;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.AwardBulletGtrConfigExtendNone;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.AwardBulletGtrConfigExtendOneType;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.awardBulletCtrConfigExtend.AwardBulletCtrConfigExtendNormal;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.rtpChoiceHlr.enity.RtpChoiceHlrConfigExtendBullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.rtpChoiceHlr.enity.RtpChoiceHlrConfigExtendCompanyAdjust;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureCtrConfigExtend.SpecialFeatureCtrConfigExtendNone;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureCtrConfigExtend.SpecialFeatureCtrConfigExtendRedEnvelope;
import org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.server.RobotBeginMoneyLimitBase;
import org.lsj.gs.math.games.csby_java.enity.ConstCsbyJava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// 財神捕魚預設牌桌遊戲設定產生器
public class TableGameResourceCsbyJavaDefault extends AbstractTableGameResourceFish {
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
                        put(ConstCsbyJava.BulletEnumCsbyJava.NORMAL.getBulletMechanismCode(), new BulletConfigBase(
                                ConstMathFish.BulletType.NORMAL, new BulletConfigExtendNormal(),
                                ConstMathFish.BulletCostType.RATIO, new BulletCostConfigExtendRatio(ConstMathFish.BulletCostListType.TEN_MULTI_TEN, new BulletCostListConfigExtendTenMultiTen(), new BulletCostExchange(1.0, 1.0)),
                                ConstMathFish.BulletAmountType.INFINITE,
                                ConstMathFish.BulletRtpUseType.NONE, new BulletRtpUseConfigExtendNone()
                        ));
                        put(ConstCsbyJava.BulletEnumCsbyJava.MACHINE.getBulletMechanismCode(), new BulletConfigBase(
                                ConstMathFish.BulletType.NORMAL, new BulletConfigExtendNormal(),
                                ConstMathFish.BulletCostType.FREE, new BulletCostConfigExtendFree(),
                                ConstMathFish.BulletAmountType.FINITE,
                                ConstMathFish.BulletRtpUseType.HAVE, new BulletRtpUseConfigExtendHave(1.0)
                        ));
                        put(ConstCsbyJava.BulletEnumCsbyJava.DRILL.getBulletMechanismCode(), new BulletConfigBase(
                                ConstMathFish.BulletType.NORMAL, new BulletConfigExtendNormal(),
                                ConstMathFish.BulletCostType.FREE, new BulletCostConfigExtendFree(),
                                ConstMathFish.BulletAmountType.FINITE,
                                ConstMathFish.BulletRtpUseType.HAVE, new BulletRtpUseConfigExtendHave(2.0)
                        ));
                    }
                });
                put(2, new HashMap<>(){
                    {
                        put(ConstCsbyJava.BulletEnumCsbyJava.NORMAL.getBulletMechanismCode(), new BulletConfigBase(
                                ConstMathFish.BulletType.NORMAL, new BulletConfigExtendNormal(),
                                ConstMathFish.BulletCostType.RATIO, new BulletCostConfigExtendRatio(ConstMathFish.BulletCostListType.TEN, new BulletCostListConfigExtendTen(), new BulletCostExchange(1.0, 1.0)),
                                ConstMathFish.BulletAmountType.INFINITE,
                                ConstMathFish.BulletRtpUseType.NONE, new BulletRtpUseConfigExtendNone()
                        ));
                        put(ConstCsbyJava.BulletEnumCsbyJava.MACHINE.getBulletMechanismCode(), new BulletConfigBase(
                                ConstMathFish.BulletType.NORMAL, new BulletConfigExtendNormal(),
                                ConstMathFish.BulletCostType.FREE, new BulletCostConfigExtendFree(),
                                ConstMathFish.BulletAmountType.FINITE,
                                ConstMathFish.BulletRtpUseType.HAVE, new BulletRtpUseConfigExtendHave(1.0)
                        ));
                        put(ConstCsbyJava.BulletEnumCsbyJava.DRILL.getBulletMechanismCode(), new BulletConfigBase(
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
            put(ConstCsbyJava.BulletEnumCsbyJava.NORMAL.getBulletMechanismCode(), new HashMap<>() {{
                put(1, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(2), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.02041, 5,  new int[]{2, 4, 6, 8, 10, 20},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{2}); add(new int[]{4}); add(new int[]{6}); add(new int[]{8}); add(new int[]{10}); add(new int[]{20}); add(new int[]{1000});}});}}, new HashMap<>(){{ put(1, new int[]{79207, 158415, 198019, 198019, 277227, 79207, 9906});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));

                put(2, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(2), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.02061, 5,  new int[]{4, 8, 12, 16, 20, 24},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{4}); add(new int[]{8}); add(new int[]{12}); add(new int[]{16}); add(new int[]{20}); add(new int[]{24});}});}}, new HashMap<>(){{ put(1, new int[]{50000, 50000, 100000, 250000, 250000, 300000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(3, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(3), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.02061, 5,  new int[]{4, 8, 12, 16, 20, 24},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{4}); add(new int[]{8}); add(new int[]{12}); add(new int[]{16}); add(new int[]{20}); add(new int[]{24});}});}}, new HashMap<>(){{ put(1, new int[]{50000, 50000, 100000, 250000, 250000, 300000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(4, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(4), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.02061, 5,  new int[]{4, 8, 12, 16, 20, 24},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{4}); add(new int[]{8}); add(new int[]{12}); add(new int[]{16}); add(new int[]{20}); add(new int[]{24});}});}}, new HashMap<>(){{ put(1, new int[]{50000, 50000, 100000, 250000, 250000, 300000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(5, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(5), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.02061, 5,  new int[]{4, 8, 12, 16, 20, 24},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{4}); add(new int[]{8}); add(new int[]{12}); add(new int[]{16}); add(new int[]{20}); add(new int[]{24});}});}}, new HashMap<>(){{ put(1, new int[]{50000, 50000, 100000, 250000, 250000, 300000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(6, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(6), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.02061, 5,  new int[]{4, 8, 12, 16, 20, 24},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{4}); add(new int[]{8}); add(new int[]{12}); add(new int[]{16}); add(new int[]{20}); add(new int[]{24});}});}}, new HashMap<>(){{ put(1, new int[]{50000, 50000, 100000, 250000, 250000, 300000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(7, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(7), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.02061, 5,  new int[]{4, 8, 12, 16, 20, 24},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{4}); add(new int[]{8}); add(new int[]{12}); add(new int[]{16}); add(new int[]{20}); add(new int[]{24});}});}}, new HashMap<>(){{ put(1, new int[]{50000, 50000, 100000, 250000, 250000, 300000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(8, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(8), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.02061, 5,  new int[]{4, 8, 12, 16, 20, 24},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{4}); add(new int[]{8}); add(new int[]{12}); add(new int[]{16}); add(new int[]{20}); add(new int[]{24});}});}}, new HashMap<>(){{ put(1, new int[]{50000, 50000, 100000, 250000, 250000, 300000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(9, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(9), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.02061, 5,  new int[]{4, 8, 12, 16, 20, 24},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{4}); add(new int[]{8}); add(new int[]{12}); add(new int[]{16}); add(new int[]{20}); add(new int[]{24});}});}}, new HashMap<>(){{ put(1, new int[]{50000, 50000, 100000, 250000, 250000, 300000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(10, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(10), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.02061, 5,  new int[]{4, 8, 12, 16, 20, 24},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{4}); add(new int[]{8}); add(new int[]{12}); add(new int[]{16}); add(new int[]{20}); add(new int[]{24});}});}}, new HashMap<>(){{ put(1, new int[]{50000, 50000, 100000, 250000, 250000, 300000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));

                put(11, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(12), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.04069, 5,  new int[]{35, 40, 45, 50, 55, 60},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{35}); add(new int[]{40}); add(new int[]{45}); add(new int[]{50}); add(new int[]{55}); add(new int[]{60});}});}}, new HashMap<>(){{ put(1, new int[]{130434, 173913, 173913, 260869, 130434, 130437});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(12, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(15), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.04069, 5,  new int[]{35, 40, 45, 50, 55, 60},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{35}); add(new int[]{40}); add(new int[]{45}); add(new int[]{50}); add(new int[]{55}); add(new int[]{60});}});}}, new HashMap<>(){{ put(1, new int[]{130434, 173913, 173913, 260869, 130434, 130437});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(13, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(18), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.04069, 5,  new int[]{35, 40, 45, 50, 55, 60},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{35}); add(new int[]{40}); add(new int[]{45}); add(new int[]{50}); add(new int[]{55}); add(new int[]{60});}});}}, new HashMap<>(){{ put(1, new int[]{130434, 173913, 173913, 260869, 130434, 130437});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(14, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(20), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.04069, 5,  new int[]{35, 40, 45, 50, 55, 60},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{35}); add(new int[]{40}); add(new int[]{45}); add(new int[]{50}); add(new int[]{55}); add(new int[]{60});}});}}, new HashMap<>(){{ put(1, new int[]{130434, 173913, 173913, 260869, 130434, 130437});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(15, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(25), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.04069, 5,  new int[]{35, 40, 45, 50, 55, 60},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{35}); add(new int[]{40}); add(new int[]{45}); add(new int[]{50}); add(new int[]{55}); add(new int[]{60});}});}}, new HashMap<>(){{ put(1, new int[]{130434, 173913, 173913, 260869, 130434, 130437});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(16, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(30), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.04069, 5,  new int[]{35, 40, 45, 50, 55, 60},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{35}); add(new int[]{40}); add(new int[]{45}); add(new int[]{50}); add(new int[]{55}); add(new int[]{60});}});}}, new HashMap<>(){{ put(1, new int[]{130434, 173913, 173913, 260869, 130434, 130437});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));

                put(17, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(40), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.06183, 5,  new int[]{40, 60, 70, 80, 100},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{40}); add(new int[]{60}); add(new int[]{70}); add(new int[]{80}); add(new int[]{100});}});}}, new HashMap<>(){{ put(1, new int[]{40000, 280000, 320000, 240000, 120000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(18, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(50), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.06183, 5,  new int[]{40, 60, 70, 80, 100},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{40}); add(new int[]{60}); add(new int[]{70}); add(new int[]{80}); add(new int[]{100});}});}}, new HashMap<>(){{ put(1, new int[]{40000, 280000, 320000, 240000, 120000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(19, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(80), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.06183, 5,  new int[]{40, 60, 70, 80, 100},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{40}); add(new int[]{60}); add(new int[]{70}); add(new int[]{80}); add(new int[]{100});}});}}, new HashMap<>(){{ put(1, new int[]{40000, 280000, 320000, 240000, 120000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(20, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(100), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.06183, 5,  new int[]{40, 60, 70, 80, 100},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{40}); add(new int[]{60}); add(new int[]{70}); add(new int[]{80}); add(new int[]{100});}});}}, new HashMap<>(){{ put(1, new int[]{40000, 280000, 320000, 240000, 120000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(21, new HitCtrConfig(ConstMathFish.HitType.RANDOM_ODDS, new HitTypeConfigExtendRandomOdds(new int[]{50, 75, 100, 125, 150}, new int[]{1, 1, 1, 1, 1}), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.06183, 5,  new int[]{40, 60, 70, 80, 100},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{40}); add(new int[]{60}); add(new int[]{70}); add(new int[]{80}); add(new int[]{100});}});}}, new HashMap<>(){{ put(1, new int[]{40000, 280000, 320000, 240000, 120000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));

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
                                ConstCsbyJava.BulletEnumCsbyJava.MACHINE.getBulletMechanismCode(),
                                999,
                                new int[]{20, 40, 60, 80, 100},
                                new int[]{1, 1, 1, 1, 1}),
                        ConstMathFish.RtpChoiceType.COMPANY_ADJUST,
                        new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(24, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS,
                        new HitTypeConfigExtendFixedOdds(10),
                        ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE,
                        new SpecialFeatureCtrConfigExtendRedEnvelope(0.06183, 5,  new int[]{40, 60, 70, 80, 100},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{40}); add(new int[]{60}); add(new int[]{70}); add(new int[]{80}); add(new int[]{100});}});}}, new HashMap<>(){{ put(1, new int[]{40000, 280000, 320000, 240000, 120000});}}),
                        ConstMathFish.AwardBulletType.ONE_TYPE,
                        new AwardBulletGtrConfigExtendOneType(ConstMathFish.AwardBulletCtrType.NORMAL,
                                new AwardBulletCtrConfigExtendNormal(),
                                ConstCsbyJava.BulletEnumCsbyJava.DRILL.getBulletMechanismCode(),
                                25,
                                new int[]{25},
                                new int[]{1}),
                        ConstMathFish.RtpChoiceType.COMPANY_ADJUST,
                        new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(25, new HitCtrConfig(ConstMathFish.HitType.WHEEL,
                        new HitTypeConfigExtendWheel(new int[]{40, 80, 120, 160, 200, 40, 120, 40, 80, 160}, new int[]{4, 5, 5, 5, 10, 4, 5, 2, 5, 5}),
                        ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE,
                        new SpecialFeatureCtrConfigExtendRedEnvelope(0.08251, 5,  new int[]{60, 80, 100, 120, 150, 200},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{60}); add(new int[]{80}); add(new int[]{100}); add(new int[]{120}); add(new int[]{150}); add(new int[]{200});}});}}, new HashMap<>(){{ put(1, new int[]{80000, 240000, 160000, 200000, 160000, 160000});}}),
                        ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.COMPANY_ADJUST,
                        new RtpChoiceHlrConfigExtendCompanyAdjust()));
                put(26, new HitCtrConfig(ConstMathFish.HitType.RED_ENVELOPE,
                        new HitTypeConfigExtendRedEnvelope(
                                5,
                                new int[]{80, 100, 120, 160, 200, 240, 300, 1000},
                                new int[][]{{1},{100}},
                                new HashMap<>() {{
                                    put(1, new ArrayList<>(){{
                                        add(new int[]{80});
                                        add(new int[]{100});
                                        add(new int[]{120});
                                        add(new int[]{160});
                                        add(new int[]{200});
                                        add(new int[]{240});
                                        add(new int[]{300});
                                        add(new int[]{1000});
                                    }});
                                }},
                                new HashMap<>() {{
                                    put(1, new int[]{142856, 142856, 142856, 142856, 142856, 142856, 142856, 8});
                                }}
                        ),
                        ConstMathFish.SpecialFeatureEnumType.NONE,
                        new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.COMPANY_ADJUST,
                        new RtpChoiceHlrConfigExtendCompanyAdjust()));
            }});
            put(ConstCsbyJava.BulletEnumCsbyJava.MACHINE.getBulletMechanismCode(), new HashMap<>() {{
                put(1, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(2), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.0198, 5,  new int[]{2, 4, 6, 8, 10, 20},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{2}); add(new int[]{4}); add(new int[]{6}); add(new int[]{8}); add(new int[]{10}); add(new int[]{20}); add(new int[]{1000});}});}}, new HashMap<>(){{ put(1, new int[]{79207, 158415, 198019, 198019, 277227, 79207, 9906});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));

                put(2, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(2), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.01999, 5,  new int[]{4, 8, 12, 16, 20, 24},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{4}); add(new int[]{8}); add(new int[]{12}); add(new int[]{16}); add(new int[]{20}); add(new int[]{24});}});}}, new HashMap<>(){{ put(1, new int[]{50000, 50000, 100000, 250000, 250000, 300000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(3, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(3), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.01999, 5,  new int[]{4, 8, 12, 16, 20, 24},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{4}); add(new int[]{8}); add(new int[]{12}); add(new int[]{16}); add(new int[]{20}); add(new int[]{24});}});}}, new HashMap<>(){{ put(1, new int[]{50000, 50000, 100000, 250000, 250000, 300000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(4, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(4), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.01999, 5,  new int[]{4, 8, 12, 16, 20, 24},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{4}); add(new int[]{8}); add(new int[]{12}); add(new int[]{16}); add(new int[]{20}); add(new int[]{24});}});}}, new HashMap<>(){{ put(1, new int[]{50000, 50000, 100000, 250000, 250000, 300000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(5, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(5), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.01999, 5,  new int[]{4, 8, 12, 16, 20, 24},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{4}); add(new int[]{8}); add(new int[]{12}); add(new int[]{16}); add(new int[]{20}); add(new int[]{24});}});}}, new HashMap<>(){{ put(1, new int[]{50000, 50000, 100000, 250000, 250000, 300000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(6, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(6), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.01999, 5,  new int[]{4, 8, 12, 16, 20, 24},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{4}); add(new int[]{8}); add(new int[]{12}); add(new int[]{16}); add(new int[]{20}); add(new int[]{24});}});}}, new HashMap<>(){{ put(1, new int[]{50000, 50000, 100000, 250000, 250000, 300000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(7, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(7), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.01999, 5,  new int[]{4, 8, 12, 16, 20, 24},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{4}); add(new int[]{8}); add(new int[]{12}); add(new int[]{16}); add(new int[]{20}); add(new int[]{24});}});}}, new HashMap<>(){{ put(1, new int[]{50000, 50000, 100000, 250000, 250000, 300000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(8, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(8), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.01999, 5,  new int[]{4, 8, 12, 16, 20, 24},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{4}); add(new int[]{8}); add(new int[]{12}); add(new int[]{16}); add(new int[]{20}); add(new int[]{24});}});}}, new HashMap<>(){{ put(1, new int[]{50000, 50000, 100000, 250000, 250000, 300000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(9, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(9), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.01999, 5,  new int[]{4, 8, 12, 16, 20, 24},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{4}); add(new int[]{8}); add(new int[]{12}); add(new int[]{16}); add(new int[]{20}); add(new int[]{24});}});}}, new HashMap<>(){{ put(1, new int[]{50000, 50000, 100000, 250000, 250000, 300000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(10, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(10), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.01999, 5,  new int[]{4, 8, 12, 16, 20, 24},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{4}); add(new int[]{8}); add(new int[]{12}); add(new int[]{16}); add(new int[]{20}); add(new int[]{24});}});}}, new HashMap<>(){{ put(1, new int[]{50000, 50000, 100000, 250000, 250000, 300000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));

                put(11, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(12), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.03947, 5,  new int[]{35, 40, 45, 50, 55, 60},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{35}); add(new int[]{40}); add(new int[]{45}); add(new int[]{50}); add(new int[]{55}); add(new int[]{60});}});}}, new HashMap<>(){{ put(1, new int[]{130434, 173913, 173913, 260869, 130434, 130437});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(12, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(15), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.03947, 5,  new int[]{35, 40, 45, 50, 55, 60},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{35}); add(new int[]{40}); add(new int[]{45}); add(new int[]{50}); add(new int[]{55}); add(new int[]{60});}});}}, new HashMap<>(){{ put(1, new int[]{130434, 173913, 173913, 260869, 130434, 130437});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(13, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(18), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.03947, 5,  new int[]{35, 40, 45, 50, 55, 60},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{35}); add(new int[]{40}); add(new int[]{45}); add(new int[]{50}); add(new int[]{55}); add(new int[]{60});}});}}, new HashMap<>(){{ put(1, new int[]{130434, 173913, 173913, 260869, 130434, 130437});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(14, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(20), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.03947, 5,  new int[]{35, 40, 45, 50, 55, 60},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{35}); add(new int[]{40}); add(new int[]{45}); add(new int[]{50}); add(new int[]{55}); add(new int[]{60});}});}}, new HashMap<>(){{ put(1, new int[]{130434, 173913, 173913, 260869, 130434, 130437});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(15, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(25), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.03947, 5,  new int[]{35, 40, 45, 50, 55, 60},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{35}); add(new int[]{40}); add(new int[]{45}); add(new int[]{50}); add(new int[]{55}); add(new int[]{60});}});}}, new HashMap<>(){{ put(1, new int[]{130434, 173913, 173913, 260869, 130434, 130437});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(16, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(30), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.03947, 5,  new int[]{35, 40, 45, 50, 55, 60},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{35}); add(new int[]{40}); add(new int[]{45}); add(new int[]{50}); add(new int[]{55}); add(new int[]{60});}});}}, new HashMap<>(){{ put(1, new int[]{130434, 173913, 173913, 260869, 130434, 130437});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));

                put(17, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(40), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.05997, 5,  new int[]{40, 60, 70, 80, 100},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{40}); add(new int[]{60}); add(new int[]{70}); add(new int[]{80}); add(new int[]{100});}});}}, new HashMap<>(){{ put(1, new int[]{40000, 280000, 320000, 240000, 120000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(18, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(50), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.05997, 5,  new int[]{40, 60, 70, 80, 100},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{40}); add(new int[]{60}); add(new int[]{70}); add(new int[]{80}); add(new int[]{100});}});}}, new HashMap<>(){{ put(1, new int[]{40000, 280000, 320000, 240000, 120000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(19, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(80), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.05997, 5,  new int[]{40, 60, 70, 80, 100},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{40}); add(new int[]{60}); add(new int[]{70}); add(new int[]{80}); add(new int[]{100});}});}}, new HashMap<>(){{ put(1, new int[]{40000, 280000, 320000, 240000, 120000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(20, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(100), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.05997, 5,  new int[]{40, 60, 70, 80, 100},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{40}); add(new int[]{60}); add(new int[]{70}); add(new int[]{80}); add(new int[]{100});}});}}, new HashMap<>(){{ put(1, new int[]{40000, 280000, 320000, 240000, 120000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(21, new HitCtrConfig(ConstMathFish.HitType.RANDOM_ODDS, new HitTypeConfigExtendRandomOdds(new int[]{50, 75, 100, 125, 150}, new int[]{1, 1, 1, 1, 1}), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.05997, 5,  new int[]{40, 60, 70, 80, 100},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{40}); add(new int[]{60}); add(new int[]{70}); add(new int[]{80}); add(new int[]{100});}});}}, new HashMap<>(){{ put(1, new int[]{40000, 280000, 320000, 240000, 120000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));

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
                        new AwardBulletGtrConfigExtendOneType(
                                ConstMathFish.AwardBulletCtrType.NORMAL,
                                new AwardBulletCtrConfigExtendNormal(),
                                ConstCsbyJava.BulletEnumCsbyJava.MACHINE.getBulletMechanismCode(),
                                999,
                                new int[]{20, 40, 60, 80, 100},
                                new int[]{1, 1, 1, 1, 1}),
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
                        new HitTypeConfigExtendWheel(new int[]{40, 80, 120, 160, 200, 40, 120, 40, 80, 160}, new int[]{4, 5, 5, 5, 10, 4, 5, 2, 5, 5}),
                        ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE,
                        new SpecialFeatureCtrConfigExtendRedEnvelope(0.08004, 5,  new int[]{60, 80, 100, 120, 150, 200},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{60}); add(new int[]{80}); add(new int[]{100}); add(new int[]{120}); add(new int[]{150}); add(new int[]{200});}});}}, new HashMap<>(){{ put(1, new int[]{80000, 240000, 160000, 200000, 160000, 160000});}}),
                        ConstMathFish.AwardBulletType.NONE,
                        new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.BULLET,
                        new RtpChoiceHlrConfigExtendBullet()));
                put(26, new HitCtrConfig(ConstMathFish.HitType.RED_ENVELOPE,
                        new HitTypeConfigExtendRedEnvelope(
                                5,
                                new int[]{80, 100, 120, 160, 200, 240, 300, 1000},
                                new int[][]{{1},{100}},
                                new HashMap<>() {{
                                    put(1, new ArrayList<>(){{
                                        add(new int[]{80});
                                        add(new int[]{100});
                                        add(new int[]{120});
                                        add(new int[]{160});
                                        add(new int[]{200});
                                        add(new int[]{240});
                                        add(new int[]{300});
                                        add(new int[]{1000});
                                    }});
                                }},
                                new HashMap<>() {{
                                    put(1, new int[]{142856, 142856, 142856, 142856, 142856, 142856, 142856, 8});
                                }}
                        ),
                        ConstMathFish.SpecialFeatureEnumType.NONE,
                        new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.BULLET,
                        new RtpChoiceHlrConfigExtendBullet()));
            }});
            put(ConstCsbyJava.BulletEnumCsbyJava.DRILL.getBulletMechanismCode(), new HashMap<>() {{
                put(1, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(2), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.0198, 5,  new int[]{2, 4, 6, 8, 10, 20},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{2}); add(new int[]{4}); add(new int[]{6}); add(new int[]{8}); add(new int[]{10}); add(new int[]{20}); add(new int[]{1000});}});}}, new HashMap<>(){{ put(1, new int[]{79207, 158415, 198019, 198019, 277227, 79207, 9906});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));

                put(2, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(2), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.01999, 5,  new int[]{4, 8, 12, 16, 20, 24},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{4}); add(new int[]{8}); add(new int[]{12}); add(new int[]{16}); add(new int[]{20}); add(new int[]{24});}});}}, new HashMap<>(){{ put(1, new int[]{50000, 50000, 100000, 250000, 250000, 300000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(3, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(3), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.01999, 5,  new int[]{4, 8, 12, 16, 20, 24},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{4}); add(new int[]{8}); add(new int[]{12}); add(new int[]{16}); add(new int[]{20}); add(new int[]{24});}});}}, new HashMap<>(){{ put(1, new int[]{50000, 50000, 100000, 250000, 250000, 300000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(4, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(4), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.01999, 5,  new int[]{4, 8, 12, 16, 20, 24},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{4}); add(new int[]{8}); add(new int[]{12}); add(new int[]{16}); add(new int[]{20}); add(new int[]{24});}});}}, new HashMap<>(){{ put(1, new int[]{50000, 50000, 100000, 250000, 250000, 300000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(5, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(5), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.01999, 5,  new int[]{4, 8, 12, 16, 20, 24},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{4}); add(new int[]{8}); add(new int[]{12}); add(new int[]{16}); add(new int[]{20}); add(new int[]{24});}});}}, new HashMap<>(){{ put(1, new int[]{50000, 50000, 100000, 250000, 250000, 300000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(6, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(6), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.01999, 5,  new int[]{4, 8, 12, 16, 20, 24},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{4}); add(new int[]{8}); add(new int[]{12}); add(new int[]{16}); add(new int[]{20}); add(new int[]{24});}});}}, new HashMap<>(){{ put(1, new int[]{50000, 50000, 100000, 250000, 250000, 300000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(7, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(7), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.01999, 5,  new int[]{4, 8, 12, 16, 20, 24},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{4}); add(new int[]{8}); add(new int[]{12}); add(new int[]{16}); add(new int[]{20}); add(new int[]{24});}});}}, new HashMap<>(){{ put(1, new int[]{50000, 50000, 100000, 250000, 250000, 300000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(8, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(8), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.01999, 5,  new int[]{4, 8, 12, 16, 20, 24},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{4}); add(new int[]{8}); add(new int[]{12}); add(new int[]{16}); add(new int[]{20}); add(new int[]{24});}});}}, new HashMap<>(){{ put(1, new int[]{50000, 50000, 100000, 250000, 250000, 300000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(9, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(9), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.01999, 5,  new int[]{4, 8, 12, 16, 20, 24},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{4}); add(new int[]{8}); add(new int[]{12}); add(new int[]{16}); add(new int[]{20}); add(new int[]{24});}});}}, new HashMap<>(){{ put(1, new int[]{50000, 50000, 100000, 250000, 250000, 300000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(10, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(10), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.01999, 5,  new int[]{4, 8, 12, 16, 20, 24},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{4}); add(new int[]{8}); add(new int[]{12}); add(new int[]{16}); add(new int[]{20}); add(new int[]{24});}});}}, new HashMap<>(){{ put(1, new int[]{50000, 50000, 100000, 250000, 250000, 300000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));

                put(11, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(12), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.03947, 5,  new int[]{35, 40, 45, 50, 55, 60},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{35}); add(new int[]{40}); add(new int[]{45}); add(new int[]{50}); add(new int[]{55}); add(new int[]{60});}});}}, new HashMap<>(){{ put(1, new int[]{130434, 173913, 173913, 260869, 130434, 130437});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(12, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(15), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.03947, 5,  new int[]{35, 40, 45, 50, 55, 60},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{35}); add(new int[]{40}); add(new int[]{45}); add(new int[]{50}); add(new int[]{55}); add(new int[]{60});}});}}, new HashMap<>(){{ put(1, new int[]{130434, 173913, 173913, 260869, 130434, 130437});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(13, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(18), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.03947, 5,  new int[]{35, 40, 45, 50, 55, 60},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{35}); add(new int[]{40}); add(new int[]{45}); add(new int[]{50}); add(new int[]{55}); add(new int[]{60});}});}}, new HashMap<>(){{ put(1, new int[]{130434, 173913, 173913, 260869, 130434, 130437});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(14, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(20), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.03947, 5,  new int[]{35, 40, 45, 50, 55, 60},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{35}); add(new int[]{40}); add(new int[]{45}); add(new int[]{50}); add(new int[]{55}); add(new int[]{60});}});}}, new HashMap<>(){{ put(1, new int[]{130434, 173913, 173913, 260869, 130434, 130437});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(15, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(25), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.03947, 5,  new int[]{35, 40, 45, 50, 55, 60},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{35}); add(new int[]{40}); add(new int[]{45}); add(new int[]{50}); add(new int[]{55}); add(new int[]{60});}});}}, new HashMap<>(){{ put(1, new int[]{130434, 173913, 173913, 260869, 130434, 130437});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(16, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(30), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.03947, 5,  new int[]{35, 40, 45, 50, 55, 60},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{35}); add(new int[]{40}); add(new int[]{45}); add(new int[]{50}); add(new int[]{55}); add(new int[]{60});}});}}, new HashMap<>(){{ put(1, new int[]{130434, 173913, 173913, 260869, 130434, 130437});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));

                put(17, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(40), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.05997, 5,  new int[]{40, 60, 70, 80, 100},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{40}); add(new int[]{60}); add(new int[]{70}); add(new int[]{80}); add(new int[]{100});}});}}, new HashMap<>(){{ put(1, new int[]{40000, 280000, 320000, 240000, 120000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(18, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(50), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.05997, 5,  new int[]{40, 60, 70, 80, 100},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{40}); add(new int[]{60}); add(new int[]{70}); add(new int[]{80}); add(new int[]{100});}});}}, new HashMap<>(){{ put(1, new int[]{40000, 280000, 320000, 240000, 120000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
                put(19, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(80), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.05997, 5,  new int[]{40, 60, 70, 80, 100},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{40}); add(new int[]{60}); add(new int[]{70}); add(new int[]{80}); add(new int[]{100});}});}}, new HashMap<>(){{ put(1, new int[]{40000, 280000, 320000, 240000, 120000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));

                put(20, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(100), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.01828, 5,  new int[]{10, 20, 30},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{10}); add(new int[]{20}); add(new int[]{30}); add(new int[]{1000});}});}}, new HashMap<>(){{ put(1, new int[]{399885, 399885, 199942, 288});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));

                put(21, new HitCtrConfig(ConstMathFish.HitType.RANDOM_ODDS, new HitTypeConfigExtendRandomOdds(new int[]{50, 75, 100, 125, 150}, new int[]{1, 1, 1, 1, 1}), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.05997, 5,  new int[]{40, 60, 70, 80, 100},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{40}); add(new int[]{60}); add(new int[]{70}); add(new int[]{80}); add(new int[]{100});}});}}, new HashMap<>(){{ put(1, new int[]{40000, 280000, 320000, 240000, 120000});}}), ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), ConstMathFish.RtpChoiceType.BULLET, new RtpChoiceHlrConfigExtendBullet()));
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
                        new HitTypeConfigExtendWheel(new int[]{40, 80, 120, 160, 200, 40, 120, 40, 80, 160}, new int[]{4, 5, 5, 5, 10, 4, 5, 2, 5, 5}),
                        ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureCtrConfigExtendRedEnvelope(0.08004, 5,  new int[]{60, 80, 100, 120, 150, 200},  new int[][]{{1},{100}}, new HashMap<>(){{ put(1, new ArrayList<>(){{add(new int[]{60}); add(new int[]{80}); add(new int[]{100}); add(new int[]{120}); add(new int[]{150}); add(new int[]{200});}});}}, new HashMap<>(){{ put(1, new int[]{80000, 240000, 160000, 200000, 160000, 160000});}}),
                        ConstMathFish.AwardBulletType.NONE,
                        new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.BULLET,
                        new RtpChoiceHlrConfigExtendBullet()));
                put(26, new HitCtrConfig(ConstMathFish.HitType.RED_ENVELOPE,
                        new HitTypeConfigExtendRedEnvelope(
                                5,
                                new int[]{80, 100, 120, 160, 200, 240, 300, 1000},
                                new int[][]{{1},{100}},
                                new HashMap<>() {{
                                    put(1, new ArrayList<>(){{
                                        add(new int[]{80});
                                        add(new int[]{100});
                                        add(new int[]{120});
                                        add(new int[]{160});
                                        add(new int[]{200});
                                        add(new int[]{240});
                                        add(new int[]{300});
                                        add(new int[]{1000});
                                    }});
                                }},
                                new HashMap<>() {{
                                    put(1, new int[]{142856, 142856, 142856, 142856, 142856, 142856, 142856, 8});
                                }}
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
