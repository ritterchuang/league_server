package org.lsj.gs.math.config.module;

import org.lsj.gs.math.config.entity.tableGameConfig.AbstractTableGameConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.ConstTableGameConfig.TableGameConfigResource;
import org.lsj.gs.math.config.resources.tableGameConfig.games._default.TableGameResourceDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games._develop_by_java.TableGameResourceDevelopByJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games._develop_hj_java.TableGameResourceDevelopHjJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games._model_by_java.TableGameResourceModelByJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games._model_hj_java.TableGameResourceModelHjJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.bjl_java.TableGameResourceBjlJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.brnn_java.TableGameResourceBrnnJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.bszx_java.TableGameResourceBszxJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.ccc_java.TableGameResourceCccJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.cjnn_java.TableGameResourceCjnnJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.cjwp_java.TableGameResourceCjwpJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.csby_java.TableGameResourceCsbyJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.dgry_java.TableGameResourceDgryJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.dydb_java.TableGameResourceDydbJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.ebg_java.TableGameResourceEbgJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.hhdz_java.TableGameResourceHhdzJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.hjxb_java.TableGameResourceHjxbJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.jcby_java.TableGameResourceJcbyJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.lhd_java.TableGameResourceLhdJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.lll_java.TableGameResourceLllJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.lmjjc_java.TableGameResourceLmjjcJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.lucky777.TableGameResourceLucky777Default;
import org.lsj.gs.math.config.resources.tableGameConfig.games.lznn_java.TableGameResourceLznnJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.mjws_java.TableGameResourceMjwsJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.mybjl_java.TableGameResourceMybjlJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.olldbz_java.TableGameResourceOlldbzJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.pxky_java.TableGameResourcePxkyJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.qznn_java.TableGameResourceQznnJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.qznn_k4z_java.TableGameResourceQznnK4zJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.qznn_ksz_java.TableGameResourceQznnKszJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.qzpj_java.TableGameResourceQzpjJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.rycs_java.TableGameResourceRycsJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.sbxc_java.TableGameResourceSbxcJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.sdzw_java.TableGameResourceSdzwJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.sg_java.TableGameResourceSgJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.slby_java.TableGameResourceSlbyJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.sn_java.TableGameResourceSnJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.swzs_java.TableGameResourceSwzsJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.szzb_java.TableGameResourceSzzbJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.tbnn_java.TableGameResourceTbnnJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.wl_java.TableGameResourceWlJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.xjtb_java.TableGameResourceXjtbJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.xzcq_java.TableGameResourceXzcqJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.zcjz_java.TableGameResourceZcjzJavaDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.games.zjh_java.TableGameResourceZjhJavaDefault;

import java.util.HashMap;
import java.util.Map;

// 牌桌遊戲設定檔讀取器
public class TableGameConfigReader {
    private static final TableGameConfigReader instance = new TableGameConfigReader();
    private final Map<TableGameConfigResource, AbstractTableGameConfig> tableGameConfigMap; // 牌桌遊戲設定對應表

    public static TableGameConfigReader getInstance() {
        return instance;
    }

    public TableGameConfigReader() {
        this.tableGameConfigMap = new HashMap<>();
        this.tableGameConfigMap.put(TableGameConfigResource.LUCKY777_DEFAULT, new TableGameResourceLucky777Default().create());

        this.tableGameConfigMap.put(TableGameConfigResource.HHDZ_JAVA_DEFAULT, new TableGameResourceHhdzJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.BJL_JAVA_DEFAULT, new TableGameResourceBjlJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.BRNN_JAVA_DEFAULT, new TableGameResourceBrnnJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.LHD_JAVA_DEFAULT, new TableGameResourceLhdJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.ZJH_JAVA_DEFAULT, new TableGameResourceZjhJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.QZNN_JAVA_DEFAULT, new TableGameResourceQznnJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.EBG_JAVA_DEFAULT, new TableGameResourceEbgJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.QZNN_KSZ_JAVA_DEFAULT, new TableGameResourceQznnKszJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.SG_JAVA_DEFAULT, new TableGameResourceSgJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.QZPJ_JAVA_DEFAULT, new TableGameResourceQzpjJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.TBNN_JAVA_DEFAULT, new TableGameResourceTbnnJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.QZNN_K4Z_JAVA_DEFAULT, new TableGameResourceQznnK4zJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.LZNN_JAVA_DEFAULT, new TableGameResourceLznnJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.MYBJL_JAVA_DEFAULT, new TableGameResourceMybjlJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.CJNN_JAVA_DEFAULT, new TableGameResourceCjnnJavaDefault().create());

        this.tableGameConfigMap.put(TableGameConfigResource.JCBY_JAVA_DEFAULT, new TableGameResourceJcbyJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.CSBY_JAVA_DEFAULT, new TableGameResourceCsbyJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.SLBY_JAVA_DEFAULT, new TableGameResourceSlbyJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.RYCS_JAVA_DEFAULT, new TableGameResourceRycsJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.SZZB_JAVA_DEFAULT, new TableGameResourceSzzbJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.MODEL_BY_JAVA_DEFAULT, new TableGameResourceModelByJavaDefault().create());

        this.tableGameConfigMap.put(TableGameConfigResource.PXKY_JAVA_DEFAULT, new TableGameResourcePxkyJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.DYDB_JAVA_DEFAULT, new TableGameResourceDydbJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.SDZW_JAVA_DEFAULT, new TableGameResourceSdzwJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.WL_JAVA_DEFAULT, new TableGameResourceWlJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.MJWS_JAVA_DEFAULT, new TableGameResourceMjwsJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.BSZX_JAVA_DEFAULT, new TableGameResourceBszxJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.CCC_JAVA_DEFAULT, new TableGameResourceCccJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.SBXC_JAVA_DEFAULT, new TableGameResourceSbxcJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.SN_JAVA_DEFAULT, new TableGameResourceSnJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.SWZS_JAVA_DEFAULT, new TableGameResourceSwzsJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.LMJJC_JAVA_DEFAULT, new TableGameResourceLmjjcJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.DGRY_JAVA_DEFAULT, new TableGameResourceDgryJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.LLL_JAVA_DEFAULT, new TableGameResourceLllJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.XZCQ_JAVA_DEFAULT, new TableGameResourceXzcqJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.OLLDBZ_JAVA_DEFAULT, new TableGameResourceOlldbzJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.HJXB_JAVA_DEFAULT, new TableGameResourceHjxbJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.CJWP_JAVA_DEFAULT, new TableGameResourceCjwpJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.XJTB_JAVA_DEFAULT, new TableGameResourceXjtbJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.ZCJZ_JAVA_DEFAULT, new TableGameResourceZcjzJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.MODEL_HJ_JAVA_DEFAULT, new TableGameResourceModelHjJavaDefault().create());

        this.tableGameConfigMap.put(TableGameConfigResource.DEVELOP_BY_JAVA_DEFAULT, new TableGameResourceDevelopByJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.DEVELOP_HJ_JAVA_DEFAULT, new TableGameResourceDevelopHjJavaDefault().create());
        this.tableGameConfigMap.put(TableGameConfigResource.DEFAULT, new TableGameResourceDefault().create());
    }

    public AbstractTableGameConfig getTableGameConfig(TableGameConfigResource tableGameConfigResource) {
        return this.tableGameConfigMap.get(tableGameConfigResource);
    }
}
