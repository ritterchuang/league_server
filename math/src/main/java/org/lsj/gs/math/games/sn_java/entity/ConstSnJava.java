package org.lsj.gs.math.games.sn_java.entity;

import java.util.HashMap;
import java.util.Map;

// 豼貅開運定義
public class ConstSnJava {
    // 標誌定義
    public enum SymbolEnumSnJava {
        WILD(0, "峡谷"), // 峽谷
        C1(1, "金币"), // 金幣
        M1(2, "水牛"), // 水牛
        M2(3, "白头鹰"), // 白頭鷹
        M3(4, "美洲狮"), // 美洲獅
        M4(5, "灰狼"), // 灰狼
        ACE(6, "ACE"), // ACE
        KING(7, "KING"), // KING
        QUEEN(8, "QUEEN"), // QUEEN
        JOKER(9, "JOKER"), // JOKER
        TEN(10, "TEN"); // TEN


        private final int code; // 編碼
        private final String name; // 名稱

        SymbolEnumSnJava(int code, String name){
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
            final SymbolEnumSnJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (SymbolEnumSnJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getCode(), enumInstance.getName());
            }
            return result;
        }
    }
}
