package org.lsj.gs.skillTreeGtr;

import org.lsj.gs.skillTreeGtr.games.modelSkill.SkillTreeGtrModelSkill;

public class mainSkillTree {
    private static int MAX_TREE_COUNT = 100;

    public static void main(String[] args) {
        new SkillTreeGtrModelSkill().generate(MAX_TREE_COUNT);
    }
}
