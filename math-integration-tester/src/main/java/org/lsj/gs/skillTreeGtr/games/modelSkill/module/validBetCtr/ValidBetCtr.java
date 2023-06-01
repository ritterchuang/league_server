package org.lsj.gs.skillTreeGtr.games.modelSkill.module.validBetCtr;

import org.lsj.gs.skillTreeGtr.core.module.validBetCtr.AbstractValidBetCtr;
import org.lsj.gs.skillTreeGtr.games.modelSkill.entity.ConstModelSkill;
import org.lsj.gs.skillTreeGtr.games.modelSkill.entity.tablePhase.TablePhaseModelSkill;

// 有效投注計算器
public class ValidBetCtr extends AbstractValidBetCtr {

    // 計算有效押注
    public int calculateValidBet(TablePhaseModelSkill root, int turnCount, int selectedAction) {
        if(turnCount == 1){
            return this.calculateValidBetFirstTurn(root, selectedAction);
        }

        return this.calculateValidBetAfterSecondTurn(root, selectedAction);
    }

    private int calculateValidBetFirstTurn(TablePhaseModelSkill root, int selectedAction){
        switch (ConstModelSkill.ActionModelSkill.fromCode(selectedAction)){
            case RAISE:
            case COMPARE:
                return root.getValidBet() * 2;
            default: return root.getValidBet();
        }
    }

    private int calculateValidBetAfterSecondTurn(TablePhaseModelSkill root, int selectedAction){
        if (ConstModelSkill.ActionModelSkill.fromCode(selectedAction) == ConstModelSkill.ActionModelSkill.RAISE) {
            return root.getValidBet() * 2;
        }
        return root.getValidBet();
    }
}
