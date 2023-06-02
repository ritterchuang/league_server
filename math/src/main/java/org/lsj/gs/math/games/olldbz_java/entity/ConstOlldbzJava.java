package org.lsj.gs.math.games.olldbz_java.entity;

import java.util.HashMap;
import java.util.Map;

// 歐賴利的寶藏虎機定義
public class ConstOlldbzJava {
    // 標誌定義
    public enum SymbolEnumOlldbzJava {
        WILD(0, "Wild"), // Wild
        C1(1, "金幣桶"), // 金幣桶
        M1(2, "歐賴利"), // 歐賴利
        M2(3, "綿羊"), // 綿羊
        M3(4, "酢漿草"), // 酢漿草
        M4(5, "馬蹄鐵"), // 馬蹄鐵
        M5(6, "煙斗"), // 煙斗
        ACE(7, "ACE"), // ACE
        KING(8, "KING"), // KING
        QUEEN(9, "QUEEN"), // QUEEN
        JOKER(10, "JOKER"), // JOKER
        TEN(11, "TEN"), // TEN
        NINE(12, "NINE"); // NINE


        private final int code; // 編碼
        private final String name; // 名稱

        SymbolEnumOlldbzJava(int code, String name){
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
            final SymbolEnumOlldbzJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (SymbolEnumOlldbzJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getCode(), enumInstance.getName());
            }
            return result;
        }
    }
}
