package org.lsj.gs.math.games.rycs_java.enity;

import org.lsj.gs.math.core.fish.ConstMathFish;

import java.util.HashMap;
import java.util.Map;

// 人魚傳說定義
public class ConstRycsJava {
    // 子彈定義
    public enum BulletEnumRycsJava {
        INVALID(0, ConstMathFish.BulletMechanismType.INVALID.getCode(), "無效"), // 無效
        NORMAL(1, ConstMathFish.BulletMechanismType.NORMAL.getCode(), "一般子弹"), // 一般子彈
        TRIDENT(2, ConstMathFish.BulletMechanismType.DRILL.getCode(),"三叉戟"); // 三叉戟

        private final int code; // 編碼
        private final int bulletMechanismCode; // 子彈機制編碼
        private final String name; // 名稱

        BulletEnumRycsJava(int code, int bulletMechanismCode, String name){
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
            final BulletEnumRycsJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (BulletEnumRycsJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getBulletMechanismCode(), enumInstance.getName());
            }
            return result;
        }
    }

    // 目標定義
    public enum TargetEnumRycsJava {
        INVALID(0, "無效"), // 無效
        LOBSTER(1, "龙虾"), // 龍蝦
        PUFFER_FISH(2, "河豚"), // 河豚
        SQUID(3, "鱿鱼"), // 魷魚
        CLOWN_FISH(4, "小丑鱼"), // 小丑魚
        JELLYFISH(5, "小水母"), // 小水母
        LANTERN_FISH(6, "灯笼鱼"), // 燈籠魚
        CHUBBY_PLECO(7, "热带鱼"), // 熱帶魚
        HIPPOCAMPUS(8, "海马"), // 海馬
        BIG_JELLYFISH(9, "大水母"), // 大水母
        DEVIL_FISH(10, "魔鬼鱼"), // 魔鬼魚
        MOON_FISH(11, "月亮鱼"), // 月亮魚
        DOLPHIN(12, "海豚"), // 海豚
        SWORDFISH(13, "剑鱼"), // 劍魚
        KILLER_WHALE(14, "虎鲸"), // 虎鯨
        OCTOPUS(15, "章鱼"), // 章魚
        SHARK(16, "鲨鱼"), // 鯊魚
        SUNFISH(17, "翻车鱼"), // 翻車魚
        GREEN_LOGGERHEAD_TURTLE(18, "绿蠵龟"), // 綠蠵龜
        ICE_MERMAID(19, "冰雪美人鱼"), // 冰雪美人魚
        TRIDENT(20, "三叉戟"), // 三叉戟
        DARK_MERMAID(21, "闇黑美人鱼"), // 闇黑美人魚
        GHOST_SAILOR_1(22, "幽灵水手1"), // 幽靈水手1
        GHOST_SAILOR_2(23, "幽灵水手2"), // 幽靈水手2
        GHOST_SAILOR_3(24, "幽灵水手3"), // 幽靈水手3
        GHOST_SAILOR_4(25, "幽灵水手4"), // 幽靈水手4
        GHOST_SHIP_AND_CAPTAIN(26, "幽灵船及船长"), // 幽靈船及船長
        PEARL_MERMAID(27, "珍珠美人鱼"), // 珍珠美人魚
        SHIP_WHEEL_MERMAID(28, "转轮美人鱼"); // 轉輪美人魚

        private final int code; // 編碼
        private final String name; // 名稱
        TargetEnumRycsJava(int code, String name){
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
            final TargetEnumRycsJava[] allEnumInstance = values();
            Map<Integer, String> result = new HashMap<>();
            for (TargetEnumRycsJava enumInstance : allEnumInstance) {
                result.put(enumInstance.getCode(), enumInstance.getName());
            }
            return result;
        }
    }
}
