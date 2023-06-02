package org.lsj.gs.skillTreeGtr.games.modelSkill.module.actionExecutor;

import org.lsj.gs.skillTreeGtr.core.module.actionExecutor.AbstractActionExecutor;
import org.lsj.gs.skillTreeGtr.games.modelSkill.entity.tablePhase.TablePhaseModelSkill;
import org.lsj.gs.skillTreeGtr.games.modelSkill.module.gameEndChecker.GameEndChecker;
import org.lsj.gs.skillTreeGtr.games.modelSkill.module.scoreCtr.ScoreCtr;
import org.lsj.gs.skillTreeGtr.games.modelSkill.module.validBetCtr.ValidBetCtr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 動作執行器
public class ActionExecutor extends AbstractActionExecutor {
    private final ValidBetCtr validBetCtr; // 有效投注計算器
    private final GameEndChecker gameEndChecker; // 遊戲結束判斷器
    private final ScoreCtr scoreCtr; // 得分計算器

    public ActionExecutor() {
        this.validBetCtr = new ValidBetCtr();
        this.gameEndChecker = new GameEndChecker();
        this.scoreCtr = new ScoreCtr();
    }

    // 執行動作
    public TablePhaseModelSkill[] execute(
            TablePhaseModelSkill root,
            int turn,
            int turnCount,
            int[] actionArray,
            int[] selectedActionArray) {
        List<TablePhaseModelSkill> resultList = new ArrayList<>();

        Arrays.stream(selectedActionArray).forEach(selectedAction ->
                        resultList.add(this.calculateExecutePhase(root, turn, turnCount, actionArray, selectedAction))
                );

        return resultList.toArray(TablePhaseModelSkill[]::new);
    }

    private TablePhaseModelSkill calculateExecutePhase(TablePhaseModelSkill root, int turn, int turnCount, int[] actionArray, int selectedAction){
        // 1. 計算鑑值
        String key = String.valueOf(selectedAction);

        // 2. 計算有效投注
        int validBet = this.validBetCtr.calculateValidBet(root, turnCount, selectedAction);

        // 3. 計算遊戲結束標誌
        boolean gameEndFlag = this.gameEndChecker.calculateGameEndFlag(turnCount, selectedAction);

        // 4. 計算遊戲得分
        int[] scoreArray = this.scoreCtr.calculateScoreArray(root, validBet, gameEndFlag);

        return new TablePhaseModelSkill(
                key,
                root.getCardPointArray(),
                turn,
                turnCount,
                actionArray,
                root.getBaseScore(),
                validBet,
                gameEndFlag,
                scoreArray
        );
    }
}
