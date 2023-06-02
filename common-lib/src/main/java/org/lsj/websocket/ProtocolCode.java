package org.lsj.websocket;

public enum ProtocolCode {

    SUCCESS(0),
    FAIL(1),

    // LXApi的错误码
    // js 另外一組定義的錯誤碼, 可考慮抽出
    LX_CODE_FA_FAIL(11),
    LX_CODE_FA_LOGIN_USER_NOT_EXIST(1006),  // 玩家不存在(uid或账号不存在)
    LX_CODE_COM_SVR_ERROR(503),         // 服务器内部错误，例如数据库执行出错等

    COM_SVR_MAINTAIN(505),              // 服务器正在维护
    COM_INVALID_USER(601),              // 玩家数据异常，需要重新登录
    COM_FREQUENT_REQ(602),              // 请求太频繁了
    COM_INVALID_ARGS(702),              // 请求参数非法
    COM_INVALID_DATA(703),              // 获取的数据非法
    COM_INVALID_WALLET_TYPE(705),       // 无效的钱包类型
    COM_INVALID_COMPANYKEY(709),        // 非法的companyKey

    FA_LOGIN_DUPLICATE(1015),           // 账号重复登录

    FA_GAME_MONEY_NOT_ENOUGH(2001),     // 玩家携带不够，无法入桌
    FA_GAME_MONEY_TOO_MUCH(2002),       // 玩家携带太多，无法入桌
    FA_GAME_PLAYING(2006),              // 玩家还在游戏中
    FA_GAME_INVALID_TABLE_OP(2012),     // 无效的桌子操作
    FA_GAME_INVALID_GID(2020),          // 非法的gameID
    FA_GAME_MATCHING(2024),             // 正在匹配中
    FA_GAME_SERVER_CLOSED(2026),        // 游戏服务器已关闭 (未支援的 gameid, fid 及從 redis 取得的 user 的 gameId, 與傳進來的 gameId 不同, 也送此訊息)

    FA_BALANCE_NOT_ENOUGH(5044),        // 用户余额不足,请充值后再玩
    FA_ACCOUNT_FORBIDDEN(5045),         // 账户被停用，请联系客服
    FA_REQ_LOCK_MONEY_FAIL(5046),       // 锁分失败，请联系客服


    //* 7001-7999 子遊戲內錯誤 *//
    FA_BET_RESULT_FAIL1(7001), // 無效區域
    FA_BET_RESULT_FAIL2(7002), // 無效籌碼
    FA_BET_RESULT_FAIL3(7003), // 旁觀模式不能下注
    FA_BET_RESULT_FAIL4(7004), // 下注失敗，此下注點限紅XXX
    FA_BET_RESULT_FAIL5(7005), // 金額不足
    FA_BET_RESULT_FAIL6(7006), // 測試賬號禁止下注
    FA_BET_RESULT_FAIL7(7007), // 非下注階段，不能下注
    FA_BET_RESULT_FAIL8(7008), // 已超過莊家最大賠付
    FA_BET_RESULT_FAIL9(7009), // 下注金額不能超過攜帶金額的1/maxRate
    FA_BET_RESULT_FAIL10(7010), // 不能同時下龍虎
    FA_BET_RESULT_FAIL11(7011), // 莊家不允許下注
    FA_BET_RESULT_FAIL12(7012), // 不能同時下紅黑
    FA_BET_RESULT_FAIL13(7013), // 不能同時下莊閒

    // Currency only used for the seamless wallet
    FA_GAME_BALANCE_LOCK_FAIL(9005), //相同订单请求过于频繁

    // 非 js 有定義的部分
    USER_KICK_OUT(11001),               // 直接關閉玩家 connection
    GAME_IDLE_TIMEOUT_FOR_HAS_FIELD_LIST(12001),           // 玩家遊戲中閒置過久, 使用於有房間大廳的遊戲
    GAME_IDLE_TIMEOUT_FOR_NO_FIELD_LIST(12002),            // 玩家遊戲中閒置過久, 使用於沒房間大廳的遊戲

    // GS 內部使用
    GS_LOCK_BALANCE_READ_TIMED_OUT(21001),
    GS_MATH_EXCEPTION(22001);           // 用於收到 math 的 TableException

    private final int code;

    ProtocolCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
