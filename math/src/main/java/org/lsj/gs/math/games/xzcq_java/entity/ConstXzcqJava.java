package org.lsj.gs.math.games.xzcq_java.entity;

import java.util.HashMap;
import java.util.Map;

// 小豬傳奇定義
public class ConstXzcqJava {
    // 標誌定義
    public enum SymbolEnumXzcqJava {
        EMPTY(0, "空白"), // 空白標誌
        M1(1, "金猪"), // 金猪
        M2(2, "银猪"), // 银猪
        M3(3, "铜猪"), // 铜猪
        M4(4, "金币"); // 金币

        private final int code; // 編碼
        private final String name; // 名稱

        SymbolEnumXzcqJava(int code, String name){
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
            final SymbolEnumXzcqJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (SymbolEnumXzcqJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getCode(), enumInstance.getName());
            }
            return result;
        }
    }
}
