package org.lsj.gs.rngDataGtr.config.resources;

import org.lsj.gs.math.core.baiRen.ConstMathBjl;
import org.lsj.gs.mathBoardGtr.config.entity.ConstBoardGtr;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.ConstPlayGameField;
import org.lsj.gs.mathBoardGtr.config.module.BoardGtrConfigFactory;
import org.lsj.gs.rngDataGtr.config.entity.RngDataGtrConfig;
import org.lsj.gs.rngDataGtr.config.entity.RngDataScenarioHlrConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.RngDataTesterConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigEBaiRenBjBet;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigEBaiRenBjDeal;
import org.lsj.gs.rngDataGtr.core.entity.ConstRngDataGtr;

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
