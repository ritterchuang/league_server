package org.lsj.gs.math.games.sg_java.entity.message;

import org.lsj.gs.math.core.common.table.entity.message.core.ConstMessageCore;
import org.lsj.gs.math.core.common.table.entity.message.core.IMessageEnum;

import java.util.HashMap;
import java.util.Map;

// 新三公消息定義
public class ConstMessageSgJava {
    // 物件列舉對應表
    public static final Map<String, IMessageEnum> MessageObjectEnumMap = new HashMap<>(){{
        //* CTS相關 *//
        put(CtsVieBankerSgJava.class.getCanonicalName(), MessageEnum.CTS_VIE_BANKER); // 搶莊
        put(CtsBetSgJava.class.getCanonicalName(), MessageEnum.CTS_BET); // 下注
        put(CtsSelectSgJava.class.getCanonicalName(), MessageEnum.CTS_SELECT); // 選牌

        //* STC相關 *//
        put(StcDealCardSgJava.class.getCanonicalName(), MessageEnum.STC_DEAL_CARD); // 發牌
        put(StcVieBankerSgJava.class.getCanonicalName(), MessageEnum.STC_VIE_BANKER); // 玩家搶莊
        put(StcVieResultSgJava.class.getCanonicalName(), MessageEnum.STC_VIE_RESULT); // 搶莊結果
        put(StcBetSgJava.class.getCanonicalName(), MessageEnum.STC_BET); // 玩家下注
        put(StcBetResultSgJava.class.getCanonicalName(), MessageEnum.STC_BET_RESULT); // 下注結果
        put(StcStartShowSgJava.class.getCanonicalName(), MessageEnum.STC_STARTSHOW); // 開始選牌
        put(StcSelectSgJava.class.getCanonicalName(), MessageEnum.STC_SELECT); // 玩家選牌
        put(StcCompareSgJava.class.getCanonicalName(), MessageEnum.STC_COMPARE); // 比牌
        put(StcStartRobSgJava.class.getCanonicalName(), MessageEnum.STC_STARTROB); // 開始搶莊
        put(StcStartBetSgJava.class.getCanonicalName(), MessageEnum.STC_STARTBET); // 開始下注

        //* 共用相關 *//
        put(StcCutReturnSgJava.class.getCanonicalName(), ConstMessageCore.MessageEnum.STC_CUT_RETURN); // 斷線重連
        put(StcSettlementSgJava.class.getCanonicalName(), ConstMessageCore.MessageEnum.STC_SETTLEMENT); // 結算得分
    }};

    // 消息定義
    public enum MessageEnum implements IMessageEnum {
        INVALID("invalid"), // 非法操作
        //* CTS相關 *//
        CTS_VIE_BANKER("cts_viebanker"), // 搶莊
        CTS_BET("cts_bet"), // 下注
        CTS_SELECT("cts_select"), // 選牌
        //* STC相關 *//
        STC_DEAL_CARD("stc_dealcard"), // 發牌
        STC_STARTROB("stc_startrob"), // 開始搶莊
        STC_VIE_BANKER("stc_viebanker"), // 玩家搶莊
        STC_VIE_RESULT("stc_vieresult"), // 搶莊結果
        STC_STARTBET("stc_startbet"), // 開始下注
        STC_BET("stc_bet"), // 玩家下注
        STC_BET_RESULT("stc_betresult"), // 下注結果
        STC_STARTSHOW("stc_startshow"), // 開始選牌
        STC_SELECT("stc_select"), // 玩家選牌
        STC_COMPARE("stc_compare"); // 比牌

        private final String enumString; // 消息定義字串
        MessageEnum(String enumString){
            this.enumString = enumString;
        }
        public String getSub(){
            return this.enumString;
        }
        public String getMain() { return ConstMessageCore.EventType.GAME_EVENT.getEnumString(); }
        public static MessageEnum fromEnumString(String enumString) {
            final MessageEnum[] allEnumInstance = values();
            for (MessageEnum enumInstance : allEnumInstance) {
                if (enumInstance.getSub().equals(enumString)) {
                    return enumInstance;
                }
            }
            return INVALID;
        }
    }
}
