package org.lsj.gs.math.config.resources.tableGameConfig.games.pxky_java;

import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigSlot;
import org.lsj.gs.math.config.resources.tableGameConfig.core.AbstractTableGameResourceSlot;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfig;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfigCluster;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.BaseScreenReSpinScreenBox;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.OddsBox;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.config.FiniteAwardPoolConfig;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.config.FiniteAwardPoolConfigExtendBaseReSpin;
import org.lsj.gs.math.core.common.spinResultPgr.ConstPgrSlot;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.clientReelResult.ClientReelResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.clientReelResult.ClientReelStopResultExtendStopByDependentReelIndex;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.animationCtr.enity.config.AnimationConfig;
import org.lsj.gs.math.core.slot.animationCtr.enity.config.AnimationConfigExtendOddsAnimation;
import org.lsj.gs.math.core.slot.commonInputHlr.enity.config.CommonInputConfig;
import org.lsj.gs.math.core.slot.commonInputHlr.enity.config.CommonInputConfigExtendNone;
import org.lsj.gs.math.core.slot.gameStateInputHlrMgr.enity.config.GameStateInputConfig;
import org.lsj.gs.math.core.slot.gameStateInputHlrMgr.enity.config.GameStateInputConfigExtendNone;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.betSpinConfig.BetSpinConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.betSpinConfig.BetSpinConfigCluster;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.betSpinConfig.BetSpinConfigExtendNoneNormal;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.dampConfig.DampConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfigExtendFix;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.gameConfig.GameConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.gameConfig.GameHitConfigExtendWayGame;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.gameFlowConfig.GameFlowConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.initialScreenConfig.InitialScreenConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.initialScreenConfig.InitialScreenConfigExtendNone;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayCombo;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayTableConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.paymentConfig.PaymentConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.paymentConfig.PaymentConfigExtendRatio;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.readyHandConfig.ReadyHandConfigCluster;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfigExtendReelDependent;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelStripBox;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.RoundConfigNormal;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.roundNormalGameConfig.RoundNormalGameConfigExtendPxkyBaseGame;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.specialFeatureConfig.SpecialFeatureConfigCluster;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfig;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfigExtendRound;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResultExtendFix;
import org.lsj.gs.math.games.pxky_java.enity.config.GameStateConfigExtendPxkyBaseGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// 豼貅開運老虎機牌桌遊戲設定產生器
public class TableGameResourcePxkyJavaDefault extends AbstractTableGameResourceSlot {

