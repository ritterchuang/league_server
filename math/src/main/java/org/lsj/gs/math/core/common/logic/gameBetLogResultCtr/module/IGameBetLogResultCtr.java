package org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module;

// 下注記錄計算器介面
public interface IGameBetLogResultCtr {
    void updateBeginTime(); // 更新起始時間

    void addDetail(Object detail); // 新增詳細記錄

    void resetDetail(); // 重置詳細記錄

    long getBeginTime(); // 取得起始時間

    void reset(); // 重設
}
