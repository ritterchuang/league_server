package org.lsj.gs.math.games.slby_java.enity;

import org.lsj.gs.math.core.fish.ConstMathFish;

import java.util.HashMap;
import java.util.Map;

// 神龍捕魚定義
public class ConstSlbyJava {
    // 子彈定義
    public enum BulletEnumSlbyJava {
        INVALID(0, ConstMathFish.BulletMechanismType.INVALID.getCode(), "無效"), // 無效
        NORMAL(1, ConstMathFish.BulletMechanismType.NORMAL.getCode(), "一般子彈"), // 一般子彈
        BAZOOKA(2, ConstMathFish.BulletMechanismType.MACHINE.getCode(), "龙吟枪"), // 龙吟枪
        MISSILE(3, ConstMathFish.BulletMechanismType.DRILL.getCode(), "定海导弹"); // 定海导弹

        private final int code; // 編碼
        private final int bulletMechanismCode; // 子彈機制編碼
        private final String name; // 名稱

        BulletEnumSlbyJava(int code, int bulletMechanismCode, String name){
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
            final BulletEnumSlbyJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (BulletEnumSlbyJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getBulletMechanismCode(), enumInstance.getName());
            }
            return result;
        }
    }

    // 目標定義
    public enum TargetEnumSlbyJava {
        INVALID(0, "無效"), // 無效
        ORANGE_FISH(1, "小橘鱼"), // 小橘魚
        YELLOW_FISH(2, "小黄鱼"), // 小黃魚
        CRAYFISH(3, "小龙虾"), // 小龍蝦
        PUFFER_FISH(4, "小河豚"), // 小河豚
        GUPPY(5, "小乌贼"), // 小烏賊
        SQUID(6, "小丑鱼"), // 小丑魚
        HIPPOCAMPUS(7, "小海马"), // 小海馬
        JELLY_FISH(8, "小水母"), // 小水母
        TURTLE(9, "小乌龟"), // 小烏龜
        BUTTERFLY_FISH(10, "灯笼鱼"), // 燈籠魚
        LANTERN_FISH(11, "剑鱼"), // 劍魚
        PARROT_FISH(12, "海豚"), // 海豚
        LIMULUS(13, "鲎鱼"), // 鱟魚
        STINGRAY(14, "魟鱼"), // 魟魚
        SHARK(15, "鲨鱼"), // 鯊魚
        KISSING_FISH(16, "接吻鱼"), // 接吻魚
        BIG_JELLY_FISH(17, "大水母"), // 大水母
        GOLDEN_LIMULUS(18, "黄金鲎鱼"), // 黃金鱟魚
        GOLDEN_SHARK(19, "黄金鲨鱼"), // 黃金鯊魚
        TREASURE_BOX(20, "龙珠宝盒"), // 龍珠寶盒
        DRAGON(21, "神龙"), // 神龍
        SHENRON(22, "究极神龙"), // 究極神龍
        AUTOMATIC_BAZOOKA(23, "龙吟枪"), // 龍吟槍
        MISSILE(24, "定海导弹"), // 定海導彈
        ROLL_OF_BALL(25, "神龙转运"), // 神龍轉運
        DRAGON_TREASURE(26, "神龙寻珠"); // 神龍尋珠

        private final int code; // 編碼
        private final String name; // 名稱
        TargetEnumSlbyJava(int code, String name){
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
            final TargetEnumSlbyJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (TargetEnumSlbyJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getCode(), enumInstance.getName());
            }
            return result;
        }
    }
}
