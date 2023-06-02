package org.lsj.gs.math.config.entity.fieldConfig;

// 牌桌玩法設定
public class RuleConfig {
    /* 通用規則*/
    public int play; // 玩法
    public int base; // 底注
    public int user; // 玩家人數

    /* 包廂規則相關 */
    public int[] round; // 包廂規則 房主付費
    public int[] AA; // AA制

    /* 客制規則 */
    public FieldConfigExtend extend; // 客制牌桌規則
}
