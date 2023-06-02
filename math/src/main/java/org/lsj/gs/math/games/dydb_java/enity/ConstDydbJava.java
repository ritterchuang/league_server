package org.lsj.gs.math.games.dydb_java.enity;

import java.util.HashMap;
import java.util.Map;

// 豼貅開運定義
public class ConstDydbJava {
    // 標誌定義
    public enum SymbolEnumDydbJava {
        WILD(0, "鞭炮"), // 鞭炮
        C1(1, "铜锣"), // 铜锣
        M1(2, "狮子"), // 狮子
        M2(3, "凤凰"), // 凤凰
        M3(4, "鲤鱼"), // 鲤鱼
        M4(5, "摇钱树"), // 摇钱树
        ACE(6, "ACE"), // ACE
        KING(7, "KING"), // KING
        QUEEN(8, "QUEEN"), // QUEEN
        JOKER(9, "JOKER"), // JOKER
        TEN(10, "TEN"); // TEN


        private final int code; // 編碼
        private final String name; // 名稱

        SymbolEnumDydbJava(int code, String name){
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
            final SymbolEnumDydbJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (SymbolEnumDydbJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getCode(), enumInstance.getName());
            }
            return result;
        }
    }
}
