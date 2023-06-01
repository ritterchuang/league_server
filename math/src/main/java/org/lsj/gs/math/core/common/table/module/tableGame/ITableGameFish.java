package org.lsj.gs.math.core.common.table.module.tableGame;

import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultFish;
import org.lsj.gs.math.core.common.poolCtr.entity.IPoolConfig;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.client.MathFishConfig;

import java.util.Optional;

// 遊戲介面 魚機
public interface ITableGameFish extends ITableGame{
    //* 流程相關 *//
    void startBeforeGameEnd(); // 啟動遊戲結束前處理

    //* 遊戲設定相關 *//
    MathFishConfig getMathFishConfig(); // 取得數值設定

    //* 遊戲玩家相關 *//
    boolean checkHumanBeginMoneyEnough(double cost); // 檢查真人玩家初始金額足夠性
    void updateHumanAfterMoney(double score); // 更新真人玩家 剩餘金額

    //* 水池相關 *//
    void updatePoolConfig(IPoolConfig poolConfig); // 更新水池資訊

    //* 遊戲結果相關 *//
    IGameBetLogResultFish getHitResult(String ctsGetHitResultDataString) throws TableException; // 取得打擊結果
    Optional<IGameBetLogResultFish> calculateFishReturnResult(); // 計算返還結果

    //* 亂數相關 *//
    void setRngData(String rngDataString); // 設定亂數

    //* 重置相關 *//
    void reset(); // 重置
}
