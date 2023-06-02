package org.lsj.gs.math.games.sg_java.module.logic;

import org.lsj.gs.math.core.common.logic.ILogic;

// 三公邏輯介面
public interface ILogicSgJava extends ILogic {
    // 取得所有玩家牌型
    int[] getAllPlayerSelectTypeArray();

    // 取得指定位置的選牌牌型
    int getPlayerSelectType(int chairIndex);

    // 是否過早選牌
    boolean isEarlyPlayerSelect(int chairIndex);
}
