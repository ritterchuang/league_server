package org.lsj.gs.math.core.common.poolCtr.module.personControlHlr.dataGtr;

import org.lsj.gs.math.core.common.poolCtr.entity.personControlHlr.personControlStrategy.AbstractPersonControlStrategy;
import org.lsj.gs.math.core.common.poolCtr.entity.personControlHlr.personControlStrategy.ThirdAccountIdPersonControlStrategy;
import org.lsj.utils.JsonUtil;

import java.util.ArrayList;

// 個人控策略JSON格式資料產生器
public class ControlStrategyJsonDataGtr {

    public String getDefaultControlStrategyJson(){
        return "[]";
    }

    // 三方帳號編碼控制策略
    public String getThirdAccountIdControlStrategyJson(int thirdAccountId) {
        return JsonUtil.getInstance().writeValueAsStringWithoutException(new ArrayList<AbstractPersonControlStrategy>(){{
            add(new ThirdAccountIdPersonControlStrategy(
                    "userIds",
                    0,
                    "用户ID",
                    true,
                    0,
                    0,
                    0,
                    0,
                    (new ArrayList<Integer>(){{
                        add(thirdAccountId);
                    }}).stream().mapToInt(i -> i).toArray()
            ));
        }});
    }
}
