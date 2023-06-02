package org.lsj.gs.math.config.resources.tableGameConfig.games._develop_by_java;

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
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendTripleWheel;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.AwardBulletGtrConfigExtendNone;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.rtpChoiceHlr.enity.RtpChoiceHlrConfigExtendCompanyAdjust;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureCtrConfigExtend.SpecialFeatureCtrConfigExtendNone;
import org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.server.RobotBeginMoneyLimitBase;
import org.lsj.gs.math.games._develop_by_java.enity.ConstDevelopByJava;

import java.util.HashMap;
import java.util.Map;

// 模板捕魚牌桌遊戲設定產生器
public class TableGameResourceDevelopByJavaDefault extends AbstractTableGameResourceFish {
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
                        put(ConstDevelopByJava.BulletEnumDevelopByJava.NORMAL.getBulletMechanismCode(), new BulletConfigBase(
                                ConstMathFish.BulletType.NORMAL, new BulletConfigExtendNormal(),
                                ConstMathFish.BulletCostType.RATIO, new BulletCostConfigExtendRatio(ConstMathFish.BulletCostListType.TEN_MULTI_TEN, new BulletCostListConfigExtendTenMultiTen(), new BulletCostExchange(1.0, 1.0)),
                                ConstMathFish.BulletAmountType.INFINITE,
                                ConstMathFish.BulletRtpUseType.NONE, new BulletRtpUseConfigExtendNone()));
                    }
                });
                put(2, new HashMap<>(){
                    {
                        put(ConstDevelopByJava.BulletEnumDevelopByJava.NORMAL.getBulletMechanismCode(), new BulletConfigBase(
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
                put(2, new HitCtrConfig(ConstMathFish.HitType.TRIPLE_WHEEL,
                        new HitTypeConfigExtendTripleWheel(
                                new int[]{15,20,30,40,15,20,30,40},
                                new int[]{2,3,4,2,3,4},
                                new int[]{1,2,3,1,2,3},

                                new int[]{2375,1500,660,465,2375,1500,660,465},
                                new HashMap<>(){{
                                    put(15, new int[]{875,1188,313,875,1187,312});
                                    put(20, new int[]{875,313,313,875,312,312});
                                    put(30, new int[]{313,270,78,312,270,77});
                                    put(40, new int[]{313,78,75,312,77,75});
                                }},
                                new HashMap<>(){{
                                    put(30, new HashMap<>() {{
                                        put(15,new int[]{400,250,225,400,250,225});
                                    }});
                                    put(45, new HashMap<>() {{
                                        put(15,new int[]{850,225,113,850,225,112});
                                    }});
                                    put(60, new HashMap<>() {{
                                        put(15,new int[]{250,50,13,250,50,12});
                                        put(20,new int[]{250,50,13,250,50,12});
                                        put(30,new int[]{250,50,13,250,50,12});
                                    }});
                                    put(40, new HashMap<>() {{
                                        put(20,new int[]{550,275,50,550,275,50});
                                    }});
                                    put(80, new HashMap<>() {{
                                        put(20,new int[]{275,25,13,275,25,12});
                                        put(40,new int[]{275,25,13,275,25,12});
                                    }});
                                    put(90, new HashMap<>() {{
                                        put(30,new int[]{225,13,33,225,12,32});
                                    }});
                                    put(120, new HashMap<>() {{
                                        put(30,new int[]{50,13,15,50,12,15});
                                        put(40,new int[]{50,13,15,50,12,15});
                                    }});
                                    put(160, new HashMap<>() {{
                                        put(40,new int[]{25,30,20,25,30,20});
                                    }});
                                }}
                        ),
                        ConstMathFish.SpecialFeatureEnumType.NONE,new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE,new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.COMPANY_ADJUST,new RtpChoiceHlrConfigExtendCompanyAdjust()));
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
