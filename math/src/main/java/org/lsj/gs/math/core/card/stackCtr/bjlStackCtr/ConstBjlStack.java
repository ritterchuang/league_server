package org.lsj.gs.math.core.card.stackCtr.bjlStackCtr;

// 百家牌型定義
public class ConstBjlStack {
    // 通用牌型定義
    public enum BjlTypeEnumCommon {
        INVALID(-1), // 非法
        NONE(0), // 無
        DAN_ZHANG(1), // 單張
        DUI_ZI(2), // 對子
        SHUN_ZI(3), // 順子
        TONG_HUA(4), // 金花
        TONG_HUA_SHUN(5), // 順金
        BAO_ZI(6); // 豹子

        private final int type; // 牌型編碼
        BjlTypeEnumCommon(int type){
            this.type = type;
        }
        public int getType() {
            return type;
        }
        public static BjlTypeEnumCommon fromType(int type) {
            final BjlTypeEnumCommon[] allEnumInstance = values();
            for (BjlTypeEnumCommon enumInstance : allEnumInstance) {
                if (enumInstance.getType() == type) {
                    return enumInstance;
                }
            }
            return INVALID;
        }
    }
}
