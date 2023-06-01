package org.lsj.gs.math.core.common.table.entity.message.core;

import org.lsj.gs.math.core.common.table.entity.message.baiRen.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// 共用訊息定義
public class ConstMessageCore {
    // 物件列舉對應表
    public static final Map<String, IMessageEnum> MessageObjectEnumMap = new HashMap<>() {{
        //* 回應客端相關 *//
        put(StcEnterFiled.class.getCanonicalName(), MessageEnum.ROUTE_STC_ENTER_FIELD); // 回應進入房間

        //* 框架消息定義 *//
        put(StcEnableWaterMatch.class.getCanonicalName(), MessageEnum.STC_ENABLE_WATER_MATCH); // 啟用配桌
        put(StcUpdateUserFrame.class.getCanonicalName(), MessageEnum.STC_UPDATE_USER_FRAME); // 更新玩家數據

        //* 遊戲消息相關 *//
        put(StcUserList.class.getCanonicalName(), MessageEnum.STC_USER_LIST); // 玩家列表
        put(StcUserLeave.class.getCanonicalName(), MessageEnum.STC_USER_LEAVE); // 玩家離桌
        put(StcUpdateScores.class.getCanonicalName(), MessageEnum.STC_UPDATE_SCORES); // 更新玩家分數
        put(StcUpdateLogId.class.getCanonicalName(), MessageEnum.STC_UPDATE_LOG_ID); // 更新對局日誌索引
        put(StcUpdateStage.class.getCanonicalName(), MessageEnum.STC_UPDATE_STAGE); // 更新遊戲階段

        //* 百人遊戲相關 *//
        put(StcAction.class.getCanonicalName(), MessageEnum.STC_ACTION); // 百人動作訊息
        put(StcArea.class.getCanonicalName(), MessageEnum.STC_AREA); // 百人押注區金額訊息
        put(StcBet.class.getCanonicalName(), MessageEnum.STC_BET); // 百人玩家下注訊息
        put(StcBetResult.class.getCanonicalName(), MessageEnum.STC_BET_RESULT); // 百人玩家下注結果訊息
        put(StcMoney.class.getCanonicalName(), MessageEnum.STC_MONEY); // 百人玩家餘額訊息
        put(StcOwnBet.class.getCanonicalName(), MessageEnum.STC_OWN_BET); // 百人真人下注訊息
        put(StcRichList.class.getCanonicalName(), MessageEnum.STC_RICH_LIST); // 百人榜單訊息
        put(StcUpdateUserGame.class.getCanonicalName(), MessageEnum.STC_UPDATE_USER_GAME); // 百人更新玩家訊息
        put(StcUserNum.class.getCanonicalName(), MessageEnum.STC_USER_NUM); // 百人玩家人數訊息
    }};

    // 事件類型
    public enum EventType {
        REQUIRE("require"), // 回應客端事件
        FRAME_EVENT("f_event"), // 框架事件
        GAME_EVENT("g_event"); // 遊戲事件

        private final String enumString;

        EventType(String enumString) {
            this.enumString = enumString;
        }

        public String getEnumString() {
            return this.enumString;
        }
    }

    // 消息定義
    public enum MessageEnum implements IMessageEnum {
        UNKNOWN("unknown", "unknown"),

        //* 回應客端相關 *//
        ROUTE_CTS_LOGIN("connector.entryHandler.login", EventType.REQUIRE.getEnumString()), // 請求登入
        ROUTE_STC_LOGIN("connector.entryHandler.login", EventType.REQUIRE.getEnumString()), // 回應登入
        ROUTE_CTS_GET_FIELD("gamecenter.gcHandler.getField", EventType.REQUIRE.getEnumString()), // 請求房間列表
        ROUTE_STC_GET_FIELD("gamecenter.gcHandler.getField", EventType.REQUIRE.getEnumString()), // 回應房間列表
        ROUTE_STC_GET_ROOM_DISPLAY_DATA("gamecenter.gcHandler.getRoomDisplayData", EventType.REQUIRE.getEnumString()), // 回應房間路圖
        ROUTE_CTS_ENTER_FIELD("gamecenter.gcHandler.enterField", EventType.REQUIRE.getEnumString()), // 請求進入房間
        ROUTE_STC_ENTER_FIELD("gamecenter.gcHandler.enterField", EventType.REQUIRE.getEnumString()), // 回應進入房間
        ROUTE_CTS_GAME_MESSAGE("gamecenter.gcHandler.gameMsg", EventType.REQUIRE.getEnumString()), // 請求遊戲訊息
        ROUTE_STC_GAME_MESSAGE("gamecenter.gcHandler.gameMsg", EventType.REQUIRE.getEnumString()), // 回應遊戲訊息
        ROUTE_CTS_OP_TABLE("gamecenter.gcHandler.opTable", EventType.REQUIRE.getEnumString()), // 請求操作桌子
        ROUTE_STC_OP_TABLE("gamecenter.gcHandler.opTable", EventType.REQUIRE.getEnumString()), // 回應操作桌子
        ROUTE_CTS_LEAVE_GC("gamecenter.gcHandler.leaveGC", EventType.REQUIRE.getEnumString()), // 請求離開GC
        ROUTE_STC_LEAVE_GC("gamecenter.gcHandler.leaveGC", EventType.REQUIRE.getEnumString()), // 回應離開GC
        ROUTE_CTS_GET_BILLS("gamecenter.gcHandler.getBills", EventType.REQUIRE.getEnumString()), // 請求查詢報表
        ROUTE_STC_GET_BILLS("gamecenter.gcHandler.getBills", EventType.REQUIRE.getEnumString()), // 回應查詢報表
        ROUTE_STC_SET_RNG_DATA("gamecenter.gcHandler.setRngData", EventType.REQUIRE.getEnumString()),


