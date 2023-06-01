package org.lsj.gs.math.core.common.table;

import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultFish;
import org.lsj.gs.math.core.common.poolCtr.entity.IPoolConfig;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.client.MathFishConfig;

import java.util.Optional;

// 服務端 Command 魚機介面
public interface ISeverTableCommandFish extends IServerTableCommon{
    //* 流程相關 *//
    void startBeforeGameEnd(); // 啟動遊戲結束前處理

    //* 遊戲設定相關 *//
    MathFishConfig getMathFishConfig(); // 取得數值設定

    //* 水池相關 *//
    IPoolConfig getPoolConfig(); // 取得水池
    void updatePoolConfig(IPoolConfig poolConfig); // 更新水池資訊

    //* 遊戲結果相關*//
    IGameBetLogResultFish getHitResult(String ctsGetHitResultDataString) throws TableException; // 取得打擊結果
    Optional<IGameBetLogResultFish> calculateFishReturnResult(); // 計算返還結果

    //* 亂數相關 *//
    void setRngData(String rngDataString); // 設定亂數
}
