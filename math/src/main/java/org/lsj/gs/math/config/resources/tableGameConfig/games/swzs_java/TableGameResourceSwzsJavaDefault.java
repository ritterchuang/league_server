package org.lsj.gs.math.config.resources.tableGameConfig.games.swzs_java;

import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigSlot;
import org.lsj.gs.math.config.resources.tableGameConfig.core.AbstractTableGameResourceSlot;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfig;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfigCluster;
import org.lsj.gs.math.core.common.spinResultPgr.ConstPgrSlot;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.StopBox;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.clientReelResult.ClientReelResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.clientReelResult.ClientReelStopResultExtendStopByDependentReelBox;
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
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.readyHandConfig.ReadyHandConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.readyHandConfig.ReadyHandConfigCluster;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfigExtendReelDependent;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelStripBox;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.RoundConfigNormal;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.roundNormalGameConfig.RoundNormalGameConfigExtendSwzsBaseGame;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.roundNormalGameConfig.RoundNormalGameConfigExtendSwzsFreeGame;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.specialFeatureConfig.SpecialFeatureConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.specialFeatureConfig.SpecialFeatureConfigCluster;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfig;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfigExtendRound;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfigExtendTriggerRound;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResultExtendFix;
import org.lsj.gs.math.games.swzs_java.entity.config.GameStateConfigExtendSwzsBaseGame;
import org.lsj.gs.math.games.swzs_java.entity.config.GameStateConfigExtendSwzsFreeGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// 死亡之書老虎機牌桌遊戲設定產生器
public class TableGameResourceSwzsJavaDefault extends AbstractTableGameResourceSlot {

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
                }, // 押注列表
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
        GameStateConfig swzsBaseGameStateConfig = new GameStateConfig(ConstMathSlot.GameStateType.SWZS_BASEGAME,
                new GameStateConfigExtendSwzsBaseGame(), new GameStateInputConfig(ConstMathSlot.GameStateInputType.NONE, new GameStateInputConfigExtendNone()), ConstMathSlot.RoundType.NORMAL,
                new RoundConfigNormal(
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
                                                                add(List.of(2,2,2,7,7,1,11,11,5,5,8,1,3,3,10,10,6,6,6,7,7,5,5,1,11,11,4,4,4,6,6,10,9,5,5,5,11,10,3,3,11,11,4,4,9,9,5,5,10,10,2,9,9,1,6,6,5,5,6,11,4,7,3,3,1,10,2,9,9,5,8,8,2,9,9,5,8,8,2,9,9,5,10,10,1,9,9,5,10,7));
                                                                add(List.of(3,3,3,9,9,1,8,8,3,9,9,2,2,1,10,4,4,4,5,5,5,0,11,5,5,1,5,7,7,6,6,6,11,11,4,4,10,10,2,6,6,4,10,10,3,5,6,6,4,4,10,10,5,1,11,11,3,6,6,4,1,7,7,4,5,10,4,4,11,11,6,6,8,8,1,6,6,8,8,5,5,10,10,4,4,9,9,6,6,7,7));
                                                                add(List.of(4,4,4,10,10,1,11,11,5,5,8,8,6,6,6,11,11,1,3,3,4,4,4,1,7,3,3,9,9,5,5,5,6,6,3,3,11,11,1,7,7,4,0,11,5,5,8,8,2,2,2,7,7,1,9,10,5,6,8,8,6,10,10,3,11,8,4,11,11,1,6,7,7,4,7,7,1,3,3,9,4,4,10,10));
                                                                add(List.of(5,5,5,8,8,1,11,11,10,1,9,4,4,7,7,3,3,3,9,0,6,6,6,1,11,2,2,8,8,4,4,3,10,10,5,7,7,4,10,10,6,9,9,6,11,11,4,11,11,6,10,10,5,1,6,6,10,9,3,11,8,5,5,11,1,10,10,3,3,8,8,2,2,9,9,1,5,11,9,6,8,8));
                                                                add(List.of(6,6,6,11,11,1,10,10,5,5,5,8,8,4,4,7,7,2,10,10,6,7,7,1,9,9,5,10,10,3,3,3,9,9,4,4,4,11,11,2,2,9,9,6,6,11,11,2,9,9,3,8,8,1,7,7,4,9,8,2,11,11,4,11,8,2,2,11,11,6,9,9,3,7,7,2,2,11,11,6,9,7,5,11,11,6,10,10));
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
                                                                add(List.of(2,2,2,7,7,2,11,11,5,5,8,3,3,3,10,10,6,6,6,7,7,5,5,5,8,8,4,4,4,6,6,11,9,5,5,5,11,10,3,3,11,11,4,4,10,9,5,5,10,10,2,9,9,3,3,3,5,5,4,11,4,7,3,3,4,11,5,11,11,4,4,6));
                                                                add(List.of(3,3,3,9,9,3,8,8,3,9,9,2,2,2,10,4,4,4,5,5,5,0,11,11,5,5,5,7,7,6,6,6,11,11,4,4,10,10,2,6,6,4,10,10,3,5,6,6,4,4,10,10,5,5,11,11,3,6,6,4,0,7,7,4,5,9,3,3,5,11,11));
                                                                add(List.of(4,4,4,10,10,4,11,11,5,5,8,8,6,6,6,11,11,3,3,3,4,4,4,9,7,3,3,9,9,5,5,5,6,6,3,3,11,11,2,7,7,4,0,11,5,5,8,8,2,2,2,7,7,6,9,10,5,6,9,0,6,10,10,3,11,11,4,3,9,5,11,11,2,10,10,2,9,9));
                                                                add(List.of(5,5,5,8,8,5,11,11,2,0,9,4,4,7,7,3,3,3,7,0,6,6,6,7,9,2,2,8,8,4,3,3,10,10,5,7,7,4,10,10,6,7,7,6,11,11,4,11,11,6,10,10,5,5,6,6,9,9,3,11,8,5,5,11,4,10,10,2,8,8,3,11,11,6,10,10));
                                                                add(List.of(6,6,6,11,11,6,10,10,5,5,3,8,8,4,4,7,7,2,10,10,6,7,7,5,9,9,5,10,10,3,3,3,9,9,4,4,4,11,11,2,2,9,9,6,6,11,11,2,9,9,3,8,8,6,7,7,4,9,8,2,11,11,4,11,8));
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
                                        add(new StopBox(4, 1));
                                        add(new StopBox(4, 1));
                                        add(new StopBox(4, 1));
                                        add(new StopBox(4, 1));
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
                                add(ConstMathSlot.SymbolAttribute.M5);
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
                                                add(0);
                                                add(50);
                                                add(100);
                                                add(200);
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
                                                add(25);
                                                add(50);
                                                add(100);
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
                                                add(20);
                                                add(40);
                                                add(80);
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
                                                add(12);
                                                add(25);
                                                add(50);
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
                                                add(8);
                                                add(15);
                                                add(30);
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
                                                add(5);
                                                add(8);
                                                add(16);
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
                                                add(8);
                                                add(16);
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
                                                add(8);
                                                add(16);
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
                                                add(0);
                                                add(5);
                                                add(8);
                                                add(16);
                                            }
                                        }
                                ));
                                add(new PayCombo(
                                        new ArrayList<>(){
                                            {
                                                add(11);
                                            }
                                        },
                                        new ArrayList<>(){
                                            {
                                                add(0);
                                                add(0);
                                                add(5);
                                                add(8);
                                                add(16);
                                            }
                                        }
                                ));
                            }
                        }), new ProgressConfig(ConstMathSlot.ProgressType.ROUND, new ProgressConfigExtendRound(1, 0, 1)), new SpecialFeatureConfigCluster(new ArrayList<>(){
                            {
                                add(new SpecialFeatureConfig(ConstMathSlot.SpecialFeatureType.SF_05, ConstMathSlot.TriggerEvent.TRIGGER_01, 5));
                                add(new SpecialFeatureConfig(ConstMathSlot.SpecialFeatureType.SF_06, ConstMathSlot.TriggerEvent.TRIGGER_02, 10));
                                add(new SpecialFeatureConfig(ConstMathSlot.SpecialFeatureType.SF_07, ConstMathSlot.TriggerEvent.TRIGGER_03, 30));
                            }
                        }), new ReadyHandConfigCluster(List.of(
                                new ReadyHandConfig(ConstMathSlot.ReadyHandType.READY_HAND_04, ConstMathSlot.ReadyHandLimitType.NO_LIMIT),
                                new ReadyHandConfig(ConstMathSlot.ReadyHandType.READY_HAND_05, ConstMathSlot.ReadyHandLimitType.NO_LIMIT),
                                new ReadyHandConfig(ConstMathSlot.ReadyHandType.READY_HAND_06, ConstMathSlot.ReadyHandLimitType.NO_LIMIT)
                        )), ConstMathSlot.RoundNormalGameType.SWZS_BASEGAME, new RoundNormalGameConfigExtendSwzsBaseGame()
                ),
                ConstMathSlot.SlotDetailType.SWZS_BASEGAME
        );

        GameStateConfig swzsFreeGameStateConfig = new GameStateConfig(ConstMathSlot.GameStateType.SWZS_FREEGAME,
                new GameStateConfigExtendSwzsFreeGame(), new GameStateInputConfig(ConstMathSlot.GameStateInputType.NONE, new GameStateInputConfigExtendNone()), ConstMathSlot.RoundType.NORMAL,
                new RoundConfigNormal(
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
                                                                add(List.of(2,2,1,5,5,5,1,3,3,3,5,4,4,6,6,6,1,5,5,5,4,3,5,5,5,6,6,6,5,5,5,6,6,2,5,5,4,4,4,3,6,6,6,5,4,6,6,1,5,5,5,6,6,6,5,5,5,6,6,6,2,5,5,5,3,6,6,5,5,5,6,6,6,2,2,2,5,5,5,3,6));
                                                                add(List.of(3,3,1,2,2,2,1,4,4,4,6,6,1,4,2,6,1,2,5,5,5,0,6,6,6,2,2,5,4,4,5,5,5,4,4,6,5,5,5,6,5,5,5,6,5,5,5,1,6,6,6,4,5,3,3,6,6,5,5,6,6,5,5,5,3,3,3,5,5,5,6,6,6,5,5,4,4,4,6,6,6,5,5,4,6,6,6,1,5,5,6,6,6,5,5,5));
                                                                add(List.of(4,4,1,2,2,2,1,5,5,5,6,6,6,4,4,1,6,6,6,5,5,0,4,4,4,3,3,3,4,4,3,3,3,4,4,6,3,3,3,2,3,3,3,2,3,3,3,1,4,4,4,1,3,3,3,4,4,4,3,3,3,4,4,4,3,3,3,2,2,3,3,3,4,4,4,3,3,3,4,4,2,2,5,3,3,3,4,4,4,6,6,6,4,4,4,6,6,6));
                                                                add(List.of(5,5,1,4,4,4,1,6,6,6,3,3,3,4,4,3,3,1,6,5,5,0,3,3,3,4,4,4,3,3,4,4,4,2,2,1,3,3,3,6,4,4,4,6,3,3,3,1,4,4,4,1,3,3,3,4,4,4,3,3,3,4,4,4,3,3,3,4,4,3,3,3,6,6,2,2,3,3,4,4,4,2,3,3));
                                                                add(List.of(6,6,1,2,2,2,1,3,3,3,2,5,5,5,4,4,4,5,5,5,6,6,2,2,2,5,4,5,2,2,2,6,6,5,5,6,2,2,2,5,2,2,2,5,5,2,2,1,5,4,6,1,2,2,2,5,5,5,6,6,6,2,2,2,5,4,2,5,3,6,2,2,5,5,5,2,2,2,5,6,2));
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
                                                                add(List.of(2,2,1,5,5,5,1,3,3,3,5,4,4,6,6,6,1,5,5,5,4,3,5,5,5,6,6,6,5,5,5,6,6,2,5,5,4,4,4,3,6,6,6,5,4,6,6,1,5,5,5,6,6,6,5,5,5,6,6,6,2,5,5,5,3,6,6,5,5,5,6,6,6,2,2,2,5,5,5,3,6));
                                                                add(List.of(3,3,1,2,2,2,1,4,4,4,6,6,1,4,2,6,1,2,5,5,5,0,6,6,6,2,2,5,4,4,5,5,5,4,4,6,5,5,5,6,5,5,5,6,5,5,5,1,6,6,6,4,5,3,3,6,6,5,5,6,6,5,5,5,3,3,3,5,5,5,6,6,6,5,5,4,4,4,6,6,6,5,5,4,6,6,6,1,5,5,6,6,6,5,5,5));
                                                                add(List.of(4,4,1,2,2,2,1,5,5,5,6,6,6,4,4,1,6,6,6,5,5,0,4,4,4,3,3,3,4,4,3,3,3,4,4,6,3,3,3,2,3,3,3,2,3,3,3,1,4,4,4,1,3,3,3,4,4,4,3,3,3,4,4,4,3,3,3,2,2,3,3,3,4,4,4,3,3,3,4,4,2,2,5,3,3,3,4,4,4,6,6,6,4,4,4,6,6,6));
                                                                add(List.of(5,5,1,4,4,4,1,6,6,6,3,3,3,4,4,3,3,1,6,5,5,0,3,3,3,4,4,4,3,3,4,4,4,2,2,1,3,3,3,6,4,4,4,6,3,3,3,1,4,4,4,1,3,3,3,4,4,4,3,3,3,4,4,4,3,3,3,4,4,3,3,3,6,6,2,2,3,3,4,4,4,2,3,3));
                                                                add(List.of(6,6,1,2,2,2,1,3,3,3,2,5,5,5,4,4,4,5,5,5,6,6,2,2,2,5,4,5,2,2,2,6,6,5,5,6,2,2,2,5,2,2,2,5,5,2,2,1,5,4,6,1,2,2,2,5,5,5,6,6,6,2,2,2,5,4,2,5,3,6,2,2,5,5,5,2,2,2,5,6,2));
                                                            }
                                                        }),
                                                ConstMathSlot.ReelRtpType.LOW,
                                                ConstMathSlot.ReelStopType.STOP_BY_DEPENDENT_REEL_INDEX));
                                    }
                                }),
                        new InitialScreenConfig(
                                new ClientReelResult(0, ConstPgrSlot.ClientReelStopType.STOP_BY_DEPENDENT_REEL_BOX, new ClientReelStopResultExtendStopByDependentReelBox(new ArrayList<>() {
                                    {
                                        add(new StopBox(7, 1));
                                        add(new StopBox(7, 1));
                                        add(new StopBox(11, 1));
                                        add(new StopBox(6, 1));
                                        add(new StopBox(14, 1));
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
                                add(ConstMathSlot.SymbolAttribute.M5);
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
                                                add(0);
                                                add(50);
                                                add(100);
                                                add(200);
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
                                                add(25);
                                                add(50);
                                                add(100);
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
                                                add(20);
                                                add(40);
                                                add(80);
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
                                                add(12);
                                                add(25);
                                                add(50);
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
                                                add(8);
                                                add(15);
                                                add(30);
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
                                                add(5);
                                                add(8);
                                                add(16);
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
                                                add(8);
                                                add(16);
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
                                                add(8);
                                                add(16);
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
                                                add(0);
                                                add(5);
                                                add(8);
                                                add(16);
                                            }
                                        }
                                ));
                                add(new PayCombo(
                                        new ArrayList<>(){
                                            {
                                                add(11);
                                            }
                                        },
                                        new ArrayList<>(){
                                            {
                                                add(0);
                                                add(0);
                                                add(5);
                                                add(8);
                                                add(16);
                                            }
                                        }
                                ));
                            }
                        }), new ProgressConfig(ConstMathSlot.ProgressType.TRIGGER_ROUND,
                                new ProgressConfigExtendTriggerRound(
                                        new HashMap<>(){
                                            {
                                                put(ConstMathSlot.TriggerEvent.TRIGGER_01, 10);
                                                put(ConstMathSlot.TriggerEvent.TRIGGER_02, 10);
                                                put(ConstMathSlot.TriggerEvent.TRIGGER_03, 10);
                                            }
                                        },
                                        new HashMap<>(){
                                            {
                                                put(ConstMathSlot.TriggerEvent.RE_TRIGGER_01, 10);
                                                put(ConstMathSlot.TriggerEvent.RE_TRIGGER_02, 10);
                                                put(ConstMathSlot.TriggerEvent.RE_TRIGGER_03, 10);
                                            }
                                        },
                                        99
                                )), new SpecialFeatureConfigCluster(new ArrayList<>(){
                                    {
                                        add(new SpecialFeatureConfig(ConstMathSlot.SpecialFeatureType.SF_05, ConstMathSlot.TriggerEvent.RE_TRIGGER_01, 5));
                                        add(new SpecialFeatureConfig(ConstMathSlot.SpecialFeatureType.SF_06, ConstMathSlot.TriggerEvent.RE_TRIGGER_02, 10));
                                        add(new SpecialFeatureConfig(ConstMathSlot.SpecialFeatureType.SF_07, ConstMathSlot.TriggerEvent.RE_TRIGGER_03, 30));
                                    }
                                }), new ReadyHandConfigCluster(List.of(
                                        new ReadyHandConfig(ConstMathSlot.ReadyHandType.READY_HAND_04, ConstMathSlot.ReadyHandLimitType.MAX_TRIGGER_LIMIT),
                                        new ReadyHandConfig(ConstMathSlot.ReadyHandType.READY_HAND_05, ConstMathSlot.ReadyHandLimitType.MAX_TRIGGER_LIMIT),
                                        new ReadyHandConfig(ConstMathSlot.ReadyHandType.READY_HAND_06, ConstMathSlot.ReadyHandLimitType.MAX_TRIGGER_LIMIT)
                                )), ConstMathSlot.RoundNormalGameType.SWZS_FREEGAME, new RoundNormalGameConfigExtendSwzsFreeGame()
                ),
                ConstMathSlot.SlotDetailType.SWZS_FREEGAME
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
                                swzsBaseGameStateConfig
                        );
                        add(
                                swzsFreeGameStateConfig
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
