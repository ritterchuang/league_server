package org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.lsj.db.GameBetLogObj;
import org.lsj.gs.math.core.common.gameFlowHlr.enity.result.GameFlowHlrResult;

import java.util.List;

// 遊戲下注紀錄結果老虎機
public class GameBetLogResultSlot extends AbstractGameBetLogResult implements IGameBetLogResultSlot {
    @JsonIgnore
    private final GameFlowHlrResult gameFlowHlrResult; // 遊戲流程處理者結果

    public GameBetLogResultSlot(
            GameBetLogObj gameBetLogObj,
            List<Object> detail, String personControlLog, List<Integer> randomNumberUsedList,
            GameFlowHlrResult gameFlowHlrResult
    ) {
        super(gameBetLogObj, detail, personControlLog, randomNumberUsedList);
        this.gameFlowHlrResult = gameFlowHlrResult;
    }

    // 取得遊戲流程處理者結果
    @Override
    public GameFlowHlrResult getGameFlowHlrResult() {
        return gameFlowHlrResult;
    }
}
