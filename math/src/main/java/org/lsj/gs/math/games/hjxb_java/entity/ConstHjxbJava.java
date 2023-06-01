package org.lsj.gs.math.games.hjxb_java.entity;

import java.util.HashMap;
import java.util.Map;

// 黃金西部定義
public class ConstHjxbJava {
    // 標誌定義
    public enum SymbolEnumHjxbJava {
        WILD(0, "Wild"), // Wild
        C1(1, "Scatter"), // Scatter
        M1(2, "shooter"), // shooter
        M2(3, "oldman"), // oldman
        M3(4, "hotgirl"), // hotgirl
        M4(5, "oldlady"), // oldlady
        ACE(6, "ACE"), // ACE
        KING(7, "KING"), // KING
        QUEEN(8, "QUEEN"), // QUEEN
        JOKER(9, "JOKER"), // JOKER
        TEN(10, "TEN"), // TEN
        NINE(11, "NINE"); // NINE


        private final int code; // 編碼
        private final String name; // 名稱

        SymbolEnumHjxbJava(int code, String name){
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
            final SymbolEnumHjxbJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (SymbolEnumHjxbJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getCode(), enumInstance.getName());
            }
            return result;
        }
    }
}
