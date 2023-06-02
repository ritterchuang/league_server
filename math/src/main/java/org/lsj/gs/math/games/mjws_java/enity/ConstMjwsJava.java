package org.lsj.gs.math.games.mjws_java.enity;

import java.util.HashMap;
import java.util.Map;

// 麻將無雙定義
public class ConstMjwsJava {
    // 標誌定義
    public enum SymbolEnumMjwsJava {
        WILD(0, "百搭"), // 鞭炮
        C1(1, "自摸"), // 铜锣
        M1(2, "發財"), // 狮子
        M2(3, "紅中"), // 凤凰
        M3(4, "白板"), // 鲤鱼
        ACE(5, "九萬"), // 摇钱树
        KING(6, "九筒"), // ACE
        QUEEN(7, "九索"), // KING
        JOKER(8, "一筒"), // QUEEN
        TEN(9, "一索"); // JOKER


        private final int code; // 編碼
        private final String name; // 名稱

        SymbolEnumMjwsJava(int code, String name){
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
            final SymbolEnumMjwsJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (SymbolEnumMjwsJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getCode(), enumInstance.getName());
            }
            return result;
        }
    }
}
