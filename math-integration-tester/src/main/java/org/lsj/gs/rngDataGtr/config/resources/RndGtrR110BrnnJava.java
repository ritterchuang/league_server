package org.lsj.gs.rngDataGtr.config.resources;

import org.lsj.gs.math.core.baiRen.ConstMathNiu;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import org.lsj.gs.mathBoardGtr.config.entity.ConstBoardGtr;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.ConstPlayGameField;
import org.lsj.gs.mathBoardGtr.config.module.BoardGtrConfigFactory;
import org.lsj.gs.rngDataGtr.config.entity.RngDataGtrConfig;
import org.lsj.gs.rngDataGtr.config.entity.RngDataScenarioHlrConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.RngDataTesterConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigEBaiRenNiuBet;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigEBaiRenNiuType;
import org.lsj.gs.rngDataGtr.core.entity.ConstRngDataGtr;

import java.util.ArrayList;
import java.util.List;

// 亂數產生器設定 新百人牛牛
public class RndGtrR110BrnnJava extends AbstractRndGtrR {
    public RngDataGtrConfig create(){
        return new RngDataGtrConfig(
                new RngDataScenarioHlrConfig(1000000),
                new BoardGtrConfigFactory().create(ConstPlayGameField.PlayGameFieldResource.R110_BRNN_JAVA_NORMAL, ConstBoardGtr.BoardGtrControlAlgorithmType.GRADIENT_NATURE),
                new RngDataTesterConfig(
                        new ArrayList<>(){{
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BAIREN_NIU_JAVA_SPECIFY_BET, new TestCaseConfigEBaiRenNiuBet(List.of(ConstMathNiu.BetAreaEnum.TIAN), List.of(ConstMathNiu.BetAreaEnum.TIAN))));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BAIREN_NIU_JAVA_SPECIFY_BET, new TestCaseConfigEBaiRenNiuBet(List.of(ConstMathNiu.BetAreaEnum.DI), List.of(ConstMathNiu.BetAreaEnum.DI))));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BAIREN_NIU_JAVA_SPECIFY_BET, new TestCaseConfigEBaiRenNiuBet(List.of(ConstMathNiu.BetAreaEnum.XUAN), List.of(ConstMathNiu.BetAreaEnum.XUAN))));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BAIREN_NIU_JAVA_SPECIFY_BET, new TestCaseConfigEBaiRenNiuBet(List.of(ConstMathNiu.BetAreaEnum.HUANG), List.of(ConstMathNiu.BetAreaEnum.HUANG))));

                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BAIREN_NIU_JAVA_CARD_TYPE, new TestCaseConfigEBaiRenNiuType(ConstMathNiu.BetAreaEnum.TIAN, ConstNiu.NiuTypeEnumCommon.NIU_0    )));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BAIREN_NIU_JAVA_CARD_TYPE, new TestCaseConfigEBaiRenNiuType(ConstMathNiu.BetAreaEnum.TIAN, ConstNiu.NiuTypeEnumCommon.NIU_1    )));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BAIREN_NIU_JAVA_CARD_TYPE, new TestCaseConfigEBaiRenNiuType(ConstMathNiu.BetAreaEnum.TIAN, ConstNiu.NiuTypeEnumCommon.NIU_2    )));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BAIREN_NIU_JAVA_CARD_TYPE, new TestCaseConfigEBaiRenNiuType(ConstMathNiu.BetAreaEnum.TIAN, ConstNiu.NiuTypeEnumCommon.NIU_3    )));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BAIREN_NIU_JAVA_CARD_TYPE, new TestCaseConfigEBaiRenNiuType(ConstMathNiu.BetAreaEnum.TIAN, ConstNiu.NiuTypeEnumCommon.NIU_4    )));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BAIREN_NIU_JAVA_CARD_TYPE, new TestCaseConfigEBaiRenNiuType(ConstMathNiu.BetAreaEnum.TIAN, ConstNiu.NiuTypeEnumCommon.NIU_5    )));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BAIREN_NIU_JAVA_CARD_TYPE, new TestCaseConfigEBaiRenNiuType(ConstMathNiu.BetAreaEnum.TIAN, ConstNiu.NiuTypeEnumCommon.NIU_6    )));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BAIREN_NIU_JAVA_CARD_TYPE, new TestCaseConfigEBaiRenNiuType(ConstMathNiu.BetAreaEnum.TIAN, ConstNiu.NiuTypeEnumCommon.NIU_7    )));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BAIREN_NIU_JAVA_CARD_TYPE, new TestCaseConfigEBaiRenNiuType(ConstMathNiu.BetAreaEnum.TIAN, ConstNiu.NiuTypeEnumCommon.NIU_8    )));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BAIREN_NIU_JAVA_CARD_TYPE, new TestCaseConfigEBaiRenNiuType(ConstMathNiu.BetAreaEnum.TIAN, ConstNiu.NiuTypeEnumCommon.NIU_9    )));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BAIREN_NIU_JAVA_CARD_TYPE, new TestCaseConfigEBaiRenNiuType(ConstMathNiu.BetAreaEnum.TIAN, ConstNiu.NiuTypeEnumCommon.NIU_NIU  )));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BAIREN_NIU_JAVA_CARD_TYPE, new TestCaseConfigEBaiRenNiuType(ConstMathNiu.BetAreaEnum.TIAN, ConstNiu.NiuTypeEnumCommon.FLOWER_4 )));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BAIREN_NIU_JAVA_CARD_TYPE, new TestCaseConfigEBaiRenNiuType(ConstMathNiu.BetAreaEnum.TIAN, ConstNiu.NiuTypeEnumCommon.FLOWER_5 )));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BAIREN_NIU_JAVA_CARD_TYPE, new TestCaseConfigEBaiRenNiuType(ConstMathNiu.BetAreaEnum.TIAN, ConstNiu.NiuTypeEnumCommon.BOMB     )));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_BAIREN_NIU_JAVA_CARD_TYPE, new TestCaseConfigEBaiRenNiuType(ConstMathNiu.BetAreaEnum.TIAN, ConstNiu.NiuTypeEnumCommon.SMALL_NIU)));
                        }}
                )
        );
    }
}
