package org.lsj.gs.math.core.common.table.entity.message.baiRen;

// 榜單
public class StcRichData {
    public int uid; // 識別碼
    public String nn; // 暱稱
    public String head; // 頭像
    public double money; // 餘額
    public int sex; // 性別
    public int vipLevel; // 等級
    public int type; // 種類

    public StcRichData(int uid, String nn, String head, double money, int sex, int vipLevel, int type) {
        this.uid = uid;
        this.nn = nn;
        this.head = head;
        this.money = money;
        this.sex = sex;
        this.vipLevel = vipLevel;
        this.type = type;
    }
}
