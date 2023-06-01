package com.lx.gs.math.core.common.poolCtr.module.personControlHlr.dataGtr;

import com.lx.gs.math.core.common.poolCtr.entity.personControlHlr.NumericalObj;
import com.lx.utils.JsonUtil;

import java.util.ArrayList;

// 數控物件資料產生器
public class NumericalObjDataGtr {

    // 個人控數值設定
    public String getDefaultNumericalJsonString() {
        return JsonUtil.getInstance().writeValueAsStringWithoutException(
            new ArrayList<>(){{
                add(new NumericalObj(1, "白1", 50));
                add(new NumericalObj(2, "黑1", 50));
            }}
        );
    }

    public String getOnlyWhite1NumericalJsonString(){
        return JsonUtil.getInstance().writeValueAsStringWithoutException(
                new ArrayList<>(){{
                    add(new NumericalObj(1, "白1", 100));
                }}
        );
    }
}
