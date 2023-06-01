package com.lx.gs.skillTreeGtr.games.modelSkill.module.actionSelector;

import com.lx.gs.math.core.common.ConstMathCommon;
import com.lx.gs.math.core.common.table.module.tableUtil.random.IRandomUtil;
import com.lx.gs.math.core.common.table.module.tableUtil.random.JavaRandomUtil;
import com.lx.gs.skillTreeGtr.core.module.actionSelector.AbstractActionSelector;
import com.lx.gs.skillTreeGtr.games.modelSkill.entity.ConstModelSkill;
import com.lx.gs.skillTreeGtr.games.modelSkill.entity.tablePhase.TablePhaseModelSkill;

public class ActionSelector extends AbstractActionSelector {
    private final IRandomUtil randomUtil = new JavaRandomUtil();

    // 計算選擇動作
    public int[] select(TablePhaseModelSkill root, int[] actionArray) {
        if (ConstModelSkill.TurnModelSkill.fromCode(root.getTurn()) == ConstModelSkill.TurnModelSkill.HUMAN) {
            return actionArray;
        }
        return this.calculateRobotSelectedAction(root);
    }

    private int[] calculateRobotSelectedAction(TablePhaseModelSkill root) {
        if(root.getCardPointArray()[ConstModelSkill.TurnModelSkill.ROBOT.getCode()] > 8
        ){
            return new int[]{ConstModelSkill.ActionModelSkill.RAISE.getCode()};
        }

        if(root.getCardPointArray()[ConstModelSkill.TurnModelSkill.ROBOT.getCode()] > 6
//                && randomUtil.isHitWithAccuracy(0.6, ConstMathCommon.AccuracyType.MILLION)
        ){
            return new int[]{ConstModelSkill.ActionModelSkill.COMPARE.getCode()};
        }

        if(root.getCardPointArray()[ConstModelSkill.TurnModelSkill.ROBOT.getCode()] < 4
            && randomUtil.isHitWithAccuracy(0.3, ConstMathCommon.AccuracyType.MILLION)){
            return new int[]{ConstModelSkill.ActionModelSkill.RAISE.getCode()};
        }

        return new int[]{ConstModelSkill.ActionModelSkill.FOLD.getCode()};
    }
}
