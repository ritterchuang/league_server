package com.lx.gs.mathStr.core.module.playStr;

import com.lx.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import com.lx.gs.mathBoardGtr.core.fish.IBoardGtrFish;
import com.lx.gs.mathStr.config.entity.ControlAlgorithmConfig;
import com.lx.gs.mathStr.core.entity.GamePlayerSimulation;
import com.lx.gs.mathStr.core.entity.playStr.BoardGtrResult;
import com.lx.gs.mathStr.core.entity.playStr.BoardGtrReturnResult;
import com.lx.gs.mathStr.core.entity.playStr.playStrResult.PlayStrResult;
import com.lx.gs.mathStr.core.entity.playStr.playStrResult.PlayStrResultFish;
import com.lx.gs.mathStr.core.module.poolCltr.PoolCtr;

import java.util.ArrayList;
import java.util.List;

// 魚機遊戲模擬器
public class PlayStrFish extends AbstractPlayStr {
    public PlayStrFish(GamePlayerSimulation gamePlayerSimulation, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
        super(gamePlayerSimulation, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
    }

    @Override
    public PlayStrResult run() {
        // 1. 計算局產生器結果陣列
        List<BoardGtrResult> result = new ArrayList<>();

        // 2. 生成局產生器
        IBoardGtrFish boardGtrFish = super.boardGtrFactory.createBoardGtrFish();

        do {
            // 3. 產生局結果
            BoardGtrResult boardGtrResult = boardGtrFish.calculateOneBoardResult();

            // 4. 更新資訊
            boardGtrFish.update(boardGtrResult);

            // 5. 紀錄結果
            result.add(boardGtrResult);

            // 6. 判斷是否結束
        } while (boardGtrFish.canPlay());

        // 7. 取得返還資訊
        BoardGtrReturnResult fishReturnResult = boardGtrFish.getReturnResult();

        // 8. 更新返還資訊
        boardGtrFish.updateReturnResult(fishReturnResult);

        // 9. 封裝遊戲模擬器結果
        return new PlayStrResultFish(result, fishReturnResult);
    }

}
