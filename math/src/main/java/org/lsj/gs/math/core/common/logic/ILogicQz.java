package org.lsj.gs.math.core.common.logic;

import java.util.List;
import java.util.Map;

// 搶莊類邏輯介面
public interface ILogicQz {
    //* 搶莊計算器 *//
    //** 可搶莊倍數 **//

    // 取得真人可搶莊倍數列表
    int[] getHumanCanVieRateArray();

    // 取得真人搶莊最大倍數
    int getHumanMaxVieRate();

    // 取得指定玩家可搶莊列表
    List<Integer> getPlayerCanVieRateList(int chairIndex);

    //** 接收搶莊訊息相關 **//

    // 是否重複搶莊
    boolean isPlayerVied(int chairIndex);

    // 驗證有效的搶莊倍數
    boolean isValidVieRate(int chairIndex, int rate);

    // 更新搶莊倍數
    void setPlayerVieRate(int chairIndex, int rate);


    //** 結束搶莊相關 **//

    // 是否完成搶莊
    boolean isFinishVie();

    // 結束搶莊
    void finishVie();


    //** 搶莊結果相關 **//

    // 取得莊家座位
    int getBankerChairIndex();

    // 取得莊家倍數
    int getBankerRate();

    // 檢查莊家押注
    boolean isBanker(int chairIndex);

    // 取得搶莊倍數列表
    int[] getVieRateArray();


    //** 超時搶莊相關 **//

    // 取得超時未押注表演名單
    Map<Integer, Integer> getTimeOutVieRate();


    //** 候選搶莊相關 **//

    // 是否表演候選搶莊
    boolean isShowVieBankAnimation();

    // 取得搶莊候選名單
    int[] getVieCandidateArrayMessage();


    //* 下注計算器 *//
    //** 可下注倍數相關 **//

    // 產出閒家押注列表
    void generateCanBetRateListMap();

    // 取得倍數列表
    List<Integer> getPlayerCanBetRateList(int chairIndex);

    // 取得真人押注列表
    int[] getHumanCanBetRateArray();


    //** 接受下注訊息相關 **//

    // 檢查重複押注
    boolean isPlayerBet(int chairIndex);

    // 驗證有效的下注倍數
    boolean isValidBetRate(int chairIndex, int rate);

    // 更新下注倍數
    void setPlayerBetRate(int chairIndex, int rate);


    //** 結束下注相關 **//

    // 檢查是否結束下注狀態
    boolean isFinishBet();

    // 結束下注
    void finishBet();


    //** 下注結果相關 **//

    // 取得閒家倍數列表
    int[] getBetResultArray();


    //** 超時下注相關 **//

    // 取得超時未押注表演名單
    Map<Integer, Integer> getTimeOutBetRate();
}
