package org.lsj.gs.rngDataGtr.core.module.rngDataGtr;

import org.lsj.gs.rngDataGtr.config.entity.RngDataGtrConfig;

public class RngDataGtrFactory {

    public IRngDataGtr create(RngDataGtrConfig rngDataGtrConfig){
        switch(rngDataGtrConfig.getBoardGtrConfig().getPlayGameFieldConfig().getGameType()){
            case CARD: return new RngDataGtrCard(rngDataGtrConfig);
            case BAIREN: return new RngDataGtrBaiRen(rngDataGtrConfig);
            case SLOT: return new RngDataGtrSlot(rngDataGtrConfig);
            case FISH: return new RngDataGtrFish(rngDataGtrConfig);
            default: return new RngDataGtrDefault(rngDataGtrConfig);
        }
    }
}
