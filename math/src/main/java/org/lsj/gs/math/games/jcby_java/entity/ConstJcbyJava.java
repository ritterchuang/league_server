package org.lsj.gs.math.games.jcby_java.entity;

import org.lsj.gs.math.core.fish.ConstMathFish;

import java.util.HashMap;
import java.util.Map;

// 金蟾捕魚定義
public class ConstJcbyJava {
    // 子彈定義
    public enum BulletEnumJcbyJava {
        INVALID(0, ConstMathFish.BulletMechanismType.INVALID.getCode(), "無效"), // 無效
        NORMAL(1, ConstMathFish.BulletMechanismType.NORMAL.getCode(), "一般子弹"); // 一般子弹

        private final int code; // 編碼
        private final int bulletMechanismCode; // 子彈機制編碼
        private final String name; // 名稱

        BulletEnumJcbyJava(int code, int bulletMechanismCode, String name){
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
            final BulletEnumJcbyJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (BulletEnumJcbyJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getBulletMechanismCode(), enumInstance.getName());
            }
            return result;
        }
    }

    // 目標定義
    public enum TargetEnumJcbyJava {
        INVALID(0, "無效"), // 無效
        HALIBUT(1, "大比目鱼"), // 大比目魚
        MULLET(2, "胭脂鱼"), // 胭脂魚
        CROAKER(3, "黄花鱼"), // 黃花魚
        CARP(4, "鲤鱼"), // 鯉魚
        COD(5, "鳕鱼"), // 鱈魚
        HAPPY_FISH(6, "开心鱼"), // 開心魚
        PLAICE(7, "欧蝶鱼"), // 歐蝶魚
        BLUE_FISH(8, "蓝色鱼"), // 藍色魚
        LIGHT_FISH(9, "灯光鱼"), // 燈光魚
        TURTLE(10, "乌龟"), // 烏龜
        HADDOCK(11, "北大西洋鳕鱼"), // 北大西洋鱈魚
        DACE(12, "鲮鱼"), // 鯪魚
        CATFIS(13, "鲶鱼"), // 鯰魚
        BUTTERFLY_FISH(14, "蝴蝶鱼"), // 蝴蝶魚
        DRUM_FISH(15, "豉鱼"), // 豉魚
        WHITEBAIT(16, "银鱼"), // 銀魚
        GOLDFISH(17, "金鱼"), // 金魚
        BIG_GOLDFISH(18, "大金鱼"), // 大金魚
        SQUID(19, "水母"), // 水母
        STARFISH(20, "海星"), // 海星
        SILVER_DRAGON_FISH(21, "银龙"), // 銀龍
        GOLDEN_DRAGON_FISH(22, "金龙"), // 金龍
        GOLDEN_TOAD(23, "金蟾"), // 金蟾
        FLAG_KILL_FISH(24, "一网打尽"), // 一網打盡
        ALL_KILL_FISH(25, "深水炸弹"); // 深水炸彈

        private final int code; // 編碼
        private final String name; // 名稱
        TargetEnumJcbyJava(int code, String name){
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
            final TargetEnumJcbyJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (TargetEnumJcbyJava enumInstance : allEnumInstance) {
               result.put(enumInstance.getCode(), enumInstance.getName());
            }
            return result;
        }
    }
}
