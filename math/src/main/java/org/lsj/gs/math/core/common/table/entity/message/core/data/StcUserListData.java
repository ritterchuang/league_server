package org.lsj.gs.math.core.common.table.entity.message.core.data;

import org.lsj.gs.math.core.common.gamePlayerHlr.entity.GamePlayer;

// 玩家列表訊息資料
public class StcUserListData {
    public int chair; // 座位
    public String account; // 帳戶名稱
    public String headImgUrl; // 頭像ID
    public String ip; // IP
    public String nickName; // 暱稱
    public int uid; // 玩家索引
    public int boxid; // 包廂索引
    public int role; // 角色
    public int sex; // 性別
    public int state; // 狀態
    public int vipLevel; // VIP等級

    public StcUserListData(GamePlayer gamePlayer) {
        this.chair = gamePlayer.getChairIndex();
        this.account = gamePlayer.getAccount();
        this.headImgUrl = gamePlayer.getHeadImgUrl();
        this.ip = gamePlayer.getIp();
        this.nickName = gamePlayer.getNickName();
        this.uid = gamePlayer.getUid();
        this.boxid = gamePlayer.getBoxid();
        this.role = gamePlayer.getRole();
        this.sex = gamePlayer.getSex();
        this.state = gamePlayer.getState();
        this.vipLevel = gamePlayer.getVipLevel();
    }
}