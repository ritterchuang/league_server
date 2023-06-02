package org.lsj.gs.skillTreeGtr.games.modelSkill.module.gameEndChecker;

import org.lsj.gs.skillTreeGtr.core.module.gameEndChecker.AbstractGameEndChecker;
import org.lsj.gs.skillTreeGtr.games.modelSkill.entity.ConstModelSkill;

public class GameEndChecker extends AbstractGameEndChecker {
    private final int MAX_TURN_COUNT = 5;

    // 計算遊戲結束標誌
    public boolean calculateGameEndFlag(int turnCount, int selectedAction) {
        if(turnCount >= MAX_TURN_COUNT){
            return true;
        }

        return ConstModelSkill.ActionModelSkill.fromCode(selectedAction) != ConstModelSkill.ActionModelSkill.RAISE;
    }
}
