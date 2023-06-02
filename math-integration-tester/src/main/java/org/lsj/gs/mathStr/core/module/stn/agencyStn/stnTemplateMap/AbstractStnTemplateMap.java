package org.lsj.gs.mathStr.core.module.stn.agencyStn.stnTemplateMap;

import org.lsj.gs.mathStr.config.entity.AgencyStnConfig;
import org.lsj.gs.mathStr.core.module.stn.TemplateStn;
import org.lsj.gs.mathStr.core.module.stn.agencyStn.AbstractStn;

import java.util.Map;
import java.util.Objects;

// 抽象統計者
public abstract class AbstractStnTemplateMap extends AbstractStn {
    public AbstractStnTemplateMap(AgencyStnConfig config) {
        super(config);
    }

    // 取得統計物件
    protected TemplateStn getStn(Map<Integer, TemplateStn> stnMap, int mapIndex){
        // 1. 取得對應統計物件
        TemplateStn stn = stnMap.get(mapIndex);

        // 2. 有取到則回傳
        if(!Objects.isNull(stn)){
            return stn;
        }

        // 3. 無取到則創立
        return new TemplateStn();
    }
}
