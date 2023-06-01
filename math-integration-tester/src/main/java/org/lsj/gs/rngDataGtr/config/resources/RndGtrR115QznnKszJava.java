package org.lsj.gs.rngDataGtr.config.resources;

import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.games.qznn_ksz_java.entity.ConstQznnKszJava;
import org.lsj.gs.mathBoardGtr.config.entity.ConstBoardGtr;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.ConstPlayGameField;
import org.lsj.gs.mathBoardGtr.config.module.BoardGtrConfigFactory;
import org.lsj.gs.rngDataGtr.config.entity.RngDataGtrConfig;
import org.lsj.gs.rngDataGtr.config.entity.RngDataScenarioHlrConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.RngDataTesterConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigE115QznnKszJavaNiuType;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigEBankerAllWinLoss;
import org.lsj.gs.rngDataGtr.core.entity.ConstRngDataGtr;

import java.util.ArrayList;

// 亂數產生器設定 115 新看三張搶莊牛牛
public class RndGtrR115QznnKszJava extends AbstractRndGtrR {
    public RngDataGtrConfig create(){
        return new RngDataGtrConfig(
                new RngDataScenarioHlrConfig(1000000),
                new BoardGtrConfigFactory().create(ConstPlayGameField.PlayGameFieldResource.R115_QZNN_KSZ_JAVA_NORMAL, ConstBoardGtr.BoardGtrControlAlgorithmType.GRADIENT_NATURE),
                new RngDataTesterConfig(
                        new ArrayList<>(){{
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BANKER_ALL_WIN_LOSS, new TestCaseConfigEBankerAllWinLoss(3, -1, ConstMathCard.WinType.WIN)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BANKER_ALL_WIN_LOSS, new TestCaseConfigEBankerAllWinLoss(3, -1, ConstMathCard.WinType.LOSS)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_115_QZNN_KSZ_JAVA_NIU_TYPE, new TestCaseConfigE115QznnKszJavaNiuType(0,  1, ConstQznnKszJava.NiuTypeEnumQznnKszJava.NIU_0)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_115_QZNN_KSZ_JAVA_NIU_TYPE, new TestCaseConfigE115QznnKszJavaNiuType(0,  1, ConstQznnKszJava.NiuTypeEnumQznnKszJava.NIU_1)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_115_QZNN_KSZ_JAVA_NIU_TYPE, new TestCaseConfigE115QznnKszJavaNiuType(0,  1, ConstQznnKszJava.NiuTypeEnumQznnKszJava.NIU_2)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_115_QZNN_KSZ_JAVA_NIU_TYPE, new TestCaseConfigE115QznnKszJavaNiuType(0,  1, ConstQznnKszJava.NiuTypeEnumQznnKszJava.NIU_3)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_115_QZNN_KSZ_JAVA_NIU_TYPE, new TestCaseConfigE115QznnKszJavaNiuType(0,  1, ConstQznnKszJava.NiuTypeEnumQznnKszJava.NIU_4)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_115_QZNN_KSZ_JAVA_NIU_TYPE, new TestCaseConfigE115QznnKszJavaNiuType(0,  1, ConstQznnKszJava.NiuTypeEnumQznnKszJava.NIU_5)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_115_QZNN_KSZ_JAVA_NIU_TYPE, new TestCaseConfigE115QznnKszJavaNiuType(0,  1, ConstQznnKszJava.NiuTypeEnumQznnKszJava.NIU_6)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_115_QZNN_KSZ_JAVA_NIU_TYPE, new TestCaseConfigE115QznnKszJavaNiuType(0,  1, ConstQznnKszJava.NiuTypeEnumQznnKszJava.NIU_7)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_115_QZNN_KSZ_JAVA_NIU_TYPE, new TestCaseConfigE115QznnKszJavaNiuType(0,  1, ConstQznnKszJava.NiuTypeEnumQznnKszJava.NIU_8)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_115_QZNN_KSZ_JAVA_NIU_TYPE, new TestCaseConfigE115QznnKszJavaNiuType(0,  1, ConstQznnKszJava.NiuTypeEnumQznnKszJava.NIU_9)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_115_QZNN_KSZ_JAVA_NIU_TYPE, new TestCaseConfigE115QznnKszJavaNiuType(0,  1, ConstQznnKszJava.NiuTypeEnumQznnKszJava.NIU_NIU)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_115_QZNN_KSZ_JAVA_NIU_TYPE, new TestCaseConfigE115QznnKszJavaNiuType(0,  1, ConstQznnKszJava.NiuTypeEnumQznnKszJava.FLOWER_4)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_115_QZNN_KSZ_JAVA_NIU_TYPE, new TestCaseConfigE115QznnKszJavaNiuType(0,  1, ConstQznnKszJava.NiuTypeEnumQznnKszJava.FLOWER_5)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_115_QZNN_KSZ_JAVA_NIU_TYPE, new TestCaseConfigE115QznnKszJavaNiuType(0,  1, ConstQznnKszJava.NiuTypeEnumQznnKszJava.BOMB)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_115_QZNN_KSZ_JAVA_NIU_TYPE, new TestCaseConfigE115QznnKszJavaNiuType(0,  1, ConstQznnKszJava.NiuTypeEnumQznnKszJava.SMALL_NIU)));
                        }}
                )
        );
    }
}
