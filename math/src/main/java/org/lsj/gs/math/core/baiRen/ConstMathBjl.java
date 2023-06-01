package org.lsj.gs.math.core.baiRen;

import java.util.Arrays;

// 數值百家樂定義
public class ConstMathBjl {
    // 發牌區域定義
    public enum DealAreaEnum {
        INVALID(-1), // 無效
        PLAY(0), // 閒
        BANK(1); // 庄

        private final int code; // 編碼

        DealAreaEnum(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        // 取得區域個數
        public static int getDealAreaCount(){
            return (int) Arrays.stream(values()).mapToInt(DealAreaEnum::getCode).filter(areaCode -> areaCode != -1).count();
        }
    }

    // 路圖定義
    public enum ChartEnum {
        INVALID(-1, BetAreaTypeEnum.INVALID),   // 無效

        TIE(1, BetAreaTypeEnum.TIE),            // 和牌

        BANK_WIN(11,    BetAreaTypeEnum.BANK),  // 庄
        BANK_BP(12,     BetAreaTypeEnum.BANK),  // 庄、庄对
        BANK_PP(13,     BetAreaTypeEnum.BANK),  // 庄、闲对
        BANK_BP_PP(14,  BetAreaTypeEnum.BANK),  // 庄、庄对、闲对

        PLAY_WIN(21,    BetAreaTypeEnum.PLAY),  // 闲
        PLAY_BP(22,     BetAreaTypeEnum.PLAY),  // 闲、庄对
        PLAY_PP(23,     BetAreaTypeEnum.PLAY),  // 闲、闲对
        PLAY_BP_PP(24,  BetAreaTypeEnum.PLAY);  // 闲、庄对、闲对

        private final int code; // 編碼
        private final BetAreaTypeEnum betAreaTypeEnum; // 押注區域類型

        ChartEnum(int code, BetAreaTypeEnum betAreaTypeEnum){
            this.code = code;
            this.betAreaTypeEnum = betAreaTypeEnum;
        }

        public int getCode() {
            return code;
        }

        public BetAreaTypeEnum getBetAreaType() {
            return betAreaTypeEnum;
        }

        public static ChartEnum fromCode(int code) {
            final ChartEnum[] allEnumInstance = values();
            for (ChartEnum enumInstance : allEnumInstance) {
                if (enumInstance.getCode() == code) {
                    return enumInstance;
                }
            }
            return INVALID;
        }
    }

    // 押注區定義
    public enum BetAreaEnum {
        INVALID(       -1, BetAreaTypeEnum.INVALID),       // 無效

        UPBANK_LOSE(    0, BetAreaTypeEnum.PLAY),          // 上庄输
        PLAY(           1, BetAreaTypeEnum.PLAY),          // 闲
        PLAY_PAIR(      2, BetAreaTypeEnum.PLAY),          // 闲对
        SMALL(          3, BetAreaTypeEnum.PLAY),          // 小

        UPBANK_WIN(     4, BetAreaTypeEnum.BANK),          // 上庄赢
        BANK(           5, BetAreaTypeEnum.BANK),          // 庄
        BANK_PAIR(      6, BetAreaTypeEnum.BANK),          // 庄对
        BIG(            7, BetAreaTypeEnum.BANK),          // 大

        TIE(            8, BetAreaTypeEnum.TIE);           // 和

        private final int code; // 牌型
        private final BetAreaTypeEnum betAreaTypeEnum; // 押注區域類型

        BetAreaEnum(int code, BetAreaTypeEnum betAreaTypeEnum) {
            this.code = code;
            this.betAreaTypeEnum = betAreaTypeEnum;
        }

        public int getCode() {
            return code;
        }
        public BetAreaTypeEnum getBetAreaType() {
            return betAreaTypeEnum;
        }

        // 取得區域個數
        public static int getBetAreaCount(){
            return (int) Arrays.stream(values()).mapToInt(BetAreaEnum::getCode).filter(betAreaId -> betAreaId != -1).count();
        }

        // 取得區域個數
        public static boolean checkSameBetAreaType(int betAreaId, BetAreaTypeEnum betAreaTypeEnum){
            return BetAreaEnum.fromCode(betAreaId).getBetAreaType().getType() == betAreaTypeEnum.getType();
        }

        public static BetAreaEnum fromCode(int betAreaId) {
            final BetAreaEnum[] allEnumInstance = values();
            for (BetAreaEnum enumInstance : allEnumInstance) {
                if (enumInstance.getCode() == betAreaId) {
                    return enumInstance;
                }
            }
            return INVALID;
        }
    }

    // 押注類型定義
    public enum BetAreaTypeEnum {
        INVALID(-1),    // 無效
        PLAY(0),        // 閒
        BANK(1),        // 庄
        TIE(2);         // 和

        private final int type; // 牌型
        BetAreaTypeEnum(int type){
            this.type = type;
        }
        public int getType() {
            return type;
        }

        public static BetAreaTypeEnum fromCode(int code) {
            final BetAreaTypeEnum[] allEnumInstance = values();
            for (BetAreaTypeEnum enumInstance : allEnumInstance) {
                if (enumInstance.getType() == code) {
                    return enumInstance;
                }
            }
            return INVALID;
        }
    }
}