    public TableGameConfigSlot create() {
        // 1. 計算遊戲調控設定
        GameAdjustConfig gameAdjustConfig = super.createGameAdjustConfig();

        // 2. 計算亂數產生器演算法設定
        RngAlgorithmConfig rngAlgorithmConfig = super.createRngAlgorithmConfig();

        // 3. 設定押注類型列表
        List<ConstMathSlot.BetType> betTypeList = new ArrayList<>() {
            {
                add(ConstMathSlot.BetType.NONE);
            }
        };

        // 4. 設定玩法類型列表
        List<ConstMathSlot.SpinType> SpinTypeList = new ArrayList<>() {
            {
                add(ConstMathSlot.SpinType.NORMAL);
            }
        };

        // 5. 設定投注玩法集合
        BetSpinConfigCluster betSpinConfigCluster = new BetSpinConfigCluster(
                // 押注列表
                new ArrayList<>() {
                    {
                        add(1.0);
                        add(5.0);
                        add(10.0);
                        add(20.0);
                        add(50.0);
                        add(100.0);
                        add(200.0);
                        add(500.0);
                        add(1000.0);
                        add(2000.0);
                    }
                },
                ConstMathSlot.BetType.NONE,
                ConstMathSlot.SpinType.NORMAL,
                new HashMap<>() {
                    {
                        put(ConstMathSlot.BetType.NONE, new HashMap<>() {
                            {
                                put(ConstMathSlot.SpinType.NORMAL, new BetSpinConfig(
                                        ConstMathSlot.BetSpinType.NONE_NORMAL,
                                        new BetSpinConfigExtendNoneNormal(),
                                        new PaymentConfig(ConstMathSlot.PaymentType.RATIO, new PaymentConfigExtendRatio(1.0, 1.0))));
                            }
                        });
                    }
                }
        );

        // 6. 邏輯類型設定
        ConstMathSlot.LogicType logicType = ConstMathSlot.LogicType.NORMAL;

        // 7. 設定高低表
        FiniteAwardPoolConfigExtendBaseReSpin highHEReel = new FiniteAwardPoolConfigExtendBaseReSpin(
                new ArrayList<>() {
                    {
                        add(new OddsBox(0, 892698));
                        add(new OddsBox(8, 98278));
                        add(new OddsBox(8, 9056));
                        add(new OddsBox(16, 754));
                        add(new OddsBox(18, 7677));
                        add(new OddsBox(18, 784));
                        add(new OddsBox(26, 377));
                        add(new OddsBox(28, 351));
                        add(new OddsBox(28, 83));
                        add(new OddsBox(36, 181));
                        add(new OddsBox(46, 301));
                        add(new OddsBox(58, 250));
                        add(new OddsBox(58, 105));
                        add(new OddsBox(66, 120));
                        add(new OddsBox(76, 75));
                        add(new OddsBox(88, 32));
                        add(new OddsBox(88, 3));
                        add(new OddsBox(96, 17));
                        add(new OddsBox(106, 8));
                        add(new OddsBox(888, 3));
                        add(new OddsBox(888, 2));
                        add(new OddsBox(906, 3));
                    }
                }, // 倍數資訊列表
                0, // 空白標誌 damp 列表
                new ArrayList<>() {
                    {
                        add(new int[]{1, 2});
                        add(new int[]{1, 3});
                        add(new int[]{1, 4});
                        add(new int[]{2, 1});
                        add(new int[]{2, 3});
                        add(new int[]{2, 4});
                        add(new int[]{3, 1});
                        add(new int[]{3, 2});
                        add(new int[]{3, 4});
                        add(new int[]{4, 1});
                        add(new int[]{4, 2});
                        add(new int[]{4, 3});
                    }
                }, // 破框畫面列表
                new HashMap<>() {
                    {
                        put(1, new ArrayList<>() {
                            {
                                add(20);
                                add(20);
                                add(20);
                                add(20);
                                add(1);
                                add(1);
                                add(20);
                                add(1);
                                add(1);
                                add(20);
                                add(1);
                                add(1);
                            }
                        });
                        put(2, new ArrayList<>() {
                            {
                                add(50);
                                add(1);
                                add(1);
                                add(50);
                                add(20);
                                add(10);
                                add(1);
                                add(20);
                                add(1);
                                add(1);
                                add(10);
                                add(1);
                            }
                        });
                        put(3, new ArrayList<>() {
                            {
                                add(1);
                                add(50);
                                add(1);
                                add(1);
                                add(20);
                                add(1);
                                add(50);
                                add(20);
                                add(10);
                                add(1);
                                add(1);
                                add(10);
                            }
                        });
                        put(4, new ArrayList<>() {
                            {
                                add(1);
                                add(1);
                                add(50);
                                add(1);
                                add(1);
                                add(20);
                                add(1);
                                add(1);
                                add(10);
                                add(50);
                                add(20);
                                add(10);
                            }
                        });
                    }
                }, // 主要標誌對應damp的權重
                new HashMap<>() {
                    {
                        put(0, new ArrayList<>() {
                            {
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 0, 1});
                                }}, 6));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 0, 2});
                                }}, 5));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 0, 3});
                                }}, 4));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 0, 4});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{0, 1, 1});
                                }}, 6));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{0, 2, 2});
                                }}, 5));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{0, 3, 3});
                                }}, 4));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{0, 4, 4});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 0, 0});
                                }}, 4));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 0, 0});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 0, 0});
                                }}, 2));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 0, 0});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{0, 0, 0});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{0, 0, 1});
                                }}, 4));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{0, 0, 2});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{0, 0, 3});
                                }}, 2));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{0, 0, 4});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{0, 1, 0});
                                }}, 4));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{0, 2, 0});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{0, 3, 0});
                                }}, 2));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{0, 4, 0});
                                }}, 1));
                            }
                        });
                        put(1, new ArrayList<>() {
                            {
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 3, 3});
                                }}, 4));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 4, 4});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 2, 2});
                                }}, 5));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 4, 4});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 2, 2});
                                }}, 5));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 3, 3});
                                }}, 4));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 3, 2});
                                }}, 5));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 4, 2});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 2, 3});
                                }}, 4));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 4, 3});
                                }}, 4));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 2, 4});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 3, 4});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 3, 4});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 4, 3});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 2, 4});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 4, 2});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 2, 3});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 3, 2});
                                }}, 1));
                            }
                        });
                        put(2, new ArrayList<>() {
                            {
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 2, 0});
                                    add(new int[]{2, 2, 3});
                                }}, 5));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 2, 0});
                                    add(new int[]{2, 2, 4});
                                }}, 5));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 3, 0});
                                    add(new int[]{3, 3, 2});
                                }}, 4));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 3, 0});
                                    add(new int[]{3, 3, 4});
                                }}, 4));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 4, 0});
                                    add(new int[]{4, 4, 2});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 4, 0});
                                    add(new int[]{4, 4, 3});
                                }}, 3));
                            }
                        });
                        put(3, new ArrayList<>() {
                            {
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 2, 3});
                                    add(new int[]{2, 2, 4});
                                }}, 5));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 2, 4});
                                    add(new int[]{2, 2, 3});
                                }}, 5));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 3, 2});
                                    add(new int[]{3, 3, 4});
                                }}, 4));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 3, 4});
                                    add(new int[]{3, 3, 2});
                                }}, 4));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 4, 2});
                                    add(new int[]{4, 4, 3});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 4, 3});
                                    add(new int[]{4, 4, 2});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 2, 3});
                                    add(new int[]{2, 2, 3});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 2, 4});
                                    add(new int[]{2, 2, 4});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 3, 2});
                                    add(new int[]{3, 3, 2});
                                }}, 2));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 3, 4});
                                    add(new int[]{3, 3, 4});
                                }}, 2));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 4, 2});
                                    add(new int[]{4, 4, 2});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 4, 3});
                                    add(new int[]{4, 4, 3});
                                }}, 1));
                            }
                        });
                        put(4, new ArrayList<>() {
                            {
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 2, 2});
                                }}, 5));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 3, 3});
                                }}, 4));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 4, 4});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 1, 1});
                                }}, 6));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 1, 1});
                                }}, 6));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 1, 1});
                                }}, 6));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 1, 2});
                                }}, 5));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 1, 3});
                                }}, 4));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 1, 4});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 2, 3});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 3, 2});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 1, 3});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 3, 1});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 1, 2});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 2, 1});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 2, 4});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 4, 2});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 1, 4});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 4, 1});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 1, 2});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 2, 1});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 3, 4});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 4, 3});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 1, 4});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 4, 1});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 1, 3});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 3, 1});
                                }}, 1));
                            }
                        });
                        put(5, new ArrayList<>() {
                            {
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 1, 0});
                                    add(new int[]{1, 1, 2});
                                }}, 6));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 1, 0});
                                    add(new int[]{1, 1, 3});
                                }}, 6));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 1, 0});
                                    add(new int[]{1, 1, 4});
                                }}, 6));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 2, 0});
                                    add(new int[]{2, 2, 1});
                                }}, 5));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 3, 0});
                                    add(new int[]{3, 3, 1});
                                }}, 4));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 4, 0});
                                    add(new int[]{4, 4, 1});
                                }}, 3));
                            }
                        });
                        put(6, new ArrayList<>() {
                            {
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 2, 3});
                                    add(new int[]{2, 2, 1});
                                }}, 5));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 2, 4});
                                    add(new int[]{2, 2, 1});
                                }}, 5));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 3, 2});
                                    add(new int[]{3, 3, 1});
                                }}, 4));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 3, 4});
                                    add(new int[]{3, 3, 1});
                                }}, 4));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 4, 2});
                                    add(new int[]{4, 4, 1});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 4, 3});
                                    add(new int[]{4, 4, 1});
                                }}, 3));
                            }
                        });
                        put(7, new ArrayList<>() {
                            {
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 4, 4});
                                }}, 1));
                            }
                        });
                        put(8, new ArrayList<>() {
                            {
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 4, 0});
                                    add(new int[]{4, 4, 4});
                                }}, 1));
                            }
                        });
                        put(9, new ArrayList<>() {
                            {
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 1, 2});
                                    add(new int[]{1, 1, 3});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 1, 2});
                                    add(new int[]{1, 1, 4});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 1, 2});
                                    add(new int[]{1, 1, 2});
                                }}, 2));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 1, 3});
                                    add(new int[]{1, 1, 3});
                                }}, 2));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 1, 3});
                                    add(new int[]{1, 1, 4});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 1, 3});
                                    add(new int[]{1, 1, 2});
                                }}, 6));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 1, 4});
                                    add(new int[]{1, 1, 3});
                                }}, 4));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 1, 4});
                                    add(new int[]{1, 1, 4});
                                }}, 2));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 1, 4});
                                    add(new int[]{1, 1, 2});
                                }}, 6));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 4, 3});
                                    add(new int[]{4, 4, 4});
                                }}, 5));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 4, 2});
                                    add(new int[]{4, 4, 4});
                                }}, 5));
                            }
                        });
                        put(10, new ArrayList<>() {
                            {
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 4, 1});
                                    add(new int[]{4, 4, 4});
                                }}, 1));
                            }
                        });
                        put(11, new ArrayList<>() {
                            {
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 3, 3});
                                }}, 1));
                            }
                        });
                        put(12, new ArrayList<>() {
                            {
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 3, 0});
                                    add(new int[]{3, 3, 3});
                                }}, 1));
                            }
                        });
                        put(13, new ArrayList<>() {
                            {
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 3, 2});
                                    add(new int[]{3, 3, 3});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 3, 4});
                                    add(new int[]{3, 3, 3});
                                }}, 1));
                            }
                        });
                        put(14, new ArrayList<>() {
                            {
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 3, 1});
                                    add(new int[]{3, 3, 3});
                                }}, 1));
                            }
                        });
                        put(15, new ArrayList<>() {
                            {
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 2, 2});
                                }}, 1));
                            }
                        });
                        put(16, new ArrayList<>() {
                            {
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 2, 0});
                                    add(new int[]{2, 2, 2});
                                }}, 1));
                            }
                        });
                        put(17, new ArrayList<>() {
                            {
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 2, 3});
                                    add(new int[]{2, 2, 2});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 2, 4});
                                    add(new int[]{2, 2, 2});
                                }}, 1));
                            }
                        });
                        put(18, new ArrayList<>() {
                            {
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 2, 1});
                                    add(new int[]{2, 2, 2});
                                }}, 1));
                            }
                        });
                        put(19, new ArrayList<>() {
                            {
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 1, 1});
                                }}, 1));
                            }
                        });
                        put(20, new ArrayList<>() {
                            {
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 1, 0});
                                    add(new int[]{1, 1, 1});
                                }}, 1));
                            }
                        });
                        put(21, new ArrayList<>() {
                            {
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 1, 2});
                                    add(new int[]{1, 1, 1});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 1, 3});
                                    add(new int[]{1, 1, 1});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 1, 4});
                                    add(new int[]{1, 1, 1});
                                }}, 1));
                            }
                        });
                    }
                } // <倍數索引, 畫面列表> 對應表
        );
        FiniteAwardPoolConfigExtendBaseReSpin lowHEReel = new FiniteAwardPoolConfigExtendBaseReSpin(
                new ArrayList<>() {
                    {
                        add(new OddsBox(0, 892698));
                        add(new OddsBox(8, 85602));
                    }
                }, // 倍數資訊列表
                0, // 空白標誌 damp 列表
                new ArrayList<>() {
                    {
                        add(new int[]{1, 2});
                        add(new int[]{1, 3});
                        add(new int[]{1, 4});
                        add(new int[]{2, 1});
                        add(new int[]{2, 3});
                        add(new int[]{2, 4});
                        add(new int[]{3, 1});
                        add(new int[]{3, 2});
                        add(new int[]{3, 4});
                        add(new int[]{4, 1});
                        add(new int[]{4, 2});
                        add(new int[]{4, 3});
                    }
                }, // 破框畫面列表
                new HashMap<>() {
                    {
                        put(1, new ArrayList<>() {
                            {
                                add(20);
                                add(20);
                                add(20);
                                add(20);
                                add(1);
                                add(1);
                                add(20);
                                add(1);
                                add(1);
                                add(20);
                                add(1);
                                add(1);
                            }
                        });
                        put(2, new ArrayList<>() {
                            {
                                add(50);
                                add(1);
                                add(1);
                                add(50);
                                add(20);
                                add(10);
                                add(1);
                                add(20);
                                add(1);
                                add(1);
                                add(10);
                                add(1);
                            }
                        });
                        put(3, new ArrayList<>() {
                            {
                                add(1);
                                add(50);
                                add(1);
                                add(1);
                                add(20);
                                add(1);
                                add(50);
                                add(20);
                                add(10);
                                add(1);
                                add(1);
                                add(10);
                            }
                        });
                        put(4, new ArrayList<>() {
                            {
                                add(1);
                                add(1);
                                add(50);
                                add(1);
                                add(1);
                                add(20);
                                add(1);
                                add(1);
                                add(10);
                                add(50);
                                add(20);
                                add(10);
                            }
                        });
                    }
                }, // 主要標誌對應damp的權重
                new HashMap<>() {
                    {
                        put(0, new ArrayList<>() {
                            {
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 0, 1});
                                }}, 6));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 0, 2});
                                }}, 5));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 0, 3});
                                }}, 4));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 0, 4});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{0, 1, 1});
                                }}, 6));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{0, 2, 2});
                                }}, 5));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{0, 3, 3});
                                }}, 4));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{0, 4, 4});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{1, 0, 0});
                                }}, 4));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 0, 0});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 0, 0});
                                }}, 2));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 0, 0});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{0, 0, 0});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{0, 0, 1});
                                }}, 4));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{0, 0, 2});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{0, 0, 3});
                                }}, 2));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{0, 0, 4});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{0, 1, 0});
                                }}, 4));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{0, 2, 0});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{0, 3, 0});
                                }}, 2));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{0, 4, 0});
                                }}, 1));
                            }
                        });
                        put(1, new ArrayList<>() {
                            {
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 3, 3});
                                }}, 4));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 4, 4});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 2, 2});
                                }}, 5));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 4, 4});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 2, 2});
                                }}, 5));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 3, 3});
                                }}, 4));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 3, 2});
                                }}, 5));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 4, 2});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 2, 3});
                                }}, 4));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 4, 3});
                                }}, 4));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 2, 4});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 3, 4});
                                }}, 3));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 3, 4});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 4, 3});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 2, 4});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 4, 2});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 2, 3});
                                }}, 1));
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 3, 2});
                                }}, 1));
                            }
                        });
                    }
                } // <倍數索引, 畫面列表> 對應表
        );


        // 8. 設定遊戲狀態設定
        GameStateConfig pxkyBaseGameStateConfig = new GameStateConfig(
                ConstMathSlot.GameStateType.PXKY_BASEGAME,
                new GameStateConfigExtendPxkyBaseGame(
                        new FiniteAwardPoolConfig(ConstMathSlot.FiniteAwardPoolType.BASE_RE_SPIN,
                                new HashMap<>() {
                                    {
                                        put(ConstMathSlot.ReelRtpType.HIGH, highHEReel);
                                        put(ConstMathSlot.ReelRtpType.LOW, lowHEReel);
                                    }
                                })
                ),
                new GameStateInputConfig(ConstMathSlot.GameStateInputType.NONE, new GameStateInputConfigExtendNone()),
                ConstMathSlot.RoundType.NORMAL,
                new RoundConfigNormal(
                        new ReelConfig(
                                ConstMathSlot.ReelType.REEL_DEPENDENT,
                                new ArrayList<>() {
                                    {
                                        add(new ReelStripBox(
                                                0,
                                                1,
                                                new ReelConfigExtendReelDependent(
                                                        new ArrayList<>() {
                                                            {
                                                                add(new ArrayList<>() {{
                                                                    add(0);
                                                                    add(1);
                                                                    add(0);
                                                                    add(2);
                                                                    add(0);
                                                                    add(1);
                                                                    add(0);
                                                                    add(3);
                                                                    add(0);
                                                                    add(1);
                                                                    add(0);
                                                                    add(4);
                                                                    add(0);
                                                                    add(2);
                                                                    add(0);
                                                                    add(3);
                                                                    add(0);
                                                                    add(2);
                                                                    add(0);
                                                                    add(4);
                                                                    add(0);
                                                                    add(3);
                                                                    add(0);
                                                                    add(4);
                                                                }});
                                                                add(new ArrayList<>() {{
                                                                    add(0);
                                                                    add(1);
                                                                    add(0);
                                                                    add(2);
                                                                    add(0);
                                                                    add(1);
                                                                    add(0);
                                                                    add(3);
                                                                    add(0);
                                                                    add(1);
                                                                    add(0);
                                                                    add(4);
                                                                    add(0);
                                                                    add(2);
                                                                    add(0);
                                                                    add(3);
                                                                    add(0);
                                                                    add(2);
                                                                    add(0);
                                                                    add(4);
                                                                    add(0);
                                                                    add(3);
                                                                    add(0);
                                                                    add(4);
                                                                }});
                                                                add(new ArrayList<>() {{
                                                                    add(0);
                                                                    add(1);
                                                                    add(0);
                                                                    add(2);
                                                                    add(0);
                                                                    add(1);
                                                                    add(0);
                                                                    add(3);
                                                                    add(0);
                                                                    add(1);
                                                                    add(0);
                                                                    add(4);
                                                                    add(0);
                                                                    add(2);
                                                                    add(0);
                                                                    add(3);
                                                                    add(0);
                                                                    add(2);
                                                                    add(0);
                                                                    add(4);
                                                                    add(0);
                                                                    add(3);
                                                                    add(0);
                                                                    add(4);
                                                                }});
                                                            }
                                                        }),
                                                ConstMathSlot.ReelRtpType.HIGH,
                                                ConstMathSlot.ReelStopType.STOP_BY_DEPENDENT_REEL_INDEX));
                                        add(new ReelStripBox(
                                                1,
                                                1,
                                                new ReelConfigExtendReelDependent(
                                                        new ArrayList<>() {
                                                            {
                                                                add(new ArrayList<>() {{
                                                                    add(0);
                                                                    add(1);
                                                                    add(0);
                                                                    add(2);
                                                                    add(0);
                                                                    add(1);
                                                                    add(0);
                                                                    add(3);
                                                                    add(0);
                                                                    add(1);
                                                                    add(0);
                                                                    add(4);
                                                                    add(0);
                                                                    add(2);
                                                                    add(0);
                                                                    add(3);
                                                                    add(0);
                                                                    add(2);
                                                                    add(0);
                                                                    add(4);
                                                                    add(0);
                                                                    add(3);
                                                                    add(0);
                                                                    add(4);
                                                                }});
                                                                add(new ArrayList<>() {{
                                                                    add(0);
                                                                    add(1);
                                                                    add(0);
                                                                    add(2);
                                                                    add(0);
                                                                    add(1);
                                                                    add(0);
                                                                    add(3);
                                                                    add(0);
                                                                    add(1);
                                                                    add(0);
                                                                    add(4);
                                                                    add(0);
                                                                    add(2);
                                                                    add(0);
                                                                    add(3);
                                                                    add(0);
                                                                    add(2);
                                                                    add(0);
                                                                    add(4);
                                                                    add(0);
                                                                    add(3);
                                                                    add(0);
                                                                    add(4);
                                                                }});
                                                                add(new ArrayList<>() {{
                                                                    add(0);
                                                                    add(1);
                                                                    add(0);
                                                                    add(2);
                                                                    add(0);
                                                                    add(1);
                                                                    add(0);
                                                                    add(3);
                                                                    add(0);
                                                                    add(1);
                                                                    add(0);
                                                                    add(4);
                                                                    add(0);
                                                                    add(2);
                                                                    add(0);
                                                                    add(3);
                                                                    add(0);
                                                                    add(2);
                                                                    add(0);
                                                                    add(4);
                                                                    add(0);
                                                                    add(3);
                                                                    add(0);
                                                                    add(4);
                                                                }});
                                                            }
                                                        }),
                                                ConstMathSlot.ReelRtpType.LOW,
                                                ConstMathSlot.ReelStopType.STOP_BY_DEPENDENT_REEL_INDEX));
                                    }
                                }),
                        new InitialScreenConfig(
                            new ClientReelResult(0, ConstPgrSlot.ClientReelStopType.STOP_BY_DEPENDENT_REEL_INDEX, new ClientReelStopResultExtendStopByDependentReelIndex(new ArrayList<>() {
                                {
                                    add(15);
                                    add(10);
                                    add(13);
                                }
                            })), new FrameResult(ConstMathSlot.FrameType.FIX,
                            new FrameResultExtendFix(new ArrayList<>() {
                                    {
                                        add(1);
                                        add(1);
                                        add(1);
                                    }
                                })),
                                ConstMathSlot.InitialScreenType.NONE, new InitialScreenConfigExtendNone()
                        ),
                        new FrameConfig(
                            ConstMathSlot.FrameType.FIX,
                            new FrameConfigExtendFix(new ArrayList<>() {
                            {
                                add(1);
                                add(1);
                                add(1);
                            }
                        })),
                        new SymbolConfig(new ArrayList<>() {
                            {
                                add(ConstMathSlot.SymbolAttribute.ACE);
                                add(ConstMathSlot.SymbolAttribute.M1);
                                add(ConstMathSlot.SymbolAttribute.M2);
                                add(ConstMathSlot.SymbolAttribute.M3);
                                add(ConstMathSlot.SymbolAttribute.M4);
                            }
                        },
                                new ArrayList<>() {
                                    {
                                        add(ConstMathSlot.SymbolAttributeType.EMPTY_SYMBOL);
                                        add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                                        add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                                        add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                                        add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                                    }
                                }),
                        new DampConfig(ConstMathSlot.DampType.ONE_DAMP, ConstMathSlot.DampType.ONE_DAMP),
                        new GameConfig(ConstMathSlot.GameHitType.WAY_GAME, new GameHitConfigExtendWayGame(ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT)),
                        new PayTableConfig(new ArrayList<>() {
                            {
                                add(new PayCombo(
                                        new ArrayList<>() {
                                            {
                                                add(1);
                                            }
                                        },
                                        new ArrayList<>() {
                                            {
                                                add(0);
                                                add(0);
                                                add(888);
                                            }
                                        }
                                ));
                                add(new PayCombo(
                                        new ArrayList<>() {
                                            {
                                                add(2);
                                            }
                                        },
                                        new ArrayList<>() {
                                            {
                                                add(0);
                                                add(0);
                                                add(88);
                                            }
                                        }
                                ));
                                add(new PayCombo(
                                        new ArrayList<>() {
                                            {
                                                add(3);
                                            }
                                        },
                                        new ArrayList<>() {
                                            {
                                                add(0);
                                                add(0);
                                                add(58);
                                            }
                                        }
                                ));
                                add(new PayCombo(
                                        new ArrayList<>() {
                                            {
                                                add(4);
                                            }
                                        },
                                        new ArrayList<>() {
                                            {
                                                add(0);
                                                add(0);
                                                add(28);
                                            }
                                        }
                                ));
                                add(new PayCombo(
                                        new ArrayList<>() {
                                            {
                                                add(1);
                                                add(2);
                                            }
                                        },
                                        new ArrayList<>() {
                                            {
                                                add(0);
                                                add(0);
                                                add(18);
                                            }
                                        }
                                ));
                                add(new PayCombo(
                                        new ArrayList<>() {
                                            {
                                                add(1);
                                                add(3);
                                            }
                                        },
                                        new ArrayList<>() {
                                            {
                                                add(0);
                                                add(0);
                                                add(18);
                                            }
                                        }
                                ));
                                add(new PayCombo(
                                        new ArrayList<>() {
                                            {
                                                add(1);
                                                add(4);
                                            }
                                        },
                                        new ArrayList<>() {
                                            {
                                                add(0);
                                                add(0);
                                                add(18);
                                            }
                                        }
                                ));
                                add(new PayCombo(
                                        new ArrayList<>() {
                                            {
                                                add(1);
                                                add(2);
                                                add(3);
                                            }
                                        },
                                        new ArrayList<>() {
                                            {
                                                add(0);
                                                add(0);
                                                add(18);
                                            }
                                        }
                                ));
                                add(new PayCombo(
                                        new ArrayList<>() {
                                            {
                                                add(1);
                                                add(2);
                                                add(4);
                                            }
                                        },
                                        new ArrayList<>() {
                                            {
                                                add(0);
                                                add(0);
                                                add(18);
                                            }
                                        }
                                ));
                                add(new PayCombo(
                                        new ArrayList<>() {
                                            {
                                                add(1);
                                                add(3);
                                                add(4);
                                            }
                                        },
                                        new ArrayList<>() {
                                            {
                                                add(0);
                                                add(0);
                                                add(18);
                                            }
                                        }
                                ));
                                add(new PayCombo(
                                        new ArrayList<>() {
                                            {
                                                add(2);
                                                add(3);
                                            }
                                        },
                                        new ArrayList<>() {
                                            {
                                                add(0);
                                                add(0);
                                                add(8);
                                            }
                                        }
                                ));
                                add(new PayCombo(
                                        new ArrayList<>() {
                                            {
                                                add(2);
                                                add(4);
                                            }
                                        },
                                        new ArrayList<>() {
                                            {
                                                add(0);
                                                add(0);
                                                add(8);
                                            }
                                        }
                                ));
                                add(new PayCombo(
                                        new ArrayList<>() {
                                            {
                                                add(3);
                                                add(4);
                                            }
                                        },
                                        new ArrayList<>() {
                                            {
                                                add(0);
                                                add(0);
                                                add(8);
                                            }
                                        }
                                ));
                                add(new PayCombo(
                                        new ArrayList<>() {
                                            {
                                                add(2);
                                                add(3);
                                                add(4);
                                            }
                                        },
                                        new ArrayList<>() {
                                            {
                                                add(0);
                                                add(0);
                                                add(8);
                                            }
                                        }
                                ));
                            }
                        }),
                        new ProgressConfig(ConstMathSlot.ProgressType.ROUND, new ProgressConfigExtendRound(1, 0, 1)),
                        new SpecialFeatureConfigCluster(),
                        new ReadyHandConfigCluster(),
                        ConstMathSlot.RoundNormalGameType.PXKY_BASEGAME,
                        new RoundNormalGameConfigExtendPxkyBaseGame()
                ),
                ConstMathSlot.SlotDetailType.PXKY_BASEGAME);


        // 9. 設定遊戲狀態設定集合
        GameStateConfigCluster gameStateConfigCluster = new GameStateConfigCluster(
                new HashMap<>() {
                    {
                        put(ConstMathSlot.ReelRtpType.HIGH, 1.1);
                        put(ConstMathSlot.ReelRtpType.LOW, 0.7);
                    }
                }, // 高低表設定
                new CommonInputConfig(ConstMathSlot.CommonInputType.NONE, new CommonInputConfigExtendNone()), // 通用輸入設定
                new HashMap<>() {
                    {
                        put(ConstMathSlot.BetType.NONE, new HashMap<>() {
                            {
                                put(ConstMathSlot.SpinType.NORMAL, 0);
                            }
                        });
                    }
                }, // 押注玩法遊戲識別碼對應表
                new ArrayList<>() {
                    {
                        add(
                                pxkyBaseGameStateConfig
                        );
                    }
                } // 遊戲狀態設定列表
        );

        // 10. 設定流程設定
        GameFlowConfig gameFlowConfig = new GameFlowConfig(
                new ArrayList<>() {
                    {
                        add(new ArrayList<>() {
                            {
                                add(ConstMathSlot.Condition.CD_FALSE);
                                add(ConstMathSlot.Condition.CD_TRUE);
                            }
                        });
                        add(new ArrayList<>() {
                            {
                                add(ConstMathSlot.Condition.CD_FALSE);
                                add(ConstMathSlot.Condition.CD_FALSE);
                            }
                        });
                    }
                }
        );

        // 11. 動畫設定
        AnimationConfig animationConfig = new AnimationConfig(ConstMathSlot.AnimationType.ODDS_ANIMATION, new AnimationConfigExtendOddsAnimation(
                new HashMap<>() {
                    {
                        put(ConstMathSlot.OddsWinType.BIG_WIN, 58);
                        put(ConstMathSlot.OddsWinType.MEGA_WIN, 88);
                        put(ConstMathSlot.OddsWinType.ULTRA_WIN, 888);
                    }
                }
        ));

        return new TableGameConfigSlot(
                gameAdjustConfig,
                rngAlgorithmConfig,
                betTypeList,
                SpinTypeList,
                betSpinConfigCluster,
                logicType,
                gameStateConfigCluster,
                gameFlowConfig,
                animationConfig
        );
    }
}
