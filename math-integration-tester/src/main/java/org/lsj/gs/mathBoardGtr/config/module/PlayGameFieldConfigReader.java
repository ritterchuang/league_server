package org.lsj.gs.mathBoardGtr.config.module;

import org.lsj.gs.mathBoardGtr.config.entity.playGameField.ConstPlayGameField;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathBoardGtr.config.resources.playGameField.*;

// 遊戲場設定讀取器
public class PlayGameFieldConfigReader {
    public PlayGameFieldConfig getConfig(ConstPlayGameField.PlayGameFieldResource playGameFieldResource){
        switch(playGameFieldResource){
            case R001_LUCKY777_NORMAL: return new PlayGameFieldR001Lucky777Normal().create();

            case R105_HHDZ_JAVA_NORMAL: return new PlayGameFieldR105HhdzJavaNormal().create();
            case R109_BJL_JAVA_NORMAL: return new PlayGameFieldR109BjlJavaNormal().create();
            case R110_BRNN_JAVA_NORMAL: return new PlayGameFieldR110BrnnJavaNormal().create();
            case R111_LHD_JAVA_NORMAL: return new PlayGameFieldR111LhdJavaNormal().create();
            case R112_ZJH_JAVA_NORMAL: return new PlayGameFieldR112ZjhJavaNormal().create();
            case R113_QZNN_JAVA_NORMAL: return new PlayGameFieldR113QznnJavaNormal().create();
            case R113_QZNN_JAVA_HIGH_MIN_NORMAL: return new PlayGameFieldR113QznnJavaHighMinNormal().create();
            case R113_QZNN_JAVA_TIMEOUT: return new PlayGameFieldR113QznnJavaTimeout().create();
            case R114_EBG_JAVA_NORMAL: return new PlayGameFieldR114EbgJavaNormal().create();
            case R115_QZNN_KSZ_JAVA_NORMAL: return new PlayGameFieldR115QznnKszJavaNormal().create();
            case R115_QZNN_KSZ_JAVA_TIMEOUT: return new PlayGameFieldR115QznnKszJavaTimeout().create();
            case R117_SG_JAVA_NORMAL: return new PlayGameFieldR117SgJavaNormal().create();
            case R122_QZPJ_JAVA_NORMAL: return new PlayGameFieldR122QzpjJavaNormal().create();
            case R125_TBNN_JAVA_NORMAL: return new PlayGameFieldR125TbnnJavaNormal().create();
            case R126_QZNN_K4Z_JAVA_NORMAL: return new PlayGameFieldR126QznnK4zJavaNormal().create();
            case R127_LZNN_JAVA_NORMAL: return new PlayGameFieldR127LznnJavaNormal().create();
            case R129_MYBJL_JAVA_NORMAL: return new PlayGameFieldR129MybjlJavaNormal().create();
            case R132_CJNN_JAVA_NORMAL: return new PlayGameFieldR132CjnnJavaNormal().create();

            case R201_JCBY_JAVA_ONE_TARGET: return new PlayGameFieldR201JcbyJavaOneTarget().create();
            case R201_JCBY_JAVA_RANDOM_TARGET: return new PlayGameFieldR201JcbyJavaRandomTarget().create();
            case R202_CSBY_JAVA_RANDOM_TARGET: return new PlayGameFieldR202CsbyJavaRandomTarget().create();
            case R203_SLBY_JAVA_RANDOM_TARGET: return new PlayGameFieldR203SlbyJavaRandomTarget().create();
            case R204_RYCS_JAVA_RANDOM_TARGET: return new PlayGameFieldR204RycsJavaRandomTarget().create();
            case R205_SZZB_JAVA_RANDOM_TARGET: return new PlayGameFieldR205SzzbJavaRandomTarget().create();

            case R301_PXKY_JAVA_NORMAL: return new PlayGameFieldR301PxkyJavaNormal().create();
            case R302_DYDB_JAVA_NORMAL: return new PlayGameFieldR302DydbJavaNormal().create();
            case R303_SDZW_JAVA_NORMAL: return new PlayGameFieldR303SdzwJavaNormal().create();
            case R304_WL_JAVA_NORMAL: return new PlayGameFieldR304WlJavaNormal().create();
            case R305_MJWS_JAVA_NORMAL: return new PlayGameFieldR305MjwsJavaNormal().create();
            case R306_BSZX_JAVA_NORMAL: return new PlayGameFieldR306BszxJavaNormal().create();
            case R307_CCC_JAVA_NORMAL: return new PlayGameFieldR307CccJavaNormal().create();
            case R308_SBXC_JAVA_NORMAL: return new PlayGameFieldR308SbxcJavaNormal().create();
            case R309_SN_JAVA_NORMAL: return new PlayGameFieldR309SnJavaNormal().create();
            case R310_SWZS_JAVA_NORMAL: return new PlayGameFieldR310SwzsJavaNormal().create();
            case R311_LMJJC_JAVA_NORMAL: return new PlayGameFieldR311LmjjcJavaNormal().create();
            case R312_DGRY_JAVA_NORMAL: return new PlayGameFieldR312DgryJavaNormal().create();
            case R313_LLL_JAVA_NORMAL: return new PlayGameFieldR313LllJavaNormal().create();
            case R314_OLLDBZ_JAVA_NORMAL: return new PlayGameFieldR314OlldbzJavaNormal().create();
            case R315_HJXB_JAVA_NORMAL: return new PlayGameFieldR315HjxbJavaNormal().create();
            case R316_XZCQ_JAVA_NORMAL: return new PlayGameFieldR316XzcqJavaNormal().create();
            case R320_CJWP_JAVA_NORMAL: return new PlayGameFieldR320CjwpJavaNormal().create();
            case R323_XJTB_JAVA_NORMAL: return new PlayGameFieldR323XjtbJavaNormal().create();
            case R324_ZCJZ_JAVA_NORMAL: return new PlayGameFieldR324ZcjzJavaNormal().create();

            case R982_DEVELOP_BY_JAVA_RANDOM_TARGET: return new PlayGameFieldR982DevelopByJavaRandomTarget().create();
            case R983_DEVELOP_HJ_JAVA_NORMAL: return new PlayGameFieldR983DevelopHjJavaNormal().create();
            case R992_MODEL_BY_JAVA_RANDOM_TARGET: return new PlayGameFieldR992ModelByJavaRandomTarget().create();
            case R993_MODEL_HJ_JAVA_NORMAL: return new PlayGameFieldR993ModelHjJavaNormal().create();
            default: return new PlayGameFieldRDefault().create();
        }
    }
}
