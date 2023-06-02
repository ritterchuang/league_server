package org.lsj.gs.math.core.card.stackCtr.sgStackCtr;

// 三公牌型定義 TODO 整理
public class ConstSgStack {
    // 通用三公牌型定義
    public enum SgTypeEnumCommon {
        NONE(-1), // 非法
        PT_0(0), // 0 點
        PT_1(1), // 1 點
        PT_2(2), // 2 點
        PT_3(3), // 3 點
        PT_4(4), // 4 點
        PT_5(5), // 5 點
        PT_6(6), // 6 點
        PT_7(7), // 7 點
        PT_8(8), // 8 點
        PT_9(9), // 9 點
        SANGONG(10), // 三公
        DASANGONG(11), // 大三公
        ZHIZUN(12); // 至尊

        private final int type; // 牌型編碼
        SgTypeEnumCommon(int type){
            this.type = type;
        }
        public int getType() {
            return type;
        }
        public static SgTypeEnumCommon fromType(int type) {
            final SgTypeEnumCommon[] allEnumInstance = values();
            for (SgTypeEnumCommon enumInstance : allEnumInstance) {
                if (enumInstance.getType() == type) {
                    return enumInstance;
                }
            }
            return NONE;
        }

        public static SgTypeEnumCommon calculateSgCount(int count) {
            switch (count) {
                case 0: return PT_0;
                case 1: return PT_1;
                case 2: return PT_2;
                case 3: return PT_3;
                case 4: return PT_4;
                case 5: return PT_5;
                case 6: return PT_6;
                case 7: return PT_7;
                case 8: return PT_8;
                case 9: return PT_9;
                default: return NONE;
            }
        }
    }
}
