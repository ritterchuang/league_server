package com.lx.gs.math.config.resources.tableGameConfig.games.xjtb_java;

import com.lx.gs.math.config.entity.tableGameConfig.TableGameConfigSlot;
import com.lx.gs.math.config.resources.tableGameConfig.core.AbstractTableGameResourceSlot;
import com.lx.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import com.lx.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfig;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfigCluster;
import com.lx.gs.math.core.common.spinResultPgr.ConstPgrSlot;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.StopBox;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.clientReelResult.ClientReelResult;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.clientReelResult.ClientReelStopResultExtendStopByDependentReelBox;
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
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.readyHandConfig.ReadyHandConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.readyHandConfig.ReadyHandConfigCluster;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfigExtendReelDependent;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelStripBox;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.RoundConfigNormal;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.roundNormalGameConfig.RoundNormalGameConfigExtendXjtbBaseGame;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.roundNormalGameConfig.RoundNormalGameConfigExtendXjtbFreeGame;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.specialFeatureConfig.SpecialFeatureConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.specialFeatureConfig.SpecialFeatureConfigCluster;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import com.lx.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfig;
import com.lx.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfigExtendRound;
import com.lx.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfigExtendTriggerRound;
import com.lx.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResult;
import com.lx.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResultExtendFix;
import com.lx.gs.math.games.xjtb_java.entity.config.GameStateConfigExtendXjtbBaseGame;
import com.lx.gs.math.games.xjtb_java.entity.config.GameStateConfigExtendXjtbFreeGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// 仙境探寶老虎機牌桌遊戲設定產生器
public class TableGameResourceXjtbJavaDefault extends AbstractTableGameResourceSlot {

