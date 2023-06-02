package org.lsj.gs.math.core.card.stackCtr.niuStackCtr;

// 牛型定義
public class ConstNiu {
    // 通用牛牛牌型定義
    public enum NiuTypeEnumCommon {
        INVALID(-1), // 非法
        NIU_0(0), // 無牛
        NIU_1(1), // 牛1
        NIU_2(2), // 牛2
        NIU_3(3), // 牛3
        NIU_4(4), // 牛4
        NIU_5(5), // 牛5
        NIU_6(6), // 牛6
        NIU_7(7), // 牛7
        NIU_8(8), // 牛8
        NIU_9(9), // 牛9
        NIU_NIU(10), // 牛牛
        FLOWER_4(11), // 四花牛; 一張10 另外四張皆JQK組成;
        SHUNZI_NIU(12), // 順子牛; 五張牌連續;
        TONGHUA_NIU(13), // 同花牛; 五張牌皆同花色;
        HULU_NIU(14), // 葫蘆牛; 五張牌中任意三張同點數且另兩張也同點數;
        FLOWER_5(15), // 五花牛; 全部牌皆JQK組成;
        BOMB(16), // 四炸彈; 四張牌點數一致;
        SHUNJIN_NIU(17), // 順金牛; 五張牌連續且同花色;
        SMALL_NIU(18), // 小牛牛; 五張牌皆小於5 且點數總和小於等於10;
        HYDROGEN_BOMB(19); // 氫彈牛; 五張牌點數皆相同;

        private final int type; // 牌型編碼
        NiuTypeEnumCommon(int type){
            this.type = type;
        }
        public int getType() {
            return type;
        }
        public static NiuTypeEnumCommon fromType(int type) {
            final NiuTypeEnumCommon[] allEnumInstance = values();
            for (NiuTypeEnumCommon enumInstance : allEnumInstance) {
                if (enumInstance.getType() == type) {
                    return enumInstance;
                }
            }
            return INVALID;
        }
        public static NiuTypeEnumCommon calculateNiuCount(int count) {
            switch (count) {
                case 0: return NIU_NIU;
                case 1: return NIU_1;
                case 2: return NIU_2;
                case 3: return NIU_3;
                case 4: return NIU_4;
                case 5: return NIU_5;
                case 6: return NIU_6;
                case 7: return NIU_7;
                case 8: return NIU_8;
                case 9: return NIU_9;
                default: return INVALID;
            }
        }
    }
}
