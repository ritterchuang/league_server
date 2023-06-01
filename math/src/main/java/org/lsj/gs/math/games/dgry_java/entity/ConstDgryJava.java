package org.lsj.gs.math.games.dgry_java.entity;

import java.util.HashMap;
import java.util.Map;

// 帝国榮耀定義
public class ConstDgryJava {
    // 標誌定義
    public enum SymbolEnumDgryJava {
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

        SymbolEnumDgryJava(int code, String name){
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
            final SymbolEnumDgryJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (SymbolEnumDgryJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getCode(), enumInstance.getName());
            }
            return result;
        }
    }

    public enum BonusGameOutcomeTypeEnumDgryJava {
        SWORD_ATTACK(0, "攻擊"), // 揮劍攻擊
        DOUBLE_SWORD(1, "雙劍"), // 雙劍
        SINGLE_SWORD(2, "單劍"), // 單劍
        SHIELD(3, "盾牌"), // 盾牌
        RETALIATE(4, "反擊"), // 反擊
        MISS(5, "MISS"), // MISS
        NO_SELECT(6, "不選擇"); // 不選擇


        private final int code; // 編碼
        private final String name; // 名稱

        BonusGameOutcomeTypeEnumDgryJava(int code, String name){
            this.code = code;
            this.name = name;
        }
        public int getCode() {
            return code;
        }
        public String getName() {
            return name;
        }
    }


}