    public TableGameConfigSlot create() {
        // 1. 計算遊戲調控設定
        GameAdjustConfig gameAdjustConfig = super.createGameAdjustConfig();

        // 2. 計算亂數產生器演算法設定
        RngAlgorithmConfig rngAlgorithmConfig = super.createRngAlgorithmConfig();

        // 3. 設定押注類型列表
        List<ConstMathSlot.BetType> betTypeList = new ArrayList<>(){
            {add(ConstMathSlot.BetType.NONE);}
        };

        // 4. 設定玩法類型列表
        List<ConstMathSlot.SpinType> SpinTypeList = new ArrayList<>(){
            {add(ConstMathSlot.SpinType.NORMAL);}
        };

        // 5. 設定投注玩法集合
        BetSpinConfigCluster betSpinConfigCluster = new BetSpinConfigCluster(
                // 押注列表
                new ArrayList<>(){
                    {
                        add(1.0);
                        add(2.0);
                        add(5.0);
                        add(10.0);
                        add(20.0);
                        add(30.0);
                        add(50.0);
                        add(100.0);
                        add(200.0);
                        add(300.0);
                        add(600.0);
                        add(1000.0);
                    }
                },
                ConstMathSlot.BetType.NONE,
                ConstMathSlot.SpinType.NORMAL,
                new HashMap<>(){
                    {
                        put(ConstMathSlot.BetType.NONE, new HashMap<>(){
                            {
                                put(ConstMathSlot.SpinType.NORMAL, new BetSpinConfig(
                                        ConstMathSlot.BetSpinType.NONE_NORMAL,
                                        new BetSpinConfigExtendNoneNormal(),
                                        new PaymentConfig(ConstMathSlot.PaymentType.RATIO, new PaymentConfigExtendRatio(50.0, 50.0))));
                            }
                        });
                    }
                }
        );

        // 6. 邏輯類型設定
        ConstMathSlot.LogicType logicType = ConstMathSlot.LogicType.NORMAL;

        // 7. 設定遊戲狀態設定
        GameStateConfig xjtbBaseGameStateConfig = new GameStateConfig(
                ConstMathSlot.GameStateType.XJTB_BASEGAME, new GameStateConfigExtendXjtbBaseGame(), new GameStateInputConfig(ConstMathSlot.GameStateInputType.NONE, new GameStateInputConfigExtendNone()), ConstMathSlot.RoundType.NORMAL, new RoundConfigNormal(
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
                                                        add(List.of(2, 2, 2, 9, 10, 2, 2, 9, 10, 4, 7, 9, 1, 10, 7, 9, 1, 10, 7, 4, 9, 10, 1, 7, 9, 8, 4, 7, 10, 8, 5, 7, 9, 10, 3, 7, 9, 10, 1, 7, 9, 10, 4, 6, 9, 10, 3, 6, 8, 9, 5, 6, 7, 9, 3, 6, 10, 7, 5, 9, 10, 3, 7, 9, 4, 8, 10, 5, 7, 8, 10, 2, 9, 7, 10, 4, 6, 9, 10, 2, 7, 10, 9, 5, 9, 7, 4, 10, 7, 4, 9, 7, 2, 9, 10, 7, 2, 10, 7, 2, 7, 10, 8, 4, 9, 7, 4, 8, 7, 10, 4, 9, 7, 4, 10, 9, 4, 7, 10, 9, 4, 7, 10));
                                                        add(List.of(2, 2, 2, 6, 7, 0, 8, 4, 9, 10, 5, 6, 8, 3, 10, 6, 4, 8, 10, 5, 9, 8, 3, 6, 8, 3, 10, 9, 5, 8, 10, 3, 9, 8, 4, 10, 9, 5, 6, 1, 10, 9, 3, 6, 9, 5, 8, 9, 1, 10, 9, 4, 6, 10, 5, 8, 9, 4, 10, 9, 3, 8, 6, 4, 8, 9, 1, 7, 3, 8, 9, 4, 10, 8, 5, 9, 8, 3, 10, 8, 4, 9, 3, 10, 8, 5, 9, 10, 4, 10, 8, 3, 1, 9, 5, 8, 6, 3, 10, 9, 2, 2, 8, 3, 10, 9, 5, 8, 10, 3, 9, 7, 5, 6, 9, 5, 8, 9, 5, 7, 8, 3, 8, 10, 5, 8, 9, 3, 10, 7, 5, 8, 9, 4, 6, 9));
                                                        add(List.of(6, 7, 8, 2, 2, 2, 6, 8, 2, 2, 6, 9, 10, 5, 8, 6, 7, 1, 8, 6, 7, 1, 8, 7, 3, 6, 8, 5, 10, 6, 4, 7, 10, 1, 4, 6, 8, 9, 0, 6, 7, 10, 1, 6, 9, 5, 8, 6, 4, 10, 6, 9, 5, 7, 8, 4, 6, 7, 3, 8, 9, 5, 7, 6, 3, 10, 6, 8, 5, 7, 6, 5, 8, 7, 3, 6, 8, 5, 7, 6, 4, 8, 7, 5, 6, 8, 3, 7, 6, 5, 8, 7, 4, 3, 6, 8, 5, 7, 6, 3, 8, 7, 5, 6, 8, 3, 7, 6, 5, 7, 6, 9, 3, 7, 9, 5, 10, 8, 3, 7, 9, 5));
                                                        add(List.of(6, 7, 8, 2, 2, 2, 6, 9, 2, 2, 10, 9, 6, 0, 10, 8, 9, 5, 8, 6, 1, 7, 9, 8, 3, 6, 7, 10, 1, 6, 8, 4, 10, 7, 5, 8, 6, 3, 7, 8, 5, 6, 7, 4, 8, 6, 3, 9, 8, 4, 6, 7, 3, 8, 6, 4, 7, 8, 3, 6, 10, 4, 7, 6, 3, 8, 6, 4, 7, 8, 5, 6, 7, 4, 8, 6, 3, 7, 8, 5, 6, 7, 4, 8, 6, 3, 7, 8, 5, 6, 7, 4, 8, 6, 5, 7, 6, 4));
                                                        add(List.of(2, 2, 2, 6, 9, 2, 2, 8, 9, 10, 1, 6, 7, 3, 9, 6, 5, 7, 4, 6, 9, 5, 7, 10, 4, 7, 9, 3, 7, 10, 5, 7, 10, 1, 8, 9, 3, 1, 9, 5, 7, 3, 9, 7, 4, 10, 9, 3, 7, 8, 4, 10, 7, 3, 8, 10, 5, 8, 10, 4, 9, 10, 3, 9, 10, 5, 10, 9, 3, 9, 10, 5, 6, 9, 10, 3, 10, 9, 5, 10, 9, 3, 10, 9, 6));
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
                                                        add(List.of(2, 2, 2, 6, 7, 2, 2, 8, 7, 2, 2, 2, 8, 9, 1, 7, 8, 3, 1, 9, 8, 5, 1, 10, 9, 4, 1, 6, 9, 4, 10, 6, 5, 9, 10, 3, 8, 7, 4, 9, 8, 3, 7, 10, 4, 9, 8, 5, 10, 6, 4, 9, 8, 3, 6, 10, 5, 9, 8, 3, 10, 9, 5, 8, 10, 9));
                                                        add(List.of(2, 2, 2, 6, 7, 2, 2, 6, 7, 3, 8, 6, 1, 7, 8, 5, 6, 9, 1, 8, 6, 5, 7, 8, 1, 9, 10, 4, 8, 7, 1, 9, 8, 3, 9, 10, 5, 8, 9, 4, 7, 8, 5, 10, 7, 3, 8, 10, 5, 7, 9, 3, 8, 10, 5, 9, 10, 3, 7, 8, 9));
                                                        add(List.of(2, 2, 2, 6, 7, 2, 2, 6, 8, 0, 10, 6, 4, 8, 9, 3, 10, 9, 8, 4, 10, 9, 8, 5, 10, 9, 7, 4, 10, 8, 7, 3, 9, 10, 7, 5, 9, 10, 8, 4, 9, 10, 8, 3, 9, 10, 8, 3, 9, 10, 8, 3, 9, 8, 4, 10, 7));
                                                        add(List.of(2, 2, 2, 6, 7, 2, 2, 6, 8, 9, 4, 7, 9, 5, 6, 7, 4, 6, 7, 3, 6, 7, 4, 6, 7, 3, 6, 10, 4, 6, 7, 5, 10, 7, 3, 6, 10, 5, 6, 7, 10));
                                                        add(List.of(2, 2, 2, 6, 7, 2, 2, 6, 7, 10, 3, 9, 10, 4, 9, 7, 5, 9, 10, 4, 9, 8, 5, 9, 10, 8, 4, 9, 10, 8, 5, 7, 9, 4, 8, 10, 3, 6, 8, 5, 7, 10, 3, 6, 8, 9, 3, 6, 8, 9, 5, 7, 10, 8, 3, 7, 8, 4, 10, 9, 7, 3, 6, 8, 9));
                                                    }
                                                }),
                                        ConstMathSlot.ReelRtpType.LOW,
                                        ConstMathSlot.ReelStopType.STOP_BY_DEPENDENT_REEL_INDEX));
                            }
                        }),
                new InitialScreenConfig(
                        new ClientReelResult(0, ConstPgrSlot.ClientReelStopType.STOP_BY_DEPENDENT_REEL_BOX, new ClientReelStopResultExtendStopByDependentReelBox(new ArrayList<>() {
                            {
                                add(new StopBox(4, 1));
                                add(new StopBox(17, 1));
                                add(new StopBox(42, 1));
                                add(new StopBox(41, 1));
                                add(new StopBox(14, 1));
                            }
                        })),
                        new FrameResult(ConstMathSlot.FrameType.FIX, new FrameResultExtendFix(new ArrayList<>() {
                            {
                                add(4);
                                add(4);
                                add(4);
                                add(4);
                                add(4);
                            }
                        })),
                        ConstMathSlot.InitialScreenType.NONE, new InitialScreenConfigExtendNone()),
                new FrameConfig(
                ConstMathSlot.FrameType.FIX,
                new FrameConfigExtendFix(List.of(4, 4, 4, 4, 4))),
                new SymbolConfig(new ArrayList<>() {
                    {
                        add(ConstMathSlot.SymbolAttribute.WILD_01);
                        add(ConstMathSlot.SymbolAttribute.FREE_GAME_01);
                        add(ConstMathSlot.SymbolAttribute.M1);
                        add(ConstMathSlot.SymbolAttribute.M2);
                        add(ConstMathSlot.SymbolAttribute.M3);
                        add(ConstMathSlot.SymbolAttribute.M4);
                        add(ConstMathSlot.SymbolAttribute.ACE);
                        add(ConstMathSlot.SymbolAttribute.KING);
                        add(ConstMathSlot.SymbolAttribute.QUEEN);
                        add(ConstMathSlot.SymbolAttribute.JOKER);
                        add(ConstMathSlot.SymbolAttribute.TEN);
                    }
                },
                        new ArrayList<>() {
                            {
                                add(ConstMathSlot.SymbolAttributeType.WILD_SYMBOL);
                                add(ConstMathSlot.SymbolAttributeType.FREE_GAME_SYMBOL);
                                add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                                add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                                add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                                add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                                add(ConstMathSlot.SymbolAttributeType.BASE_SYMBOL);
                                add(ConstMathSlot.SymbolAttributeType.BASE_SYMBOL);
                                add(ConstMathSlot.SymbolAttributeType.BASE_SYMBOL);
                                add(ConstMathSlot.SymbolAttributeType.BASE_SYMBOL);
                                add(ConstMathSlot.SymbolAttributeType.BASE_SYMBOL);
                            }
                        }),
                new DampConfig(ConstMathSlot.DampType.ONE_DAMP, ConstMathSlot.DampType.ONE_DAMP),
                new GameConfig(ConstMathSlot.GameHitType.WAY_GAME, new GameHitConfigExtendWayGame(ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT)),
                new PayTableConfig(new ArrayList<>() {
                    {
                        add(new PayCombo(
                                new ArrayList<>() {
                                    {
                                        add(2);
                                    }
                                },
                                new ArrayList<>() {
                                    {
                                        add(0);
                                        add(10);
                                        add(50);
                                        add(100);
                                        add(300);
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
                                        add(50);
                                        add(100);
                                        add(150);
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
                                        add(50);
                                        add(100);
                                        add(150);
                                    }
                                }
                        ));
                        add(new PayCombo(
                                new ArrayList<>() {
                                    {
                                        add(5);
                                    }
                                },
                                new ArrayList<>() {
                                    {
                                        add(0);
                                        add(0);
                                        add(20);
                                        add(80);
                                        add(120);
                                    }
                                }
                        ));
                        add(new PayCombo(
                                new ArrayList<>() {
                                    {
                                        add(6);
                                    }
                                },
                                new ArrayList<>() {
                                    {
                                        add(0);
                                        add(0);
                                        add(10);
                                        add(50);
                                        add(100);
                                    }
                                }
                        ));
                        add(new PayCombo(
                                new ArrayList<>() {
                                    {
                                        add(7);
                                    }
                                },
                                new ArrayList<>() {
                                    {
                                        add(0);
                                        add(0);
                                        add(10);
                                        add(50);
                                        add(100);
                                    }
                                }
                        ));
                        add(new PayCombo(
                                new ArrayList<>() {
                                    {
                                        add(8);
                                    }
                                },
                                new ArrayList<>() {
                                    {
                                        add(0);
                                        add(0);
                                        add(5);
                                        add(20);
                                        add(100);
                                    }
                                }
                        ));
                        add(new PayCombo(
                                new ArrayList<>() {
                                    {
                                        add(9);
                                    }
                                },
                                new ArrayList<>() {
                                    {
                                        add(0);
                                        add(0);
                                        add(5);
                                        add(20);
                                        add(100);
                                    }
                                }
                        ));
                        add(new PayCombo(
                                new ArrayList<>() {
                                    {
                                        add(10);
                                    }
                                },
                                new ArrayList<>() {
                                    {
                                        add(0);
                                        add(2);
                                        add(5);
                                        add(10);
                                        add(100);
                                    }
                                }
                        ));

                    }
                }), new ProgressConfig(ConstMathSlot.ProgressType.ROUND, new ProgressConfigExtendRound(1, 0, 1)), new SpecialFeatureConfigCluster(new ArrayList<>() {
                    {
                        add(new SpecialFeatureConfig(ConstMathSlot.SpecialFeatureType.SF_02, ConstMathSlot.TriggerEvent.TRIGGER_01, 2));
                        add(new SpecialFeatureConfig(ConstMathSlot.SpecialFeatureType.SF_03, ConstMathSlot.TriggerEvent.TRIGGER_02, 10));
                        add(new SpecialFeatureConfig(ConstMathSlot.SpecialFeatureType.SF_04, ConstMathSlot.TriggerEvent.TRIGGER_03, 20));
                    }
                }), new ReadyHandConfigCluster(List.of(
                        new ReadyHandConfig(ConstMathSlot.ReadyHandType.READY_HAND_01, ConstMathSlot.ReadyHandLimitType.NO_LIMIT)
                )), ConstMathSlot.RoundNormalGameType.XJTB_BASEGAME,
                new RoundNormalGameConfigExtendXjtbBaseGame()

        ), ConstMathSlot.SlotDetailType.XJTB_BASEGAME
        );
        GameStateConfig xjtbFreeGameStateConfig = new GameStateConfig(
                ConstMathSlot.GameStateType.XJTB_FREEGAME, new GameStateConfigExtendXjtbFreeGame(), new GameStateInputConfig(ConstMathSlot.GameStateInputType.NONE, new GameStateInputConfigExtendNone()), ConstMathSlot.RoundType.NORMAL, new RoundConfigNormal(
                        new ReelConfig(
                                ConstMathSlot.ReelType.REEL_DEPENDENT,
                                new ArrayList<>(){
                                    {
                                        add(new ReelStripBox(
                                                0,
                                                1,
                                                new ReelConfigExtendReelDependent(
                                                        new ArrayList<>(){
                                                            {
                                                                add(List.of(2,2,2,9,10,2,2,9,10,4,7,9,1,10,7,9,2,10,7,4,9,10,5,7,9,4,7,10,5,9,7,3,10,9,1,7,10,4,6,9,10,3,6,8,9,5,7,9,4,6,10,5,9,10,3,7,9,4,8,10,5,7,8,2,6,7,4,6,7,2,8,7,2,2,8,10,4,9,6,3,8,7,4,10,7,4,8,10));
                                                                add(List.of(2,2,2,6,7,8,4,9,10,5,6,8,3,10,6,4,8,10,5,9,8,3,6,8,3,10,9,5,8,10,3,9,8,4,10,9,5,6,1,10,9,3,6,9,4,10,6,1,9,10,4,6,9,3,10,7,4,9,10,3,8,10,4,8,6,3,8,9,0,4,10,8,5,9,2,2,8,3,10,8,4,9,3,10,0,8,3,9,10,4,10,5,7,2,2,2,8,9,5,8,6,3,10,9,2,2,8,3,10,9,5,8,10,3,9,7,5,0,6,9,3,8,10,5,7,8));
                                                                add(List.of(6,7,8,2,2,2,6,8,2,2,6,9,2,2,2,8,10,5,6,7,1,8,6,7,1,8,7,3,6,8,5,10,2,7,2,4,10,8,4,7,9,2,2,9,5,6,0,8,4,9,7,0,5,7,8,4,9,7,3,8,9,5,7,6,3,9,8,5,10,8,5,7,6,3,10,8,5,7,6,4,8,7,5,6,8,3,7,6,5,8,7,4,3,6,8,5,7,6,3,8,7,5,6,8,3,7,6,5,7,6,9,3,7,9,5,10,8,3,0,7,9,5));
                                                                add(List.of(6,7,8,2,2,2,6,9,2,2,10,9,2,2,2,8,9,5,8,6,1,7,9,3,8,6,0,7,10,1,6,8,4,10,7,5,8,6,3,7,4,9,8,5,6,7,4,8,6,3,9,8,4,6,7,3,9,6,4,7,9,3,6,10,4,7,6,3,8,6,4,7,0,8,5,9,7,4,8,6,3,7,8,5,6,7,4,9,5,3,7,9,2,2,6,0,9,7,4,9,6,2,2,2));
                                                                add(List.of(2,2,2,9,6,2,2,8,7,10,2,2,6,7,3,9,6,5,7,4,6,9,5,7,6,4,7,9,3,7,10,5,7,10,1,8,9,3,1,9,5,7,10,3,9,7,4,10,9,3,7,8,4,10,6,3,8,10,5,7,8,10,4,9,8,3,9,10,2,7,6,9,3,7,9,10,2,7,8,3,10,9,2,7,8,3,10,9));
                                                            }
                                                        }),
                                                ConstMathSlot.ReelRtpType.HIGH,
                                                ConstMathSlot.ReelStopType.STOP_BY_DEPENDENT_REEL_INDEX));
                                        add(new ReelStripBox(
                                                1,
                                                1,
                                                new ReelConfigExtendReelDependent(
                                                        new ArrayList<>(){
                                                            {
                                                                add(List.of(2,2,2,9,10,2,2,9,10,4,7,9,1,10,7,9,2,10,7,4,9,10,5,7,9,4,7,10,5,9,7,3,10,9,1,7,10,4,6,9,10,3,6,8,9,5,7,9,4,6,10,5,9,10,3,7,9,4,8,10,5,7,8,2,6,7,4,6,7,2,8,7,2,2,8,10,4,9,6,3,8,7,4,10,7,4,8,10));
                                                                add(List.of(2,2,2,6,7,8,4,9,10,5,6,8,3,10,6,4,8,10,5,9,8,3,6,8,3,10,9,5,8,10,3,9,8,4,10,9,5,6,1,10,9,3,6,9,4,10,6,1,9,10,4,6,9,3,10,7,4,9,10,3,8,10,4,8,6,3,8,9,0,4,10,8,5,9,2,2,8,3,10,8,4,9,3,10,0,8,3,9,10,4,10,5,7,2,2,2,8,9,5,8,6,3,10,9,2,2,8,3,10,9,5,8,10,3,9,7,5,0,6,9,3,8,10,5,7,8));
                                                                add(List.of(6,7,8,2,2,2,6,8,2,2,6,9,2,2,2,8,10,5,6,7,1,8,6,7,1,8,7,3,6,8,5,10,2,7,2,4,10,8,4,7,9,2,2,9,5,6,0,8,4,9,7,0,5,7,8,4,9,7,3,8,9,5,7,6,3,9,8,5,10,8,5,7,6,3,10,8,5,7,6,4,8,7,5,6,8,3,7,6,5,8,7,4,3,6,8,5,7,6,3,8,7,5,6,8,3,7,6,5,7,6,9,3,7,9,5,10,8,3,0,7,9,5));
                                                                add(List.of(6,7,8,2,2,2,6,9,2,2,10,9,2,2,2,8,9,5,8,6,1,7,9,3,8,6,0,7,10,1,6,8,4,10,7,5,8,6,3,7,4,9,8,5,6,7,4,8,6,3,9,8,4,6,7,3,9,6,4,7,9,3,6,10,4,7,6,3,8,6,4,7,0,8,5,9,7,4,8,6,3,7,8,5,6,7,4,9,5,3,7,9,2,2,6,0,9,7,4,9,6,2,2,2));
                                                                add(List.of(2,2,2,9,6,2,2,8,7,10,2,2,6,7,3,9,6,5,7,4,6,9,5,7,6,4,7,9,3,7,10,5,7,10,1,8,9,3,1,9,5,7,10,3,9,7,4,10,9,3,7,8,4,10,6,3,8,10,5,7,8,10,4,9,8,3,9,10,2,7,6,9,3,7,9,10,2,7,8,3,10,9,2,7,8,3,10,9));
                                                            }
                                                        }),
                                                ConstMathSlot.ReelRtpType.LOW,
                                                ConstMathSlot.ReelStopType.STOP_BY_DEPENDENT_REEL_INDEX));
                                    }
                                }),
                new InitialScreenConfig(
                        new ClientReelResult(0, ConstPgrSlot.ClientReelStopType.STOP_BY_DEPENDENT_REEL_BOX, new ClientReelStopResultExtendStopByDependentReelBox(new ArrayList<>(){
                            {
                                add(new StopBox(4, 1));
                                add(new StopBox(28, 1));
                                add(new StopBox(28, 1));
                                add(new StopBox(30, 1));
                                add(new StopBox(47, 1));
                            }
                        })),
                        new FrameResult(ConstMathSlot.FrameType.FIX,
                                                new FrameResultExtendFix(new ArrayList<>(){
                                                    {
                                                        add(4);
                                                        add(4);
                                                        add(4);
                                                        add(4);
                                                        add(4);
                                                    }
                                                })),
                        ConstMathSlot.InitialScreenType.NONE, new InitialScreenConfigExtendNone()),
                new FrameConfig(
                                ConstMathSlot.FrameType.FIX,
                                new FrameConfigExtendFix(List.of(4, 4, 4, 4, 4))),
                new SymbolConfig(new ArrayList<>(){
                    {
                        add(ConstMathSlot.SymbolAttribute.WILD_01);
                        add(ConstMathSlot.SymbolAttribute.FREE_GAME_01);
                        add(ConstMathSlot.SymbolAttribute.M1);
                        add(ConstMathSlot.SymbolAttribute.M2);
                        add(ConstMathSlot.SymbolAttribute.M3);
                        add(ConstMathSlot.SymbolAttribute.M4);
                        add(ConstMathSlot.SymbolAttribute.ACE);
                        add(ConstMathSlot.SymbolAttribute.KING);
                        add(ConstMathSlot.SymbolAttribute.QUEEN);
                        add(ConstMathSlot.SymbolAttribute.JOKER);
                        add(ConstMathSlot.SymbolAttribute.TEN);
                    }
                },
                        new ArrayList<>(){
                            {
                                add(ConstMathSlot.SymbolAttributeType.WILD_SYMBOL);
                                add(ConstMathSlot.SymbolAttributeType.FREE_GAME_SYMBOL);
                                add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                                add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                                add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                                add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                                add(ConstMathSlot.SymbolAttributeType.BASE_SYMBOL);
                                add(ConstMathSlot.SymbolAttributeType.BASE_SYMBOL);
                                add(ConstMathSlot.SymbolAttributeType.BASE_SYMBOL);
                                add(ConstMathSlot.SymbolAttributeType.BASE_SYMBOL);
                                add(ConstMathSlot.SymbolAttributeType.BASE_SYMBOL);
                            }
                        }),
                new DampConfig(ConstMathSlot.DampType.ONE_DAMP, ConstMathSlot.DampType.ONE_DAMP),
                new GameConfig(ConstMathSlot.GameHitType.WAY_GAME, new GameHitConfigExtendWayGame(ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT)),
                new PayTableConfig(new ArrayList<>(){
                    {
                        add(new PayCombo(
                                new ArrayList<>(){
                                    {
                                        add(2);
                                    }
                                },
                                new ArrayList<>(){
                                    {
                                        add(0);
                                        add(10);
                                        add(50);
                                        add(100);
                                        add(300);
                                    }
                                }
                        ));
                        add(new PayCombo(
                                new ArrayList<>(){
                                    {
                                        add(3);
                                    }
                                },
                                new ArrayList<>(){
                                    {
                                        add(0);
                                        add(0);
                                        add(50);
                                        add(100);
                                        add(150);
                                    }
                                }
                        ));
                        add(new PayCombo(
                                new ArrayList<>(){
                                    {
                                        add(4);
                                    }
                                },
                                new ArrayList<>(){
                                    {
                                        add(0);
                                        add(0);
                                        add(50);
                                        add(100);
                                        add(150);
                                    }
                                }
                        ));
                        add(new PayCombo(
                                new ArrayList<>(){
                                    {
                                        add(5);
                                    }
                                },
                                new ArrayList<>(){
                                    {
                                        add(0);
                                        add(0);
                                        add(20);
                                        add(80);
                                        add(120);
                                    }
                                }
                        ));
                        add(new PayCombo(
                                new ArrayList<>(){
                                    {
                                        add(6);
                                    }
                                },
                                new ArrayList<>(){
                                    {
                                        add(0);
                                        add(0);
                                        add(10);
                                        add(50);
                                        add(100);
                                    }
                                }
                        ));
                        add(new PayCombo(
                                new ArrayList<>(){
                                    {
                                        add(7);
                                    }
                                },
                                new ArrayList<>(){
                                    {
                                        add(0);
                                        add(0);
                                        add(10);
                                        add(50);
                                        add(100);
                                    }
                                }
                        ));
                        add(new PayCombo(
                                new ArrayList<>(){
                                    {
                                        add(8);
                                    }
                                },
                                new ArrayList<>(){
                                    {
                                        add(0);
                                        add(0);
                                        add(5);
                                        add(20);
                                        add(100);
                                    }
                                }
                        ));
                        add(new PayCombo(
                                new ArrayList<>(){
                                    {
                                        add(9);
                                    }
                                },
                                new ArrayList<>(){
                                    {
                                        add(0);
                                        add(0);
                                        add(5);
                                        add(20);
                                        add(100);
                                    }
                                }
                        ));
                        add(new PayCombo(
                                new ArrayList<>(){
                                    {
                                        add(10);
                                    }
                                },
                                new ArrayList<>(){
                                    {
                                        add(0);
                                        add(2);
                                        add(5);
                                        add(10);
                                        add(100);
                                    }
                                }
                        ));

                    }
                }), new ProgressConfig(ConstMathSlot.ProgressType.TRIGGER_ROUND,
                        new ProgressConfigExtendTriggerRound(
                                new HashMap<>(){
                                    {
                                        put(ConstMathSlot.TriggerEvent.TRIGGER_01, 8);
                                        put(ConstMathSlot.TriggerEvent.TRIGGER_02, 15);
                                        put(ConstMathSlot.TriggerEvent.TRIGGER_03, 20);
                                    }
                                },
                                new HashMap<>(){
                                    {
                                        put(ConstMathSlot.TriggerEvent.RE_TRIGGER_01, 5);
                                        put(ConstMathSlot.TriggerEvent.RE_TRIGGER_02, 8);
                                        put(ConstMathSlot.TriggerEvent.RE_TRIGGER_03, 15);
                                        put(ConstMathSlot.TriggerEvent.RE_TRIGGER_04, 20);
                                    }
                                },
                                99
                        )), new SpecialFeatureConfigCluster(new ArrayList<>(){
                            {
                                add(new SpecialFeatureConfig(ConstMathSlot.SpecialFeatureType.SF_01, ConstMathSlot.TriggerEvent.RE_TRIGGER_01, 0));
                                add(new SpecialFeatureConfig(ConstMathSlot.SpecialFeatureType.SF_02, ConstMathSlot.TriggerEvent.RE_TRIGGER_02, 2));
                                add(new SpecialFeatureConfig(ConstMathSlot.SpecialFeatureType.SF_03, ConstMathSlot.TriggerEvent.RE_TRIGGER_03, 10));
                                add(new SpecialFeatureConfig(ConstMathSlot.SpecialFeatureType.SF_04, ConstMathSlot.TriggerEvent.RE_TRIGGER_04, 20));
                            }
                        }), new ReadyHandConfigCluster(List.of(
                                new ReadyHandConfig(ConstMathSlot.ReadyHandType.READY_HAND_02, ConstMathSlot.ReadyHandLimitType.MAX_TRIGGER_LIMIT)
                        )), ConstMathSlot.RoundNormalGameType.XJTB_FREEGAME,
                        new RoundNormalGameConfigExtendXjtbFreeGame(
                                0,
                                List.of(
                                        List.of(0),
                                        List.of(2,3),
                                        List.of(2,3),
                                        List.of(2,3),
                                        List.of(0)
                                ),
                                List.of(
                                        List.of(1),
                                        List.of(50000, 31955),
                                        List.of(12000, 13179),
                                        List.of(700, 2000),
                                        List.of(1)
                                )
                        )

        ), ConstMathSlot.SlotDetailType.XJTB_FREEGAME
        );

        // 8. 設定遊戲狀態設定集合
        GameStateConfigCluster gameStateConfigCluster = new GameStateConfigCluster(
                new HashMap<>(){
                    {
                        put(ConstMathSlot.ReelRtpType.HIGH, 1.1);
                        put(ConstMathSlot.ReelRtpType.LOW, 0.7);
                    }
                }, // 高低表設定
                new CommonInputConfig(ConstMathSlot.CommonInputType.NONE, new CommonInputConfigExtendNone()), // 通用輸入設定
                new HashMap<>(){
                    {put(ConstMathSlot.BetType.NONE, new HashMap<>(){
                        {put(ConstMathSlot.SpinType.NORMAL, 0);}
                    });}
                }, // 押注玩法遊戲識別碼對應表
                new ArrayList<>(){
                    {
                        add(
                                xjtbBaseGameStateConfig
                        );
                        add(
                                xjtbFreeGameStateConfig
                        );
                    }
                } // 遊戲狀態設定列表
        );

        // 9. 設定流程設定
        GameFlowConfig gameFlowConfig = new GameFlowConfig(
                new ArrayList<>(){
                    {
                        add(new ArrayList<>(){
                            {
                                add(ConstMathSlot.Condition.CD_FALSE);
                                add(ConstMathSlot.Condition.CD_TRUE);
                                add(ConstMathSlot.Condition.CD_FALSE);
                            }
                        });
                        add(new ArrayList<>(){
                            {
                                add(ConstMathSlot.Condition.CD_FALSE);
                                add(ConstMathSlot.Condition.CD_FALSE);
                                add(ConstMathSlot.Condition.CD_01);
                            }
                        });
                        add(new ArrayList<>(){
                            {
                                add(ConstMathSlot.Condition.CD_FALSE);
                                add(ConstMathSlot.Condition.CD_FALSE);
                                add(ConstMathSlot.Condition.CD_FALSE);
                            }
                        });
                    }
                }
        );

        // 10. 動畫設定
        AnimationConfig animationConfig = new AnimationConfig(ConstMathSlot.AnimationType.ODDS_ANIMATION, new AnimationConfigExtendOddsAnimation(
                new HashMap<>(){
                    {
                        put(ConstMathSlot.OddsWinType.BIG_WIN, 30);
                        put(ConstMathSlot.OddsWinType.MEGA_WIN, 50);
                        put(ConstMathSlot.OddsWinType.ULTRA_WIN, 100);
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
