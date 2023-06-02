package org.lsj.gs.math.games.swzs_java.entity;

import java.util.HashMap;
import java.util.Map;

// 死亡之書虎機定義
public class ConstSwzsJava {
    // 標誌定義
    public enum SymbolEnumSwzsJava {
        WILD(0, "死亡金柜"), // 死亡金櫃
        C1(1, "死亡之书"), // 死亡之書
        M1(2, "探险家"), // 探險家
        M2(3, "欧西里斯"), // 歐西里斯（法老）
        M3(4, "阿努比斯"), // 阿努比斯（胡狼）
        M4(5, "荷鲁斯"), // 荷魯斯（鳥）
        M5(6, "埃及十字架"), // 埃及十字架
        ACE(7, "ACE"), // ACE
        KING(8, "KING"), // KING
        QUEEN(9, "QUEEN"), // QUEEN
        JOKER(10, "JOKER"), // JOKER
        TEN(11, "TEN"); // TEN


        private final int code; // 編碼
        private final String name; // 名稱

        SymbolEnumSwzsJava(int code, String name){
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
            final SymbolEnumSwzsJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (SymbolEnumSwzsJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getCode(), enumInstance.getName());
            }
            return result;
        }
    }
}
