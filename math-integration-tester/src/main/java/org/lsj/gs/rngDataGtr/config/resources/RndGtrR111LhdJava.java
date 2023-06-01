package org.lsj.gs.rngDataGtr.config.resources;

import org.lsj.gs.math.games.lhd_java.entity.ConstLhdJava;
import org.lsj.gs.mathBoardGtr.config.entity.ConstBoardGtr;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.ConstPlayGameField;
import org.lsj.gs.mathBoardGtr.config.module.BoardGtrConfigFactory;
import org.lsj.gs.rngDataGtr.config.entity.RngDataGtrConfig;
import org.lsj.gs.rngDataGtr.config.entity.RngDataScenarioHlrConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.RngDataTesterConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigE111LhdJavaBetAndCardType;
import org.lsj.gs.rngDataGtr.core.entity.ConstRngDataGtr;

import java.util.ArrayList;

// 亂數產生器設定 新龍虎鬥
public class RndGtrR111LhdJava extends AbstractRndGtrR {
    public RngDataGtrConfig create(){
        return new RngDataGtrConfig(
                new RngDataScenarioHlrConfig(1000000),
                new BoardGtrConfigFactory().create(ConstPlayGameField.PlayGameFieldResource.R111_LHD_JAVA_NORMAL, ConstBoardGtr.BoardGtrControlAlgorithmType.GRADIENT_NATURE),
                new RngDataTesterConfig(
                        new ArrayList<>(){{
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_111_LHD_JAVA_SPECIFY_BET_AND_CARD_TYPE, new TestCaseConfigE111LhdJavaBetAndCardType(ConstLhdJava.BetAreaIdLhdJava.DRAGON, ConstLhdJava.BetAreaIdLhdJava.DRAGON)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_111_LHD_JAVA_SPECIFY_BET_AND_CARD_TYPE, new TestCaseConfigE111LhdJavaBetAndCardType(ConstLhdJava.BetAreaIdLhdJava.TIGER, ConstLhdJava.BetAreaIdLhdJava.TIGER)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_111_LHD_JAVA_SPECIFY_BET_AND_CARD_TYPE, new TestCaseConfigE111LhdJavaBetAndCardType(ConstLhdJava.BetAreaIdLhdJava.TIE, ConstLhdJava.BetAreaIdLhdJava.TIE)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_111_LHD_JAVA_SPECIFY_BET_AND_CARD_TYPE, new TestCaseConfigE111LhdJavaBetAndCardType(ConstLhdJava.BetAreaIdLhdJava.DRAGON, ConstLhdJava.BetAreaIdLhdJava.TIE)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_111_LHD_JAVA_SPECIFY_BET_AND_CARD_TYPE, new TestCaseConfigE111LhdJavaBetAndCardType(ConstLhdJava.BetAreaIdLhdJava.TIGER, ConstLhdJava.BetAreaIdLhdJava.TIE)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_111_LHD_JAVA_SPECIFY_BET_AND_CARD_TYPE, new TestCaseConfigE111LhdJavaBetAndCardType(ConstLhdJava.BetAreaIdLhdJava.TIE, ConstLhdJava.BetAreaIdLhdJava.DRAGON)));
                        }}
                )
        );
    }
}
