package org.lsj.gs.math.core.common.poolCtr.entity.personControlHlr.personControlStrategy;

import org.lsj.gs.user.IUser;

import java.util.Objects;

// IP個人控策略
public class IpPersonControlStrategy extends AbstractPersonControlStrategy{
    private String[] valueStart; // 檢測區間

    // 原始建構子提供JSON解析用
    public IpPersonControlStrategy() {
        super();
    }

    public IpPersonControlStrategy(String key, int value, String name, boolean isChecked, long regTimeStart, long regTimeEnd, int rangeId, int valueEnd, String[] valueStart) {
        super(key, value, name, isChecked, regTimeStart, regTimeEnd, rangeId, valueEnd);
        this.valueStart = valueStart;
    }

    // 檢驗策略符合性
    @Override
    public boolean isSatisfyStrategy(IUser user) {
        // 1. 防呆設定
        if (Objects.isNull(valueStart) || valueStart.length == 0) {
            return false;
        }

        // 2. 防呆判斷類型
        if (super.rangeId != 0) {
            return false;
        }

        // 3. 檢驗玩家IP符合性
        for (String ip : valueStart) {
            if (ip.equals(user.getIp())) {
                return true;
            }
        }

        return false;
    }
}
