package org.lsj.websocket;

import org.lsj.utils.JsonUtil;

// 接收遊戲指令
public class ReceiveGameCommand {
    private final int uid; // 玩家識別碼
    private final String key; // 訊息識別碼字串
    private final String message; // 訊息字串

    public ReceiveGameCommand(int uid, String key, String message) {
        this.uid = uid;
        this.key = key;
        this.message = message;
    }

    public Object getUid() {
        return uid;
    }

    public String getKey() {
        return key;
    }

    public String getMessage() {
        return message;
    }

    public String toString() { return JsonUtil.getInstance().writeValueAsStringWithoutException(this); }
}
