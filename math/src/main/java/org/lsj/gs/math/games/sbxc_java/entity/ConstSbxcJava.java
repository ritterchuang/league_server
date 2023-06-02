package org.lsj.gs.math.games.sbxc_java.entity;

import java.util.HashMap;
import java.util.Map;

// 三倍小丑定義
public class ConstSbxcJava {
    // 標誌定義
    public enum SymbolEnumSbxcJava {
        EMPTY(0, "空白"), // 空白標誌
        M1(1, "Joker"), // Joker
        M2(2, "金7"), // 金7
        M3(3, "金鐘"), // 金鐘
        M4(4, "Bar"); // Bar

        private final int code; // 編碼
        private final String name; // 名稱

        SymbolEnumSbxcJava(int code, String name){
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
            final SymbolEnumSbxcJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (SymbolEnumSbxcJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getCode(), enumInstance.getName());
            }
            return result;
        }
    }
}
