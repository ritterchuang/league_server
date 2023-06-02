package org.lsj.gs.math.core.card.betCtr.qzBetCtr;

import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;

import java.util.List;

// 下注工具包
public interface IQzBetUtil {

    // 計算 每位玩家 可下注倍數
    List<Integer> calculateCanBetRateListByPlayer(GamePlayerHlr gamePlayerHlr, QzBetCtrConfig qzBetCtrConfig, int bankChair, int playerChair, int bankRate);
}
