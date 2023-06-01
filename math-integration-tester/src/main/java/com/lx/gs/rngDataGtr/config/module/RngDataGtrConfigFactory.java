package com.lx.gs.rngDataGtr.config.module;

import com.lx.gs.rngDataGtr.config.entity.RngDataGtrConfig;
import com.lx.gs.rngDataGtr.config.resources.ConstRndGtrConfig;

// 亂數產生器設定工廠
public class RngDataGtrConfigFactory {

    public RngDataGtrConfig getRngDataGtrConfig(ConstRndGtrConfig.RngDataGtrResourceEnum rngDataGtrResourceEnum){
        return rngDataGtrResourceEnum.getRngDataGtrConfig();
    }
}
