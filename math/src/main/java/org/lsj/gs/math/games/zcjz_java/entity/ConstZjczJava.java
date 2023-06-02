package org.lsj.gs.math.games.zcjz_java.entity;

import java.util.HashMap;
import java.util.Map;

// 招財金豬定義
public class ConstZjczJava {
    // 標誌定義
    public enum SymbolEnumZcjzJava {
        EMPTY(0, "空白"), // 空白標誌
        M1(1, "金龙"), // 金龍
        M2(2, "紅龙"), // 紅龍
        M3(3, "綠龙"); // 綠龍

        private final int code; // 編碼
        private final String name; // 名稱

        SymbolEnumZcjzJava(int code, String name){
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
            final SymbolEnumZcjzJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (SymbolEnumZcjzJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getCode(), enumInstance.getName());
            }
            return result;
        }
    }
}
