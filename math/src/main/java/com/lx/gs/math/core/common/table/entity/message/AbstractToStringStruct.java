package com.lx.gs.math.core.common.table.entity.message;

import com.lx.utils.JsonUtil;

public abstract class AbstractToStringStruct {
    public String toString() { return JsonUtil.getInstance().writeValueAsStringWithoutException(this); }
}
