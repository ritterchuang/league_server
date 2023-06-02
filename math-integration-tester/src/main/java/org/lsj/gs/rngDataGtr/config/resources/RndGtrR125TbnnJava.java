package org.lsj.gs.rngDataGtr.config.resources;

import org.lsj.gs.math.games.tbnn_java.entity.ConstTbnnJava;
import org.lsj.gs.mathBoardGtr.config.entity.ConstBoardGtr;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.ConstPlayGameField;
import org.lsj.gs.mathBoardGtr.config.module.BoardGtrConfigFactory;
import org.lsj.gs.rngDataGtr.config.entity.RngDataGtrConfig;
import org.lsj.gs.rngDataGtr.config.entity.RngDataScenarioHlrConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.RngDataTesterConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigE125TbnnJavaNiuType;
import org.lsj.gs.rngDataGtr.core.entity.ConstRngDataGtr;

import java.util.ArrayList;

// 亂數產生器設定 125 新通比牛牛
public class RndGtrR125TbnnJava extends AbstractRndGtrR {
    public RngDataGtrConfig create(){
        return new RngDataGtrConfig(
                new RngDataScenarioHlrConfig(1000000),
                new BoardGtrConfigFactory().create(ConstPlayGameField.PlayGameFieldResource.R125_TBNN_JAVA_NORMAL, ConstBoardGtr.BoardGtrControlAlgorithmType.GRADIENT_NATURE),
                new RngDataTesterConfig(
                        new ArrayList<>(){{
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_125_TBNN_JAVA_NIU_TYPE, new TestCaseConfigE125TbnnJavaNiuType(1, ConstTbnnJava.NiuTypeEnumTbnnJava.NIU_0)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_125_TBNN_JAVA_NIU_TYPE, new TestCaseConfigE125TbnnJavaNiuType(1, ConstTbnnJava.NiuTypeEnumTbnnJava.NIU_1)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_125_TBNN_JAVA_NIU_TYPE, new TestCaseConfigE125TbnnJavaNiuType(1, ConstTbnnJava.NiuTypeEnumTbnnJava.NIU_2)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_125_TBNN_JAVA_NIU_TYPE, new TestCaseConfigE125TbnnJavaNiuType(1, ConstTbnnJava.NiuTypeEnumTbnnJava.NIU_3)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_125_TBNN_JAVA_NIU_TYPE, new TestCaseConfigE125TbnnJavaNiuType(1, ConstTbnnJava.NiuTypeEnumTbnnJava.NIU_4)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_125_TBNN_JAVA_NIU_TYPE, new TestCaseConfigE125TbnnJavaNiuType(1, ConstTbnnJava.NiuTypeEnumTbnnJava.NIU_5)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_125_TBNN_JAVA_NIU_TYPE, new TestCaseConfigE125TbnnJavaNiuType(1, ConstTbnnJava.NiuTypeEnumTbnnJava.NIU_6)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_125_TBNN_JAVA_NIU_TYPE, new TestCaseConfigE125TbnnJavaNiuType(1, ConstTbnnJava.NiuTypeEnumTbnnJava.NIU_7)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_125_TBNN_JAVA_NIU_TYPE, new TestCaseConfigE125TbnnJavaNiuType(1, ConstTbnnJava.NiuTypeEnumTbnnJava.NIU_8)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_125_TBNN_JAVA_NIU_TYPE, new TestCaseConfigE125TbnnJavaNiuType(1, ConstTbnnJava.NiuTypeEnumTbnnJava.NIU_9)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_125_TBNN_JAVA_NIU_TYPE, new TestCaseConfigE125TbnnJavaNiuType(1, ConstTbnnJava.NiuTypeEnumTbnnJava.NIU_NIU)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_125_TBNN_JAVA_NIU_TYPE, new TestCaseConfigE125TbnnJavaNiuType(1, ConstTbnnJava.NiuTypeEnumTbnnJava.FLOWER_4)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_125_TBNN_JAVA_NIU_TYPE, new TestCaseConfigE125TbnnJavaNiuType(1, ConstTbnnJava.NiuTypeEnumTbnnJava.FLOWER_5)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_125_TBNN_JAVA_NIU_TYPE, new TestCaseConfigE125TbnnJavaNiuType(1, ConstTbnnJava.NiuTypeEnumTbnnJava.BOMB)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_125_TBNN_JAVA_NIU_TYPE, new TestCaseConfigE125TbnnJavaNiuType(1, ConstTbnnJava.NiuTypeEnumTbnnJava.SMALL_NIU)));
                        }}
                )
        );
    }
}
