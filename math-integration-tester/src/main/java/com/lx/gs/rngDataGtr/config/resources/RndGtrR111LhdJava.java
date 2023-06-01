package com.lx.gs.rngDataGtr.config.resources;

import com.lx.gs.math.games.lhd_java.entity.ConstLhdJava;
import com.lx.gs.mathBoardGtr.config.entity.ConstBoardGtr;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.ConstPlayGameField;
import com.lx.gs.mathBoardGtr.config.module.BoardGtrConfigFactory;
import com.lx.gs.rngDataGtr.config.entity.RngDataGtrConfig;
import com.lx.gs.rngDataGtr.config.entity.RngDataScenarioHlrConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.RngDataTesterConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigE111LhdJavaBetAndCardType;
import com.lx.gs.rngDataGtr.core.entity.ConstRngDataGtr;

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
