package org.lsj.gs.math.games._develop_by_java.enity;

import org.lsj.gs.math.core.fish.ConstMathFish;

import java.util.HashMap;
import java.util.Map;

// 基礎捕魚定義
public class ConstDevelopByJava {
    // 子彈定義
    public enum BulletEnumDevelopByJava {
        INVALID(0, ConstMathFish.BulletMechanismType.INVALID.getCode(), "無效"), // 無效
        NORMAL(1, ConstMathFish.BulletMechanismType.NORMAL.getCode(), "一般子彈"); // 一般子彈

        private final int code; // 編碼
        private final int bulletMechanismCode; // 子彈機制編碼
        private final String name; // 名稱

        BulletEnumDevelopByJava(int code, int bulletMechanismCode, String name){
            this.code = code;
            this.bulletMechanismCode = bulletMechanismCode;
            this.name = name;
        }
        public int getCode() {
            return code;
        }
        public int getBulletMechanismCode() {
            return bulletMechanismCode;
        }
        public String getName() {
            return name;
        }
        public static Map<Integer, String> getBulletIndexStringMap() {
            final BulletEnumDevelopByJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (BulletEnumDevelopByJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getCode(), enumInstance.getName());
            }
            return result;
        }
    }

    // 目標定義
    public enum TargetEnumDevelopByJava {
        INVALID(0, "無效"), // 無效
        FIX_ODDS_FISH(1, "固定倍數魚"); // 固定倍數魚

        private final int code; // 編碼
        private final String name; // 名稱
        TargetEnumDevelopByJava(int code, String name){
            this.code = code;
            this.name = name;
        }
        public int getCode() {
            return code;
        }
        public String getName() {
            return name;
        }
        public static Map<Integer, String> getTargetIndexStringMap() {
            final TargetEnumDevelopByJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (TargetEnumDevelopByJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getCode(), enumInstance.getName());
            }
            return result;
        }
    }
}
