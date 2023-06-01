package org.lsj.gs.math.games.csby_java.enity;

import org.lsj.gs.math.core.fish.ConstMathFish;

import java.util.HashMap;
import java.util.Map;

// 財神捕魚定義
public class ConstCsbyJava {
    // 子彈定義
    public enum BulletEnumCsbyJava {
        INVALID(0, ConstMathFish.BulletMechanismType.INVALID.getCode(), "無效"), // 無效
        NORMAL(1, ConstMathFish.BulletMechanismType.NORMAL.getCode(), "一般子弹"), // 一般子弹
        MACHINE(2, ConstMathFish.BulletMechanismType.MACHINE.getCode(), "机关炮"), // 機關炮
        DRILL(3, ConstMathFish.BulletMechanismType.DRILL.getCode(), "钻头炮"); // 鑽頭炮

        private final int code; // 編碼
        private final int bulletMechanismCode; // 子彈機制編碼
        private final String name; // 名稱

        BulletEnumCsbyJava(int code, int bulletMechanismCode, String name){
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
            final BulletEnumCsbyJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (BulletEnumCsbyJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getBulletMechanismCode(), enumInstance.getName());
            }
            return result;
        }
    }

    // 目標定義
    public enum TargetEnumCsbyJava {
        INVALID(0, "無效"), // 無效
        STARFISH(1, "海星"), // 海星
        MULLET(2, "胭脂鱼"), // 胭脂魚
        PARROTFISH(3, "鹦哥鱼"), // 鸚哥魚
        CAROAKER(4, "黄花鱼"), // 黃花魚
        CLOWN_FISH(5, "小丑鱼"), // 小丑魚
        HAPPY_FISH(6, "开心鱼"), // 開心魚
        CARP(7, "鲤鱼"), // 鯉魚
        HIPPOCAMPUS(8, "海马"), // 海馬
        GREEN_JELLYFISH(9, "绿水母"), // 綠水母
        TURTLE(10, "乌龟"), // 烏龜
        SQUID(11, "鱿鱼"), // 魷魚
        MONKFISH(12, "安康鱼"), // 安康魚
        PUFFERFISH(13, "河豚"), // 河豚
        LIMULUS(14, "鲎鱼"), // 鱟魚
        SPEARFISH(15, "旗鱼"), // 旗魚
        WHALE(16, "鲸鱼"), // 鯨魚
        JELLY_FISH(17, "水母"), // 水母
        BAT_FISH(18, "蝠鱼"), // 蝠魚
        GOLDEN_HAMMERHEAD_SHARK(19, "金鲨"), // 金鯊
        GOLDEN_TOAD(20, "金蟾"), // 金蟾
        GOLD_BAOPEN(21, "金宝盆"), // 金寶盆
        MAMMON(22, "财神"), // 財神
        MACHINE_CANNON(23, "机关炮"), // 機關炮
        BIT_GUN(24, "钻头炮"), // 鑽頭炮
        TURNTABLE(25, "财运转轮"), // 財運轉輪
        THE_GOD_OF_WEALTH(26, "财神发发发"); // 財神發發發

        private final int code; // 編碼
        private final String name; // 名稱
        TargetEnumCsbyJava(int code, String name){
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
            final TargetEnumCsbyJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (TargetEnumCsbyJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getCode(), enumInstance.getName());
            }
            return result;
        }
    }
}
