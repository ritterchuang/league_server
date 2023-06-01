package com.lx.gs.rngDataGtr.config.resources;

import com.lx.gs.math.core.card.ConstMathCard;
import com.lx.gs.math.games.sg_java.entity.ConstSgJava;
import com.lx.gs.mathBoardGtr.config.entity.ConstBoardGtr;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.ConstPlayGameField;
import com.lx.gs.mathBoardGtr.config.module.BoardGtrConfigFactory;
import com.lx.gs.rngDataGtr.config.entity.RngDataGtrConfig;
import com.lx.gs.rngDataGtr.config.entity.RngDataScenarioHlrConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.RngDataTesterConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigE117SgJavaSgType;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigEBankerAllWinLoss;
import com.lx.gs.rngDataGtr.core.entity.ConstRngDataGtr;

import java.util.ArrayList;

// 亂數產生器設定 117 新三公
public class RndGtrR117SgJava extends AbstractRndGtrR {
    public RngDataGtrConfig create(){
        return new RngDataGtrConfig(
                new RngDataScenarioHlrConfig(1000000),
                new BoardGtrConfigFactory().create(ConstPlayGameField.PlayGameFieldResource.R117_SG_JAVA_NORMAL, ConstBoardGtr.BoardGtrControlAlgorithmType.GRADIENT_NATURE),
                new RngDataTesterConfig(
                        new ArrayList<>(){{
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BANKER_ALL_WIN_LOSS, new TestCaseConfigEBankerAllWinLoss(1, -1, ConstMathCard.WinType.WIN)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BANKER_ALL_WIN_LOSS, new TestCaseConfigEBankerAllWinLoss(1, -1, ConstMathCard.WinType.LOSS)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_117_SG_JAVA_SG_TYPE, new TestCaseConfigE117SgJavaSgType(0, 1, ConstSgJava.SgTypeEnumSgJava.PT_0)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_117_SG_JAVA_SG_TYPE, new TestCaseConfigE117SgJavaSgType(0, 1, ConstSgJava.SgTypeEnumSgJava.PT_1)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_117_SG_JAVA_SG_TYPE, new TestCaseConfigE117SgJavaSgType(0, 1, ConstSgJava.SgTypeEnumSgJava.PT_2)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_117_SG_JAVA_SG_TYPE, new TestCaseConfigE117SgJavaSgType(0, 1, ConstSgJava.SgTypeEnumSgJava.PT_3)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_117_SG_JAVA_SG_TYPE, new TestCaseConfigE117SgJavaSgType(0, 1, ConstSgJava.SgTypeEnumSgJava.PT_4)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_117_SG_JAVA_SG_TYPE, new TestCaseConfigE117SgJavaSgType(0, 1, ConstSgJava.SgTypeEnumSgJava.PT_5)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_117_SG_JAVA_SG_TYPE, new TestCaseConfigE117SgJavaSgType(0, 1, ConstSgJava.SgTypeEnumSgJava.PT_6)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_117_SG_JAVA_SG_TYPE, new TestCaseConfigE117SgJavaSgType(0, 1, ConstSgJava.SgTypeEnumSgJava.PT_7)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_117_SG_JAVA_SG_TYPE, new TestCaseConfigE117SgJavaSgType(0, 1, ConstSgJava.SgTypeEnumSgJava.PT_8)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_117_SG_JAVA_SG_TYPE, new TestCaseConfigE117SgJavaSgType(0, 1, ConstSgJava.SgTypeEnumSgJava.PT_9)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_117_SG_JAVA_SG_TYPE, new TestCaseConfigE117SgJavaSgType(0, 1, ConstSgJava.SgTypeEnumSgJava.SANGONG)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_117_SG_JAVA_SG_TYPE, new TestCaseConfigE117SgJavaSgType(0, 1, ConstSgJava.SgTypeEnumSgJava.DASANGONG)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_117_SG_JAVA_SG_TYPE, new TestCaseConfigE117SgJavaSgType(0, 1, ConstSgJava.SgTypeEnumSgJava.ZHIZUN)));
                        }}
                )
        );
    }
}
