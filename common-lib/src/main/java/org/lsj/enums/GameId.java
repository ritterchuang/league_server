package org.lsj.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 遊戲識別碼
 */
public enum GameId {
    INVALID(0, GameType.INVALID), // 無效
    LUCKY777(1, GameType.SLOT), // LUCKY777
    HHDZ_JAVA(105, GameType.BAIREN), // 新紅黑大戰
    BJL_JAVA(109, GameType.BAIREN), // 新百家樂
    BRNN_JAVA(110, GameType.BAIREN), // 新百人牛牛
    LHD_JAVA(111, GameType.BAIREN), // 新龍虎鬥
    ZJH_JAVA(112, GameType.BANKER), // 新炸金花
    QZNN_JAVA(113, GameType.BANKER), // 新搶莊牛牛
    EBG_JAVA(114, GameType.BANKER), // 新搶莊二八槓
    QZNN_KSZ_JAVA(115, GameType.BANKER), // 新看三張搶莊牛牛
    SG_JAVA(117, GameType.BANKER), // 新三公
    QZPJ_JAVA(122, GameType.BANKER), // 新搶莊牌九
    TBNN_JAVA(125, GameType.BANKER), // 新通比牛牛
    QZNN_K4Z_JAVA(126, GameType.BANKER), // 新看四張搶莊牛牛
    LZNN_JAVA(127, GameType.BANKER), // 新賴子牛牛
    MYBJL_JAVA(129, GameType.BAIREN), // 新免傭百家樂
    CJNN_JAVA(132, GameType.BAIREN), // 新超級牛牛

    JCBY_JAVA(201, GameType.FISH), // 金蟾捕魚
    CSBY_JAVA(202, GameType.FISH), // 財神捕魚
    SLBY_JAVA(203, GameType.FISH), // 神龍捕魚
    RYCS_JAVA(204, GameType.FISH), // 人魚傳說
    SZZB_JAVA(205, GameType.FISH), // 世足爭霸

    PXKY_JAVA(301, GameType.SLOT), // 豼貅開運
    DYDB_JAVA(302, GameType.SLOT), // 大運奪寶
    SDZW_JAVA(303, GameType.SLOT), // 聖誕任務
    WL_JAVA(304, GameType.SLOT), // 旺來
    MJWS_JAVA(305, GameType.SLOT), // 麻將無雙
    BSZX_JAVA(306, GameType.SLOT), // 寶石之星
    CCC_JAVA(307, GameType.SLOT), // 777
    SBXC_JAVA(308, GameType.SLOT), // 三倍小丑
    SN_JAVA(309, GameType.SLOT), // 水牛
    SWZS_JAVA(310, GameType.SLOT), // 死亡之書
    LMJJC_JAVA(311, GameType.SLOT), // 羅馬競技場
    DGRY_JAVA(312, GameType.SLOT), // 帝国榮耀
    LLL_JAVA(313, GameType.SLOT), // 龍龍龍
    OLLDBZ_JAVA(314, GameType.SLOT), // 歐賴利的寶藏
    HJXB_JAVA(315, GameType.SLOT), // 黃金西部
    XZCQ_JAVA(316, GameType.SLOT), // 小豬傳奇
    CJWP_JAVA(320, GameType.SLOT), // 超級王牌
    XJTB_JAVA(323, GameType.SLOT), // 仙境探寶
    ZCJZ_JAVA(324, GameType.SLOT), // 招財金豬

    _DEVELOP_BY_JAVA(982, GameType.FISH), // 魚機測試模板
    _DEVELOP_HJ_JAVA(983, GameType.SLOT), // 虎機測試模板
    _MODEL_BY_JAVA(992, GameType.FISH), // 魚機模板
    _MODEL_HJ_JAVA(993, GameType.SLOT), // 虎機模板
    THIRD_GAME_ID_BEGIN(501,GameType.THIRD_GAME),       //三方游戏game_id的起始id（与客户端约定大于此id为三方游戏）
    THIRD_GAME_ID_END(8000,GameType.THIRD_GAME);        //三方游戏game_id的起始id（与客户端约定大于此id为三方游戏）

    private final int id; // 編碼
    private final GameType gameType; // 遊戲類型

    private static final Map<Integer, GameId> BY_ID = new HashMap<>();

    static {
        for (GameId e : values()) {
            BY_ID.put(e.id, e);
        }
    }

    GameId(int id, GameType gameType) {
        this.id = id;
        this.gameType = gameType;
    }

    public int getId() {
        return id;
    }

    public GameType getGameType() {
        return gameType;
    }

    public static GameId fromId(int id) {
        final GameId gameId = BY_ID.get(id);
        return gameId == null ? INVALID : gameId;
    }

}
