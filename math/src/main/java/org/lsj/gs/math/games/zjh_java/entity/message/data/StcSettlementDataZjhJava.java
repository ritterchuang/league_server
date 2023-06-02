package org.lsj.gs.math.games.zjh_java.entity.message.data;

// 結算分數資料
public class StcSettlementDataZjhJava {
    public int uid; // 玩家識別碼
    public double update; // 更新金額
    public double result; // 結算金額

    public StcSettlementDataZjhJava(int uid, double update, double result) {
        this.uid = uid;
        this.update = update;
        this.result = result;
    }
}
