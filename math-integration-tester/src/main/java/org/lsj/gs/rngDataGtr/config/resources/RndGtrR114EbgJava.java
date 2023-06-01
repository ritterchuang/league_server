package org.lsj.gs.rngDataGtr.config.resources;

import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.games.ebg_java.entity.ConstEbgJava;
import org.lsj.gs.mathBoardGtr.config.entity.ConstBoardGtr;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.ConstPlayGameField;
import org.lsj.gs.mathBoardGtr.config.module.BoardGtrConfigFactory;
import org.lsj.gs.rngDataGtr.config.entity.RngDataGtrConfig;
import org.lsj.gs.rngDataGtr.config.entity.RngDataScenarioHlrConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.RngDataTesterConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigE114EbgJavaType;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigEBankerAllWinLoss;
import org.lsj.gs.rngDataGtr.core.entity.ConstRngDataGtr;

import java.util.ArrayList;

// 亂數產生器設定 114 新搶莊二八槓
public class RndGtrR114EbgJava extends AbstractRndGtrR {
    public RngDataGtrConfig create(){
        return new RngDataGtrConfig(
                new RngDataScenarioHlrConfig(1000000),
                new BoardGtrConfigFactory().create(ConstPlayGameField.PlayGameFieldResource.R114_EBG_JAVA_NORMAL, ConstBoardGtr.BoardGtrControlAlgorithmType.GRADIENT_NATURE),
                new RngDataTesterConfig(
                        new ArrayList<>(){{
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BANKER_ALL_WIN_LOSS, new TestCaseConfigEBankerAllWinLoss(136, -1, ConstMathCard.WinType.WIN)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BANKER_ALL_WIN_LOSS, new TestCaseConfigEBankerAllWinLoss(136, -1, ConstMathCard.WinType.LOSS)));

                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.BS_64)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.BS_73)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.BS_91)));

                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.ONE_65)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.ONE_74)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.ONE_83)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.ONE_92)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.ONE_AND_HALF)));

                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.TWO_75)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.TWO_84)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.TWO_93)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.TWO_AND_HALF)));

                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.THREE_21)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.THREE_76)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.THREE_85)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.THREE_94)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.THREE_AND_HALF)));

                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.FOUR_31)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.FOUR_86)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.FOUR_95)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.FOUR_AND_HALF)));

                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.FIVE_32)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.FIVE_41)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.FIVE_87)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.FIVE_96)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.FIVE_AND_HALF)));

                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.SIX_42)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.SIX_51)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.SIX_97)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.SIX_AND_HALF)));

                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.SEVEN_43)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.SEVEN_52)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.SEVEN_61)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.SEVEN_98)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.SEVEN_AND_HALF)));

                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.EIGHT_53)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.EIGHT_62)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.EIGHT_71)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.EIGHT_AND_HALF)));

                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.NINE_54)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.NINE_63)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.NINE_72)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.NINE_81)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.NINE_AND_HALF)));

                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.EBG)));

                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.BAO_1)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.BAO_2)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.BAO_3)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.BAO_4)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.BAO_5)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.BAO_6)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.BAO_7)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.BAO_8)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.BAO_9)));

                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_114_EBG_JAVA_EBG_TYPE, new TestCaseConfigE114EbgJavaType(0, 3, ConstEbgJava.EbgTypeEnumEbgJava.KING)));
                        }}
                )
        );
    }
}
