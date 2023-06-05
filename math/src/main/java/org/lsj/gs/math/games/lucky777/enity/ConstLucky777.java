package org.lsj.gs.math.games.lucky777.enity;

import java.util.HashMap;
import java.util.Map;

// Lucky777 定義
public class ConstLucky777 {
    // 標誌定義
    public enum SymbolEnum {
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

        SymbolEnum(int code, String name){
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
            final SymbolEnum[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (SymbolEnum enumInstance : allEnumInstance) {
                result.put(enumInstance.getCode(), enumInstance.getName());
            }
            return result;
        }
    }
}
