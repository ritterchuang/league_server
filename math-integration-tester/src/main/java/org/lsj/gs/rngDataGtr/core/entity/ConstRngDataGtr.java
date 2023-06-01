package org.lsj.gs.rngDataGtr.core.entity;

// 亂數產生器定義
public class ConstRngDataGtr {
    // 測項種類定義
    public enum TestCaseTypeEnum {
        // 百人
        E_105_HHDZ_JAVA_SPECIFY_BET, // 新紅黑大戰 押注測項
        E_105_HHDZ_JAVA_SPECIFY_CARD_TYPE, // 新紅黑大戰 牌型測項
        E_111_LHD_JAVA_SPECIFY_BET_AND_CARD_TYPE, // 新龍虎 牌型測項
        E_BAIREN_NIU_JAVA_SPECIFY_BET, // 新百人類 牛 押注測項
        E_BAIREN_NIU_JAVA_CARD_TYPE, // 新百人類 牛 牌型測項
        E_BAIREN_BJ_JAVA_SPECIFY_BET, // 新新百人類 百家 押注測項
        E_BAIREN_BJ_JAVA_DEAL, // 新新百人類 百家 發牌測項

        // 搶莊
        E_113_QZNN_JAVA_NIU_TYPE, // 新搶莊牛牛 牌型測項
        E_114_EBG_JAVA_EBG_TYPE, // 新二八槓 牌型測項
        E_115_QZNN_KSZ_JAVA_NIU_TYPE, // 新看三張搶莊牛牛 牌型測項
        E_117_SG_JAVA_SG_TYPE, // 新三公 牌型測項
        E_122_QZPJ_JAVA_PJ_TYPE, // 新牌九 牌型測項
        E_122_QZPJ_JAVA_TIE, // 新牌九 牌型相等測項
        E_125_TBNN_JAVA_NIU_TYPE, // 新通比牛牛 牌型測項
        E_126_QZNN_K4Z_JAVA_NIU_TYPE, // 新看四張搶莊牛牛 牌型測項
        E_126_QZNN_K4Z_JAVA_SAME_RESULT_TYPE, // 新看四張搶莊牛牛 相同牌型測項
        E_127_LZNN_JAVA_NIU_TYPE, // 新賴子牛牛 牌型測項
        E_BANKER_ALL_WIN_LOSS, // 搶莊全輸贏測項

        // 魚機
        E_FISH_SPECIFY_HIT_KILL_FLAG, // 魚機指定打擊方式的擊殺判斷

        // 虎機
        E_SLOT_CASCADE_WAY_GAME, // 消除虎機 賠率表連線測項
        E_SLOT_CASCADE_LINE_GAME, // 消除虎機 賠率表連線測項(Line Game)
        E_SLOT_CASCADE_WAY_GAME_AND_SCREEN, // 消除虎機 賠率表連線與畫面測項
        E_SLOT_CASCADE_TIMES, //  消除虎機 消除次數測項
        E_SLOT_GAMESTATE, // 消除虎機 進指定狀態
        E_SLOT_ODDS_WIN_TYPE // 虎機倍數表演類型
    }
}
