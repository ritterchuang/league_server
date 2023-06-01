package org.lsj.gs.rngDataGtr.config.module;

import org.lsj.gs.rngDataGtr.config.entity.RngDataGtrConfig;
import org.lsj.gs.rngDataGtr.config.resources.ConstRndGtrConfig;

// 亂數產生器設定工廠
public class RngDataGtrConfigFactory {

    public RngDataGtrConfig getRngDataGtrConfig(ConstRndGtrConfig.RngDataGtrResourceEnum rngDataGtrResourceEnum){
        return rngDataGtrResourceEnum.getRngDataGtrConfig();
    }
}
