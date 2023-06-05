package org.lsj.gs.mathStr.config.resources;

import org.lsj.gs.mathStr.config.entity.*;
import org.lsj.gs.mathStr.config.resources.agencyStn.AgencyStnR00NotOutputRawData;
import org.lsj.gs.mathStr.config.resources.agencyStn.AgencyStnR01OutputRawData;
import org.lsj.gs.mathStr.config.resources.controlAlgorithm.*;
import org.lsj.gs.mathStr.config.resources.gameCenterMgr.*;
import org.lsj.gs.mathStr.config.resources.gamePlayerFactory.GamePlayerFactoryR00M200WB50;
import org.lsj.gs.mathStr.config.resources.gamePlayerFactory.GamePlayerFactoryR01M2TAnd200WB50;
import org.lsj.gs.mathStr.config.resources.gamePlayerFactory.GamePlayerFactoryR02M1BB1M;
import org.lsj.gs.mathStr.config.resources.gamePlayerFactory.GamePlayerFactoryR98M2HB50;
import org.lsj.gs.mathStr.config.resources.poolCtr.PoolCtrR00R30;
import org.lsj.gs.mathStr.config.resources.poolCtr.PoolCtrR01R25;
import org.lsj.gs.mathStr.config.resources.poolCtr.PoolCtrR98ErrorPool;
import org.lsj.gs.mathStr.config.resources.poolCtr.PoolCtrR99NullPool;
import org.lsj.gs.mathStr.config.resources.scenarioHlr.*;

// 模擬器資源定義
public class ConstStrConfigResource {
    // 強控演算法設定資源
    public enum ControlAlgorithmConfigResourceEnum {
        R_00_NO_DEBUG(new ControlAlgorithmR00NoDebug().create()),
        R_10_NONE_NONE_NATURE_NORMAL(new ControlAlgorithmR10NoneNoneNatureNormal().create()),
        R_11_NONE_NONE_PN_NORMAL(new ControlAlgorithmR11NoneNonePseudoNatureNormal().create()),
        R_20_NONE_GRADIENT_NATURE_NORMAL(new ControlAlgorithmR20NoneGradientNatureNormal().create()),
        R_21_NONE_GRADIENT_NATURE_WHITE1(new ControlAlgorithmR21NoneGradientNatureWhite1().create()),
        R_22_NONE_GRADIENT_NATURE_BLACK1(new ControlAlgorithmR22NoneGradientNatureBlack1().create()),
        R_30_PAPG_GRADIENT_NATURE_NORMAL(new ControlAlgorithmR30PapgGradientNatureNormal().create()),
        R_31_PAPG_GRADIENT_NATURE_WHITE1(new ControlAlgorithmR31PapgGradientNatureWhite1().create()),
        R_32_PAPG_GRADIENT_NATURE_BLACK1(new ControlAlgorithmR32PapgGradientNatureBlack1().create()),
        R_40_PAPG_NONE_NATURE_NORMAL(new ControlAlgorithmR40PapgNoneNatureNormal().create()),
        R_41_PAPG_NONE_NATURE_WHITE1(new ControlAlgorithmR41PapgNoneNatureWhite1().create()),
        R_42_PAPG_NONE_NATURE_BLACK1(new ControlAlgorithmR42PapgNoneNatureBlack1().create()),
        R_50_PAPG_NONE_PN_NORMAL(new ControlAlgorithmR50PapgNonePseudoNatureNormal().create()),
        R_51_PAPG_NONE_PN_WHITE1(new ControlAlgorithmR51PapgNonePseudoNatureWhite1().create()),
        R_52_PAPG_NONE_PN_BLACK1(new ControlAlgorithmR52PapgNonePseudoNatureBlack1().create());

        private final ControlAlgorithmConfig controlAlgorithmConfig;
        ControlAlgorithmConfigResourceEnum(ControlAlgorithmConfig controlAlgorithmConfig) {
            this.controlAlgorithmConfig = controlAlgorithmConfig;
        }

        public ControlAlgorithmConfig getConfigResource() {
            return controlAlgorithmConfig;
        }
    }

    // 遊戲中心管理器設定資源
    public enum GameCenterMgrConfigResourceEnum {
        R_001_LUCKY777_NORMAL(new GameCenterMgrR001Lucky777Normal().create()),

