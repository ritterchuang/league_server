package org.lsj.gs.math.games.dydb_java.module.logic.slotDetailCtr;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultNormal;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.roundResultExtend.RoundHlrResultExtend;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.roundResultExtend.RoundHlrResultExtendDefault;
import org.lsj.gs.math.core.common.table.entity.message.slot.betSpinTypeExtend.BetSpinTypeExtendNoneNormal;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtrResult;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.commonInputHlr.enity.result.CommonInputResult;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.GameCtrResult;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.way.GameCtrResultExtendWay;
import org.lsj.gs.math.core.slot.gameStateInputHlrMgr.enity.result.GameStateInputResult;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfigExtendFix;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfigExtendReelDependent;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelStripBox;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResultExtendTriggerRound;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.RoundProgress;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResultUnionCluster;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResultExtendFix;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.DampCtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.ReelCtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.ReelStopResultExtendStopByDependentReelIndex;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetail;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetailHlrConfig;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;
import org.lsj.gs.math.games.dydb_java.enity.ConstDydbJava;
import org.lsj.gs.math.games.dydb_java.enity.config.SlotDetailHlrConfigExtendDydbFreeGame;
import org.lsj.gs.math.games.dydb_java.enity.result.gameStateResult.GameStateHlrResultExtendDydbFreeGame;
import org.lsj.gs.math.games.dydb_java.enity.result.roundResult.RoundHlrResultExtendDydbFreeGame;
import org.lsj.gs.math.games.dydb_java.enity.result.slotDetail.SlotDetailDydbFreeGame;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 大運奪寶免費遊戲詳細記錄處理者測試
@ExtendWith(MockitoExtension.class)
class SlotDetailHlrDydbFreeGameTest {
    SlotDetailHlrDydbFreeGame slotDetailHlrDydbFreeGame; // 大運奪寶免費遊戲詳細記錄處理者
    @Mock
    ITableUtil mockTableUtil;

