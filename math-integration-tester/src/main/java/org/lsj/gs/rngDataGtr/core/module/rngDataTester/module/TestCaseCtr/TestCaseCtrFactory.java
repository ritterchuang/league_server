package org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr;

import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.baiRen.TestCaseCtrEBaiRenBjBet;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.baiRen.TestCaseCtrEBaiRenBjDeal;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.baiRen.TestCaseCtrEBaiRenNiuBet;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.baiRen.TestCaseCtrEBaiRenNiuType;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.slot.*;
import org.lsj.gs.rngDataGtr.games.ebg_java.TestCaseCtrE114EbgJavaEbgType;
import org.lsj.gs.rngDataGtr.games.hhdz_java.TestCaseCtrE105HhdzJavaSpecifyBet;
import org.lsj.gs.rngDataGtr.games.hhdz_java.TestCaseCtrE105HhdzJavaSpecifyCardType;
import org.lsj.gs.rngDataGtr.games.lhd_java.TestCaseCtrE111LhdJavaSpecifyBetAndCardType;
import org.lsj.gs.rngDataGtr.games.lznn_java.TestCaseCtrE127LznnJavaNiuType;
import org.lsj.gs.rngDataGtr.games.qznn_java.TestCaseCtrE113QznnJavaNiuType;
import org.lsj.gs.rngDataGtr.games.qznn_k4z_java.TestCaseCtrE126QznnK4zJavaNiuType;
import org.lsj.gs.rngDataGtr.games.qznn_k4z_java.TestCaseCtrE126QznnK4zJavaSameResultType;
import org.lsj.gs.rngDataGtr.games.qznn_ksz_java.TestCaseCtrE115QznnKszJavaNiuType;
import org.lsj.gs.rngDataGtr.games.qzpj_java.TestCaseCtrE122QzpjJavaPjType;
import org.lsj.gs.rngDataGtr.games.qzpj_java.TestCaseCtrE122QzpjJavaTie;
import org.lsj.gs.rngDataGtr.games.sg_java.TestCaseCtrE117SgJavaSgType;
import org.lsj.gs.rngDataGtr.games.tbnn_java.TestCaseCtrE125TbnnJavaNiuType;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.slot.*;

// 測項計算器工廠
public class TestCaseCtrFactory {

    public AbstractTestCaseCtr getTestCaseCtr(TestCaseConfig testCaseConfig){
        switch(testCaseConfig.getTestCaseTypeEnum()){
            case E_105_HHDZ_JAVA_SPECIFY_BET: return new TestCaseCtrE105HhdzJavaSpecifyBet(testCaseConfig);
            case E_105_HHDZ_JAVA_SPECIFY_CARD_TYPE: return new TestCaseCtrE105HhdzJavaSpecifyCardType(testCaseConfig);
            case E_BAIREN_BJ_JAVA_SPECIFY_BET: return new TestCaseCtrEBaiRenBjBet(testCaseConfig);
            case E_BAIREN_BJ_JAVA_DEAL: return new TestCaseCtrEBaiRenBjDeal(testCaseConfig);
            case E_BAIREN_NIU_JAVA_SPECIFY_BET: return new TestCaseCtrEBaiRenNiuBet(testCaseConfig);
            case E_BAIREN_NIU_JAVA_CARD_TYPE: return new TestCaseCtrEBaiRenNiuType(testCaseConfig);
            case E_111_LHD_JAVA_SPECIFY_BET_AND_CARD_TYPE: return new TestCaseCtrE111LhdJavaSpecifyBetAndCardType(testCaseConfig);
            case E_113_QZNN_JAVA_NIU_TYPE: return new TestCaseCtrE113QznnJavaNiuType(testCaseConfig);
            case E_114_EBG_JAVA_EBG_TYPE: return new TestCaseCtrE114EbgJavaEbgType(testCaseConfig);
            case E_115_QZNN_KSZ_JAVA_NIU_TYPE: return new TestCaseCtrE115QznnKszJavaNiuType(testCaseConfig);
            case E_117_SG_JAVA_SG_TYPE: return new TestCaseCtrE117SgJavaSgType(testCaseConfig);
            case E_122_QZPJ_JAVA_PJ_TYPE: return new TestCaseCtrE122QzpjJavaPjType(testCaseConfig);
            case E_122_QZPJ_JAVA_TIE: return new TestCaseCtrE122QzpjJavaTie(testCaseConfig);
            case E_125_TBNN_JAVA_NIU_TYPE: return new TestCaseCtrE125TbnnJavaNiuType(testCaseConfig);
            case E_126_QZNN_K4Z_JAVA_NIU_TYPE: return new TestCaseCtrE126QznnK4zJavaNiuType(testCaseConfig);
            case E_126_QZNN_K4Z_JAVA_SAME_RESULT_TYPE: return new TestCaseCtrE126QznnK4zJavaSameResultType(testCaseConfig);
            case E_127_LZNN_JAVA_NIU_TYPE: return new TestCaseCtrE127LznnJavaNiuType(testCaseConfig);
            case E_BANKER_ALL_WIN_LOSS: return new TestCaseCtrEVieBankerAllWinLoss(testCaseConfig);
            case E_SLOT_CASCADE_WAY_GAME: return new TestCaseCtrESlotCascadeWayGame(testCaseConfig);
            case E_SLOT_CASCADE_LINE_GAME: return new TestCaseCtrESlotCascadeLineGame(testCaseConfig);
            case E_SLOT_CASCADE_WAY_GAME_AND_SCREEN: return new TestCaseCtrESlotCascadeWayGameAndScreen(testCaseConfig);
            case E_SLOT_CASCADE_TIMES: return new TestCaseCtrESlotCascadeTimes(testCaseConfig);
            case E_SLOT_GAMESTATE: return new TestCaseCtrESlotCascadeGameState(testCaseConfig);
            case E_FISH_SPECIFY_HIT_KILL_FLAG: return new TestCaseCtrEFishSpecifyHitKillFlag(testCaseConfig);
            case E_SLOT_ODDS_WIN_TYPE: return new TestCaseCtrESlotOddsWinType(testCaseConfig);
            default: return new TestCaseCtrDefault(testCaseConfig);
        }
    }
}
