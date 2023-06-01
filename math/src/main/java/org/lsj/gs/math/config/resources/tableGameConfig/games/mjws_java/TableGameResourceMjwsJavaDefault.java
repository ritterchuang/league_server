package org.lsj.gs.math.config.resources.tableGameConfig.games.mjws_java;

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
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.CascadeClusterConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.cascadeClusterConfigExtend.CascadeClusterConfigExtendMjhl;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.cascadeConfig.CascadeConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.cascadeConfig.cascadeConfigExtend.CascadeConfigExtendMjhl;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.dampConfig.DampConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.eliminationConfig.EliminateConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.eliminationConfig.EliminationConfigExtendNormal;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfigExtendFix;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.gameConfig.GameConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.gameConfig.GameHitConfigExtendWayGame;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.gameFlowConfig.GameFlowConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.initialScreenConfig.InitialScreenConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.initialScreenConfig.InitialScreenConfigExtendMjhl;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayCombo;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayTableConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.paymentConfig.PaymentConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.paymentConfig.PaymentConfigExtendRatio;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.readyHandConfig.ReadyHandConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.readyHandConfig.ReadyHandConfigCluster;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfigExtendNonReelWeight;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfigExtendReelDependent;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelStripBox;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.RoundConfigCascade;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.roundCascadeGameConfig.RoundCascadeGameConfigExtendMjwsBaseGame;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.roundCascadeGameConfig.RoundCascadeGameConfigExtendMjwsFreeGame;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.specialFeatureConfig.SpecialFeatureConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.specialFeatureConfig.SpecialFeatureConfigCluster;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfig;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfigExtendCascade;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfigExtendRound;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResultExtendFix;
import org.lsj.gs.math.games.mjws_java.enity.config.GameStateConfigExtendMjwsBaseGame;
import org.lsj.gs.math.games.mjws_java.enity.config.GameStateConfigExtendMjwsFreeGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// 麻將無雙 牌桌遊戲設定產生器
public class TableGameResourceMjwsJavaDefault extends AbstractTableGameResourceSlot {

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
                                        new PaymentConfig(ConstMathSlot.PaymentType.RATIO, new PaymentConfigExtendRatio(20.0, 20.0))));
                            }
                        });
                    }
                }
        );

        // 6. 邏輯類型設定
        ConstMathSlot.LogicType logicType = ConstMathSlot.LogicType.CASCADE;

        // 7. 輪帶表相關設定
        // 7.1 基礎遊戲設定
        List<ReelStripBox> baseStripBoxList = new ArrayList<>(){
            {
                add(new ReelStripBox(
                        0,
                        1,
                        new ReelConfigExtendReelDependent(
                                new ArrayList<>(){
                                    {
                                        add(List.of(2,5,6,4,7,8,3,9,1,8,5,3,9,7,8,3,9,7,8,4,9,7,8,3,9,7,8,4,5,9,7,8,3,9,8,4,9,8,4,9,8,2,9,8,4,9,7,8,4,9,7,8,2,7,8,4,7,8,4,9,8,4,7,6,8));
                                        add(List.of(2,5,6,4,7,8,3,9,1,6,2,5,6,8,4,9,5,6,2,5,6,3,5,8,3,7,5,6,2,5,5,6,6,3,5,6,3,5,6,3,5,6,5,6,4,6,5,6,5,3,5,5,3,6,4,5,5,3,6,5,6,3,6,5,6));
                                        add(List.of(2,5,6,4,7,8,3,9,1,8,7,2,8,7,4,9,7,8,2,9,7,8,2,9,7,8,4,9,7,8,2,5,9,4,7,8,8,3,9,7,8,4,9,8,2,7,9,8,1,9,8,3,7,9,1,7,9,2,7,9,8,7,3,9,8,7,9));
                                        add(List.of(2,5,6,4,7,8,3,9,1,6,5,6,4,7,5,3,8,5,6,2,8,5,6,5,6,4,9,5,5,6,6,2,5,6,2,5,6,5,6,4,5,6,5,6,4,6,5,6,5,4,6,5,6,5,4,6,5,4,5,3,6,5,4,6,5,6));
                                        add(List.of(2,5,6,4,7,8,3,9,1,8,5,3,7,8,4,7,8,3,9,7,8,4,9,7,8,4,9,7,8,3,9,7,8,3,7,8,4,7,9,3,9,7,8,4,9,7,2,9,8,4,8,9,7,2,8,9,7));
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
                                        add(List.of(2,5,6,4,7,8,3,9,7,8,5,3,9,7,8,3,9,7,8,4,9,7,8,3,9,7,8,4,5,9,7,8,3,9,8,4,9,8,4,9,8,4,9,8,4,9,7,8,4,9,7,8,4,7,8,4,7,8,4,9,8,4,7,6,8));
                                        add(List.of(2,5,6,4,7,8,3,9,5,6,2,5,6,8,3,9,5,6,2,5,6,3,5,8,3,7,5,6,2,5,5,6,6,3,5,6,3,5,6,3,5,6,5,6,3,6,5,6,5,3,5,5,3,6,6,5,5,3,6,5,6,3,6,5,6));
                                        add(List.of(2,5,6,4,7,8,3,9,7,8,7,2,8,7,3,9,7,8,2,9,7,8,2,9,7,8,4,9,7,8,2,9,9,7,7,8,8,3,9,7,8,7,9,8,2,7,9,8,7,9,8,3,7,9,8,7,9,2,7,9,8,7,2,9,8,7,9));
                                        add(List.of(2,5,6,4,7,8,3,9,5,6,5,6,4,7,5,6,8,5,6,2,8,5,6,5,6,4,9,5,5,6,6,2,5,6,2,5,6,5,6,4,5,6,5,6,4,6,5,6,5,4,6,5,6,5,4,6,5,4,5,6,6,5,4,6,5,6));
                                        add(List.of(2,5,6,4,7,8,3,9,7,8,5,3,7,8,4,7,8,3,9,7,8,4,9,7,8,4,9,7,8,3,9,7,8,3,7,8,4,7,9,3,9,7,8,4,9,7,2,9,8,4,8,9,7,2,8,9,7));
                                    }
                                }),
                        ConstMathSlot.ReelRtpType.LOW,
                        ConstMathSlot.ReelStopType.STOP_BY_DEPENDENT_REEL_INDEX));
            }
        };
        List<ReelStripBox> baseCascadeStripBoxList = new ArrayList<>() {
            {
                add(new ReelStripBox(0, 1,
                        new ReelConfigExtendNonReelWeight(new ArrayList<>() {{
                            add(List.of(0, 5, 25, 50, 150, 50, 30, 150, 150, 250));
                            add(List.of(0, 5, 50, 25, 25, 300, 300, 30, 30, 50));
                            add(List.of(0, 5, 50, 25, 25, 20, 20, 200, 250, 250));
                            add(List.of(0, 5, 50, 20, 20, 300, 300, 30, 30, 50));
                            add(List.of(0, 5, 30, 50, 200, 50, 25, 200, 250, 300));
                        }}),
                        ConstMathSlot.ReelRtpType.HIGH, ConstMathSlot.ReelStopType.STOP_BY_SCREEN));
                add(new ReelStripBox(1, 1,
                        new ReelConfigExtendNonReelWeight(new ArrayList<>() {{
                            add(List.of(0, 0, 10, 50, 150, 50, 10, 150, 150, 250));
                            add(List.of(0, 0, 50, 10, 10, 300, 300, 20, 20, 50));
                            add(List.of(0, 0, 50, 10, 10, 10, 10, 200, 250, 250));
                            add(List.of(0, 0, 50, 10, 10, 300, 300, 20, 20, 50));
                            add(List.of(0, 0, 10, 50, 200, 50, 10, 200, 250, 300));
                        }}), ConstMathSlot.ReelRtpType.LOW, ConstMathSlot.ReelStopType.STOP_BY_SCREEN));
            }
        };
        List<Double> baseGoldProb = List.of(0.0, 0.08, 0.1, 0.12, 0.0);
        List<Integer> baseMultiplier =  List.of(1, 2, 3, 5);

        // 7.2 免費遊戲設定
        List<ReelStripBox> freeStripBoxList = new ArrayList<>(){
            {
                add(new ReelStripBox(
                        0,
                        1,
                        new ReelConfigExtendReelDependent(
                                new ArrayList<>(){
                                    {
                                        add(List.of(2,5,6,4,7,8,3,9,1,8,7,8,5,3,9,7,8,3,9,7,8,4,9,7,8,3,9,7,8,4,5,9,7,8,3,9,8,4,9,8,4,9,8,4,9,8,4,9,7,8,4,9,7,8,4,7,8,4,7,8,4,9,8));
                                        add(List.of(2,5,6,4,7,8,3,9,1,6,5,6,2,5,6,8,3,9,5,6,2,5,6,3,5,8,3,7,5,6,2,5,5,6,6,3,5,6,3,5,6,3,5,6,5,6,3,6,5,6,5,3,5,5,3,6,6,5,5,3,6,5,6));
                                        add(List.of(2,5,6,4,7,8,3,9,1,8,7,8,7,2,8,7,3,9,7,8,2,9,7,8,2,9,7,8,4,9,7,8,2,9,9,7,7,8,8,3,9,7,1,7,9,8,2,7,9,8,1,9,8,3,7,9,1,7,9,2,7,9,8));
                                        add(List.of(2,5,6,4,7,8,3,9,1,6,5,6,5,6,4,7,5,6,8,5,6,2,8,5,6,5,6,4,9,5,5,6,6,2,5,6,2,5,6,5,6,4,5,6,5,6,4,6,5,6,5,4,6,5,6,5,4,6,5,4,5,6,6));
                                        add(List.of(2,5,6,4,7,8,3,9,1,8,7,8,5,3,7,8,4,7,8,3,9,7,8,4,9,7,8,4,9,7,8,3,9,7,8,3,7,8,4,7,9,3,9,7,8,4,9,7,2,9,8,4,8,9,7,2,8,9,7,4,8,9,7));
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
                                        add(List.of(2,5,6,4,7,8,3,9,1,8,7,8,5,3,9,7,8,3,9,7,8,4,9,7,8,3,9,7,8,4,5,9,7,8,3,9,8,4,9,8,4,9,8,4,9,8,4,9,7,8,4,9,7,8,4,7,8,4,7,8,4,9,8));
                                        add(List.of(2,5,6,4,7,8,3,9,1,6,5,6,2,5,6,8,3,9,5,6,2,5,6,3,5,8,3,7,5,6,2,5,5,6,6,3,5,6,3,5,6,3,5,6,5,6,3,6,5,6,5,3,5,5,3,6,6,5,5,3,6,5,6));
                                        add(List.of(2,5,6,4,7,8,3,9,1,8,7,8,7,2,8,7,3,9,7,8,2,9,7,8,2,9,7,8,4,9,7,8,2,9,9,7,7,8,8,3,9,7,1,7,9,8,2,7,9,8,1,9,8,3,7,9,1,7,9,2,7,9,8));
                                        add(List.of(2,5,6,4,7,8,3,9,1,6,5,6,5,6,4,7,5,6,8,5,6,2,8,5,6,5,6,4,9,5,5,6,6,2,5,6,2,5,6,5,6,4,5,6,5,6,4,6,5,6,5,4,6,5,6,5,4,6,5,4,5,6,6));
                                        add(List.of(2,5,6,4,7,8,3,9,1,8,7,8,5,3,7,8,4,7,8,3,9,7,8,4,9,7,8,4,9,7,8,3,9,7,8,3,7,8,4,7,9,3,9,7,8,4,9,7,2,9,8,4,8,9,7,2,8,9,7,4,8,9,7));
                                    }
                                }),
                        ConstMathSlot.ReelRtpType.LOW,
                        ConstMathSlot.ReelStopType.STOP_BY_DEPENDENT_REEL_INDEX));
            }
        };
        List<ReelStripBox> freeCascadeStripBoxList = new ArrayList<>() {
            {
                add(new ReelStripBox(0, 1,
                        new ReelConfigExtendNonReelWeight(new ArrayList<>() {{
                            add(List.of(0, 5, 20, 50, 100, 50, 20, 100, 150, 150));
                            add(List.of(0, 5, 50, 30, 20, 150, 150, 50, 50, 50));
                            add(List.of(0, 5, 50, 30, 50, 30, 50, 150, 200, 200));
                            add(List.of(0, 5, 50, 30, 20, 150, 150, 50, 50, 50));
                            add(List.of(0, 5, 20, 50, 150, 50, 20, 100, 150, 150));
                        }}),
                        ConstMathSlot.ReelRtpType.HIGH, ConstMathSlot.ReelStopType.STOP_BY_SCREEN));
                add(new ReelStripBox(1, 1,
                        new ReelConfigExtendNonReelWeight(new ArrayList<>() {{
                            add(List.of(0, 5, 20, 50, 100, 50, 20, 100, 150, 150));
                            add(List.of(0, 5, 50, 30, 20, 150, 150, 50, 50, 50));
                            add(List.of(0, 5, 50, 30, 50, 30, 50, 150, 200, 200));
                            add(List.of(0, 5, 50, 30, 20, 150, 150, 50, 50, 50));
                            add(List.of(0, 5, 20, 50, 150, 50, 20, 100, 150, 150));
                        }}),
                        ConstMathSlot.ReelRtpType.LOW, ConstMathSlot.ReelStopType.STOP_BY_SCREEN));
            }
        };
        List<Double> freeGoldProb = List.of(0.0, 0.095, 0.13, 0.15, 0.0);
        List<Integer> freeMultiplier = List.of(2, 4, 6, 10);

        // 7.3 免費遊戲開關設定
        ConstMathSlot.Condition targetCondition = ConstMathSlot.Condition.CD_01;
        List<ConstMathSlot.Condition> freeGameConditionList = List.of(ConstMathSlot.Condition.CD_FALSE, ConstMathSlot.Condition.CD_FALSE, targetCondition);

        // 8. 設定遊戲狀態設定
        // 8.1 基礎遊戲狀態設定
        GameStateConfig mjwsBaseGameStateConfig = new GameStateConfig(
                ConstMathSlot.GameStateType.MJWS_BASEGAME,
                new GameStateConfigExtendMjwsBaseGame(),
                new GameStateInputConfig(ConstMathSlot.GameStateInputType.NONE, new GameStateInputConfigExtendNone()),
                ConstMathSlot.RoundType.CASCADE,
                new RoundConfigCascade(
                        new ReelConfig(
                                ConstMathSlot.ReelType.REEL_DEPENDENT,
                                baseStripBoxList),
                        new InitialScreenConfig(
                                        new ClientReelResult(0, ConstPgrSlot.ClientReelStopType.STOP_BY_DEPENDENT_REEL_BOX, new ClientReelStopResultExtendStopByDependentReelBox(new ArrayList<>() {
                                            {
                                                add(new StopBox(4, 1));
                                                add(new StopBox(12, 1));
                                                add(new StopBox(41, 1));
                                                add(new StopBox(12, 1));
                                                add(new StopBox(4, 1));
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
                                ConstMathSlot.InitialScreenType.MJHL,
                                new InitialScreenConfigExtendMjhl(List.of(
                                        List.of(),
                                        List.of(1,3,5,10),
                                        List.of(1,3,5,10),
                                        List.of(1,3,5,10),
                                        List.of()
                                ))),
                        new FrameConfig(
                                ConstMathSlot.FrameType.FIX,
                                new FrameConfigExtendFix(List.of(4, 4, 4, 4, 4))),
                        new SymbolConfig(
                                new ArrayList<>() {
                                    {
                                        add(ConstMathSlot.SymbolAttribute.WILD_01);
                                        add(ConstMathSlot.SymbolAttribute.FREE_GAME_01);
                                        add(ConstMathSlot.SymbolAttribute.M1);
                                        add(ConstMathSlot.SymbolAttribute.M2);
                                        add(ConstMathSlot.SymbolAttribute.M3);
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
                                                add(0);
                                                add(15);
                                                add(60);
                                                add(100);
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
                                                add(10);
                                                add(40);
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
                                                add(8);
                                                add(20);
                                                add(60);
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
                                                add(6);
                                                add(15);
                                                add(40);
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
                                                add(4);
                                                add(10);
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
                                                add(4);
                                                add(10);
                                                add(20);
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
                                                add(2);
                                                add(5);
                                                add(10);
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
                                                add(2);
                                                add(5);
                                                add(10);
                                            }
                                        }
                                ));
                            }
                        }),
                        new ProgressConfig(ConstMathSlot.ProgressType.ROUND, new ProgressConfigExtendRound(1, 0, 1)),
                        new SpecialFeatureConfigCluster(
                                new ArrayList<>(){
                                    {
                                        add(new SpecialFeatureConfig(ConstMathSlot.SpecialFeatureType.SF_08, ConstMathSlot.TriggerEvent.TRIGGER_01, 0));
                                    }
                                }
                        ),
                        new ReadyHandConfigCluster(List.of(
                                new ReadyHandConfig(ConstMathSlot.ReadyHandType.READY_HAND_01, ConstMathSlot.ReadyHandLimitType.NO_LIMIT)
                        )),
                        new CascadeClusterConfig(
                                ConstMathSlot.CascadeClusterType.MJHL,
                                new CascadeClusterConfigExtendMjhl(
                                        List.of(
                                                List.of(),
                                                List.of(1,3,5,7,9),
                                                List.of(1,3,5,7,9),
                                                List.of(1,3,5,7,9),
                                                List.of()
                                        )),
                                new CascadeConfig(
                                        ConstMathSlot.CascadeType.MJHL,
                                        new CascadeConfigExtendMjhl(
                                                new ReelConfig(
                                                        ConstMathSlot.ReelType.NON_REEL_WEIGHT,
                                                        baseCascadeStripBoxList),
                                                baseGoldProb,
                                                baseMultiplier,
                                                ConstMathSlot.SymbolAttribute.WILD_01
                                        ),
                                        new SpecialFeatureConfigCluster(),
                                        new ReadyHandConfigCluster(),
                                        new ProgressConfig(ConstMathSlot.ProgressType.CASCADE, new ProgressConfigExtendCascade(1, 1, 99)),
                                        new EliminateConfig(ConstMathSlot.EliminationType.NORMAL, new EliminationConfigExtendNormal())
                                )),
                        ConstMathSlot.RoundCascadeGameType.MJWS_BASEGAME,
                        new RoundCascadeGameConfigExtendMjwsBaseGame()
                ),
                ConstMathSlot.SlotDetailType.MJWS_BASEGAME
        );

        // 8.2 免費遊戲狀態設定
        GameStateConfig mjwsFreeGameStateConfig = new GameStateConfig(
                ConstMathSlot.GameStateType.MJWS_FREEGAME,
                new GameStateConfigExtendMjwsFreeGame(),
                new GameStateInputConfig(ConstMathSlot.GameStateInputType.NONE, new GameStateInputConfigExtendNone()),
                ConstMathSlot.RoundType.CASCADE,
                new RoundConfigCascade(
                        new ReelConfig(
                                ConstMathSlot.ReelType.REEL_DEPENDENT,
                                freeStripBoxList),
                        new InitialScreenConfig(
                                new ClientReelResult(0, ConstPgrSlot.ClientReelStopType.STOP_BY_DEPENDENT_REEL_BOX, new ClientReelStopResultExtendStopByDependentReelBox(new ArrayList<>() {
                                    {
                                        add(new StopBox(58, 1));
                                        add(new StopBox(55, 1));
                                        add(new StopBox(54, 1));
                                        add(new StopBox(37, 1));
                                        add(new StopBox(13, 1));
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
                                ConstMathSlot.InitialScreenType.MJHL,
                                new InitialScreenConfigExtendMjhl(List.of(
                                        List.of(),
                                        List.of(1,3,5,10),
                                        List.of(1,3,5,10),
                                        List.of(1,3,5,10),
                                        List.of()
                                ))),
                        new FrameConfig(
                                ConstMathSlot.FrameType.FIX,
                                new FrameConfigExtendFix(List.of(4, 4, 4, 4, 4))),
                        new SymbolConfig(
                                new ArrayList<>() {
                                    {
                                        add(ConstMathSlot.SymbolAttribute.WILD_01);
                                        add(ConstMathSlot.SymbolAttribute.FREE_GAME_01);
                                        add(ConstMathSlot.SymbolAttribute.M1);
                                        add(ConstMathSlot.SymbolAttribute.M2);
                                        add(ConstMathSlot.SymbolAttribute.M3);
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
                                                add(0);
                                                add(15);
                                                add(60);
                                                add(100);
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
                                                add(10);
                                                add(40);
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
                                                add(8);
                                                add(20);
                                                add(60);
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
                                                add(6);
                                                add(15);
                                                add(40);
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
                                                add(4);
                                                add(10);
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
                                                add(4);
                                                add(10);
                                                add(20);
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
                                                add(2);
                                                add(5);
                                                add(10);
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
                                                add(2);
                                                add(5);
                                                add(10);
                                            }
                                        }
                                ));
                            }
                        }),
                        new ProgressConfig(ConstMathSlot.ProgressType.ROUND, new ProgressConfigExtendRound(12, 12, 99)),
                        new SpecialFeatureConfigCluster(
                                new ArrayList<>(){
                                    {
                                        add(new SpecialFeatureConfig(ConstMathSlot.SpecialFeatureType.SF_08, ConstMathSlot.TriggerEvent.RE_TRIGGER_01, 0));
                                    }
                                }
                        ),
                        new ReadyHandConfigCluster(List.of(
                                new ReadyHandConfig(ConstMathSlot.ReadyHandType.READY_HAND_01, ConstMathSlot.ReadyHandLimitType.NO_LIMIT)
                        )),
                        new CascadeClusterConfig(
                                ConstMathSlot.CascadeClusterType.MJHL,
                                new CascadeClusterConfigExtendMjhl(
                                        List.of(
                                                List.of(),
                                                List.of(1,3,5,7,9),
                                                List.of(1,3,5,7,9),
                                                List.of(1,3,5,7,9),
                                                List.of()
                                        )),
                                new CascadeConfig(
                                        ConstMathSlot.CascadeType.MJHL,
                                        new CascadeConfigExtendMjhl(
                                                new ReelConfig(
                                                        ConstMathSlot.ReelType.NON_REEL_WEIGHT,
                                                        freeCascadeStripBoxList),
                                                freeGoldProb,
                                                freeMultiplier,
                                                ConstMathSlot.SymbolAttribute.WILD_01
                                        ),
                                        new SpecialFeatureConfigCluster(),
                                        new ReadyHandConfigCluster(),
                                        new ProgressConfig(ConstMathSlot.ProgressType.CASCADE, new ProgressConfigExtendCascade(1, 1, 99)),
                                        new EliminateConfig(ConstMathSlot.EliminationType.NORMAL, new EliminationConfigExtendNormal())
                                )),
                        ConstMathSlot.RoundCascadeGameType.MJWS_FREEGAME,
                        new RoundCascadeGameConfigExtendMjwsFreeGame(3, 2)
                ),
                ConstMathSlot.SlotDetailType.MJWS_FREEGAME
        );

        // 9. 設定遊戲狀態設定集合
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
                List.of(mjwsBaseGameStateConfig, mjwsFreeGameStateConfig)
        );

        // 10. 設定流程設定
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
                        add(freeGameConditionList);
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

        // 11. 動畫設定
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
