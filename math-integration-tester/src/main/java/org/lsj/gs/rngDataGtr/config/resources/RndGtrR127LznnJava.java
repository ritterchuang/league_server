package org.lsj.gs.rngDataGtr.config.resources;

import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.games.lznn_java.entity.ConstLznnJava;
import org.lsj.gs.mathBoardGtr.config.entity.ConstBoardGtr;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.ConstPlayGameField;
import org.lsj.gs.mathBoardGtr.config.module.BoardGtrConfigFactory;
import org.lsj.gs.rngDataGtr.config.entity.RngDataGtrConfig;
import org.lsj.gs.rngDataGtr.config.entity.RngDataScenarioHlrConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.RngDataTesterConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigE127LznnJavaNiuType;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigEBankerAllWinLoss;
import org.lsj.gs.rngDataGtr.core.entity.ConstRngDataGtr;

import java.util.ArrayList;

// 亂數產生器設定 127 新賴子牛牛
public class RndGtrR127LznnJava extends AbstractRndGtrR {
    public RngDataGtrConfig create(){
        return new RngDataGtrConfig(
                new RngDataScenarioHlrConfig(1000000),
                new BoardGtrConfigFactory().create(ConstPlayGameField.PlayGameFieldResource.R127_LZNN_JAVA_NORMAL, ConstBoardGtr.BoardGtrControlAlgorithmType.GRADIENT_NATURE),
                new RngDataTesterConfig(
                        new ArrayList<>(){{
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BANKER_ALL_WIN_LOSS, new TestCaseConfigEBankerAllWinLoss(3, -1, ConstMathCard.WinType.WIN)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BANKER_ALL_WIN_LOSS, new TestCaseConfigEBankerAllWinLoss(3, -1, ConstMathCard.WinType.LOSS)));

                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_127_LZNN_JAVA_NIU_TYPE, new TestCaseConfigE127LznnJavaNiuType(0, 1, ConstLznnJava.NiuTypeEnumLznnJava.NIU_0)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_127_LZNN_JAVA_NIU_TYPE, new TestCaseConfigE127LznnJavaNiuType(0, 1, ConstLznnJava.NiuTypeEnumLznnJava.NIU_1)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_127_LZNN_JAVA_NIU_TYPE, new TestCaseConfigE127LznnJavaNiuType(0, 1, ConstLznnJava.NiuTypeEnumLznnJava.NIU_2)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_127_LZNN_JAVA_NIU_TYPE, new TestCaseConfigE127LznnJavaNiuType(0, 1, ConstLznnJava.NiuTypeEnumLznnJava.NIU_3)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_127_LZNN_JAVA_NIU_TYPE, new TestCaseConfigE127LznnJavaNiuType(0, 1, ConstLznnJava.NiuTypeEnumLznnJava.NIU_4)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_127_LZNN_JAVA_NIU_TYPE, new TestCaseConfigE127LznnJavaNiuType(0, 1, ConstLznnJava.NiuTypeEnumLznnJava.NIU_5)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_127_LZNN_JAVA_NIU_TYPE, new TestCaseConfigE127LznnJavaNiuType(0, 1, ConstLznnJava.NiuTypeEnumLznnJava.NIU_6)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_127_LZNN_JAVA_NIU_TYPE, new TestCaseConfigE127LznnJavaNiuType(0, 1, ConstLznnJava.NiuTypeEnumLznnJava.NIU_7)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_127_LZNN_JAVA_NIU_TYPE, new TestCaseConfigE127LznnJavaNiuType(0, 1, ConstLznnJava.NiuTypeEnumLznnJava.NIU_8)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_127_LZNN_JAVA_NIU_TYPE, new TestCaseConfigE127LznnJavaNiuType(0, 1, ConstLznnJava.NiuTypeEnumLznnJava.NIU_9)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_127_LZNN_JAVA_NIU_TYPE, new TestCaseConfigE127LznnJavaNiuType(0, 1, ConstLznnJava.NiuTypeEnumLznnJava.NIU_NIU)));

                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_127_LZNN_JAVA_NIU_TYPE, new TestCaseConfigE127LznnJavaNiuType(0, 1, ConstLznnJava.NiuTypeEnumLznnJava.FLOWER_4)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_127_LZNN_JAVA_NIU_TYPE, new TestCaseConfigE127LznnJavaNiuType(0, 1, ConstLznnJava.NiuTypeEnumLznnJava.STRAIGHT)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_127_LZNN_JAVA_NIU_TYPE, new TestCaseConfigE127LznnJavaNiuType(0, 1, ConstLznnJava.NiuTypeEnumLznnJava.TONGHUA)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_127_LZNN_JAVA_NIU_TYPE, new TestCaseConfigE127LznnJavaNiuType(0, 1, ConstLznnJava.NiuTypeEnumLznnJava.HULU)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_127_LZNN_JAVA_NIU_TYPE, new TestCaseConfigE127LznnJavaNiuType(0, 1, ConstLznnJava.NiuTypeEnumLznnJava.FLOWER_5)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_127_LZNN_JAVA_NIU_TYPE, new TestCaseConfigE127LznnJavaNiuType(0, 1, ConstLznnJava.NiuTypeEnumLznnJava.BOMB)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_127_LZNN_JAVA_NIU_TYPE, new TestCaseConfigE127LznnJavaNiuType(0, 1, ConstLznnJava.NiuTypeEnumLznnJava.STRAIGHTFLUSH)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_127_LZNN_JAVA_NIU_TYPE, new TestCaseConfigE127LznnJavaNiuType(0, 1, ConstLznnJava.NiuTypeEnumLznnJava.SMALL_NIU)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_127_LZNN_JAVA_NIU_TYPE, new TestCaseConfigE127LznnJavaNiuType(0, 1, ConstLznnJava.NiuTypeEnumLznnJava.HYDROGENBOMB)));
                        }}
                )
        );
    }
}
