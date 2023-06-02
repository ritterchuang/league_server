package org.lsj.gs.math.games._model_hj_java.enity;

import java.util.HashMap;
import java.util.Map;

// 模板虎機定義
public class ConstModelHjJava {
    // 標誌定義
    public enum SymbolEnumModelHjJava {
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

        SymbolEnumModelHjJava(int code, String name){
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
            final SymbolEnumModelHjJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (SymbolEnumModelHjJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getCode(), enumInstance.getName());
            }
            return result;
        }
    }
}
