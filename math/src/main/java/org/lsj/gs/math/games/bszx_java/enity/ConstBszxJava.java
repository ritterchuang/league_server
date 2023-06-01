package org.lsj.gs.math.games.bszx_java.enity;

import java.util.HashMap;
import java.util.Map;

// 寶石之星虎機定義
public class ConstBszxJava {
    // 標誌定義
    public enum SymbolEnumBszxJava {
        WILD(0, "多彩宝石"), // 多彩寶石
        C1(1, "流星"), // 流星
        M1(2, "黄宝石"), // 黃寶石
        M2(3, "绿宝石"), // 綠寶石
        M3(4, "橘宝石"), // 橘寶石
        M4(5, "蓝宝石"), // 藍寶石
        M5(6, "紫宝石"), // 紫寶石
        ACE(7, "ACE"), // ACE
        KING(8, "KING"), // KING
        QUEEN(9, "QUEEN"), // QUEEN
        JOKER(10, "JOKER"), // JOKER
        TEN(11, "TEN"), // TEN
        NINE(12, "NINE"); // NINE


        private final int code; // 編碼
        private final String name; // 名稱

        SymbolEnumBszxJava(int code, String name){
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
            final SymbolEnumBszxJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (SymbolEnumBszxJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getCode(), enumInstance.getName());
            }
            return result;
        }
    }
}
