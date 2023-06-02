package org.lsj.gs.math.core.fish;

import org.lsj.gs.math.core.common.ConstMathCommon;

// 魚機工具包定義
public class ConstMathFish {
    // 狀態定義
    public enum StateEnumFish {
        INVALID(0), // 非法
        WAIT_BEGIN(1), // 等待開始
        GAME_BEGIN(2), // 遊戲開始
        GAME_END(ConstMathCommon.StateEnum.GAME_END.getCode());

        private final int code; // 編碼
        StateEnumFish(int code){
            this.code = code;
        }
        public int getCode(){
            return this.code;
        }
        public static StateEnumFish fromCode(int code) {
            final StateEnumFish[] allEnumInstance = values();
            for (StateEnumFish enumInstance : allEnumInstance) {
                if (enumInstance.getCode() == code) {
                    return enumInstance;
                }
            }
            return INVALID;
        }
    }

    // 子彈類型定義
    public enum BulletType {
        INVALID(0), // 非法
        NORMAL(1); // 一般子彈

        private final int code; // 編碼
        BulletType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 子彈類型定義
    public enum BulletMechanismType {
        INVALID(0), // 非法
        NORMAL(1), // 一般子彈
        MACHINE(2), // 機關炮機制
        DRILL(3); // 鑽頭炮機制

        private final int code; // 編碼
        BulletMechanismType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 子彈成本類型
    public enum BulletCostType {
        INVALID(0), // 非法
        RATIO(1), // 等比例成本
        FREE(2); // 免費

        private final int code; // 編碼
        BulletCostType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    public enum BulletCostListType {
        INVALID(0), // 非法
        NONE(1),
        TEN_MULTI_TEN(2), // 十等份再乘十等份; 舉例: [1, 100] -> [1,2,3,4,5,6,7,8,9,10,20,30,40,50,60,70,80,90,100]
        TEN(3); // 切十等分; 舉例: [1, 10] -> [1,2,3,4,5,6,7,8,9,10

        private final int code; // 編碼
        BulletCostListType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

     // 子彈數量類型
     public enum BulletAmountType {
         INVALID(0), // 非法
         INFINITE(1), // 無限
         FINITE(2); // 有限

         private final int code; // 編碼
         BulletAmountType(int code){
             this.code = code;
         }
         public int getCode() {
             return code;
         }
     }

    // 子彈Rtp類型
    public enum BulletRtpUseType {
        INVALID(0), // 非法
        NONE(1), // 子彈不攜帶Rtp
        HAVE(2); // 子彈攜帶Rtp

        private final int code; // 編碼
        BulletRtpUseType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 特殊事件類型 TODO 待確認名稱
    public enum SpecialFeatureEnumType {
        INVALID(0, "非法"), // 非法
        NONE(1, "無特殊事件"), // 無特殊獎項
        RED_ENVELOPE(2, "紅包事件"); // 紅包特殊獎項

        private final int code; // 編碼
        private final String name; // 名稱

        SpecialFeatureEnumType(int code, String name){
            this.code = code;
            this.name = name;
        }

        public int getCode() {
            return code;
        }
        public String getName() {
            return name;
        }

        public static SpecialFeatureEnumType fromCode(int code) {
            final SpecialFeatureEnumType[] allEnumInstance = values();
            for (SpecialFeatureEnumType enumInstance : allEnumInstance) {
                if (enumInstance.getCode() == code) {
                    return enumInstance;
                }
            }
            return INVALID;
        }
    }

     // 獎勵子彈類型
     public enum AwardBulletType {
        INVALID(0), // 非法
        NONE(1), // 無獎勵子彈
        ONE_TYPE(2), // 單一獎勵子彈類型
        MULTI_TYPE(3); // 多重獎勵子彈類型

        private final int code; // 編碼
        AwardBulletType(int code){
            this.code = code;
        }
        public int getCode() {
             return code;
         }
     }

     // 獎勵子彈計算方式類型
    public enum AwardBulletCtrType {
        INVALID(0), // 非法
        NORMAL(1), // 單純新子彈
        UPGRADE(2); // 升級子彈

         private final int code; // 編碼
         AwardBulletCtrType(int code){
             this.code = code;
         }
         public int getCode() {
             return code;
         }
     }

     // Rtp選擇類型
    public enum RtpChoiceType {
        INVALID(0), // 非法
        BULLET(1), // 從子彈獲取Rtp
        COMPANY_ADJUST(2); // 從調控設定獲取Rtp

        private final int code;
        RtpChoiceType(int code) {this.code = code;}
        public int getCode() {return code;}
     }

    // 打擊類型
    public enum HitType {
        INVALID(0), // 非法
        FIXED_ODDS(1), // 固定倍數
        RANDOM_ODDS(2), // 隨機倍數
        MULTI_TARGET(3), // 多重目標
        WHEEL(4), // 輪盤
        RED_ENVELOPE(5), // 紅包
        DRAGON_TREASURE(6), // 神龍尋珠
        DOUBLE_WHEEL(7), // 雙層輪盤
        TREASURE_BOX(8), // 連開寶箱
        REEL(9), // 滾輪倍數
        TRIPLE_WHEEL(10); // 三層輪盤

        private final int code; // 編碼
        HitType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 客製特殊表演類型
    public enum DisplayType {
        INVALID(0), // 非法
        MULTI_TARGET(1), // 多重目標
        RED_ENVELOPE(2), // 紅包特殊事件
        WHEEL(3), // 滾輪特殊事件
        DRAGON_TREASURE(4), // 神龍尋珠
        DOUBLE_WHEEL(5), // 雙層輪盤
        TREASURE_BOX(6), // 連開寶箱
        REEL(7),
        TRIPLE_WHEEL(8); // 滾輪倍數

        private final int code; // 編碼
        DisplayType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }
}
