package org.lsj.gs.math.games.qznn_k4z_java.entity.message;

import org.lsj.gs.math.core.common.table.entity.message.core.ConstMessageCore;
import org.lsj.gs.math.core.common.table.entity.message.core.IMessageEnum;

import java.util.HashMap;
import java.util.Map;

// 新看四張搶莊牛牛消息定義
public class ConstMessageQznnK4zJava {
    // 物件列舉對應表
    public static final Map<String, IMessageEnum> MessageObjectEnumMap = new HashMap<>(){{
        //* CTS相關 *//
        put(CtsVieBankerQznnK4zJava.class.getCanonicalName(), MessageEnum.CTS_VIE_BANKER); // 搶莊
        put(CtsBetQznnK4zJava.class.getCanonicalName(), MessageEnum.CTS_BET); // 下注
        put(CtsSelectQznnK4zJava.class.getCanonicalName(), MessageEnum.CTS_SELECT); // 選牌

        //* STC相關 *//
        put(StcDealCardQznnK4zJava.class.getCanonicalName(), MessageEnum.STC_DEAL_CARD); // 發牌
        put(StcVieBankerQznnK4zJava.class.getCanonicalName(), MessageEnum.STC_VIE_BANKER); // 玩家搶莊
        put(StcVieResultQznnK4zJava.class.getCanonicalName(), MessageEnum.STC_VIE_RESULT); // 搶莊結果
        put(StcBetQznnK4zJava.class.getCanonicalName(), MessageEnum.STC_BET); // 玩家下注
        put(StcBetResultQznnK4zJava.class.getCanonicalName(), MessageEnum.STC_BET_RESULT); // 下注結果
        put(StcBeginSelectQznnK4zJava.class.getCanonicalName(), MessageEnum.STC_BEGIN_SELECT); // 開始選牌
        put(StcSelectQznnK4zJava.class.getCanonicalName(), MessageEnum.STC_SELECT); // 玩家選牌
        put(StcMaxSelectQznnK4zJava.class.getCanonicalName(), MessageEnum.STC_MAX_SELECT); // 最大選牌(自定義)
        put(StcCompareQznnK4zJava.class.getCanonicalName(), MessageEnum.STC_COMPARE); // 比牌
        put(StcMaxCardTypeQznnK4zJava.class.getCanonicalName(), MessageEnum.STC_MAX_CARD_TYPE); // 玩家手牌最大牌型
        put(StcTurnQznnK4zJava.class.getCanonicalName(), MessageEnum.STC_TURN); // 操作位置

        //* 共用相關 *//
        put(StcCutReturnQznnK4zJava.class.getCanonicalName(), ConstMessageCore.MessageEnum.STC_CUT_RETURN); // 斷線重連
        put(StcSettlementQznnK4zJava.class.getCanonicalName(), ConstMessageCore.MessageEnum.STC_SETTLEMENT); // 結算得分
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
        STC_VIE_BANKER("stc_viebanker"), // 玩家搶莊
        STC_VIE_RESULT("stc_vieresult"), // 搶莊結果
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
