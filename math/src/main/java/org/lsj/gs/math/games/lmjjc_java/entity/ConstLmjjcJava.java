package org.lsj.gs.math.games.lmjjc_java.entity;

import java.util.HashMap;
import java.util.Map;

// 羅馬競技場定義
public class ConstLmjjcJava {
    // 標誌定義
    public enum SymbolEnumLmjjcJava {
        WILD(0, "Wild頭像"), // Wild頭像
        C1(1, "SCatter競技場"), // SCatter競技場
        M1(2, "角鬥士"), // 角鬥士
        M2(3, "皇冠"), // 皇冠
        M3(4, "獅子"), // 獅子
        ACE(5, "劍斧"), // 劍斧
        KING(6, "盾牌"), // 盾牌
        QUEEN(7, "酒"), // 酒
        JOKER(8, "葡萄"), // 葡萄
        TEN(9, "蜂蜜"); // 蜂蜜


        private final int code; // 編碼
        private final String name; // 名稱

        SymbolEnumLmjjcJava(int code, String name){
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
            final SymbolEnumLmjjcJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (SymbolEnumLmjjcJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getCode(), enumInstance.getName());
            }
            return result;
        }
    }
}
