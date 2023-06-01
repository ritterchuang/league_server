package com.lx.gs.rngDataGtr.config.resources;

import com.lx.gs.math.core.slot.ConstMathSlot;
import com.lx.gs.mathBoardGtr.config.entity.ConstBoardGtr;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.ConstPlayGameField;
import com.lx.gs.mathBoardGtr.config.module.BoardGtrConfigFactory;
import com.lx.gs.rngDataGtr.config.entity.RngDataGtrConfig;
import com.lx.gs.rngDataGtr.config.entity.RngDataScenarioHlrConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.RngDataTesterConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigESlotOddsWinType;
import com.lx.gs.rngDataGtr.core.entity.ConstRngDataGtr;

import java.util.ArrayList;

// 亂數產生器設定 302 大運奪寶
public class RndGtrR302DydbJava extends AbstractRndGtrR {
    public RngDataGtrConfig create(){
        return new RngDataGtrConfig(
                new RngDataScenarioHlrConfig(1000000),
                new BoardGtrConfigFactory().create(ConstPlayGameField.PlayGameFieldResource.R302_DYDB_JAVA_NORMAL, ConstBoardGtr.BoardGtrControlAlgorithmType.NATURE),
                new RngDataTesterConfig(
                        new ArrayList<>(){{
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_ODDS_WIN_TYPE, new TestCaseConfigESlotOddsWinType(ConstMathSlot.OddsWinType.NO_WIN)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_ODDS_WIN_TYPE, new TestCaseConfigESlotOddsWinType(ConstMathSlot.OddsWinType.REGULAR_WIN)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_ODDS_WIN_TYPE, new TestCaseConfigESlotOddsWinType(ConstMathSlot.OddsWinType.BIG_WIN)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_ODDS_WIN_TYPE, new TestCaseConfigESlotOddsWinType(ConstMathSlot.OddsWinType.MEGA_WIN)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_SLOT_ODDS_WIN_TYPE, new TestCaseConfigESlotOddsWinType(ConstMathSlot.OddsWinType.ULTRA_WIN)));
                        }}
                )
        );
    }
}
