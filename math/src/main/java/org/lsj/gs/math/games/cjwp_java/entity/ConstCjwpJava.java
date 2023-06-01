package org.lsj.gs.math.games.cjwp_java.entity;

import java.util.HashMap;
import java.util.Map;

// 羅馬競技場定義
public class ConstCjwpJava {
    // 標誌定義
    public enum SymbolEnumCjwpJava {
        WILD(0, "小Joker"), // 小Joker
        WILD2(1, "大Joker"), // 大Joker
        C1(2, "Scatter"), // Scatter
        M1(3, "Ace"), // Ace
        M2(4, "King"), // King
        M3(5, "Queen"), // Queen
        M4(6, "Jack"), // Jack
        SPADE(7, "黑桃"), // 黑桃
        HEART(8, "紅心"), // 紅心
        DIAMOND(9, "方塊"), // 方塊
        CLUB(10, "梅花"); // 梅花


        private final int code; // 編碼
        private final String name; // 名稱

        SymbolEnumCjwpJava(int code, String name){
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
            final SymbolEnumCjwpJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (SymbolEnumCjwpJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getCode(), enumInstance.getName());
            }
            return result;
        }
    }
}
