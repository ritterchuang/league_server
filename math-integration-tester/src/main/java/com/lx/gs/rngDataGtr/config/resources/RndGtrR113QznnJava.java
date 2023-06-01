package com.lx.gs.rngDataGtr.config.resources;

import com.lx.gs.math.core.card.ConstMathCard;
import com.lx.gs.math.games.qznn_java.entity.ConstQznnJava;
import com.lx.gs.mathBoardGtr.config.entity.ConstBoardGtr;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.ConstPlayGameField;
import com.lx.gs.mathBoardGtr.config.module.BoardGtrConfigFactory;
import com.lx.gs.rngDataGtr.config.entity.RngDataGtrConfig;
import com.lx.gs.rngDataGtr.config.entity.RngDataScenarioHlrConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.RngDataTesterConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigE113QznnJavaNiuType;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigEBankerAllWinLoss;
import com.lx.gs.rngDataGtr.core.entity.ConstRngDataGtr;

import java.util.ArrayList;

// 亂數產生器設定 113 新搶莊牛牛
public class RndGtrR113QznnJava extends AbstractRndGtrR {
    public RngDataGtrConfig create(){
        return new RngDataGtrConfig(
                new RngDataScenarioHlrConfig(1000000),
                new BoardGtrConfigFactory().create(ConstPlayGameField.PlayGameFieldResource.R113_QZNN_JAVA_NORMAL, ConstBoardGtr.BoardGtrControlAlgorithmType.GRADIENT_NATURE),
                new RngDataTesterConfig(
                        new ArrayList<>(){{
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BANKER_ALL_WIN_LOSS, new TestCaseConfigEBankerAllWinLoss(3, -1, ConstMathCard.WinType.WIN)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BANKER_ALL_WIN_LOSS, new TestCaseConfigEBankerAllWinLoss(3, -1, ConstMathCard.WinType.LOSS)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_113_QZNN_JAVA_NIU_TYPE, new TestCaseConfigE113QznnJavaNiuType(0, 1, ConstQznnJava.NiuTypeEnumQznnJava.NIU_0)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_113_QZNN_JAVA_NIU_TYPE, new TestCaseConfigE113QznnJavaNiuType(0, 1, ConstQznnJava.NiuTypeEnumQznnJava.NIU_1)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_113_QZNN_JAVA_NIU_TYPE, new TestCaseConfigE113QznnJavaNiuType(0, 1, ConstQznnJava.NiuTypeEnumQznnJava.NIU_2)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_113_QZNN_JAVA_NIU_TYPE, new TestCaseConfigE113QznnJavaNiuType(0, 1, ConstQznnJava.NiuTypeEnumQznnJava.NIU_3)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_113_QZNN_JAVA_NIU_TYPE, new TestCaseConfigE113QznnJavaNiuType(0, 1, ConstQznnJava.NiuTypeEnumQznnJava.NIU_4)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_113_QZNN_JAVA_NIU_TYPE, new TestCaseConfigE113QznnJavaNiuType(0, 1, ConstQznnJava.NiuTypeEnumQznnJava.NIU_5)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_113_QZNN_JAVA_NIU_TYPE, new TestCaseConfigE113QznnJavaNiuType(0, 1, ConstQznnJava.NiuTypeEnumQznnJava.NIU_6)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_113_QZNN_JAVA_NIU_TYPE, new TestCaseConfigE113QznnJavaNiuType(0, 1, ConstQznnJava.NiuTypeEnumQznnJava.NIU_7)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_113_QZNN_JAVA_NIU_TYPE, new TestCaseConfigE113QznnJavaNiuType(0, 1, ConstQznnJava.NiuTypeEnumQznnJava.NIU_8)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_113_QZNN_JAVA_NIU_TYPE, new TestCaseConfigE113QznnJavaNiuType(0, 1, ConstQznnJava.NiuTypeEnumQznnJava.NIU_9)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_113_QZNN_JAVA_NIU_TYPE, new TestCaseConfigE113QznnJavaNiuType(0, 1, ConstQznnJava.NiuTypeEnumQznnJava.NIU_NIU)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_113_QZNN_JAVA_NIU_TYPE, new TestCaseConfigE113QznnJavaNiuType(0, 1, ConstQznnJava.NiuTypeEnumQznnJava.FLOWER_4)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_113_QZNN_JAVA_NIU_TYPE, new TestCaseConfigE113QznnJavaNiuType(0, 1, ConstQznnJava.NiuTypeEnumQznnJava.FLOWER_5)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_113_QZNN_JAVA_NIU_TYPE, new TestCaseConfigE113QznnJavaNiuType(0, 1, ConstQznnJava.NiuTypeEnumQznnJava.BOMB)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_113_QZNN_JAVA_NIU_TYPE, new TestCaseConfigE113QznnJavaNiuType(0, 1, ConstQznnJava.NiuTypeEnumQznnJava.SMALL_NIU)));
                        }}
                )
        );
    }
}
