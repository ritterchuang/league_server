package org.lsj.gs.math.config.resources.tableGameConfig.games.dgry_java;

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
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.betSpinConfig.*;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.CascadeClusterConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.cascadeClusterConfigExtend.CascadeClusterConfigExtendDgry;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.cascadeConfig.CascadeConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.cascadeConfig.cascadeConfigExtend.CascadeConfigExtendDgry;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.dampConfig.DampConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.eliminationConfig.EliminateConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.eliminationConfig.EliminationConfigExtendNormal;
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
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.readyHandConfig.ReadyHandConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.readyHandConfig.ReadyHandConfigCluster;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfigExtendNonReelWeight;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfigExtendReelDependent;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelStripBox;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.RoundConfigCascade;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.roundCascadeGameConfig.RoundCascadeGameConfigExtendDgryBaseGame;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.roundCascadeGameConfig.RoundCascadeGameConfigExtendDgryBonusGame;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.roundCascadeGameConfig.RoundCascadeGameConfigExtendDgryFreeGame;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.specialFeatureConfig.SpecialFeatureConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.specialFeatureConfig.SpecialFeatureConfigCluster;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfig;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfigExtendCascade;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfigExtendRound;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfigExtendTriggerRound;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResultExtendFix;
import org.lsj.gs.math.games.dgry_java.entity.ConstDgryJava;
import org.lsj.gs.math.games.dgry_java.entity.config.DgryBonusGameDisplayType;
import org.lsj.gs.math.games.dgry_java.entity.config.GameStateConfigExtendDgryBaseGame;
import org.lsj.gs.math.games.dgry_java.entity.config.GameStateConfigExtendDgryBonusGame;
import org.lsj.gs.math.games.dgry_java.entity.config.GameStateConfigExtendDgryFreeGame;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 帝国榮耀 牌桌遊戲設定產生器
public class TableGameResourceDgryJavaDefault extends AbstractTableGameResourceSlot {

    public TableGameConfigSlot create() {
        // 1. 計算遊戲調控設定
        GameAdjustConfig gameAdjustConfig = super.createGameAdjustConfig();

        // 2. 計算亂數產生器演算法設定
        RngAlgorithmConfig rngAlgorithmConfig = super.createRngAlgorithmConfig();

        // 3. 設定押注類型列表
        List<ConstMathSlot.BetType> betTypeList = new ArrayList<>(){
            {
                add(ConstMathSlot.BetType.NONE);
            }
        };

        // 4. 設定玩法類型列表
        List<ConstMathSlot.SpinType> SpinTypeList = new ArrayList<>(){
            {
                add(ConstMathSlot.SpinType.NORMAL);
                add(ConstMathSlot.SpinType.BUYFEATURE01);
                add(ConstMathSlot.SpinType.BUYFEATURE02);
            }
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
                                        new PaymentConfig(ConstMathSlot.PaymentType.RATIO, new PaymentConfigExtendRatio(15.0, 15.0))));

                                put(ConstMathSlot.SpinType.BUYFEATURE01, new BetSpinConfig(
                                        ConstMathSlot.BetSpinType.NONE_BUYFEATURE01,
                                        new BetSpinConfigExtendBuyFeature01(),
                                        new PaymentConfig(ConstMathSlot.PaymentType.RATIO, new PaymentConfigExtendRatio(MathUtil.multiply(15.0, 50), 15.0))));