        R_98_HIGH1000MIN_NORMAL(new GameCenterMgrR98High1000MinNormal().create()),
        R_99_MIX_GAME(new GameCenterMgrR99MixGame().create()),
        R_105_HHDZ_JAVA_NORMAL(new GameCenterMgrR105HhdzJavaNormal().create()),
        R_109_BJL_JAVA_NORMAL(new GameCenterMgrR109BjlJavaNormal().create()),
        R_110_BRNN_JAVA_NORMAL(new GameCenterMgrR110BrnnJavaNormal().create()),
        R_111_LHD_JAVA_NORMAL(new GameCenterMgrR111LhdJavaNormal().create()),
        R_112_ZJH_JAVA_NORMAL(new GameCenterMgrR112ZjhJavaNormal().create()),
        R_113_QZNN_JAVA_NORMAL(new GameCenterMgrR113QznnJavaNormal().create()),
        R_113_QZNN_JAVA_TIMEOUT(new GameCenterMgrR113QznnJavaTimeout().create()),
        R_114_EBG_JAVA_NORMAL(new GameCenterMgrR114EbgJavaNormal().create()),
        R_115_QZNN_KSZ_JAVA_NORMAL(new GameCenterMgrR115QznnKszJavaNormal().create()),
        R_115_QZNN_KSZ_JAVA_TIMEOUT(new GameCenterMgrR115QznnKszJavaTimeout().create()),
        R_117_SG_JAVA_NORMAL(new GameCenterMgrR117SgJavaNormal().create()),
        R_122_QZPJ_JAVA_NORMAL(new GameCenterMgrR122QzpjJavaNormal().create()),
        R_125_TBNN_JAVA_NORMAL(new GameCenterMgrR125TbnnJavaNormal().create()),
        R_126_QZNN_K4Z_JAVA_NORMAL(new GameCenterMgrR126QznnK4zJavaNormal().create()),
        R_127_LZNN_JAVA_NORMAL(new GameCenterMgrR127LznnJavaNormal().create()),
        R_129_MYBJL_JAVA_NORMAL(new GameCenterMgrR129MybjlJavaNormal().create()),
        R_132_CJNN_JAVA_NORMAL(new GameCenterMgrR132CjnnJavaNormal().create()),

        R_201_JCBY_JAVA_NORMAL(new GameCenterMgrR201JcbyJavaRandomTarget().create()),
        R_202_CSBY_JAVA_NORMAL(new GameCenterMgrR202CsbyJavaRandomTarget().create()),
        R_203_SLBY_JAVA_NORMAL(new GameCenterMgrR203SlbyJavaRandomTarget().create()),
        R_204_RYCS_JAVA_NORMAL(new GameCenterMgrR204RycsJavaRandomTarget().create()),
        R_205_SZZB_JAVA_NORMAL(new GameCenterMgrR205SzzbJavaRandomTarget().create()),

        R_301_PXKY_JAVA_NORMAL(new GameCenterMgrR301PxkyJavaNormal().create()),
        R_302_DYDB_JAVA_NORMAL(new GameCenterMgrR302DydbJavaNormal().create()),
        R_303_SDZW_JAVA_NORMAL(new GameCenterMgrR303SdzwJavaNormal().create()),
        R_304_WL_JAVA_NORMAL(new GameCenterMgrR304WlJavaNormal().create()),
        R_305_MJWS_JAVA_NORMAL(new GameCenterMgrR305MjwsJavaNormal().create()),
        R_306_BSZX_JAVA_NORMAL(new GameCenterMgrR306BszxJavaNormal().create()),
        R_307_CCC_JAVA_NORMAL(new GameCenterMgrR307CccJavaNormal().create()),
        R_308_SBXC_JAVA_NORMAL(new GameCenterMgrR308SbxcJavaNormal().create()),
        R_309_SN_JAVA_NORMAL(new GameCenterMgrR309SnJavaNormal().create()),
        R_310_SWZS_JAVA_NORMAL(new GameCenterMgrR310SwzsJavaNormal().create()),
        R_311_LMJJC_JAVA_NORMAL(new GameCenterMgrR311LmjjcJavaNormal().create()),
        R_312_DGRY_JAVA_NORMAL(new GameCenterMgrR312DgryJavaNormal().create()),
        R_313_LLL_JAVA_NORMAL(new GameCenterMgrR313LllJavaNormal().create()),
        R_314_OLLDBZ_JAVA_NORMAL(new GameCenterMgrR314OlldbzJavaNormal().create()),
        R_315_HJXB_JAVA_NORMAL(new GameCenterMgrR315HjxbJavaNormal().create()),
        R_316_XZCQ_JAVA_NORMAL(new GameCenterMgrR316XzcqJavaNormal().create()),
        R_320_CJWP_JAVA_NORMAL(new GameCenterMgrR320CjwpJavaNormal().create()),
        R_323_XJTB_JAVA_NORMAL(new GameCenterMgrR323XjtbJavaNormal().create()),
        R_324_ZCJZ_JAVA_NORMAL(new GameCenterMgrR324ZcjzJavaNormal().create()),
        R_982_DEVELOP_BY_JAVA_NORMAL(new GameCenterMgrR982DevelopByJavaRandomTarget().create()),
        R_983_DEVELOP_HJ_JAVA_NORMAL(new GameCenterMgrR983DevelopHjJavaNormal().create()),
        R_992_MODEL_BY_JAVA_NORMAL(new GameCenterMgrR992ModelByJavaRandomTarget().create()),
        R_993_MODEL_HJ_JAVA_NORMAL(new GameCenterMgrR993ModelHjJavaNormal().create());

