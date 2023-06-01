package org.lsj.gs.math.games.szzb_java.enity;

import org.lsj.gs.math.core.fish.ConstMathFish;

import java.util.HashMap;
import java.util.Map;

// 神龍捕魚定義
public class ConstSzzbJava {
    // 子彈定義
    public enum BulletEnumSzzbJava {
        INVALID(0, ConstMathFish.BulletMechanismType.INVALID.getCode(), "無效"), // 無效
        NORMAL(1, ConstMathFish.BulletMechanismType.NORMAL.getCode(), "普通足球"), // 普通足球
        LIGHTNING_COMBO_KICKS(2, ConstMathFish.BulletMechanismType.MACHINE.getCode(), "闪电连踢"), // 闪电连踢
        BEAST_SHOT(3, ConstMathFish.BulletMechanismType.DRILL.getCode(), "猛虎射球"); // 猛虎射球

        private final int code; // 編碼
        private final int bulletMechanismCode; // 子彈機制編碼
        private final String name; // 名稱

        BulletEnumSzzbJava(int code, int bulletMechanismCode, String name){
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
            final BulletEnumSzzbJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (BulletEnumSzzbJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getCode(), enumInstance.getName());
            }
            return result;
        }
    }

    // 目標定義
    public enum TargetEnumSzzbJava {
        INVALID(0, "無效"), // 無效
        BRAZILIAN_CAPTAIN(1, "巴西主将"), // 巴西主将
        BRAZILIAN_DEFENDER(2, "巴西后卫"), // 巴西后卫
        BRAZILIAN_MIDFIELDER(3, "巴西中场"), // 巴西中场
        BRAZILIAN_FORWARD(4, "巴西前锋"), // 巴西前锋
        BRAZILIAN_GOALKEEPER(5, "巴西守门员"), // 巴西守门员
        BRITISH_CAPTAIN(6, "英国主将"), // 英国主将
        BRITISH_DEFENDER(7, "英国后卫"), // 英国后卫
        BRITISH_MIDFIELDER(8, "英国中场"), // 英国中场
        BRITISH_FORWARD(9, "英国前锋"), // 英国前锋
        BRITISH_GOALKEEPER(10, "英国守门员"), // 英国守门员
        ITALIAN_CAPTAIN(11, "意大利主将"), // 意大利主将
        ITALIAN_DEFENDER(12, "意大利后卫"), // 意大利后卫
        ITALIAN_MIDFIELDER(13, "意大利中场"), // 意大利中场
        ITALIAN_FORWARD(14, "意大利前锋"), // 意大利前锋
        ITALIAN_GOALKEEPER(15, "意大利守门员"), // 意大利守门员
        SPANISH_CAPTAIN(16, "西班牙主将"), // 西班牙主将
        SPANISH_DEFENDER(17, "西班牙后卫"), // 西班牙后卫
        SPANISH_MIDFIELDER(18, "西班牙中场"), // 西班牙中场
        SPANISH_FORWARD(19, "西班牙前锋"), // 西班牙前锋
        SPANISH_GOALKEEPER(20, "西班牙守门员"), // 西班牙守门员
        YELLOW_CARD(21, "黄牌"), // 黄牌
        RED_CARD(22, "红牌"), // 红牌
        BEAST_SHOT(23, "猛虎射球"), // 猛虎射球
        LIGHTNING_COMBO_KICKS(24, "闪电连踢"); // 闪电连踢

        private final int code; // 編碼
        private final String name; // 名稱
        TargetEnumSzzbJava(int code, String name){
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
            final TargetEnumSzzbJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (TargetEnumSzzbJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getCode(), enumInstance.getName());
            }
            return result;
        }
    }
}
