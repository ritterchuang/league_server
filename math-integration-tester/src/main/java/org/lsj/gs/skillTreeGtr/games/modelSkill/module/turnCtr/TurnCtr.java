package org.lsj.gs.skillTreeGtr.games.modelSkill.module.turnCtr;

import org.lsj.gs.skillTreeGtr.core.module.turnCtr.AbstractTurnCtr;
import org.lsj.gs.skillTreeGtr.games.modelSkill.entity.ConstModelSkill;
import org.lsj.gs.skillTreeGtr.games.modelSkill.entity.tablePhase.TablePhaseModelSkill;

// 執行權計算器
public class TurnCtr extends AbstractTurnCtr {

    // 計算執行權
    public int calculateTurn(TablePhaseModelSkill root) {
        if(ConstModelSkill.TurnModelSkill.fromCode(root.getTurn()).equals(ConstModelSkill.TurnModelSkill.HUMAN)){
            return ConstModelSkill.TurnModelSkill.ROBOT.getCode();
        }

        return ConstModelSkill.TurnModelSkill.HUMAN.getCode();
    }
}
