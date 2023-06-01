package org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity;

import org.lsj.db.GameBetLogObj;

import java.util.List;

// 遊戲下注紀錄結果介面
public interface IGameBetLogResult {
    GameBetLogObj getGameBetLogObj(); // 取得下注記錄物件
    List<Object> getDetail(); // 本局詳細資訊
    String getPersonControlLog(); // 本局個人調控更新資訊
    List<Integer> getRandomNumberUsedList(); // 取得使用過的隨機亂數列表
}
