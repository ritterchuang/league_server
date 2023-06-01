package org.lsj.gs.skillTreeGtr.games.modelSkill.module.rootGtr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.IRandomUtil;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.JavaRandomUtil;
import org.lsj.gs.skillTreeGtr.core.module.rootGtr.AbstractRootGtr;
import org.lsj.gs.skillTreeGtr.games.modelSkill.entity.ConstModelSkill;
import org.lsj.gs.skillTreeGtr.games.modelSkill.entity.tablePhase.TablePhaseModelSkill;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 模板 根產生器
public class RootGtrModelSkill extends AbstractRootGtr {
    private final IRandomUtil randomUtil;

    public RootGtrModelSkill() {
        this.randomUtil = new JavaRandomUtil();
    }

    // 產生根
    public TablePhaseModelSkill generateRoot() {
        return new TablePhaseModelSkill(
            "level-0-root",
                this.calculateCardPoint(),
                this.calculateTurn(),
                1,
                new int[]{
                        ConstModelSkill.ActionModelSkill.FOLD.getCode(),
                        ConstModelSkill.ActionModelSkill.COMPARE.getCode(),
                        ConstModelSkill.ActionModelSkill.RAISE.getCode()
                },
                5,
                5,
                false,
                new int[]{0,0}
        );
    }

    private int[] calculateCardPoint() {
        List<Integer> cardPointList = IntStream.range(0,10).boxed().collect(Collectors.toList());

        this.randomUtil.shuffleList(cardPointList);

        return new int[]{cardPointList.get(0), cardPointList.get(1)};
    }

    private int calculateTurn(){
        return this.randomUtil.getRandomElement(new ArrayList<>(){{
            add(ConstModelSkill.TurnModelSkill.HUMAN.getCode());
            add(ConstModelSkill.TurnModelSkill.ROBOT.getCode());
        }},
                ConstMathCommon.AccuracyType.A32768);
    }
}
