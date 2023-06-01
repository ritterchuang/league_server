package org.lsj.gs.rngDataGtr.config.resources;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.mathBoardGtr.config.entity.ConstBoardGtr;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.ConstPlayGameField;
import org.lsj.gs.mathBoardGtr.config.module.BoardGtrConfigFactory;
import org.lsj.gs.rngDataGtr.config.entity.RngDataGtrConfig;
import org.lsj.gs.rngDataGtr.config.entity.RngDataScenarioHlrConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.RngDataTesterConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.*;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.*;
import org.lsj.gs.rngDataGtr.core.entity.ConstRngDataGtr;

import java.util.ArrayList;

// 亂數產生器設定 312 帝国榮耀
public class RndGtrR312DgryJava extends AbstractRndGtrR {
    public RngDataGtrConfig create(){
        return new RngDataGtrConfig(
                new RngDataScenarioHlrConfig(10_000_000),
                new BoardGtrConfigFactory().create(ConstPlayGameField.PlayGameFieldResource.R312_DGRY_JAVA_NORMAL, ConstBoardGtr.BoardGtrControlAlgorithmType.NATURE),
                new RngDataTesterConfig(
                        new ArrayList<>(){{
                            
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(0, 3, ConstMathSlot.GameStateType.DGRY_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(0, 4, ConstMathSlot.GameStateType.DGRY_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(0, 5, ConstMathSlot.GameStateType.DGRY_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(1, 3, ConstMathSlot.GameStateType.DGRY_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(1, 4, ConstMathSlot.GameStateType.DGRY_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(1, 5, ConstMathSlot.GameStateType.DGRY_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(2, 3, ConstMathSlot.GameStateType.DGRY_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(2, 4, ConstMathSlot.GameStateType.DGRY_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(2, 5, ConstMathSlot.GameStateType.DGRY_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(3, 3, ConstMathSlot.GameStateType.DGRY_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(3, 4, ConstMathSlot.GameStateType.DGRY_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(3, 5, ConstMathSlot.GameStateType.DGRY_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(4, 3, ConstMathSlot.GameStateType.DGRY_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(4, 4, ConstMathSlot.GameStateType.DGRY_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(4, 5, ConstMathSlot.GameStateType.DGRY_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(5, 3, ConstMathSlot.GameStateType.DGRY_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(5, 4, ConstMathSlot.GameStateType.DGRY_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(5, 5, ConstMathSlot.GameStateType.DGRY_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(6, 3, ConstMathSlot.GameStateType.DGRY_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(6, 4, ConstMathSlot.GameStateType.DGRY_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(6, 5, ConstMathSlot.GameStateType.DGRY_BASEGAME)));


                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(0, 3, ConstMathSlot.GameStateType.DGRY_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(0, 4, ConstMathSlot.GameStateType.DGRY_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(0, 5, ConstMathSlot.GameStateType.DGRY_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(1, 3, ConstMathSlot.GameStateType.DGRY_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(1, 4, ConstMathSlot.GameStateType.DGRY_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(1, 5, ConstMathSlot.GameStateType.DGRY_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(2, 3, ConstMathSlot.GameStateType.DGRY_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(2, 4, ConstMathSlot.GameStateType.DGRY_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(2, 5, ConstMathSlot.GameStateType.DGRY_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(3, 3, ConstMathSlot.GameStateType.DGRY_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(3, 4, ConstMathSlot.GameStateType.DGRY_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(3, 5, ConstMathSlot.GameStateType.DGRY_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(4, 3, ConstMathSlot.GameStateType.DGRY_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(4, 4, ConstMathSlot.GameStateType.DGRY_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(4, 5, ConstMathSlot.GameStateType.DGRY_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(5, 3, ConstMathSlot.GameStateType.DGRY_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(5, 4, ConstMathSlot.GameStateType.DGRY_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(5, 5, ConstMathSlot.GameStateType.DGRY_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(6, 3, ConstMathSlot.GameStateType.DGRY_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(6, 4, ConstMathSlot.GameStateType.DGRY_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_LINE_GAME, new TestCaseConfigESlotCascadeLineGame(6, 5, ConstMathSlot.GameStateType.DGRY_FREEGAME)));

                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_TIMES, new TestCaseConfigESlotCascadeTimes(4, ConstMathSlot.GameStateType.DGRY_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_TIMES, new TestCaseConfigESlotCascadeTimes(5, ConstMathSlot.GameStateType.DGRY_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_TIMES, new TestCaseConfigESlotCascadeTimes(6, ConstMathSlot.GameStateType.DGRY_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_TIMES, new TestCaseConfigESlotCascadeTimes(7, ConstMathSlot.GameStateType.DGRY_BASEGAME)));

                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_ODDS_WIN_TYPE, new TestCaseConfigESlotOddsWinType(ConstMathSlot.OddsWinType.BIG_WIN)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_ODDS_WIN_TYPE, new TestCaseConfigESlotOddsWinType(ConstMathSlot.OddsWinType.MEGA_WIN)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_ODDS_WIN_TYPE, new TestCaseConfigESlotOddsWinType(ConstMathSlot.OddsWinType.ULTRA_WIN)));

                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_GAMESTATE, new TestCaseConfigESlotCascadeGameState(ConstMathSlot.GameStateType.DGRY_BONUSGAME)));
                        }}
                )
        );
    }
}
