package com.lx.gs.rngDataGtr.config.resources;

import com.lx.gs.math.core.baiRen.ConstMathBjl;
import com.lx.gs.mathBoardGtr.config.entity.ConstBoardGtr;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.ConstPlayGameField;
import com.lx.gs.mathBoardGtr.config.module.BoardGtrConfigFactory;
import com.lx.gs.rngDataGtr.config.entity.RngDataGtrConfig;
import com.lx.gs.rngDataGtr.config.entity.RngDataScenarioHlrConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.RngDataTesterConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigEBaiRenBjBet;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigEBaiRenBjDeal;
import com.lx.gs.rngDataGtr.core.entity.ConstRngDataGtr;

import java.util.ArrayList;
import java.util.List;

// 亂數產生器設定 新百家樂
public class RndGtrR109BjlJava extends AbstractRndGtrR {
    public RngDataGtrConfig create(){
        return new RngDataGtrConfig(
                new RngDataScenarioHlrConfig(1000000),
                new BoardGtrConfigFactory().create(ConstPlayGameField.PlayGameFieldResource.R109_BJL_JAVA_NORMAL, ConstBoardGtr.BoardGtrControlAlgorithmType.GRADIENT_NATURE),
                new RngDataTesterConfig(
                        new ArrayList<>(){{
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BAIREN_BJ_JAVA_SPECIFY_BET, new TestCaseConfigEBaiRenBjBet(1, List.of(ConstMathBjl.BetAreaEnum.BANK), List.of(ConstMathBjl.BetAreaEnum.UPBANK_LOSE))));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BAIREN_BJ_JAVA_DEAL, new TestCaseConfigEBaiRenBjDeal(ConstMathBjl.DealAreaEnum.BANK, 2, 8)));
                        }}
                )
        );
    }
}
