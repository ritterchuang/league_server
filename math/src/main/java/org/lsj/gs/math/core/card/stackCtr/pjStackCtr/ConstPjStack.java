package org.lsj.gs.math.core.card.stackCtr.pjStackCtr;

// 牌九牌型定義
public class ConstPjStack {
    // 通用牌九單牌定義
    public enum PjCardTypeEnumCommon {
        INVALID(-1, 0), // 非法
        TP(606, 21), // 天牌
        DP(101, 20), // 地牌
        RP(404, 19), // 人牌
        EP(103, 18), // 鵝牌
        MP(505, 17), // 梅牌
        CS(303, 16), // 长三
        BD(202, 15), // 板凳
        FT(506, 14), // 斧头
        HTS(406, 13), // 红头十
        GJQ(106, 12), // 高脚七
        LLL(105, 11), // 零零六
        Z91(306, 9), // 杂九
        Z92(405, 9), // 杂九
        Z81(305, 7), // 杂八
        Z82(206, 7), // 杂八
        Z71(304, 5), // 杂七
        Z72(205, 5), // 杂七
        Z51(302, 3), // 杂五
        Z52(104, 3), // 杂五
        ES(204, 2), // 二四
        DS(102, 1); // 丁三

        private final int code; // 牌型編碼
        private final int weight; // 牌型權重
        PjCardTypeEnumCommon(int code, int weight){
            this.code = code;
            this.weight = weight;
        }
        public int getCode() {
            return code;
        }

        public int getWeight() {
            return weight;
        }

        public static PjCardTypeEnumCommon fromCode(int code) {
            final PjCardTypeEnumCommon[] allEnumInstance = values();
            for (PjCardTypeEnumCommon enumInstance : allEnumInstance) {
                if (enumInstance.getCode() == code) {
                    return enumInstance;
                }
            }
            return INVALID;
        }
    }

    // 通用牌九牌型定義
    public enum PjTypeEnumCommon {
        INVALID(-1, PjClassTypeEnumCommon.INVALID, 0), // 非法
        PT_0(0,     PjClassTypeEnumCommon.SINGLE, 0),  // 0 点
        PT_1(1,     PjClassTypeEnumCommon.SINGLE, 1),  // 1 点
        PT_2(2,     PjClassTypeEnumCommon.SINGLE, 2),  // 2 点
        PT_3(3,     PjClassTypeEnumCommon.SINGLE, 3),  // 3 点
        PT_4(4,     PjClassTypeEnumCommon.SINGLE, 4),  // 4 点
        PT_5(5,     PjClassTypeEnumCommon.SINGLE, 5),  // 5 点
        PT_6(6,     PjClassTypeEnumCommon.SINGLE, 6),  // 6 点
        PT_7(7,     PjClassTypeEnumCommon.SINGLE, 7),  // 7 点
        PT_8(8,     PjClassTypeEnumCommon.SINGLE, 8),  // 8 点
        PT_9(9,     PjClassTypeEnumCommon.SINGLE, 9),  // 9  点
        DGJ(10,     PjClassTypeEnumCommon.COMBO, 10),  // 地高九
        TGJ(11,     PjClassTypeEnumCommon.COMBO, 11),  // 天高九
        DG(12,      PjClassTypeEnumCommon.COMBO, 12),  // 地杠
        TG(13,      PjClassTypeEnumCommon.COMBO, 13),  // 天杠
        DW(14,      PjClassTypeEnumCommon.COMBO, 14),  // 地王
        TW(15,      PjClassTypeEnumCommon.COMBO, 15),  // 天王
        ZW(16,      PjClassTypeEnumCommon.COMBO, 16),  // 杂五
        Z7(17,      PjClassTypeEnumCommon.COMBO, 17),  // 杂七
        Z8(18,      PjClassTypeEnumCommon.COMBO, 18),  // 杂八
        Z9(19,      PjClassTypeEnumCommon.COMBO, 19),  // 杂九
        SLL(20,     PjClassTypeEnumCommon.COMBO, 20),  // 双零霖
        SGJ(21,     PjClassTypeEnumCommon.COMBO, 21),  // 双高脚
        SHT(22,     PjClassTypeEnumCommon.COMBO, 22),  // 双红头
        SFT(23,     PjClassTypeEnumCommon.COMBO, 23),  // 双斧头
        SBD(24,     PjClassTypeEnumCommon.COMBO, 24),  // 双板凳
        SCS(25,     PjClassTypeEnumCommon.COMBO, 25),  // 双长三
        SM(26,      PjClassTypeEnumCommon.COMBO, 26),  // 双梅
        SE(27,      PjClassTypeEnumCommon.COMBO, 27),  // 双鹅
        SR(28,      PjClassTypeEnumCommon.COMBO, 28),  // 双人
        SD(29,      PjClassTypeEnumCommon.COMBO, 29),  // 双地
        ST(30,      PjClassTypeEnumCommon.COMBO, 30),  // 双天
        ZZ(31,      PjClassTypeEnumCommon.COMBO, 31);  // 至尊

        private final int type; // 牌型編碼
        private final int weight; // 牌型權重
        private final PjClassTypeEnumCommon pjClassTypeEnumCommon; // 牌九牌型分類
        PjTypeEnumCommon(int type, PjClassTypeEnumCommon pjClassTypeEnumCommon, int weight){
            this.type = type;
            this.pjClassTypeEnumCommon = pjClassTypeEnumCommon;
            this.weight = weight;
        }
        public int getType() {
            return type;
        }

        public int getWeight() {
            return weight;
        }

        public PjClassTypeEnumCommon getPjClassTypeEnumCommon() {
            return pjClassTypeEnumCommon;
        }

        public static PjTypeEnumCommon fromCode(int code) {
            final PjTypeEnumCommon[] allEnumInstance = values();
            for (PjTypeEnumCommon enumInstance : allEnumInstance) {
                if (enumInstance.getType() == code) {
                    return enumInstance;
                }
            }
            return INVALID;
        }
    }

    // 牌九牌型分類定義
    public enum PjClassTypeEnumCommon {
        INVALID(-1), // 非法
        SINGLE(0), // 單牌
        COMBO(1); // 雙牌

        private final int code; // 牌型編碼
        PjClassTypeEnumCommon(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
        public static PjClassTypeEnumCommon fromCode(int code) {
            final PjClassTypeEnumCommon[] allEnumInstance = values();
            for (PjClassTypeEnumCommon enumInstance : allEnumInstance) {
                if (enumInstance.getCode() == code) {
                    return enumInstance;
                }
            }
            return INVALID;
        }
    }
}
