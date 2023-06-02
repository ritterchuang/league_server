package org.lsj.gs.math.config.entity.fieldConfig;

// 客製牌桌設定
public class FieldConfigExtend {
    public int open; // 開啟匹配標誌 0: 關閉 1:開啟
    public int maxReal; // 最大真人數量
    public int round; // N局內真人不能重複匹配
    public int noSameIp; // 同IP不可同桌標誌 0: 關閉 1:開啟
    public int skinsMatch; // 多皮膚匹配標誌 0: 關閉 1:開啟
    public int maxRate; // 最大下注倍數
    public int isOrdered; // 坐下方式
    public double applySitDownMoney; // 申請入桌最小金額
    public double applyBankerMoney; // 申請上庄最小金額
    public double endBankerMoney; // 申請下庄最小金額
    public int[] areasTopLimit; // 單點限額列表
    public boolean isOpenByb; // 開啟博一博標誌
    public double[][] awardRateConfig; // 未知
}
