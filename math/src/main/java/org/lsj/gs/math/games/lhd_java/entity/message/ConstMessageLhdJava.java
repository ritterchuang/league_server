package org.lsj.gs.math.games.lhd_java.entity.message;

import org.lsj.gs.math.core.common.table.entity.message.core.ConstMessageCore;
import org.lsj.gs.math.core.common.table.entity.message.core.IMessageEnum;

import java.util.HashMap;
import java.util.Map;

// 新龍虎鬥消息定義
public class ConstMessageLhdJava {
    // 物件列舉對應表
    public static final Map<String, IMessageEnum> MessageObjectEnumMap = new HashMap<>(){{

        //* STC相關 *//
        put(StcCutReturnLhdJava.class.getCanonicalName(), MessageEnum.STC_CUT_RETURN); // 回復資訊
        put(StcUpdateUserLhdJava.class.getCanonicalName(), MessageEnum.STC_UPDATE_USER); // 更新玩家資訊 TODO 使用 UpdateUserGame
        put(StcChartLhdJava.class.getCanonicalName(), MessageEnum.STC_CHART); // 路線圖
        put(StcGameResultLhdJava.class.getCanonicalName(), MessageEnum.STC_GAME_RESULT); // 遊戲結果
    }};

    // 消息定義
    public enum MessageEnum implements IMessageEnum {
        INVALID("invalid"), // 非法操作

        //* STC相關 *//
        STC_CUT_RETURN("stc_cutreturn"), // 回復訊息
        STC_UPDATE_USER("updateUser"), // 更新玩家資訊
        STC_CHART("stc_chart"), // 路線圖
        STC_GAME_RESULT("stc_gameresult"); // 遊戲結果


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
