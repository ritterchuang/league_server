package org.lsj.gs.math.core.common.table.entity.message.core.data;

// 玩家餘額
public class StcMoneyData {
    public int uid; // 識別碼
    public double money; // 餘額

    public StcMoneyData(int uid, double money) {
        this.uid = uid;
        this.money = money;
    }
}
