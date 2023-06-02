package org.lsj.gs.math.games.lll_java.entity;

import java.util.HashMap;
import java.util.Map;

// 龍龍龍定義
public class ConstLllJava {
    // 標誌定義
    public enum SymbolEnumLllJava {
        EMPTY(0, "空白"), // 空白標誌
        M1(1, "金龙"), // 金龍
        M2(2, "紅龙"), // 紅龍
        M3(3, "綠龙"); // 綠龍

        private final int code; // 編碼
        private final String name; // 名稱

        SymbolEnumLllJava(int code, String name){
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
            final SymbolEnumLllJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (SymbolEnumLllJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getCode(), enumInstance.getName());
            }
            return result;
        }
    }
}
