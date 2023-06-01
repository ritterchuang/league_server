package com.lx.gs.skillTreeGtr.core;

import com.lx.gs.skillTreeGtr.core.module.javaPrinter.SkillTreeJavaGtr;

// 抽象技巧樹產生器
public abstract class AbstractSkillTreeGtr {
    protected final SkillTreeJavaGtr skillTreeJavaGtr; // 技巧樹 JAVA檔打印器

    protected AbstractSkillTreeGtr() {
        this.skillTreeJavaGtr = new SkillTreeJavaGtr();
    }
}
