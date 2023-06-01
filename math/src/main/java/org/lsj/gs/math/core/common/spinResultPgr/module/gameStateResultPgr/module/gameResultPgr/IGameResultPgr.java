package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.gameResult.GameResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.ScreenResult;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.GameCtrResult;

// 算分結果包裝者介面
public interface IGameResultPgr {

    GameResult packageGameResult(ScreenResult screenResult, GameCtrResult gameCtrResult); // 包裝遊戲算分結果
}
