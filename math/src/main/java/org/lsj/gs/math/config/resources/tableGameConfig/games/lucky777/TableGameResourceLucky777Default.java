package org.lsj.gs.math.config.resources.tableGameConfig.games.lucky777;

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
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.gameConfig.GameHitConfigExtendLineGame;
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
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.roundNormalGameConfig.RoundNormalGameConfigExtendLucky777BaseGame;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.specialFeatureConfig.SpecialFeatureConfigCluster;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfig;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfigExtendRound;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResultExtendFix;
import org.lsj.gs.math.games.lucky777.enity.config.GameStateConfigExtendLucky777BaseGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Lucky777 老虎機牌桌遊戲設定產生器
public class TableGameResourceLucky777Default extends AbstractTableGameResourceSlot {

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
                        add(3.0);
                        add(4.0);
                        add(5.0);
                        add(10.0);
                        add(20.0);
                        add(50.0);
                        add(100.0);
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
                                        new PaymentConfig(ConstMathSlot.PaymentType.RATIO, new PaymentConfigExtendRatio(1.0, 1.0))));
                            }
                        });
                    }
                }
        );

        // 6. 邏輯類型設定
        ConstMathSlot.LogicType logicType = ConstMathSlot.LogicType.NORMAL;

        // 7. 設定遊戲狀態設定
        GameStateConfig baseGameStateConfig = new GameStateConfig(
                ConstMathSlot.GameStateType.LUCKY777_BASEGAME, new GameStateConfigExtendLucky777BaseGame(), new GameStateInputConfig(ConstMathSlot.GameStateInputType.NONE, new GameStateInputConfigExtendNone()), ConstMathSlot.RoundType.NORMAL, new RoundConfigNormal(
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
                                                        add(List.of(3,0,6,4,1,7,5,2,8,6,3,8,7,4,7,8,5,6,8,7,6,4,3,4,3,0,6,4,1,7,5,2,8,6,3,8,7,4,7,8,5,6,8,7,6,4,3,4));
                                                        add(List.of(3,0,6,4,1,7,5,2,8,6,3,8,7,4,7,8,5,6,8,7,6,4,3,4,3,0,6,4,1,7,5,2,8,6,3,8,7,4,7,8,5,6,8,7,6,4,3,4));
                                                        add(List.of(3,0,6,4,1,7,5,2,8,6,3,8,7,4,7,8,5,6,8,7,6,4,3,4,3,0,6,4,1,7,5,2,8,6,3,8,7,4,7,8,5,6,8,7,6,4,3,4,6));
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
                                                        add(List.of(3,0,6,4,1,7,5,2,8,6,3,8,7,4,7,8,5,6,8,7,6,4,3,4,3,6,4,7,5,8,6,8,7,5,8,6,8,7,6,4,3,4,6,8,7,5,6,8,7,8,7,8,7,8));
                                                        add(List.of(3,0,6,4,1,7,5,2,8,6,3,8,7,4,7,8,5,6,8,7,6,5,3,5,3,6,5,7,5,8,6,8,7,5,8,6,8,7,6,5,3,5,6,8,7,5,6,8,7,8,7,8,7,8));
                                                        add(List.of(3,0,6,4,1,7,5,2,8,4,3,8,7,4,7,8,5,4,8,7,4,4,3,4,3,4,4,7,5,8,6,8,7,5,8,4,8,7,6,4,3,4,4,8,7,5,6,5,7,8,7,8,7,8,5));
                                                    }
                                                }),
                                        ConstMathSlot.ReelRtpType.LOW,
                                        ConstMathSlot.ReelStopType.STOP_BY_DEPENDENT_REEL_INDEX));
                            }
                        }),
                new InitialScreenConfig(
                        new ClientReelResult(0, ConstPgrSlot.ClientReelStopType.STOP_BY_DEPENDENT_REEL_BOX, new ClientReelStopResultExtendStopByDependentReelBox(new ArrayList<>() {
                            {
                                add(new StopBox(1, 1));
                                add(new StopBox(0, 1));
                                add(new StopBox(2, 2));
                            }
                        })),
                        new FrameResult(ConstMathSlot.FrameType.FIX, new FrameResultExtendFix(new ArrayList<>() {
                            {
                                add(1);
                                add(1);
                                add(1);
                            }
                        })),
                        ConstMathSlot.InitialScreenType.NONE, new InitialScreenConfigExtendNone()),
                new FrameConfig(
                ConstMathSlot.FrameType.FIX,
                new FrameConfigExtendFix(List.of(1, 1, 1))),
                new SymbolConfig(new ArrayList<>() {
                    {
                        add(ConstMathSlot.SymbolAttribute.M1);
                        add(ConstMathSlot.SymbolAttribute.M2);
                        add(ConstMathSlot.SymbolAttribute.M3);
                        add(ConstMathSlot.SymbolAttribute.M4);
                        add(ConstMathSlot.SymbolAttribute.M5);
                        add(ConstMathSlot.SymbolAttribute.M6);
                        add(ConstMathSlot.SymbolAttribute.M7);
                        add(ConstMathSlot.SymbolAttribute.M8);
                        add(ConstMathSlot.SymbolAttribute.M9);
                    }
                },
                        new ArrayList<>() {
                            {
                                add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                                add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                                add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                                add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                                add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                                add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                                add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                                add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                                add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                            }
                        }),
                new DampConfig(ConstMathSlot.DampType.ONE_DAMP, ConstMathSlot.DampType.ONE_DAMP),
                new GameConfig(ConstMathSlot.GameHitType.LINE_GAME, new GameHitConfigExtendLineGame(ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT, new ArrayList<>(){{add(List.of(0,0,0));}})),
                new PayTableConfig(new ArrayList<>() {
                    {
                        add(new PayCombo(
                                new ArrayList<>() {
                                    {
                                        add(0);
                                    }
                                },
                                new ArrayList<>() {
                                    {
                                        add(0);
                                        add(0);
                                        add(1000);
                                    }
                                }
                        ));
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
                                        add(500);
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
                                        add(150);
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
                                        add(80);
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
                                        add(30);
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
                                        add(20);
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
                                    }
                                }
                        ));
                        add(new PayCombo(
                                new ArrayList<>() {
                                    {
                                        add(0);
                                        add(1);
                                    }
                                },
                                new ArrayList<>() {
                                    {
                                        add(0);
                                        add(0);
                                        add(100);
                                    }
                                }
                        ));
                        add(new PayCombo(
                                new ArrayList<>() {
                                    {
                                        add(0);
                                        add(2);
                                    }
                                },
                                new ArrayList<>() {
                                    {
                                        add(0);
                                        add(0);
                                        add(100);
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
                                        add(100);
                                    }
                                }
                        ));
                        add(new PayCombo(
                                new ArrayList<>() {
                                    {
                                        add(0);
                                        add(1);
                                        add(2);
                                    }
                                },
                                new ArrayList<>() {
                                    {
                                        add(0);
                                        add(0);
                                        add(100);
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
                                        add(3);
                                    }
                                }
                        ));
                        add(new PayCombo(
                                new ArrayList<>() {
                                    {
                                        add(3);
                                        add(5);
                                    }
                                },
                                new ArrayList<>() {
                                    {
                                        add(0);
                                        add(0);
                                        add(3);
                                    }
                                }
                        ));
                        add(new PayCombo(
                                new ArrayList<>() {
                                    {
                                        add(4);
                                        add(5);
                                    }
                                },
                                new ArrayList<>() {
                                    {
                                        add(0);
                                        add(0);
                                        add(3);
                                    }
                                }
                        ));
                        add(new PayCombo(
                                new ArrayList<>() {
                                    {
                                        add(3);
                                        add(4);
                                        add(5);
                                    }
                                },
                                new ArrayList<>() {
                                    {
                                        add(0);
                                        add(0);
                                        add(3);
                                    }
                                }
                        ));
                        add(new PayCombo(
                                new ArrayList<>() {
                                    {
                                        add(6);
                                        add(7);
                                    }
                                },
                                new ArrayList<>() {
                                    {
                                        add(0);
                                        add(0);
                                        add(1);
                                    }
                                }
                        ));
                        add(new PayCombo(
                                new ArrayList<>() {
                                    {
                                        add(6);
                                        add(8);
                                    }
                                },
                                new ArrayList<>() {
                                    {
                                        add(0);
                                        add(0);
                                        add(1);
                                    }
                                }
                        ));
                        add(new PayCombo(
                                new ArrayList<>() {
                                    {
                                        add(7);
                                        add(8);
                                    }
                                },
                                new ArrayList<>() {
                                    {
                                        add(0);
                                        add(0);
                                        add(1);
                                    }
                                }
                        ));
                        add(new PayCombo(
                                new ArrayList<>() {
                                    {
                                        add(6);
                                        add(7);
                                        add(8);
                                    }
                                },
                                new ArrayList<>() {
                                    {
                                        add(0);
                                        add(0);
                                        add(1);
                                    }
                                }
                        ));
                    }
                }), new ProgressConfig(ConstMathSlot.ProgressType.ROUND, new ProgressConfigExtendRound(1, 0, 1)), new SpecialFeatureConfigCluster(new ArrayList<>() {{}
                }), new ReadyHandConfigCluster(),
                ConstMathSlot.RoundNormalGameType.LUCKY777_BASEGAME,
                new RoundNormalGameConfigExtendLucky777BaseGame()
        ), ConstMathSlot.SlotDetailType.LUCKY777_BASEGAME
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
                                baseGameStateConfig
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
                            }
                        });
                        add(new ArrayList<>(){
                            {
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
                        put(ConstMathSlot.OddsWinType.BIG_WIN, 10);
                        put(ConstMathSlot.OddsWinType.MEGA_WIN, 30);
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
