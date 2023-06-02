package org.lsj.gs.math.games.wl_java.enity;

import java.util.HashMap;
import java.util.Map;

// 旺來定義
public class ConstWlJava {
    // 標誌定義
    public enum SymbolEnumWlJava {
        WILD(0, "凤梨"), // 鳳梨
        C1(1, "天灯"), // 天燈
        M1(2, "狮子"), // 獅子
        M2(3, "金龟"), // 金龜
        M3(4, "蟾蜍"), // 蟾蜍
        M4(5, "招财猫"), // 招財貓
        ACE(6, "ACE"), // ACE
        KING(7, "KING"), // KING
        QUEEN(8, "QUEEN"), // QUEEN
        JOKER(9, "JOKER"), // JOKER
        TEN(10, "TEN"); // TEN


        private final int code; // 編碼
        private final String name; // 名稱

        SymbolEnumWlJava(int code, String name){
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
            final SymbolEnumWlJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (SymbolEnumWlJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getCode(), enumInstance.getName());
            }
            return result;
        }
    }
}