    // 給定結果有觸發倍數矩陣，計算詳細記錄，會帶出倍數資訊
    @Test
    void test_given_triggerMultiplierMatrix_when_calculateSlotDetail_then_correct() {
        // 1. given
        SlotDetailHlrConfig config = this.generateSlotDetailHlrConfig();
        this.slotDetailHlrDydbFreeGame = new SlotDetailHlrDydbFreeGame(config, mockTableUtil);
        GameStateHlrResult gameStateHlrResult = this.generateGameStateHlrResult(ConstMathSlot.RoundNormalGameType.DYDB_FREEGAME);
        SpinRequest spinRequest = this.generateSpinRequest();

        // 2. when
        List<SlotDetail> actualResult = this.slotDetailHlrDydbFreeGame.calculateSlotDetail(spinRequest, gameStateHlrResult);
        List<SlotDetail> expectResult = new ArrayList<>(){
            {
                add(new SlotDetailDydbFreeGame(
                        3,
                        15,
                        0.0,
                        "凤凰,鲤鱼,摇钱树,ACE", "狮子,狮子,狮子,鞭炮", "凤凰,鲤鱼,摇钱树,ACE", "凤凰,鲤鱼,摇钱树,ACE","凤凰,鲤鱼,摇钱树,ACE",
                        "0,3,0,0","0,0,0,0","0,0,2,0"

                ));
            }
        };

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定結果沒有觸發倍數矩陣，計算詳細記錄，背書資訊皆為0
    @Test
    void test_given_noTriggerMultiplierMatrix_when_calculateSlotDetail_then_correct() {
        // 1. given
        SlotDetailHlrConfig config = this.generateSlotDetailHlrConfig();
        this.slotDetailHlrDydbFreeGame = new SlotDetailHlrDydbFreeGame(config, mockTableUtil);
        GameStateHlrResult gameStateHlrResult = this.generateGameStateHlrResult(ConstMathSlot.RoundNormalGameType.DEFAULT);
        SpinRequest spinRequest = this.generateSpinRequest();

        // 2. when
        List<SlotDetail> actualResult = this.slotDetailHlrDydbFreeGame.calculateSlotDetail(spinRequest, gameStateHlrResult);
        List<SlotDetail> expectResult = new ArrayList<>(){
            {
                add(new SlotDetailDydbFreeGame(
                        3,
                        15,
                        0.0,
                        "凤凰,鲤鱼,摇钱树,ACE", "狮子,狮子,狮子,鞭炮", "凤凰,鲤鱼,摇钱树,ACE", "凤凰,鲤鱼,摇钱树,ACE","凤凰,鲤鱼,摇钱树,ACE",
                        "0,0,0,0","0,0,0,0","0,0,0,0"

                ));
            }
        };

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 產出虎機詳細記錄處理者設定
    private SlotDetailHlrConfig generateSlotDetailHlrConfig() {
        return new SlotDetailHlrConfig(
                ConstMathSlot.SlotDetailType.DYDB_FREEGAME,
                new SlotDetailHlrConfigExtendDydbFreeGame(
                        ConstDydbJava.SymbolEnumDydbJava.getSymbolIdStringMap(),
                        new FrameConfig(ConstMathSlot.FrameType.FIX, new FrameConfigExtendFix(List.of(4,4,4,4,4))),
                        new ReelConfig(
                                ConstMathSlot.ReelType.REEL_DEPENDENT,
                                List.of(
                                        new ReelStripBox(
                                                0,
                                                1,
                                                new ReelConfigExtendReelDependent(
                                                    List.of(
                                                            List.of(2,2,2,0,3,4,5,6,7,8,9,10),
                                                            List.of(2,2,2,0,3,4,5,6,7,8,9,10),
                                                            List.of(2,2,2,0,3,4,5,6,7,8,9,10),
                                                            List.of(2,2,2,0,3,4,5,6,7,8,9,10),
                                                            List.of(2,2,2,0,3,4,5,6,7,8,9,10)
                                                    )
                                                ),
                                                ConstMathSlot.ReelRtpType.HIGH,
                                                ConstMathSlot.ReelStopType.STOP_BY_DEPENDENT_REEL_INDEX))
                                ),
                        new SymbolConfig(
                                List.of(
                                        ConstMathSlot.SymbolAttribute.WILD_01,
                                        ConstMathSlot.SymbolAttribute.FREE_GAME_01,
                                        ConstMathSlot.SymbolAttribute.M1,
                                        ConstMathSlot.SymbolAttribute.M2,
                                        ConstMathSlot.SymbolAttribute.M3,
                                        ConstMathSlot.SymbolAttribute.M4,
                                        ConstMathSlot.SymbolAttribute.ACE,
                                        ConstMathSlot.SymbolAttribute.KING,
                                        ConstMathSlot.SymbolAttribute.QUEEN,
                                        ConstMathSlot.SymbolAttribute.JOKER,
                                        ConstMathSlot.SymbolAttribute.TEN
                                ),
                                List.of(
                                        ConstMathSlot.SymbolAttributeType.WILD_SYMBOL,
                                        ConstMathSlot.SymbolAttributeType.FREE_GAME_SYMBOL,
                                        ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL,
                                        ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL,
                                        ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL,
                                        ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL,
                                        ConstMathSlot.SymbolAttributeType.BASE_SYMBOL,
                                        ConstMathSlot.SymbolAttributeType.BASE_SYMBOL,
                                        ConstMathSlot.SymbolAttributeType.BASE_SYMBOL,
                                        ConstMathSlot.SymbolAttributeType.BASE_SYMBOL,
                                        ConstMathSlot.SymbolAttributeType.BASE_SYMBOL
                                )
                        )
                        ));
    }

    // 產出押注請求
    private SpinRequest generateSpinRequest() {
        return new SpinRequest(1.0, 50.0, ConstMathSlot.BetType.NONE, ConstMathSlot.SpinType.NORMAL, new BetSpinTypeExtendNoneNormal());
    }

    // 產出遊戲狀態結果
    private GameStateHlrResult generateGameStateHlrResult(ConstMathSlot.RoundNormalGameType roundNormalGameType) {
        return new GameStateHlrResult(
                2,
                1,
                1,
                0.0,
                ConstMathSlot.GameStateType.DYDB_FREEGAME,
                new GameStateHlrResultExtendDydbFreeGame(),
                ConstMathSlot.RoundType.NORMAL,
                List.of(
                        new RoundHlrResultNormal(
                                0,
                                0.0,
                                new SpecialFeatureHlrResultCluster(),
                                new ProgressHlrResult(
                                        false,
                                        ConstMathSlot.ProgressType.TRIGGER_ROUND,
                                        new ProgressHlrResultExtendTriggerRound(new RoundProgress(3, 0, 15))),
                                new ReadyHandHlrResultUnionCluster(),
                                new AccumulateWinCtrResult(),
                                new ScreenGtrResult(
                                        List.of(
                                                List.of(3,4,5,6),
                                                List.of(2,2,2,0),
                                                List.of(3,4,5,6),
                                                List.of(3,4,5,6),
                                                List.of(3,4,5,6)
                                        ),
                                        new FrameResult(ConstMathSlot.FrameType.FIX, new FrameResultExtendFix(List.of(4,4,4,4,4))),
                                        new ReelCtrResult(0, ConstMathSlot.ReelStopType.STOP_BY_DEPENDENT_REEL_INDEX, new ReelStopResultExtendStopByDependentReelIndex(new DampCtrResult(), List.of(7,3,7,7,7)))
                                 ),
                                new GameCtrResult(0.0, ConstMathSlot.GameHitType.WAY_GAME, new GameCtrResultExtendWay(ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT)),
                                roundNormalGameType,
                                this.generateRoundHlrResultExtend(roundNormalGameType)
                                )
                ),
                new CommonInputResult(),
                new GameStateInputResult()
        );
    }

    // 產出遊戲額外結果
    private RoundHlrResultExtend generateRoundHlrResultExtend(ConstMathSlot.RoundNormalGameType roundNormalGameType) {
        if (roundNormalGameType.equals(ConstMathSlot.RoundNormalGameType.DEFAULT)) {
            return new RoundHlrResultExtendDefault();
        }

        return new RoundHlrResultExtendDydbFreeGame(List.of(
                List.of(0,0,0,0),
                List.of(0,3,0,0),
                List.of(0,0,0,0),
                List.of(0,0,2,0),
                List.of(0,0,0,0)
        ));
    }
}