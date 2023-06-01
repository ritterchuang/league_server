package com.lx.gs.mathStr.core.module.playStr;

import com.lx.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import com.lx.gs.mathBoardGtr.core.slot.IBoardGtrSlot;
import com.lx.gs.mathStr.config.entity.ControlAlgorithmConfig;
import com.lx.gs.mathStr.core.entity.GamePlayerSimulation;
import com.lx.gs.mathStr.core.entity.playStr.BoardGtrResult;
import com.lx.gs.mathStr.core.entity.playStr.playStrResult.PlayStrResult;
import com.lx.gs.mathStr.core.entity.playStr.playStrResult.PlayStrResultSlot;
import com.lx.gs.mathStr.core.module.poolCltr.PoolCtr;

import java.util.ArrayList;
import java.util.List;

// 老虎遊戲模擬器
public class PlayStrSlot extends AbstractPlayStr {
    public PlayStrSlot(GamePlayerSimulation gamePlayerSimulation, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
        super(gamePlayerSimulation, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
    }

    @Override
    public PlayStrResult run() {
        // 1. 計算局產生器結果陣列
        List<BoardGtrResult> result = new ArrayList<>();

        // 2. 生成局產生器
        IBoardGtrSlot boardGtrSlot = super.boardGtrFactory.createBoardGtrSlot();

        do {
            // 3. 產生局結果
            BoardGtrResult boardGtrResult = boardGtrSlot.calculateOneBoardResult();

            // 4. 更新資訊
            boardGtrSlot.update(boardGtrResult);

            // 5. 紀錄結果
            result.add(boardGtrResult);

            // 6. 判斷是否結束
        } while (boardGtrSlot.canPlay());

        // 7. 封裝遊戲模擬器結果
        return new PlayStrResultSlot(result);
    }
}
