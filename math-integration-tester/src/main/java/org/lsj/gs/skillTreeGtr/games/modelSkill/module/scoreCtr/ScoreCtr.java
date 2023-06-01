package org.lsj.gs.skillTreeGtr.games.modelSkill.module.scoreCtr;

import org.lsj.gs.skillTreeGtr.core.module.scoreCtr.AbstractScoreCtr;
import org.lsj.gs.skillTreeGtr.games.modelSkill.entity.ConstModelSkill;
import org.lsj.gs.skillTreeGtr.games.modelSkill.entity.tablePhase.TablePhaseModelSkill;

public class ScoreCtr extends AbstractScoreCtr {
    public int[] calculateScoreArray(TablePhaseModelSkill root, int validBet, boolean gameEndFlag) {
        if(!gameEndFlag){
            return new int[]{0, 0};
        }

        // 1. 計算輸贏標誌
        boolean humanWinFlag = this.calculateHumanWinFlag(root);

        // 2. 計算贏分
        if(humanWinFlag){
            return new int[]{validBet, -1 * validBet};
        }

        return new int[]{ -1 * validBet, validBet};
    }

    // 計算輸贏標誌
    private boolean calculateHumanWinFlag(TablePhaseModelSkill root) {
        return root.getCardPointArray()[ConstModelSkill.TurnModelSkill.HUMAN.getCode()] > root.getCardPointArray()[ConstModelSkill.TurnModelSkill.ROBOT.getCode()];
    }
}
