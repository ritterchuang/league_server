package org.lsj.gs.math.core.common.poolCtr.entity.personControlHlr.personControlStrategy;

import org.lsj.gs.user.IUser;

import java.util.Objects;

public class AgentPersonControlStrategy extends AbstractPersonControlStrategy{
    private int[] valueStart; // 檢測區間

    public AgentPersonControlStrategy() {
        super();
    }

    @Override
    public boolean isSatisfyStrategy(IUser user) {
        // 1. 讀取資料防呆
        if (Objects.isNull(valueStart) || valueStart.length == 0) {
            return false;
        }

        // 2. rangeId 不為0，不做事
        if (super.rangeId != 0) {
            return false;
        }

        // 3. 玩家agent在列表才做事
        for (int valueIndex = 0; valueIndex < valueStart.length; valueIndex++) {
            if (valueStart[valueIndex] == user.getBaseAgencyId()) {
                return true;
            }
        }

        return false;
    }
}
