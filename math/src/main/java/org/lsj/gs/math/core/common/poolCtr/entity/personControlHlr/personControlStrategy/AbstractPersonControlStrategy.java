package org.lsj.gs.math.core.common.poolCtr.entity.personControlHlr.personControlStrategy;

import org.lsj.gs.user.IUser;

// 個人控策略設定
public abstract class AbstractPersonControlStrategy {
    protected String key; // 策略鑑值
    protected int value; // 策略設定值
    protected String name; // 策略名稱
    protected boolean isChecked; // 使用策略標誌
    protected long regTimeStart; // 設定起始時間(無用)
    protected long regTimeEnd; // 設定結束時間(無用)
    protected int rangeId; // 判斷範圍類型; 0: 值相等; 1: 小於等於上限; 2: 大於等於下限; 3: 小於等於隨機區間數; 4: 大於等於隨機區間數;
    protected int valueEnd; // 判斷範圍上限

    // 原始建構子提供JSON解析用
    public AbstractPersonControlStrategy() {}

    public AbstractPersonControlStrategy(String key, int value, String name, boolean isChecked, long regTimeStart, long regTimeEnd, int rangeId, int valueEnd) {
        this.key = key;
        this.value = value;
        this.name = name;
        this.isChecked = isChecked;
        this.regTimeStart = regTimeStart;
        this.regTimeEnd = regTimeEnd;
        this.rangeId = rangeId;
        this.valueEnd = valueEnd;
    }

    // 檢驗策略符合性
    public abstract boolean isSatisfyStrategy(IUser user);
}