        private final GameCenterMgrConfig gameCenterMgrConfig; // 遊戲中心管理器設定

        GameCenterMgrConfigResourceEnum(GameCenterMgrConfig gameCenterMgrConfig) {
            this.gameCenterMgrConfig = gameCenterMgrConfig;
        }

        public GameCenterMgrConfig getConfigResource() {
            return gameCenterMgrConfig;
        }
    }

    public enum UserFactoryConfigResourceEnum{
        R_00_M200W_B50(new GamePlayerFactoryR00M200WB50().create()),
        R_01_M2T_AND_200W_B50(new GamePlayerFactoryR01M2TAnd200WB50().create()),
        R_02_M1B_B1M(new GamePlayerFactoryR02M1BB1M().create()),
        R_98_M2H_B50(new GamePlayerFactoryR98M2HB50().create());

        private final GamePlayerFactoryConfig gamePlayerFactoryConfig;
        UserFactoryConfigResourceEnum(GamePlayerFactoryConfig gamePlayerFactoryConfig){
            this.gamePlayerFactoryConfig = gamePlayerFactoryConfig;
        }
        public GamePlayerFactoryConfig getConfigResource(){
            return this.gamePlayerFactoryConfig;
        }
    }

    // 水池設定資源
    public enum PoolCtrConfigResourceEnum {
        R_00_R30(new PoolCtrR00R30().create()),
        R_01_R25(new PoolCtrR01R25().create()),
        R_98_ERROR_POOL(new PoolCtrR98ErrorPool().create()),
        R_99_NULL_POOL(new PoolCtrR99NullPool().create());

        private final PoolCtrConfig poolCtrConfig;
        PoolCtrConfigResourceEnum(PoolCtrConfig poolCtrConfig){
            this.poolCtrConfig = poolCtrConfig;
        }
        public PoolCtrConfig getConfigResource(){
            return this.poolCtrConfig;
        }
    }

    // 情境處理器設定資源
    public enum ScenarioHlrConfigResourceEnum {
        R_00_B1T(new ScenarioHlrR00B1T().create()),
        R_01_B100T(new ScenarioHlrR01B100T().create()),
        R_02_B1M(new ScenarioHlrR02B1M().create()),
        R_03_B10M(new ScenarioHlrR03B10M().create()),
        R_04_B100M(new ScenarioHlrR04B100M().create());

        private final ScenarioHlrConfig scenarioHlrConfig;
        ScenarioHlrConfigResourceEnum(ScenarioHlrConfig scenarioHlrConfig){
            this.scenarioHlrConfig = scenarioHlrConfig;
        }
        public ScenarioHlrConfig getConfigResource() {
            return this.scenarioHlrConfig;
        }
    }

    // 統計設定資源
    public enum StnConfigResourceEnum{
        R_00_NOT_OUTPUT_RAW_DATA(new AgencyStnR00NotOutputRawData().create()), // 不輸出原始資料
        R_01_OUTPUT_RAW_DATA(new AgencyStnR01OutputRawData().create()); // 輸出原始資料

        private final AgencyStnConfig agencyStnConfig;
        StnConfigResourceEnum(AgencyStnConfig agencyStnConfig){
            this.agencyStnConfig = agencyStnConfig;
        }
        public AgencyStnConfig getConfigResource() {
            return this.agencyStnConfig;
        }
    }
}