                                put(ConstMathSlot.SpinType.BUYFEATURE02, new BetSpinConfig(
                                        ConstMathSlot.BetSpinType.NONE_BUYFEATURE02,
                                        new BetSpinConfigExtendBuyFeature02(),
                                        new PaymentConfig(ConstMathSlot.PaymentType.RATIO, new PaymentConfigExtendRatio(MathUtil.multiply(15.0, 100), 15.0))));
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
                                        add(List.of(2,2,2,8,3,6,0,8,3,4,7,8,2,3,7,0,2,5,1,4,5,7,2,7,8,2,5,0,7,5,5,1,4,4,1,5,8,7,2,5,8,0,4,6,8,7,3,6,4,2,5,1,4,4,3,6,0,7,5,8,7,8,5,1,4,8,7,5,5,8,1,2,8,1,5,4,8,8,1,2,5,1,8,5,8,7,4,7,7,4,8,1,2,8,6,6,3,3,7,2,7,3,8,4,8,1,4,8,3,6,6,8,7,4,7,8,6,7,7,8,4,7,7));
                                        add(List.of(2,2,2,6,7,0,8,4,8,7,5,6,8,2,7,6,0,8,7,5,4,4,3,6,1,3,7,8,5,8,0,3,3,3,1,7,7,1,6,7,1,3,3,6,0,5,7,7,1,3,3,1,6,7,5,0,8,4,7,8,3,8,6,4,4,4,2,7,3,8,8,4,7,8,5,4,4,3,7,8,4,8,3,7,8,5,8,7,4,7,8,3,2,8,5,6,3,1,7,6,1,3,3,2,7,8,5,8,7,3,1,7,6,6,8,5,8,8,6,7,1,3,6,7,5,8,6,3,1,7,7,8,8,4,6,8));
                                        add(List.of(6,7,8,2,2,0,6,8,0,5,5,8,7,1,8,6,0,2,8,4,1,2,8,7,3,6,1,5,4,6,1,3,7,2,1,6,8,8,0,4,4,7,2,6,1,5,5,6,1,7,6,8,5,1,8,4,6,7,1,8,8,5,7,1,3,7,6,8,1,4,6,5,8,7,1,6,8,5,7,6,1,8,7,5,6,8,1,7,6,5,8,7,4,1,6,8,5,7,6,1,8,7,5,6,8,1,7,6,5,7,1,8,3,7,1,5,7,8,1,7,8,5));
                                        add(List.of(6,7,8,2,2,2,6,8,0,2,3,6,6,0,2,8,8,0,6,8,2,7,8,8,3,6,7,7,2,6,4,4,3,7,5,8,6,3,7,8,5,6,7,4,8,6,3,8,8,4,6,7,3,8,6,4,7,8,3,6,7,4,7,6,3,8,6,4,7,8,5,6,7,4,8,6,3,7,8,5,6,7,4,8,6,3,7,8,5,6,7,4,8,6,5,7,6,4));
                                        add(List.of(2,2,2,6,8,7,2,8,8,7,4,6,7,3,8,6,5,7,4,6,8,5,4,7,4,7,8,3,7,7,5,7,7,2,8,8,3,2,8,5,7,4,8,7,4,7,8,3,7,8,4,7,7,3,8,7,5,8,7,4,8,7,3,8,7,5,7,8,3,8,7,5,6,8,7,4,7,8,5,7,8,4,7,8,6));
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
                                        add(List.of(2,2,2,8,3,6,6,8,3,4,7,8,2,3,7,8,2,5,7,4,5,7,2,7,8,2,5,0,7,5,5,7,4,4,3,7,8,7,2,5,8,7,4,6,8,7,3,6,4,3,5,6,4,4,3,6,0,7,5,8,7,3,5,8,4,8,7,5,7,8,7,2,8,5,5,4,6,8,7,2,5,7,8,5,8,7,4,7,7,4,8,7,2,8,6,6,3,3,7,2,7,3,8,4,8,7,4,8,3,6,6,8,7,4,7,8,6,7,7,8,4,7,7));
                                        add(List.of(2,2,2,6,7,8,8,4,8,7,5,6,8,2,7,6,4,8,7,5,4,4,3,6,8,3,7,8,5,8,0,3,4,8,4,7,8,5,6,2,7,4,3,6,0,5,8,8,2,7,8,4,6,7,5,8,8,4,7,8,3,8,6,4,4,4,2,7,3,8,8,4,7,8,5,4,4,3,7,8,4,8,3,7,8,5,8,7,4,7,8,3,2,8,5,8,6,3,7,8,2,2,8,3,7,8,5,8,7,3,8,7,5,6,8,5,8,8,5,7,8,3,8,7,5,8,8,3,7,7,5,8,8,4,6,8));
                                        add(List.of(6,7,8,2,2,2,6,8,0,5,5,8,7,2,8,6,7,2,8,4,4,2,8,7,3,6,8,5,4,6,6,3,7,2,4,6,8,8,0,4,4,7,2,6,8,5,5,6,4,7,6,8,5,7,8,4,6,7,3,8,8,5,7,6,3,7,6,8,5,4,6,5,8,7,3,6,8,5,7,6,4,8,7,5,6,8,3,7,6,5,8,7,4,3,6,8,5,7,6,3,8,7,5,6,8,3,7,6,5,7,6,8,3,7,8,5,7,8,3,7,8,5));
                                        add(List.of(6,7,8,2,2,2,6,8,0,2,3,6,6,0,2,8,8,5,6,8,2,7,8,8,3,6,7,7,2,6,4,4,3,7,5,8,6,3,7,8,5,6,7,4,8,6,3,8,8,4,6,7,3,8,6,4,7,8,3,6,7,4,7,6,3,8,6,4,7,8,5,6,7,4,8,6,3,7,8,5,6,7,4,8,6,3,7,8,5,6,7,4,8,6,5,7,6,4));
                                        add(List.of(2,2,2,6,8,7,2,8,8,7,4,6,7,3,8,6,5,7,4,6,8,5,4,7,4,7,8,3,7,7,5,7,7,2,8,8,3,2,8,5,7,4,8,7,4,7,8,3,7,8,4,7,7,3,8,7,5,8,7,4,8,7,3,8,7,5,7,8,3,8,7,5,6,8,7,4,7,8,5,7,8,4,7,8,6));
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
                            add(List.of(62, 0, 60, 60, 100, 50, 20, 100, 150));
                            add(List.of(64, 0, 60, 40, 30, 150, 150, 50, 50));
                            add(List.of(60, 0, 40, 61, 50, 50, 50, 150, 200));
                            add(List.of(65, 0, 60, 40, 30, 150, 150, 50, 50));
                            add(List.of(0, 0, 60, 60, 150, 50, 20, 100, 150));
                        }}),
                        ConstMathSlot.ReelRtpType.HIGH, ConstMathSlot.ReelStopType.STOP_BY_SCREEN));
                add(new ReelStripBox(1, 1,
                        new ReelConfigExtendNonReelWeight(new ArrayList<>() {{
                            add(List.of(38, 0, 50, 50, 100, 50, 20, 100, 150));
                            add(List.of(44, 0, 50, 30, 30, 150, 150, 50, 50));
                            add(List.of(40, 0, 29, 50, 50, 50, 50, 150, 200));
                            add(List.of(45, 0, 50, 30, 30, 150, 150, 50, 50));
                            add(List.of(0, 0, 50, 50, 150, 50, 20, 100, 150));
                        }}), ConstMathSlot.ReelRtpType.LOW, ConstMathSlot.ReelStopType.STOP_BY_SCREEN));
            }
        };

        // 7.2 免費遊戲設定
        List<ReelStripBox> freeStripBoxList = new ArrayList<>(){
            {
                add(new ReelStripBox(
                        0,
                        1,
                        new ReelConfigExtendReelDependent(
                                new ArrayList<>(){
                                    {
                                        add(List.of(2,2,2,8,3,4,6,8,0,4,7,8,2,0,7,8,2,7,7,0,8,7,2,7,8,2,5,0,7,8,5,7,4,4,3,7,8,7,2,5,8,7,4,6,8,7,3,6,4,3,5,6,4,4,3,6,0,7,5,8,7,3,5,8,4,8,7,5,7,8,7,2,8,5,5,4,6,8,7,2,5,7,8,5,8,7,4,7,7,4,8,7,2,8,6,6,3,3,7,2,7,3,8,4,8,7,4,8,3,6,6,8,7,4,7,8,6,7,7,8,4,7,7));
                                        add(List.of(2,2,2,6,7,8,0,4,8,7,5,6,8,8,7,6,4,8,7,6,4,4,3,6,8,3,7,8,5,8,0,3,4,8,4,7,8,5,6,2,7,4,3,6,0,5,8,8,2,7,8,4,4,7,5,8,8,4,7,0,3,8,6,6,4,4,2,7,0,8,8,4,7,8,5,4,4,3,7,8,4,8,3,7,8,5,8,7,3,7,8,3,2,8,5,8,3,3,3,8,2,2,8,3,7,8,5,8,7,3,8,7,5,6,8,5,8,8,5,7,8,3,8,7,5,8,8,3,7,7,5,8,8,4,6,8));
                                        add(List.of(6,7,8,2,2,2,6,8,0,3,6,8,7,2,8,6,7,0,8,3,4,2,8,7,3,6,8,5,4,6,3,3,7,2,4,6,8,8,0,4,4,7,2,6,8,5,0,6,3,7,6,8,5,4,8,4,6,7,3,8,8,5,7,6,3,7,6,8,0,4,6,5,8,7,3,6,8,5,7,6,4,8,7,5,6,6,3,7,6,5,8,7,4,3,6,8,5,7,6,3,8,7,5,6,8,3,7,6,5,7,6,8,3,7,8,5,7,8,3,7,8,5));
                                        add(List.of(6,7,8,2,2,2,8,8,4,0,3,8,6,6,2,0,8,5,8,0,2,7,8,8,3,6,7,7,2,6,0,4,3,7,5,8,6,3,7,8,5,6,7,4,8,6,3,8,8,4,6,7,3,8,6,4,7,8,3,6,7,4,7,6,3,8,6,4,7,8,5,6,7,4,8,6,3,7,8,5,6,7,4,8,6,3,7,8,5,6,7,4,8,6,5,7,6,4));
                                        add(List.of(2,2,2,6,8,2,2,8,8,7,4,6,7,3,8,6,5,7,4,6,8,5,4,8,4,7,8,3,7,7,5,7,7,2,8,8,3,2,8,5,7,3,8,7,4,7,8,3,7,8,4,7,7,3,8,7,5,8,7,4,8,7,3,8,7,5,7,8,3,8,7,5,6,8,7,3,7,8,5,7,8,3,7,8,6));
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
                                        add(List.of(2,2,2,8,3,4,6,8,3,4,7,8,2,0,7,8,2,7,7,0,8,7,2,7,8,2,5,0,7,8,5,7,4,4,3,7,8,7,2,5,8,7,4,6,8,7,3,6,4,3,5,6,4,4,3,6,0,7,5,8,7,3,5,8,4,8,7,5,7,8,7,2,8,5,5,4,6,8,7,2,5,7,8,5,8,7,4,7,7,4,8,7,2,8,6,6,3,3,7,2,7,3,8,4,8,7,4,8,3,6,6,8,7,4,7,8,6,7,7,8,4,7,7));
                                        add(List.of(2,2,2,6,7,8,0,4,8,7,5,6,8,8,7,6,4,8,7,6,4,4,3,6,8,3,7,8,5,8,0,3,4,8,4,7,8,5,6,2,7,4,3,6,0,5,8,8,2,7,8,4,4,7,5,8,8,4,7,0,3,8,6,6,4,4,2,7,0,8,8,4,7,8,5,4,4,3,7,8,4,8,3,7,8,5,8,7,3,7,8,3,2,8,5,8,3,3,3,8,2,2,8,3,7,8,5,8,7,3,8,7,5,6,8,5,8,8,5,7,8,3,8,7,5,8,8,3,7,7,5,8,8,4,6,8));
                                        add(List.of(6,7,8,2,2,2,6,8,4,3,6,8,7,2,8,6,7,0,8,3,4,2,8,7,3,6,8,5,4,6,3,3,7,2,4,6,8,8,0,4,4,7,2,6,8,5,0,6,3,7,6,8,5,4,8,4,6,7,3,8,8,5,7,6,3,7,6,8,0,4,6,5,8,7,3,6,8,5,7,6,4,8,7,5,6,6,3,7,6,5,8,7,4,3,6,8,5,7,6,3,8,7,5,6,8,3,7,6,5,7,6,8,3,7,8,5,7,8,3,7,8,5));
                                        add(List.of(6,7,8,2,2,2,8,8,4,0,3,8,6,6,2,0,8,5,8,0,2,7,8,8,3,6,7,7,2,6,0,4,3,7,5,8,6,3,7,8,5,6,7,4,8,6,3,8,8,4,6,7,3,8,6,4,7,8,3,6,7,4,7,6,3,8,6,4,7,8,5,6,7,4,8,6,3,7,8,5,6,7,4,8,6,3,7,8,5,6,7,4,8,6,5,7,6,4));
                                        add(List.of(2,2,2,6,8,2,2,8,8,7,4,6,7,3,8,6,5,7,4,6,8,5,4,8,4,7,8,3,7,7,5,7,7,2,8,8,3,2,8,5,7,3,8,7,4,7,8,3,7,8,4,7,7,3,8,7,5,8,7,4,8,7,3,8,7,5,7,8,3,8,7,5,6,8,7,3,7,8,5,7,8,3,7,8,6));
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
                            add(List.of(59, 0, 50, 50, 100, 50, 20, 100, 150));
                            add(List.of(60, 0, 50, 30, 30, 150, 150, 50, 50));
                            add(List.of(52, 0, 33, 50, 50, 50, 50, 150, 200));
                            add(List.of(55, 0, 50, 30, 30, 150, 150, 50, 50));
                            add(List.of(0, 0, 50, 50, 150, 50, 20, 100, 150));
                        }}),
                        ConstMathSlot.ReelRtpType.HIGH, ConstMathSlot.ReelStopType.STOP_BY_SCREEN));
                add(new ReelStripBox(1, 1,
                        new ReelConfigExtendNonReelWeight(new ArrayList<>() {{
                            add(List.of(38, 0, 50, 50, 100, 50, 20, 100, 150));
                            add(List.of(44, 0, 50, 30, 30, 150, 150, 50, 50));
                            add(List.of(40, 0, 31, 50, 50, 50, 50, 150, 200));
                            add(List.of(45, 0, 50, 30, 30, 150, 150, 50, 50));
                            add(List.of(0, 0, 50, 50, 150, 50, 20, 100, 150));
                        }}),
                        ConstMathSlot.ReelRtpType.LOW, ConstMathSlot.ReelStopType.STOP_BY_SCREEN));
            }
        };

        // 7.3 免費遊戲(Buy Feature)設定
        List<ReelStripBox> freeBuyFeatureStripBoxList = new ArrayList<>(){
            {
                add(new ReelStripBox(
                        0,
                        1,
                        new ReelConfigExtendReelDependent(
                                new ArrayList<>(){
                                    {
                                        add(List.of(2,2,2,8,3,0,6,8,3,4,0,8,2,3,7,8,2,7,7,4,8,7,2,7,0,2,5,0,7,8,5,7,4,4,3,7,8,7,2,5,8,7,4,6,8,7,3,6,4,3,5,6,4,4,3,6,0,7,5,8,7,3,5,8,4,8,7,5,7,8,7,2,8,5,5,4,6,8,7,2,5,7,8,5,8,7,4,7,7,4,8,7,2,8,6,6,3,3,7,2,7,3,8,4,8,7,4,8,3,6,6,8,7,4,7,8,6,7,7,8,4,7,7));
                                        add(List.of(2,2,2,6,7,7,8,4,8,0,5,6,8,0,7,6,4,8,7,5,4,4,3,6,0,3,7,8,5,8,0,3,4,8,4,7,8,5,6,2,7,4,3,6,0,5,8,8,2,7,8,4,6,7,5,8,8,4,7,8,3,8,6,4,3,3,2,7,3,8,8,4,7,8,5,4,4,3,7,8,4,8,3,7,8,5,8,7,4,7,8,3,2,8,5,8,6,3,7,8,2,2,8,3,7,8,5,8,7,3,8,7,5,6,8,5,8,8,5,7,8,3,8,7,5,8,8,3,7,7,5,8,8,4,6,8));
                                        add(List.of(6,7,8,2,2,2,6,8,0,4,6,8,7,2,8,6,7,2,8,4,4,0,8,7,3,6,8,8,4,6,6,3,7,2,4,6,8,8,0,4,4,7,2,6,8,5,0,6,4,7,6,8,5,7,8,4,6,7,3,8,8,5,7,6,3,7,6,8,5,4,6,5,8,7,3,6,8,5,7,6,4,8,7,5,6,8,3,7,6,5,8,7,4,3,6,8,5,7,6,3,8,7,5,6,8,3,7,6,5,7,6,8,3,7,8,5,7,8,3,7,8,5));
                                        add(List.of(6,7,8,2,2,2,6,8,8,2,3,8,6,0,2,8,5,5,8,0,2,7,8,8,0,6,7,7,2,6,0,4,3,7,5,8,6,3,7,8,5,6,7,4,8,6,3,8,8,4,6,7,3,8,6,4,7,8,3,6,7,4,7,6,3,8,6,4,7,8,5,6,7,4,8,6,3,7,8,5,6,7,4,8,6,3,7,8,5,6,7,4,8,6,5,7,6,4));
                                        add(List.of(2,2,2,6,8,2,2,8,8,7,4,6,7,3,8,6,5,7,4,6,8,5,4,7,4,7,8,3,7,7,5,7,7,2,8,8,3,2,8,5,7,3,8,7,4,7,8,3,7,8,4,7,7,3,8,7,5,8,7,4,8,7,3,8,7,5,7,8,3,8,7,5,6,8,7,3,7,8,5,7,8,3,7,8,6));
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
                                        add(List.of(2,2,2,8,3,0,6,8,3,4,0,8,2,3,7,8,2,7,7,4,8,7,2,7,0,2,5,0,7,8,5,7,4,4,3,7,8,7,2,5,8,7,4,6,8,7,3,6,4,3,5,6,4,4,3,6,0,7,5,8,7,3,5,8,4,8,7,5,7,8,7,2,8,5,5,4,6,8,7,2,5,7,8,5,8,7,4,7,7,4,8,7,2,8,6,6,3,3,7,2,7,3,8,4,8,7,4,8,3,6,6,8,7,4,7,8,6,7,7,8,4,7,7));
                                        add(List.of(2,2,2,6,7,7,8,4,8,0,5,6,8,0,7,6,4,8,7,5,4,4,3,6,0,3,7,8,5,8,0,3,4,8,4,7,8,5,6,2,7,4,3,6,0,5,8,8,2,7,8,4,6,7,5,8,8,4,7,8,3,8,6,4,3,3,2,7,3,8,8,4,7,8,5,4,4,3,7,8,4,8,3,7,8,5,8,7,4,7,8,3,2,8,5,8,6,3,7,8,2,2,8,3,7,8,5,8,7,3,8,7,5,6,8,5,8,8,5,7,8,3,8,7,5,8,8,3,7,7,5,8,8,4,6,8));
                                        add(List.of(6,7,8,2,2,2,6,8,0,4,6,8,7,2,8,6,7,2,8,4,4,0,8,7,3,6,8,8,4,6,6,3,7,2,4,6,8,8,0,4,4,7,2,6,8,5,0,6,4,7,6,8,5,7,8,4,6,7,3,8,8,5,7,6,3,7,6,8,5,4,6,5,8,7,3,6,8,5,7,6,4,8,7,5,6,8,3,7,6,5,8,7,4,3,6,8,5,7,6,3,8,7,5,6,8,3,7,6,5,7,6,8,3,7,8,5,7,8,3,7,8,5));
                                        add(List.of(6,7,8,2,2,2,6,8,8,2,3,8,6,0,2,8,5,5,8,0,2,7,8,8,0,6,7,7,2,6,0,4,3,7,5,8,6,3,7,8,5,6,7,4,8,6,3,8,8,4,6,7,3,8,6,4,7,8,3,6,7,4,7,6,3,8,6,4,7,8,5,6,7,4,8,6,3,7,8,5,6,7,4,8,6,3,7,8,5,6,7,4,8,6,5,7,6,4));
                                        add(List.of(2,2,2,6,8,2,2,8,8,7,4,6,7,3,8,6,5,7,4,6,8,5,4,7,4,7,8,3,7,7,5,7,7,2,8,8,3,2,8,5,7,3,8,7,4,7,8,3,7,8,4,7,7,3,8,7,5,8,7,4,8,7,3,8,7,5,7,8,3,8,7,5,6,8,7,3,7,8,5,7,8,3,7,8,6));
                                    }
                                }),
                        ConstMathSlot.ReelRtpType.LOW,
                        ConstMathSlot.ReelStopType.STOP_BY_DEPENDENT_REEL_INDEX));
            }
        };
        List<ReelStripBox> freeBuyFeatureCascadeStripBoxList = new ArrayList<>() {
            {
                add(new ReelStripBox(0, 1,
                        new ReelConfigExtendNonReelWeight(new ArrayList<>() {{
                            add(List.of(28, 0, 50, 50, 100, 50, 20, 100, 150));
                            add(List.of(34, 0, 50, 30, 30, 150, 150, 50, 50));
                            add(List.of(28, 0, 27, 50, 50, 50, 50, 150, 200));
                            add(List.of(35, 0, 50, 30, 30, 150, 150, 50, 50));
                            add(List.of(0, 0, 50, 50, 150, 50, 20, 100, 150));
                        }}),
                        ConstMathSlot.ReelRtpType.HIGH, ConstMathSlot.ReelStopType.STOP_BY_SCREEN));
                add(new ReelStripBox(1, 1,
                        new ReelConfigExtendNonReelWeight(new ArrayList<>() {{
                            add(List.of(28, 0, 50, 50, 100, 50, 20, 100, 150));
                            add(List.of(34, 0, 50, 30, 30, 150, 150, 50, 50));
                            add(List.of(28, 0, 27, 50, 50, 50, 50, 150, 200));
                            add(List.of(35, 0, 50, 30, 30, 150, 150, 50, 50));
                            add(List.of(0, 0, 50, 50, 150, 50, 20, 100, 150));
                        }}),
                        ConstMathSlot.ReelRtpType.LOW, ConstMathSlot.ReelStopType.STOP_BY_SCREEN));
            }
        };

        // 7.4 Win Line 設定
        List<List<Integer>> winLineList = new ArrayList<>(){
            {
                add(List.of(1,1,1,1,1));
                add(List.of(0,0,0,0,0));
                add(List.of(2,2,2,2,2));
                add(List.of(0,1,2,1,0));
                add(List.of(2,1,0,1,2));
                add(List.of(0,0,1,0,0));
                add(List.of(2,2,1,2,2));
                add(List.of(1,2,2,2,1));
                add(List.of(1,0,0,0,1));
                add(List.of(1,0,1,0,1));
                add(List.of(1,2,1,2,1));
                add(List.of(0,1,0,1,0));
                add(List.of(2,1,2,1,2));
                add(List.of(1,1,0,1,1));
                add(List.of(1,1,2,1,1));
            }
        };

        // 8. 免費遊戲畫面倍數設定
        // 8.1 普通免費遊戲倍數設定(同時適用普通額外遊戲)
        List<Integer> baseMultiplier = List.of(1, 1, 1, 1, 1, 1);
        Map<ConstMathSlot.ReelRtpType, List<Integer>> baseMultiplierWeightMap = new HashMap<>(){
            {
                put(ConstMathSlot.ReelRtpType.HIGH, List.of(100, 100, 100, 100, 100, 100));
                put(ConstMathSlot.ReelRtpType.LOW, List.of(100, 100, 100, 100, 100, 100));
            }
        };

        // 8.2 Buy Feature 免費遊戲倍數設定
        List<Integer> buyFeature01Multiplier = List.of(2, 3, 5, 7, 10, 15);
        Map<ConstMathSlot.ReelRtpType, List<Integer>> buyFeature01MultiplierWeightMap = new HashMap<>(){
            {
                put(ConstMathSlot.ReelRtpType.HIGH, List.of(100, 100, 80, 50, 30, 13));
                put(ConstMathSlot.ReelRtpType.LOW, List.of(100, 100, 80, 50, 30, 13));
            }
        };

        // 9. Bonus Game 相關設定
        // 9.1 表演次數範圍設定[a, b]
        List<Integer> displayTimesRange = List.of(4,7);

        // 9.2 玩家血量設定
        int lifePoint = 3;

        // 9.3 插入可能表演列表
        List<DgryBonusGameDisplayType> bonusGameInsertDisplayList = new ArrayList<>(){
            {
                add(new DgryBonusGameDisplayType(ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava.MISS, ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava.SINGLE_SWORD, Boolean.FALSE, 0, 1, lifePoint, lifePoint, List.of(ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava.DOUBLE_SWORD, ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava.SHIELD)));
                add(new DgryBonusGameDisplayType(ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava.MISS, ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava.DOUBLE_SWORD, Boolean.FALSE, 0, 1, lifePoint, lifePoint, List.of(ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava.SINGLE_SWORD, ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava.SHIELD)));
            }
        };

        // 9.4 插入可能表演權重
        List<Integer> bonusGameInsertDisplayWeightList = List.of(100,100);

        // 9.5 bonus game 可能表演列表(不扣血)
        List<DgryBonusGameDisplayType> bonusGameNoDamageDisplayList = new ArrayList<>(){
            {
                add(new DgryBonusGameDisplayType(ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava.SWORD_ATTACK, ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava.DOUBLE_SWORD, Boolean.FALSE, 300, 1, lifePoint, lifePoint, List.of(ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava.SINGLE_SWORD, ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava.SHIELD)));
                add(new DgryBonusGameDisplayType(ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava.SWORD_ATTACK, ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava.SINGLE_SWORD, Boolean.FALSE, 150, 1, lifePoint, lifePoint, List.of(ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava.DOUBLE_SWORD, ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava.SHIELD)));
            }
        };

        // 9.6 可能表演列表(不扣血)權重
        Map<ConstMathSlot.ReelRtpType, List<Integer>> bonusGameNoDamageDisplayWeightMap = new HashMap<>(){
            {
                put(ConstMathSlot.ReelRtpType.HIGH, List.of(40, 60));
                put(ConstMathSlot.ReelRtpType.LOW, List.of(40, 60));
            }
        };

        // 9.7 可能表演(不扣血)數量
        List<Integer> NoDamageDisplayCountList = List.of(0, 1, 2);
        Map<ConstMathSlot.ReelRtpType, List<Integer>> NoDamageDisplayCountWeightMap = new HashMap<>(){
            {
                put(ConstMathSlot.ReelRtpType.HIGH, List.of(50, 40, 10));
                put(ConstMathSlot.ReelRtpType.LOW, List.of(50, 40, 6));
            }
        };


        // 9.8 bonus game 可能表演列表(會扣血)
        List<DgryBonusGameDisplayType> bonusGameTakeDamageDisplayList = new ArrayList<>(){
            {
                add(new DgryBonusGameDisplayType(ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava.SHIELD, Boolean.TRUE, 45, 1, lifePoint, lifePoint, List.of(ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava.DOUBLE_SWORD, ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava.SINGLE_SWORD)));
                add(new DgryBonusGameDisplayType(ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava.RETALIATE, ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava.NO_SELECT, Boolean.TRUE, 0, 1, lifePoint, lifePoint, new ArrayList<>()));
            }
        };

        // 9.9 可能表演列表(會扣血)權重
        Map<ConstMathSlot.ReelRtpType, List<Integer>> bonusGameTakeDamageDisplayWeightMap = new HashMap<>(){
            {
                put(ConstMathSlot.ReelRtpType.HIGH, List.of(95, 5));
                put(ConstMathSlot.ReelRtpType.LOW, List.of(97, 3));
            }
        };

        // 9.10 Buy Feature 額外遊戲倍數設定
        List<Integer> buyFeature02Multiplier = List.of(3, 5, 7, 10, 15);
        Map<ConstMathSlot.ReelRtpType, List<Integer>> buyFeature02MultiplierWeightMap = new HashMap<>(){
            {
                put(ConstMathSlot.ReelRtpType.HIGH, List.of(100, 100, 63, 60, 30));
                put(ConstMathSlot.ReelRtpType.LOW, List.of(101, 60, 10, 5, 3));
            }
        };


        // 10. 設定遊戲狀態設定
        // 10.1 基礎遊戲狀態設定
        GameStateConfig dgryBaseGameStateConfig = new GameStateConfig(
                ConstMathSlot.GameStateType.DGRY_BASEGAME,
                new GameStateConfigExtendDgryBaseGame(),
                new GameStateInputConfig(ConstMathSlot.GameStateInputType.NONE, new GameStateInputConfigExtendNone()),
                ConstMathSlot.RoundType.CASCADE,
                new RoundConfigCascade(
                        new ReelConfig(
                                ConstMathSlot.ReelType.REEL_DEPENDENT,
                                baseStripBoxList),
                        new InitialScreenConfig(
                                new ClientReelResult(0, ConstPgrSlot.ClientReelStopType.STOP_BY_DEPENDENT_REEL_BOX, new ClientReelStopResultExtendStopByDependentReelBox(new ArrayList<>() {
                                    {
                                        add(new StopBox(2, 1));
                                        add(new StopBox(2, 1));
                                        add(new StopBox(2, 1));
                                        add(new StopBox(2, 1));
                                        add(new StopBox(2, 1));
                                    }
                                })),
                                new FrameResult(ConstMathSlot.FrameType.FIX, new FrameResultExtendFix(new ArrayList<>() {
                                    {
                                        add(3);
                                        add(3);
                                        add(3);
                                        add(3);
                                        add(3);
                                    }
                                })),
                                ConstMathSlot.InitialScreenType.NONE,
                                new InitialScreenConfigExtendNone()),
                        new FrameConfig(
                                ConstMathSlot.FrameType.FIX,
                                new FrameConfigExtendFix(List.of(3, 3, 3, 3, 3))),
                        new SymbolConfig(
                                new ArrayList<>() {
                                    {
                                        add(ConstMathSlot.SymbolAttribute.WILD_01);
                                        add(ConstMathSlot.SymbolAttribute.FREE_GAME_01);
                                        add(ConstMathSlot.SymbolAttribute.M1);
                                        add(ConstMathSlot.SymbolAttribute.M2);
                                        add(ConstMathSlot.SymbolAttribute.M3);
                                        add(ConstMathSlot.SymbolAttribute.M4);
                                        add(ConstMathSlot.SymbolAttribute.M5);
                                        add(ConstMathSlot.SymbolAttribute.M6);
                                        add(ConstMathSlot.SymbolAttribute.M7);
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
                                        add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                                        add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                                        add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                                    }
                                }),
                        new DampConfig(ConstMathSlot.DampType.NO_DAMP, ConstMathSlot.DampType.NO_DAMP),
                        new GameConfig(ConstMathSlot.GameHitType.LINE_GAME, new GameHitConfigExtendLineGame(ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT, winLineList)),
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
                                                add(150);
                                                add(1000);
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
                                                add(15);
                                                add(100);
                                                add(500);
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
                                                add(10);
                                                add(50);
                                                add(200);
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
                                                add(10);
                                                add(30);
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
                                                add(5);
                                                add(15);
                                                add(75);
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
                                                add(5);
                                                add(10);
                                                add(30);
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
                                                add(10);
                                                add(30);
                                            }
                                        }
                                ));
                            }
                        }),
                        new ProgressConfig(ConstMathSlot.ProgressType.ROUND, new ProgressConfigExtendRound(1, 0, 1)),
                        new SpecialFeatureConfigCluster(
                                new ArrayList<>(){
                                    {
                                        add(new SpecialFeatureConfig(ConstMathSlot.SpecialFeatureType.SF_09, ConstMathSlot.TriggerEvent.TRIGGER_01, 0));
                                        add(new SpecialFeatureConfig(ConstMathSlot.SpecialFeatureType.SF_10, ConstMathSlot.TriggerEvent.TRIGGER_02, 0));
                                        add(new SpecialFeatureConfig(ConstMathSlot.SpecialFeatureType.SF_11, ConstMathSlot.TriggerEvent.TRIGGER_03, 0));
                                        add(new SpecialFeatureConfig(ConstMathSlot.SpecialFeatureType.SF_12, ConstMathSlot.TriggerEvent.TRIGGER_04, 0));
                                        add(new SpecialFeatureConfig(ConstMathSlot.SpecialFeatureType.SF_13, ConstMathSlot.TriggerEvent.TRIGGER_06, 0));
                                    }
                                }
                        ),
                        new ReadyHandConfigCluster(List.of(
                                new ReadyHandConfig(ConstMathSlot.ReadyHandType.READY_HAND_01, ConstMathSlot.ReadyHandLimitType.NO_LIMIT)
                        )),
                        new CascadeClusterConfig(
                                ConstMathSlot.CascadeClusterType.DGRY,
                                new CascadeClusterConfigExtendDgry(),
                                new CascadeConfig(
                                        ConstMathSlot.CascadeType.DGRY,
                                        new CascadeConfigExtendDgry(
                                                new ReelConfig(
                                                        ConstMathSlot.ReelType.NON_REEL_WEIGHT,
                                                        baseCascadeStripBoxList),
                                                baseMultiplier,
                                                baseMultiplierWeightMap
                                        ),
                                        new SpecialFeatureConfigCluster(),
                                        new ReadyHandConfigCluster(),
                                        new ProgressConfig(ConstMathSlot.ProgressType.CASCADE, new ProgressConfigExtendCascade(1, 1, 99)), //todo
                                        new EliminateConfig(ConstMathSlot.EliminationType.NORMAL, new EliminationConfigExtendNormal())
                                )),
                        ConstMathSlot.RoundCascadeGameType.DGRY_BASEGAME,
                        new RoundCascadeGameConfigExtendDgryBaseGame()
                ),
                ConstMathSlot.SlotDetailType.DGRY_BASEGAME
        );

        // 10.2 免費遊戲狀態設定
        GameStateConfig dgryFreeGameStateConfig = new GameStateConfig(
                ConstMathSlot.GameStateType.DGRY_FREEGAME,
                new GameStateConfigExtendDgryFreeGame(),
                new GameStateInputConfig(ConstMathSlot.GameStateInputType.NONE, new GameStateInputConfigExtendNone()),
                ConstMathSlot.RoundType.CASCADE,
                new RoundConfigCascade(
                        new ReelConfig(
                                ConstMathSlot.ReelType.REEL_DEPENDENT,
                                freeStripBoxList),
                        new InitialScreenConfig(
                                new ClientReelResult(0, ConstPgrSlot.ClientReelStopType.STOP_BY_DEPENDENT_REEL_BOX, new ClientReelStopResultExtendStopByDependentReelBox(new ArrayList<>() {
                                    {
                                        add(new StopBox(2, 1));
                                        add(new StopBox(2, 1));
                                        add(new StopBox(2, 1));
                                        add(new StopBox(2, 1));
                                        add(new StopBox(2, 1));
                                    }
                                })),
                                new FrameResult(ConstMathSlot.FrameType.FIX, new FrameResultExtendFix(new ArrayList<>() {
                                    {
                                        add(3);
                                        add(3);
                                        add(3);
                                        add(3);
                                        add(3);
                                    }
                                })),
                                ConstMathSlot.InitialScreenType.NONE,
                                new InitialScreenConfigExtendNone()),
                        new FrameConfig(
                                ConstMathSlot.FrameType.FIX,
                                new FrameConfigExtendFix(List.of(3, 3, 3, 3, 3))),
                        new SymbolConfig(
                                new ArrayList<>() {
                                    {
                                        add(ConstMathSlot.SymbolAttribute.WILD_01);
                                        add(ConstMathSlot.SymbolAttribute.FREE_GAME_01);
                                        add(ConstMathSlot.SymbolAttribute.M1);
                                        add(ConstMathSlot.SymbolAttribute.M2);
                                        add(ConstMathSlot.SymbolAttribute.M3);
                                        add(ConstMathSlot.SymbolAttribute.M4);
                                        add(ConstMathSlot.SymbolAttribute.M5);
                                        add(ConstMathSlot.SymbolAttribute.M6);
                                        add(ConstMathSlot.SymbolAttribute.M7);
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
                                        add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                                        add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                                        add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                                    }
                                }),
                        new DampConfig(ConstMathSlot.DampType.NO_DAMP, ConstMathSlot.DampType.NO_DAMP),
                        new GameConfig(ConstMathSlot.GameHitType.LINE_GAME, new GameHitConfigExtendLineGame(ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT, winLineList)),
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
                                                add(150);
                                                add(1000);
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
                                                add(15);
                                                add(100);
                                                add(500);
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
                                                add(10);
                                                add(50);
                                                add(200);
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
                                                add(10);
                                                add(30);
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
                                                add(5);
                                                add(15);
                                                add(75);
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
                                                add(5);
                                                add(10);
                                                add(30);
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
                                                add(10);
                                                add(30);
                                            }
                                        }
                                ));
                            }
                        }),
                        new ProgressConfig(ConstMathSlot.ProgressType.TRIGGER_ROUND,
                                new ProgressConfigExtendTriggerRound(
                                        new HashMap<>(){
                                            {
                                                put(ConstMathSlot.TriggerEvent.TRIGGER_01, 4);
                                                put(ConstMathSlot.TriggerEvent.TRIGGER_02, 5);
                                                put(ConstMathSlot.TriggerEvent.TRIGGER_03, 10);
                                                put(ConstMathSlot.TriggerEvent.TRIGGER_04, 20);
                                            }
                                        },
                                        new HashMap<>(){
                                            {

                                            }
                                        },
                                        99
                                )),
                        new SpecialFeatureConfigCluster(
                                new ArrayList<>(){
                                    {

                                    }
                                }
                        ),
                        new ReadyHandConfigCluster(List.of(
                                new ReadyHandConfig(ConstMathSlot.ReadyHandType.READY_HAND_01, ConstMathSlot.ReadyHandLimitType.NO_LIMIT)
                        )),
                        new CascadeClusterConfig(
                                ConstMathSlot.CascadeClusterType.DGRY,
                                new CascadeClusterConfigExtendDgry(),
                                new CascadeConfig(
                                        ConstMathSlot.CascadeType.DGRY,
                                        new CascadeConfigExtendDgry(
                                                new ReelConfig(
                                                        ConstMathSlot.ReelType.NON_REEL_WEIGHT,
                                                        freeCascadeStripBoxList),
                                                baseMultiplier,
                                                baseMultiplierWeightMap
                                        ),
                                        new SpecialFeatureConfigCluster(),
                                        new ReadyHandConfigCluster(),
                                        new ProgressConfig(ConstMathSlot.ProgressType.CASCADE, new ProgressConfigExtendCascade(1, 1, 99)), //todo
                                        new EliminateConfig(ConstMathSlot.EliminationType.NORMAL, new EliminationConfigExtendNormal())
                                )),
                        ConstMathSlot.RoundCascadeGameType.DGRY_FREEGAME,
                        new RoundCascadeGameConfigExtendDgryFreeGame(0, 0)
                ),
                ConstMathSlot.SlotDetailType.DGRY_FREEGAME
        );

        // 10.3 Buy Feature免費遊戲狀態設定
        GameStateConfig dgryFreeGameStateBuyFeatureConfig = new GameStateConfig(
                ConstMathSlot.GameStateType.DGRY_FREEGAME,
                new GameStateConfigExtendDgryFreeGame(),
                new GameStateInputConfig(ConstMathSlot.GameStateInputType.NONE, new GameStateInputConfigExtendNone()),
                ConstMathSlot.RoundType.CASCADE,
                new RoundConfigCascade(
                        new ReelConfig(
                                ConstMathSlot.ReelType.REEL_DEPENDENT,
                                freeBuyFeatureStripBoxList),
                        new InitialScreenConfig(
                                new ClientReelResult(0, ConstPgrSlot.ClientReelStopType.STOP_BY_DEPENDENT_REEL_BOX, new ClientReelStopResultExtendStopByDependentReelBox(new ArrayList<>() {
                                    {
                                        add(new StopBox(2, 1));
                                        add(new StopBox(2, 1));
                                        add(new StopBox(2, 1));
                                        add(new StopBox(2, 1));
                                        add(new StopBox(2, 1));
                                    }
                                })),
                                new FrameResult(ConstMathSlot.FrameType.FIX, new FrameResultExtendFix(new ArrayList<>() {
                                    {
                                        add(3);
                                        add(3);
                                        add(3);
                                        add(3);
                                        add(3);
                                    }
                                })),
                                ConstMathSlot.InitialScreenType.NONE,
                                new InitialScreenConfigExtendNone()),
                        new FrameConfig(
                                ConstMathSlot.FrameType.FIX,
                                new FrameConfigExtendFix(List.of(3, 3, 3, 3, 3))),
                        new SymbolConfig(
                                new ArrayList<>() {
                                    {
                                        add(ConstMathSlot.SymbolAttribute.WILD_01);
                                        add(ConstMathSlot.SymbolAttribute.FREE_GAME_01);
                                        add(ConstMathSlot.SymbolAttribute.M1);
                                        add(ConstMathSlot.SymbolAttribute.M2);
                                        add(ConstMathSlot.SymbolAttribute.M3);
                                        add(ConstMathSlot.SymbolAttribute.M4);
                                        add(ConstMathSlot.SymbolAttribute.M5);
                                        add(ConstMathSlot.SymbolAttribute.M6);
                                        add(ConstMathSlot.SymbolAttribute.M7);
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
                                        add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                                        add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                                        add(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL);
                                    }
                                }),
                        new DampConfig(ConstMathSlot.DampType.NO_DAMP, ConstMathSlot.DampType.NO_DAMP),
                        new GameConfig(ConstMathSlot.GameHitType.LINE_GAME, new GameHitConfigExtendLineGame(ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT, winLineList)),
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
                                                add(150);
                                                add(1000);
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
                                                add(15);
                                                add(100);
                                                add(500);
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
                                                add(10);
                                                add(50);
                                                add(200);
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
                                                add(10);
                                                add(30);
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
                                                add(5);
                                                add(15);
                                                add(75);
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
                                                add(5);
                                                add(10);
                                                add(30);
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
                                                add(10);
                                                add(30);
                                            }
                                        }
                                ));
                            }
                        }),
                        new ProgressConfig(ConstMathSlot.ProgressType.TRIGGER_ROUND,
                                new ProgressConfigExtendTriggerRound(
                                        new HashMap<>(){
                                            {
                                                put(ConstMathSlot.TriggerEvent.TRIGGER_01, 10); //todo
                                            }
                                        },
                                        new HashMap<>(){},
                                        10
                                )),
                        new SpecialFeatureConfigCluster(
                                new ArrayList<>(){
                                    {

                                    }
                                }
                        ),
                        new ReadyHandConfigCluster(List.of(
                                new ReadyHandConfig(ConstMathSlot.ReadyHandType.READY_HAND_01, ConstMathSlot.ReadyHandLimitType.NO_LIMIT)
                        )),
                        new CascadeClusterConfig(
                                ConstMathSlot.CascadeClusterType.DGRY,
                                new CascadeClusterConfigExtendDgry(),
                                new CascadeConfig(
                                        ConstMathSlot.CascadeType.DGRY,
                                        new CascadeConfigExtendDgry(
                                                new ReelConfig(
                                                        ConstMathSlot.ReelType.NON_REEL_WEIGHT,
                                                        freeBuyFeatureCascadeStripBoxList),
                                                buyFeature01Multiplier,
                                                buyFeature01MultiplierWeightMap
                                        ),
                                        new SpecialFeatureConfigCluster(),
                                        new ReadyHandConfigCluster(),
                                        new ProgressConfig(ConstMathSlot.ProgressType.CASCADE, new ProgressConfigExtendCascade(1, 1, 99)), //todo
                                        new EliminateConfig(ConstMathSlot.EliminationType.NORMAL, new EliminationConfigExtendNormal())
                                )),
                        ConstMathSlot.RoundCascadeGameType.DGRY_FREEGAME,
                        new RoundCascadeGameConfigExtendDgryFreeGame(0, 0)
                ),
                ConstMathSlot.SlotDetailType.DGRY_FREEGAME
        );

        // 10.4 Bonus Game 狀態設定
        GameStateConfig dgryBonusGameStateConfig = new GameStateConfig(
                ConstMathSlot.GameStateType.DGRY_BONUSGAME,
                new GameStateConfigExtendDgryBonusGame(
                        bonusGameInsertDisplayList,
                        bonusGameInsertDisplayWeightList,
                        bonusGameNoDamageDisplayList,
                        bonusGameNoDamageDisplayWeightMap,
                        NoDamageDisplayCountList,
                        NoDamageDisplayCountWeightMap,
                        bonusGameTakeDamageDisplayList,
                        bonusGameTakeDamageDisplayWeightMap,
                        baseMultiplier,
                        baseMultiplierWeightMap,
                        displayTimesRange,
                        lifePoint),
                new GameStateInputConfig(ConstMathSlot.GameStateInputType.NONE, new GameStateInputConfigExtendNone()),
                ConstMathSlot.RoundType.CASCADE,
                new RoundConfigCascade(
                        new ReelConfig(
                                ConstMathSlot.ReelType.NONE,
                                new ArrayList<>()),
                        new InitialScreenConfig(
                                new ClientReelResult(0, ConstPgrSlot.ClientReelStopType.NO_SCREEN, new ClientReelStopResultExtendStopByDependentReelBox(new ArrayList<>())),
                                new FrameResult(ConstMathSlot.FrameType.NO_SCREEN, new FrameResultExtendFix(new ArrayList<>())),
                                ConstMathSlot.InitialScreenType.NONE,
                                new InitialScreenConfigExtendNone()),
                        new FrameConfig(
                                ConstMathSlot.FrameType.NO_SCREEN,
                                new FrameConfigExtendFix(List.of())),
                        new SymbolConfig(
                                new ArrayList<>(),
                                new ArrayList<>()),
                        new DampConfig(ConstMathSlot.DampType.NO_DAMP, ConstMathSlot.DampType.NO_DAMP),
                        new GameConfig(ConstMathSlot.GameHitType.LINE_GAME, new GameHitConfigExtendLineGame(ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT, new ArrayList<>())),
                        new PayTableConfig(new ArrayList<>()),
                        new ProgressConfig(ConstMathSlot.ProgressType.TRIGGER_ROUND,
                                new ProgressConfigExtendTriggerRound(
                                        new HashMap<>(){
                                            {
                                                put(ConstMathSlot.TriggerEvent.TRIGGER_06, 1);

                                            }
                                        },
                                        new HashMap<>(){},
                                        1
                                )),
                        new SpecialFeatureConfigCluster(
                                new ArrayList<>()
                        ),
                        new ReadyHandConfigCluster(List.of()),
                        new CascadeClusterConfig(
                                ConstMathSlot.CascadeClusterType.DGRY,
                                new CascadeClusterConfigExtendDgry(),
                                new CascadeConfig(
                                        ConstMathSlot.CascadeType.NONE,
                                        new CascadeConfigExtendDgry(
                                                new ReelConfig(
                                                        ConstMathSlot.ReelType.NON_REEL_WEIGHT,
                                                        freeBuyFeatureCascadeStripBoxList),
                                                baseMultiplier,
                                                baseMultiplierWeightMap
                                        ),
                                        new SpecialFeatureConfigCluster(),
                                        new ReadyHandConfigCluster(),
                                        new ProgressConfig(ConstMathSlot.ProgressType.CASCADE, new ProgressConfigExtendCascade(1, 1, 1)),
                                        new EliminateConfig(ConstMathSlot.EliminationType.NORMAL, new EliminationConfigExtendNormal())
                                )),
                        ConstMathSlot.RoundCascadeGameType.DGRY_BONUSGAME,
                        new RoundCascadeGameConfigExtendDgryBonusGame()
                ),
                ConstMathSlot.SlotDetailType.DGRY_BONUSGAME
        );

        // 10.5 Buy Feature Bonus Game 狀態設定
        GameStateConfig dgryBonusGameStateBuyFeatureConfig = new GameStateConfig(
                ConstMathSlot.GameStateType.DGRY_BONUSGAME,
                new GameStateConfigExtendDgryBonusGame(
                        bonusGameInsertDisplayList,
                        bonusGameInsertDisplayWeightList,
                        bonusGameNoDamageDisplayList,
                        bonusGameNoDamageDisplayWeightMap,
                        NoDamageDisplayCountList,
                        NoDamageDisplayCountWeightMap,
                        bonusGameTakeDamageDisplayList,
                        bonusGameTakeDamageDisplayWeightMap,
                        buyFeature02Multiplier,
                        buyFeature02MultiplierWeightMap,
                        displayTimesRange,
                        lifePoint),
                new GameStateInputConfig(ConstMathSlot.GameStateInputType.NONE, new GameStateInputConfigExtendNone()),
                ConstMathSlot.RoundType.CASCADE,
                new RoundConfigCascade(
                        new ReelConfig(
                                ConstMathSlot.ReelType.NONE,
                                new ArrayList<>()),
                        new InitialScreenConfig(
                                new ClientReelResult(0, ConstPgrSlot.ClientReelStopType.NO_SCREEN, new ClientReelStopResultExtendStopByDependentReelBox(new ArrayList<>())),
                                new FrameResult(ConstMathSlot.FrameType.NO_SCREEN, new FrameResultExtendFix(new ArrayList<>())),
                                ConstMathSlot.InitialScreenType.NONE,
                                new InitialScreenConfigExtendNone()),
                        new FrameConfig(
                                ConstMathSlot.FrameType.NO_SCREEN,
                                new FrameConfigExtendFix(List.of())),
                        new SymbolConfig(
                                new ArrayList<>(),
                                new ArrayList<>()),
                        new DampConfig(ConstMathSlot.DampType.NO_DAMP, ConstMathSlot.DampType.NO_DAMP),
                        new GameConfig(ConstMathSlot.GameHitType.LINE_GAME, new GameHitConfigExtendLineGame(ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT, new ArrayList<>())),
                        new PayTableConfig(new ArrayList<>()),
                        new ProgressConfig(ConstMathSlot.ProgressType.TRIGGER_ROUND,
                                new ProgressConfigExtendTriggerRound(
                                        new HashMap<>(){
                                            {

                                            }
                                        },
                                        new HashMap<>(){},
                                        1
                                )),
                        new SpecialFeatureConfigCluster(
                                new ArrayList<>()
                        ),
                        new ReadyHandConfigCluster(List.of()),
                        new CascadeClusterConfig(
                                ConstMathSlot.CascadeClusterType.DGRY,
                                new CascadeClusterConfigExtendDgry(),
                                new CascadeConfig(
                                        ConstMathSlot.CascadeType.NONE,
                                        new CascadeConfigExtendDgry(
                                                new ReelConfig(
                                                        ConstMathSlot.ReelType.NON_REEL_WEIGHT,
                                                        freeBuyFeatureCascadeStripBoxList),
                                                baseMultiplier,
                                                baseMultiplierWeightMap
                                        ),
                                        new SpecialFeatureConfigCluster(),
                                        new ReadyHandConfigCluster(),
                                        new ProgressConfig(ConstMathSlot.ProgressType.CASCADE, new ProgressConfigExtendCascade(1, 1, 1)),
                                        new EliminateConfig(ConstMathSlot.EliminationType.NORMAL, new EliminationConfigExtendNormal())
                                )),
                        ConstMathSlot.RoundCascadeGameType.DGRY_BONUSGAME,
                        new RoundCascadeGameConfigExtendDgryBonusGame()
                ),
                ConstMathSlot.SlotDetailType.DGRY_BONUSGAME
        );

        // 11. 設定遊戲狀態設定集合
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
                List.of(dgryBaseGameStateConfig, dgryFreeGameStateConfig, dgryBonusGameStateConfig, dgryFreeGameStateBuyFeatureConfig, dgryBonusGameStateBuyFeatureConfig)
        );

        // 12. 遊戲流程設定
        // 12.1 預設狀態轉移設定
        ConstMathSlot.Condition targetConditionBuyFeature01 = ConstMathSlot.Condition.CD_BUY_FEATURE_01;
        ConstMathSlot.Condition targetConditionBuyFeature02 = ConstMathSlot.Condition.CD_BUY_FEATURE_02;
        List<ConstMathSlot.Condition> InitialConditionTransformList = List.of(ConstMathSlot.Condition.CD_FALSE, ConstMathSlot.Condition.CD_BASIC_BET, ConstMathSlot.Condition.CD_FALSE, ConstMathSlot.Condition.CD_FALSE, targetConditionBuyFeature01, targetConditionBuyFeature02);

        // 12.2 基礎遊戲轉移設定
        ConstMathSlot.Condition targetConditionFreeGame = ConstMathSlot.Condition.CD_01;
        ConstMathSlot.Condition targetConditionBonusGame = ConstMathSlot.Condition.CD_02;
        List<ConstMathSlot.Condition> BaseConditionTransformList = List.of(ConstMathSlot.Condition.CD_FALSE, ConstMathSlot.Condition.CD_FALSE, targetConditionFreeGame, targetConditionBonusGame, ConstMathSlot.Condition.CD_FALSE, ConstMathSlot.Condition.CD_FALSE);

        // 12.3 定義無轉移目標陣列
        List<ConstMathSlot.Condition> allFalseTransformList = List.of(ConstMathSlot.Condition.CD_FALSE, ConstMathSlot.Condition.CD_FALSE, ConstMathSlot.Condition.CD_FALSE, ConstMathSlot.Condition.CD_FALSE, ConstMathSlot.Condition.CD_FALSE, ConstMathSlot.Condition.CD_FALSE);

        // 12.4 遊戲流程轉移設定
        GameFlowConfig gameFlowConfig = new GameFlowConfig(
                new ArrayList<>(){
                    {
                        add(InitialConditionTransformList);
                        add(BaseConditionTransformList);
                        add(allFalseTransformList);
                        add(allFalseTransformList);
                        add(allFalseTransformList);
                        add(allFalseTransformList);
                    }
                }
        );

        // 13. 動畫設定
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