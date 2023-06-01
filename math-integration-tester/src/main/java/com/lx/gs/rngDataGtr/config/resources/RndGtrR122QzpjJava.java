package com.lx.gs.rngDataGtr.config.resources;

import com.lx.gs.math.core.card.ConstMathCard;
import com.lx.gs.math.games.qzpj_java.entity.ConstQzpjJava;
import com.lx.gs.mathBoardGtr.config.entity.ConstBoardGtr;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.ConstPlayGameField;
import com.lx.gs.mathBoardGtr.config.module.BoardGtrConfigFactory;
import com.lx.gs.rngDataGtr.config.entity.RngDataGtrConfig;
import com.lx.gs.rngDataGtr.config.entity.RngDataScenarioHlrConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.RngDataTesterConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigE122QzpjJavaTie;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigE122QzpjJavaType;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigEBankerAllWinLoss;
import com.lx.gs.rngDataGtr.core.entity.ConstRngDataGtr;

import java.util.ArrayList;

// 亂數產生器設定 122 新搶莊牌九
public class RndGtrR122QzpjJava extends AbstractRndGtrR {
    public RngDataGtrConfig create(){
        return new RngDataGtrConfig(
                new RngDataScenarioHlrConfig(1000000),
                new BoardGtrConfigFactory().create(ConstPlayGameField.PlayGameFieldResource.R122_QZPJ_JAVA_NORMAL, ConstBoardGtr.BoardGtrControlAlgorithmType.GRADIENT_NATURE),
                new RngDataTesterConfig(
                        new ArrayList<>(){{
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BANKER_ALL_WIN_LOSS, new TestCaseConfigEBankerAllWinLoss(1, -1, ConstMathCard.WinType.WIN)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BANKER_ALL_WIN_LOSS, new TestCaseConfigEBankerAllWinLoss(1, -1, ConstMathCard.WinType.LOSS)));

                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_TIE, new TestCaseConfigE122QzpjJavaTie(1, -1)));

                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.PT_0)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.PT_1)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.PT_2)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.PT_3)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.PT_4)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.PT_5)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.PT_6)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.PT_7)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.PT_8)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.PT_9)));

                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.DGJ)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.TGJ)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.DG)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.TG)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.DW)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.TW)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.ZW)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.Z7)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.Z8)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.Z9)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.SLL)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.SGJ)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.SHT)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.SFT)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.SBD)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.SCS)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.SM)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.SE)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.SR)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.SD)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.ST)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_122_QZPJ_JAVA_PJ_TYPE, new TestCaseConfigE122QzpjJavaType(0, 1, ConstQzpjJava.PjTypeEnumQzpjJava.ZZ)));
                        }}
                )
        );
    }
}
