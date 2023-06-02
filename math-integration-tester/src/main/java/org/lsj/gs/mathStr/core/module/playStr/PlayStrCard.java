package org.lsj.gs.mathStr.core.module.playStr;

import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathBoardGtr.core.card.IBoardGtrCard;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;
import org.lsj.gs.mathStr.core.entity.GamePlayerSimulation;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.playStrResult.PlayStrResult;
import org.lsj.gs.mathStr.core.entity.playStr.playStrResult.PlayStrResultCard;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;

import java.util.ArrayList;
import java.util.List;

// 卡牌遊戲模擬器
public class PlayStrCard extends AbstractPlayStr {
    public PlayStrCard(GamePlayerSimulation gamePlayerSimulation, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
        super(gamePlayerSimulation, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
    }

    @Override
    public PlayStrResult run() {
        // 1. 計算局產生器結果陣列
        List<BoardGtrResult> result = new ArrayList<>();

        while (true) {
            // 2. 生成局產生器
            IBoardGtrCard boardGtrCard = super.boardGtrFactory.createBoardGtrCard();

            // 3. 產生局結果
            BoardGtrResult boardGtrResult = boardGtrCard.calculateOneBoardResult();

            // 4. 更新資訊
            boardGtrCard.update(boardGtrResult);

            // 5. 紀錄結果
            result.add(boardGtrResult);

            // 6. 判斷是否結束
            if (!boardGtrCard.canPlay()) {
                break;
            }
        }

        // 7. 封裝遊戲模擬器結果
        return new PlayStrResultCard(result);
    }
}
