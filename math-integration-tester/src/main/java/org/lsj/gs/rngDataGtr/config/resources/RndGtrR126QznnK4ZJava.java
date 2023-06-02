package org.lsj.gs.rngDataGtr.config.resources;

import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import org.lsj.gs.math.games.qznn_k4z_java.entity.ConstQznnK4zJava;
import org.lsj.gs.mathBoardGtr.config.entity.ConstBoardGtr;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.ConstPlayGameField;
import org.lsj.gs.mathBoardGtr.config.module.BoardGtrConfigFactory;
import org.lsj.gs.rngDataGtr.config.entity.RngDataGtrConfig;
import org.lsj.gs.rngDataGtr.config.entity.RngDataScenarioHlrConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.RngDataTesterConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigE126QznnK4ZJavaNiuType;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigE126QznnK4ZJavaSameResultType;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigEBankerAllWinLoss;
import org.lsj.gs.rngDataGtr.core.entity.ConstRngDataGtr;

import java.util.ArrayList;

// 亂數產生器設定 126 新看四張搶莊牛牛
public class RndGtrR126QznnK4ZJava extends AbstractRndGtrR {
    public RngDataGtrConfig create(){
        return new RngDataGtrConfig(
                new RngDataScenarioHlrConfig(1000000),
                new BoardGtrConfigFactory().create(ConstPlayGameField.PlayGameFieldResource.R126_QZNN_K4Z_JAVA_NORMAL, ConstBoardGtr.BoardGtrControlAlgorithmType.GRADIENT_NATURE),
                new RngDataTesterConfig(
                        new ArrayList<>(){{
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BANKER_ALL_WIN_LOSS, new TestCaseConfigEBankerAllWinLoss(3, -1, ConstMathCard.WinType.WIN)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BANKER_ALL_WIN_LOSS, new TestCaseConfigEBankerAllWinLoss(3, -1, ConstMathCard.WinType.LOSS)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_126_QZNN_K4Z_JAVA_NIU_TYPE, new TestCaseConfigE126QznnK4ZJavaNiuType(0, 1, ConstQznnK4zJava.NiuTypeEnumQznnK4zJava.NIU_0)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_126_QZNN_K4Z_JAVA_NIU_TYPE, new TestCaseConfigE126QznnK4ZJavaNiuType(0, 1, ConstQznnK4zJava.NiuTypeEnumQznnK4zJava.NIU_1)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_126_QZNN_K4Z_JAVA_NIU_TYPE, new TestCaseConfigE126QznnK4ZJavaNiuType(0, 1, ConstQznnK4zJava.NiuTypeEnumQznnK4zJava.NIU_2)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_126_QZNN_K4Z_JAVA_NIU_TYPE, new TestCaseConfigE126QznnK4ZJavaNiuType(0, 1, ConstQznnK4zJava.NiuTypeEnumQznnK4zJava.NIU_3)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_126_QZNN_K4Z_JAVA_NIU_TYPE, new TestCaseConfigE126QznnK4ZJavaNiuType(0, 1, ConstQznnK4zJava.NiuTypeEnumQznnK4zJava.NIU_4)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_126_QZNN_K4Z_JAVA_NIU_TYPE, new TestCaseConfigE126QznnK4ZJavaNiuType(0, 1, ConstQznnK4zJava.NiuTypeEnumQznnK4zJava.NIU_5)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_126_QZNN_K4Z_JAVA_NIU_TYPE, new TestCaseConfigE126QznnK4ZJavaNiuType(0, 1, ConstQznnK4zJava.NiuTypeEnumQznnK4zJava.NIU_6)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_126_QZNN_K4Z_JAVA_NIU_TYPE, new TestCaseConfigE126QznnK4ZJavaNiuType(0, 1, ConstQznnK4zJava.NiuTypeEnumQznnK4zJava.NIU_7)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_126_QZNN_K4Z_JAVA_NIU_TYPE, new TestCaseConfigE126QznnK4ZJavaNiuType(0, 1, ConstQznnK4zJava.NiuTypeEnumQznnK4zJava.NIU_8)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_126_QZNN_K4Z_JAVA_NIU_TYPE, new TestCaseConfigE126QznnK4ZJavaNiuType(0, 1, ConstQznnK4zJava.NiuTypeEnumQznnK4zJava.NIU_9)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_126_QZNN_K4Z_JAVA_NIU_TYPE, new TestCaseConfigE126QznnK4ZJavaNiuType(0, 1, ConstQznnK4zJava.NiuTypeEnumQznnK4zJava.NIU_NIU)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_126_QZNN_K4Z_JAVA_NIU_TYPE, new TestCaseConfigE126QznnK4ZJavaNiuType(0, 1, ConstQznnK4zJava.NiuTypeEnumQznnK4zJava.FLOWER_4)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_126_QZNN_K4Z_JAVA_NIU_TYPE, new TestCaseConfigE126QznnK4ZJavaNiuType(0, 1, ConstQznnK4zJava.NiuTypeEnumQznnK4zJava.SHUNZI_NIU)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_126_QZNN_K4Z_JAVA_NIU_TYPE, new TestCaseConfigE126QznnK4ZJavaNiuType(0, 1, ConstQznnK4zJava.NiuTypeEnumQznnK4zJava.TONGHUA_NIU)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_126_QZNN_K4Z_JAVA_NIU_TYPE, new TestCaseConfigE126QznnK4ZJavaNiuType(0, 1, ConstQznnK4zJava.NiuTypeEnumQznnK4zJava.HULU_NIU)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_126_QZNN_K4Z_JAVA_NIU_TYPE, new TestCaseConfigE126QznnK4ZJavaNiuType(0, 1, ConstQznnK4zJava.NiuTypeEnumQznnK4zJava.FLOWER_5)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_126_QZNN_K4Z_JAVA_NIU_TYPE, new TestCaseConfigE126QznnK4ZJavaNiuType(0, 1, ConstQznnK4zJava.NiuTypeEnumQznnK4zJava.BOMB)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_126_QZNN_K4Z_JAVA_NIU_TYPE, new TestCaseConfigE126QznnK4ZJavaNiuType(0, 1, ConstQznnK4zJava.NiuTypeEnumQznnK4zJava.SHUNJIN_NIU)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_126_QZNN_K4Z_JAVA_NIU_TYPE, new TestCaseConfigE126QznnK4ZJavaNiuType(0, 1, ConstQznnK4zJava.NiuTypeEnumQznnK4zJava.SMALL_NIU)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_126_QZNN_K4Z_JAVA_SAME_RESULT_TYPE, new TestCaseConfigE126QznnK4ZJavaSameResultType(ConstNiu.NiuTypeEnumCommon.SHUNZI_NIU)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_126_QZNN_K4Z_JAVA_SAME_RESULT_TYPE, new TestCaseConfigE126QznnK4ZJavaSameResultType(ConstNiu.NiuTypeEnumCommon.HULU_NIU)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_126_QZNN_K4Z_JAVA_SAME_RESULT_TYPE, new TestCaseConfigE126QznnK4ZJavaSameResultType(ConstNiu.NiuTypeEnumCommon.BOMB)));
                        }}
                )
        );
    }
}