        //* 框架消息定義 *//
        STC_ENABLE_WATER_MATCH("enabledWaterMatch", EventType.FRAME_EVENT.getEnumString()), // 啟用配桌
        STC_UPDATE_USER_FRAME("updateUser", EventType.FRAME_EVENT.getEnumString()), // 更新玩家數據
        STC_GAME_CONFIG("gameconfig", EventType.FRAME_EVENT.getEnumString()), // 遊戲桌子設定


        //* 遊戲消息相關 *//

        //** CTS 遊戲消息定義 **//
        CTS_SWITCH_BG("cts_switchbg", EventType.GAME_EVENT.getEnumString()), // 客戶端前後台切換
        CTS_WATER_MATCH("cts_watermatch", EventType.GAME_EVENT.getEnumString()), // 請求配桌
        CTS_BET("cts_bet", EventType.GAME_EVENT.getEnumString()), // 百人下注

        //** STC 百人遊戲消息定義 **//
        STC_ACTION("stc_action", EventType.GAME_EVENT.getEnumString()), // 百人動作訊息
        STC_AREA("stc_area", EventType.GAME_EVENT.getEnumString()), // 百人押注區金額訊息
        STC_BET("stc_bet", EventType.GAME_EVENT.getEnumString()), // 百人玩家下注訊息
        STC_BET_RESULT("stc_bet_result", EventType.GAME_EVENT.getEnumString()), // 百人玩家下注結果訊息
        STC_MONEY("stc_money", EventType.GAME_EVENT.getEnumString()), // 百人玩家餘額訊息
        STC_OWN_BET("stc_ownbet", EventType.GAME_EVENT.getEnumString()), // 百人真人下注訊息
        STC_RICH_LIST("stc_richlist", EventType.GAME_EVENT.getEnumString()), // 百人榜單訊息
        STC_UPDATE_USER_GAME("updateUser", EventType.GAME_EVENT.getEnumString()), // 百人更新玩家訊息
        STC_USER_NUM("stc_usernum", EventType.GAME_EVENT.getEnumString()), // 百人玩家人數訊息

        //** STC 遊戲消息定義 **//
        STC_USER_LIST("userlist", EventType.GAME_EVENT.getEnumString()), // 玩家列表
        STC_USER_LEAVE("userleave", EventType.GAME_EVENT.getEnumString()), // 玩家離桌
        STC_CONFIG("config", EventType.GAME_EVENT.getEnumString()), // 牌桌配置消息
        STC_WATER_MATCH("stc_watermatch", EventType.GAME_EVENT.getEnumString()), // 請求配桌結果
        STC_UPDATE_SCORES("stc_updatescores", EventType.GAME_EVENT.getEnumString()), // 更新玩家分數
        STC_UPDATE_LOG_ID("stc_updatelogid", EventType.GAME_EVENT.getEnumString()), // 更新對局日誌索引
        STC_UPDATE_STAGE("stc_updatestage", EventType.GAME_EVENT.getEnumString()), // 更新遊戲階段
        STC_SETTLEMENT("stc_settlement", EventType.GAME_EVENT.getEnumString()), // 結算得分
        STC_CUT_RETURN("stc_cutreturn", EventType.GAME_EVENT.getEnumString()); // 斷線重連

        private static final Map<String, MessageEnum> BY_ROUTE = new HashMap<>();

        static {
            for (MessageEnum e : values()) {
                BY_ROUTE.put(e.getSub(), e);
            }
        }

        public static MessageEnum getMessageEnum(String route) {
            final MessageEnum messageEnum = BY_ROUTE.get(route);
            return Objects.isNull(messageEnum) ? UNKNOWN : messageEnum;
        }

        private final String sub; // 列舉定義
        private final String main; // 事件定義

        MessageEnum(String sub, String main) {
            this.sub = sub;
            this.main = main;
        }

        public String getSub() {
            return this.sub;
        }

        public String getMain() {
            return this.main;
        }
    }
}
