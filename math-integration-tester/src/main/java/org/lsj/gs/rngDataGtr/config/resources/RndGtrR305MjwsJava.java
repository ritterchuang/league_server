package org.lsj.gs.rngDataGtr.config.resources;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.mathBoardGtr.config.entity.ConstBoardGtr;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.ConstPlayGameField;
import org.lsj.gs.mathBoardGtr.config.module.BoardGtrConfigFactory;
import org.lsj.gs.rngDataGtr.config.entity.RngDataGtrConfig;
import org.lsj.gs.rngDataGtr.config.entity.RngDataScenarioHlrConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.RngDataTesterConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigESlotCascadeWayGame;
import org.lsj.gs.rngDataGtr.core.entity.ConstRngDataGtr;

import java.util.ArrayList;

// 亂數產生器設定 305 麻將無雙
public class RndGtrR305MjwsJava extends AbstractRndGtrR {
    public RngDataGtrConfig create(){
        return new RngDataGtrConfig(
                new RngDataScenarioHlrConfig(10_000_000),
                new BoardGtrConfigFactory().create(ConstPlayGameField.PlayGameFieldResource.R305_MJWS_JAVA_NORMAL, ConstBoardGtr.BoardGtrControlAlgorithmType.NATURE),
                new RngDataTesterConfig(
                        new ArrayList<>(){{
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(0, 3, ConstMathSlot.GameStateType.MJWS_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(0, 4, ConstMathSlot.GameStateType.MJWS_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(0, 5, ConstMathSlot.GameStateType.MJWS_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(1, 3, ConstMathSlot.GameStateType.MJWS_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(1, 4, ConstMathSlot.GameStateType.MJWS_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(1, 5, ConstMathSlot.GameStateType.MJWS_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(2, 3, ConstMathSlot.GameStateType.MJWS_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(2, 4, ConstMathSlot.GameStateType.MJWS_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(2, 5, ConstMathSlot.GameStateType.MJWS_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(3, 3, ConstMathSlot.GameStateType.MJWS_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(3, 4, ConstMathSlot.GameStateType.MJWS_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(3, 5, ConstMathSlot.GameStateType.MJWS_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(4, 3, ConstMathSlot.GameStateType.MJWS_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(4, 4, ConstMathSlot.GameStateType.MJWS_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(4, 5, ConstMathSlot.GameStateType.MJWS_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(5, 3, ConstMathSlot.GameStateType.MJWS_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(5, 4, ConstMathSlot.GameStateType.MJWS_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(5, 5, ConstMathSlot.GameStateType.MJWS_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(6, 3, ConstMathSlot.GameStateType.MJWS_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(6, 4, ConstMathSlot.GameStateType.MJWS_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(6, 5, ConstMathSlot.GameStateType.MJWS_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(7, 3, ConstMathSlot.GameStateType.MJWS_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(7, 4, ConstMathSlot.GameStateType.MJWS_BASEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(7, 5, ConstMathSlot.GameStateType.MJWS_BASEGAME)));

                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(0, 3, ConstMathSlot.GameStateType.MJWS_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(0, 4, ConstMathSlot.GameStateType.MJWS_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(0, 5, ConstMathSlot.GameStateType.MJWS_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(1, 3, ConstMathSlot.GameStateType.MJWS_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(1, 4, ConstMathSlot.GameStateType.MJWS_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(1, 5, ConstMathSlot.GameStateType.MJWS_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(2, 3, ConstMathSlot.GameStateType.MJWS_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(2, 4, ConstMathSlot.GameStateType.MJWS_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(2, 5, ConstMathSlot.GameStateType.MJWS_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(3, 3, ConstMathSlot.GameStateType.MJWS_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(3, 4, ConstMathSlot.GameStateType.MJWS_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(3, 5, ConstMathSlot.GameStateType.MJWS_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(4, 3, ConstMathSlot.GameStateType.MJWS_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(4, 4, ConstMathSlot.GameStateType.MJWS_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(4, 5, ConstMathSlot.GameStateType.MJWS_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(5, 3, ConstMathSlot.GameStateType.MJWS_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(5, 4, ConstMathSlot.GameStateType.MJWS_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(5, 5, ConstMathSlot.GameStateType.MJWS_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(6, 3, ConstMathSlot.GameStateType.MJWS_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(6, 4, ConstMathSlot.GameStateType.MJWS_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(6, 5, ConstMathSlot.GameStateType.MJWS_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(7, 3, ConstMathSlot.GameStateType.MJWS_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(7, 4, ConstMathSlot.GameStateType.MJWS_FREEGAME)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_CASCADE_WAY_GAME, new TestCaseConfigESlotCascadeWayGame(7, 5, ConstMathSlot.GameStateType.MJWS_FREEGAME)));
                        }}
                )
        );
    }
}
