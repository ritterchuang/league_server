package org.lsj.gs.rngDataGtr.config.resources;

import org.lsj.gs.rngDataGtr.config.entity.RngDataGtrConfig;

// 亂數產生器設定定義
public class ConstRndGtrConfig {
    public enum RngDataGtrResourceEnum{
        R_105_HHDZ_JAVA(new RndGtrR105HhdzJava().create()), // 新紅黑大戰
        R_109_BJL_JAVA(new RndGtrR109BjlJava().create()), // 新百家樂
        R_110_BRNN_JAVA(new RndGtrR110BrnnJava().create()), // 新百人牛牛
        R_111_LHD_JAVA(new RndGtrR111LhdJava().create()), // 新龍虎鬥
        R_113_QZNN_JAVA(new RndGtrR113QznnJava().create()), // 新搶莊牛牛
        R_114_EBG_JAVA(new RndGtrR114EbgJava().create()), // 新搶莊二八槓
        R_115_QZNN_KSZ_JAVA(new RndGtrR115QznnKszJava().create()), // 新看三張搶莊牛牛
        R_117_SG_JAVA(new RndGtrR117SgJava().create()), // 新三公
        R_122_QZPJ_JAVA(new RndGtrR122QzpjJava().create()), // 新搶莊牌九
        R_125_TBNN_JAVA(new RndGtrR125TbnnJava().create()), // 新通比牛牛
        R_126_QZNN_K4Z_JAVA(new RndGtrR126QznnK4ZJava().create()), // 新看四張搶莊牛牛
        R_127_LZNN_JAVA(new RndGtrR127LznnJava().create()), // 新賴子牛牛
        R_132_CJNN_JAVA(new RndGtrR132CjnnJava().create()), // 新超級牛牛

        R_201_JCBY_JAVA(new RndGtrR201JcbyJava().create()), // 金蟾捕魚
        R_203_SLBY_JAVA(new RndGtrR203SlbyJava().create()), // 神龍捕魚

        R_301_PXKY_JAVA(new RndGtrR301PxkyJava().create()), // 貔貅開運
        R_302_DYDB_JAVA(new RndGtrR302DydbJava().create()), // 大運奪寶
        R_303_SDZW_JAVA(new RndGtrR303SdzwJava().create()), // 聖誕任務
        R_305_MJWS_JAVA(new RndGtrR305MjwsJava().create()), // 麻將無雙
        R_307_CCC_JAVA(new RndGtrR307CccJava().create()),   // 777
        R_308_SBXC_JAVA(new RndGtrR308SbxcJava().create()), // 三倍小丑
        R_309_SN_JAVA(new RndGtrR309SnJava().create()), // 水牛
        R_310_SWZS_JAVA(new RndGtrR310SwzsJava().create()), // 死亡之書
        R_311_LMJJC_JAVA(new RndGtrR311LmjjcJava().create()), // 羅馬競技場
        R_312_DGRY_JAVA(new RndGtrR312DgryJava().create()), // 帝國榮耀
        R_313_LLL_JAVA(new RndGtrR313LllJava().create()),   // 龍龍龍
        R_314_OLLDBZ_JAVA(new RndGtrR314OlldbzJava().create()),   // 歐賴利的寶藏
        R_315_HJXB_JAVA(new RndGtrR315HjxbJava().create()),   // 黃金西部
        R_316_XZCQ_JAVA(new RndGtrR316XzcqJava().create()),   // 小豬傳奇
        R_320_CJWP_JAVA(new RndGtrR320CjwpJava().create());   // 超級王牌

        private final RngDataGtrConfig rngDataGtrConfig;

        RngDataGtrResourceEnum(RngDataGtrConfig rngDataGtrConfig) {
            this.rngDataGtrConfig = rngDataGtrConfig;
        }

        public RngDataGtrConfig getRngDataGtrConfig() {
            return rngDataGtrConfig;
        }
    }
}
