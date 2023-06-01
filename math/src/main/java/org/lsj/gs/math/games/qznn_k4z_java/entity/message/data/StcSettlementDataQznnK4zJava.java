package org.lsj.gs.math.games.qznn_k4z_java.entity.message.data;

// 結算分數資料
public class StcSettlementDataQznnK4zJava {
    public int uid; // 玩家識別碼
    public double update; // 更新金額
    public double result; // 結算金額

    public StcSettlementDataQznnK4zJava(int uid, double update, double result) {
        this.uid = uid;
        this.update = update;
        this.result = result;
    }
}
