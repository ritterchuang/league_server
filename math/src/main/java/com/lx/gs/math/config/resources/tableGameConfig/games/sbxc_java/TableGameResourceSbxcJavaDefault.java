package com.lx.gs.math.config.resources.tableGameConfig.games.sbxc_java;

import com.lx.gs.math.config.entity.tableGameConfig.TableGameConfigSlot;
import com.lx.gs.math.config.resources.tableGameConfig.core.AbstractTableGameResourceSlot;
import com.lx.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import com.lx.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfig;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfigCluster;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.BaseScreenReSpinScreenBox;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.OddsBox;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.config.FiniteAwardPoolConfig;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.config.FiniteAwardPoolConfigExtendBaseReSpin;
import com.lx.gs.math.core.common.spinResultPgr.ConstPgrSlot;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.clientReelResult.ClientReelResult;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.clientReelResult.ClientReelStopResultExtendStopByDependentReelIndex;
import com.lx.gs.math.core.slot.ConstMathSlot;
import com.lx.gs.math.core.slot.animationCtr.enity.config.AnimationConfig;
import com.lx.gs.math.core.slot.animationCtr.enity.config.AnimationConfigExtendOddsAnimation;
import com.lx.gs.math.core.slot.commonInputHlr.enity.config.CommonInputConfig;
import com.lx.gs.math.core.slot.commonInputHlr.enity.config.CommonInputConfigExtendNone;
import com.lx.gs.math.core.slot.gameStateInputHlrMgr.enity.config.GameStateInputConfig;
import com.lx.gs.math.core.slot.gameStateInputHlrMgr.enity.config.GameStateInputConfigExtendNone;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.betSpinConfig.BetSpinConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.betSpinConfig.BetSpinConfigCluster;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.betSpinConfig.BetSpinConfigExtendNoneNormal;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.dampConfig.DampConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfigExtendFix;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.gameConfig.GameConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.gameConfig.GameHitConfigExtendWayGame;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.gameFlowConfig.GameFlowConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.initialScreenConfig.InitialScreenConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.initialScreenConfig.InitialScreenConfigExtendNone;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayCombo;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayTableConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.paymentConfig.PaymentConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.paymentConfig.PaymentConfigExtendRatio;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.readyHandConfig.ReadyHandConfigCluster;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfigExtendReelDependent;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelStripBox;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.RoundConfigNormal;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.roundNormalGameConfig.RoundNormalGameConfigExtendSbxcBaseGame;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.specialFeatureConfig.SpecialFeatureConfigCluster;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import com.lx.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfig;
import com.lx.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfigExtendRound;
import com.lx.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResult;
import com.lx.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResultExtendFix;
import com.lx.gs.math.games.sbxc_java.entity.config.GameStateConfigExtendSbxcBaseGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// 三倍小丑老虎機牌桌遊戲設定產生器
public class TableGameResourceSbxcJavaDefault extends AbstractTableGameResourceSlot {

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
                        add(new OddsBox(8, 110337));
                        add(new OddsBox(18, 5464));
                        add(new OddsBox(28, 2500));
                        add(new OddsBox(58, 300));
                        add(new OddsBox(88, 200));
                        add(new OddsBox(888, 30));
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
                        put(3, new ArrayList<>() {
                            {
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{4, 4, 4});
                                }}, 1));
                            }
                        });
                        put(4, new ArrayList<>() {
                            {
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{3, 3, 3});
                                }}, 1));
                            }
                        });
                        put(5, new ArrayList<>() {
                            {
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
                                    add(new int[]{2, 2, 2});
                                }}, 1));
                            }
                        });
                        put(6, new ArrayList<>() {
                            {
                                add(new BaseScreenReSpinScreenBox(new ArrayList<>() {{
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
        GameStateConfig sbxcBaseGameStateConfig = new GameStateConfig(
                ConstMathSlot.GameStateType.SBXC_BASEGAME,
                new GameStateConfigExtendSbxcBaseGame(
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
                        ConstMathSlot.RoundNormalGameType.SBXC_BASEGAME,
                        new RoundNormalGameConfigExtendSbxcBaseGame()
                ),
                ConstMathSlot.SlotDetailType.SBXC_BASEGAME);


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
                                sbxcBaseGameStateConfig
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
