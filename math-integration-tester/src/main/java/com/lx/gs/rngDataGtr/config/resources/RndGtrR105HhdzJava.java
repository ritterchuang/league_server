package com.lx.gs.rngDataGtr.config.resources;

import com.lx.gs.math.core.card.stackCtr.jhStackCtr.ConstJhStack;
import com.lx.gs.math.games.hhdz_java.entity.ConstHhdzJava;
import com.lx.gs.mathBoardGtr.config.entity.ConstBoardGtr;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.ConstPlayGameField;
import com.lx.gs.mathBoardGtr.config.module.BoardGtrConfigFactory;
import com.lx.gs.rngDataGtr.config.entity.RngDataGtrConfig;
import com.lx.gs.rngDataGtr.config.entity.RngDataScenarioHlrConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.RngDataTesterConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigE105HhdzJavaBet;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigE105HhdzJavaCardType;
import com.lx.gs.rngDataGtr.core.entity.ConstRngDataGtr;

import java.util.ArrayList;

// 亂數產生器設定 新紅黑大戰
public class RndGtrR105HhdzJava extends AbstractRndGtrR {
    public RngDataGtrConfig create(){
        return new RngDataGtrConfig(
                new RngDataScenarioHlrConfig(1000000),
                new BoardGtrConfigFactory().create(ConstPlayGameField.PlayGameFieldResource.R105_HHDZ_JAVA_NORMAL, ConstBoardGtr.BoardGtrControlAlgorithmType.GRADIENT_NATURE),
                new RngDataTesterConfig(
                        new ArrayList<>(){{
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_105_HHDZ_JAVA_SPECIFY_BET, new TestCaseConfigE105HhdzJavaBet(ConstHhdzJava.BetAreaIdHhdzJava.BLACK, ConstHhdzJava.BetAreaIdHhdzJava.BLACK)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_105_HHDZ_JAVA_SPECIFY_BET, new TestCaseConfigE105HhdzJavaBet(ConstHhdzJava.BetAreaIdHhdzJava.RED, ConstHhdzJava.BetAreaIdHhdzJava.RED)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_105_HHDZ_JAVA_SPECIFY_CARD_TYPE, new TestCaseConfigE105HhdzJavaCardType(ConstHhdzJava.BetAreaIdHhdzJava.BLACK, ConstJhStack.JhTypeEnumCommon.DAN_ZHANG)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_105_HHDZ_JAVA_SPECIFY_CARD_TYPE, new TestCaseConfigE105HhdzJavaCardType(ConstHhdzJava.BetAreaIdHhdzJava.BLACK, ConstJhStack.JhTypeEnumCommon.DUI_ZI)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_105_HHDZ_JAVA_SPECIFY_CARD_TYPE, new TestCaseConfigE105HhdzJavaCardType(ConstHhdzJava.BetAreaIdHhdzJava.BLACK, ConstJhStack.JhTypeEnumCommon.SHUN_ZI)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_105_HHDZ_JAVA_SPECIFY_CARD_TYPE, new TestCaseConfigE105HhdzJavaCardType(ConstHhdzJava.BetAreaIdHhdzJava.BLACK, ConstJhStack.JhTypeEnumCommon.TONG_HUA)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_105_HHDZ_JAVA_SPECIFY_CARD_TYPE, new TestCaseConfigE105HhdzJavaCardType(ConstHhdzJava.BetAreaIdHhdzJava.BLACK, ConstJhStack.JhTypeEnumCommon.TONG_HUA_SHUN)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_105_HHDZ_JAVA_SPECIFY_CARD_TYPE, new TestCaseConfigE105HhdzJavaCardType(ConstHhdzJava.BetAreaIdHhdzJava.BLACK, ConstJhStack.JhTypeEnumCommon.BAO_ZI)));
                        }}
                )
        );
    }
}
