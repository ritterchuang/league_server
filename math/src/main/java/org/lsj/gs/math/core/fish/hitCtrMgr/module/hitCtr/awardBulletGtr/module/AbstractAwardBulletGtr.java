package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 抽象獎勵子彈生產者
public abstract class AbstractAwardBulletGtr implements IAwardBulletGtr {
    protected final ITableUtil tableUtil; // 牌桌工具包

    public AbstractAwardBulletGtr(ITableUtil tableUtil) {
        this.tableUtil = tableUtil;
    }
}
