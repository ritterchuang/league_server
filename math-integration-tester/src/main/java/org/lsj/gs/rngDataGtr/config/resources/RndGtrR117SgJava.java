package org.lsj.gs.rngDataGtr.config.resources;

import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.games.sg_java.entity.ConstSgJava;
import org.lsj.gs.mathBoardGtr.config.entity.ConstBoardGtr;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.ConstPlayGameField;
import org.lsj.gs.mathBoardGtr.config.module.BoardGtrConfigFactory;
import org.lsj.gs.rngDataGtr.config.entity.RngDataGtrConfig;
import org.lsj.gs.rngDataGtr.config.entity.RngDataScenarioHlrConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.RngDataTesterConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigE117SgJavaSgType;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigEBankerAllWinLoss;
import org.lsj.gs.rngDataGtr.core.entity.ConstRngDataGtr;

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
