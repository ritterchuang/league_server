package com.lx.gs.skillTreeGtr.games.modelSkill.module.actionGtr;

import com.lx.gs.skillTreeGtr.core.module.actionGtr.AbstractActionGtr;
import com.lx.gs.skillTreeGtr.games.modelSkill.entity.tablePhase.TablePhaseModelSkill;

// 動作產生器
public class ActionGtr extends AbstractActionGtr {

    // 計算動作陣列
    public int[] calculateActionArray(TablePhaseModelSkill root, int turnCount) {
        if(turnCount == 5){
            return new int[]{};
        }

        return root.getActionArray();
    }
}
