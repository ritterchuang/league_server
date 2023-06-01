package org.lsj.gs.math.core.common.table.entity.detail;

// 玩家資訊詳細記錄
public class DetailUserInfo {
    public final int chair; // 座位索引
    public final double money; // 起始金額
    public final String account; // 玩家帳戶
    public final String nickName; // 玩家暱稱

    public DetailUserInfo(int chair, double money, String account, String nickName) {
        this.chair = chair;
        this.money = money;
        this.account = account;
        this.nickName = nickName;
    }
}
