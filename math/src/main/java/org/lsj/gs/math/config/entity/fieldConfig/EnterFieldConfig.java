package org.lsj.gs.math.config.entity.fieldConfig;

// 入房設定
public class EnterFieldConfig {
    public int companyId; // 公司識別碼
    public int master; // 房主
    public int roomid; // 房間識別碼(包廂則為boxid)
    public int gameid; // 遊戲識別碼
    public int fid; // 房間識別碼
    public int maxPlayer; // 最大玩家數
    public int moneyType; // 貨幣類型
    public int roomType; // 房間類型
    public int minlimit; // 入桌下限
    public int maxlimit; // 入桌上限
    public int kicklimit; //
    public int feeType; // 手續費類型
    public int fee; // 手續費率
    public boolean watermatch; // 匹配標誌
    public int gameKind; // 遊戲類型
    public RuleConfig rule; // 牌桌設定
    public String fnameCN; // 房間中文名稱
    public int[] areasTopLimit; // 單點限額列表
}
