package org.lsj.gs.math.core.common.table.entity.message;

import org.lsj.utils.JsonUtil;

public abstract class AbstractToStringStruct {
    public String toString() { return JsonUtil.getInstance().writeValueAsStringWithoutException(this); }
}
