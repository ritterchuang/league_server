package org.lsj.gs.math.games.ccc_java.entity;

import java.util.HashMap;
import java.util.Map;

// 777定義
public class ConstCccJava {
    // 標誌定義
    public enum SymbolEnumCccJava {
        EMPTY(0, "空白"), // 空白標誌
        M1(1, "777"), // 777
        M2(2, "Bar3"), // Bar3
        M3(3, "Bar2"), // Bar2
        M4(4, "Bar1"); // Bar1

        private final int code; // 編碼
        private final String name; // 名稱

        SymbolEnumCccJava(int code, String name){
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
            final SymbolEnumCccJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (SymbolEnumCccJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getCode(), enumInstance.getName());
            }
            return result;
        }
    }
}
