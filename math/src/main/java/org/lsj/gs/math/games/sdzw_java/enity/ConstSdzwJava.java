package org.lsj.gs.math.games.sdzw_java.enity;

import java.util.HashMap;
import java.util.Map;

// 聖誕任務虎機定義
public class ConstSdzwJava {
    // 標誌定義
    public enum SymbolEnumSdzwJava {
        WILD(0, "圣诞袜"), // 聖誕襪
        C1(1, "雪橇"), // 雪橇
        M1(2, "圣诞老人"), // 聖誕老人
        M2(3, "圣诞精灵"), // 聖誕精靈
        M3(4, "糜鹿"), // 糜鹿
        M4(5, "雪人"), // 雪人
        M5(6, "姜饼人"), // 薑餅人
        ACE(7, "ACE"), // ACE
        KING(8, "KING"), // KING
        QUEEN(9, "QUEEN"), // QUEEN
        JOKER(10, "JOKER"), // JOKER
        TEN(11, "TEN"), // JOKER
        NINE(12, "NINE"); // TEN


        private final int code; // 編碼
        private final String name; // 名稱

        SymbolEnumSdzwJava(int code, String name){
            this.code = code;
            this.name = name;
        }
        public int getCode() {
            return code;
        }
        public String getName() {
            return name;
        }
        public static Map<Integer, String> getSymbolIdStringMap() {
            final SymbolEnumSdzwJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (SymbolEnumSdzwJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getCode(), enumInstance.getName());
            }
            return result;
        }
    }
}
