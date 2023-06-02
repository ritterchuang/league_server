package org.lsj.gs.math.games.brnn_java.entity;

// 新百人牛牛定義
public class ConstBrnnJava {

    // 牛型定義
    public enum NiuTypeEnumBrnnJava {
        INVALID(-1), // 無效
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
        FLOWER_5(12), // 五花牛; 全部牌皆JQK組成;
        BOMB(13), // 四炸彈; 四張牌點數一致;
        SMALL_NIU(14); // 小牛牛; 五張牌皆小於5 且點數總和小於等於10;

        private final int type; // 牌型
        NiuTypeEnumBrnnJava(int type){
            this.type = type;
        }
        public int getType() {
            return type;
        }
    }
}
