package org.lsj.gs.math.games.pxky_java.enity;

import java.util.HashMap;
import java.util.Map;

// 豼貅開運定義
public class ConstPxkyJava {
    // 標誌定義
    public enum SymbolEnumPxkyJava {
        EMPTY(0, "空白"), // 空白標誌
        M1(1, "金貔貅"), // 金貔貅
        M2(2, "红貔貅"), // 紅貔貅
        M3(3, "绿貔貅"), // 綠貔貅
        M4(4, "蓝貔貅"); // 藍貔貅

        private final int code; // 編碼
        private final String name; // 名稱

        SymbolEnumPxkyJava(int code, String name){
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
            final SymbolEnumPxkyJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (SymbolEnumPxkyJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getCode(), enumInstance.getName());
            }
            return result;
        }
    }
}
