package org.lsj.gs.math.games.tbnn_java.entity.message;

import org.lsj.gs.math.core.common.table.entity.message.core.ConstMessageCore;
import org.lsj.gs.math.core.common.table.entity.message.core.IMessageEnum;

import java.util.HashMap;
import java.util.Map;

// 搶莊牛牛消息定義
public class ConstMessageTbnnJava {
    // 物件列舉對應表
    public static final Map<String, IMessageEnum> MessageObjectEnumMap = new HashMap<>(){{
        //* CTS相關 *//
        put(CtsBetTbnnJava.class.getCanonicalName(), MessageEnum.CTS_BET); // 下注
        put(CtsSelectTbnnJava.class.getCanonicalName(), MessageEnum.CTS_SELECT); // 選牌

        //* STC相關 *//
        put(StcDealCardTbnnJava.class.getCanonicalName(), MessageEnum.STC_DEAL_CARD); // 發牌
        put(StcBetTbnnJava.class.getCanonicalName(), MessageEnum.STC_BET); // 玩家下注
        put(StcBetResultTbnnJava.class.getCanonicalName(), MessageEnum.STC_BET_RESULT); // 下注結果
        put(StcBeginSelectTbnnJava.class.getCanonicalName(), MessageEnum.STC_BEGIN_SELECT); // 開始選牌
        put(StcSelectTbnnJava.class.getCanonicalName(), MessageEnum.STC_SELECT); // 玩家選牌
        put(StcMaxSelectTbnnJava.class.getCanonicalName(), MessageEnum.STC_MAX_SELECT); // 最大選牌(自定義)
        put(StcCompareTbnnJava.class.getCanonicalName(), MessageEnum.STC_COMPARE); // 比牌
        put(StcMaxCardTypeTbnnJava.class.getCanonicalName(), MessageEnum.STC_MAX_CARD_TYPE); // 玩家手牌最大牌型
        put(StcTurnTbnnJava.class.getCanonicalName(), MessageEnum.STC_TURN); // 操作位置

        //* 共用相關 *//
        put(StcCutReturnTbnnJava.class.getCanonicalName(), ConstMessageCore.MessageEnum.STC_CUT_RETURN); // 斷線重連
        put(StcSettlementTbnnJava.class.getCanonicalName(), ConstMessageCore.MessageEnum.STC_SETTLEMENT); // 結算得分
    }};

    // 消息定義
    public enum MessageEnum implements IMessageEnum {
        INVALID("invalid"), // 非法操作
        //* CTS相關 *//
        CTS_BET("cts_bet"), // 下注
        CTS_SELECT("cts_select"), // 選牌
        //* STC相關 *//
        STC_DEAL_CARD("stc_dealcard"), // 發牌
        STC_BET("stc_bet"), // 玩家下注
        STC_BET_RESULT("stc_betresult"), // 下注結果
        STC_BEGIN_SELECT("stc_beginselect"), // 開始選牌
        STC_SELECT("stc_select"), // 玩家選牌
        STC_MAX_SELECT("cts_select"), // 最大選牌(自定義)
        STC_COMPARE("stc_compare"), // 比牌
        STC_MAX_CARD_TYPE("stc_maxcardtype"), // 玩家手牌最大牌型
        STC_TURN("stc_turn"); // 操作位置

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
